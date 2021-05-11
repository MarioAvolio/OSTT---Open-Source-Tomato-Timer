package com.application.care.util;

import android.content.Context;
import android.widget.Toast;

public class HandlerAlert {

    private static HandlerAlert instance;
    private static Context context;

    private HandlerAlert() {
    }

    public static HandlerAlert getInstance() throws Exception {

        if (context == null)
            throw new Exception("view == null");

        if (instance == null)
            instance = new HandlerAlert();
        return instance;
    }

    public static void setContext(Context context) {
        HandlerAlert.context = context;
    }

    public void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
