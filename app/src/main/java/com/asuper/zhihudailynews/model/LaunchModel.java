package com.asuper.zhihudailynews.model;

import com.asuper.zhihudailynews.Bean.LaunchImageBean;
import com.asuper.zhihudailynews.network.ZhiHuRetrofitHelper;
import com.asuper.zhihudailynews.presenter.ILaunchPresenter;
import com.asuper.zhihudailynews.utils.Log;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Super on 2016/8/23.
 */
public class LaunchModel {
    ILaunchPresenter mILaunchPresenter;

    public LaunchModel(ILaunchPresenter mILaunchPresenter) {
        this.mILaunchPresenter = mILaunchPresenter;
    }

    public void loadData(String resSize) {
        ZhiHuRetrofitHelper.getInstance().getLaunchImage(resSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LaunchImageBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.getStackTraceString(e);
                        mILaunchPresenter.loadDataFailure();
                    }

                    @Override
                    public void onNext(LaunchImageBean launchImageBean) {
                        if (launchImageBean != null) {
                            mILaunchPresenter.loadDataSuccess(launchImageBean);
                        }
                    }
                });
    }
}
