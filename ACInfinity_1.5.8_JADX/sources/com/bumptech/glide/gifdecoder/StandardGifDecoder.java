package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

public class StandardGifDecoder implements GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    private static final int COLOR_TRANSPARENT_BLACK = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    private static final String TAG = "StandardGifDecoder";
    private int[] act;
    private Bitmap.Config bitmapConfig;
    private final GifDecoder.BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider2, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider2, gifHeader, byteBuffer, 1);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider2, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        this(bitmapProvider2);
        setData(gifHeader, byteBuffer, i);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider2) {
        this.pct = new int[256];
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.bitmapProvider = bitmapProvider2;
        this.header = new GifHeader();
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    public ByteBuffer getData() {
        return this.rawData;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int i) {
        if (i < 0 || i >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i).delay;
    }

    public int getNextDelay() {
        int i;
        if (this.header.frameCount <= 0 || (i = this.framePointer) < 0) {
            return 0;
        }
        return getDelay(i);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    public int getByteSize() {
        return this.rawData.limit() + this.mainPixels.length + (this.mainScratch.length * 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00db, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap getNextFrame() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.bumptech.glide.gifdecoder.GifHeader r0 = r7.header     // Catch:{ all -> 0x00dc }
            int r0 = r0.frameCount     // Catch:{ all -> 0x00dc }
            r1 = 3
            r2 = 1
            if (r0 <= 0) goto L_0x000d
            int r0 = r7.framePointer     // Catch:{ all -> 0x00dc }
            if (r0 >= 0) goto L_0x0039
        L_0x000d:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00dc }
            boolean r3 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00dc }
            if (r3 == 0) goto L_0x0037
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dc }
            r3.<init>()     // Catch:{ all -> 0x00dc }
            java.lang.String r4 = "Unable to decode frame, frameCount="
            r3.append(r4)     // Catch:{ all -> 0x00dc }
            com.bumptech.glide.gifdecoder.GifHeader r4 = r7.header     // Catch:{ all -> 0x00dc }
            int r4 = r4.frameCount     // Catch:{ all -> 0x00dc }
            r3.append(r4)     // Catch:{ all -> 0x00dc }
            java.lang.String r4 = ", framePointer="
            r3.append(r4)     // Catch:{ all -> 0x00dc }
            int r4 = r7.framePointer     // Catch:{ all -> 0x00dc }
            r3.append(r4)     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00dc }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00dc }
        L_0x0037:
            r7.status = r2     // Catch:{ all -> 0x00dc }
        L_0x0039:
            int r0 = r7.status     // Catch:{ all -> 0x00dc }
            r3 = 0
            if (r0 == r2) goto L_0x00bc
            r4 = 2
            if (r0 != r4) goto L_0x0043
            goto L_0x00bc
        L_0x0043:
            r0 = 0
            r7.status = r0     // Catch:{ all -> 0x00dc }
            byte[] r4 = r7.block     // Catch:{ all -> 0x00dc }
            if (r4 != 0) goto L_0x0054
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r4 = r7.bitmapProvider     // Catch:{ all -> 0x00dc }
            r5 = 255(0xff, float:3.57E-43)
            byte[] r4 = r4.obtainByteArray(r5)     // Catch:{ all -> 0x00dc }
            r7.block = r4     // Catch:{ all -> 0x00dc }
        L_0x0054:
            com.bumptech.glide.gifdecoder.GifHeader r4 = r7.header     // Catch:{ all -> 0x00dc }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r4 = r4.frames     // Catch:{ all -> 0x00dc }
            int r5 = r7.framePointer     // Catch:{ all -> 0x00dc }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x00dc }
            com.bumptech.glide.gifdecoder.GifFrame r4 = (com.bumptech.glide.gifdecoder.GifFrame) r4     // Catch:{ all -> 0x00dc }
            int r5 = r7.framePointer     // Catch:{ all -> 0x00dc }
            int r5 = r5 - r2
            if (r5 < 0) goto L_0x0070
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.header     // Catch:{ all -> 0x00dc }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r6 = r6.frames     // Catch:{ all -> 0x00dc }
            java.lang.Object r5 = r6.get(r5)     // Catch:{ all -> 0x00dc }
            com.bumptech.glide.gifdecoder.GifFrame r5 = (com.bumptech.glide.gifdecoder.GifFrame) r5     // Catch:{ all -> 0x00dc }
            goto L_0x0071
        L_0x0070:
            r5 = r3
        L_0x0071:
            int[] r6 = r4.lct     // Catch:{ all -> 0x00dc }
            if (r6 == 0) goto L_0x0078
            int[] r6 = r4.lct     // Catch:{ all -> 0x00dc }
            goto L_0x007c
        L_0x0078:
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.header     // Catch:{ all -> 0x00dc }
            int[] r6 = r6.gct     // Catch:{ all -> 0x00dc }
        L_0x007c:
            r7.act = r6     // Catch:{ all -> 0x00dc }
            if (r6 != 0) goto L_0x00a2
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00dc }
            boolean r1 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00dc }
            if (r1 == 0) goto L_0x009e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dc }
            r1.<init>()     // Catch:{ all -> 0x00dc }
            java.lang.String r4 = "No valid color table found for frame #"
            r1.append(r4)     // Catch:{ all -> 0x00dc }
            int r4 = r7.framePointer     // Catch:{ all -> 0x00dc }
            r1.append(r4)     // Catch:{ all -> 0x00dc }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00dc }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00dc }
        L_0x009e:
            r7.status = r2     // Catch:{ all -> 0x00dc }
            monitor-exit(r7)
            return r3
        L_0x00a2:
            boolean r1 = r4.transparency     // Catch:{ all -> 0x00dc }
            if (r1 == 0) goto L_0x00b6
            int[] r1 = r7.act     // Catch:{ all -> 0x00dc }
            int[] r2 = r7.pct     // Catch:{ all -> 0x00dc }
            int r3 = r1.length     // Catch:{ all -> 0x00dc }
            java.lang.System.arraycopy(r1, r0, r2, r0, r3)     // Catch:{ all -> 0x00dc }
            int[] r1 = r7.pct     // Catch:{ all -> 0x00dc }
            r7.act = r1     // Catch:{ all -> 0x00dc }
            int r2 = r4.transIndex     // Catch:{ all -> 0x00dc }
            r1[r2] = r0     // Catch:{ all -> 0x00dc }
        L_0x00b6:
            android.graphics.Bitmap r0 = r7.setPixels(r4, r5)     // Catch:{ all -> 0x00dc }
            monitor-exit(r7)
            return r0
        L_0x00bc:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00dc }
            boolean r1 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00dc }
            if (r1 == 0) goto L_0x00da
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dc }
            r1.<init>()     // Catch:{ all -> 0x00dc }
            java.lang.String r2 = "Unable to decode frame, status="
            r1.append(r2)     // Catch:{ all -> 0x00dc }
            int r2 = r7.status     // Catch:{ all -> 0x00dc }
            r1.append(r2)     // Catch:{ all -> 0x00dc }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00dc }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00dc }
        L_0x00da:
            monitor-exit(r7)
            return r3
        L_0x00dc:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.getNextFrame():android.graphics.Bitmap");
    }

    public int read(InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, 16384);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        byte[] bArr = this.mainPixels;
        if (bArr != null) {
            this.bitmapProvider.release(bArr);
        }
        int[] iArr = this.mainScratch;
        if (iArr != null) {
            this.bitmapProvider.release(iArr);
        }
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        byte[] bArr2 = this.block;
        if (bArr2 != null) {
            this.bitmapProvider.release(bArr2);
        }
    }

    public synchronized void setData(GifHeader gifHeader, byte[] bArr) {
        setData(gifHeader, ByteBuffer.wrap(bArr));
    }

    public synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer) {
        setData(gifHeader, byteBuffer, 1);
    }

    public synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        if (i > 0) {
            int highestOneBit = Integer.highestOneBit(i);
            this.status = 0;
            this.header = gifHeader;
            this.framePointer = -1;
            ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
            this.rawData = asReadOnlyBuffer;
            asReadOnlyBuffer.position(0);
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.savePrevious = false;
            Iterator<GifFrame> it = gifHeader.frames.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().dispose == 3) {
                        this.savePrevious = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            this.sampleSize = highestOneBit;
            this.downsampledWidth = gifHeader.width / highestOneBit;
            this.downsampledHeight = gifHeader.height / highestOneBit;
            this.mainPixels = this.bitmapProvider.obtainByteArray(gifHeader.width * gifHeader.height);
            this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
        } else {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i);
        }
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    public synchronized int read(byte[] bArr) {
        GifHeader parseHeader = getHeaderParser().setData(bArr).parseHeader();
        this.header = parseHeader;
        if (bArr != null) {
            setData(parseHeader, bArr);
        }
        return this.status;
    }

    public void setDefaultBitmapConfig(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888 || config == Bitmap.Config.RGB_565) {
            this.bitmapConfig = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        Bitmap bitmap;
        int[] iArr = this.mainScratch;
        int i = 0;
        if (gifFrame2 == null) {
            Bitmap bitmap2 = this.previousImage;
            if (bitmap2 != null) {
                this.bitmapProvider.release(bitmap2);
            }
            this.previousImage = null;
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose == 3 && this.previousImage == null) {
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose > 0) {
            if (gifFrame2.dispose == 2) {
                if (!gifFrame.transparency) {
                    int i2 = this.header.bgColor;
                    if (gifFrame.lct == null || this.header.bgIndex != gifFrame.transIndex) {
                        i = i2;
                    }
                } else if (this.framePointer == 0) {
                    this.isFirstFrameTransparent = true;
                }
                int i3 = gifFrame2.f80ih / this.sampleSize;
                int i4 = gifFrame2.f83iy / this.sampleSize;
                int i5 = gifFrame2.f81iw / this.sampleSize;
                int i6 = gifFrame2.f82ix / this.sampleSize;
                int i7 = this.downsampledWidth;
                int i8 = (i4 * i7) + i6;
                int i9 = (i3 * i7) + i8;
                while (i8 < i9) {
                    int i10 = i8 + i5;
                    for (int i11 = i8; i11 < i10; i11++) {
                        iArr[i11] = i;
                    }
                    i8 += this.downsampledWidth;
                }
            } else if (gifFrame2.dispose == 3 && (bitmap = this.previousImage) != null) {
                int i12 = this.downsampledWidth;
                bitmap.getPixels(iArr, 0, i12, 0, 0, i12, this.downsampledHeight);
            }
        }
        decodeBitmapData(gifFrame);
        if (gifFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(gifFrame);
        } else {
            copyIntoScratchFast(gifFrame);
        }
        if (this.savePrevious && (gifFrame.dispose == 0 || gifFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            Bitmap bitmap3 = this.previousImage;
            int i13 = this.downsampledWidth;
            bitmap3.setPixels(iArr, 0, i13, 0, 0, i13, this.downsampledHeight);
        }
        Bitmap nextBitmap = getNextBitmap();
        int i14 = this.downsampledWidth;
        nextBitmap.setPixels(iArr, 0, i14, 0, 0, i14, this.downsampledHeight);
        return nextBitmap;
    }

    private void copyIntoScratchFast(GifFrame gifFrame) {
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.mainScratch;
        int i = gifFrame2.f80ih;
        int i2 = gifFrame2.f83iy;
        int i3 = gifFrame2.f81iw;
        int i4 = gifFrame2.f82ix;
        boolean z = this.framePointer == 0;
        int i5 = this.downsampledWidth;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        int i6 = 0;
        byte b = -1;
        while (i6 < i) {
            int i7 = (i6 + i2) * i5;
            int i8 = i7 + i4;
            int i9 = i8 + i3;
            int i10 = i7 + i5;
            if (i10 < i9) {
                i9 = i10;
            }
            int i11 = gifFrame2.f81iw * i6;
            int i12 = i8;
            while (i12 < i9) {
                byte b2 = bArr[i11];
                int i13 = i;
                byte b3 = b2 & 255;
                if (b3 != b) {
                    int i14 = iArr2[b3];
                    if (i14 != 0) {
                        iArr[i12] = i14;
                    } else {
                        b = b2;
                    }
                }
                i11++;
                i12++;
                GifFrame gifFrame3 = gifFrame;
                i = i13;
            }
            int i15 = i;
            i6++;
            gifFrame2 = gifFrame;
        }
        this.isFirstFrameTransparent = Boolean.valueOf(this.isFirstFrameTransparent == null && z && b != -1);
    }

    private void copyCopyIntoScratchRobust(GifFrame gifFrame) {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.mainScratch;
        int i6 = gifFrame2.f80ih / this.sampleSize;
        int i7 = gifFrame2.f83iy / this.sampleSize;
        int i8 = gifFrame2.f81iw / this.sampleSize;
        int i9 = gifFrame2.f82ix;
        int i10 = this.sampleSize;
        int i11 = i9 / i10;
        boolean z2 = this.framePointer == 0;
        int i12 = this.downsampledWidth;
        int i13 = this.downsampledHeight;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        Boolean bool = this.isFirstFrameTransparent;
        int i14 = 8;
        int i15 = 0;
        int i16 = 0;
        int i17 = 1;
        while (i15 < i6) {
            Boolean bool2 = bool;
            if (gifFrame2.interlace) {
                if (i16 >= i6) {
                    i = i6;
                    int i18 = i17 + 1;
                    if (i18 == 2) {
                        i17 = i18;
                        i16 = 4;
                    } else if (i18 == 3) {
                        i17 = i18;
                        i16 = 2;
                        i14 = 4;
                    } else if (i18 != 4) {
                        i17 = i18;
                    } else {
                        i17 = i18;
                        i16 = 1;
                        i14 = 2;
                    }
                } else {
                    i = i6;
                }
                i2 = i16 + i14;
            } else {
                i = i6;
                i2 = i16;
                i16 = i15;
            }
            int i19 = i16 + i7;
            boolean z3 = i10 == 1;
            if (i19 < i13) {
                int i20 = i19 * i12;
                int i21 = i20 + i11;
                int i22 = i21 + i8;
                int i23 = i20 + i12;
                if (i23 < i22) {
                    i22 = i23;
                }
                i3 = i2;
                int i24 = i15 * i10 * gifFrame2.f81iw;
                if (z3) {
                    int i25 = i21;
                    while (i25 < i22) {
                        int i26 = i7;
                        int i27 = iArr2[bArr[i24] & 255];
                        if (i27 != 0) {
                            iArr[i25] = i27;
                        } else if (z2 && bool2 == null) {
                            bool2 = 1;
                        }
                        i24 += i10;
                        i25++;
                        i7 = i26;
                    }
                } else {
                    i5 = i7;
                    int i28 = ((i22 - i21) * i10) + i24;
                    int i29 = i21;
                    while (true) {
                        i4 = i8;
                        if (i29 >= i22) {
                            break;
                        }
                        int averageColorsNear = averageColorsNear(i24, i28, gifFrame2.f81iw);
                        if (averageColorsNear != 0) {
                            iArr[i29] = averageColorsNear;
                        } else if (z2 && bool2 == null) {
                            bool2 = 1;
                        }
                        i24 += i10;
                        i29++;
                        i8 = i4;
                    }
                    bool = bool2;
                    i15++;
                    i7 = i5;
                    i8 = i4;
                    i6 = i;
                    i16 = i3;
                }
            } else {
                i3 = i2;
            }
            i5 = i7;
            i4 = i8;
            bool = bool2;
            i15++;
            i7 = i5;
            i8 = i4;
            i6 = i;
            i16 = i3;
        }
        Boolean bool3 = bool;
        if (this.isFirstFrameTransparent == null) {
            if (bool3 == null) {
                z = false;
            } else {
                z = bool3.booleanValue();
            }
            this.isFirstFrameTransparent = Boolean.valueOf(z);
        }
    }

    private int averageColorsNear(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = i; i9 < this.sampleSize + i; i9++) {
            byte[] bArr = this.mainPixels;
            if (i9 >= bArr.length || i9 >= i2) {
                break;
            }
            int i10 = this.act[bArr[i9] & 255];
            if (i10 != 0) {
                i4 += (i10 >> 24) & 255;
                i5 += (i10 >> 16) & 255;
                i6 += (i10 >> 8) & 255;
                i7 += i10 & 255;
                i8++;
            }
        }
        int i11 = i + i3;
        for (int i12 = i11; i12 < this.sampleSize + i11; i12++) {
            byte[] bArr2 = this.mainPixels;
            if (i12 >= bArr2.length || i12 >= i2) {
                break;
            }
            int i13 = this.act[bArr2[i12] & 255];
            if (i13 != 0) {
                i4 += (i13 >> 24) & 255;
                i5 += (i13 >> 16) & 255;
                i6 += (i13 >> 8) & 255;
                i7 += i13 & 255;
                i8++;
            }
        }
        if (i8 == 0) {
            return 0;
        }
        return ((i4 / i8) << 24) | ((i5 / i8) << 16) | ((i6 / i8) << 8) | (i7 / i8);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r7v13, types: [short] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r29) {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
            if (r1 == 0) goto L_0x000d
            java.nio.ByteBuffer r2 = r0.rawData
            int r3 = r1.bufferFrameStart
            r2.position(r3)
        L_0x000d:
            if (r1 != 0) goto L_0x001a
            com.bumptech.glide.gifdecoder.GifHeader r1 = r0.header
            int r1 = r1.width
            com.bumptech.glide.gifdecoder.GifHeader r2 = r0.header
            int r2 = r2.height
            int r1 = r1 * r2
            goto L_0x0020
        L_0x001a:
            int r2 = r1.f81iw
            int r1 = r1.f80ih
            int r1 = r1 * r2
        L_0x0020:
            byte[] r2 = r0.mainPixels
            if (r2 == 0) goto L_0x0027
            int r2 = r2.length
            if (r2 >= r1) goto L_0x002f
        L_0x0027:
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r2 = r0.bitmapProvider
            byte[] r2 = r2.obtainByteArray(r1)
            r0.mainPixels = r2
        L_0x002f:
            byte[] r2 = r0.mainPixels
            short[] r3 = r0.prefix
            r4 = 4096(0x1000, float:5.74E-42)
            if (r3 != 0) goto L_0x003b
            short[] r3 = new short[r4]
            r0.prefix = r3
        L_0x003b:
            short[] r3 = r0.prefix
            byte[] r5 = r0.suffix
            if (r5 != 0) goto L_0x0045
            byte[] r5 = new byte[r4]
            r0.suffix = r5
        L_0x0045:
            byte[] r5 = r0.suffix
            byte[] r6 = r0.pixelStack
            if (r6 != 0) goto L_0x0051
            r6 = 4097(0x1001, float:5.741E-42)
            byte[] r6 = new byte[r6]
            r0.pixelStack = r6
        L_0x0051:
            byte[] r6 = r0.pixelStack
            int r7 = r28.readByte()
            r8 = 1
            int r9 = r8 << r7
            int r10 = r9 + 1
            int r11 = r9 + 2
            int r7 = r7 + r8
            int r12 = r8 << r7
            int r12 = r12 - r8
            r13 = 0
            r14 = 0
        L_0x0064:
            if (r14 >= r9) goto L_0x006e
            r3[r14] = r13
            byte r15 = (byte) r14
            r5[r14] = r15
            int r14 = r14 + 1
            goto L_0x0064
        L_0x006e:
            byte[] r14 = r0.block
            r15 = -1
            r23 = r7
            r21 = r11
            r22 = r12
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r24 = -1
            r25 = 0
            r26 = 0
        L_0x0087:
            if (r13 >= r1) goto L_0x0150
            if (r16 != 0) goto L_0x0098
            int r16 = r28.readBlock()
            if (r16 > 0) goto L_0x0096
            r3 = 3
            r0.status = r3
            goto L_0x0150
        L_0x0096:
            r17 = 0
        L_0x0098:
            byte r4 = r14[r17]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r18
            int r19 = r19 + r4
            int r18 = r18 + 8
            int r17 = r17 + 1
            int r16 = r16 + -1
            r4 = r18
            r8 = r21
            r15 = r23
            r0 = r24
            r23 = r7
            r7 = r25
        L_0x00b2:
            if (r4 < r15) goto L_0x013a
            r24 = r11
            r11 = r19 & r22
            int r19 = r19 >> r15
            int r4 = r4 - r15
            if (r11 != r9) goto L_0x00c6
            r22 = r12
            r15 = r23
            r8 = r24
            r11 = r8
            r0 = -1
            goto L_0x00b2
        L_0x00c6:
            if (r11 != r10) goto L_0x00dd
            r18 = r4
            r25 = r7
            r21 = r8
            r7 = r23
            r11 = r24
            r4 = 4096(0x1000, float:5.74E-42)
            r8 = 1
            r24 = r0
            r23 = r15
            r15 = -1
            r0 = r28
            goto L_0x0087
        L_0x00dd:
            r25 = r4
            r4 = -1
            if (r0 != r4) goto L_0x00f1
            byte r0 = r5[r11]
            r2[r20] = r0
            int r20 = r20 + 1
            int r13 = r13 + 1
            r0 = r11
            r7 = r0
            r11 = r24
            r4 = r25
            goto L_0x00b2
        L_0x00f1:
            if (r11 < r8) goto L_0x00fa
            byte r7 = (byte) r7
            r6[r26] = r7
            int r26 = r26 + 1
            r7 = r0
            goto L_0x00fb
        L_0x00fa:
            r7 = r11
        L_0x00fb:
            if (r7 < r9) goto L_0x0106
            byte r21 = r5[r7]
            r6[r26] = r21
            int r26 = r26 + 1
            short r7 = r3[r7]
            goto L_0x00fb
        L_0x0106:
            byte r7 = r5[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r7
            r2[r20] = r4
        L_0x010d:
            int r20 = r20 + 1
            int r13 = r13 + 1
            if (r26 <= 0) goto L_0x011a
            int r26 = r26 + -1
            byte r27 = r6[r26]
            r2[r20] = r27
            goto L_0x010d
        L_0x011a:
            r27 = r6
            r6 = 4096(0x1000, float:5.74E-42)
            if (r8 >= r6) goto L_0x0131
            short r0 = (short) r0
            r3[r8] = r0
            r5[r8] = r4
            int r8 = r8 + 1
            r0 = r8 & r22
            if (r0 != 0) goto L_0x0131
            if (r8 >= r6) goto L_0x0131
            int r15 = r15 + 1
            int r22 = r22 + r8
        L_0x0131:
            r0 = r11
            r11 = r24
            r4 = r25
            r6 = r27
            goto L_0x00b2
        L_0x013a:
            r25 = r4
            r24 = r0
            r21 = r8
            r18 = r25
            r4 = 4096(0x1000, float:5.74E-42)
            r8 = 1
            r0 = r28
            r25 = r7
            r7 = r23
            r23 = r15
            r15 = -1
            goto L_0x0087
        L_0x0150:
            r13 = r20
            r0 = 0
            java.util.Arrays.fill(r2, r13, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private int readByte() {
        return this.rawData.get() & 255;
    }

    private int readBlock() {
        int readByte = readByte();
        if (readByte <= 0) {
            return readByte;
        }
        ByteBuffer byteBuffer = this.rawData;
        byteBuffer.get(this.block, 0, Math.min(readByte, byteBuffer.remaining()));
        return readByte;
    }

    private Bitmap getNextBitmap() {
        Boolean bool = this.isFirstFrameTransparent;
        Bitmap obtain = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig);
        obtain.setHasAlpha(true);
        return obtain;
    }
}
