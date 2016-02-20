package com.example.hackillinois0219;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

/**
 * Created by Joe on 2/20/2016.
 */
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LinkedList<Post> mPosts;

    public PostAdapter(LinkedList<Post> posts){

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);


        }


    }

}
