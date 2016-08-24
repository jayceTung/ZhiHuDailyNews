package com.asuper.zhihudailynews.utils;

import android.os.Environment;

import com.asuper.zhihudailynews.base.ZhihuApp;

import java.io.File;

/**
 * Created by Super on 2016/8/24.
 */
public class FileUtil {
    private static final String CACHE = "cache";
    private static final String DOWNLOAD = "download";
    private static final String LOG = "log";
    private static final String USERDATA = "userdata";

    /** filepath */
    public static String APP_PATH;
    public static String APP_CACHE_PATH;
    public static String APP_DOWNLOAD_PATH;
    public static String APP_LOG_PATH;
    public static String APP_USERDATA_PATH;

    /** Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /** init fIle */
    public static void initFile() {
        if (isExternalStorageWritable()) {
            APP_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + ZhihuApp.getContext().getPackageName();
        } else {
            APP_PATH = ZhihuApp.getContext().getCacheDir().getAbsolutePath()
                    + File.separator + ZhihuApp.getContext().getPackageName();
        }

        APP_CACHE_PATH = APP_PATH + File.separator + CACHE;
        APP_DOWNLOAD_PATH = APP_PATH + File.separator + DOWNLOAD;
        APP_LOG_PATH = APP_PATH + File.separator + LOG;
        APP_USERDATA_PATH = APP_PATH + File.separator + USERDATA;

        new File(APP_PATH).mkdirs();
        new File(APP_DOWNLOAD_PATH).mkdirs();
        new File(APP_LOG_PATH).mkdirs();
        new File(APP_CACHE_PATH).mkdirs();
        new File(APP_USERDATA_PATH).mkdirs();
    }
}
