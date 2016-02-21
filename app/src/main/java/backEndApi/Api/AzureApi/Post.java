package backEndApi.Api.AzureApi;

import java.sql.Timestamp;

/**
 * Created by persinme on 2/20/2016.
 */
public class Post {

    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("Title")
    private String mTitle;

    @com.google.gson.annotations.SerializedName("Description")
    private String mDescription;

    @com.google.gson.annotations.SerializedName("Price")
    private float mPrice;

    @com.google.gson.annotations.SerializedName("LocationLat")
    private float mLocationLat;

    @com.google.gson.annotations.SerializedName("LocationLong")
    private float mLocationLong;

    @com.google.gson.annotations.SerializedName("BargainTime")
    private int mBargainTime;

    @com.google.gson.annotations.SerializedName("PostedTime")
    private java.sql.Timestamp mPostedTime;

    @com.google.gson.annotations.SerializedName("ExpirationTime")
    private java.sql.Timestamp mExpirationTime;

    public Post() {}

    public Post(String id, String title, String desc, float price, float locationLat, float locationLong, Timestamp postedTime, int bargainTime, Timestamp expireTime) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(desc);
        this.setPrice(price);
        this.setLocationLat(locationLat);
        this.setLocationLong(locationLong);
        this.setPostedTime(postedTime);
        this.setBargainTime(bargainTime);
        this.setExpirationTime(expireTime);
    }

    public void setId(String id) { this.mId = id; }
    public String getId() { return this.mId; }

    public void setTitle(String title) { this.mTitle = title; }
    public String getTitle() { return this.mTitle; }

    public void setDescription(String desc) { this.mDescription = desc; }
    public String getDescription() { return this.mDescription; }

    public void setPrice(float price) { this.mPrice = price; }
    public float getPrice() { return this.mPrice; }

    public void setLocationLat(float lat) { this.mLocationLat = lat; }
    public float getLocationLat() { return this.mLocationLat; }

    public void setLocationLong(float locLong) { this.mLocationLong = locLong; }
    public float getLocationLong() { return this.mLocationLong; }

    public void setBargainTime(int time) { this.mBargainTime = time; }
    public int getBargainTime() { return this.mBargainTime; }

    public void setPostedTime(Timestamp posted) { this.mPostedTime = posted; }
    public Timestamp getPostedTime() { return this.mPostedTime; }

    public void setExpirationTime(Timestamp expires) { this.mExpirationTime = expires; }
    public Timestamp getExpirationTime() { return this.mExpirationTime; }
}
