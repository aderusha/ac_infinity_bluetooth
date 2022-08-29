package com.telink.p010lt.ble;

import com.telink.p010lt.util.Arrays;
import com.telink.p010lt.util.TelinkLog;

/* renamed from: com.telink.lt.ble.OtaPacketParser */
public class OtaPacketParser {
    private byte[] data;
    private int index = -1;
    private int progress;
    private int total;

    public void set(byte[] bArr) {
        clear();
        this.data = bArr;
        int length = bArr.length;
        if (length % 16 == 0) {
            this.total = length / 16;
        } else {
            this.total = (int) Math.floor((double) ((length / 16) + 1));
        }
    }

    public void clear() {
        this.progress = 0;
        this.total = 0;
        this.index = -1;
        this.data = null;
    }

    public boolean hasNextPacket() {
        int i = this.total;
        return i > 0 && this.index + 1 < i;
    }

    public boolean isLast() {
        return this.index + 1 == this.total;
    }

    public int getNextPacketIndex() {
        return this.index + 1;
    }

    public byte[] getNextPacket() {
        int nextPacketIndex = getNextPacketIndex();
        byte[] packet = getPacket(nextPacketIndex);
        this.index = nextPacketIndex;
        return packet;
    }

    public byte[] getPacket(int i) {
        int length = this.data.length;
        if (length > 16) {
            length = i + 1 == this.total ? length - (i * 16) : 16;
        }
        int i2 = length + 4;
        byte[] bArr = new byte[20];
        for (int i3 = 0; i3 < 20; i3++) {
            bArr[i3] = -1;
        }
        System.arraycopy(this.data, i * 16, bArr, 2, i2 - 4);
        fillIndex(bArr, i);
        int crc16 = crc16(bArr);
        fillCrc(bArr, crc16);
        TelinkLog.m265d("ota packet ---> index : " + i + " total : " + this.total + " crc : " + crc16 + " content : " + Arrays.bytesToHexString(bArr, ":"));
        return bArr;
    }

    public byte[] getCheckPacket() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = -1;
        }
        int nextPacketIndex = getNextPacketIndex();
        fillIndex(bArr, nextPacketIndex);
        int crc16 = crc16(bArr);
        fillCrc(bArr, crc16);
        TelinkLog.m265d("ota check packet ---> index : " + nextPacketIndex + " crc : " + crc16 + " content : " + Arrays.bytesToHexString(bArr, ":"));
        return bArr;
    }

    public void fillIndex(byte[] bArr, int i) {
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((i >> 8) & 255);
    }

    public void fillCrc(byte[] bArr, int i) {
        int length = bArr.length - 2;
        bArr[length] = (byte) (i & 255);
        bArr[length + 1] = (byte) ((i >> 8) & 255);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int crc16(byte[] r10) {
        /*
            r9 = this;
            int r0 = r10.length
            r1 = 2
            int r0 = r0 - r1
            short[] r1 = new short[r1]
            r1 = {0, -24575} // fill-array
            r2 = 0
            r3 = 65535(0xffff, float:9.1834E-41)
            r4 = 0
            r5 = 65535(0xffff, float:9.1834E-41)
        L_0x0010:
            if (r4 >= r0) goto L_0x002a
            byte r6 = r10[r4]
            r7 = 0
        L_0x0015:
            r8 = 8
            if (r7 >= r8) goto L_0x0027
            int r8 = r5 >> 1
            r5 = r5 ^ r6
            r5 = r5 & 1
            short r5 = r1[r5]
            r5 = r5 & r3
            r5 = r5 ^ r8
            int r6 = r6 >> 1
            int r7 = r7 + 1
            goto L_0x0015
        L_0x0027:
            int r4 = r4 + 1
            goto L_0x0010
        L_0x002a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.telink.p010lt.ble.OtaPacketParser.crc16(byte[]):int");
    }

    public boolean invalidateProgress() {
        int floor = (int) Math.floor((double) ((((float) getNextPacketIndex()) / ((float) this.total)) * 100.0f));
        if (floor == this.progress) {
            return false;
        }
        this.progress = floor;
        return true;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getIndex() {
        return this.index;
    }
}
