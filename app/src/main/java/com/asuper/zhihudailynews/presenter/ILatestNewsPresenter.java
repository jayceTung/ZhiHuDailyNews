package com.asuper.zhihudailynews.presenter;

import com.asuper.zhihudailynews.bean.DailyListBean;

/**
 * Created by Super on 2016/8/25.
 */
public interface ILatestNewsPresenter {
    void loadData();

    void loadDataSuccess(DailyListBean dailyListBean);

    void loadDataFailure(Throwable e);
}
