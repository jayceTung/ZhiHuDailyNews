package com.asuper.zhihudailynews.utils;

import android.content.Context;

import com.asuper.zhihudailynews.R;

import java.util.Calendar;

/**
 * Created by super on 16/8/25.
 */
public class WeekUtil {

    public static String getWeek(Context context, String date) {

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));
        Calendar calendar = Calendar.getInstance();//获得一个日历
        calendar.set(year, month - 1, day);//设置当前时间,月份是从0月开始计算
        int number = calendar.get(Calendar.DAY_OF_WEEK);//星期表示1-7，是从星期日开始，
//        String[] str = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String weekDay = getWeekDay(context, number);
        return weekDay;
    }

    private static String getWeekDay(Context context, int dayForWeek) {

        if (dayForWeek == 1) {
            return context.getString(R.string.sunday);
        } else if (dayForWeek == 2) {
            return context.getString(R.string.monday);
        } else if (dayForWeek == 3) {
            return context.getString(R.string.tuesday);
        } else if (dayForWeek == 4) {
            return context.getString(R.string.wednesday);
        } else if (dayForWeek == 5) {
            return context.getString(R.string.thursday);
        } else if (dayForWeek == 6) {
            return context.getString(R.string.friday);
        } else if (dayForWeek == 7) {
            return context.getString(R.string.saturday);
        } else {
            return "";
        }


    }
}
