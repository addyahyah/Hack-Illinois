package backEndApi.framework;

import java.util.Date;

/**
 * Created by persinme on 2/20/2016.
 */
public interface IReply {
    public String getText();
    public Date getDate();
    public void Edit(String str);
    public IUser getAuthor();
    public IUser getReviewer();
}
