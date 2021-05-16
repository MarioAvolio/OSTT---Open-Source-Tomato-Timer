package com.application.care.model;

import android.util.Log;

import com.application.care.util.HandlerSharedPreferences;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import org.jetbrains.annotations.NotNull;

public class BreakSeekBar implements OnSeekChangeListener {

    private static final String TAG = "BreakSeekBar";

    public BreakSeekBar() {
    }

    @Override
    public void onSeeking(@NotNull SeekParams seekParams) {
        Log.d(TAG, "onSeeking: " + seekParams.progress);
    }

    @Override
    public void onStartTrackingTouch(@NotNull IndicatorSeekBar seekBar) {
        Log.d(TAG, "onStartTrackingTouch: " + seekBar.getProgress());
    }

    @Override
    public void onStopTrackingTouch(@NotNull IndicatorSeekBar seekBar) {
        Log.d(TAG, "onStopTrackingTouch: " + seekBar.getProgress());

        long breakTime = seekBar.getProgress();
        HandlerSharedPreferences.getInstance().setBreakTime(breakTime);
    }
}
