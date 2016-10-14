package com.asuper.zhihudailynews.presenter;

import com.asuper.zhihudailynews.bean.LaunchImageBean;

/**
 * Created by Super on 2016/8/23.
 */
public interface ILaunchPresenter {
    void loadData();

    void loadDataSuccess(LaunchImageBean launchImageBean);

    void loadDataFailure();
}
