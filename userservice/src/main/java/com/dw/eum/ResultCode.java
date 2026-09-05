package com.dw.eum;

public enum ResultCode implements StatusCode{

    SUCCESS(1000, "请求成功"),
    FAILED(1001, "请求失败"),
    RESPONSE_PACK_ERROR(1003, "response返回包装失败"),
    VALIDATE_ERROR(1002, "参数校验失败"),
    USER_NOT_EXISTS(1004, "用户名不存在"),
    PASSWORD_NOT_MATCH(1005, "密码不正确");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return  code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
