package com.qhh.netframe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.qhh.network.manager.RetrofitManager;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetApiService apiService = RetrofitManager.getInstance().getApiService(NetApiService.class);
        Observable<ResponseBody> articals = apiService.getArticals(0);
        articals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        BufferedSource bufferedSource = Okio.buffer(responseBody.source());
                        String tempStr = "";
                        try {
                            tempStr = bufferedSource.readUtf8();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                bufferedSource.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d(TAG,"tempStr = "+tempStr);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
