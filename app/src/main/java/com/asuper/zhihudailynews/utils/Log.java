package com.asuper.zhihudailynews.utils;

import com.asuper.zhihudailynews.BuildConfig;

/**
 * Created by Super on 2016/8/4.
 */
public class Log {
    private static final boolean debug = BuildConfig.DEBUG;

    public static void i(String tag, String str) {
        if (debug) {
            android.util.Log.i(tag, str);
        }
    }

    public static void e(String tag, String str) {
        if (debug) {
            android.util.Log.e(tag, str);
        }
    }

    public static void v(String tag, String str) {
        if (debug) {
            android.util.Log.v(tag, str);
        }
    }

    public static void d(String tag, String str) {
        if (debug) {
            android.util.Log.d(tag, str);
        }
    }

    public static void w(String tag, String str) {
        if (debug) {
            android.util.Log.w(tag, str);
        }
    }

    public static String getStackTraceString(Throwable throwable) {
        return android.util.Log.getStackTraceString(throwable);
    }
}