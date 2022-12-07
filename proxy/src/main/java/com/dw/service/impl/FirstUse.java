package com.dw.service.impl;

import com.dw.aspect.SelfDefine;
import com.dw.service.CommonUse;
import org.springframework.stereotype.Service;

@Service
public class FirstUse implements CommonUse {
    @Override
    @SelfDefine
    public void action() {
        System.out.println("first action content");
    }

    @Override
    public void method1() {
        System.out.println("first method1 content");
    }

    @Override
    public void method2(String name) {
        System.out.println(name);
    }
}
