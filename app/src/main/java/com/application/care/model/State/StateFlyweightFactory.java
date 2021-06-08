package com.application.care.model.State;

import java.util.HashMap;
import java.util.Map;

public class StateFlyweightFactory { // FlyweightFactory
    private static StateFlyweightFactory instance;
    private final Map<String, State> stringStateMap;

    private StateFlyweightFactory() {
        stringStateMap = new HashMap<>();
    }

    public static StateFlyweightFactory getInstance() {
        if (instance == null)
            instance = new StateFlyweightFactory();

        return instance;
    }

    public State getState(String type) {
        State state = null;
        if (!stringStateMap.containsKey(type)) {
            if (type.equals(BreakState.BREAK_STATE))
                state = new BreakState();
            else if (type.equals(WorkState.WORK_STATE))
                state = new WorkState();

            stringStateMap.put(type, state);
        } else {
            state = stringStateMap.get(type);
        }
        return state;
    }
}
