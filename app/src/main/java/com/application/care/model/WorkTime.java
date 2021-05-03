package com.application.care.model;

import android.annotation.SuppressLint;

public class WorkTime {

    @SuppressLint("SimpleDateFormat")
    private String date;
    private float time;

    public WorkTime() {
        time = 0;
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

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
