package com.dw.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class SqlSessionConfig {

    Logger log = LoggerFactory.getLogger(SqlSessionConfig.class);
    @Resource
    private ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(applicationContext.getBean(DataSource.class));
        org.springframework.core.io.Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis/mapper/UserMapper.xml");
        ssfb.setMapperLocations(resource);
        try {
            SqlSessionFactory object = ssfb.getObject();

            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
