package backEndApi.framework;

import java.util.Observable;

/**
 * Created by yangr on 2/20/2016.
 */
public interface IPost {
    public void reply(IUser author, String content);

    public void bid(IUser bidder, int amount);

    public void pay(IUser buyer, IUser seller);

    public IUser getAuthor();
}
