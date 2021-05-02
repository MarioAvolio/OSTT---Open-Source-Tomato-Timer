package com.application.care.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.application.care.R;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import static com.application.care.R.color.thirdColor;

public class HandlerProgressBar {

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
    }

    public void increase(int update) {
        numberProgressBar.incrementProgressBy(update);
    }

    @SuppressLint("ResourceType")
    private void setColor() {
        numberProgressBar.setProgressTextColor(Color.parseColor(root.getResources().getString(R.color.firstColor)));
        numberProgressBar.setReachedBarColor(Color.parseColor(root.getResources().getString(R.color.firstColor)));
        numberProgressBar.setUnreachedBarColor(Color.parseColor(root.getResources().getString(thirdColor)));
    }

    public void setView(View root) {
        this.root = root;
        numberProgressBar = root.findViewById(R.id.number_progress_bar);
        numberProgressBar.setMax(8);
        numberProgressBar.setProgress(1);
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
