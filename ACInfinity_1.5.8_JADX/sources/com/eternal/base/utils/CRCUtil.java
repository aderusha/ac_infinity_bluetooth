package com.eternal.base.utils;

public class CRCUtil {
    public static byte[] crc16(byte[] bArr) {
        byte b = 65535;
        for (byte b2 : bArr) {
            byte b3 = (((b << 8) | (b >>> 8)) & 65535) ^ (b2 & 255);
            byte b4 = b3 ^ ((b3 & 255) >> 4);
            byte b5 = b4 ^ ((b4 << 12) & 65535);
            b = b5 ^ (((b5 & 255) << 5) & 65535);
        }
        byte b6 = b & 65535;
        return new byte[]{(byte) ((b6 >> 8) & 255), (byte) (b6 & 255)};
    }

    public static byte[] crc16(byte[] bArr, int i, int i2) {
        byte b = 65535;
        for (int i3 = i; i3 < i + i2; i3++) {
            byte b2 = (((b << 8) | (b >>> 8)) & 65535) ^ (bArr[i3] & 255);
            byte b3 = b2 ^ ((b2 & 255) >> 4);
            byte b4 = b3 ^ ((b3 << 12) & 65535);
            b = b4 ^ (((b4 & 255) << 5) & 65535);
        }
        byte b5 = b & 65535;
        return new byte[]{(byte) ((b5 >> 8) & 255), (byte) (b5 & 255)};
    }
}
