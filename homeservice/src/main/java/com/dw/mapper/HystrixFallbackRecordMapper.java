package com.dw.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface HystrixFallbackRecordMapper {

    @Insert("insert into t_hystrix_fb(platform,methodSign,url,content,header,encodeType,fallbackTime) values(#{platform},#{methodSign},#{url},#{content},#{header},#{encodeType},#{fallbackTime})")
    int insert(Map<String, Object> body);
}
