package com.stronger.platform;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author stronger
 * @version release-1.0.0
 * @class StrongerPlatformApplication.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 启动类
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.stronger.platform"})
@MapperScan("com.stronger.platform.infrastructure.*.mapper")
public class StrongerPlatformApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(StrongerPlatformApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        String port = environment.getProperty("server.port");
        String application = environment.getProperty("spring.application.name");
        String mysql = environment.getProperty("spring.datasource.url");
        String redis = environment.getProperty("spring.data.redis.host") + ":"
                + environment.getProperty("spring.data.redis.port") + "/"
                + environment.getProperty("spring.data.redis.database");
        log.info("--------------------------------------------------------------");
        log.info("      [{}]服务启动成功！端口号：{}", application, port);
        log.info("      [Mysql]连接：{}", mysql);
        log.info("      [Redis]连接：{}", redis);
        log.info("--------------------------------------------------------------");
    }
}
