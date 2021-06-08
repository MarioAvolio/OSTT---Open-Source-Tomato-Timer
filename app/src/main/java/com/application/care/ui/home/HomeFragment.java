package com.application.care.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.care.R;
import com.application.care.data.HandlerDB;
import com.application.care.util.HandlerColor;
import com.application.care.util.HandlerCountDownTime;
import com.application.care.util.HandlerProgressBar;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private void manageCountDownTime(View root) {
        HandlerCountDownTime.setCountDown(root);
    }

    private void manageProgressBar(View root) throws Exception {
        HandlerProgressBar.setView(root);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Log.d(TAG, "onCreateView: ");

        /* SET VIEW */
        HandlerColor.setView(root); //set view on HandlerColor to change color of background
        try {
            manageProgressBar(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        manageCountDownTime(root);


//        init HandlerDB
        HandlerDB.setContext(root.getContext());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
//        try {
//            HandlerCountDownTime.getInstance().goOnPause();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
//        try {
//            HandlerCountDownTime.getInstance().startingOrResume();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}