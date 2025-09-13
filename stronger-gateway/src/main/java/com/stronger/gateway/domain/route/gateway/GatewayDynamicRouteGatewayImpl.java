package com.stronger.gateway.domain.route.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stronger.gateway.domain.route.entity.GatewayDynamicRoute;
import com.stronger.gateway.domain.route.mapper.GatewayDynamicRouteMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 网关路由信息 服务实现类
 * </p>
 *
 * @author stronger
 * @since 2025-09-12
 */
@Component
public class GatewayDynamicRouteGatewayImpl extends ServiceImpl<GatewayDynamicRouteMapper, GatewayDynamicRoute> implements IGatewayDynamicRouteGateway {

    @Override
    public List<GatewayDynamicRoute> selectEnabledRoutes() {
        return this.list(lambdaQuery().eq(GatewayDynamicRoute::getIsEnable, Boolean.TRUE));
    }
}
