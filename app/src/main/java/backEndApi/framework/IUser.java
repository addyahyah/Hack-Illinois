package backEndApi.framework;

import com.facebook.Profile;

import java.util.Map;

/**
 * Created by yangr on 2/20/2016.
 */
public interface IUser {
    public IPost createPost(Map<String, Object> postMap);
    public Profile getProfile();

    public float getBuyerRating();
    public float getSellerRating();
    public int getBuyerRatingCount();
    public int getSellerRatingCount();
    public String getId();
    public String getName();
}
