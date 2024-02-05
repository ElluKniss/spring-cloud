package com.dw.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("beanLife".equals(beanName)) {
            System.out.println("8.bean初始化阶段-------before");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("beanLife".equals(beanName)) {
            System.out.println("10.bean初始化阶段-------after");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
