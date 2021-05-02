package com.application.care.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.application.care.R;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private static final String REMAINING_TIME = "REMAINING";
    private HomeViewModel homeViewModel;

    private void manageCountDownTime(View root) {
        HandlerCountDownTime.getInstance().setView(root);
    }

    private void manageProgressBar(View root) {
        HandlerProgressBar.getInstance().setView(root);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        manageCountDownTime(root);
        manageProgressBar(root);

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }


//    save state

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            outState.putLong(REMAINING_TIME, HandlerCountDownTime.getInstance().getRemainingTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        outState.putString(RANDOM_GOOD_DEED_KEY, randomGoodDeed);
    }

}