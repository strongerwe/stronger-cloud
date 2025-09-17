package com.stronger.platform.infrastructure.user.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stronger.commons.utils.JsonUtils;
import com.stronger.commons.utils.StringUtils;
import com.stronger.platform.client.admin.frame.enums.LoginLockedEnum;
import com.stronger.platform.domain.user.dto.LoginLockedDTO;
import com.stronger.platform.domain.user.entity.SysLoginLocked;
import com.stronger.platform.domain.user.gateway.SysLoginLockedGateway;
import com.stronger.platform.infrastructure.system.RedisGateway;
import com.stronger.platform.infrastructure.user.mapper.SysLoginLockedMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 * 登录账户锁定信息表 服务实现类
 * </p>
 *
 * @author stronger
 * @since 2025-09-17
 */
@Service
public class SysLoginLockedGatewayImpl extends ServiceImpl<SysLoginLockedMapper, SysLoginLocked> implements SysLoginLockedGateway {

    @Resource
    private ExecutorService executorService;

    @Resource
    private RedisGateway redisGateway;

    @Override
    public SysLoginLocked getById(Serializable id) {
        return baseMapper.selectOne(lambdaQueryWrap().eq(SysLoginLocked::getUserId, id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginLockedDTO loginFailed(String uuid) {
        SysLoginLocked loginLocked = getById(uuid);
        if (null == loginLocked) {
            executorService.execute(() -> newOne(uuid));
            return new LoginLockedDTO(uuid);
        }
        // 登录失败次数累加
        Integer errCount = loginLocked.getLoginFailedCount() + 1;
        loginLocked.setLoginFailedCount(errCount);
        loginLocked.setLastFailedTime(LocalDateTime.now());
        LoginLockedDTO locked = setLocked(loginLocked);
        if (locked.getLocked()) {
            loginLocked.setLoginFailedTime(LocalDateTime.now());
        }
        updateById(loginLocked);
        // 判断并触发锁定
        return locked;
    }

    @Override
    public LoginLockedDTO queryLockedStatus(String uuid) {
        // 优先查询账号锁定缓存
        String lockedJson = redisGateway.get(cacheKey(uuid));
        if (StringUtils.isNotEmpty(lockedJson)) {
            return JsonUtils.getObject(lockedJson, LoginLockedDTO.class);
        }
        // 查询数据库配置
        SysLoginLocked loginLocked = getById(uuid);
        if (null == loginLocked) {
            return new LoginLockedDTO(uuid);
        }
        return checkLocked(loginLocked);
    }

    @Override
    public void cleanLocked(String uuid) {

    }

    private void newOne(String uuid) {
        SysLoginLocked locked = new SysLoginLocked();
        locked.setUserId(uuid);
        locked.setLastFailedTime(LocalDateTime.now());
        locked.setLoginFailedTime(LocalDateTime.now());
        locked.setLoginFailedCount(1);
        save(locked);
    }

    /**
     * 设置用户锁定状态
     *
     * @param loginLocked loginLocked
     * @return {@link LoginLockedDTO }
     */
    private LoginLockedDTO setLocked(SysLoginLocked loginLocked) {
        // 当错误等于4次时
        if (loginLocked.getLoginFailedCount().toString()
                .equals(LoginLockedEnum.LOGIN_ERR_3.getErrCount().toString())) {
            LoginLockedDTO loginLockedDTO = new LoginLockedDTO(
                    loginLocked.getUserId(),
                    LoginLockedEnum.LOGIN_ERR_3.getAfterErrMsg().code(),
                    LoginLockedEnum.LOGIN_ERR_3.getAfterErrMsg().message(),
                    Boolean.TRUE);
            redisGateway.set(cacheKey(loginLocked.getUserId()),
                    JsonUtils.toJson(loginLockedDTO),
                    LoginLockedEnum.LOGIN_ERR_3.getTimeOut());
            loginLockedDTO.setErrCode(LoginLockedEnum.LOGIN_ERR_3.getLockedErrMsg().code());
            loginLockedDTO.setErrMsg(LoginLockedEnum.LOGIN_ERR_3.getLockedErrMsg().message());
            return loginLockedDTO;
        }
        // 当错误等于6次时
        if (loginLocked.getLoginFailedCount().toString()
                .equals(LoginLockedEnum.LOGIN_ERR_6.getErrCount().toString())) {
            LoginLockedDTO loginLockedDTO = new LoginLockedDTO(
                    loginLocked.getUserId(),
                    LoginLockedEnum.LOGIN_ERR_6.getAfterErrMsg().code(),
                    LoginLockedEnum.LOGIN_ERR_6.getAfterErrMsg().message(),
                    Boolean.TRUE);
            redisGateway.set(cacheKey(loginLocked.getUserId()),
                    JsonUtils.toJson(loginLockedDTO), LoginLockedEnum.LOGIN_ERR_6.getTimeOut());
            loginLockedDTO.setErrCode(LoginLockedEnum.LOGIN_ERR_6.getLockedErrMsg().code());
            loginLockedDTO.setErrMsg(LoginLockedEnum.LOGIN_ERR_6.getLockedErrMsg().message());
            return loginLockedDTO;
        }
        if (loginLocked.getLoginFailedCount() > LoginLockedEnum.LOGIN_ERR_6.getErrCount()
                && loginLocked.getLoginFailedCount() < LoginLockedEnum.LOGIN_ERR_9.getErrCount()) {
            return new LoginLockedDTO(loginLocked.getUserId(),
                    LoginLockedEnum.errMsg(loginLocked.getLoginFailedCount()).code(),
                    LoginLockedEnum.errMsg(loginLocked.getLoginFailedCount()).message(), Boolean.TRUE);
        }
        // 当错误等于9次时
        if (loginLocked.getLoginFailedCount() >= LoginLockedEnum.LOGIN_ERR_9.getErrCount()) {
            LoginLockedDTO loginLockedDTO = new LoginLockedDTO(
                    loginLocked.getUserId(),
                    LoginLockedEnum.LOGIN_ERR_9.getAfterErrMsg().code(),
                    LoginLockedEnum.LOGIN_ERR_9.getAfterErrMsg().message(),
                    Boolean.TRUE);
            redisGateway.set(cacheKey(loginLocked.getUserId()),
                    JsonUtils.toJson(loginLockedDTO),
                    LoginLockedEnum.LOGIN_ERR_9.getTimeOut());
            loginLockedDTO.setErrCode(LoginLockedEnum.LOGIN_ERR_9.getLockedErrMsg().code());
            loginLockedDTO.setErrMsg(LoginLockedEnum.LOGIN_ERR_9.getLockedErrMsg().message());
            return loginLockedDTO;
        }
        return new LoginLockedDTO(loginLocked.getUserId());
    }
}
