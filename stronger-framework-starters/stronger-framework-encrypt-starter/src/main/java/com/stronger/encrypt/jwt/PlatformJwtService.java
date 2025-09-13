package com.stronger.encrypt.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.stronger.commons.base.BaseRequest;
import com.stronger.commons.base.BaseRequestAttributeEnums;
import com.stronger.commons.exception.BizRuntimeException;
import com.stronger.commons.utils.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PlatformJwtService.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc jwt工具类
 */
public class PlatformJwtService {

    public PlatformJwtService(String sign) {
        algorithm = Algorithm.HMAC256(sign);
    }

    /**
     * jwt密钥 【上线后勿动】
     */
    private final Algorithm algorithm;

    /**
     * 密码过期表示，密码过期token，仅能修改密码操作
     */
    private final static String PDT_KEY = "pdt";

    /**
     * 创建jwt
     * 生成token
     *
     * @param tokenType token类型(SsoTokenType.java)
     * @param timeOut   超时时间
     * @param jti       唯一ID
     * @param pdt       密码是否过期
     * @param request   BaseRequest
     * @return {@link String }
     */
    public String newFrameLoginJwtToken(
            final JwtTypeEnums tokenType,
            final BaseRequest request,
            final Integer timeOut,
            final String jti,
            final String pdt) {
        JWTCreator.Builder builder = JWT.create();
        builder.withJWTId(jti);
        builder.withIssuedAt(new Date());
        builder.withSubject(tokenType.getType());
        builder.withIssuer(request.getFrameUserId());
        builder.withAudience(request.getFrameClientId());
        builder.withClaim(PDT_KEY, pdt);
        builder.withClaim(BaseRequestAttributeEnums.FRAME_USER_ID.getJwtKey(), request.getFrameUserId());
        builder.withClaim(BaseRequestAttributeEnums.FRAME_USER_NAME.getJwtKey(), encodeChn(request.getFrameUserName()));
        builder.withClaim(BaseRequestAttributeEnums.FRAME_USER_VERSION.getJwtKey(), request.getFrameUserVersion());
        builder.withClaim(BaseRequestAttributeEnums.FRAME_ORG_CODE.getJwtKey(), request.getFrameOrgCode());
        builder.withClaim(BaseRequestAttributeEnums.FRAME_CLIENT_ID.getJwtKey(), request.getFrameClientId());
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, timeOut);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(algorithm);
    }

    /**
     * 切换机构
     *
     * @param loginJwtToken loginJwtToken
     * @param frameOrgCode  切换机构编码
     * @return {@link String }
     */
    public String changeOrgJwtToken(final String loginJwtToken,
                                    final String frameOrgCode) {
        DecodedJWT decodedJwt = analysisJwt(loginJwtToken);
        if (decodedJwt == null) {
            throw new BizRuntimeException("jwtToken失效");
        }
        /* 校验token类型 */
        if (JwtTypeEnums.APP_TOKEN.getType().equals(decodedJwt.getSubject())) {
            throw new BizRuntimeException("jwtToken类型异常，仅支持登录token");
        }
        BaseRequest request = analysis(decodedJwt);
        JWTCreator.Builder builder = JWT.create();
        builder.withJWTId(decodedJwt.getId());
        builder.withIssuedAt(new Date());
        builder.withSubject(decodedJwt.getSubject());
        builder.withIssuer(request.getFrameUserId());
        builder.withAudience(request.getFrameClientId());
        builder.withClaim(PDT_KEY, decodedJwt.getClaim(PDT_KEY).asString());
        builder.withClaim(BaseRequestAttributeEnums.FRAME_USER_ID.getJwtKey(), request.getFrameUserId());
        builder.withClaim(BaseRequestAttributeEnums.FRAME_USER_NAME.getJwtKey(), encodeChn(request.getFrameUserName()));
        builder.withClaim(BaseRequestAttributeEnums.FRAME_USER_VERSION.getJwtKey(), request.getFrameUserVersion());
        builder.withClaim(BaseRequestAttributeEnums.FRAME_ORG_CODE.getJwtKey(), frameOrgCode);
        builder.withClaim(BaseRequestAttributeEnums.FRAME_CLIENT_ID.getJwtKey(), request.getFrameClientId());
        builder.withExpiresAt(decodedJwt.getExpiresAt());
        return builder.sign(algorithm);
    }

    /**
     * 解析jwt
     *
     * @param loginJwtToken loginJwtToken
     * @return {@link BaseRequest }
     */
    public BaseRequest analysisJwtToBaseDto(final String loginJwtToken) {
        // 非空验证
        if (StringUtils.isEmpty(loginJwtToken)) {
            return null;
        }
        // 解析并验证Token有效期
        DecodedJWT decodedJwt = this.analysisJwt(loginJwtToken);
        if (null == decodedJwt) {
            return null;
        }
        /* 校验token类型 */
        if (JwtTypeEnums.APP_TOKEN.getType().equals(decodedJwt.getSubject())) {
            return null;
        }
        return analysis(decodedJwt);
    }

    /**
     * 解析Request
     *
     * @param decodedJwt decodedJwt
     * @return {@link BaseRequest }
     */
    private BaseRequest analysis(DecodedJWT decodedJwt) {
        String frameUserId = decodedJwt
                .getClaim(BaseRequestAttributeEnums.FRAME_USER_ID.getJwtKey()).asString();
        String frameUserName = decode(decodedJwt
                .getClaim(BaseRequestAttributeEnums.FRAME_USER_NAME.getJwtKey()).asString());
        String frameUserVersion = decodedJwt
                .getClaim(BaseRequestAttributeEnums.FRAME_USER_VERSION.getJwtKey()).asString();
        String frameOrgCode = decodedJwt
                .getClaim(BaseRequestAttributeEnums.FRAME_ORG_CODE.getJwtKey()).asString();
        String frameClientId = decodedJwt
                .getClaim(BaseRequestAttributeEnums.FRAME_CLIENT_ID.getJwtKey()).asString();
        BaseRequest request = new BaseRequest();
        request.setFrameUserId(frameUserId);
        request.setFrameUserName(frameUserName);
        request.setFrameUserVersion(Integer.valueOf(frameUserVersion));
        request.setFrameOrgCode(frameOrgCode);
        request.setFrameClientId(frameClientId);
        return request;
    }

    /**
     * 中文Encode
     *
     * @param opn 中文名称
     * @return {@link String}
     */
    private String encodeChn(String opn) {
        if (StringUtils.isEmpty(opn)) {
            return "";
        }
        return URLEncoder.encode(opn, StandardCharsets.UTF_8);
    }

    /**
     * 中文Decode
     *
     * @param hz hz
     * @return {@link String }
     */
    private String decode(String hz) {
        if (StringUtils.isEmpty(hz)) {
            return "";
        }
        try {
            return URLDecoder.decode(hz, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return hz;
        }
    }

    /**
     * 获取token中payload
     *
     * @param jwt jwt
     * @return {@link DecodedJWT}
     */
    private DecodedJWT analysisJwt(String jwt) {
        if (StringUtils.isEmpty(jwt)) {
            return null;
        }
        try {
            return JWT.require(algorithm).build().verify(jwt);
        } catch (Exception e) {
            return null;
        }
    }
}
