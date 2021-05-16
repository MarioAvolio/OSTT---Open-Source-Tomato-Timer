package com.application.care.model;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.application.care.R;
import com.warkiz.widget.IndicatorSeekBar;

import java.util.HashMap;
import java.util.Map;

public class SeekBarFactory {
    private static final String TAG = "SeekBarFactory";
    @SuppressLint("StaticFieldLeak")
    private static SeekBarFactory instance;
    @SuppressLint("StaticFieldLeak")
    private static View view;
    private final Map<Integer, IndicatorSeekBar> integerIndicatorSeekBarMap;

    private SeekBarFactory() {
        integerIndicatorSeekBarMap = new HashMap<>();
    }

    public static void setView(View view) {
        SeekBarFactory.view = view;
    }

    public static SeekBarFactory getInstance() throws Exception {

        if (view == null)
            throw new Exception("view == null");

        if (instance == null)
            instance = new SeekBarFactory();
        return instance;
    }

    @SuppressLint("NonConstantResourceId")
    public IndicatorSeekBar getSeekBar(int type) throws Exception {

        Log.d(TAG, "getSeekBar: " + type);
        if (integerIndicatorSeekBarMap.containsKey(type)) {
            return integerIndicatorSeekBarMap.get(type);
        } else {
            IndicatorSeekBar indicatorSeekBar = null;
            switch (type) {
                case R.id.work_time:
                    indicatorSeekBar = view.findViewById(R.id.work_time);
                    indicatorSeekBar.setOnSeekChangeListener(new WorkSeekBar());
                    break;

                case R.id.break_time:
                    indicatorSeekBar = view.findViewById(R.id.break_time);
                    indicatorSeekBar.setOnSeekChangeListener(new BreakSeekBar());
                    break;

                case R.id.long_break_time:
                    indicatorSeekBar = view.findViewById(R.id.long_break_time);
                    indicatorSeekBar.setOnSeekChangeListener(new LongBreakSeekBar());
                    break;

                default:
                    throw new Exception("type is not identified");

            }

            integerIndicatorSeekBarMap.put(type, indicatorSeekBar);
            return indicatorSeekBar;
        }

    }
}
