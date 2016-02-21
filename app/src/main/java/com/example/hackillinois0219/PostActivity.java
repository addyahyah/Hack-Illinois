package com.example.hackillinois0219;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joe on 2/20/2016.
 */
public class PostActivity extends Activity {
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private boolean userAccepted;

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

        final Button accept = (Button)findViewById(R.id.btn_accept_post);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAccepted = !userAccepted;

                if(userAccepted){
                    accept.setBackgroundColor(Color.parseColor("#026A00"));
                    accept.setTextColor(Color.parseColor("#FFFFFF"));
                    accept.setText("Accepted");
                }else{
                    accept.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    accept.setTextColor(Color.parseColor("#026A00"));
                    accept.setText("Accept");
                }

            }
        });

    }

}
