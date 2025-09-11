package com.stronger.redis;


import java.util.Map;

/**
 * @author stronger
 * @version release-1.0.0
 * @interface IRedisGateway.class
 * @department Platform R&D
 * @date 2025/9/11
 * @desc redisGateway 接口
 */
public interface IRedisGateway {

    /**
     * redis 常用 失效时间
     */
    int SS_30 = 30;
    int MIN_1 = 60;
    int MIN_5 = 300;
    int MIN_10 = 600;
    int MIN_30 = 1800;
    int HOUR_1 = 3600;
    int HOUR_2 = 7200;
    int HOUR_24 = 86400;
    int MONTH_1 = 2678400;


    /**
     * 获取缓存结果
     *
     * @param key key
     * @return {@link String }
     */
    String get(String key);

    /**
     * 设置缓存
     *
     * @param key     key
     * @param value   value
     * @param timeout timeout
     */
    void set(String key, String value, long timeout);

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    boolean hasKey(String key);

    /**
     * 删除
     *
     * @param key 关键
     * @return boolean
     */
    boolean delete(String key);

    /**
     * 删除
     *
     * @param key 关键
     */
    void delete(String... key);

    /**
     * 设置失效时间
     *
     * @param key  key
     * @param time time
     * @return boolean
     */
    boolean expire(String key, long time);

    /**
     * 获取缓存失效时间
     *
     * @param key key
     * @return long
     */
    long getExpire(String key);

    /**
     * 哈希得到
     *
     * @param key  关键
     * @param item 项
     * @return {@link String}
     */
    String hashGet(String key, String item);

    /**
     * 哈希得到
     *
     * @param key 关键
     * @return {@link Map}<{@link Object}, {@link Object}>
     */
    Map<Object, Object> hashGet(String key);

    /**
     * 散列集
     *
     * @param key 关键
     * @param map 地图
     * @return boolean
     */
    boolean hashSet(String key, Map<String, Object> map);

    /**
     * 散列集
     *
     * @param key  关键
     * @param map  地图
     * @param time 时间
     * @return boolean
     */
    boolean hashSet(String key, Map<String, Object> map, long time);

    /**
     * 添加or更新hash的值
     *
     * @param key   key
     * @param field field
     * @param value value
     */
    void hashSet(String key, String field, String value);
}
