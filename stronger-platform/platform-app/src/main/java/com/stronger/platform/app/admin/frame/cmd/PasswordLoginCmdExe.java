package com.stronger.platform.app.admin.frame.cmd;


import com.alibaba.fastjson.JSONObject;
import com.stronger.commons.AssertCheck;
import com.stronger.commons.base.BaseRequest;
import com.stronger.commons.base.BaseResultCode;
import com.stronger.commons.exception.BizRuntimeException;
import com.stronger.commons.framework.AbstractCmdExe;
import com.stronger.commons.utils.MD5Utils;
import com.stronger.commons.utils.UuidUtils;
import com.stronger.encrypt.jwt.JwtTypeEnums;
import com.stronger.encrypt.jwt.PlatformJwtService;
import com.stronger.encrypt.pwd.EncryptType;
import com.stronger.encrypt.pwd.PwdEncrypt;
import com.stronger.platform.client.admin.frame.request.LoginRequest;
import com.stronger.platform.client.admin.frame.response.LoginResponse;
import com.stronger.platform.client.constants.PlatformAdminResultCode;
import com.stronger.platform.domain.user.dto.LoginLockedDTO;
import com.stronger.platform.domain.user.entity.SysLoginAccount;
import com.stronger.platform.domain.user.entity.SysUserInfo;
import com.stronger.platform.domain.user.gateway.SysLoginAccountGateway;
import com.stronger.platform.domain.user.gateway.SysLoginLockedGateway;
import com.stronger.platform.domain.user.gateway.SysUserInfoGateway;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PasswordLoginCmdExe.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 账号密码登录
 */
@Slf4j
@Component(value = "PasswordLoginCmdExe")
public class PasswordLoginCmdExe extends AbstractCmdExe<LoginRequest, LoginResponse> {

    @Resource
    private SysLoginAccountGateway sysLoginAccountGateway;

    @Resource
    private SysLoginLockedGateway sysLoginLockedGateway;

    @Resource
    private SysUserInfoGateway sysUserInfoGateway;

    @Resource
    private PlatformJwtService platformJwtService;

    /**
     * 执行账号密码登录
     *
     * @param request request
     * @return {@link LoginResponse }
     */
    @Override
    protected LoginResponse cmdExecute(LoginRequest request) {
        // 1. 根据用户名查询账号信息
        SysLoginAccount account = sysLoginAccountGateway.findUseLogin(request.getModel().getUsername());
        AssertCheck.notNull(account, PlatformAdminResultCode.USER_NOT_EXIST);
        AssertCheck.isTrue(!account.getIsEnable(), PlatformAdminResultCode.USER_ACC_IS_DISABLE);
        // 2.验证账号锁定状态
        LoginLockedDTO loginLockedDTO = sysLoginLockedGateway.queryLockedStatus(account.getUserId());
        if (loginLockedDTO.getLocked()) {
            throw new BizRuntimeException(loginLockedDTO.getErrCode(), loginLockedDTO.getErrMsg());
        }
        // 登录密码需要MD5加密  TODO 密码登录为了安全还需要结合前端进行加密操作
        String passwordCheck = MD5Utils.encrypt32(request.getModel().getPassword());
        // 3. 验证登录密码（单向加密验证）
        if (!PwdEncrypt.matches(passwordCheck, account.getAccountPassword(), EncryptType.USER_PASSWORD)) {
            // 记录密码错误次数，如若触发锁定返回对应错误提示
            loginLockedDTO = sysLoginLockedGateway.loginFailed(account.getUserId());
            if (loginLockedDTO.getLocked()) {
                throw new BizRuntimeException(loginLockedDTO.getErrCode(), loginLockedDTO.getErrMsg());
            }
            throw new BizRuntimeException(PlatformAdminResultCode.USER_ACCOUNT_PASSWORD);
        }
        // 4. 登录成功 清除错误次数
        sysLoginLockedGateway.cleanLocked(account.getUserId());
        // 5. 验证通过，查询用户信息
        SysUserInfo userInfo = sysUserInfoGateway.getByUserId(account.getUserId());
        AssertCheck.notNull(userInfo, PlatformAdminResultCode.USER_NOT_EXIST);
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setFrameUserId(account.getUserId());
        baseRequest.setFrameUserName(userInfo.getRealName());
        baseRequest.setFrameUserVersion(userInfo.getUserVersion());
        // TODO 对接客户端ID&机构Code
        baseRequest.setFrameClientId("client-id");
        baseRequest.setFrameOrgCode("1001");
        String jti = UuidUtils.fastSimpleUuid();
        // TODO 验证是否需要重置密码：默认密码|密码过期
        String pdt = String.valueOf(account.getIsDefaultPassword());
        Integer timeOut = 86400;
        // 颁发token
        String appToken = platformJwtService.newFrameLoginJwtToken(JwtTypeEnums.APP_TOKEN, baseRequest, timeOut, jti, pdt);
        return new LoginResponse(appToken, timeOut.longValue(), "PasswordLoginCmdExe");
    }

    @Override
    protected void validate(LoginRequest request) {
        // 验证入参参数
        log.debug("PasswordLoginCmdExe.validate|校验账号密码登录入参|{}", JSONObject.toJSONString(request));
        AssertCheck.notEmpty(request.getModel().getUsername(), BaseResultCode.PARAM_IS_EMPTY.code(),
                MessageFormat.format(BaseResultCode.PARAM_IS_EMPTY.message(), "登录用户名"));
        AssertCheck.notEmpty(request.getModel().getPassword(), BaseResultCode.PARAM_IS_EMPTY.code(),
                MessageFormat.format(BaseResultCode.PARAM_IS_EMPTY.message(), "登录密码"));
    }
}
