package backEndApi.framework;

import java.util.Date;

public interface IReply {
    public String getText();
    public Date getDate();
    public void edit(String str);
    public IUser getAuthor();
    public IUser getReviewee();

    void setRate();
}
