package com.dw.bean;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner, PriorityOrdered {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(args+"++++++++++++++++++++++++++++++");
    }

    @Override
    public int getOrder() {
        return 50;
    }
}
