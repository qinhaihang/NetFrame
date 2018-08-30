package com.qhh.network.manager;

import com.qhh.network.common.NetUrl;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author qinhaihang
 * @version $Rev$
 * @time 2018/8/30 22:19
 * @des 配置网络框架相关信息
 * @packgename com.qhh.network.manager
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class RetrofitManager {

    //public final ApiManagerService apiService;

    public RetrofitManager(boolean isConvert) {

        //配置Okhttp网络请求参数

        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(NetUrl.baseUrl);
        //builder.client(mOkHttpClient);
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        if (isConvert) //TODO:调试使用自己的解析器
            builder.addConverterFactory(GsonConverterFactory.create());
        //builder.addConverterFactory(MyConverterFactory.create(gson));
        //builder.addConverterFactory(FastJsonConverterFactory.create());
        Retrofit retrofit = builder.build();



    }
}