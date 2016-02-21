package backEndApi.Api;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.JsonReader;
import android.util.Log;

import com.example.hackillinois0219.ToDoItem;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOrder;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;

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
        Log.d("AZURE", "getUser called");
        IUser toReturn = null;
        azurecomm.data.User azureUser = null;
        Log.d("AZURE", "Entered try");
        try {
            azureUser = mUserTable.lookUp(fbid).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("AZURE", "Looked up azureUser");
        if(azureUser == null) {
            Log.d("AZURE", "azureUser is null!");
            azureUser = new azurecomm.data.User(fbid, 0.0f, 0, 0.0f, 0);
            Log.d("AZURE", "created new azureUser");
            mUserTable.insert(azureUser);
            Log.d("AZURE", "inserted azureUser");
        }
        Log.d("AZURE", "azureUser exists");
        final User user = new User(azureUser);
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + fbid + "/",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.d("AZURE", "Graph request complete");
                        user.setProfile(parseProfile(response));
                    }
                }).executeAsync();
        toReturn = user;
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
        this.posts = new ArrayList<IPost>();
        this.feed = new ArrayList<IPost>();
        try {
            // set up owner
            this.mClient = new MobileServiceClient("https://hackillinois0219.azurewebsites.net", activity);
            Log.d("AZURE", "Client setup");
            this.setupTables();
            Log.d("AZURE", "Tables setup");
            this.initLocalStore().get();
            Log.d("AZURE", "Local store setup");
            owner = this.getUser(profile.getId());
            Log.d("AZURE", "Got owner set");

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


        } catch (MalformedURLException | MobileServiceLocalStoreException | InterruptedException | ExecutionException e) {
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

    private AsyncTask<Void, Void, Void> initLocalStore() throws MobileServiceLocalStoreException, ExecutionException, InterruptedException {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    MobileServiceSyncContext syncContext = mClient.getSyncContext();

                    if (syncContext.isInitialized())
                        return null;

                    SQLiteLocalStore localStore = new SQLiteLocalStore(mClient.getContext(), "OfflineStore", null, 1);

                    Map<String, ColumnDataType> tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("BuyerRating", ColumnDataType.Real);
                    tableDefinition.put("BuyerRatingCount", ColumnDataType.Integer);
                    tableDefinition.put("SellerRating", ColumnDataType.Real);
                    tableDefinition.put("SellerRatingCount", ColumnDataType.Integer);
                    localStore.defineTable("User", tableDefinition);

                    tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("Title", ColumnDataType.String);
                    tableDefinition.put("Description", ColumnDataType.String);
                    tableDefinition.put("Price", ColumnDataType.Real);
                    tableDefinition.put("LocationLat", ColumnDataType.Real);
                    tableDefinition.put("LocationLong", ColumnDataType.Real);
                    tableDefinition.put("BargainTime", ColumnDataType.Integer);
                    tableDefinition.put("PostedTime", ColumnDataType.Date);
                    tableDefinition.put("ExpirationTime", ColumnDataType.Date);
                    localStore.defineTable("Post", tableDefinition);

                    tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("PostID", ColumnDataType.String);
                    tableDefinition.put("FacebookID", ColumnDataType.String);
                    localStore.defineTable("Post_Buyer", tableDefinition);

                    tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("ReplyID", ColumnDataType.String);
                    tableDefinition.put("PostID", ColumnDataType.String);
                    localStore.defineTable("Post_Replies", tableDefinition);

                    tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("PostID", ColumnDataType.String);
                    tableDefinition.put("FacebookID", ColumnDataType.String);
                    tableDefinition.put("Price", ColumnDataType.Real);
                    localStore.defineTable("Post_Seller", tableDefinition);

                    tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("Message", ColumnDataType.String);
                    tableDefinition.put("Time", ColumnDataType.Date);
                    localStore.defineTable("Reply", tableDefinition);

                    tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("PostID", ColumnDataType.String);
                    tableDefinition.put("LeftByBuyer", ColumnDataType.Boolean);
                    tableDefinition.put("Rating", ColumnDataType.Integer);
                    tableDefinition.put("Review", ColumnDataType.String);
                    localStore.defineTable("Review", tableDefinition);

                    tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("ReplyID", ColumnDataType.String);
                    tableDefinition.put("FacebookID", ColumnDataType.String);
                    localStore.defineTable("User_Replies", tableDefinition);

                    SimpleSyncHandler handler = new SimpleSyncHandler();

                    syncContext.initialize(localStore, handler).get();

                } catch (final Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        return runAsyncTask(task);
    }

    /**
     * Run an ASync task on the corresponding executor
     * @param task
     * @return
     */
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }
}
