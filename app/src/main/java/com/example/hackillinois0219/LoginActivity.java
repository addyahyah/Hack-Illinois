package com.example.hackillinois0219;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

/**
 * Created by zhangl3 on 2/21/2016.
 */
public class LoginActivity extends Activity {
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // On AccessToken changes fetch the new profile which fires the event on
                // the ProfileTracker if the profile is different
                Profile.fetchProfileForCurrentAccessToken();

            }
        };

        accessTokenTracker.startTracking();

        if(AccessToken.getCurrentAccessToken()!= null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(ACCESS_TOKEN, AccessToken.getCurrentAccessToken());
            startActivity(intent);
        }
    }
}
