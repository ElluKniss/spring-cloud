package com.dw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    /**
     * 自定义线程池实例，默认方法名
     * @return
     */
    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        // 核心线程数
        pool.setCorePoolSize(5);
        // 最大线程数
        pool.setMaxPoolSize(20);
        // 线程活跃时间
        pool.setKeepAliveSeconds(0);
        // 任务队列容量
        pool.setQueueCapacity(10);
        // 线程名前缀
        pool.setThreadNamePrefix("dw-");
        // 拒绝策略直接丢弃
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 等待所有任务结束后再关闭线程池
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }
}
