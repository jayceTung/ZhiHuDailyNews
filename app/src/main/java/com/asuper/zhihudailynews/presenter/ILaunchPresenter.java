package com.asuper.zhihudailynews.presenter;

import com.asuper.zhihudailynews.Bean.LaunchImageBean;

/**
 * Created by Super on 2016/8/23.
 */
public interface ILaunchPresenter {
    void loadDataSuccess(LaunchImageBean launchImageBean);

    void loadDataFailure();
}
