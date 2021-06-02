package com.application.care.util;

import android.graphics.Color;
import android.view.View;

public class HandlerColor {
    private static HandlerColor instance;
    private static View view;

    private HandlerColor() {
    }

    public static HandlerColor getInstance() throws Exception {

        if (view == null)
            throw new Exception("view == null");

        if (instance == null)
            instance = new HandlerColor();
        return instance;
    }

    public static void setView(View view) {
        HandlerColor.view = view;
    }

    private String getColorStringFromInt(int color) {
        return view.getResources().getString(color);
    }

    public int getColorFromColorString(int color) {
        return Color.parseColor(getColorStringFromInt(color));
    }
}
