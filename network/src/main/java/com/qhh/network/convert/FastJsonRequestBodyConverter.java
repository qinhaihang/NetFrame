package com.qhh.network.convert;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @author admin
 * @version $Rev$
 * @time 2018/6/1 14:10
 * @des ${TODO}
 * @packgename com.hbjs.renrenshengyi.net.converter
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        //return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
        return null;
    }
}