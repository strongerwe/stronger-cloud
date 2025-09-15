package com.stronger.admin.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author stronger
 * @version release-1.0.0
 * @class AdminRequestFilterProperties.class
 * @department Platform R&D
 * @date 2025/9/15
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("stronger.base.request.param")
public class AdminRequestFilterProperties {

    /**
     * 配置启用网关请求参数回填BaseDTO功能（默认：false.关闭）
     */
    private boolean enabled = false;
}
