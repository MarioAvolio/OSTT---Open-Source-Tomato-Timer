package com.application.care.util;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.application.care.R;
import com.application.care.model.State.ContextState;

import org.jetbrains.annotations.NotNull;

import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.DynamicConfig;

public class HandlerCountDownTime {

    private static final String TAG = "HandlerCountDownTime";
    @SuppressLint("StaticFieldLeak")
    private static HandlerCountDownTime instance;
    private static CountdownView mCvCountdownView;
    private static boolean isStarted;

    private HandlerCountDownTime() {
        isStarted = false;
    }

    public static HandlerCountDownTime getInstance() throws Exception {

        if (mCvCountdownView == null)
            throw new Exception("CountDownView == null");

        if (instance == null)
            instance = new HandlerCountDownTime();
        return instance;
    }

    public long getRemainingTime() throws Exception {
        return mCvCountdownView.getRemainTime();
    }

    public void setTime(float time) {
        mCvCountdownView.start((long) time);
    }

    private void setColor(int colorTime, int colorSuffix) throws Exception {
        mCvCountdownView.dynamicShow
                (
                        new DynamicConfig.Builder()
                                .setTimeTextColor(colorTime)
                                .setSuffixTextColor(colorSuffix)
                                .build());
    }

    public void setWorkColor() throws Exception {
        setColor(HandlerColor.getInstance().getColorFromColorString(R.color.firstColor),
                HandlerColor.getInstance().getColorFromColorString(R.color.thirdColor));
    }

    public void setBreakColor() throws Exception {
        setColor(HandlerColor.getInstance().getColorFromColorString(R.color.thirdColor),
                HandlerColor.getInstance().getColorFromColorString(R.color.thirdColor));
    }

    public static void setCountDown(@NotNull View root) {

        /*
         *  INIT COUNTDOWN
         * */
        mCvCountdownView = root.findViewById(R.id.countDown);

//        set click listener
        mCvCountdownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: CLICK ON COUNTDOWN  -> " + isStarted);


                /*
                 *
                 *   IF COUNTDOWN IS JUST STARTED TAKE A PAUSE
                 *   ELSE START WORKING.
                 *
                 * */
                try {
                    if (isStarted)
                        ContextState.getInstance().pause();
                    else
                        ContextState.getInstance().resume();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                /*
                 *  CHANGE STATE ON CLICK
                 * */
                isStarted = !isStarted;
            }
        });

//        set stop listener
        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                Log.d(TAG, "onEnd: ");
                try {
                    ContextState.getInstance().stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void goOnPause() throws Exception {
        isStarted = false;
        ContextState.getInstance().pause();
    }

    public void startingOrResume() throws Exception {
        isStarted = true;
        ContextState.getInstance().resume();
    }

    public CountdownView getmCvCountdownView() {
        return mCvCountdownView;
    }
}
