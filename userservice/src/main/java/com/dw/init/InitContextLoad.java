package com.dw.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 容器启动后执行一次run
 * order越小优先级越高
 */
@Component
@Order(3)
public class InitContextLoad implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("InitContextLoad1.........");
    }
}
