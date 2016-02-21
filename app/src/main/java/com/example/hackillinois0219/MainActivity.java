package com.example.hackillinois0219;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Joe on 2/20/2016.
 */
public class MainActivity extends Activity {
    private static final int REQUEST_CODE_INPUT = 1;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private Menu mMenu;
    private LoginButton facebookLoginButton;
    private Toolbar toolbar;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);

        setContentView(R.layout.activity_main);
        // Start Your LandingActivity
        Intent i = new Intent(this, LoginActivity.class);
        startActivityForResult(i, REQUEST_CODE_INPUT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==REQUEST_CODE_INPUT && resultCode == Activity.RESULT_OK){
            Bundle b = data.getExtras();
            AccessToken token = (AccessToken) b.get("Access Token");
            swichToFeedActivity(token);
        }
    }

    private void swichToFeedActivity(AccessToken token){
        Intent intent = new Intent(this, FeedActivity.class);
        intent.putExtra("Access Token", token);
        startActivity(intent);
        finish();
    }



}
