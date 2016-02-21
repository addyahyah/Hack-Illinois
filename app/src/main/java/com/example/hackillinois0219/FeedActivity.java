package com.example.hackillinois0219;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.facebook.login.widget.LoginButton;

import java.util.LinkedList;

import backEndApi.Implementation.Review;
import backEndApi.Implementation.User;

/**
 * Created by zhangl3 on 2/21/2016.
 */
public class FeedActivity extends Activity {
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private Menu mMenu;
    private LoginButton facebookLoginButton;
    private Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feed);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();
        Log.d("Feed", "I'm fed");
        mRecyclerView = (RecyclerView) findViewById(R.id.layout_feed);
        mStaggeredLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        LinkedList<Post> dummyFeed = new LinkedList<>();
        dummyFeed.add(new Post("Laundry", "Joe", "Pending", "Get my laundry and bring it to my apartment at Heritage Trail", 3));
        dummyFeed.add(new Post("Get Chinese", "Bob", "Bidding", "Can someone pick up my chinese food and bring it to BSB?", 4));
        dummyFeed.add(new Post("Give me a blowjob", "RunZ", "Pending", "I'm just really horny", 22));

        PostAdapter adapter = new PostAdapter(dummyFeed, this);
        mRecyclerView.setAdapter(adapter);


        Button feed = (Button)findViewById(R.id.btn_feed);
        Button profile = (Button)findViewById(R.id.btn_profile);
        Button notifications = (Button)findViewById(R.id.btn_notes);

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStaggeredLayoutManager =
                        new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

                LinkedList<Post> dummyFeed = new LinkedList<>();
                dummyFeed.add(new Post("Laundry", "Joe", "Pending", "Get my laundry and bring it to my apartment at Heritage Trail", 3));
                dummyFeed.add(new Post("Get Chinese", "Bob", "Bidding", "Can someone pick up my chinese food and bring it to BSB?", 4));
                dummyFeed.add(new Post("Give me a blowjob", "RunZ", "Pending", "I'm just really horny", 22));

                PostAdapter adapter = new PostAdapter(dummyFeed,FeedActivity.this);
                mRecyclerView.setAdapter(adapter);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStaggeredLayoutManager =
                        new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

                LinkedList<Post> dummyFeed = new LinkedList<>();
                dummyFeed.add(new Post("Laundry", "Joe", "Pending", "Get my laundry and bring it to my apartment at Heritage Trail", 3));
                dummyFeed.add(new Post("Get Chinese", "Bob", "Bidding", "Can someone pick up my chinese food and bring it to BSB?", 4));
                dummyFeed.add(new Post("Give me a blowjob", "RunZ", "Pending", "I'm just really horny", 22));

                final ProfileAdapter pAdapter = new ProfileAdapter(null, dummyFeed, new LinkedList<Review>(), FeedActivity.this);
                ProfileAdapter.OnItemClickListener myFeed = new ProfileAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view) {
                        pAdapter.setFeed(true);

                        mStaggeredLayoutManager =
                                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
                        mRecyclerView.setAdapter(pAdapter);
                    }
                };

                ProfileAdapter.OnReviewClickListener reviews = new ProfileAdapter.OnReviewClickListener() {

                    @Override
                    public void onReviewClick(View view) {
                        pAdapter.setFeed(false);

                        mStaggeredLayoutManager =
                                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
                        mRecyclerView.setAdapter(pAdapter);
                    }
                };

                pAdapter.setOnItemClickListener(myFeed);
                mRecyclerView.setAdapter(pAdapter);

            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    private void setUpActionBar() {
        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(20);
            getActionBar().setTitle("Feed");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        mMenu = menu;

        MenuItem sv = menu.findItem(R.id.action_search);

        sv.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });

        return true;
    }
}
