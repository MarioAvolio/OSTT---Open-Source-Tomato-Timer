package com.application.care.model;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.application.care.R;
import com.application.care.util.HandlerAlert;
import com.application.care.util.HandlerColor;
import com.application.care.util.HandlerSharedPreferences;

import org.jetbrains.annotations.NotNull;

import cn.iwgang.countdownview.CountdownView;

public class BreakState extends State {

    public static final String BREAK_STATE = "BREAK STATE";

    public BreakState(CountdownView mCvCountdownView) {
        super(mCvCountdownView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void start(State state) {
        Log.d(BREAK_STATE, "I AM IN START.");

        try {
            HandlerAlert.getInstance().showToast("Take a break");
            HandlerColor.getInstance().changeBackgroundColor(R.color.secondColor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCvCountdownView.start(HandlerSharedPreferences.getInstance().getBreakTime());
    }

    @Override
    public void stop(State state) {
        Log.d(BREAK_STATE, "I AM IN STOP.");

        try {
            HandlerColor.getInstance().changeBackgroundColor(R.color.fourthColor);
            // change state
            ContextState.setState(StateFlyweightFactory.getInstance().getState(WorkState.WORK_STATE));
        } catch (Exception e) {
            e.printStackTrace();
        }


        state.start(state);
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "BREAK STATE";
    }
}
