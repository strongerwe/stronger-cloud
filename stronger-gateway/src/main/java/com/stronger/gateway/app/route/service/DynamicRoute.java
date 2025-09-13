package com.stronger.gateway.app.route.service;


import com.stronger.commons.utils.StringUtils;

/**
 * @author stronger
 * @version release-1.0.0
 * @class DynamicRoute.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc 动态路由Record
 */
public record DynamicRoute(String routeId,
                           String application,
                           String serviceDiscovery,
                           String pathPattern) {

    public DynamicRoute {
        if (StringUtils.isBlank(routeId)) {
            throw new IllegalArgumentException("routeId不能为空");
        }
        if (StringUtils.isBlank(application)) {
            throw new IllegalArgumentException("application不能为空");
        }
        if (StringUtils.isBlank(serviceDiscovery)) {
            throw new IllegalArgumentException("serviceDiscovery不能为空");
        }
        if (StringUtils.isBlank(pathPattern)) {
            throw new IllegalArgumentException("pathPattern不能为空");
        }
    }
}
