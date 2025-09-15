package com.stronger;


import com.stronger.commons.utils.MD5Utils;
import com.stronger.commons.utils.UuidUtils;
import com.stronger.encrypt.pwd.EncryptType;
import com.stronger.encrypt.pwd.PwdEncrypt;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PwdEncryptTest.class
 * @department Platform R&D
 * @date 2025/9/15
 * @desc do what?
 */
public class PwdEncryptTest {

    public static void main(String[] args) {
        String encrypt = PwdEncrypt.encrypt(MD5Utils.encrypt32("123456"), EncryptType.USER_PASSWORD);
        System.out.println(encrypt);

        String pwd = "$2y$10$F6.9xilYiEHuAdOIPdeQdO7a6e5FRFqZf47H/WlS8x1YwBjbDoFvK";
        System.out.println(PwdEncrypt.matches(MD5Utils.encrypt32("123456"), pwd, EncryptType.USER_PASSWORD));

        System.out.println(UuidUtils.fastSimpleUuid());
    }
}
