package com.stronger.gateway.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.stronger.commons.RestResult;
import com.stronger.commons.exception.BizRuntimeException;
import com.stronger.gateway.config.NacosRefreshScopeConfig;
import com.stronger.gateway.entity.SsoUser;
import com.stronger.gateway.gateway.SsoUserGateway;
import com.stronger.log.BusinessType;
import com.stronger.log.annotation.ControllerLog;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author stronger
 * @version release-1.0.0
 * @class DemoController.class
 * @department Platform R&D
 * @date 2025/9/7
 * @desc do what?
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private SsoUserGateway ssoUserGateway;

    @Resource
    private NacosRefreshScopeConfig nacosRefreshScopeConfig;


    @GetMapping("/selectAll")
    @ControllerLog(title = "查询所有用户", businessType = BusinessType.QUERY, isSaveLog = true)
    public List<SsoUser> selectAll(@RequestParam("abcd") String abcd) {
        if ("1".equals(abcd)) {
            throw new BizRuntimeException("1111");
        }

        if ("2".equals(abcd)) {
            throw new RuntimeException("2222");
        }
        return ssoUserGateway.list();
    }

    @RequestMapping("/hello")
    @ControllerLog(title = "查询所有用户", businessType = BusinessType.QUERY, isSaveLog = true)
    public RestResult<String> hello() {
        return RestResult.success(nacosRefreshScopeConfig.getDashscopeKey());
    }
}
