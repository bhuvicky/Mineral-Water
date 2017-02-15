package com.bhuvanesh.mineralwater.util;

import android.util.Log;

import com.bhuvanesh.mineralwater.Config;

/**
 * Created by bhuvanesh on 31-01-2017.
 */

public final class LoggerUtil {

    private static final boolean mIsLogEnabled = Config.DEBUG;

    private LoggerUtil() {
    }

    public static void info(String tag, String msg) {
        if (mIsLogEnabled)
            Log.i(tag, msg);
    }

    public static void println(Object msg) {
        if (mIsLogEnabled)
            System.out.println(msg);
    }
}
