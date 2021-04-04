package com.imooc.mall.util;

import com.imooc.mall.common.Constant;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述： MD5工具
 */
public class MD5Utils {
    public static String getMd5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strValue + Constant.SALT).getBytes()));
    }

    // 用这个方法测试生成的MD5的值
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md5 = getMd5Str("ddd");
        System.out.println(md5);
    }
}
