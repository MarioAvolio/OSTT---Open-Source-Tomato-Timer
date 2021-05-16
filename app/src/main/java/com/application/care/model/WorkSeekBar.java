package com.application.care.model;

import android.content.Context;
import android.util.Log;

import com.application.care.R;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import org.jetbrains.annotations.NotNull;

public class WorkSeekBar extends IndicatorSeekBar implements OnSeekChangeListener {

    private static final String TAG = "WorkSeekBar";

    public WorkSeekBar(Context context) {
        super(context);
        IndicatorSeekBar indicatorSeekBar = findViewById(R.id.work_time);
        indicatorSeekBar.setOnSeekChangeListener(this);
    }

    @Override
    public void onSeeking(@NotNull SeekParams seekParams) {
        Log.d(TAG, "onSeeking: " + seekParams.toString());
    }

    @Override
    public void onStartTrackingTouch(@NotNull IndicatorSeekBar seekBar) {
        Log.d(TAG, "onStartTrackingTouch: " + seekBar.toString());
    }

    @Override
    public void onStopTrackingTouch(@NotNull IndicatorSeekBar seekBar) {
        Log.d(TAG, "onStopTrackingTouch: " + seekBar.toString());
    }
}
