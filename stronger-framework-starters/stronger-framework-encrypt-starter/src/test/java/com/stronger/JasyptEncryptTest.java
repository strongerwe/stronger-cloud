package com.stronger;


import org.jasypt.util.text.BasicTextEncryptor;

import java.util.Objects;

/**
 * @author stronger
 * @version release-1.0.0
 * @class JasyptEncryptTest.class
 * @department Platform R&D
 * @date 2025/9/7
 * @desc 加解密Test
 */
public class JasyptEncryptTest {

    public static final String ENCRYPT_KEY = "n$ZyyESsxiwyxs=shrbxyaksoelxsaqq=";


    public static void main(String[] args) {
        String key = "stronger@jasypt";
        String passwordDecrypt = encodePassword("Cola@@2024.", key);
        System.out.println(passwordDecrypt);
//        System.out.println(decodePassword(passwordDecrypt));
    }

    /**
     * 加密密码
     *
     * @param password password
     * @return {@link String }
     */
    public static String encodePassword(String password) {
        //密码解密
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(ENCRYPT_KEY);
        return basicTextEncryptor.encrypt(password);
    }

    public static String encodePassword(String password, String key) {
        //密码解密
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        return basicTextEncryptor.encrypt(password);
    }

    /**
     * 解码密码
     *
     * @param password 加密密码
     * @return {@link String }
     */
    public static String decodePassword(String password) {
        //密码解密
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(ENCRYPT_KEY);
        String passwordDecrypt = null;
        try {
            passwordDecrypt = basicTextEncryptor.decrypt(password);
        } catch (Exception e) {
            System.out.println("check connection user password if encrypt : " + password);
        }
        if (Objects.isNull(passwordDecrypt) || passwordDecrypt.isEmpty()) {
            throw new RuntimeException("解密密码错误，请联系管理员！");
        }
        return passwordDecrypt;
    }

}
