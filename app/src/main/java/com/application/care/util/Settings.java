package com.application.care.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Settings {


    private static final String TIME_ID = "time";
    private static final long DEFAULT_VALUE_TIME = 1;

    @NotNull
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate( ) {
        Calendar calendar = Calendar.getInstance( );
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(calendar.getTime( ));
    }

    private static SharedPreferences.Editor getEditor(@NotNull Context context, String ID) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ID, Context.MODE_PRIVATE);
        return sharedPreferences.edit( );
    }

    public static void setWorkTime(Context context, long time) {
        SharedPreferences.Editor editor = getEditor(context, TIME_ID);
        editor.putLong(TIME_ID, time);
        editor.apply( ); //saving to disk
    }

    public static long getWorkTime(@NotNull Activity activity) {
        SharedPreferences getShareData = activity.getPreferences(Context.MODE_PRIVATE);
        return getShareData.getLong(TIME_ID, DEFAULT_VALUE_TIME);
    }

}
