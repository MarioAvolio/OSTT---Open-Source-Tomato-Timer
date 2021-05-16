package com.application.care.model;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import com.application.care.R;
import com.warkiz.widget.IndicatorSeekBar;

public class SeekBarFactory {
    @SuppressLint("StaticFieldLeak")
    private static SeekBarFactory instance;
    @SuppressLint("StaticFieldLeak")
    private static ViewGroup container;

    private SeekBarFactory() {
    }

    public static void setContext(ViewGroup container) {
        SeekBarFactory.container = container;
    }

    public static SeekBarFactory getInstance() throws Exception {

        if (container == null)
            throw new Exception("container == null");

        if (instance == null)
            instance = new SeekBarFactory();
        return instance;
    }

    public IndicatorSeekBar getSeekBar(int type) {
        switch (type) {
            case R.id.work_time:
                return
            break;

            case R.id.break_time:
                break;

            case R.id.long_break_time:
                break;

        }
    }
}
