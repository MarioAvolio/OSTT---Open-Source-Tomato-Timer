package com.application.care.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.application.care.R;
import com.application.care.data.HandlerDB;
import com.application.care.model.State.ContextState;
import com.application.care.model.State.StateFlyweightFactory;
import com.application.care.util.HandlerColor;

import org.jetbrains.annotations.NotNull;

import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.DynamicConfig;

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

    public void setView(@NotNull View root, Activity activity) {
        this.root = root;
//        Log.d(TAG, "onCreateView TIME SEATED: " + HandlerTime.getInstance().getTime());
        mCvCountdownView = root.findViewById(R.id.countDown);

        /*
         *   INIT CONTEXT STATE
         * */
        ContextState contextState = new ContextState(mCvCountdownView);


//        init StateFlyweightFactory
        StateFlyweightFactory.setCountDownView(mCvCountdownView);
//        init HandlerDB
        HandlerDB.setContext(root.getContext());
    }
}
