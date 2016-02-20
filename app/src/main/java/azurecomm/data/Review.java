package azurecomm.data;

/**
 * Created by persinme on 2/20/2016.
 */
public class Review {

    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("PostID")
    private String mPostID;

    @com.google.gson.annotations.SerializedName("LeftByBuyer")
    private boolean mLeftByBuyer;

    @com.google.gson.annotations.SerializedName("Rating")
    private int mRating;

    @com.google.gson.annotations.SerializedName("Review")
    private String mReview;

    public Review() {}

    public Review(String id, String postID, boolean buyer, int rating, String review) {
        this.setId(id);
        this.setPostId(postID);
        this.setLeftByBuyer(buyer);
        this.setRating(rating);
        this.setReview(review);
    }

    public void setId(String id) { this.mId = id; }
    public String getId() { return this.mId; }

    public void setPostId(String PostID) { this.mPostID = PostID; }
    public String getPostId() { return this.mPostID; }

    public void setLeftByBuyer(boolean buyer) { this.mLeftByBuyer = buyer; }
    public boolean getLeftByBuyer() { return this.mLeftByBuyer; }

    public void setRating(int rating) { this.mRating = rating; }
    public int getRating() { return this.mRating; }

    public void setReview(String review) { this.mReview = review; }
    public String getReview() { return this.mReview; }
}
