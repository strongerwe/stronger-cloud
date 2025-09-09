package com.stronger.redis.redisson;


import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author stronger
 * @version release-1.0.0
 * @class RedissonLock.class
 * @department Platform R&D
 * @date 2025/9/9
 * @desc 分布式锁注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedissonLock {

    /**
     * @return {@link String }
     */
    String keyTemplate();

    /**
     * 后缀表达式，为空时候表示不需要后缀表达式，采用 SPEL
     * eg:
     * 1.不支持加固定后缀！！！ 目前spEl数据源是来自方法入参，要是需要固定后缀，放置在前缀就好了;
     * 2.访问参数 比如 methodName(String userId, String pageId)  ->"#userId+'_'+#pageId";
     * 3.访问对象内属性 比如 methodName(UserBO userBO)  ->"#userBO.name";
     * 4.访问集合数据 比如 methodName(List<String> list) -> "#list.toString()";
     *
     * @return s
     */
    String suffix();


    /**
     * lockTime
     *
     * @return long
     */
    long lockTime() default 3;

    /**
     * 单位
     *
     * @return {@link TimeUnit }
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
