package com.qhh.netframe;

import android.app.Application;

import com.qhh.network.manager.RetrofitManager;

/**
 * @author admin
 * @version $Rev$
 * @time 2018/9/12 10:40
 * @des ${TODO}
 * @packgename com.qhh.netframe
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance()
                .build()
                .setBaseUrl("http://www.wanandroid.com")
                .setDefaultOkHttpClient()
                .setRxJavaFactory();
    }
}
