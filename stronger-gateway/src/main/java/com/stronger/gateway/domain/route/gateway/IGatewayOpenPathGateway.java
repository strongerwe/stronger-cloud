package com.stronger.gateway.domain.route.gateway;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stronger.gateway.domain.route.entity.GatewayOpenPath;

import java.util.List;

/**
 * <p>
 * 开放apiPath信息 服务类
 * </p>
 *
 * @author stronger
 * @since 2025-09-12
 */
public interface IGatewayOpenPathGateway extends IService<GatewayOpenPath> {

    /**
     * 查询所有启用的开放apiPath信息
     *
     * @return {@link List }<{@link GatewayOpenPath }>
     */
    List<GatewayOpenPath> selectEnabledOpenPaths();
}
