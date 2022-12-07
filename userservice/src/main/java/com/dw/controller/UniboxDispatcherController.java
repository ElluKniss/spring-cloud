package com.dw.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.dw.domain.base.Rsp;
import com.dw.domain.base.ServiceConstant;
import com.dw.domain.base.ServiceResponse;
import com.dw.service.IActionService;
import com.dw.util.ApplicationUtils;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@RestController
public class UniboxDispatcherController {

    @RequestMapping("/core")
    public ServiceResponse dispatcher(HttpServletRequest request) throws Exception {
        ServiceResponse serviceResponse = new ServiceResponse();
        String logId = UUID.randomUUID().toString().replace("-", "");
        serviceResponse.setLogId(logId);

        JSONObject jsonParam = getRequestJson(request);
        if (!jsonParam.containsKey("header")) {
            serviceResponse.setMsg(ServiceConstant.MSG_EINVAL);
            serviceResponse.setStatus(ServiceConstant.STATUS_EINVAL);
            return serviceResponse;
        }
        if (!jsonParam.containsKey("body")) {
            serviceResponse.setMsg(ServiceConstant.MSG_EINVAL);
            serviceResponse.setStatus(ServiceConstant.STATUS_EINVAL);
            return serviceResponse;
        }

        JSONObject header = jsonParam.getJSONObject("header");
        String key = header.getString("key");

        IActionService actionService = ApplicationUtils.getBean(key + "Service");

        serviceResponse.setMsg(ServiceConstant.MSG_EINVAL);
        serviceResponse.setStatus(ServiceConstant.STATUS_EINVAL);

        Optional.of(actionService).ifPresent(action -> {
            Rsp rsp = null;
            try {
                rsp = action.doAction(jsonParam);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            serviceResponse.getSuccessResponse(rsp);
            serviceResponse.setMsg(ServiceConstant.MSG_SUCCESS);
            serviceResponse.setStatus(ServiceConstant.STATUS_SUCCESS);
            log.info("响应报文：" + JSON.toJSON(serviceResponse));
        });

        return serviceResponse;

    }

    protected JSONObject getRequestJson(HttpServletRequest request) throws Exception {
        JSONObject json;
        ServletInputStream sis = request.getInputStream();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        int read = 0;
        byte[] buf = new byte[4096];
        while ((read = sis.read(buf)) > 0) {
            bao.write(buf, 0, read);
            bao.flush();
        }
        String jsonRequest = new String(bao.toByteArray(), "UTF-8");
        sis.close();
        bao.close();

        @SuppressWarnings("rawtypes")
        Enumeration headerNames = request.getHeaderNames();
        JSONObject reqheader = new JSONObject();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            if (StringUtils.isEmpty(headerName)) {
                String value = request.getHeader(headerName) == null ? "" : request.getHeader(headerName);
                reqheader.put(headerName, value);
            }
        }

        json = JSONObject.parseObject(jsonRequest);

        json.put("requestheader", reqheader);

        return json;
    }
}
