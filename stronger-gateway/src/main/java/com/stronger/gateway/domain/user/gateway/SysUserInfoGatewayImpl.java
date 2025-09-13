package com.stronger.gateway.domain.user.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stronger.gateway.domain.user.entity.SysUserInfo;
import com.stronger.gateway.domain.user.mapper.SysUserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户信息表 服务实现类
 * </p>
 *
 * @author stronger
 * @since 2025-09-12
 */
@Service
public class SysUserInfoGatewayImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements ISysUserInfoGateway {

}
