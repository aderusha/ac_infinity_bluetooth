package com.eternal.base.utils;

import android.text.TextUtils;
import com.eternal.export.CSVUtil;
import java.nio.charset.StandardCharsets;

public class ByteUtils {
    public static int char2Int(char c) {
        return (short) ((((byte) (c & 255)) & 255) | (65280 & (((byte) ((c & 65280) >> 8)) << 8)));
    }

    public static boolean getBit(byte b, int i) {
        return ((b >> (7 - i)) & 1) == 0;
    }

    public static int getBits(byte b, int i, int i2) {
        return (b >> ((8 - i) - i2)) & (255 >> (8 - i2));
    }

    public static byte[] int2ByteArray(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static char int2Char(int i) {
        return (char) ((((byte) (i & 255)) & 255) | (65280 & (((byte) ((i & 65280) >> 8)) << 8)));
    }

    public static String bytes2HexString(byte[] bArr) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    str = "0x0" + hexString + CSVUtil.COLUMN_SEPARATOR;
                } else {
                    str = "0x" + hexString + CSVUtil.COLUMN_SEPARATOR;
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String bytes2HexString(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (int i3 = i; i3 < i + i2; i3++) {
                String hexString = Integer.toHexString(bArr[i3] & 255);
                if (hexString.length() == 1) {
                    sb.append("0x0");
                    sb.append(hexString);
                    sb.append(',');
                } else {
                    sb.append("0x");
                    sb.append(hexString);
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    public static short getShort(byte[] bArr, int i) {
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] << 8) & 65280));
    }

    public static char getChar(byte[] bArr, int i) {
        return (char) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public static int getInt(byte[] bArr, int i) {
        return char2Int(getChar(bArr, i));
    }

    public static byte[] mergeBytes(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte[] subByte(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static byte[] getBytesByString(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        return str.getBytes(StandardCharsets.UTF_8);
    }
}
