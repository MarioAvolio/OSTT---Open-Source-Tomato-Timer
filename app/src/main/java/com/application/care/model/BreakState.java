package com.application.care.model;

import android.util.Log;

import com.application.care.util.HandlerSharedPreferences;

import cn.iwgang.countdownview.CountdownView;

public class BreakState extends State {

    private static final String TAG = "BREAK STATE";

    public BreakState(CountdownView mCvCountdownView) {
        super(mCvCountdownView);
    }

    @Override
    public void start() {
        Log.d(TAG, "I AM IN START.");

        mCvCountdownView.start(HandlerSharedPreferences.getInstance().getBreakTime());
    }

    @Override
    public void stop(State state) {
        Log.d(TAG, "I AM IN STOP.");
        state = new WorkState(mCvCountdownView);
        state.start();
    }
}
