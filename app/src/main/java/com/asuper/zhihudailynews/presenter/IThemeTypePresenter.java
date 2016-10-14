package com.asuper.zhihudailynews.presenter;

import com.asuper.zhihudailynews.bean.ThemeBean;

/**
 * Created by Super on 2016/9/27.
 */
public interface IThemeTypePresenter {
    void loadData();

    void loadDataSuccess(ThemeBean themeBean);

    void loadDataFailure(Throwable e);
}
