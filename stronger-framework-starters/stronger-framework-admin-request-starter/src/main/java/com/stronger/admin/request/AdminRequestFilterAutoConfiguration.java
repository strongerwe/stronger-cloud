package com.stronger.admin.request;


import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author stronger
 * @version release-1.0.0
 * @class AdminRequestFilterAutoConfiguration.class
 * @department Platform R&D
 * @date 2025/9/15
 * @desc AdminRequestFilterAutoConfiguration
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration(value = "AdminRequestFilterAutoConfiguration")
@EnableConfigurationProperties(AdminRequestFilterProperties.class)
@ConditionalOnProperty(
        prefix = "stronger.base.request",
        name = "param.enabled",
        matchIfMissing = true,
        havingValue = "true")
public class AdminRequestFilterAutoConfiguration {

    /**
     * 请求参数处理的实现Bean
     *
     * @return {@link FilterRegistrationBean }<{@link AdminRequestParamsFilter }>
     */
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<AdminRequestParamsFilter> parmsFilterRegistration() {
        log.info(">>>>>>>> 配置启用网关请求参数回填BaseRequest功能!");
        FilterRegistrationBean<AdminRequestParamsFilter> registration =
                new FilterRegistrationBean<AdminRequestParamsFilter>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new AdminRequestParamsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("paramsFilter");
        registration.setOrder(Integer.MAX_VALUE - 1);
        return registration;
    }
}
