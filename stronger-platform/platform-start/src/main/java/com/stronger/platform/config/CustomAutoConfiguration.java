package com.stronger.platform.config;


import com.stronger.commons.framework.PlatformFrameworkCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author stronger
 * @version release-1.0.0
 * @class CustomAutoConf.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 自定义启动Bean
 */
@Configuration
public class CustomAutoConfiguration {

    @Bean("platformFrameworkCore")
    public PlatformFrameworkCore platformFrameworkCore(ApplicationContext ioc) {
        return new PlatformFrameworkCore(ioc);
    }
}
