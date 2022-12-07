package com.dw.domain.base;

import lombok.Data;

/**
 * 业务相关所有返回信息请添加到这里
 */
@Data
public class Rsp {
    private String rspCode;
    private String rspDesc;
    private Object data;

//	@Override
//	public String toString() {
//		return JSON.toJSONString(this);
//	}

}
