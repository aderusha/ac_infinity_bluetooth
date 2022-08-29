package com.eternal.framework.http.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static final String SALT = "HXWcjvQWVG1wI4FQBLZpQ3pWj48AV63d";

    public static String EncoderByMd5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 16; i++) {
                stringBuffer.append(byteHEX(digest[i]));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encodeByMd5AndSalt(String str) {
        return EncoderByMd5(EncoderByMd5(str) + SALT);
    }

    public static String byteHEX(byte b) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        return new String(new char[]{cArr[(b >>> 4) & 15], cArr[b & 15]});
    }
}
