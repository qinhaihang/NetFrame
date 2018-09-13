package com.qhh.netframe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.qhh.netframe.bean.BannerBean;
import com.qhh.network.custom.Result;
import com.qhh.network.manager.RetrofitManager;
import com.qhh.network.rxconfig.RxHelper;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RetrofitManager.getInstance().getApiService(NetApiService.class).getArticals(0)
        //        .compose(RxHelper.<okhttp3.ResponseBody>schedulerIOMain())
        //        .subscribe(new GsonConvertObserver<ArticalsBean>(ArticalsBean.class) {
        //            @Override
        //            public void onSuccess(ArticalsBean data, String msg) {
        //                Log.d(TAG, data.getSize() + "");
        //            }
        //        });

        RetrofitManager.getInstance().getApiService(NetApiService.class).getBanners()
                .compose(RxHelper.<Result<List<BannerBean>>>schedulerIOMain())
                .subscribe(new DisposableObserver<Result<List<BannerBean>>>() {
                    @Override
                    public void onNext(Result<List<BannerBean>> listResult) {
                        List<BannerBean> data = listResult.getData();
                        Log.d(TAG,data.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
