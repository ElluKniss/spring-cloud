package com.dw.controller;

import com.dw.config.ConfigProperties;
import com.dw.domain.User;
import com.dw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private ConfigProperties configProperties;

    @GetMapping("/query/{userID}")
    public User queryUser(@PathVariable String userID){
        User user = null;//userService.getById(userID);
//        if (null == user){
//            log.info("query getUserById ...");
//            user = userService.getById(userID);
//        }
        return user;
    }

    @GetMapping("now")
    public String getNowTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(configProperties.getDateformat(), Locale.CHINA));
    }

    @RequestMapping("/log")
    public void testLog(){
        log.debug("debug----");
        log.info("info----");
        log.warn("warn----");
        log.error("error---");
    }
}
