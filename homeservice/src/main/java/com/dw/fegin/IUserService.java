package com.dw.fegin;

import com.dw.entity.base.ServiceResponse;
import com.dw.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "userservice", fallback = UserServiceFallback.class)
public interface IUserService {

    @RequestMapping(value = "/dispatcher", method = RequestMethod.POST)
    void getUserID();

    @RequestMapping(value = "/dispatcher", method = RequestMethod.POST)
    ServiceResponse delAllUserGroups(@RequestBody String body);

    @RequestMapping(value = "/dispatcher", method = RequestMethod.POST)
    ServiceResponse queryUserGroupDetail(@RequestBody String body);

    @RequestMapping(value = "/dispatcher", method = RequestMethod.POST)
    ServiceResponse queryUserGroups(@RequestBody String body);

    @RequestMapping(value = "/dispatcher", method = RequestMethod.POST)
    ServiceResponse queryUserGroupMembers(@RequestBody String body);
}
