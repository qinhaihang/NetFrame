package com.qhh.network.custom;

import com.qhh.network.exception.ApiException;

import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;

/**
 * @author admin
 * @version $Rev$
 * @time 2018/6/2 8:49
 * @des 用于解析玩安卓接口的观察者, 该包下都是根据项目自定义实现的
 * @packgename com.hbjs.renrenshengyi.net.base
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public abstract class NoConvertObserver extends DisposableObserver<ResponseBody> {

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


        try {

            JSONObject jsonObject = new JSONObject(tempStr.trim());

            Integer code = jsonObject.getInt("errorCode");
            String data = jsonObject.getString("data");
            String errorMsg = jsonObject.getString("errorMsg");

            if (code == 0) {
                //T t = JSON.parseObject(data, new TypeReference<T>() {});
                if ("[]".equals(data)) {
                    isDataEmpty();
                    isDataEmpty(errorMsg);
                } else if ("{}".equals(data)) {
                    isDataEmpty();
                    isDataEmpty(errorMsg);
                } else {
                    onSuccess(data, errorMsg);
                }
            } else {
                onFailure(code, errorMsg);
            }

        } catch (Exception e) {

        }

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {

        } else {
            onSysError(e);
        }
    }

    @Override
    public void onComplete() {

    }

    public void isDataEmpty() {

    }

    public void isDataEmpty(String msg) {

    }

    //token过期拦截
    public void onTokenExpire(int code, String msg) {

    }

    public abstract void onSuccess(String data, String msg);

    public abstract void onFailure(int code, String msg);

    public abstract void onSysError(Throwable e);

}
