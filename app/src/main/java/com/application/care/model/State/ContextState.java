package com.application.care.model.State;

import android.util.Log;

public class ContextState {
    private static final String TAG = "ContextState";
    private static ContextState instance;
    private static State state;
    private int currentSession = 0;

    private ContextState() {
        state = new WorkState();
    }

    public static ContextState getInstance() {

        if (instance == null)
            instance = new ContextState();
        return instance;
    }

    public void increaseSession() {
        currentSession++;
    }

    public int getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(int currentSession) {
        this.currentSession = currentSession;
    }

    public static void setState(State nextState) {
        state = nextState;
    }

    public void start() {
        state.start();
    }

    public void stop() {
        Log.d(TAG, "stop: " + state.toString());
        state.stop();
    }

    public void resume() throws Exception {
        state.resume();
    }

    public final void pause() throws Exception {
        state.pause();
    }
}
