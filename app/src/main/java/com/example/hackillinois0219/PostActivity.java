package com.example.hackillinois0219;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by Joe on 2/20/2016.
 */
public class PostActivity extends Activity {
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;

    @Override
    public void onCreate(Bundle onSavedInstance){
        super.onCreate(onSavedInstance);
        setContentView(R.layout.activity_post);

        mRecyclerView = (RecyclerView)findViewById(R.id.post_holder);
        mStaggeredLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        Post post = getIntent().getParcelableExtra("Post");
        PostDetailAdapter mAdapter = new PostDetailAdapter(post, this);

        mRecyclerView.setAdapter(mAdapter);

    }

}
