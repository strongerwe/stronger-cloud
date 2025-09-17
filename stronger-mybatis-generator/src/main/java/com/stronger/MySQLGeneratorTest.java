package com.stronger;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

import java.util.List;

/**
 * @author stronger
 * @version release-1.0.0
 * @class MySQLGeneratorTest.class
 * @department Platform R&D
 * @date 2025/9/7
 * @desc MySQL代码生成测试类
 */
public class MySQLGeneratorTest extends BaseGeneratorTest {

    private static final String MYSQL_HOST = "127.0.0.1";
    private static final String MYSQL_PORT = "3306";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "Cola@@2024.";
    private static final String MYSQL_DATABASE = "platform";
    private static final String AUTHOR = "stronger";
    private static final String PACKAGE_NAME = "com.stronger.platform";

    public static void main(String[] args) {
        String domain = "domain.user";
        String infrastructure = "infrastructure.user";
        List<String> tableList = List.of("sys_login_locked");
        generate(tableList, domain, infrastructure);
        System.out.println("生成完成!");
    }

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG =
            new DataSourceConfig
                    .Builder("jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DATABASE
                    + "?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8",
                    MYSQL_USERNAME, MYSQL_PASSWORD)
                    .schema(MYSQL_DATABASE)
                    .build();

    public static void generate(List<String> tables, String domain, String infrastructure) {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(
                strategyConfig()
                        .addInclude(tables)
                        .entityBuilder()
                        .enableLombok()
                        .serviceBuilder()
                        .convertServiceFileName((tableName) -> tableName + "Gateway")
                        .convertServiceImplFileName((tableName) -> tableName + "GatewayImpl")
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .build());
        generator.global(
                globalConfig()
                        .author(AUTHOR)
                        .build());
        generator.packageInfo(
                packageConfig()
                        .parent(PACKAGE_NAME)
                        .service(domain + ".gateway")
                        .serviceImpl(infrastructure + ".gateway")
                        .mapper(infrastructure + ".mapper")
                        .entity(domain + ".entity")
                        .xml(infrastructure + ".mapper")
                        .build());
        generator.execute();
    }
}
