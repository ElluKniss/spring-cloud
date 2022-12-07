package com.dw.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    public Object target;

    public Object newProxy(Object obj) {
        this.target = obj;
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);
        saveClass(target.getClass(), "ProxyDW");
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    private void saveClass(Class<?> clazz, String proxyDW) {
        byte[] bytes = ProxyGenerator.generateProxyClass(proxyDW, clazz.getInterfaces());
        String path = clazz.getResource(".").getPath();

        System.out.println(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + proxyDW + ".class");
            fos.write(bytes);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start aop");
        System.out.println("execute method is " + method.getName());
        Object invoke = method.invoke(target, args);
        System.out.println("end aop");
        return invoke;
    }
}
