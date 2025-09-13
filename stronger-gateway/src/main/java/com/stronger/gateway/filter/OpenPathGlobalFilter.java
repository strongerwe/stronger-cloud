package com.stronger.gateway.filter;


import com.stronger.gateway.app.route.service.OpenPathService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author stronger
 * @version release-1.0.0
 * @class OpenPathGlobalFilter.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc 开放API拦截器
 */
@Slf4j
public abstract class OpenPathGlobalFilter extends LoginTokenGlobalFilter {

    /**
     * 平台框架规范 `.open` 结尾接口均为开放接口
     */
    protected final static String OPEN_API_END_WITH = ".open";

    @Resource
    private OpenPathService openPathService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain, long inTime, ServerHttpRequest request) {
        // .open结尾开放接口直接返回 && 配置开放API Path验证
        if (openApiCheck(request.getPath().toString())) {
            long time = System.currentTimeMillis() - inTime;
            log.info("【{}ms】Request In [{}] IS OPEN API PATH! Passed!", time, request.getPath());
            return chain.filter(exchange);
        }
        return super.filter(exchange, chain, inTime, request);
    }

    @Override
    public int getOrder() {
        return 100;
    }

    /**
     * 验证开放接口
     *
     * @param reqPath reqPath
     * @return boolean
     */
    public boolean openApiCheck(String reqPath) {
        // .open结尾开放接口直接返回
        if (reqPath.endsWith(OPEN_API_END_WITH)) {
            return true;
        }
        // 开放API Path验证
        return openPathService.validateOpenPath(reqPath);
    }
}
