package com.asuper.zhihudailynews.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.asuper.zhihudailynews.R;
import com.asuper.zhihudailynews.base.ZhihuApp;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Super on 2016/8/4.
 */
public class NetWorkUtil {

    private NetWorkUtil() {
    }

    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) ZhihuApp.getContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    public static boolean iswifikConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) ZhihuApp.getContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }


    public static boolean isMobileConnected() {

        ConnectivityManager mConnectivityManager = (ConnectivityManager) ZhihuApp.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mMobileNetworkInfo = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mMobileNetworkInfo != null) {
            return mMobileNetworkInfo.isAvailable();
        }
        return false;
    }

    public static int getConnectedType() {

        ConnectivityManager mConnectivityManager = (ConnectivityManager) ZhihuApp.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
            return mNetworkInfo.getType();
        }
        return -1;
    }

    public static void showErrorCode(Throwable e, Context context, View view) {
        String msg;
        if (e instanceof IOException && isNetworkConnected()) {
            msg = context.getString(R.string.network_failed);
        } else if (e instanceof HttpException) {
            msg = ((HttpException) e).message();
        } else {
            msg = e.getMessage();
        }

        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }
}
