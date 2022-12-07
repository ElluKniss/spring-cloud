package com.dw.test;

import com.dw.proxy.JDKProxy;
import com.dw.service.CommonUse;
import com.dw.service.impl.FirstUse;

public class ProxyTest {

    public static void main(String[] args) {

        JDKProxy jdkProxy = new JDKProxy();
        CommonUse object = (CommonUse)jdkProxy.newProxy(new FirstUse());
//        CommonUse object =  (CommonUse) new JDKProxy().newProxy(new FirstUse());
//        object.action();
//        object.method2("123");

    }
}
