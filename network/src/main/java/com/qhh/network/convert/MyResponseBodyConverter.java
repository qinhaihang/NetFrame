package com.qhh.network.convert;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * @author admin
 * @version $Rev$
 * @time 2018/5/29 19:21
 * @des ${TODO}
 * @packgename com.hbjs.renrenshengyi.net.converter
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public MyResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        //BufferedSource bufferedSource = Okio.buffer(value.source());
        //String tempStr = bufferedSource.readUtf8();
        //bufferedSource.close();

//        Reader reader = value.charStream();
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        StringBuilder sb = new StringBuilder();
//        String line = "";
//        while ((line = bufferedReader.readLine())!=null){
//            sb.append(line);
//        }
//        String json = sb.toString();
//
//        int code = 0;
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            code = jsonObject.getInt("code");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        if(code != 1){
//            throw new ApiException(code);
//        }else{
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
//        }
    }
}
