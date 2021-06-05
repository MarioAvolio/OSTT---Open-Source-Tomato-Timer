package com.application.care.model.State;

import java.util.HashMap;
import java.util.Map;

import cn.iwgang.countdownview.CountdownView;

public class StateFlyweightFactory { // FlyweightFactory
    private static StateFlyweightFactory instance;
    private static CountdownView countdownView;
    private final Map<String, State> stringStateMap;

    private StateFlyweightFactory() {
        stringStateMap = new HashMap<>();
    }

    public static void setCountDownView(CountdownView countDownView) {
        StateFlyweightFactory.countdownView = countDownView;
    }

    public static StateFlyweightFactory getInstance() throws Exception {

        if (countdownView == null)
            throw new Exception("countdownView == null");

        if (instance == null)
            instance = new StateFlyweightFactory();

        return instance;
    }

    public State getState(String type) {
        State state = null;
        if (!stringStateMap.containsKey(type)) {
            if (type.equals(BreakState.BREAK_STATE))
                state = new BreakState(countdownView);
            else if (type.equals(WorkState.WORK_STATE))
                state = new WorkState(countdownView);

            stringStateMap.put(type, state);
        } else {
            state = stringStateMap.get(type);
        }
        return state;
    }
}
