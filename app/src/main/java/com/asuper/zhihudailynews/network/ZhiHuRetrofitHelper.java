package com.asuper.zhihudailynews.network;


import com.asuper.zhihudailynews.bean.DailyDetail;
import com.asuper.zhihudailynews.bean.DailyListBean;
import com.asuper.zhihudailynews.bean.LaunchImageBean;
import com.asuper.zhihudailynews.bean.ThemeBean;
import com.asuper.zhihudailynews.utils.FileUtil;
import com.asuper.zhihudailynews.utils.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Super on 2016/8/4.
 */
public class ZhiHuRetrofitHelper {
    private static final String TAG = "ZhiHuRetrofitHelper";

    private static OkHttpClient sOkHttpClient;
    private static ZhiHuDailyAPI sZhiHuApi;
    private static ZhiHuRetrofitHelper instance;

    private ZhiHuRetrofitHelper() {
    }

    public static ZhiHuRetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (ZhiHuRetrofitHelper.class) {
                if (instance == null) {
                    initOkHttpClient();

                    Retrofit mRetrofit = new Retrofit.Builder()
                            .baseUrl(ApiHost.ZHIHU_LAST_URL.value())
                            .client(sOkHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();

                    sZhiHuApi = mRetrofit.create(ZhiHuDailyAPI.class);
                    instance = new ZhiHuRetrofitHelper();
                }
            }
        }
        return instance;
    }

    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        if (sOkHttpClient == null) {
            synchronized (ZhiHuRetrofitHelper.class) {
                if (sOkHttpClient == null) {
                    Log.i(TAG, "initOkHttpClient");

                    final Cache cache = new Cache(new File(new File(FileUtil.APP_CACHE_PATH), "HttpCache")
                            , 1024 * 1024 * 100);

                    NetworkInterceptor mRewriteCacheControlInterceptor = new NetworkInterceptor();

                    sOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }


    public Observable<DailyListBean> getLatestNews() {
        return sZhiHuApi.getLatestNews();
    }

    public Observable<LaunchImageBean> getLaunchImage(String res) {
        return sZhiHuApi.getLaunchImage(res);
    }

    public Observable<DailyDetail> getNewsDailyDetail(int id) {
        return sZhiHuApi.getNewsDeatails(id);
    }

    public Observable<ThemeBean> getThemeType() {
        return sZhiHuApi.getThemeType();
    }

}
