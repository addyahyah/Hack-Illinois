package com.example.hackillinois0219;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Joe on 2/20/2016.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private LinkedList<Post> mPosts;

    public PostAdapter(LinkedList<Post> posts){
        mPosts = posts;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mPosts.get(position).mTitle);
        holder.mStatus.setText(mPosts.get(position).mStatus);
        holder.mOwner.setText(mPosts.get(position).mName);
        holder.mComments.setText(mPosts.get(position).mComments + " Comments");
        holder.mBid.setText(mPosts.get(position).mBid + "$");

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mOwner;
        TextView mStatus;
        TextView mComments;
        TextView mBid;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView)itemView.findViewById(R.id.txt_post_title);
            mOwner = (TextView)itemView.findViewById(R.id.txt_post_owner);
            mStatus = (TextView)itemView.findViewById(R.id.txt_post_status);
            mComments = (TextView)itemView.findViewById(R.id.txt_post_comments);
            mBid = (TextView)itemView.findViewById(R.id.txt_post_bid);


        }


    }

}
