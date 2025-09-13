package com.stronger.gateway;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author stronger
 * @version release-1.0.0
 * @class StrongerGatewayApplication.class
 * @department Platform R&D
 * @date 2025/9/7
 * @desc 启动类
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.stronger.gateway.domain.*.mapper")
public class StrongerGatewayApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(StrongerGatewayApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        String port = environment.getProperty("server.port");
        String application = environment.getProperty("spring.application.name");
        String mysqlUrl = environment.getProperty("spring.datasource.url");
        log.info("------------------------------------------------------------");
        log.info("[{}]服务启动成功|端口号：{}", application, port);
        log.info("[MySql]连接：{}", mysqlUrl);
        log.info("------------------------------------------------------------");
    }

}
