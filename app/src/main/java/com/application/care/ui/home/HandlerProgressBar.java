package com.application.care.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.application.care.R;
import com.application.care.util.HandlerAlert;
import com.application.care.util.HandlerColor;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import org.jetbrains.annotations.NotNull;

public class HandlerProgressBar {

    private static final String TAG = "HandlerProgressBar";
    @SuppressLint("StaticFieldLeak")
    private static HandlerProgressBar instance;
    private View root;
    private NumberProgressBar numberProgressBar;

    public HandlerProgressBar() {
    }

    public static HandlerProgressBar getInstance() {
        if (instance == null)
            instance = new HandlerProgressBar();
        return instance;
    }

    public void setMax(int max) {
        numberProgressBar.setMax(max);
        Log.d(TAG, "setMax of " + max);

    }

    public void increase(int update) {
        Log.d(TAG, "increse of " + update);
        numberProgressBar.incrementProgressBy(update);
    }

    @SuppressLint("ResourceType")
    private void setColor() throws Exception {
        numberProgressBar.setProgressTextColor(HandlerColor.getInstance().getColorFromColorString(R.color.thirdColor));
        numberProgressBar.setReachedBarColor(HandlerColor.getInstance().getColorFromColorString(R.color.firstColor));
        numberProgressBar.setUnreachedBarColor(HandlerColor.getInstance().getColorFromColorString(R.color.thirdColor));
    }

    public int getProgress() {
        return numberProgressBar.getProgress();
    }

    public void setPercent(int percent) {
        Log.d(TAG, "setPercent of " + percent);
        numberProgressBar.setProgress(percent);
    }

    public void setView(@NotNull View root) {
        this.root = root;
        numberProgressBar = root.findViewById(R.id.number_progress_bar);
        numberProgressBar.setMax(8);
        numberProgressBar.setProgress(0);
        numberProgressBar.setReachedBarHeight(10);
        numberProgressBar.setProgressTextSize(50);

        try {
            setColor(); // set color bar
        } catch (Exception e) {
            e.printStackTrace();
        }


        numberProgressBar.setOnProgressBarListener(new OnProgressBarListener() {
            @Override
            public void onProgressChange(int current, int max) {
                if (current == max) {
                    try {
                        HandlerAlert.getInstance().showToast("MISSION COMPLETE!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
