package com.stronger;

import com.baomidou.mybatisplus.generator.config.*;

import java.io.File;
import java.util.Objects;

/**
 * @author stronger
 * @version release-1.0.0
 * @class BaseGeneratorTest.class
 * @department Platform R&D
 * @date 2025/9/7
 * @desc BaseGeneratorTest
 */
public abstract class BaseGeneratorTest {

    /**
     * 策略配置
     */
    protected static StrategyConfig.Builder strategyConfig() {
        return new StrategyConfig.Builder();
    }

    /**
     * 全局配置
     */
    protected static GlobalConfig.Builder globalConfig() {
        String classPath = new File(Objects.requireNonNull(BaseGeneratorTest.class.getResource("/")).getPath()).getPath();
        // 当前 项目 根路径
        String projectPath = classPath.substring(0,
                classPath.lastIndexOf(File.separator + "target" + File.separator));
        String path = projectPath + "/src/main/java";
        return new GlobalConfig.Builder().outputDir(path);
    }

    /**
     * 包配置
     */
    protected static PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder();
    }

    /**
     * 注入配置
     */
    protected static InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
        });
    }
}
