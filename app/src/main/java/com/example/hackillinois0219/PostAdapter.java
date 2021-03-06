package com.example.hackillinois0219;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Joe on 2/20/2016.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private LinkedList<Post> mPosts;
    private Activity mActivity;

    public PostAdapter(LinkedList<Post> posts, Activity activity){
        mPosts = posts;
        mActivity = activity;

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
        holder.mPosition = position;

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mOwner;
        public TextView mStatus;
        public TextView mComments;
        public TextView mBid;
        public int mPosition;

        public ViewHolder(View itemView) {
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

}
