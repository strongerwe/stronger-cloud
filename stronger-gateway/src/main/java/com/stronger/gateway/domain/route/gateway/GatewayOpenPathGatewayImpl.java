package com.stronger.gateway.domain.route.gateway;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stronger.gateway.domain.route.entity.GatewayOpenPath;
import com.stronger.gateway.domain.route.mapper.GatewayOpenPathMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 开放apiPath信息 服务实现类
 * </p>
 *
 * @author stronger
 * @since 2025-09-12
 */
@Component
public class GatewayOpenPathGatewayImpl extends ServiceImpl<GatewayOpenPathMapper, GatewayOpenPath> implements IGatewayOpenPathGateway {

    @Override
    public List<GatewayOpenPath> selectEnabledOpenPaths() {
        return this.list(new QueryWrapper<GatewayOpenPath>().lambda().eq(GatewayOpenPath::getIsEnable, Boolean.TRUE));
    }
}
