package com.application.care.model.SeekBar;

import android.util.Log;

import com.application.care.util.HandlerSharedPreferences;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import org.jetbrains.annotations.NotNull;

public class BeforeALongBreakSeekBar implements OnSeekChangeListener {

    private static final String TAG = "BeforeALongBreakSeekBar";

    public BeforeALongBreakSeekBar() {
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

        long worksBeforeALongBreak = seekBar.getProgress();
        try {
            HandlerSharedPreferences.getInstance().setWorksBeforeLongBreakTime(worksBeforeALongBreak);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
