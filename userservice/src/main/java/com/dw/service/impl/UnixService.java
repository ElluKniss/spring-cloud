package com.dw.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.dw.domain.base.Rsp;
import com.dw.domain.base.ServiceConstant;
import com.dw.service.IActionService;
import com.dw.util.AESBase64Util;
import com.dw.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * woAPI统一入口
 * 更具body里面的interface确定接口名
 */
@Slf4j
@Service("UnixService")
public class UnixService implements IActionService {

    private static final String SECRET_KEY = "fb1d497e55e0433b";

    private static final String URL = "http://120.52.8.241/woapi/dispatcher";

    @Override
    public Rsp doAction(JSONObject jsonParam) {
        // 请求响应
        Rsp resp = new Rsp();
        resp.setRspCode(ServiceConstant.RESP_SUCCESS);
        resp.setRspDesc(ServiceConstant.MSG_RESP_SUCCESS);

        // 渠道来源
        String channel = jsonParam.getJSONObject("header").getString("channel");
        // 明文报文来源，需要aes加密
        JSONObject body = jsonParam.getJSONObject("body");
        // 请求云盘指定接口名
        String serviceName = body.getString("interface");

        JSONObject header = new JSONObject();
        header.put("channel", channel);
        header.put("key", serviceName);

        JSONObject reqBody = new JSONObject();
        String deBody = null;
        try {
            deBody = AESBase64Util.encrypt(body.toString(), SECRET_KEY);
        } catch (Exception e) {
            log.error("encrypt error.");
            return resp;
        }
        reqBody.put("header", header);
        reqBody.put("body", deBody);

        log.info("请求云盘接口serviceName:{}", serviceName);
        String respStr = null;
        try {
            respStr = HttpUtil.doPost(URL, reqBody.toString());
        } catch (Exception e) {
            resp.setRspCode(ServiceConstant.STATUS_ERR);
            resp.setRspDesc(ServiceConstant.MSG_ERR);
            log.error("云盘接口请求异常:{}！", e.getMessage());
            return resp;
        }
        log.info("云盘接口serviceName:{}返回结果:{}", serviceName, respStr);

        if(StringUtils.isEmpty(respStr)){
            JSONObject respJson = JSONObject.parseObject(respStr);
            JSONObject rsp = respJson.getJSONObject("RSP");

            String data = rsp.getString("DATA");
            // 消息体DATA解密
            String decrypt = null;
            try {
                decrypt = AESBase64Util.decrypt(data, SECRET_KEY);
            } catch (Exception e) {
                log.error("decrypt error.");
                return resp;
            }
            try {
                JSONObject jsonObject = JSONObject.parseObject(decrypt);
                resp.setData(jsonObject);
            } catch (JSONException e) {
                // Array格式
                JSONArray jsonArray = JSONArray.parseArray(decrypt);
                resp.setData(jsonArray);
            } catch (Exception e) {
                // 其他不解析
                resp.setData(decrypt);
            }

            if(!ServiceConstant.RESP_SUCCESS.equals(rsp.getString("RSP_CODE"))){
                resp.setRspCode(rsp.getString("RSP_CODE"));
                resp.setRspDesc(rsp.getString("RSP_DESC"));
                log.error("业务错误码:{},描述:{}", rsp.getString("RSP_CODE"), rsp.getString("RSP_DESC"));
                return resp;
            }

            log.info("云盘接口serviceName:{}返回结果data:{}", serviceName, decrypt);
            return resp;
        }

        resp.setRspCode(ServiceConstant.STATUS_ERR);
        resp.setRspDesc(ServiceConstant.MSG_ERR);
        log.error("云盘接口请求失败！");
        return resp;
    }
}
