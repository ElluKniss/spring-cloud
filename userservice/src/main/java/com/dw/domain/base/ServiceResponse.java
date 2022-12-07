package com.dw.domain.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务应答类
 */
@Slf4j
public class ServiceResponse {

    @JsonProperty("STATUS")
    private String status;
    @JsonProperty("MSG")
    private String msg;
    @JsonProperty("LOG_ID")
    private String logId;
    @JsonProperty("RSP")
    private Rsp rsp;

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
