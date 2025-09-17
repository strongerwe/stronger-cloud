package com.stronger.platform.domain.user.gateway;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stronger.platform.client.admin.frame.enums.LoginLockedEnum;
import com.stronger.platform.client.constants.PlatformConstants;
import com.stronger.platform.domain.user.dto.LoginLockedDTO;
import com.stronger.platform.domain.user.entity.SysLoginLocked;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * <p>
 * 登录账户锁定信息表 服务类
 * </p>
 *
 * @author stronger
 * @since 2025-09-17
 */
public interface SysLoginLockedGateway extends IService<SysLoginLocked> {

    /**
     * 登录失败
     *
     * @param uuid uuid
     * @return LoginLockedDTO
     */
    LoginLockedDTO loginFailed(String uuid);

    /**
     * 查询用户锁定状态
     *
     * @param uuid uuid
     * @return LoginLockedDTO
     */
    LoginLockedDTO queryLockedStatus(String uuid);

    /**
     * 清除失败记录
     *
     * @param uuid uuid
     */
    void cleanLocked(String uuid);

    /**
     * checkLocked
     *
     * @param loginLocked PfLoginLocked
     * @return LoginLockedDTO.class
     */
    default LoginLockedDTO checkLocked(SysLoginLocked loginLocked) {
        if (loginLocked.getLoginFailedCount() < LoginLockedEnum.LOGIN_ERR_3.getErrCount()) {
            return new LoginLockedDTO(loginLocked.getUserId());
        }
        LocalDateTime now = LocalDateTime.now();
        long timeOut = ChronoUnit.SECONDS.between(loginLocked.getLoginFailedTime(), now);
        // 超过9次的timeOut 重新计算
        if (timeOut > LoginLockedEnum.LOGIN_ERR_9.getTimeOut()) {
            // 超过过期时间, 可直接登陆
            cleanLocked(loginLocked.getUserId());
            return new LoginLockedDTO(loginLocked.getUserId());
        }
        // 超过6次 并且
        if (loginLocked.getLoginFailedCount() > LoginLockedEnum.LOGIN_ERR_6.getErrCount()) {
            return new LoginLockedDTO(loginLocked.getUserId());
        }
        // 未超过9次的timeOut，超过6次的timeOut 并且错误次数小于等于9次 可继续登录
        if (timeOut > LoginLockedEnum.LOGIN_ERR_6.getTimeOut()
                && loginLocked.getLoginFailedCount() < LoginLockedEnum.LOGIN_ERR_9.getErrCount()) {
            return new LoginLockedDTO(loginLocked.getUserId());
        }
        if (loginLocked.getLoginFailedCount() > LoginLockedEnum.LOGIN_ERR_3.getErrCount()) {
            return new LoginLockedDTO(loginLocked.getUserId());
        }
        // 为超过6次的timeOut, 超过3次的timeOut 并且错误次数小于等于6次 可继续登录
        if (timeOut > LoginLockedEnum.LOGIN_ERR_3.getTimeOut()
                && loginLocked.getLoginFailedCount() < LoginLockedEnum.LOGIN_ERR_6.getErrCount()) {
            return new LoginLockedDTO(loginLocked.getUserId());
        }
        return new LoginLockedDTO(
                loginLocked.getUserId(),
                LoginLockedEnum.errMsg(loginLocked.getLoginFailedCount()).code(),
                LoginLockedEnum.errMsg(loginLocked.getLoginFailedCount()).message(), Boolean.TRUE);
    }

    /**
     * 获取LambdaQueryWrapper
     *
     * @return LambdaQueryWrapper
     */
    default LambdaQueryWrapper<SysLoginLocked> lambdaQueryWrap() {
        return new QueryWrapper<SysLoginLocked>().lambda();
    }

    /**
     * redisKey
     *
     * @param id id
     * @return {@link String}
     */
    default String cacheKey(String id) {
        return PlatformConstants.LOGIN_LOCKED_REDIS_KEY + id;
    }
}
