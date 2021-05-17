package com.application.care.model;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.application.care.R;
import com.application.care.util.HandlerAlert;
import com.application.care.util.HandlerColor;
import com.application.care.util.HandlerSharedPreferences;
import com.application.care.util.HandlerTime;

import org.jetbrains.annotations.NotNull;

import cn.iwgang.countdownview.CountdownView;

public class BreakState extends State {

    public static final String BREAK_STATE = "BreakState";

    public BreakState(CountdownView mCvCountdownView) {
        super(mCvCountdownView);
    }

    /*START BREAK */
    @SuppressLint("ResourceAsColor")
    @Override
    public void start() {
        Log.d(BREAK_STATE, "I AM IN START.");

        try {
            HandlerAlert.getInstance().showToast("Take a break");
            HandlerColor.getInstance().changeBackgroundColor(R.color.secondColor);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        IF IS THE TIME OF LONG BREAK THE PROGRAM WILL START A COUNTDOWN WITH LONG BREAK TIME.
        ContextState.SESSIONS++;
        long realWorksBeforeLongBreakTime = HandlerTime.getInstance().getRealTime(HandlerSharedPreferences.getInstance().getWorksBeforeLongBreakTime());
        if (ContextState.SESSIONS >= realWorksBeforeLongBreakTime) {
            Log.d(BREAK_STATE, "start: " + "I AM IN THE LONG BREAK TIME!");
            ContextState.SESSIONS = 0;
            mCvCountdownView.start(HandlerSharedPreferences.getInstance().getLongBreakTime());
        } else {
            mCvCountdownView.start(HandlerSharedPreferences.getInstance().getBreakTime());
        }
    }

    /*STOP BREAK */
    @Override
    public void stop() {
        Log.d(BREAK_STATE, "I AM IN STOP.");

        try {
            HandlerColor.getInstance().changeBackgroundColor(R.color.fourthColor);

            // change state
            State nextState = StateFlyweightFactory.getInstance().getState(WorkState.WORK_STATE);
            Log.d(BREAK_STATE, "Next State -> " + nextState.toString());
            ContextState.setState(nextState);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ContextState.getState().start();
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "BREAK STATE";
    }
}
