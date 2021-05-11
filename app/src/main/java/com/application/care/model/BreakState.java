package com.application.care.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.application.care.R;
import com.application.care.util.HandlerColor;
import com.application.care.util.HandlerSharedPreferences;

import cn.iwgang.countdownview.CountdownView;

public class BreakState extends State {

    private static final String TAG = "BREAK STATE";

    public BreakState(CountdownView mCvCountdownView) {
        super(mCvCountdownView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void start() {
        Log.d(TAG, "I AM IN START.");

        try {
            HandlerColor.getInstance().changeBackgroundColor(R.color.secondColor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCvCountdownView.start(HandlerSharedPreferences.getInstance().getBreakTime());
    }

    @Override
    public void stop(State state) {
        Log.d(TAG, "I AM IN STOP.");

        try {
            HandlerColor.getInstance().changeBackgroundColor(R.color.fourthColor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        state = new WorkState(mCvCountdownView);
        state.start();
    }
}
