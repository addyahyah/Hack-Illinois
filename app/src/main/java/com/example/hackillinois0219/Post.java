package com.example.hackillinois0219;

import java.io.Serializable;

import backEndApi.framework.IPost;
import backEndApi.framework.IUser;

/**
 * Created by Joe on 2/20/2016.
 */
public class Post implements IPost, Serializable {
    String mTitle;
    String mName;
    String status;
    int mBid;

    public Post(String title, String name, String status, int bid){
        mTitle = title;
        mName = name;
        mBid = bid;

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
}
