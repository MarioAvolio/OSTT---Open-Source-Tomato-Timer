package com.application.care.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Settings {

    private static Calendar calendar;
    private static SimpleDateFormat dateFormat;

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(calendar.getTime());
    }

}
