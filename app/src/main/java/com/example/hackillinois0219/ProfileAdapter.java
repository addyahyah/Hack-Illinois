package com.example.hackillinois0219;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

import backEndApi.Implementation.Review;
import backEndApi.Implementation.User;

/**
 * Created by Joe on 2/21/2016.
 */
public class ProfileAdapter extends RecyclerView.Adapter{
    User mUser;
    Activity mActivity;
    boolean profileFeed = true;
    LinkedList<Post> mPosts;
    LinkedList<Review> mReviews;
    OnItemClickListener mMyFeedListener;
    OnItemClickListener mReviewsListener;

    public ProfileAdapter(User user, LinkedList<Post> posts, LinkedList<Review> reviews, Activity activity){
        mUser = user;
        mActivity = activity;
        mPosts = posts;
        mReviews = reviews;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if(viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile, parent, false);

            return new ViewHolder(view);
        }

        if(viewType == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);

            return new FeedHolder(view);
        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review, parent, false);

        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getClass().equals(ViewHolder.class)){

        }else if(holder.getClass().equals(FeedHolder.class)){
            ((FeedHolder)holder).mTitle.setText(mPosts.get(position - 1).mTitle);
            ((FeedHolder)holder).mOwner.setText(mPosts.get(position - 1).mName);
            ((FeedHolder)holder).mBid.setText(mPosts.get(position - 1).mBid + "$");
        }

    }

    @Override
    public int getItemCount() {
        if(profileFeed){
            return 1 + mPosts.size();
        }

        return 1 + mReviews.size();
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0){

            return 0;
        }

        if(profileFeed){
            return 1;
        }

        return 2;
    }

    public void setFeed(boolean b){
        profileFeed = b;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        TextView mName;
        ImageView mPicture;

        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView)itemView.findViewById(R.id.txt_profile_name);
            mPicture = (ImageView) itemView.findViewById(R.id.img_profile_pic);

            Button myEvent = (Button) itemView.findViewById(R.id.btn_profile_mine);
            Button reviews = (Button) itemView.findViewById(R.id.btn_profile_reviews);

            myEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mMyFeedListener != null){
                        mMyFeedListener.onItemClick(v);
                    }
                }
            });

            reviews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mReviewsListener != null){
                        mReviewsListener.onItemClick(v);
                    }
                }
            });

        }
    }

    protected class FeedHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;
        public TextView mOwner;
        public TextView mStatus;
        public TextView mComments;
        public TextView mBid;
        public int mPosition;

        public FeedHolder(View itemView) {
            super(itemView);

            mTitle = (TextView)itemView.findViewById(R.id.txt_post_title);
            mOwner = (TextView)itemView.findViewById(R.id.txt_post_owner);
            mStatus = (TextView)itemView.findViewById(R.id.txt_post_status);
            mComments = (TextView)itemView.findViewById(R.id.txt_post_comments);
            mBid = (TextView)itemView.findViewById(R.id.txt_post_bid);

            LinearLayout layout = (LinearLayout) itemView.findViewById(R.id.post_holder);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity.getApplicationContext(), PostActivity.class);
                    intent.putExtra("Post", (Parcelable) mPosts.get(mPosition));
                    mActivity.startActivity(intent);

                }
            });

        }
    }

    protected class ReviewHolder extends RecyclerView.ViewHolder{

        public ReviewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view);
    }

    public void setOnItemClickListener(final OnItemClickListener myFeedListener) {
        mMyFeedListener = myFeedListener;
    }

    public interface OnReviewClickListener {
        void onReviewClick(View view);
    }

    public void setOnReviewClickListener(final OnItemClickListener reviewsListener) {
        mReviewsListener = reviewsListener;
    }


}
