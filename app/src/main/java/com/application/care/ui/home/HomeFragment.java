package com.application.care.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.application.care.R;
import com.application.care.util.HandlerColor;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private void manageCountDownTime(View root) {
        HandlerCountDownTime.getInstance().setView(root, getActivity());
    }

    private void manageProgressBar(View root) throws Exception {
        HandlerProgressBar.setView(root);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /* SET VIEW */
        HandlerColor.setView(root); //set view on HandlerColor to change color of background
        try {
            manageProgressBar(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        manageCountDownTime(root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }
}