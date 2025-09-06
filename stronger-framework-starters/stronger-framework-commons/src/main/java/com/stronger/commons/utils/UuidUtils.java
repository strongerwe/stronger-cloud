package com.stronger.commons.utils;

/**
 * @author stronger
 * @version release-1.0.0
 * @class UuidUtils.class
 * @department Platform R&D
 * @date 2025/9/6
 * @desc do what?
 */
public class UuidUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUuid() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUuid() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUuid() {
        return UUID.fastUUID().toString(true);
    }
}
