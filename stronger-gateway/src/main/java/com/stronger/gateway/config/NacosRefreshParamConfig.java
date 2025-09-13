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
 * @desc Nacos动态参数配置
 */
@Getter
@Component
@RefreshScope
public class NacosRefreshParamConfig {

    /**
     * 本地调试
     */
    @Value("${gateway.local.debug:true}")
    private boolean localDebug;

    @Value("${gateway.local.route:[{\"id\":\"stronger-platform\",\"predicates\":[{\"name\":\"Path\",\"args\":{\"pattern\":\"/platform/**\"}}],\"filters\":[],\"uri\":\"http://127.0.0.1:5701\",\"metadata\":{},\"order\":0}]}")
    private String localRouteJson;

}
