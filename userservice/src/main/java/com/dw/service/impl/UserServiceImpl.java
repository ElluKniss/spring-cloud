package com.dw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dw.dao.UserMapper;
import com.dw.domain.User;
import com.dw.eum.ResultCode;
import com.dw.exception.CustomException;
import com.dw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 密码加盐的固定盐值。service 与测试用例共用此常量,保证两侧算法一致。
     * 生产环境建议改为从配置中心/外部配置读取,并做到每个用户一份随机盐。
     */
    public static final String PASSWORD_SALT = "dw@2024#salt";

    @Resource
    private UserMapper userMapper;

    public User getUserById(String userId) {
        log.debug("debug++++++++");
        log.info("info++++++++++");
        log.warn("warn++++++++++");
        log.error("error+++++++++");
        User user = userMapper.getUserById(userId, "1");
        return user;
    }

    /**
     * Ĭ��taskExecutor
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

    public User getUser(String username, String password) {

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, username));
        if(user == null){
            log.info("登录用户：{} 不存在.", username);
            throw new CustomException(ResultCode.USER_NOT_EXISTS);

        }

        // 把参数自带的password加盐后进行md5加密,和数据库查出来的user的password比较
        String md5Password = DigestUtils.md5DigestAsHex((PASSWORD_SALT + password).getBytes(StandardCharsets.UTF_8));
        if (!md5Password.equals(user.getPassword())) {
            log.info("登录用户：{} 密码错误.", username);
            throw new CustomException(ResultCode.PASSWORD_NOT_MATCH);
        }

        return user;
    }
}
