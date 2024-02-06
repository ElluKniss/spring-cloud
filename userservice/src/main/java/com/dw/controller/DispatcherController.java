package com.dw.controller;

import com.dw.entity.Rsp;
import com.dw.entity.ServiceConstant;
import com.dw.entity.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class DispatcherController {


    @RequestMapping(value = "/dispatcher", method = RequestMethod.POST)
    public ServiceResponse dispatcher(String key) throws Exception {
        log.info("request from home, key is {} ", key);
        long startTime = System.currentTimeMillis();
        ServiceResponse serviceResponse = new ServiceResponse();
        String logId = UUID.randomUUID().toString().replace("-", "");
        serviceResponse.setLogId(logId);
        MDC.put("log_id", logId);

        serviceResponse.setMsg(ServiceConstant.MSG_SUCCESS);
        serviceResponse.setStatus(ServiceConstant.STATUS_SUCCESS);

        Rsp rsp = new Rsp();

        int a = 1/0;

        MDC.remove("log_id");
        MDC.remove("wohometraceid");
        return serviceResponse;

    }
}
