package com.dw.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 业务相关所有返回信息请添加到这里
 */
public class Rsp {
//	@ApiModelProperty(name = "RSP_CODE", value = "业务返回编码", required = true, example = "00000")
	@JsonProperty("RSP_CODE")
	private String rspCode;
//	@ApiModelProperty(name = "RSP_DESC", value = "业务返回描述", required = true, example = "数据处理正确！")
	@JsonProperty("RSP_DESC")
	private String rspDesc;
//	@ApiModelProperty(name = "DATA", value = "服务返回数据", required = true)
	@JsonProperty("DATA")
	private Object data;

	public String getRspCode() {
		return rspCode;
	}

	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	public String getRspDesc() {
		return rspDesc;
	}

	public void setRspDesc(String rspDesc) {
		this.rspDesc = rspDesc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


}
