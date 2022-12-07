package com.dw.obsever;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("启动完成");
        myEvent.echo();
    }
}
