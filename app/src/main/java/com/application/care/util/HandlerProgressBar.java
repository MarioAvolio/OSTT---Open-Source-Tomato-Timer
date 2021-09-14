package com.application.care.util;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.application.care.R;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import org.jetbrains.annotations.NotNull;

public class HandlerProgressBar {

    private static final String TAG = "HandlerProgressBar";
    @SuppressLint("StaticFieldLeak")
    private static HandlerProgressBar instance;
    private static View root;
    private static NumberProgressBar numberProgressBar;

    private HandlerProgressBar() {
    }

    public static HandlerProgressBar getInstance() throws Exception {

        if (root == null)
            throw new Exception("root == null");

        if (instance == null)
            instance = new HandlerProgressBar();
        return instance;
    }

    @SuppressLint("ResourceType")
    private static void setColor() throws Exception {
        numberProgressBar.setProgressTextColor(HandlerColor.getInstance().getColorFromColorString(R.color.secondColor));
        numberProgressBar.setReachedBarColor(HandlerColor.getInstance().getColorFromColorString(R.color.firstColor));
        numberProgressBar.setUnreachedBarColor(HandlerColor.getInstance().getColorFromColorString(R.color.thirdColor));
    }

    private static void init() throws Exception {
        numberProgressBar = root.findViewById(R.id.number_progress_bar);

        /*
         *  SET DAILY GOAL
         * */

        int tmp = (int) HandlerSharedPreferences.getInstance().getDailyGoal();
        int dailyGoal = (int) HandlerTime.getInstance().getRealTime(tmp);
        Log.d(TAG, "dailyGoal: " + dailyGoal);
        numberProgressBar.setMax(dailyGoal);
        numberProgressBar.setProgress(0);

        /*
         *  CUSTOMIZATION
         * */
        numberProgressBar.setReachedBarHeight(10);
        numberProgressBar.setProgressTextSize(35);

        try {
            setColor(); // set color bar
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*
         * LISTENER
         * */

        numberProgressBar.setOnProgressBarListener(new OnProgressBarListener() {
            @Override
            public void onProgressChange(int current, int max) {
                if (current == max) {
                    try {
                        HandlerAlert.getInstance().showToast("DAILY GOAL COMPLETE!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    public static void setView(@NotNull View root) throws Exception {
        HandlerProgressBar.root = root;
        init();
    }

    public void setMax(int max) {
        numberProgressBar.setMax(max);
        Log.d(TAG, "setMax of " + max);

    }

    public int getProgress() {
        return numberProgressBar.getProgress();
    }

    public void setPercent(int percent) {
        Log.d(TAG, "setPercent of " + percent);
        numberProgressBar.setProgress(percent);
    }

    public void increase(int update) {
        Log.d(TAG, "increase of " + update);
        numberProgressBar.incrementProgressBy(update);
    }

    public void increase() {
        Log.d(TAG, "increase of single element");
        numberProgressBar.incrementProgressBy(1);
    }
}
