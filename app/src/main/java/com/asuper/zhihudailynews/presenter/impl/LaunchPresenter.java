package com.asuper.zhihudailynews.presenter.impl;

import com.asuper.zhihudailynews.bean.LaunchImageBean;
import com.asuper.zhihudailynews.model.BaseModel;
import com.asuper.zhihudailynews.model.LaunchModel;
import com.asuper.zhihudailynews.presenter.ILaunchPresenter;
import com.asuper.zhihudailynews.presenter.Presenter;
import com.asuper.zhihudailynews.ui.activity.LaunchActivity;
import com.asuper.zhihudailynews.view.LaunchView;

/**
 * Created by Super on 2016/8/23.
 */
public class LaunchPresenter implements Presenter<LaunchView>, ILaunchPresenter {
    private BaseModel mLaunchModel;
    private LaunchView mLaunchView;

    public LaunchPresenter(LaunchView launchView) {
        attachView(launchView);
        mLaunchModel = new LaunchModel(this);
    }

    @Override
    public void attachView(LaunchView launchView) {
        mLaunchView = launchView;
    }

    @Override
    public void detachView() {
        this.mLaunchView = null;
    }

    @Override
    public void loadData() {
        mLaunchView.showProgress();
        mLaunchModel.loadData(LaunchActivity.RESOLUTION);
    }

    @Override
    public void loadDataSuccess(LaunchImageBean launchImageBean) {
        mLaunchView.showData(launchImageBean);
        mLaunchView.hideProgress();
    }

    @Override
    public void loadDataFailure() {
        mLaunchView.hideProgress();
    }
}
