package com.asuper.zhihudailynews.model;

import com.asuper.zhihudailynews.bean.ThemeBean;
import com.asuper.zhihudailynews.network.ZhiHuRetrofitHelper;
import com.asuper.zhihudailynews.presenter.IThemeTypePresenter;
import com.asuper.zhihudailynews.utils.Log;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Super on 2016/9/27.
 */
public class ThemeTypeModel implements BaseModel {
    private IThemeTypePresenter presenter;

    public ThemeTypeModel(IThemeTypePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadData(Object... args) {
        ZhiHuRetrofitHelper.getInstance().getThemeType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThemeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.getStackTraceString(e);
                        presenter.loadDataFailure(e);
                    }

                    @Override
                    public void onNext(ThemeBean themeBean) {
                        if (themeBean != null) {
                            presenter.loadDataSuccess(themeBean);
                        }
                    }
                });
    }
}
