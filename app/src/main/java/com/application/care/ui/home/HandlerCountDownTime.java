package com.application.care.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.application.care.R;
import com.application.care.data.HandlerDB;
import com.application.care.model.WorkTime;
import com.application.care.util.HandlerTime;

import cn.iwgang.countdownview.CountdownView;

public class HandlerCountDownTime {

    private static final String TAG = "HandlerCountDownTime";
    @SuppressLint("StaticFieldLeak")
    private static HandlerCountDownTime instance;
    private View root;
    private CountdownView mCvCountdownView;


    private HandlerCountDownTime() {
    }

    public static HandlerCountDownTime getInstance() {

        if (instance == null)
            instance = new HandlerCountDownTime();
        return instance;
    }

    public long getRemainingTime() throws Exception {
        if (root == null)
            throw new Exception();

        return mCvCountdownView.getRemainTime();
    }


    public void setTime(long time) {
        mCvCountdownView.start(time);
    }


    public void setView(View root) {

//        if (this.root != null)
//            return;

        this.root = root;

//        manage countdown time

        Log.d(TAG, "onCreateView TIME SEATED: " + HandlerTime.getInstance().getTime());
        mCvCountdownView = root.findViewById(R.id.countDown);
        final long[] timeLeft = {HandlerTime.getInstance().getTime()}; //remaining time
        final boolean[] isStarted = {false}; // stop / start / resume

        WorkTime workTime = new WorkTime(HandlerTime.getInstance().getTime());
        HandlerDB.getInstance(root.getContext()).addWorkTime(workTime);

//        set click listener
        mCvCountdownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarted[0]) {
                    mCvCountdownView.pause();
                    timeLeft[0] = mCvCountdownView.getRemainTime();
                } else
                    setTime(timeLeft[0]);


                isStarted[0] = !isStarted[0];
            }
        });


//        set stop listener

        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                
            }
        });
    }
}
