package com.application.care.model;

import android.util.Log;

import com.application.care.data.HandlerDB;
import com.application.care.ui.home.HandlerProgressBar;
import com.application.care.util.HandlerSharedPreferences;

import cn.iwgang.countdownview.CountdownView;

public class WorkState extends State {

    private static final String TAG = "WORK STATE";

    public WorkState(CountdownView mCvCountdownView) {
        super(mCvCountdownView);
    }

    @Override
    public void start() {
        Log.d(TAG, "I AM IN START. -> " + HandlerSharedPreferences.getInstance().getWorkTime());
        mCvCountdownView.start(HandlerSharedPreferences.getInstance().getWorkTime());
    }

    @Override
    public void stop(State state) {
        Log.d(TAG, "I AM IN STOP.");

        WorkTime workTime = new WorkTime(mCvCountdownView.getDrawingTime());
        try {
            HandlerDB.getInstance().increaseWorkTime(workTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HandlerProgressBar.getInstance().increase(1); //TODO

        state = new BreakState(mCvCountdownView);
        state.start();
    }
}