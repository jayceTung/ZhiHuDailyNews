package com.asuper.zhihudailynews.base;


import com.asuper.zhihudailynews.utils.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Joker on 2015/12/21.
 */
public class SimpleUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "SimpleUncaughtExceptionHandler";

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //读取stacktrace信息
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        ex.printStackTrace(printWriter);
        String errorReport = result.toString();
        Log.i(TAG, "uncaughtException errorReport=" + errorReport);
    }

}
