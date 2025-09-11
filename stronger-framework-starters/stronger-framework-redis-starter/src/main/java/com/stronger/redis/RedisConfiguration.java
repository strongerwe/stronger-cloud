package com.stronger.redis;


import com.stronger.redis.serializer.FastJson2JsonRedisSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author stronger
 * @version release-1.0.0
 * @class RedisConfiguration.class
 * @department Platform R&D
 * @date 2025/9/11
 * @desc Redis配置
 */
@Configuration
@EnableCaching
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisConfiguration {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        FastJson2JsonRedisSerializer<?> serializer = new FastJson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}
