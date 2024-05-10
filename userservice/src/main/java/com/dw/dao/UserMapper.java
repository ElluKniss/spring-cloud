package com.dw.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dw.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    User getUserById(String id, String status);
}
