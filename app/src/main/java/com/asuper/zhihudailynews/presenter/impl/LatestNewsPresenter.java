package com.asuper.zhihudailynews.presenter.impl;

import com.asuper.zhihudailynews.Bean.DailyListBean;
import com.asuper.zhihudailynews.model.BaseModel;
import com.asuper.zhihudailynews.model.LatestNewsModel;
import com.asuper.zhihudailynews.presenter.ILatestNewsPresenter;
import com.asuper.zhihudailynews.presenter.Presenter;
import com.asuper.zhihudailynews.view.LatestNewsView;

/**
 * Created by Super on 2016/8/25.
 */
public class LatestNewsPresenter implements Presenter<LatestNewsView>, ILatestNewsPresenter {
    private LatestNewsView mLatestNewsView;
    private BaseModel mLatestNewsModel;

    public LatestNewsPresenter(LatestNewsView mLatestNewsView) {
        mLatestNewsModel = new LatestNewsModel(this);
        attachView(mLatestNewsView);
    }

    @Override
    public void loadData() {
        mLatestNewsModel.loadData();
    }

    @Override
    public void loadDataSuccess(DailyListBean dailyListBean) {
        mLatestNewsView.showData(dailyListBean);
    }

    @Override
    public void loadDataFailure(Throwable e) {
        mLatestNewsView.loadFailure(e);
    }

    @Override
    public void attachView(LatestNewsView latestNewsView) {
        this.mLatestNewsView = latestNewsView;
    }

    @Override
    public void detachView() {
        this.mLatestNewsView = null;
    }
}
