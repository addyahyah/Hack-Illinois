package com.example.hackillinois0219;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.facebook.appevents.AppEventsLogger;

import java.util.LinkedList;

/**
 * Created by Joe on 2/20/2016.
 */
public class MainActivity extends Activity{
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private Menu mMenu;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.login_fragment_container, new MainFragment(), "login");
        ft.commit();

        mRecyclerView = (RecyclerView)findViewById(R.id.layout_feed);
        mStaggeredLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        LinkedList<Post> dummyFeed = new LinkedList<>();
        dummyFeed.add(new Post("Laundry", "Joe", "Pending", 3));
        dummyFeed.add(new Post("Get Chinese", "Bob", "Bidding", 4));
        dummyFeed.add(new Post("Give me a blowjob", "RunZ", "Pending", 22));

        PostAdapter adapter = new PostAdapter(dummyFeed, this);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume(){
        super.onResume();
        AppEventsLogger.activateApp(this);

    }

    @Override
    protected void onPause(){
        super.onPause();
        AppEventsLogger.deactivateApp(this);

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
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(intent);

                return false;
            }
        });

        return true;
    }

}
