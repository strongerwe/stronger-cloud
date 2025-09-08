package com.stronger.gateway.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stronger.gateway.entity.SsoUser;
import com.stronger.gateway.mapper.SsoUserMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qiang.w
 * @since 2025-08-02
 */
@Component
public class SsoUserGatewayImpl extends ServiceImpl<SsoUserMapper, SsoUser> implements SsoUserGateway {

}
