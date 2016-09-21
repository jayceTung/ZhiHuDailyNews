package com.asuper.zhihudailynews.presenter;

import com.asuper.zhihudailynews.Bean.DailyDetail;

/**
 * Created by Super on 2016/9/20.
 */
public interface IDetailNewsPresenter {
    void loadData(int newsId);

    void loadDataSuccess(DailyDetail dailyDetail);

    void loadDataFailure(Throwable e);
}
