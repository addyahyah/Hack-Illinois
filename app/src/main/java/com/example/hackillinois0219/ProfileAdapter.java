package com.example.hackillinois0219;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

import backEndApi.Implementation.User;

/**
 * Created by Joe on 2/21/2016.
 */
public class ProfileAdapter extends RecyclerView.Adapter{
    User mUser;
    Activity mActivity;
    boolean profileFeed;
    LinkedList<Post> mPosts;

    public ProfileAdapter(User user, LinkedList<Post> posts, Activity activity){
        mUser = user;
        mActivity = activity;
        mPosts = posts;

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

    }

    @Override
    public int getItemCount() {
        return 1;
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

    protected class ViewHolder extends RecyclerView.ViewHolder{
        TextView mName;
        ImageView mPicture;

        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView)itemView.findViewById(R.id.txt_profile_name);
            mPicture = (ImageView) itemView.findViewById(R.id.img_profile_pic);

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

}
