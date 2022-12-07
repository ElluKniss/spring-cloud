package com.dw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dw.dao.UserMapper;
import com.dw.domain.User;
import com.dw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(String userId) {
        log.debug("debug++++++++");
        log.info("info++++++++++");
        log.warn("warn++++++++++");
        log.error("error+++++++++");
        User user = userMapper.getUserById(userId, "1");
        return user;
    }

    /**
     * 默认taskExecutor
     * @throws InterruptedException
     */
    @Async
    public void asyncQuery(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("query db ..."+ Thread.currentThread().getName());
    }
}
