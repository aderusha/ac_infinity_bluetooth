package com.telink.p010lt.util;

import java.nio.charset.Charset;

/* renamed from: com.telink.lt.util.Strings */
public final class Strings {
    private Strings() {
    }

    public static byte[] stringToBytes(String str, int i) {
        if (i <= 0) {
            return str.getBytes(Charset.defaultCharset());
        }
        byte[] bArr = new byte[i];
        byte[] bytes = str.getBytes(Charset.defaultCharset());
        if (bytes.length <= i) {
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        } else {
            System.arraycopy(bytes, 0, bArr, 0, i);
        }
        return bArr;
    }

    public static byte[] stringToBytes(String str) {
        return stringToBytes(str, 0);
    }

    public static String bytesToString(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new String(bArr, Charset.defaultCharset()).trim();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
