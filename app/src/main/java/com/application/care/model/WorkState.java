package com.application.care.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.application.care.data.HandlerDB;
import com.application.care.ui.home.HandlerProgressBar;
import com.application.care.util.HandlerAlert;
import com.application.care.util.HandlerSharedPreferences;

import org.jetbrains.annotations.NotNull;

import cn.iwgang.countdownview.CountdownView;

public class WorkState extends State {

    public static final String WORK_STATE = "WORK STATE";

    public WorkState(CountdownView mCvCountdownView) {
        super(mCvCountdownView);
    }

    @Override
    public void start(State state) {

        try {
            HandlerAlert.getInstance().showToast("Start Work");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(WORK_STATE, "I AM IN START. -> " + HandlerSharedPreferences.getInstance().getWorkTime());
        mCvCountdownView.start(HandlerSharedPreferences.getInstance().getWorkTime());
    }

    @Override
    public void stop(State state) {
        Log.d(WORK_STATE, "I AM IN STOP.");

        WorkTime workTime = new WorkTime(mCvCountdownView.getDrawingTime());
        try {
            HandlerDB.getInstance().increaseWorkTime(workTime);

            // change state
            ContextState.setState(StateFlyweightFactory.getInstance().getState(BreakState.BREAK_STATE));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HandlerProgressBar.getInstance().increase(1);
        state.start(state);
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "WORK STATE";
    }
}