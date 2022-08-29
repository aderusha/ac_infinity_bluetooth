package com.bumptech.glide.load.engine.bitmap_recycle;

public final class ByteArrayAdapter implements ArrayAdapterInterface<byte[]> {
    private static final String TAG = "ByteArrayPool";

    public int getElementSizeInBytes() {
        return 1;
    }

    public String getTag() {
        return TAG;
    }

    public int getArrayLength(byte[] bArr) {
        return bArr.length;
    }

    public byte[] newArray(int i) {
        return new byte[i];
    }
}
