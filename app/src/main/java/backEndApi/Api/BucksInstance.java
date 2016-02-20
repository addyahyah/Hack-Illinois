package backEndApi.Api;

import java.util.Collection;
import java.util.Map;

import backEndApi.Implementation.User;
import backEndApi.framework.IPost;
import backEndApi.framework.IReview;
import backEndApi.framework.IUser;

/**
 * Created by persinme on 2/20/2016.
 */
public class BucksInstance {
    private IUser owner;
    private Collection<IPost> posts;
    private Collection<IReview> reviews;
    private static BucksInstance ourInstance = new BucksInstance();

    public static BucksInstance getInstance() {
        return ourInstance;
    }

    private BucksInstance() {
        owner = new User("username", "password");
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
