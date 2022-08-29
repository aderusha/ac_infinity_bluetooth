package com.bumptech.glide.load.engine.bitmap_recycle;

public final class IntegerArrayAdapter implements ArrayAdapterInterface<int[]> {
    private static final String TAG = "IntegerArrayPool";

    public int getElementSizeInBytes() {
        return 4;
    }

    public String getTag() {
        return TAG;
    }

    public int getArrayLength(int[] iArr) {
        return iArr.length;
    }

    public int[] newArray(int i) {
        return new int[i];
    }
}
