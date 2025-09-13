package com.stronger.encrypt.jwt;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PlatformJwtProperties.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc do what?
 */
@Data
@ConfigurationProperties("stronger.framework.encrypt.jwt")
public class PlatformJwtProperties {

    private boolean enabled = true;

    private String sign = "Stronger@2025!";
}
