package com.stronger.gateway.app.route.service;


import com.alibaba.fastjson.JSONArray;
import com.stronger.commons.utils.StringUtils;
import com.stronger.gateway.config.redis.RedisGateway;
import com.stronger.gateway.constants.GatewayConstants;
import com.stronger.gateway.domain.route.entity.GatewayOpenPath;
import com.stronger.gateway.domain.route.gateway.IGatewayOpenPathGateway;
import com.stronger.redis.IRedisGateway;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stronger
 * @version release-1.0.0
 * @class OpenPathService.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc 开发API路径Service
 */
@Slf4j
@Service
public class OpenPathService {

    @Resource
    private RedisGateway redisGateway;

    @Resource
    private IGatewayOpenPathGateway gatewayOpenPathGateway;

    /**
     * 验证API路径
     *
     * @param path API路径
     * @return boolean
     */
    public boolean validateOpenPath(String path) {
        String openPathsJsonArray = redisGateway.get(GatewayConstants.CACHE_OPEN_PATH_KEY);
        List<String> collect = new ArrayList<>();
        if (StringUtils.isNotBlank(openPathsJsonArray)) {
            collect = JSONArray.parseArray(openPathsJsonArray, String.class);
        }
        if (StringUtils.isBlank(openPathsJsonArray)) {
            List<GatewayOpenPath> openPaths = gatewayOpenPathGateway.selectEnabledOpenPaths();
            collect = openPaths.stream().map(GatewayOpenPath::getPathUrl).distinct().collect(Collectors.toList());
            redisGateway.set(GatewayConstants.CACHE_OPEN_PATH_KEY, JSONArray.toJSONString(collect), IRedisGateway.HOUR_24);
        }
        if (collect.isEmpty()) {
            return false;
        }
        return collect.contains(path);
    }

    /**
     * 清除动态路由缓存
     */
    public void cleanOpenApiCache() {
        redisGateway.delete(GatewayConstants.CACHE_OPEN_PATH_KEY);
    }
}
