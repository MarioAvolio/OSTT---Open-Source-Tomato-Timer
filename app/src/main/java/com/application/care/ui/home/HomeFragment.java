package com.application.care.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.application.care.R;
import com.application.care.data.HandlerDB;
import com.application.care.model.HandlerTime;

import cn.iwgang.countdownview.CountdownView;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


//        manage countdown time

        Log.d(TAG, "onCreateView TIME SEATED: " + HandlerTime.getInstance().getTime());
        CountdownView mCvCountdownView = (CountdownView) root.findViewById(R.id.countDown);
        final long[] timeLeft = {HandlerTime.getInstance().getTime()}; //remaining time
        final boolean[] isStarted = {false}; // stop / start / resume


//        set click listener
        mCvCountdownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarted[0]) {
                    mCvCountdownView.pause();
                    timeLeft[0] = mCvCountdownView.getRemainTime();
                } else
                    mCvCountdownView.start(timeLeft[0]); // Millisecond


                isStarted[0] = !isStarted[0];
            }
        });


//        set stop listener

        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {

                HandlerDB.getInstance().insertTime(HandlerTime.getInstance().getTime());

            }
        });

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}