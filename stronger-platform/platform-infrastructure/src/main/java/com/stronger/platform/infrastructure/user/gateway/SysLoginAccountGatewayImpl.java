package com.stronger.platform.infrastructure.user.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stronger.platform.domain.user.entity.SysLoginAccount;
import com.stronger.platform.domain.user.gateway.SysLoginAccountGateway;
import com.stronger.platform.infrastructure.user.mapper.SysLoginAccountMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统登录账户表 服务实现类
 * </p>
 *
 * @author stronger
 * @since 2025-09-13
 */
@Service
public class SysLoginAccountGatewayImpl extends ServiceImpl<SysLoginAccountMapper, SysLoginAccount> implements SysLoginAccountGateway {

}
