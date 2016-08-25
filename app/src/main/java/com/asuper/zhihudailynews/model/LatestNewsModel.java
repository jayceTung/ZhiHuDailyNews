package com.asuper.zhihudailynews.model;

import com.asuper.zhihudailynews.Bean.DailyListBean;
import com.asuper.zhihudailynews.network.ZhiHuRetrofitHelper;
import com.asuper.zhihudailynews.presenter.ILatestNewsPresenter;
import com.asuper.zhihudailynews.utils.Log;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Super on 2016/8/25.
 */
public class LatestNewsModel {
    private ILatestNewsPresenter mPresenter;

    public LatestNewsModel(ILatestNewsPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void loadData() {
        ZhiHuRetrofitHelper.getInstance().getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.getStackTraceString(e);
                        mPresenter.loadDataFailure(e);
                    }

                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        if (dailyListBean != null) {
                            mPresenter.loadDataSuccess(dailyListBean);
                        }
                    }
                });
    }
}
