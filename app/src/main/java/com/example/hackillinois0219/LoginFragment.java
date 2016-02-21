package com.example.hackillinois0219;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by yangr on 2/20/2016.
 */
public class LoginFragment extends Fragment {

    LoginButton facebookLoginButton;
    CallbackManager callbackManager;

    public LoginFragment(){}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

            @Override
                                public View onCreateView(
                                        LayoutInflater inflater,
                                        ViewGroup container,
                                        Bundle savedInstanceState) {
                                    View view = inflater.inflate(R.layout.login_fragment, container, false);

                                    facebookLoginButton = (LoginButton) view.findViewById(R.id.log_in_button);

                                    facebookLoginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));

                                    callbackManager = CallbackManager.Factory.create();

                                    // Callback registration
                                    facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                                        @Override
                                        public void onSuccess(LoginResult loginResult) {
                                            // App code
                                            GraphRequest request = GraphRequest.newMeRequest(
                                                    loginResult.getAccessToken(),
                                                    new GraphRequest.GraphJSONObjectCallback() {
                                                        @Override
                                                        public void onCompleted(JSONObject object, GraphResponse response) {
                                                            Log.v("LoginActivity", response.toString());
                                // Application code
                                try {
                                    String email = object.getString("email");
                                    String birthday = object.getString("birthday"); // 01/31/1980 format
                                    Log.v("birthday", birthday);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Work");

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {
                // App code
                Log.v("LoginActivity", "cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });
        return view;
    }
}
