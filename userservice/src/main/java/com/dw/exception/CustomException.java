package com.dw.exception;

import com.dw.eum.ResultCode;
import com.dw.util.MessageUtils;
import org.apache.commons.lang.StringUtils;

public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    /**
     * 业务状态码,用于接口响应中返回具体的 code
     */
    private ResultCode resultCode;

    public CustomException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public CustomException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public CustomException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public CustomException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public CustomException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }
}
