package azurecomm.data;

/**
 * Created by persinme on 2/20/2016.
 */
public class PostSeller {

    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("PostID")
    private String mPostId;

    @com.google.gson.annotations.SerializedName("FacebookID")
    private String mFacebookId;

    @com.google.gson.annotations.SerializedName("Price")
    private float mPrice;

    public PostSeller() {}

    public PostSeller(String id, String postId, String facebookId, float price) {
        this.setId(id);
        this.setPostID(postId);
        this.setFacebookId(facebookId);
        this.setPrice(price);
    }

    public void setId(String id) { this.mId = id; }
    public String getId() { return this.mId; }

    public void setPostID(String postid) { this.mPostId = postid; }
    public String getPostId() { return this.mPostId; }

    public void setFacebookId(String facebookId) { this.mFacebookId = facebookId; }
    public String getFacebookId() { return this.mFacebookId; }

    public void setPrice(float price) { this.mPrice = price; }
    public float getPrice() { return this.mPrice; }
}
