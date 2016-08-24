package com.asuper.zhihudailynews.base;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Super on 2016/8/11.
 */
public class ExitAppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context != null) {
            if (context instanceof Activity) {
                ((Activity) context).finish();
            } else if (context instanceof FragmentActivity) {
                ((FragmentActivity) context).finish();
            } else if (context instanceof Service) {
                ((Service) context).stopSelf();
            }
        }

    }
}
