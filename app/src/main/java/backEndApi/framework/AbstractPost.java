package backEndApi.framework;

import java.util.Observable;

/**
 * Created by yangr on 2/20/2016.
 */
<<<<<<< HEAD
public class AbstractPost extends Observable implements IPost {
=======
public abstract class AbstractPost extends Observable implements IPost {
    private final int barginTime = 180; //in seconds
    protected IUser author;
    protected IUser seller;
    protected String description;
    protected double price;
    protected List<IReply> replies;
    protected Date creationTime;
    protected Date expirationTime;
    protected boolean hasTransactionComplete;

    public AbstractPost(Map<String, Object> postMap){
        this.author = (IUser)postMap.get("author");
        this.description = (String) postMap.get("description");
        this.price = (double) postMap.get("price");
        this.seller = (IUser) postMap.get("seller");
        this.replies = (List<IReply>) postMap.get("replies");
        this.creationTime = (Date) postMap.get("creationDate");
        this.expirationTime = (Date) postMap.get("expirationTime");
        this.hasTransactionComplete = (boolean) postMap.get("hasTransactionComplete");
    }
>>>>>>> 551b81aa237341bbc5b1c4b0d555d3dea30ddfcb

    @Override
    public IUser getAuthor() {
        return this.author;
    }

}
