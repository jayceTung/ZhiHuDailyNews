package com.asuper.zhihudailynews.presenter.impl;

import com.asuper.zhihudailynews.bean.DailyDetail;
import com.asuper.zhihudailynews.model.BaseModel;
import com.asuper.zhihudailynews.model.DetailNewsModel;
import com.asuper.zhihudailynews.presenter.IDetailNewsPresenter;
import com.asuper.zhihudailynews.presenter.Presenter;
import com.asuper.zhihudailynews.view.DetailNewsView;

/**
 * Created by Super on 2016/9/21.
 */
public class DetailNewsPresenter implements Presenter<DetailNewsView>, IDetailNewsPresenter {
    private DetailNewsView mDetailNewsView;
    private BaseModel mDetailNewsModel;

    public DetailNewsPresenter(DetailNewsView detailNewsView) {
        mDetailNewsModel = new DetailNewsModel(this);
        attachView(detailNewsView);
    }


    @Override
    public void loadData(int newsId) {
        mDetailNewsModel.loadData(newsId);
    }

    @Override
    public void loadDataSuccess(DailyDetail dailyDetail) {
        mDetailNewsView.showData(dailyDetail);
    }

    @Override
    public void loadDataFailure(Throwable e) {
        mDetailNewsView.loadFailure(e);
    }

    @Override
    public void attachView(DetailNewsView detailNewsView) {
        this.mDetailNewsView = detailNewsView;
    }

    @Override
    public void detachView() {

    }
}
