package com.stronger.platform.infrastructure.system;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author stronger
 * @version release-1.0.0
 * @class NacosRefreshParamConfig.class
 * @department Platform R&D
 * @date 2025/9/17
 * @desc do what?
 */
@Getter
@Component
@RefreshScope
public class NacosRefreshParamConfig {

    /**
     * 线程池核心线程数
     */
    @Value("${pub.thread.pool.core-size:10}")
    private int threadPoolCorePoolSize;

    /**
     * 线程池最大线程数
     */
    @Value("${pub.thread.pool.max-size:300}")
    private int threadPoolMaximumPoolSize;

    /**
     * 分页显示最大数量，默认200
     */
    @Value("${pub.page.max.size:200}")
    private int pageMaxSize;

    /**
     * 批量查询最大数量
     */
    @Value("${pub.batch.max.query:300}")
    private int maxBatchQueryQuantity;
}
