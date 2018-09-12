package com.qhh.network.manager;

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
