package com.application.care.model;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeDate {
    private String date;
    private float time;

    private final Calendar calendar = Calendar.getInstance();
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @SuppressLint("SimpleDateFormat")
    public TimeDate(float time) {
        this.time = time;
        this.date = getCurrentDate();
    }

    public TimeDate() {
    }

    public TimeDate(String date, float time) {
        this.date = date;
        this.time = time;
    }

    @NotNull
    private String getCurrentDate() {
        return dateFormat.format(getDateFormat());
    }

    public String getDate() {
        return date;
    }

    public Date getDateFormat() {
        return calendar.getTime();
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TimeDate{" +
                "date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}
