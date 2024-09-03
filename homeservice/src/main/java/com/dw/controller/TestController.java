package com.dw.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dw.entity.base.ServiceResponse;
import com.dw.fegin.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Resource
    private IUserService userService;

    @RequestMapping(value="/fegin",method = RequestMethod.GET)
    public ServiceResponse test(@RequestParam(value="userId",required=true) String userId, @RequestParam(value="syncNo",required=true)String syncNo){
        LOGGER.info("TestController.test");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("syncNo",syncNo);
        ServiceResponse serviceResponse = userService.delAllUserGroups(userId);
        return serviceResponse;
    }

    @RequestMapping("/testlog")
    public void log(){
        LOGGER.info("info.testlog");
        LOGGER.debug("debug.testlog");
        LOGGER.warn("warn.testlog");
        LOGGER.error("error.testlog");
    }
}
