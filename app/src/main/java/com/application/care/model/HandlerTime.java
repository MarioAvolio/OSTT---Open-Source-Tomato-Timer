package com.application.care.model;

public class HandlerTime {
    private static HandlerTime instance;
    private final long oneMinute = 60000;
    private long time;

    private HandlerTime() {
        time = 1;
        setTime(time);
    }

    public static HandlerTime getInstance() {
        if (instance == null)
            instance = new HandlerTime();

        return instance;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time * oneMinute;
    }
}
