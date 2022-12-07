package com.dw.config;

import org.aspectj.weaver.ast.Or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserDao userDao(){

        System.out.println(1);
        return new UserDao();
    }

    @Bean
    public OrderDao orderDao(){

        userDao();
        System.out.println(2);
        return new OrderDao();
    }
}

class UserDao{

}

class OrderDao{

}