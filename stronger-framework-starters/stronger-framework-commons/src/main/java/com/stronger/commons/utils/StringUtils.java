package com.stronger.commons.utils;

/**
 * @author stronger
 * @version release-1.0.0
 * @class StringTools.class
 * @department Platform R&D
 * @date 2025/5/22
 * @desc 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public StringUtils() {
        super();
    }

    /**
     * 验证手机号
     *
     * @param mobile 手机号
     * @return boolean
     */
    public static boolean isMobile(final String mobile) {
        if (isEmpty(mobile)) {
            return false;
        }
        if (mobile.length() != 11) {
            return false;
        }
        if (!mobile.startsWith("13")
                && !mobile.startsWith("14")
                && !mobile.startsWith("15")
                && !mobile.startsWith("16")
                && !mobile.startsWith("17")
                && !mobile.startsWith("18")
                && !mobile.startsWith("19")) {
            return false;
        }
        try {
            Long.parseLong(mobile);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 是否非空
     *
     * @param o o
     * @return boolean
     */
    public static boolean isNotEmpty(final StringBuilder o) {
        if (null == o) {
            return false;
        }
        return !o.toString().isEmpty();
    }

    public static boolean isNotNullOrNotEmpty(StringBuilder o) {
        if (null == o) {
            return false;
        }
        return org.apache.commons.lang3.StringUtils.isNotEmpty(o.toString());
    }

    /**
     * 截取字符串
     *
     * @param str       str
     * @param maxLength maxLength
     * @return {@link String }
     */
    public static String getSub(String str, Integer maxLength) {
        if (isEmpty(str)) {
            return str;
        }
        if (str.length() < maxLength) {
            return str;
        }
        return str.substring(0, maxLength);
    }
}
