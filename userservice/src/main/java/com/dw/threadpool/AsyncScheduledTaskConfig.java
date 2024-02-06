package com.dw.threadpool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncScheduledTaskConfig {

    @Value("${spring.pool.maxSize}")
    private int maxSize;

    @Value("${spring.pool.coreSize}")
    private int coreSize;

    @Value("${spring.pool.queueSize}")
    private int queueSize;

    @Value("${spring.pool.keepAlive}")
    private int keepLive;

    @Value("${spring.pool.prefixName}")
    private String prefixName;

    @Bean
    public Executor myAsync() {

        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(coreSize);
        threadPool.setMaxPoolSize(maxSize);
        threadPool.setKeepAliveSeconds(keepLive);
        threadPool.setQueueCapacity(queueSize);
        threadPool.setThreadNamePrefix(prefixName);
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        threadPool.initialize();
        return threadPool;
    }
}
