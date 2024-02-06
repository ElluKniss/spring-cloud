package com.dw.fallback;

import com.dw.entity.base.Rsp;
import com.dw.entity.base.ServiceResponse;
import com.dw.fegin.IUserService;
import com.dw.util.HystrixUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class UserServiceFallback implements IUserService {

    @Resource
    private HystrixUtil hystrixUtil;

    @Override
    public void getUserID() {

    }

    @Override
    public ServiceResponse delAllUserGroups(String body) {
        return getServiceResponse(body);
    }

    @Override
    public ServiceResponse queryUserGroupDetail(String body) {
        return getServiceResponse(body);
    }

    private ServiceResponse getServiceResponse(String body) {
        log.info("fallback---------------------");
        Rsp rsp = new Rsp();
        rsp.setRspCode("500");
        rsp.setRspDesc("系统繁忙，请稍后再试");
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.getSuccessResponse(rsp);
        serviceResponse.setMsg("系统繁忙，请稍后再试");
        serviceResponse.setStatus("5001");
        hystrixUtil.addHystrixRecord(this.getClass().getInterfaces()[0].getName(),
                Thread.currentThread().getStackTrace()[1].getMethodName(), body, null, null);
        return serviceResponse;
    }

    @Override
    public ServiceResponse queryUserGroups(String body) {
        return null;
    }

    @Override
    public ServiceResponse queryUserGroupMembers(String body) {
        return null;
    }
}
