package com.stronger.redis;


import com.stronger.commons.exception.BizRuntimeException;
import com.stronger.commons.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author stronger
 * @version release-1.0.0
 * @class AbstractRedisGateway.class
 * @department Platform R&D
 * @date 2025/9/11
 * @desc 静态抽象Redis网关，提供基础的redis操作
 */
@Slf4j
public abstract class AbstractRedisGateway implements IRedisGateway {

    protected final StringRedisTemplate stringRedisTemplate;
    protected final RedisTemplate<String, Object> redisTemplate;

    public AbstractRedisGateway(StringRedisTemplate stringRedisTemplate,
                                RedisTemplate<String, Object> redisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String get(String key) {
        validateKeyNotNull(key, "[RedisGateway] get key error|key is blank");
        return stringRedisTemplate.boundValueOps(key).get();
    }

    @Override
    public void set(String key, String value, long timeout) {
        validateKeyNotNull(key, "[RedisGateway] set key error|key is blank");
        try {
            stringRedisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            log.error("[RedisGateway] set key error|{}", e.getMessage());
            return;
        }
        if (timeout > 0) {
            stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    @Override
    public boolean hasKey(String key) {
        validateKeyNotNull(key, "[RedisGateway] hasKey error|key is blank");
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("[RedisGateway] hasKey error|{}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        validateKeyNotNull(key, "[RedisGateway] delete key error|key is blank");
        return stringRedisTemplate.delete(key);
    }

    @Override
    public void delete(String... key) {
        if (key == null || key.length == 0) {
            log.error("[RedisGateway] delete key error|key is blank");
            throw new BizRuntimeException("[RedisGateway] delete key error|key is blank");
        }
        if (key.length == 1) {
            redisTemplate.delete(key[0]);
        } else {
            redisTemplate.delete(Arrays.asList(key));
        }
    }

    @Override
    public boolean expire(String key, long time) {
        validateKeyNotNull(key, "[RedisGateway] expire key error|key is blank");
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("[RedisGateway] expire key error|{}", e.getMessage());
            return false;
        }
    }

    @Override
    public long getExpire(String key) {
        validateKeyNotNull(key, "[RedisGateway] getExpire key error|key is blank");
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public String hashGet(String key, String item) {
        validateKeyNotNull(key, "[RedisGateway] hashGet key error|key is blank");
        Object result = redisTemplate.opsForHash().get(key, item);
        return result != null ? result.toString() : "";
    }

    @Override
    public Map<Object, Object> hashGet(String key) {
        validateKeyNotNull(key, "[RedisGateway] hashGet key error|key is blank");
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public boolean hashSet(String key, Map<String, Object> map) {
        validateKeyNotNull(key, "[RedisGateway] hashSet key error|key is blank");
        if (null == map) {
            throw new BizRuntimeException("[RedisGateway] hashSet key error|map is null");
        }
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("[RedisGateway] hashSet key error|{}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean hashSet(String key, Map<String, Object> map, long time) {
        validateKeyNotNull(key, "[RedisGateway] hashSet key error|key is blank");
        if (null == map) {
            throw new BizRuntimeException("[RedisGateway] hashSet key error|map is null");
        }
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("[RedisGateway] hashSet key error|{}", e.getMessage());
            return false;
        }
    }

    @Override
    public void hashSet(String key, String field, String value) {
        validateKeyNotNull(key, "[RedisGateway] hashSet key error|key is blank");
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 校验key是否为空
     *
     * @param key    key
     * @param errMsg errMsg
     */
    private void validateKeyNotNull(String key, String errMsg) {
        if (StringUtils.isBlank(key)) {
            log.error(errMsg);
            throw new BizRuntimeException(errMsg);
        }
    }
}
