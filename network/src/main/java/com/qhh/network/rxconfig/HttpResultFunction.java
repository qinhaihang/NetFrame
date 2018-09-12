package com.qhh.network.rxconfig;

import com.qhh.network.exception.ApiException;

import io.reactivex.functions.Function;

/**
 * @author qinhaihang
 * @version $Rev$
 * @time 2018/4/7 16:56
 * @des 转换处理
 * @packgename com.example.qinhaihang.rxjava2retrofit2demo.net
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class HttpResultFunction<T> implements Function<HttpResult<T>, T> {
    @Override
    public T apply(HttpResult<T> tHttpResult) throws Exception {
        if (tHttpResult.code != 1) {
            throw new ApiException(tHttpResult.code);
        }
        return tHttpResult.data;
    }
}
