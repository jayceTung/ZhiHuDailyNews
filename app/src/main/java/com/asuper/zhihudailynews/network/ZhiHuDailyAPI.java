package com.asuper.zhihudailynews.network;


import com.asuper.zhihudailynews.Bean.DailyListBean;
import com.asuper.zhihudailynews.Bean.LaunchImageBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Super on 2016/8/4.
 */
public interface ZhiHuDailyAPI {

    /**
     *获取最新的日报数据
     *
     * @return
     */
    @GET("stories/latest")
    Observable<DailyListBean> getLatestNews();

    /**
     * 根据分辨率获取启动界面图片
     *
     * @param res
     * @return
     */
    @GET("start-image/{res}")
    Observable<LaunchImageBean> getLaunchImage(@Path("res") String res);
}
