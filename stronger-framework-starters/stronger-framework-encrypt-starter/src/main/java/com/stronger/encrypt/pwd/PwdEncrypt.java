package com.stronger.encrypt.pwd;

import com.stronger.commons.utils.MD5Utils;
import com.stronger.commons.utils.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PlatformJwtServiceAutoConfiguration.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 密码加密
 */
public class PwdEncrypt {

    private static final BCryptPasswordEncoder ENCODER;

    static {
        ENCODER = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, -1);
    }

    /**
     * 加密
     *
     * @param pwd         pwd
     * @param encryptType encryptType
     * @return {@link String }
     */
    public static String encrypt(String pwd, EncryptType encryptType) {
        if (StringUtils.isEmpty(pwd) || encryptType == null) {
            throw new NullPointerException("encrypt value or type cannot be empty");
        }
        String enPwd = intensify(pwd, encryptType);
        return encrypt(enPwd);
    }

    /**
     * 匹配密码验证
     *
     * @param pwd         pwd
     * @param encodedPwd  encodedPwd
     * @param encryptType encryptType
     * @return boolean
     */
    public static boolean matches(String pwd, String encodedPwd, EncryptType encryptType) {
        if (StringUtils.isEmpty(pwd) || StringUtils.isEmpty(encodedPwd) || encryptType == null) {
            throw new NullPointerException("encrypt value or type or encoded pwd cannot be empty");
        }
        String enPwd = intensify(pwd, encryptType);
        return matches(enPwd, encodedPwd);
    }

    /**
     * spring-security加密
     *
     * @param rawPassword rawPassword
     * @return {@link String }
     */
    private static String encrypt(CharSequence rawPassword) {
        return ENCODER.encode(rawPassword);
    }


    /**
     * spring-security匹配
     *
     * @param rawPassword     rawPassword
     * @param encodedPassword encodedPassword
     * @return boolean
     */
    private static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }

    /**
     * 密码增强转化【慎重！！！修改后导致数据库密码失效】
     *
     * @param pwd  pwd
     * @param type type
     * @return {@link String }
     */
    private static String intensify(String pwd, EncryptType type) {
        return switch (type) {
            case CLIENT_SECRET -> MD5Utils.encrypt32(pwd);
            case USER_PASSWORD -> {
                byte[] encodedAuth = Base64.getEncoder().encode(MD5Utils.encrypt32(pwd).getBytes(StandardCharsets.US_ASCII));
                yield new String(encodedAuth);
            }
            default -> pwd;
        };
    }
}
