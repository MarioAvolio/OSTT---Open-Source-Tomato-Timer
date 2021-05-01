package com.application.care.data;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HandlerDB {

    public static final String KEY_DAY = "day";
    public static final String KEY_TIME_SPEND = "time_spend";
    private static final String TAG = "HandlerDB";
    private static HandlerDB instance;
    private final Map<String, Object> data;
    private final String date;

    private HandlerDB() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(Calendar.getInstance().getTime());
        // Write a message to the database
        data = new HashMap<>();
    }

    public static HandlerDB getInstance() {

        if (instance == null)
            instance = new HandlerDB();
        return instance;
    }

    public void insertTime(float time) {

    }
}
