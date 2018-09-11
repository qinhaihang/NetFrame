package com.qhh.network.manager;

import com.qhh.network.common.NetUrl;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
public class RetrofitManager<T> {

    public static class Holder {
        private static RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return Holder.INSTANCE;
    }

    public RetrofitManager() {

    }

    /**
     * 不解析数据，默认配置OkHttpClient
     *
     * @param apiService
     * @return
     */
    public T getApiService(Class<T> apiService) {
        //配置Okhttp网络请求参数

        return getApiService(OkHttpClientManager.getInstance().getNormalOkHttp(),
                RxJava2CallAdapterFactory.create(),
                null,
                apiService);

    }

    /**
     * 默认的常规OkHttpClient,可配置的解析工厂
     * @param factory
     * @param apiService
     * @return
     */
    public T getApiService(Converter.Factory factory, Class<T> apiService) {

        return getApiService(OkHttpClientManager.getInstance().getNormalOkHttp(),
                RxJava2CallAdapterFactory.create(),
                factory,
                apiService);
    }

    public T getApiService(@Nullable OkHttpClient okHttpClient, @Nullable CallAdapter.Factory rxJavafactory,
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
}
