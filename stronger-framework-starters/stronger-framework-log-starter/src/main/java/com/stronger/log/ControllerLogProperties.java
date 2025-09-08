package com.stronger.log;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author stronger
 * @version release-1.0.0
 * @class ControllerLogProperties.class
 * @department Platform R&D
 * @date 2025/9/8
 * @desc do what?
 */
@Data
@ConfigurationProperties("stronger.framework.log")
public class ControllerLogProperties {
    private boolean enabled = true;
    private String level = "INFO";
    private int maxLength = 3096;
}
