package backEndApi.Api;

import java.util.Collection;
import java.util.Map;

import backEndApi.framework.IPost;
import backEndApi.framework.IReview;
import backEndApi.framework.IUser;

/**
 * Created by persinme on 2/20/2016.
 */
public class UserProfile {
    private IUser owner;
    private Collection<IPost> posts;
    private Collection<IReview> reviews;
    private static UserProfile ourInstance = new UserProfile();

    public static UserProfile getInstance() {
        return ourInstance;
    }

    private UserProfile() {
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
