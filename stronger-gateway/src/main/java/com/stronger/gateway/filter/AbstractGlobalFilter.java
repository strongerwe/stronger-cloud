package com.stronger.gateway.filter;


import com.alibaba.fastjson.JSONObject;
import com.stronger.commons.base.BaseRequest;
import com.stronger.commons.base.BaseRequestAttributeEnums;
import com.stronger.commons.utils.StringUtils;
import com.stronger.encrypt.jwt.PlatformJwtService;
import com.stronger.gateway.constants.GatewayConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author stronger
 * @version release-1.0.0
 * @class AbstractGlobalFilter.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc do what?
 */
@Slf4j
public abstract class AbstractGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    protected PlatformJwtService platformJwtService;
    /**
     * token请求头key
     */
    protected final String TOKEN_HEADER_KEY = "Authorization";

    /**
     * token请求头key前缀
     */
    protected final String TOKEN_HEADER_PREFIX = "Bearer ";

    /**
     * token请求头key前缀长度
     */
    protected final int TOKEN_HEADER_PREFIX_LENGTH = TOKEN_HEADER_PREFIX.length();

    public abstract Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain, long inTime, ServerHttpRequest request);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * pass
     *
     * @param exchange    exchange
     * @param chain       chain
     * @param baseRequest baseRequest
     * @param path        path
     * @param begin       begin
     * @return {@link Mono }<{@link Void }>
     */
    protected Mono<Void> filterLoginPass(ServerWebExchange exchange,
                                         GatewayFilterChain chain,
                                         BaseRequest baseRequest,
                                         final String path,
                                         final long begin) {
        ServerHttpRequest newRequest =
                exchange.getRequest().mutate()
                        .header(BaseRequestAttributeEnums.FRAME_CLIENT_ID.getHeaderName(),
                                baseRequest.getFrameClientId())
                        .header(BaseRequestAttributeEnums.FRAME_USER_NAME.getHeaderName(),
                                URLEncoder.encode(baseRequest.getFrameUserName(), StandardCharsets.UTF_8))
                        .header(BaseRequestAttributeEnums.FRAME_USER_ID.getHeaderName(),
                                baseRequest.getFrameUserId())
                        .header(BaseRequestAttributeEnums.FRAME_USER_VERSION.getHeaderName(),
                                String.valueOf(baseRequest.getFrameUserVersion()))
                        .header(BaseRequestAttributeEnums.FRAME_ORG_CODE.getHeaderName(),
                                baseRequest.getFrameOrgCode())
                        .build();
        long time = System.currentTimeMillis() - begin;
        log.info("【{}ms】System App Request In [{}] Passed | {}", time, path, JSONObject.toJSONString(baseRequest));
        return chain.filter(exchange.mutate().request(newRequest).build());
    }

    /**
     * 401错误响应
     *
     * @param exchange   exchange
     * @param errMessage errMessage
     * @return {@link Mono }<{@link Void }>
     */
    protected Mono<Void> unauthorized(ServerWebExchange exchange, String errMessage) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().set("Content-Type", GatewayConstants.RETURN_CONTENT_TYPE);
        DataBuffer wrap = response.bufferFactory().wrap(errMessage.getBytes());
        return response.writeWith(Mono.just(wrap));
    }

    /**
     * 获取token
     *
     * @param exchange 服务器web交换
     * @return token
     */
    protected String getToken(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(TOKEN_HEADER_KEY);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        if (token.startsWith(TOKEN_HEADER_PREFIX)) {
            token = token.substring(TOKEN_HEADER_PREFIX_LENGTH);
        }
        return token;
    }
}
