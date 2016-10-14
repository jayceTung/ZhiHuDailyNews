package com.asuper.zhihudailynews.view;

import com.asuper.zhihudailynews.bean.DailyDetail;

/**
 * Created by Super on 2016/9/21.
 */
public interface DetailNewsView {
    void showData(DailyDetail dailyDetail);

    void loadFailure(Throwable e);
}
