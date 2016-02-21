package backEndApi.Implementation;

import java.util.Date;

import backEndApi.framework.IPost;
import backEndApi.framework.IReply;
import backEndApi.framework.IUser;

public class Reply implements IReply {
    private IUser author;
    private IPost post;
    private String content;
    private boolean isBuyer;
    private Date creationTime;

    public Reply(IUser author, IPost post, String content, boolean isBuyer, Date creationTime){
        this.author = author;
        this.post = post;
        this.content = content;
        this.isBuyer = isBuyer;
        this.creationTime = creationTime;
    }

    @Override
    public String getText() {
        return content;
    }

    @Override
    public Date getDate() {
        return creationTime;
    }

    @Override
    public void edit(String content) {
        this.content = content;
    }

    @Override
    public IUser getAuthor() {
        return this.author;
    }

    @Override
    public IUser getReviewee() {
        return this.post.getAuthor();
    }

    @Override
    public void setRate() {

    }
}
