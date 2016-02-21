package com.example.hackillinois0219;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Joe on 2/20/2016.
 */
public class PostDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Post mPost;
    private Activity mActivity;
    private int comments = 3;

    public PostDetailAdapter(Post post, Activity activity){
        mPost = post;
        mActivity = activity;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if(viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_detail, parent, false);

            return new ViewHolder(view);
        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment, parent, false);

        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getClass().equals(ViewHolder.class)){
            ((ViewHolder)holder).mTitle.setText(mPost.mTitle);
            ((ViewHolder)holder).mOwner.setText(mPost.mName);
            ((ViewHolder)holder).mBid.setText(mPost.mBid + "$");
            ((ViewHolder)holder).mDescription.setText(mPost.mDescription);
            ((ViewHolder)holder).mLocation.setText(mPost.mTitle);

        }else{
            ((CommentHolder)holder).mName.setText("Joe Novosel");
            ((CommentHolder)holder).mComment.setText("Joe was here");

        }


    }

    @Override
    public int getItemCount() {
        return 1 + comments;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){

            return 0;
        }

        return 1;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTitle;
        public TextView mOwner;
        public TextView mBid;
        public TextView mDescription;
        public TextView mLocation;
        public ImageView mPicture;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.txt_post_det_title);
            mOwner = (TextView) itemView.findViewById(R.id.txt_post_det_owner);
            mBid = (TextView) itemView.findViewById(R.id.txt_post_det_bid);
            mDescription = (TextView) itemView.findViewById(R.id.txt_post_det_desc);
            mLocation = (TextView) itemView.findViewById(R.id.txt_post_det_loc);
            mPicture = (ImageView) itemView.findViewById(R.id.img_post_det_pic);
            RelativeLayout layout = (RelativeLayout) itemView.findViewById(R.id.layout_post_det_user);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

    }

    protected class CommentHolder extends RecyclerView.ViewHolder{
        TextView mName;
        TextView mComment;

        public CommentHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.comment_name);
            mComment = (TextView) itemView.findViewById(R.id.comment_text);

        }
    }

}
