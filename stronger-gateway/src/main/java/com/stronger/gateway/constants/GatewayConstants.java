package com.stronger.gateway.constants;


/**
 * @author stronger
 * @version release-1.0.0
 * @interface GatewayConstants.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc 常量
 */
public interface GatewayConstants {

    /**
     * 动态路由缓存key
     */
    String CACHE_DYNAMIC_ROUTES_KEY = "gateway:routes";

    /**
     * 开放apiPath缓存key
     */
    String CACHE_OPEN_PATH_KEY = "gateway:open:path";

    String UNAUTHORIZED = "{\"code\": \"unauthorized\",\"message\": \"Full authentication is required to access this resource\"}";
    String UNAUTHORIZED_TOKEN_EXPIRED = "{\"code\": \"unauthorized\",\"message\": \"The authentication period has expired\"}";


    /**
     * 路由前缀
     */
    String K8S_ROUTE_PREFIX = "http://";

    /**
     * 路由前缀
     */
    String DEFAULT_ROUTE_PREFIX = "lb://";

    String RETURN_CONTENT_TYPE = "application/json;charset=UTF-8";
}
