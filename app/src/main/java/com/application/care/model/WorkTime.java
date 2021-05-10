package com.application.care.model;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WorkTime {
    private String date;
    private float time;

    @SuppressLint("SimpleDateFormat")
    public WorkTime(float time) {
        this.time = time;
        this.date = getCurrentDate();
    }

    public WorkTime() {
    }

    @NotNull
    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(calendar.getTime());
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
