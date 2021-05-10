package com.application.care.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public class HandlerSharedPreferences {


    @SuppressLint("StaticFieldLeak")
    private static HandlerSharedPreferences instance;
    private final String WORK_ID = "time";
    private final String BREAK_ID = "break";
    private final long DEFAULT_VALUE_WORK = 1;
    private final long DEFAULT_VALUE_BREAK = 5;
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

    private SharedPreferences.Editor getEditor(@NotNull Context context, String ID) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ID, Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }

    public long getWorkTime() {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        return HandlerTime.getInstance().getTime(getShareData.getLong(WORK_ID, DEFAULT_VALUE_WORK));
    }

    public void setWorkTime(long time) {
        SharedPreferences.Editor editor = getEditor(context, WORK_ID);
        editor.putLong(WORK_ID, time);
        editor.apply(); //saving to disk
    }

    public long getBreakTime() {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        return HandlerTime.getInstance().getTime(getShareData.getLong(BREAK_ID, DEFAULT_VALUE_BREAK));
    }

    public void setBreakTime(long time) {
        SharedPreferences.Editor editor = getEditor(context, BREAK_ID);
        editor.putLong(BREAK_ID, time);
        editor.apply(); //saving to disk
    }
}
