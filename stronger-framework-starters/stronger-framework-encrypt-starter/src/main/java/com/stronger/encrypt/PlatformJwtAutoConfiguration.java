package com.stronger.encrypt;


import com.stronger.commons.utils.StringUtils;
import com.stronger.encrypt.jwt.PlatformJwtProperties;
import com.stronger.encrypt.jwt.PlatformJwtService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PlatformJwtServiceAutoConfiguration.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc PlatformJwtAutoConfiguration
 */
@Slf4j
@Aspect
@Order(-201)
@Configuration("PlatformJwtAutoConfiguration")
@EnableConfigurationProperties({PlatformJwtProperties.class})
@ConditionalOnProperty(
        prefix = "stronger.framework.encrypt.jwt",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class PlatformJwtAutoConfiguration {

    @Bean
    public PlatformJwtService platformJwtService(PlatformJwtProperties properties) {
        String sign = properties.getSign();
        if (StringUtils.isBlank(sign)) {
            sign = "Stronger@2025!";
        }
        log.info("PlatformJwtService init success! sign:{}", sign);
        return new PlatformJwtService(sign);
    }
}
