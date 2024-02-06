package com.dw.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * bean生命周期阶段
 * 实例化instance---------属性赋值--------------初始化init---------销毁
 */
@Component
public class BeanLife implements InitializingBean, BeanNameAware, ApplicationContextAware, CommandLineRunner, PriorityOrdered {

    private String name;

    public void setName(String name) {
        System.out.println("5.属性赋值");
        this.name = name;
    }

    public BeanLife() {
        System.out.println("3.构造器方法");
    }

    @Override
    public void setBeanName(String s) {
        //第一组aware接口BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        System.out.println("6.BeanNameAware接口实现设置beanName");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("9.init 方法");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //第二组aware接口xxx+Aware+Processor
        // 可以看到并不是所有的 Aware 接口都使用同样的方式调用。Bean××Aware 都是在代码中直接调用的，
        // 而 ApplicationContext 相关的 Aware 都是通过 BeanPostProcessor#postProcessBeforeInitialization() 实现的
        // 自己看一下 ApplicationContextAwareProcessor 这个类的源码，
        // 就是判断当前创建的 Bean 是否实现了相关的 Aware 方法，如果实现了会调用回调方法将资源传递给 Bean。
        System.out.println("7.给bean添加上下文信息");

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------commondline");
    }

    @Override
    public int getOrder() {
        return 30;
    }
}
