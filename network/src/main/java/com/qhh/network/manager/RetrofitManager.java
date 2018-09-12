package com.qhh.network.manager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.qhh.network.common.NetUrl;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author qinhaihang
 * @version $Rev$
 * @time 2018/8/30 22:19
 * @des 配置网络框架相关信息, 使用单例模式
 * @packgename com.qhh.network.manager
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class RetrofitManager {

    private Builder mBuilder;

    public static class Holder {
        private static RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return Holder.INSTANCE;
    }

    public RetrofitManager() {
        //mBuilder = new Builder(new Retrofit.Builder());
    }

    public Builder build() {
        mBuilder = new Builder(new Retrofit.Builder());
        return mBuilder;
    }

    public <T> T getApiService(Class<T> apiService) {
        return mBuilder.mReBuilder.build().create(apiService);
    }

    public <T> T getApiService(@Nullable OkHttpClient okHttpClient, @Nullable CallAdapter.Factory rxJavafactory,
                               @Nullable Converter.Factory factory, @NonNull Class<T> apiService) {

        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(NetUrl.baseUrl);
        if (null != okHttpClient)
            builder.client(okHttpClient);
        if (null != rxJavafactory)
            builder.addCallAdapterFactory(rxJavafactory);
        if (null != factory)
            builder.addConverterFactory(factory);
        //builder.addConverterFactory(GsonConverterFactory.create());
        //builder.addConverterFactory(MyConverterFactory.create(gson));
        //builder.addConverterFactory(FastJsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        T api = retrofit.create(apiService);

        return api;
    }

    public class Builder {

        public Retrofit.Builder mReBuilder;

        public Builder(Retrofit.Builder builder) {
            mReBuilder = builder;
        }

        /**
         * 必须设置基地址
         *
         * @param baseUrl
         * @return
         */
        public Builder setBaseUrl(String baseUrl) {
            mReBuilder.baseUrl(baseUrl);
            return this;
        }

        /**
         * 设置默认OkHttpClient
         * @return
         */
        public Builder setDefaultOkHttpClient() {
            mReBuilder.client(OkHttpClientManager.getInstance().getNormalOkHttp());
            return this;
        }

        public Builder setRxJavaFactory() {
            mReBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            return this;
        }

        public Builder setGsonConvertFactory() {
            mReBuilder.addConverterFactory(GsonConverterFactory.create());
            return this;
        }

    }

}
