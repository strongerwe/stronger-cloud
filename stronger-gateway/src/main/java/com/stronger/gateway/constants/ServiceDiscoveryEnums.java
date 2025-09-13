package com.stronger.gateway.constants;


import com.stronger.commons.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum ServiceDiscoveryEnums.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc 服务发现枚举
 */
@AllArgsConstructor
@Getter
public enum ServiceDiscoveryEnums implements BaseEnum {
    K8S("k8s", "k8s"),
    NACOS("nacos", "nacos"),
    ;

    private final String type;
    private final String value;
}
