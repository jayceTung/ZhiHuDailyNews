package com.asuper.zhihudailynews.network;

import com.asuper.zhihudailynews.utils.Log;
import com.asuper.zhihudailynews.utils.NetWorkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Super on 2016/9/20.
 */
public class NetworkInterceptor implements Interceptor {
    private static final String TAG = "Network";

    public static final int REFRESH_TIME_LONG = 60;
    public static final int CACHE_TIME_LONG = 60 * 60 * 24 * 7;

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
}
