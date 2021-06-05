package com.application.care.model.State;

import android.util.Log;

import androidx.annotation.NonNull;

import com.application.care.data.HandlerDB;
import com.application.care.model.TimeDate;
import com.application.care.ui.home.HandlerCountDownTime;
import com.application.care.ui.home.HandlerProgressBar;
import com.application.care.util.HandlerAlert;
import com.application.care.util.HandlerSharedPreferences;
import com.application.care.util.HandlerTime;

import org.jetbrains.annotations.NotNull;

import cn.iwgang.countdownview.CountdownView;

public class WorkState extends State {

    public static final String WORK_STATE = "WorkState";

    public WorkState(CountdownView mCvCountdownView) {
        super(mCvCountdownView);
    }

    /*START STATE*/
    @Override
    public void start() {

        try {

            /*
             *  SET WORK COLOR AT COUNTDOWN OBJECT
             * */
            HandlerCountDownTime.getInstance().setWorkColor();

            HandlerAlert.getInstance().showToast("Start Work");
            Log.d(WORK_STATE, "start: " + HandlerSharedPreferences.getInstance().getWorkTime());
            mCvCountdownView.start(HandlerSharedPreferences.getInstance().getWorkTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*STOP STATE*/
    @Override
    public void stop() {
        Log.d(WORK_STATE, "I AM IN STOP.");


        try {
            /*
             * SAVE CURRENT TIME DATE AND INSERT IT ON DB
             * */
            long time = HandlerTime.getInstance().getRealTime(HandlerSharedPreferences.getInstance().getWorkTime());
            TimeDate timeDate = new TimeDate(time);

            HandlerDB.getInstance().increaseTimeDate(timeDate);

            // change state
            State nextState = StateFlyweightFactory.getInstance().getState(BreakState.BREAK_STATE);
            Log.d(WORK_STATE, "Next State -> " + nextState.toString());
            ContextState.setState(nextState);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*INCREASE PROGRESS BAR*/
        HandlerProgressBar.getInstance().increase(1);
        ContextState.getState().start(); // PASS TO START STATE
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "WORK STATE";
    }
}