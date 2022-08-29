package p019no.nordicsemi.android.dfu.internal;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import p019no.nordicsemi.android.dfu.internal.exception.HexFileValidationException;

/* renamed from: no.nordicsemi.android.dfu.internal.HexInputStream */
public class HexInputStream extends FilterInputStream {
    private final int LINE_LENGTH = 128;
    private final int MBRSize;
    private final int available;
    private int bytesRead;
    private int lastAddress;
    private final byte[] localBuf;
    private int localPos;
    private int pos;
    private int size;

    private int asciiToInt(int i) {
        if (i >= 65) {
            return i - 55;
        }
        if (i >= 48) {
            return i - 48;
        }
        return -1;
    }

    public HexInputStream(InputStream inputStream, int i) throws HexFileValidationException, IOException {
        super(new BufferedInputStream(inputStream));
        byte[] bArr = new byte[128];
        this.localBuf = bArr;
        this.localPos = 128;
        this.size = bArr.length;
        this.lastAddress = 0;
        this.MBRSize = i;
        this.available = calculateBinSize(i);
    }

    public HexInputStream(byte[] bArr, int i) throws HexFileValidationException, IOException {
        super(new ByteArrayInputStream(bArr));
        byte[] bArr2 = new byte[128];
        this.localBuf = bArr2;
        this.localPos = 128;
        this.size = bArr2.length;
        this.lastAddress = 0;
        this.MBRSize = i;
        this.available = calculateBinSize(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x0067 A[LOOP:1: B:38:0x0067->B:49:0x0067, LOOP_END, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x000f A[LOOP:0: B:3:0x000f->B:50:0x000f, LOOP_END, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int calculateBinSize(int r11) throws p019no.nordicsemi.android.dfu.internal.exception.HexFileValidationException, java.io.IOException {
        /*
            r10 = this;
            java.io.InputStream r0 = r10.in
            int r1 = r0.available()
            r0.mark(r1)
            int r1 = r0.read()     // Catch:{ all -> 0x0074 }
            r2 = 0
            r3 = 0
        L_0x000f:
            r10.checkComma(r1)     // Catch:{ all -> 0x0074 }
            int r1 = r10.readByte(r0)     // Catch:{ all -> 0x0074 }
            int r4 = r10.readAddress(r0)     // Catch:{ all -> 0x0074 }
            int r5 = r10.readByte(r0)     // Catch:{ all -> 0x0074 }
            r6 = 2
            if (r5 == 0) goto L_0x005c
            r4 = 1
            if (r5 == r4) goto L_0x0058
            r8 = 2
            r9 = 4
            if (r5 == r8) goto L_0x0041
            if (r5 == r9) goto L_0x002c
            goto L_0x0060
        L_0x002c:
            int r1 = r10.readAddress(r0)     // Catch:{ all -> 0x0074 }
            if (r3 <= 0) goto L_0x003b
            int r2 = r2 >> 16
            int r2 = r2 + r4
            if (r1 == r2) goto L_0x003b
            r0.reset()
            return r3
        L_0x003b:
            int r1 = r1 << 16
            r10.skip(r0, r6)     // Catch:{ all -> 0x0074 }
            goto L_0x0056
        L_0x0041:
            int r1 = r10.readAddress(r0)     // Catch:{ all -> 0x0074 }
            int r1 = r1 << r9
            if (r3 <= 0) goto L_0x0053
            int r5 = r1 >> 16
            int r2 = r2 >> 16
            int r2 = r2 + r4
            if (r5 == r2) goto L_0x0053
            r0.reset()
            return r3
        L_0x0053:
            r10.skip(r0, r6)     // Catch:{ all -> 0x0074 }
        L_0x0056:
            r2 = r1
            goto L_0x0067
        L_0x0058:
            r0.reset()
            return r3
        L_0x005c:
            int r4 = r4 + r2
            if (r4 < r11) goto L_0x0060
            int r3 = r3 + r1
        L_0x0060:
            long r4 = (long) r1
            long r4 = r4 * r6
            long r4 = r4 + r6
            r10.skip(r0, r4)     // Catch:{ all -> 0x0074 }
        L_0x0067:
            int r1 = r0.read()     // Catch:{ all -> 0x0074 }
            r4 = 10
            if (r1 == r4) goto L_0x0067
            r4 = 13
            if (r1 == r4) goto L_0x0067
            goto L_0x000f
        L_0x0074:
            r11 = move-exception
            r0.reset()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.internal.HexInputStream.calculateBinSize(int):int");
    }

    public int available() {
        return this.available - this.bytesRead;
    }

    public int readPacket(byte[] bArr) throws IOException {
        int i = 0;
        while (i < bArr.length) {
            int i2 = this.localPos;
            if (i2 < this.size) {
                byte[] bArr2 = this.localBuf;
                this.localPos = i2 + 1;
                bArr[i] = bArr2[i2];
                i++;
            } else {
                int i3 = this.bytesRead;
                int readLine = readLine();
                this.size = readLine;
                this.bytesRead = i3 + readLine;
                if (readLine == 0) {
                    break;
                }
            }
        }
        return i;
    }

    public int read() {
        throw new UnsupportedOperationException("Please, use readPacket() method instead");
    }

    public int read(byte[] bArr) throws IOException {
        return readPacket(bArr);
    }

    public int read(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("Please, use readPacket() method instead");
    }

    public int sizeInBytes() {
        return this.available;
    }

    public int sizeInPackets(int i) {
        int sizeInBytes = sizeInBytes();
        return (sizeInBytes / i) + (sizeInBytes % i > 0 ? 1 : 0);
    }

    private int readLine() throws IOException {
        if (this.pos == -1) {
            return 0;
        }
        InputStream inputStream = this.in;
        while (true) {
            int read = inputStream.read();
            this.pos++;
            if (!(read == 10 || read == 13)) {
                checkComma(read);
                int readByte = readByte(inputStream);
                this.pos += 2;
                int readAddress = readAddress(inputStream);
                this.pos += 4;
                int readByte2 = readByte(inputStream);
                int i = this.pos + 2;
                this.pos = i;
                if (readByte2 != 0) {
                    if (readByte2 == 1) {
                        this.pos = -1;
                        return 0;
                    } else if (readByte2 == 2) {
                        int readAddress2 = readAddress(inputStream) << 4;
                        int i2 = this.pos + 4;
                        this.pos = i2;
                        if (this.bytesRead > 0 && (readAddress2 >> 16) != (this.lastAddress >> 16) + 1) {
                            return 0;
                        }
                        this.lastAddress = readAddress2;
                        this.pos = (int) (((long) i2) + skip(inputStream, 2));
                    } else if (readByte2 != 4) {
                        this.pos = (int) (((long) i) + skip(inputStream, (((long) readByte) * 2) + 2));
                    } else {
                        int readAddress3 = readAddress(inputStream);
                        int i3 = this.pos + 4;
                        this.pos = i3;
                        if (this.bytesRead > 0 && readAddress3 != (this.lastAddress >> 16) + 1) {
                            return 0;
                        }
                        this.lastAddress = readAddress3 << 16;
                        this.pos = (int) (((long) i3) + skip(inputStream, 2));
                    }
                } else if (this.lastAddress + readAddress < this.MBRSize) {
                    this.pos = (int) (((long) i) + skip(inputStream, (((long) readByte) * 2) + 2));
                    readByte2 = -1;
                }
                if (readByte2 == 0) {
                    int i4 = 0;
                    while (i4 < this.localBuf.length && i4 < readByte) {
                        int readByte3 = readByte(inputStream);
                        this.pos += 2;
                        this.localBuf[i4] = (byte) readByte3;
                        i4++;
                    }
                    this.pos = (int) (((long) this.pos) + skip(inputStream, 2));
                    this.localPos = 0;
                    return readByte;
                }
            }
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mark(int r1) {
        /*
            r0 = this;
            monitor-enter(r0)
            java.io.InputStream r1 = r0.in     // Catch:{ IOException -> 0x000e, all -> 0x000b }
            int r1 = r1.available()     // Catch:{ IOException -> 0x000e, all -> 0x000b }
            super.mark(r1)     // Catch:{ IOException -> 0x000e, all -> 0x000b }
            goto L_0x000e
        L_0x000b:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x000e:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.internal.HexInputStream.mark(int):void");
    }

    public synchronized void reset() throws IOException {
        super.reset();
        this.pos = 0;
        this.bytesRead = 0;
        this.localPos = 128;
    }

    private void checkComma(int i) throws HexFileValidationException {
        if (i != 58) {
            throw new HexFileValidationException("Not a HEX file");
        }
    }

    private long skip(InputStream inputStream, long j) throws IOException {
        long skip = inputStream.skip(j);
        return skip < j ? skip + inputStream.skip(j - skip) : skip;
    }

    private int readByte(InputStream inputStream) throws IOException {
        return asciiToInt(inputStream.read()) | (asciiToInt(inputStream.read()) << 4);
    }

    private int readAddress(InputStream inputStream) throws IOException {
        return readByte(inputStream) | (readByte(inputStream) << 8);
    }
}
