package com.application.care.model;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import cn.iwgang.countdownview.CountdownView;

public abstract class State {
    private static final String TAG = "STATE"; // STATE PATTERN

    protected CountdownView mCvCountdownView;
    private long remainTime = -1;

    public State(@NotNull CountdownView mCvCountdownView) {
        this.mCvCountdownView = mCvCountdownView;
    }

    public abstract void start();

    public abstract void stop();

    public final void resume() {
        if (remainTime == -1)
            ContextState.getState().start();
        else {
            mCvCountdownView.start(remainTime);
            Log.d(TAG, "I AM IN RESUME.");
        }
    }

    public final void pause() {
        Log.d(TAG, "I AM IN PAUSE.");
        mCvCountdownView.pause();
        remainTime = mCvCountdownView.getRemainTime();
    }
}
