package com.dw.eum;

import lombok.Getter;

@Getter
public enum AppCode implements StatusCode{

    APP_ERROR(2000, "业务异常"),
    PRICE_ERROR(2001, "价格异常");

    private final int code;

    private final String msg;

    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
