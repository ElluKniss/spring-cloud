package com.dw.domain.base;

import com.dw.eum.ResultCode;
import lombok.Data;

/**
 * 业务相关所有返回信息请添加到这里
 */
@Data
public class Rsp {
    private String code;
    private String message;
    private Object data;

//	@Override
//	public String toString() {
//		return JSON.toJSONString(this);
//	}

    public Rsp(){

    }

    public Rsp(Object data) {
        this.code = String.valueOf(ResultCode.SUCCESS.getCode());
        this.message = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

}
