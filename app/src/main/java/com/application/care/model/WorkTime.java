package com.application.care.model;

import android.annotation.SuppressLint;

import com.application.care.util.Settings;

public class WorkTime {
    private String date;
    private float time;

    public WorkTime() {
    }

    @SuppressLint("SimpleDateFormat")
    public WorkTime(float time) {
        this.time = time;
        this.date = Settings.getCurrentDate();
    }

    public WorkTime(String date, float time) {
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTime( ) {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    @Override
    public String toString( ) {
        return "WorkTime{" +
                "date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}
