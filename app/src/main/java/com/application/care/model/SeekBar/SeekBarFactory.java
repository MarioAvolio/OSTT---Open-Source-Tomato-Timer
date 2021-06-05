package com.application.care.model.SeekBar;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.application.care.R;
import com.application.care.util.HandlerSharedPreferences;
import com.application.care.util.HandlerTime;
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

    private SeekBarFactory() throws Exception {

        integerIndicatorSeekBarMap = new HashMap<>();
        initBarToRealValue(R.id.work_time);
        initBarToRealValue(R.id.break_time);
        initBarToRealValue(R.id.long_break_time);
        initBarToRealValue(R.id.works_before_a_long_break);
        initBarToRealValue(R.id.goal);
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

    /*
     * WITH THIS METHOD IT SET THE BAR WITH THE LATEST SAVED VALUE
     * */
    @SuppressLint("NonConstantResourceId")
    private void initBarToRealValue(int type) throws Exception {

        Log.d(TAG, "initBarToRealValue: " + type);
        IndicatorSeekBar indicatorSeekBar = getSeekBar(type);
        long fullValue;
        switch (type) {
            case R.id.work_time:
                fullValue = HandlerSharedPreferences.getInstance().getWorkTime();
                break;

            case R.id.break_time:
                fullValue = HandlerSharedPreferences.getInstance().getBreakTime();
                break;

            case R.id.long_break_time:
                fullValue = HandlerSharedPreferences.getInstance().getLongBreakTime();
                break;

            case R.id.works_before_a_long_break:
                fullValue = HandlerSharedPreferences.getInstance().getWorksBeforeLongBreakTime();
                break;

            case R.id.goal:
                fullValue = HandlerSharedPreferences.getInstance().getDailyGoal();
                break;

            default:
                throw new Exception("type is not identified");

        }
        indicatorSeekBar.setProgress(HandlerTime.getInstance().getRealTime(fullValue));
    }

    /*
     * WITH THIS METHOD IT SET THE BAR WITH THE RELATIVE LISTENER
     * */

    @SuppressLint("NonConstantResourceId")
    public IndicatorSeekBar getSeekBar(int type) throws Exception {

        Log.d(TAG, "getSeekBar: " + type);
        if (integerIndicatorSeekBarMap.containsKey(type)) {
            return integerIndicatorSeekBarMap.get(type);
        } else {
            IndicatorSeekBar indicatorSeekBar = null;
            try {
                indicatorSeekBar = view.findViewById(type);
            } catch (Exception e) {
                throw new Exception("type is not identified");
            }

            switch (type) {
                case R.id.work_time:
                    indicatorSeekBar.setOnSeekChangeListener(new WorkSeekBar());
                    break;

                case R.id.break_time:
                    indicatorSeekBar.setOnSeekChangeListener(new BreakSeekBar());
                    break;

                case R.id.long_break_time:
                    indicatorSeekBar.setOnSeekChangeListener(new LongBreakSeekBar());
                    break;

                case R.id.works_before_a_long_break:
                    indicatorSeekBar.setOnSeekChangeListener(new BeforeALongBreakSeekBar());
                    break;


                case R.id.goal:
                    indicatorSeekBar.setOnSeekChangeListener(new GoalSeekBar());
                    break;

                default:
                    throw new Exception("type is not identified");

            }

            integerIndicatorSeekBarMap.put(type, indicatorSeekBar);
            return indicatorSeekBar;
        }

    }
}
