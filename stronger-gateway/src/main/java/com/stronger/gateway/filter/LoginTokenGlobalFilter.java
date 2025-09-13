package com.stronger.gateway.filter;


import com.stronger.commons.base.BaseRequest;
import com.stronger.commons.utils.StringUtils;
import com.stronger.gateway.constants.GatewayConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PlatformTokenGlobalFilter.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc 登录Token拦截验证
 */
@Slf4j
public abstract class LoginTokenGlobalFilter extends AbstractGlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain, long inTime, ServerHttpRequest request) {
        String platformToken = getToken(exchange);
        String reqPath = request.getPath().toString();
        if (StringUtils.isEmpty(platformToken)) {
            // 未获取到平台token等信息 按非平台token情况处理
            long time = System.currentTimeMillis() - inTime;
            log.debug("【{}ms】Request Into [{}] | token获取为空！| {}", time, reqPath, platformToken);
            return unauthorized(exchange, GatewayConstants.UNAUTHORIZED);
        }
        // 解析TOKEN
        BaseRequest baseRequest = platformJwtService.analysisJwtToBaseDto(platformToken);
        if (null == baseRequest) {
            long time = System.currentTimeMillis() - inTime;
            log.debug("【{}ms】Request Into [{}] | token验证jwt失败！| {}", time, reqPath, platformToken);
            return unauthorized(exchange, GatewayConstants.UNAUTHORIZED_TOKEN_EXPIRED);
        }
        // 验证通过
        return filterLoginPass(exchange, chain, baseRequest, reqPath, inTime);
    }

    @Override
    public int getOrder() {
        return 200;
    }
}
