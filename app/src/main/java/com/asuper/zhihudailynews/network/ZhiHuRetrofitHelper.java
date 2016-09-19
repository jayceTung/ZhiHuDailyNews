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

    private static OkHttpClient sOkHttpClient;
    private static ZhiHuDailyAPI sZhiHuApi;
    private static ZhiHuRetrofitHelper instance;

    public static final int CACHE_TIME_LONG = 60 * 60 * 24 * 7;
    public static final int REFRESH_TIME_LONG = 60;

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
                    final Cache cache = new Cache(new File(new File(FileUtil.APP_CACHE_PATH), "HttpCache")
                            , 1024 * 1024 * 100);

                    Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();

                            final CacheControl.Builder builder = new CacheControl.Builder();
//                            builder.noCache();//不使用缓存，全部走网络
//                            builder.noStore();//不使用缓存，也不存储缓存
//                            builder.onlyIfCached();//只使用缓存
//                            builder.noTransform();//禁止转码
                            builder.maxAge(10, TimeUnit.SECONDS);//指示客户机可以接收生存期不大于指定时间的响应。
//                            builder.maxStale(10, TimeUnit.SECONDS);//指示客户机可以接收超出超时期间的响应消息
//                            builder.minFresh(10, TimeUnit.SECONDS);//指示客户机可以接收响应时间小于当前时间加上指定时间的响应
                            CacheControl cache = builder.build();//cacheControl

                            if (!NetWorkUtil.isNetworkConnected()) {
                                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                            } else {
                                request = request.newBuilder().cacheControl(cache).build();
                            }

                            Log.i(TAG, String.format("Sending request %s on %s%n%s",
                                    request.url(), chain.connection(), request.headers()));


                            Response originalResponse = chain.proceed(request);


                            Response.Builder responseBuilder;

                            if (NetWorkUtil.isNetworkConnected()) {
                                String cacheControl = request.cacheControl().toString();
                                responseBuilder = originalResponse.newBuilder().header("Cache-Control", "max-age=" + REFRESH_TIME_LONG)
                                        .removeHeader("Pragma");
                            } else {
                                responseBuilder = originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_TIME_LONG)
                                        .removeHeader("Pragma");
                            }


                            Log.i(TAG, String.format("Received response for %s in %n%s",
                                    responseBuilder.build().request().url(), responseBuilder.build().headers()));

                            return responseBuilder.build();

                        }
                    };

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


}
