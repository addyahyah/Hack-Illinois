package backEndApi.framework;

import java.util.Date;

/**
 * Created by persinme on 2/20/2016.
 */
public interface IReview {
    public String getText();
    public Date getDate();
    public void edit(String str);
    public IUser getAuthor();
    public IUser getReviewee();
    public int getRate();

}
