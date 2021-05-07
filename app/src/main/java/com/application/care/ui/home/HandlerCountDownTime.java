package com.application.care.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.application.care.R;
import com.application.care.data.HandlerDB;
import com.application.care.model.WorkTime;
import com.application.care.util.HandlerTime;
import com.application.care.util.Settings;

import org.jetbrains.annotations.NotNull;

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

    public long getRemainingTime( ) throws Exception {
        if ( root == null )
            throw new Exception( );

        return mCvCountdownView.getRemainTime( );
    }


    public void setTime(float time) {
        mCvCountdownView.start(( long ) time);
    }


    public void setView(@NotNull View root, Activity activity) {

//        if (this.root != null)
//            return;

        this.root = root;


//        manage countdown time

//        Log.d(TAG, "onCreateView TIME SEATED: " + HandlerTime.getInstance().getTime());
        mCvCountdownView = root.findViewById(R.id.countDown);

//        HandlerDB.getInstance(root.getContext()).addWorkTime(new WorkTime(100));
//        Log.d(TAG, "onCreateView List: " + HandlerDB.getInstance(root.getContext()).getAllWorkTimes());
        final long time = Settings.getWorkTime(activity);
        final long[] timeLeft = {HandlerTime.getInstance().getTime(time)}; //remaining time
        final boolean[] isStarted = {false}; // stop / start / resume

//
//        int max = 8;
//        HandlerProgressBar.getInstance().setMax(max);
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
                WorkTime workTime = new WorkTime(cv.getDrawingTime( ));
                HandlerDB.getInstance(root.getContext( )).increaseWorkTime(workTime);
                HandlerProgressBar.getInstance( ).increase(( int ) cv.getDrawingTime( ));
            }
        });
    }
}
