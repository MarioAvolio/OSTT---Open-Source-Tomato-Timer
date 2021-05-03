package com.application.care.ui;


import java.util.HashMap;
import java.util.Map;

public class FragmentSaveStateManager {
    private static FragmentSaveStateManager instance;
    private final Map<String, Object> savedStates;

    private FragmentSaveStateManager() {
        savedStates = new HashMap<>();
    }

    public static FragmentSaveStateManager getInstance() {
        if (instance == null) {
            instance = new FragmentSaveStateManager();
        }
        return instance;
    }

    public void saveFragmentState(String fragmentKey, Object fragmentState) {
        savedStates.put(fragmentKey, fragmentState);
    }

    public Object getFragmentState(String fragmentName) {
        if (savedStates.containsKey(fragmentName)) {
            return savedStates.get(fragmentName);
        }
        return null;
    }
}