package com.qhh.netframe;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.qhh.network.custom.Result;
import com.qhh.network.manager.RetrofitManager;

import java.io.IOException;

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
                .setGsonBuildConvertFactory(configGson(new Result()))
                .setRxJavaFactory();
    }

    public Gson configGson(Result result) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(result.getClass(), new TypeAdapter<Result>() {
                    @Override
                    public void write(JsonWriter out, Result value) throws IOException {
                        Object data = value.getData();
                    }

                    @Override
                    public Result read(JsonReader in) throws IOException {
                        Result<Object> objectResult = new Result<>();

                        in.beginObject();

                        while (in.hasNext()) {
                            String s = in.nextName();
                            switch (s) {
                                case "errorCode":
                                    objectResult.setErrorCode(in.nextInt());
                                    break;
                                case "errorMsg":
                                    objectResult.setErrorMsg(in.nextString());
                                    break;
                                case "data":
                                    //String s1 = in.nextString();
                                    //if (!TextUtils.isEmpty(s1) && !TextUtils.equals(s1, "[]") && !TextUtils.equals(s1, "{}")) {
                                    //    objectResult.setData(s1);
                                    //} else {
                                    //    objectResult.setData(null);
                                    //}
                                    objectResult.setData(null);
                                    break;
                                default:
                                    break;
                            }

                        }
                        in.endObject();
                        return objectResult;
                    }
                })
                .create();

        return gson;
    }
}
