package com.stronger.gateway.domain.route.gateway;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stronger.gateway.domain.route.entity.GatewayDynamicRoute;

import java.util.List;

/**
 * <p>
 * 网关路由信息 服务类
 * </p>
 *
 * @author stronger
 * @since 2025-09-12
 */
public interface IGatewayDynamicRouteGateway extends IService<GatewayDynamicRoute> {

    /**
     * 查询所有可用的路由信息
     *
     * @return {@link List }<{@link GatewayDynamicRoute }>
     */
    List<GatewayDynamicRoute> selectEnabledRoutes();
}
