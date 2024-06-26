package com.dw.domain.com.dw.domain.vo;

import com.dw.eum.ResultCode;
import com.dw.eum.StatusCode;
import lombok.Data;

@Data
public class ResultVo {

    // 状态码
    private int code;

    // 状态信息
    private String msg;

    // 返回对象
    private Object data;

    public ResultVo(StatusCode statusCode, Object data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    //默认返回成功状态码，数据对象
    public ResultVo(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    // 手动设置返回vo
    public ResultVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 返回指定状态码，数据对象

    // 只返回状态码
}
