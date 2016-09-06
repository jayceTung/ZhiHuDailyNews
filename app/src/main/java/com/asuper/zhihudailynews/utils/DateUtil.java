package com.asuper.zhihudailynews.utils;

import android.content.Context;

import com.asuper.zhihudailynews.R;

import java.text.SimpleDateFormat;

public class DateUtil {

    private DateUtil() {

    }

    public static String formatDate(Context context, String date) {

        String dateFormat = null;
        try {
            dateFormat = date.substring(4, 6) + context.getString(R.string.month)
                    + date.substring(6, 8) + context.getString(R.string.date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat;
    }

    public static String getTime(long date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        Long time = new Long(date);
        String d = format.format(time);

        return d;
    }
}
