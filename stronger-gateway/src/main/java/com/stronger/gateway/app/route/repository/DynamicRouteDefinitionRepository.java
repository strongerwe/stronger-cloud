package com.stronger.gateway.app.route.repository;


import com.stronger.gateway.app.route.service.DynamicRouteService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author stronger
 * @version release-1.0.0
 * @class DynamicRouteDefinitionRepository.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc 动态路由加载仓库
 */
@Component
public class DynamicRouteDefinitionRepository implements RouteDefinitionRepository {

    private final DynamicRouteService dynamicRouteService;

    public DynamicRouteDefinitionRepository(DynamicRouteService dynamicRouteService) {
        this.dynamicRouteService = dynamicRouteService;
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(dynamicRouteService.selectRouteDefinitionList());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
