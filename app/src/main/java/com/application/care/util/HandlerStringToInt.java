package com.application.care.util;

import android.content.Context;
import android.util.Log;

public class HandlerStringToInt {
    private static final String TAG = "HandlerStringToInt";
    private static HandlerStringToInt instance;
    private static Context context;

    private HandlerStringToInt() {
        // CONSTRUCTOR
    }

    public static HandlerStringToInt getInstance() throws Exception {

        if (context == null)
            throw new Exception("context == null");

        if (instance == null)
            instance = new HandlerStringToInt();

        return instance;
    }

    public static void setContext(Context context) {
        HandlerStringToInt.context = context;
    }

    public int getIntToString(int idString) {
        String str = context.getResources().getString(idString);
        int value = Integer.parseInt(str);
        Log.d(TAG, "getIntToString: " + value);
        return value;
    }
}
