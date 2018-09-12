package com.qhh.netframe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.qhh.netframe.bean.ArticalsBean;
import com.qhh.network.custom.GsonConvertObserver;
import com.qhh.network.custom.NetApiService;
import com.qhh.network.manager.RetrofitManager;
import com.qhh.network.rxconfig.RxHelper;

import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitManager.getInstance().getApiService(NetApiService.class).getArticals(0)
                .compose(RxHelper.<okhttp3.ResponseBody>schedulerIOMain())
                .subscribe(new GsonConvertObserver<ArticalsBean>(ArticalsBean.class) {
                    @Override
                    public void onSuccess(ArticalsBean data, String msg) {
                        Log.d(TAG, data.getSize() + "");
                    }
                });

        RetrofitManager.getInstance().getApiService(NetApiService.class).getBanners()
                .compose(RxHelper.<ResponseBody>schedulerIOMain())
                .subscribe();
    }
}
