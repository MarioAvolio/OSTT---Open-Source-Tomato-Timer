package com.application.care.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.application.care.R;

import org.jetbrains.annotations.NotNull;

public class HandlerSharedPreferences {

    private static final String TAG = "HandlerSharedPreferences";

    @SuppressLint("StaticFieldLeak")
    private static HandlerSharedPreferences instance;


    private final String WORK_ID = "WORK_ID";
    private final String BREAK_ID = "BREAK_ID";
    private static final String WORKS_BEFORE_LONG_BREAK_TIME_ID = "WORKS_BEFORE_LONG_BREAK_TIME_ID";
    private final String LONG_BREAK_ID = "LONG_BREAK_ID";
    private static Activity activity;
    private final String DAILY_GOAL_ID = "DAILY_GOAL_ID";
    private final long DEFAULT_TIME_WORK;
    private final long DEFAULT_TIME_BREAK;
    private final long DEFAULT_TIME_WORKS_BEFORE_LONG_BREAK;
    private final long DEFAULT_TIME_LONG_BREAK;
    private final long DEFAULT_TIME_DAILY_GOAL;

    private HandlerSharedPreferences() throws Exception {

        /*
         * INIT HandlerStringToInt
         * */
        HandlerStringToInt.setContext(activity.getBaseContext());
        DEFAULT_TIME_WORK = HandlerStringToInt.getInstance().getIntToString(R.string.work_time_default);
        DEFAULT_TIME_BREAK = HandlerStringToInt.getInstance().getIntToString(R.string.break_time_default);
        DEFAULT_TIME_WORKS_BEFORE_LONG_BREAK = HandlerStringToInt.getInstance().getIntToString(R.string.works_before_a_long_break_default);
        DEFAULT_TIME_LONG_BREAK = HandlerStringToInt.getInstance().getIntToString(R.string.long_break_time_default);
        DEFAULT_TIME_DAILY_GOAL = HandlerStringToInt.getInstance().getIntToString(R.string.daily_goal_default);
    }

    public static HandlerSharedPreferences getInstance() throws Exception {

        if (activity == null)
            throw new Exception("activity == null");

        if (instance == null)
            instance = new HandlerSharedPreferences();
        return instance;
    }

    public static void setActivity(@NotNull Activity activity) {
        HandlerSharedPreferences.activity = activity;
    }

    private SharedPreferences.Editor getEditor() {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.edit();
    }

    public long getWorkTime() {

        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getWorkTime: " + HandlerTime.getInstance().getTime(getShareData.getLong(WORK_ID, DEFAULT_TIME_WORK)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(WORK_ID, DEFAULT_TIME_WORK));
    }

    public void setWorkTime(long time) {

        Log.d(TAG, "setWorkTime: " + time);
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(WORK_ID, time);
        editor.apply(); //saving to disk

    }

    public long getBreakTime() {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getBreakTime: " + HandlerTime.getInstance().getTime(getShareData.getLong(BREAK_ID, DEFAULT_TIME_BREAK)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(BREAK_ID, DEFAULT_TIME_BREAK));
    }

    public void setBreakTime(long time) {
        Log.d(TAG, "setBreakTime: " + time);
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(BREAK_ID, time);
        editor.apply(); //saving to disk
    }


    public long getLongBreakTime() {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getLongBreakTime: " + HandlerTime.getInstance().getTime(getShareData.getLong(LONG_BREAK_ID, DEFAULT_TIME_LONG_BREAK)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(LONG_BREAK_ID, DEFAULT_TIME_LONG_BREAK));
    }

    public void setLongBreakTime(long time) {
        Log.d(TAG, "setLongBreakTime: " + time);
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(LONG_BREAK_ID, time);
        editor.apply(); //saving to disk
    }

    public long getWorksBeforeLongBreakTime() {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getLongBreakTime: " + HandlerTime.getInstance().getTime(getShareData.getLong(WORKS_BEFORE_LONG_BREAK_TIME_ID, DEFAULT_TIME_WORKS_BEFORE_LONG_BREAK)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(WORKS_BEFORE_LONG_BREAK_TIME_ID, DEFAULT_TIME_WORKS_BEFORE_LONG_BREAK));
    }

    public void setWorksBeforeLongBreakTime(long time) {
        Log.d(TAG, "setLongBreakTime: " + time);
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(WORKS_BEFORE_LONG_BREAK_TIME_ID, time);
        editor.apply(); //saving to disk
    }


    public long getDailyGoal() {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        Log.d(TAG, "getDailyGoal: " + HandlerTime.getInstance().getTime(getShareData.getLong(DAILY_GOAL_ID, DEFAULT_TIME_DAILY_GOAL)));
        return HandlerTime.getInstance().getTime(getShareData.getLong(DAILY_GOAL_ID, DEFAULT_TIME_DAILY_GOAL));
    }

    public void setDailyGoal(long dailyGoal) {
        Log.d(TAG, "setLongBreakTime: " + dailyGoal);
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(WORKS_BEFORE_LONG_BREAK_TIME_ID, dailyGoal);
        editor.apply(); //saving to disk
    }
}
