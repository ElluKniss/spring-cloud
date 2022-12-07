package com.dw.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log.info("set application successful");
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 从容器获取对应bean
     * @param beanName
     * @return 实例bean
     * @param <T>
     */
    public static <T> T getBean(String beanName){
        if (applicationContext.containsBean(beanName)){
            T bean = (T) applicationContext.getBean(beanName);
            return bean;
        }
        return null;
    }
}
