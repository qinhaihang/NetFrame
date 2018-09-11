package com.qhh.network.manager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author qinhaihang
 * @version $Rev$
 * @time 2018/9/11 22:14
 * @des ${TODO}
 * @packgename com.qhh.network.manager
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class OkHttpClientManager {

    public int DEFAULT_TIME_CONNECT = 60;//链接超时时间

    //静态内部类实现单例
    private static class Holder {
        private static OkHttpClientManager INSTANCE = new OkHttpClientManager();
    }

    public static OkHttpClientManager getInstance() {
        return Holder.INSTANCE;
    }

    public OkHttpClientManager() {

    }

    public OkHttpClient getNormalOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIME_CONNECT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        return okHttpClient;
    }

}
