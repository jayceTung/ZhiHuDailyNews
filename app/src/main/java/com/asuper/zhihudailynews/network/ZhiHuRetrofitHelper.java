package com.asuper.zhihudailynews.network;


import com.asuper.zhihudailynews.Bean.DailyListBean;
import com.asuper.zhihudailynews.Bean.LaunchImageBean;
import com.asuper.zhihudailynews.utils.FileUtil;
import com.asuper.zhihudailynews.utils.Log;
import com.asuper.zhihudailynews.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    private static OkHttpClient mOkHttpClient;
    private static ZhiHuDailyAPI mZhiHuApi;
    private static ZhiHuRetrofitHelper instance;

    public static final int CACHE_TIME_LONG = 60 * 60 * 24 * 7;

    private ZhiHuRetrofitHelper() {
    }

    public static ZhiHuRetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (ZhiHuRetrofitHelper.class) {
                if (instance == null) {
                    initOkHttpClient();

                    Retrofit mRetrofit = new Retrofit.Builder()
                            .baseUrl(ApiHost.ZHIHU_LAST_URL.vaule())
                            .client(mOkHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();

                    mZhiHuApi = mRetrofit.create(ZhiHuDailyAPI.class);
                    instance = new ZhiHuRetrofitHelper();
                }
            }
        }
        return instance;
    }

    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        if (mOkHttpClient == null) {
            synchronized (ZhiHuRetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    Cache cache = new Cache(new File(new File(FileUtil.APP_CACHE_PATH), "HttpCache")
                            , 1024 * 1024 * 100);

                    Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();

                            Log.i(TAG, String.format("Sending request %s on %s%n%s",
                                    request.url(), chain.connection(), request.headers()));

                            if (!NetWorkUtil.isNetworkConnected()) {
                                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                            }
                            Response originalResponse = chain.proceed(request);

                            Log.i(TAG, String.format("Received response for %s in %n%s",
                                    originalResponse.request().url(), originalResponse.headers()));

                            if (NetWorkUtil.isNetworkConnected()) {
                                String cacheControl = request.cacheControl().toString();
                                return originalResponse.newBuilder().header("cache-Control", cacheControl)
                                        .removeHeader("Pragma").build();
                            } else {
                                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_TIME_LONG)
                                        .removeHeader("Pragma").build();
                            }
                        }
                    };

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }


    public Observable<DailyListBean> getLatestNews() {
        return mZhiHuApi.getLatestNews();
    }

    public Observable<LaunchImageBean> getLaunchImage(String res) {
        return mZhiHuApi.getLaunchImage(res);
    }


}
