package com.qhh.network.rxconfig;

/**
 * @author qinhaihang
 * @version $Rev$
 * @time 2018/4/7 15:29
 * @des 封装服务器返回的统一数据,统一示例
 * @packgename com.example.qinhaihang.rxjava2retrofit2demo.net
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class HttpResult<T> {

    public int code;
    public String msg;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
