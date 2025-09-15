package com.stronger.platform.domain.user.gateway;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stronger.platform.domain.user.entity.SysLoginAccount;

/**
 * <p>
 * 系统登录账户表 服务类
 * </p>
 *
 * @author stronger
 * @since 2025-09-13
 */
public interface SysLoginAccountGateway extends IService<SysLoginAccount> {

    /**
     * 根据账户名称查询登录账户
     *
     * @param accountName accountName
     * @return {@link SysLoginAccount }
     */
    SysLoginAccount findUseLogin(String accountName);

    /**
     * 获取查询条件
     *
     * @return {@link LambdaQueryWrapper }<{@link SysLoginAccount }>
     */
    default LambdaQueryWrapper<SysLoginAccount> lambdaQueryWrapper() {
        return new QueryWrapper<SysLoginAccount>().lambda();
    }
}
