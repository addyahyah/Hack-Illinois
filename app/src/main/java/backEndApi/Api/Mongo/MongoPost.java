package backEndApi.Api.Mongo;

import java.util.Map;

import backEndApi.framework.AbstractPost;
import backEndApi.framework.IUser;

/**
 * Created by yangr on 2/21/2016.
 */
public class MongoPost extends AbstractPost {

    public MongoPost(Map<String, Object> postMap) {
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
