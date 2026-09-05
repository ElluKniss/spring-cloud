package com.dw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dw.domain.User;

public interface UserService extends IService<User> {

    User getUser(String name, String password);
}
