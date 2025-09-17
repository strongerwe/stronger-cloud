package com.stronger.platform.infrastructure.system;


import com.stronger.redis.AbstractRedisGateway;
import com.stronger.redis.RedisConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author stronger
 * @version release-1.0.0
 * @class RedisGateway.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc redis服务
 */
@Component
@AutoConfigureAfter(RedisConfiguration.class)
public class RedisGateway extends AbstractRedisGateway {

    public RedisGateway(StringRedisTemplate stringRedisTemplate,
                        RedisTemplate<String, Object> redisTemplate) {
        super(stringRedisTemplate, redisTemplate);
    }
}
