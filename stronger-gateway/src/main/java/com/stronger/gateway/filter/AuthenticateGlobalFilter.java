package com.stronger.gateway.filter;


import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author stronger
 * @version release-1.0.0
 * @class AuthenticateGlobalFilter.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc 授权认证拦截器
 */
@Slf4j
@Component
public class AuthenticateGlobalFilter extends OpenPathGlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long inTime = System.currentTimeMillis();
        ServerHttpRequest request = exchange.getRequest();
        String reqPath = request.getPath().toString();
        log.info("Request In [{}] | {}", reqPath, JSONArray.toJSONString(request.getHeaders()));
        return super.filter(exchange, chain, inTime, request);
    }
}
