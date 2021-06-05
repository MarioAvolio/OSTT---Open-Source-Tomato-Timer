package com.application.care.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.application.care.R;
import com.application.care.ui.FragmentSaveStateManager;
import com.application.care.util.HandlerColor;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private static final String REMAINING_TIME = "REMAINING";
    private static final String GOAL_PERCENT = "GOAL";
    private static final String FRAGMENT_NAME = "Fragment HomeFragment";
    private HomeViewModel homeViewModel;

    private void manageCountDownTime(View root) {
        HandlerCountDownTime.getInstance().setView(root, getActivity());
    }

    private void manageProgressBar(View root) {
        HandlerProgressBar.getInstance().setView(root);
    }

    private void restore(float remainingTime, int goalPercent) {
        HandlerCountDownTime.getInstance().setTime(remainingTime);
        HandlerProgressBar.getInstance().setPercent(goalPercent);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ViewModel home = (ViewModel) FragmentSaveStateManager.getInstance().getFragmentState(FRAGMENT_NAME);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /* SET VIEW */
        HandlerColor.setView(root); //set view on HandlerColor to change color of background
        manageProgressBar(root);
        manageCountDownTime(root);

        /*IF CHANGE FRAGMENT*/

        if (home != null) {
            Log.d(TAG, "home != NULL!");

            homeViewModel = (HomeViewModel) home;

            Object o = FragmentSaveStateManager.getInstance().getFragmentState(REMAINING_TIME);
            Long l = null;
            if (o instanceof Long) {
                l = (long) o;
            }
            float remainingTime = 0;
            if (l != null) {
                remainingTime = l.floatValue();
            }
            int goalPercent = (int) FragmentSaveStateManager.getInstance().getFragmentState(GOAL_PERCENT);

            // Restore value of members from saved state
            restore(remainingTime, goalPercent);

        } else {

            homeViewModel =
                    new ViewModelProvider(this).get(HomeViewModel.class);

            Log.d(TAG, "home == NULL!");

        }


        /*IF CLOSE APP*/


        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {

            Log.d(TAG, "savedInstanceState != NULL!");

            long remainingTime = savedInstanceState.getLong(REMAINING_TIME);
            int goalPercent = savedInstanceState.getInt(GOAL_PERCENT);

            // Restore value of members from saved state
            restore(remainingTime, goalPercent);

        } else {
            Log.d(TAG, "savedInstanceState == NULL!");

            // Probably initialize members with default values for a new instance

        }

        return root;
    }


//    save state

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView!");

        super.onDestroyView();
        try {
            FragmentSaveStateManager.getInstance().saveFragmentState(REMAINING_TIME, HandlerCountDownTime.getInstance().getRemainingTime());
            FragmentSaveStateManager.getInstance().saveFragmentState(GOAL_PERCENT, HandlerProgressBar.getInstance().getProgress());
            FragmentSaveStateManager.getInstance().saveFragmentState(FRAGMENT_NAME, homeViewModel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "sonSaveInstanceState!");

        try {
            outState.putLong(REMAINING_TIME, HandlerCountDownTime.getInstance().getRemainingTime());
            outState.putInt(GOAL_PERCENT, HandlerProgressBar.getInstance().getProgress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}