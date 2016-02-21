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
    azurecomm.data.User azureData;

    public User(azurecomm.data.User azureData) {
        this.azureData = azureData;
    }

    public User(Profile profile, azurecomm.data.User azureData){
        this.owner = profile;
        this.azureData = azureData;
    }

    public void setProfile(Profile p) { this.owner = p; }

    public float getBuyerRating() { return azureData.getBuyerRating(); }
    public float getSellerRating() { return azureData.getSellerRating(); }
    public int getBuyerRatingCount() { return azureData.getBuyerRatingCount(); }
    public int getSellerRatingCount() { return azureData.getSellerRatingCount(); }
    public String getId() { return owner.getId(); }
    public String getName() { return owner.getName(); }
    public Profile getProfile() { return owner; }

    @Override
    public IPost createPost(Map<String, Object> postMap){
        return  new Post(postMap);
    }


}
