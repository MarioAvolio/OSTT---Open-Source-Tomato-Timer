package com.application.care.model.State;

import android.util.Log;

import com.application.care.util.HandlerCountDownTime;

public abstract class State {
    private static final String TAG = "STATE"; // STATE PATTERN
    private long remainTime = -1;

    public State() {
    }

    public abstract void start();

    public abstract void stop();

    public final void resume() throws Exception {
        if (remainTime == -1)
            ContextState.getInstance().start();
        else {
            HandlerCountDownTime.getInstance().getmCvCountdownView().start(remainTime);
            Log.d(TAG, "I AM IN RESUME.");
        }
    }

    public final void pause() throws Exception {
        Log.d(TAG, "I AM IN PAUSE.");
        HandlerCountDownTime.getInstance().getmCvCountdownView().pause();
        remainTime = HandlerCountDownTime.getInstance().getmCvCountdownView().getRemainTime();
    }
}
