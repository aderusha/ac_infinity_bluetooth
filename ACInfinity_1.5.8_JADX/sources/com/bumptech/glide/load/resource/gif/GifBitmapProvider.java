package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public final class GifBitmapProvider implements GifDecoder.BitmapProvider {
    private final ArrayPool arrayPool;
    private final BitmapPool bitmapPool;

    public GifBitmapProvider(BitmapPool bitmapPool2) {
        this(bitmapPool2, (ArrayPool) null);
    }

    public GifBitmapProvider(BitmapPool bitmapPool2, ArrayPool arrayPool2) {
        this.bitmapPool = bitmapPool2;
        this.arrayPool = arrayPool2;
    }

    public Bitmap obtain(int i, int i2, Bitmap.Config config) {
        return this.bitmapPool.getDirty(i, i2, config);
    }

    public void release(Bitmap bitmap) {
        this.bitmapPool.put(bitmap);
    }

    public byte[] obtainByteArray(int i) {
        ArrayPool arrayPool2 = this.arrayPool;
        if (arrayPool2 == null) {
            return new byte[i];
        }
        return (byte[]) arrayPool2.get(i, byte[].class);
    }

    public void release(byte[] bArr) {
        ArrayPool arrayPool2 = this.arrayPool;
        if (arrayPool2 != null) {
            arrayPool2.put(bArr);
        }
    }

    public int[] obtainIntArray(int i) {
        ArrayPool arrayPool2 = this.arrayPool;
        if (arrayPool2 == null) {
            return new int[i];
        }
        return (int[]) arrayPool2.get(i, int[].class);
    }

    public void release(int[] iArr) {
        ArrayPool arrayPool2 = this.arrayPool;
        if (arrayPool2 != null) {
            arrayPool2.put(iArr);
        }
    }
}
