package backEndApi.Api;

import android.app.Activity;
import android.net.Uri;
import android.util.JsonReader;

import com.example.hackillinois0219.ToDoItem;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOrder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import azurecomm.data.Post;
import azurecomm.data.PostBuyer;
import azurecomm.data.PostReplies;
import azurecomm.data.PostSeller;
import azurecomm.data.Reply;
import azurecomm.data.Review;
import azurecomm.data.UserReplies;
import backEndApi.Implementation.User;
import backEndApi.framework.IPost;
import backEndApi.framework.IReply;
import backEndApi.framework.IReview;
import backEndApi.framework.IUser;

/**
 * Created by persinme on 2/20/2016.
 */
public class BucksInstance {
    private IUser owner;
    /**
     * Mobile Service Client reference
     */
    private MobileServiceClient mClient;

    /**
     * Mobile Service Tables used to access data
     */
    private MobileServiceTable<azurecomm.data.User> mUserTable;
    private MobileServiceTable<Post> mPostTable;
    private MobileServiceTable<Review> mReviewTable;
    private MobileServiceTable<Reply> mReplyTable;
    private MobileServiceTable<PostBuyer> mPostBuyerTable;
    private MobileServiceTable<PostSeller> mPostSellerTable;
    private MobileServiceTable<PostReplies> mPostRepliesTable;
    private MobileServiceTable<UserReplies> mUserRepliesTable;


    private Collection<IPost> posts;
    private Collection<IPost> feed;
    private Collection<IReview> reviews;
    private static BucksInstance ourInstance;

    public static BucksInstance getInstance(Activity activity, Profile profile) {
        if(ourInstance == null) {
            ourInstance = new BucksInstance(activity, profile);
        }
        return ourInstance;
    }

    private void setupTables() {
        this.mUserTable = this.mClient.getTable("User", azurecomm.data.User.class);
        this.mPostTable = this.mClient.getTable("Post", azurecomm.data.Post.class);
        this.mReviewTable = this.mClient.getTable("Review", azurecomm.data.Review.class);
        this.mReplyTable = this.mClient.getTable("Reply", azurecomm.data.Reply.class);
        this.mPostBuyerTable = this.mClient.getTable("Post_Buyer", azurecomm.data.PostBuyer.class);
        this.mPostSellerTable = this.mClient.getTable("Post_Seller", azurecomm.data.PostSeller.class);
        this.mPostRepliesTable = this.mClient.getTable("Post_Replies", azurecomm.data.PostReplies.class);
        this.mUserRepliesTable = this.mClient.getTable("User_Replies", azurecomm.data.UserReplies.class);
    }

    private Profile parseProfile(GraphResponse response) {
        JSONObject toParse = response.getJSONObject();
        String id = null;
        String first = null;
        String middle = null;
        String last = null;
        String name = null;
        android.net.Uri linkURI = null;
        try {
            id = toParse.getString("fb:profile_id");
            first = toParse.getString("profile:first_name");
            middle = "";
            last = toParse.getString("profile:last_name");
            name = toParse.getString("name");
            linkURI = Uri.parse(toParse.getString("og:URL"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Profile p = new Profile(id, first, middle, last, name, linkURI);
        return p;
    }

    public IUser getUser(String fbid) {
        IUser toReturn = null;
        try {
            azurecomm.data.User azureUser = mUserTable.lookUp(fbid).get();
            final backEndApi.Implementation.User user = new User(azureUser);
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/" + fbid + "/",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            user.setProfile(parseProfile(response));
                        }
                    }).executeAndWait();
            toReturn = user;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    private void getIPostListFromPostList(List<Post> postList, Collection<IPost> posts) throws ExecutionException, InterruptedException {
        List<PostBuyer> postBuyer;
        List<PostSeller> postSeller;
        List<PostReplies> postRepliesList;
        for(Post p : postList) {
            postBuyer = mPostBuyerTable.where().field("PostID").eq(p.getId()).top(1).execute().get();
            postSeller = mPostSellerTable.where().field("PostID").eq(p.getId()).top(1).execute().get();
            postRepliesList = mPostRepliesTable.where().field("PostID").eq(p.getId()).execute().get();
            Map<String,Object> postMap = new HashMap<String, Object>();
            postMap.put("id", p.getId());
            postMap.put("author", this.getUser(postBuyer.get(0).getFacebookId()));
            postMap.put("description", p.getDescription());
            postMap.put("price", p.getPrice());
            if(postSeller.size() == 0) { // no seller
                postMap.put("seller", null);
            } else {
                postMap.put("seller", this.getUser(postSeller.get(0).getFacebookId()));
            }
            List<IReply> iReplyList = new ArrayList<IReply>();
            // need to do replies yet
            postMap.put("replies", iReplyList);
            postMap.put("creationTime", new Date(p.getPostedTime().getTime()));
            postMap.put("expirationTime", new Date(p.getExpirationTime().getTime()));
            postMap.put("bargainTime", p.getBargainTime());
            postMap.put("hasTransactionComplete", Calendar.getInstance().getTimeInMillis() > p.getExpirationTime().getTime());
            IPost toAdd = new backEndApi.Implementation.Post(postMap);
            this.feed.add(toAdd);
        }
    }

    private BucksInstance(Activity activity, Profile profile) {
        this.setupTables();
        this.posts = new ArrayList<IPost>();
        this.feed = new ArrayList<IPost>();
        try {
            // set up owner
            this.mClient = new MobileServiceClient("https://hackillinois0219.azurewebsites.net", activity);
            owner = this.getUser(profile.getId());

            // set up feed
            List<Post> postList = mPostTable.orderBy("PostedTime", QueryOrder.Ascending).top(10).execute().get();
            this.getIPostListFromPostList(postList, this.feed);

            // set up profile posts - this is REALLY inefficient and could use some optimization
            List<PostBuyer> postBuyerList = mPostBuyerTable.where().field("FacebookID").eq(owner.getId()).execute().get();
            postList = new ArrayList<Post>();
            for(PostBuyer pb : postBuyerList) {
                postList.add(mPostTable.lookUp(pb.getPostId()).get());
            }
            this.getIPostListFromPostList(postList, this.posts);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Collection<IReview> getReviews(){
        return this.reviews;
    }

    public IUser getOwner(){
        return this.owner;
    }

    public Collection<IPost> getPosts(){
        return this.posts;
    }

    public void createPost(Map<String, Object> postmap) {
        owner.createPost(postmap);
    }

}
