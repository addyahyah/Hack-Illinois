package backEndApi.Implementation;

import java.util.Map;

import backEndApi.framework.AbstractPost;
import backEndApi.framework.IUser;

/**
 * Created by persinme on 2/20/2016.
 */
public class Post extends AbstractPost {
    public Post(Map<String, Object> postMap) {
        super(postMap);
    }

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
