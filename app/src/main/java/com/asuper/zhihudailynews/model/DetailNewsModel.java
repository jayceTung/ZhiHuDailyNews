package com.asuper.zhihudailynews.model;

import com.asuper.zhihudailynews.Bean.DailyDetail;
import com.asuper.zhihudailynews.network.ZhiHuRetrofitHelper;
import com.asuper.zhihudailynews.presenter.IDetailNewsPresenter;
import com.asuper.zhihudailynews.utils.Log;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Super on 2016/9/20.
 */
public class DetailNewsModel implements BaseModel{
    private IDetailNewsPresenter presenter;

    public DetailNewsModel(IDetailNewsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadData(Object... args) {
        ZhiHuRetrofitHelper.getInstance().getNewsDailyDetail((Integer) args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DailyDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.getStackTraceString(e);
                        presenter.loadDataFailure(e);
                    }

                    @Override
                    public void onNext(DailyDetail dailyDetail) {
                        if (dailyDetail != null) {
                            presenter.loadDataSuccess(dailyDetail);
                        }
                    }
                });
    }
}
