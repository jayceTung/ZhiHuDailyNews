package com.asuper.zhihudailynews.network;


import com.asuper.zhihudailynews.Bean.DailyDetail;
import com.asuper.zhihudailynews.Bean.DailyListBean;
import com.asuper.zhihudailynews.Bean.LaunchImageBean;
import com.asuper.zhihudailynews.Bean.ThemeBean;

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

    /**
     * 获取日报详情数据
     *
     * @param id
     * @return
     */
    @GET("story/{id}")
    Observable<DailyDetail> getNewsDeatails(@Path("id") int id);

    /**
     * 获取专题日报
     *
     * @return
     */
    @GET("themes")
    Observable<ThemeBean> getThemeType();
}
