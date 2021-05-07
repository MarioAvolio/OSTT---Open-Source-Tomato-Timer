package com.application.care.util;

public class HandlerTime {
    private static HandlerTime instance;
    private final long oneMinute = 60000;

    private HandlerTime( ) {
    }

    public static HandlerTime getInstance() {
        if (instance == null)
            instance = new HandlerTime();

        return instance;
    }

    public long getTime(long time) {
        return time * oneMinute;
    }

    public long getRealTime(long countDownTime) {
        return countDownTime / oneMinute;
    }
}
