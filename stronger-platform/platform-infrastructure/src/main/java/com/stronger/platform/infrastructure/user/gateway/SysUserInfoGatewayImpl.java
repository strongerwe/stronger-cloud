package com.stronger.platform.infrastructure.user.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stronger.commons.utils.StringUtils;
import com.stronger.platform.domain.user.entity.SysUserInfo;
import com.stronger.platform.domain.user.gateway.SysUserInfoGateway;
import com.stronger.platform.infrastructure.user.mapper.SysUserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户信息表 服务实现类
 * </p>
 *
 * @author stronger
 * @since 2025-09-13
 */
@Service
public class SysUserInfoGatewayImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements SysUserInfoGateway {

    @Override
    public SysUserInfo getByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        return baseMapper.selectOne(
                lambdaQueryWrapper()
                        .eq(SysUserInfo::getUserId, userId)
                        .eq(SysUserInfo::getIsDeleted, false));
    }
}
