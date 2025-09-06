package com.stronger.commons.utils;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author stronger
 * @version release-1.0.0
 * @class MD5Tools.class
 * @department Platform R&D
 * @date 2025/5/22
 * @desc Md5生成工具
 */
public class MD5Utils {

    /**
     * MD5加密32位小写
     *
     * @param context context
     * @return str
     */
    public static String encrypt32(String context) {
        try {
            // 获取MD5摘要对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入字符串转换为字节，使用UTF-8编码
            byte[] messageDigest = md.digest(context.getBytes(StandardCharsets.UTF_8));

            // 使用BigInteger将字节数组转换为16进制字符串
            BigInteger no = new BigInteger(1, messageDigest);

            // 将整数转为16进制字符串
            StringBuilder hashText = new StringBuilder(no.toString(16));

            // 如果不足32位，前面补0（因为BigInteger会自动省略前导零）
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }

            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密过程中出现错误", e);
        }
    }

    /**
     * MD5加密32位大写
     *
     * @param context context
     * @return str
     */
    public static String encrypt32ToUpperCase(String context) {
        return Objects.requireNonNull(encrypt32(context)).toUpperCase();
    }

    /**
     * MD5加密16位小写
     *
     * @param context context
     * @return str
     */
    public static String encrypt16(String context) {
        String encrypt32 = encrypt32(context);
        if (encrypt32.length() >= 32) {
            // 取中间16位
            return encrypt32.substring(8, 24);
        } else {
            throw new RuntimeException("MD5生成失败");
        }
    }

    /**
     * MD5加密16位大写
     *
     * @param context context
     * @return str
     */
    public static String encrypt16ToUpperCase(String context) {
        return Objects.requireNonNull(encrypt16(context)).toUpperCase();
    }
}
