package com.asuper.zhihudailynews.presenter;

/**
 * Created by Super on 2016/8/23.
 */
public interface Presenter<V> {
    void attachView(V v);

    void detachView();
}
