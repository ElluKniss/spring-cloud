package com.dw;

import com.dw.service.impl.FirstUse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        FirstUse bean = run.getBean(FirstUse.class);
        bean.action();
    }
}
