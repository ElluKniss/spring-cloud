package com.dw.entity.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务应答类
 */
public class ServiceResponse {
    private static Logger logger =  LoggerFactory.getLogger(ServiceResponse.class);


//    @ApiModelProperty(name = "STATUS", value = "服务请求结果编码", required = true, example = "0000")
    @JsonProperty("STATUS")
    private String status;
//    @ApiModelProperty(name = "MSG", value = "服务请求结果描述", required = true, example = "服务调用成功！")
    @JsonProperty("MSG")
    private String msg;
    @JsonProperty("LOGID")
    private String logId;
//    @ApiModelProperty(name = "RSP", value = "服务返回业务数据", required = true)
    @JsonProperty("RSP")
    private Rsp rsp;


    /**
     * 默认构造函数
     */
    public ServiceResponse(){
    }

    /**
     * 服务成功调用应答信息
     * @param rsp
     * @return ServiceResponse
     */
    public ServiceResponse getSuccessResponse(Rsp rsp){
        this.setStatus(ServiceConstant.STATUS_SUCCESS);
        this.setMsg(ServiceConstant.MSG_SUCCESS);
        this.setRsp(rsp);
        return this;
    }
    /**
     * 服务其他错误返回结果
     * @param errorStatus
     * @param errorMsg
     * @param rsp
     * @return
     */
    public ServiceResponse getErrorResponse(String errorStatus, String errorMsg, Rsp rsp){
        this.setStatus(errorStatus);
        this.setMsg(errorMsg);
        this.setRsp(rsp);
        return this;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Rsp getRsp() {
        return rsp;
    }

    public void setRsp(Rsp rsp) {
        this.rsp = rsp;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }
    
}
