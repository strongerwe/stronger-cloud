package com.stronger.redis.redisson;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author stronger
 * @version release-1.0.0
 * @class RedissonLockProperties.class
 * @department Platform R&D
 * @date 2025/9/9
 * @desc do what?
 */
@Data
@ConfigurationProperties("stronger.framework.redisson")
public class RedissonLockProperties {
    private boolean enabled = true;
}
