package com.example.hackillinois0219;

import backEndApi.framework.IPost;
import backEndApi.framework.IUser;

/**
 * Created by Joe on 2/20/2016.
 */
public class Post implements IPost {
    String mTitle;
    String mName;
    int mBid;

    public Post(String title, String name, int bid){
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
