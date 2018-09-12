package com.qhh.network.custom;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;

/**
 * @author admin
 * @version $Rev$
 * @time 2018/9/12 16:29
 * @des 使用Gson泛型解析
 * @packgename com.qhh.network.custom
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class GsonConvertObserver<T> extends DisposableObserver<ResponseBody> {

    private Class<T> mClass;

    public GsonConvertObserver(Class<T> aClass) {
        mClass = aClass;
    }

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

            Result<T> tResult = fromJsonObject(tempStr, mClass);
            onSuccess(tResult.getData(), "");

        } catch (Exception e) {

        }

    }


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public Result<T> fromJsonObject(String reader, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
        return new Gson().fromJson(reader, type);
    }

    public Result<List<T>> fromJsonArray(String reader, Class<T> clazz) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(Result.class, new Type[]{listType});
        return new Gson().fromJson(reader, type);
    }

    public abstract void onSuccess(T data, String msg);

}
