package com.stronger.commons.utils;


import com.alibaba.fastjson.JSONObject;

/**
 * @author stronger
 * @version release-1.0.0
 * @class JsonUtils.class
 * @department Platform R&D
 * @date 2025/9/17
 * @desc JSONUtils
 */
public class JsonUtils {

    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof String) {
            return (String) object;
        }
        return JSONObject.toJSONString(object);
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        return JSONObject.parseObject(json, clazz);
    }

    public static <T> T getObject(String json, Class<T> clazz) {
        return parseObject(json, clazz);
    }
}
