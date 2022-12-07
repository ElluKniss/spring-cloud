package com.dw.test;

import com.dw.aspect.SelfDefine;
import com.dw.proxy.CglibProxy;
import com.dw.service.CommonUse;
import com.dw.service.impl.FirstUse;
import net.sf.cglib.core.DebuggingClassWriter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CglibTest {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code2");
//        FirstUse o = (FirstUse) cglibProxy.newProxy(new FirstUse());
        CommonUse o = (new FirstUse());
        Method[] methods = o.getClass().getMethods();
        Arrays.stream(methods).forEach(a-> {
            Annotation[] declaredAnnotations = a.getDeclaredAnnotations();
            Arrays.stream(declaredAnnotations).forEach(b-> System.out.println(b.annotationType().getName()));

        });
        o.action();
    }

}
