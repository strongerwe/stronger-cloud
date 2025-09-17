package com.stronger.platform.config;


import com.stronger.platform.infrastructure.system.NacosRefreshParamConfig;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author stronger
 * @version release-1.0.0
 * @class SpringThreadPoolConfiguration.class
 * @department Platform R&D
 * @date 2025/9/17
 * @desc 线程池bean配置
 */
@Configuration
public class ThreadPoolConfiguration {

    @Resource
    private NacosRefreshParamConfig nacosRefreshParamConfig;

    @Bean
    public ExecutorService customTaskExecutor() {
        return new ThreadPoolExecutor(
                /* 核心线程数 */
                nacosRefreshParamConfig.getThreadPoolCorePoolSize(),
                /* 最大线程数 */
                nacosRefreshParamConfig.getThreadPoolMaximumPoolSize(),
                0L,
                TimeUnit.MILLISECONDS,
                /* 队列 */
                new LinkedBlockingQueue<>(),
                /* 队列拒绝策略 */
                new ThreadPoolExecutor.AbortPolicy());
    }
}
