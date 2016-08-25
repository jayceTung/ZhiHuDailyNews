package com.asuper.zhihudailynews.network;

import com.asuper.zhihudailynews.BuildConfig;

/**
 * Created by Super on 2016/8/5.
 */
public enum ApiHost {
    ZHIHU_LAST_URL("http://news-at.zhihu.com/api/4/", "t.kktv8.com");

    public String debug;
    public String release;

    ApiHost(String debug, String release) {
        this.debug = debug;
        this.release = release;
    }

    public String value() {
        if (BuildConfig.API_HOST) {
            return release;
        } else {
            return debug;
        }
    }
}
