package backEndApi.Implementation;

import com.facebook.Profile;

import java.math.BigInteger;
import java.util.Map;

import backEndApi.framework.IPost;
import backEndApi.framework.IUser;

/**
 * Created by persinme on 2/20/2016.
 */
public class User implements IUser{
    Profile owner;
    String facebookId;
    String name;

    public User(String userName, String password){
//     this.facebookId = dbconnectoin get facebookID
//     this.name = getName and stuff
    }
    @Override
    public IPost createPost(Map<String, Object> postMap){
        return  new Post(postMap);
    }


}
