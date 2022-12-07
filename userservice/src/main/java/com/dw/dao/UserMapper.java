package com.dw.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dw.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select id,user_name as name,phone from t_user where  status = #{status} and id = #{id}")
    User getUserById(String id, String status);
}
