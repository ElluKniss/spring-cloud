package com.dw.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dw.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getUserById(@Param("id") String id);
}
