package com.eternal.account.bleota;

public class EspCRC16 {
    public static int crc(byte[] bArr) {
        return crc(bArr, 0, bArr.length);
    }

    public static int crc(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i < i2) {
            i3 ^= (bArr[i] & 255) << 8;
            for (int i4 = 0; i4 < 8; i4++) {
                i3 = ((32768 & i3) != 0 ? (i3 << 1) ^ 4129 : i3 << 1) & 65535;
            }
            i++;
        }
        return i3;
    }
}
