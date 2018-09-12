package com.qhh.network.exception;

/**
 * @author qinhaihang
 * @version $Rev$
 * @time 2018/3/23 15:15
 * @des ${TODO}
 * @packgename com.example.qinhaihang.homepagedemo.net
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class ApiException extends RuntimeException {

    //public static final int ACTION_FAILED = 0;
    //
    //public static final int ACTION_NO_PERMISSION = -1;
    //
    //public static final int LOGIN_TIME_OUT = -2;
    //
    //public static final int SUCESS = 200;
    //
    //public static final int ERRO = 201;
    //
    //public static final int ERRO_PARAM = 202;

    public int code;

    public ApiException(int code) {
        this(getApiExceptionMessage(code));
        this.code = code;
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    public int getCode() {
        return code;
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case -1:
                message = "非法操作";
                break;
            case -2:
                message = "数据存储失败";
                break;
            case -3:
                message = "数据读取失败";
                break;
            case -4:
                message = "缓存存储失败";
                break;
            case -5:
                message = "缓存读取失败";
                break;
            case -6:
                message = "文件读取失败";
                break;
            case -7:
                message = "登录失败";
                break;
            case -8:
                message = "不存在";
                break;
            case -9:
                message = "JSON数据格式错误";
                break;
            case -10:
                message = "类型错误";
                break;
            case -11:
                message = "数字匹配失败";
                break;
            case -12:
                message = "丢失必要数据";
                break;
            case -13:
                message = "数据已经存在";
                break;
            case -14:
                message = "权限认证失败";
                break;
            case -16:
                message = "别的终端登录";
                break;
            case -17:
                message = "API版本非法";
                break;
            case -18:
                message = "CURL操作异常";
                break;
            case -19:
                message = "记录未找到";
                break;
            case -20:
                message = "删除失败";
                break;
            case -21:
                message = "添加记录失败";
                break;
            case -22:
                message = "更新记录失败";
                break;
            case -995:
                message = "数据类型非法";
                break;
//            case -996:
//                message = "身份令牌过期";
//                break;
            case -997:
                message = "SESSION过期";
                break;
            case -998:
                message = "未知错误";
                break;
            case -999:
                message = "系统异常";
                break;
        }
        return message;
    }
}

