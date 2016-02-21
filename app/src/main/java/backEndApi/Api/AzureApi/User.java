package backEndApi.Api.AzureApi;

/**
 * Created by persinme on 2/20/2016.
 */
public class User {
    /**
     * User facebook id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    /**
     * BuyerRating
     */
    @com.google.gson.annotations.SerializedName("BuyerRating")
    private float mBuyerRating;

    /**
     * BuyerRatingCount
     */
    @com.google.gson.annotations.SerializedName("BuyerRatingCount")
    private int mBuyerRatingCount;

    /**
     * SellerRating
     */
    @com.google.gson.annotations.SerializedName("SellerRating")
    private float mSellerRating;

    /**
     * SellerRatingCount
     */
    @com.google.gson.annotations.SerializedName("SellerRatingCount")
    private int mSellerRatingCount;

    public User() {}
    public User(String id, float buyerRating, int buyerRatingCount, float sellerRating, int sellerRatingCount ) {
        this.setFacebookID(id);
        this.setBuyerRating(buyerRating);
        this.setBuyerRatingCount(buyerRatingCount);
        this.setSellerRating(sellerRating);
        this.setSellerRatingCount(sellerRatingCount);
    }

    public void setFacebookID(String id) { this.mId = id; }
    public String getFacebookID() { return this.mId; }

    public void setBuyerRating(float newRating) { this.mBuyerRating = newRating; }
    public float getBuyerRating() { return this.mBuyerRating; }

    public void setBuyerRatingCount(int newCount) { this.mBuyerRatingCount = newCount; }
    public int getBuyerRatingCount() { return this.mBuyerRatingCount; }

    public void setSellerRating(float newRating) { this.mSellerRating = newRating; }
    public float getSellerRating() { return this.mSellerRating; }

    public void setSellerRatingCount(int newCount) { this.mSellerRatingCount = newCount; }
    public int getSellerRatingCount() { return this.mSellerRatingCount; }


    @Override
    public boolean equals(Object o) {
        return o instanceof User && ((User) o).mId == this.mId;
    }
}
