package com.asuper.zhihudailynews.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.asuper.zhihudailynews.utils.FileUtil;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Super on 2016/8/4.
 */
public class ZhihuApp extends MultiDexApplication {
    private static Context sContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //65536 methods
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        //init file
        FileUtil.initFile();

        //fresco config
        FLog.setMinimumLoggingLevel(FLog.WARN);
        Fresco.initialize(this, ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(this));

        //catch exception
        Thread.setDefaultUncaughtExceptionHandler(new SimpleUncaughtExceptionHandler());
    }

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }


}
