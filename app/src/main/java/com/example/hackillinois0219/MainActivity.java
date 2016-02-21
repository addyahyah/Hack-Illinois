package com.example.hackillinois0219;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Joe on 2/20/2016.
 */
public class MainActivity extends Activity{
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private Menu mMenu;
    private LoginButton facebookLoginButton;
    private Toolbar toolbar;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);


//        if(savedInstanceState==null){
            this.switchToFeedFragment();
//        }else {
//
//        }

//        facebookLoginButton = (LoginButton)findViewById(R.id.log_in_button);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setUpActionBar();
//
//        mRecyclerView = (RecyclerView)findViewById(R.id.layout_feed);
//        mStaggeredLayoutManager =
//                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
//
//        LinkedList<Post> dummyFeed = new LinkedList<>();
//        dummyFeed.add(new Post("Laundry", "Joe", "Pending", "Get my laundry and bring it to my apartment at Heritage Trail", 3));
//        dummyFeed.add(new Post("Get Chinese", "Bob", "Bidding", "Can someone pick up my chinese food and bring it to BSB?", 4));
//        dummyFeed.add(new Post("Give me a blowjob", "RunZ", "Pending", "I'm just really horny", 22));
//
//        PostAdapter adapter = new PostAdapter(dummyFeed, this);
//        mRecyclerView.setAdapter(adapter);
//        }

    }

    public void switchToLoginFragment(){
        findViewById(R.id.tabs).setVisibility(View.GONE);
        findViewById(R.id.toolbar).setVisibility(View.GONE);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container_main, new LoginFragment(), "login");
        ft.commit();
    }

    public void switchToFeedFragment(){
        //TODO: need to create feedFragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new ProfileFragment(), "login");
        ft.commit();
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

                return false;
            }
        });

        return true;
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

}
