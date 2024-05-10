package com.dw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaClient
public class GateWayApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GateWayApp.class);

    }
}