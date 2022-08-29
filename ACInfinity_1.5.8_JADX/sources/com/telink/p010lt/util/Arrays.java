package com.telink.p010lt.util;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;

/* renamed from: com.telink.lt.util.Arrays */
public final class Arrays {
    private Arrays() {
    }

    public static byte[] reverse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int i = 0;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        while (true) {
            length--;
            if (length < 0) {
                return bArr2;
            }
            bArr2[length] = bArr[i];
            i++;
        }
    }

    public static byte[] reverse(byte[] bArr, int i, int i2) {
        while (i < i2) {
            byte b = bArr[i2];
            bArr[i2] = bArr[i];
            bArr[i] = b;
            i++;
            i2--;
        }
        return bArr;
    }

    public static boolean equals(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static String bytesToString(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        if (bArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 6);
        sb.append('[');
        sb.append(bArr[0]);
        for (int i = 1; i < bArr.length; i++) {
            sb.append(", ");
            sb.append(bArr[i]);
        }
        sb.append(']');
        return sb.toString();
    }

    public static String bytesToString(byte[] bArr, String str) throws UnsupportedEncodingException {
        return new String(bArr, str);
    }

    public static String bytesToHexString(byte[] bArr, String str) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%02X", new Object[]{Byte.valueOf(bArr[0])});
        for (int i = 1; i < bArr.length; i++) {
            if (!Strings.isEmpty(str)) {
                sb.append(str);
            }
            formatter.format("%02X", new Object[]{Byte.valueOf(bArr[i])});
        }
        formatter.flush();
        formatter.close();
        return sb.toString();
    }

    public static byte[] hexToBytes(String str) {
        if (str.length() == 1) {
            str = "0" + str;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static int bytesToInt(byte[] bArr, int i) {
        if (bArr.length != 4) {
            return 0;
        }
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }
}
