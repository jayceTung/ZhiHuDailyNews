package com.asuper.zhihudailynews.view;

import com.asuper.zhihudailynews.Bean.DailyListBean;

/**
 * Created by Super on 2016/8/25.
 */
public interface LatestNewsView {
    void showData(DailyListBean dailyListBean);

    void loadFailure(Throwable e);
}
