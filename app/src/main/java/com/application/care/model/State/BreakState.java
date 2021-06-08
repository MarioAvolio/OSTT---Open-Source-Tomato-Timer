package com.application.care.model.State;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.application.care.util.HandlerAlert;
import com.application.care.util.HandlerCountDownTime;
import com.application.care.util.HandlerSharedPreferences;
import com.application.care.util.HandlerTime;

import org.jetbrains.annotations.NotNull;

public class BreakState extends State {

    public static final String BREAK_STATE = "BreakState";

    public BreakState() {
    }

    /*START BREAK */
    @SuppressLint("ResourceAsColor")
    @Override
    public void start() {
        Log.d(BREAK_STATE, "I AM IN START.");

        try {

            /*
             *  SET BREAK COLOR AT COUNTDOWN OBJECT
             * */
            HandlerCountDownTime.getInstance().setBreakColor();


            //        IF IS THE TIME OF LONG BREAK THE PROGRAM WILL START A COUNTDOWN WITH LONG BREAK TIME.
            ContextState.getInstance().increaseSession();
            long realWorksBeforeLongBreakTime = HandlerTime.getInstance().getRealTime(HandlerSharedPreferences.getInstance().getWorksBeforeLongBreakTime());

            if (ContextState.getInstance().getCurrentSession() >= realWorksBeforeLongBreakTime) {
                Log.d(BREAK_STATE, "start: " + "I AM IN THE LONG BREAK TIME!");

                /*
                 * RESTART SESSION
                 * */
                ContextState.getInstance().setCurrentSession(0);
                HandlerAlert.getInstance().showToast("Take a Long break");
                HandlerCountDownTime.getInstance().getmCvCountdownView().start(HandlerSharedPreferences.getInstance().getLongBreakTime());
            } else {
                HandlerAlert.getInstance().showToast("Take a break");
                HandlerCountDownTime.getInstance().getmCvCountdownView().start(HandlerSharedPreferences.getInstance().getBreakTime());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*STOP BREAK */
    @Override
    public void stop() {
        Log.d(BREAK_STATE, "I AM IN STOP.");

        try {
            // change state
            State nextState = StateFlyweightFactory.getInstance().getState(WorkState.WORK_STATE);
            Log.d(BREAK_STATE, "Next State -> " + nextState.toString());
            ContextState.setState(nextState);
            ContextState.getInstance().start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "BREAK STATE";
    }
}
