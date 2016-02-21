package backEndApi.Implementation;

import java.util.Date;

import backEndApi.framework.IReview;
import backEndApi.framework.IUser;

/**
 * Created by Joe on 2/21/2016.
 */
public class Review implements IReview {
    private String content;
    private Date reviewDate;
    private IUser author;
    private IUser reviewee;
    private int rate;
    public Review(String content, Date reviewDate, IUser author, IUser reviewee, int rate){
        this.content = content;
        this.reviewDate = reviewDate;
        this.author = author;
        this.reviewee = reviewee;
        this.rate = rate;
    }
    @Override
    public String getText() {
        return content;
    }

    @Override
    public Date getDate() {
        return reviewDate;
    }

    @Override
    public void edit(String str) {
        this.content = str;
    }

    @Override
    public IUser getAuthor() {
        return author;
    }

    @Override
    public IUser getReviewee() {
        return reviewee;
    }

    @Override
    public int getRate() {
        return rate;
    }
}
