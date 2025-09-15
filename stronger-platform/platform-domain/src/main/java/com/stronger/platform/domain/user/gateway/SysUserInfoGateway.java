package com.stronger.platform.domain.user.gateway;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stronger.platform.domain.user.entity.SysUserInfo;

/**
 * <p>
 * 系统用户信息表 服务类
 * </p>
 *
 * @author stronger
 * @since 2025-09-13
 */
public interface SysUserInfoGateway extends IService<SysUserInfo> {

    /**
     * 根据用户id查询用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    SysUserInfo getByUserId(String userId);

    /**
     * 获取查询条件
     *
     * @return {@link LambdaQueryWrapper }<{@link SysUserInfo }>
     */
    default LambdaQueryWrapper<SysUserInfo> lambdaQueryWrapper() {
        return new QueryWrapper<SysUserInfo>().lambda();
    }
}
