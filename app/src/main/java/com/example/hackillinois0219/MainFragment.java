package com.example.hackillinois0219;

import android.app.Fragment;
import android.os.Bundle;

import com.facebook.FacebookSdk;

/**
 * Created by yangr on 2/20/2016.
 */
public class MainFragment extends Fragment {
    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
    }
}
