package com.application.care.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.application.care.R;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import org.jetbrains.annotations.NotNull;

import static com.application.care.R.color.thirdColor;

public class HandlerProgressBar {

    private static final String TAG = "HandlerProgressBar";
    @SuppressLint("StaticFieldLeak")
    private static HandlerProgressBar instance;
    private View root;
    private NumberProgressBar numberProgressBar;

    HandlerProgressBar() {
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
    private void setColor() {
        numberProgressBar.setProgressTextColor(Color.parseColor(root.getResources().getString(R.color.firstColor)));
        numberProgressBar.setReachedBarColor(Color.parseColor(root.getResources().getString(R.color.firstColor)));
        numberProgressBar.setUnreachedBarColor(Color.parseColor(root.getResources().getString(thirdColor)));
    }

    public int getProgress() {
        return numberProgressBar.getProgress();
    }

    public void setPercent(int percent) {
        Log.d(TAG, "setPercent of " + percent);
        numberProgressBar.setProgress(percent);
    }

    public void setView(@NotNull View root) {
//
//        if (this.root != null)
//            return;

        this.root = root;
        numberProgressBar = root.findViewById(R.id.number_progress_bar);
        numberProgressBar.setMax(8);
        numberProgressBar.setProgress(0);
        numberProgressBar.setReachedBarHeight(10);
        numberProgressBar.setPrefix("Goal ");

        setColor(); // set color bar


        numberProgressBar.setOnProgressBarListener(new OnProgressBarListener() {
            @Override
            public void onProgressChange(int current, int max) {
                if (current == max) {
                    Toast.makeText(root.getContext(), "MISSION COMPLETE!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}