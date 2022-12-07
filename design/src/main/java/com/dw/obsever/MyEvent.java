package com.dw.obsever;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyEvent extends ApplicationEvent {

    public MyEvent(ApplicationContext source) {
        super(source);
        System.out.println("Myevent con");
    }

    @PostConstruct
    public void cacheData(){
        System.out.println("开始查询数据");
    }

    void echo(){
        System.out.println("预热缓存");
    }
}
