package com.dw.exception;

import com.dw.eum.AppCode;
import com.dw.eum.StatusCode;
import lombok.Getter;

@Getter
public class APIException extends RuntimeException {

    private int code;

    private String msg;

    // 默认异常使用APP_ERROR状态码
    public APIException(String msg) {
        super(msg);
        this.code = AppCode.APP_ERROR.getCode();
        this.msg = AppCode.APP_ERROR.getMsg();
    }

    // 手动设置异常
    public APIException( StatusCode statusCode, String msg) {
        super(msg);
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }
}
