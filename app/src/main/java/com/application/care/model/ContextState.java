package com.application.care.model;

import android.view.View;

import cn.iwgang.countdownview.CountdownView;

public class ContextState {
    private State state;

    public ContextState(CountdownView mCvCountdownView) {
        this.state = new WorkState(mCvCountdownView);

        final boolean[] isStarted = {false}; // pause / resume
//        set click listener
        mCvCountdownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarted[0])
                    pause();
                else
                    resume();
                isStarted[0] = !isStarted[0];
            }
        });


//        set stop listener
        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                stop();
            }
        });
    }

    public void setState(State state) {
        this.state = state;
    }

    public void start() {
        state.start();
    }

    public void stop() {
        state.stop(state);
    }

    public void resume() {
        state.resume(state);
    }

    public final void pause() {
        state.pause();
    }
}
