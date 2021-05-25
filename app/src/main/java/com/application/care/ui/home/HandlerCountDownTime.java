package com.application.care.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.application.care.R;
import com.application.care.data.HandlerDB;
import com.application.care.model.ContextState;
import com.application.care.model.StateFlyweightFactory;

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

    public long getRemainingTime() throws Exception {
        if (root == null)
            throw new Exception();

        return mCvCountdownView.getRemainTime();
    }


    public void setTime(float time) {
        mCvCountdownView.start((long) time);
    }


    public void setView(@NotNull View root, Activity activity) {
        this.root = root;


//        Log.d(TAG, "onCreateView TIME SEATED: " + HandlerTime.getInstance().getTime());
        mCvCountdownView = root.findViewById(R.id.countDown);

//        init StateFlyweightFactory
        StateFlyweightFactory.setCountDownView(mCvCountdownView);

//        init HandlerDB
        HandlerDB.setContext(root.getContext());


        ContextState contextState = new ContextState(mCvCountdownView);

//        HandlerDB.getInstance(root.getContext()).addWorkTime(new WorkTime(100));
//        Log.d(TAG, "onCreateView List: " + HandlerDB.getInstance(root.getContext()).getAllWorkTimes());

    }
}
