package com.dw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaServer
public class GateWayApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GateWayApp.class);

    }
}