package com.stronger.gateway.app.route.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stronger.commons.utils.StringUtils;
import com.stronger.gateway.config.NacosRefreshParamConfig;
import com.stronger.gateway.config.redis.RedisGateway;
import com.stronger.gateway.constants.GatewayConstants;
import com.stronger.gateway.constants.ServiceDiscoveryEnums;
import com.stronger.gateway.domain.route.entity.GatewayDynamicRoute;
import com.stronger.gateway.domain.route.gateway.IGatewayDynamicRouteGateway;
import com.stronger.redis.IRedisGateway;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stronger
 * @version release-1.0.0
 * @class DynamicRouteService.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc 动态路由Service
 */
@Slf4j
@Service
public class DynamicRouteService {

    @Resource
    private RedisGateway redisGateway;
    @Resource
    private NacosRefreshParamConfig nacosRefreshParamConfig;
    @Resource
    private IGatewayDynamicRouteGateway gatewayDynamicRouteGateway;

    /**
     * 获取动态路由
     *
     * @return {@link List }<{@link RouteDefinition }>
     */
    public List<RouteDefinition> selectRouteDefinitionList() {
        if (nacosRefreshParamConfig.isLocalDebug()) {
            return JSONArray.parseArray(nacosRefreshParamConfig.getLocalRouteJson(), RouteDefinition.class);
        }
        List<RouteDefinition> routeDefinitions = getRouteDefinitionsFromRedis();
        if (CollectionUtils.isEmpty(routeDefinitions)) {
            routeDefinitions = getRouteDefinitionsFromDbTable();
        }
        return routeDefinitions;
    }

    /**
     * redis获取动态路由缓存
     *
     * @return {@link List }<{@link RouteDefinition }>
     */
    private List<RouteDefinition> getRouteDefinitionsFromRedis() {
        List<RouteDefinition> list = null;
        try {
            String redisJson = redisGateway.get(GatewayConstants.CACHE_DYNAMIC_ROUTES_KEY);
            if (StringUtils.isNotEmpty(redisJson)) {
                list = new Gson().fromJson(redisJson, new TypeToken<List<RouteDefinition>>() {
                }.getType());
            }
        } catch (Exception e) {
            log.error("DynamicRouteService.getRouteDefinitionsFromRedis(): ####GET REDIS Exception|{}", e.getMessage());
        }
        log.debug("DynamicRouteService.getRouteDefinitionsFromRedis()|From Redis:{}", new Gson().toJson(list));
        return list;
    }

    /**
     * 数据库获取动态配置
     * * 获取成功设置缓存与失效时间
     *
     * @return {@link List }<{@link RouteDefinition }>
     */
    private List<RouteDefinition> getRouteDefinitionsFromDbTable() {
        // 获取数据库路由
        List<RouteDefinition> list = routeDefinitionList();
        try {
            if (CollectionUtils.isNotEmpty(list)) {
                redisGateway.set(
                        GatewayConstants.CACHE_DYNAMIC_ROUTES_KEY,
                        new Gson().toJson(list),
                        IRedisGateway.HOUR_24);
            }
        } catch (Exception e) {
            log.error("DynamicRouteService.getRouteDefinitionsFromDbTable(): ####SET REDIS Exception|{}"
                    , e.getMessage());
        }
        log.debug("DynamicRouteService.getRouteDefinitionsFromDbTable()|From DbTable:{}", new Gson().toJson(list));
        return list;
    }

    private List<RouteDefinition> routeDefinitionList() {
        List<DynamicRoute> list = dynamicRouteList();
        List<RouteDefinition> routes = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return routes;
        }
        list.forEach(i -> {
            try {
                RouteDefinition route = new RouteDefinition();
                route.setId(i.routeId());
                if (i.serviceDiscovery().equals(ServiceDiscoveryEnums.K8S.getType())) {
                    route.setUri(new URI(GatewayConstants.K8S_ROUTE_PREFIX + i.application()));
                } else {
                    route.setUri(new URI(GatewayConstants.DEFAULT_ROUTE_PREFIX + i.application()));
                }
                List<PredicateDefinition> predicates = new ArrayList<>();

                PredicateDefinition path = new PredicateDefinition();
                path.setName("Path");
                Map<String, String> args = new HashMap<>();
                args.put("pattern", i.pathPattern());
                path.setArgs(args);
                predicates.add(path);
                route.setPredicates(predicates);
                routes.add(route);
            } catch (URISyntaxException e) {
                log.error("DynamicRouteService.routeDefinitionList(): ####URISyntaxException|{}"
                        , e.getMessage());
            }
        });
        return routes;
    }

    /**
     * 数据库获取Routes
     *
     * @return {@link List }<{@link DynamicRoute }>
     */
    private List<DynamicRoute> dynamicRouteList() {
        List<GatewayDynamicRoute> list = gatewayDynamicRouteGateway.selectEnabledRoutes();
        List<DynamicRoute> results = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            log.error("DynamicRouteService.dynamicRouteList(): ####DYNAMICROUTES DOES NOT EXIST!!!!!");
            return results;
        }
        for (GatewayDynamicRoute i : list) {
            results.add(new DynamicRoute(i.getRouteId(), i.getApplication(), i.getServiceDiscovery(), i.getPathPattern()));
        }
        return results;
    }

    /**
     * 清除动态路由缓存
     *
     * @return boolean
     */
    public boolean cleanRouteDefinitionsInRedis() {
        return redisGateway.delete(GatewayConstants.CACHE_DYNAMIC_ROUTES_KEY);
    }
}
