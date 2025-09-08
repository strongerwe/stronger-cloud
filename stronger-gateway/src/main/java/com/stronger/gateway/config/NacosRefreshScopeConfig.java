package com.stronger.gateway.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author stronger
 * @version release-1.0.0
 * @class NacosRefreshScopeConfig.class
 * @department Platform R&D
 * @date 2025/9/8
 * @desc do what?
 */
@Getter
@Component
@RefreshScope
public class NacosRefreshScopeConfig {
    @Value("${hello.ai:123}")
    private String vel;

    @Value("${ai.api-key.dashscope}")
    private String dashscopeKey;
}
