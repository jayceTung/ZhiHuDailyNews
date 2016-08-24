package com.asuper.zhihudailynews.view;

import com.asuper.zhihudailynews.Bean.LaunchImageBean;

/**
 * Created by Super on 2016/8/23.
 */
public interface LaunchView {
    void showData(LaunchImageBean launchImageBean);

    void showProgress();

    void hideProgress();
}
