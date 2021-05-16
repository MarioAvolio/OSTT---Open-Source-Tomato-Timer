package com.application.care.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

public class HandlerSharedPreferences {


    private static final String TAG = "HandlerSharedPreferences";
    @SuppressLint("StaticFieldLeak")
    private static HandlerSharedPreferences instance;
    private final String WORK_ID = "time";
    private final String BREAK_ID = "break";
    private final String LONG_BREAK_ID = "long break";
    private final long DEFAULT_VALUE_WORK = 1;
    private final long DEFAULT_VALUE_BREAK = 1;
    private final long DEFAULT_VALUE_LONG_BREAK = 1;
    private Context context;
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
        this.context = activity.getApplicationContext();
    }

    public long getWorkTime() {

        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getWorkTime: " + HandlerTime.getInstance().getTime(getShareData.getLong(WORK_ID, DEFAULT_VALUE_WORK)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(WORK_ID, DEFAULT_VALUE_WORK));
    }

    public void setWorkTime(long time) {

        Log.d(TAG, "setWorkTime: " + time);
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
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
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
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
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(LONG_BREAK_ID, time);
        editor.apply(); //saving to disk
    }
}
