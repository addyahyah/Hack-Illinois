package com.example.hackillinois0219;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import backEndApi.framework.IPost;
import backEndApi.framework.IUser;

/**
 * Created by Joe on 2/20/2016.
 */
public class Post implements IPost, Serializable, Parcelable {
    String mTitle;
    String mName;
    String mStatus;
    String mDescription;
    int mComments;
    int mBid;

    public Post(String title, String name, String status, String description, int bid){
        mTitle = title;
        mName = name;
        mBid = bid;
        mStatus = status;
        mDescription = description;
        mComments = 1;

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

    @Override
    public IUser getAuthor() {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        String[] details = {mTitle, mName, mDescription, mStatus, mComments + "", mBid + ""};

    }
}
