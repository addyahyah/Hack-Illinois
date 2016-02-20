package backEndApi.framework;

import java.util.Observable;

/**
 * Created by yangr on 2/20/2016.
 */
public class AbstractPost extends Observable implements IPost {

    @Override
    public void reply(IUser author, String content) {

    }

    @Override
    public void bid(IUser bidder, int amount) {

    }

    @Override
    public void pay(IUser buyer, IUser seller) {

    }
}
