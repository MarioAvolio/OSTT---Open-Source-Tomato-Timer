package com.application.care.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

public class HandlerSharedPreferences {


    private static final String TAG = "HandlerSharedPreferences";
    private static final String WORKS_BEFORE_LONG_BREAK_TIME_ID = "WORKS_BEFORE_LONG_BREAK_TIME_ID";
    private static final long DEFAULT_VALUE_WORKS_BEFORE_LONG_BREAK = 4;
    @SuppressLint("StaticFieldLeak")
    private static HandlerSharedPreferences instance;
    private final String WORK_ID = "WORK_ID";
    private final String BREAK_ID = "BREAK_ID";
    private final long DEFAULT_VALUE_WORK = 1;
    private final long DEFAULT_VALUE_BREAK = 1;
    private final long DEFAULT_VALUE_LONG_BREAK = 1;
    private final String LONG_BREAK_ID = "LONG_BREAK_ID";
    private Activity activity;

    private HandlerSharedPreferences() {
    }

    public static HandlerSharedPreferences getInstance() {

        if (instance == null)
            instance = new HandlerSharedPreferences();
        return instance;
    }

    public void setActivity(@NotNull Activity activity) {
        this.activity = activity;
    }

    private SharedPreferences.Editor getEditor() {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.edit();
    }

    public long getWorkTime() {

        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getWorkTime: " + HandlerTime.getInstance().getTime(getShareData.getLong(WORK_ID, DEFAULT_VALUE_WORK)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(WORK_ID, DEFAULT_VALUE_WORK));
    }

    public void setWorkTime(long time) {

        Log.d(TAG, "setWorkTime: " + time);
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(WORK_ID, time);
        editor.apply(); //saving to disk

    }

    public long getBreakTime() {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getBreakTime: " + HandlerTime.getInstance().getTime(getShareData.getLong(BREAK_ID, DEFAULT_VALUE_BREAK)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(BREAK_ID, DEFAULT_VALUE_BREAK));
    }

    public void setBreakTime(long time) {
        Log.d(TAG, "setBreakTime: " + time);
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(BREAK_ID, time);
        editor.apply(); //saving to disk
    }


    public long getLongBreakTime() {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getLongBreakTime: " + HandlerTime.getInstance().getTime(getShareData.getLong(LONG_BREAK_ID, DEFAULT_VALUE_LONG_BREAK)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(LONG_BREAK_ID, DEFAULT_VALUE_LONG_BREAK));
    }

    public void setLongBreakTime(long time) {
        Log.d(TAG, "setLongBreakTime: " + time);
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(LONG_BREAK_ID, time);
        editor.apply(); //saving to disk
    }

    public long getWorksBeforeLongBreakTime() {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getLongBreakTime: " + HandlerTime.getInstance().getTime(getShareData.getLong(WORKS_BEFORE_LONG_BREAK_TIME_ID, DEFAULT_VALUE_WORKS_BEFORE_LONG_BREAK)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(WORKS_BEFORE_LONG_BREAK_TIME_ID, DEFAULT_VALUE_WORKS_BEFORE_LONG_BREAK));
    }

    public void setWorksBeforeLongBreakTime(long time) {
        Log.d(TAG, "setLongBreakTime: " + time);
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(WORKS_BEFORE_LONG_BREAK_TIME_ID, time);
        editor.apply(); //saving to disk
    }
}
