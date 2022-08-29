package com.bumptech.glide.util;

import com.bumptech.glide.ListPreloader;

public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {
    private final int[] size;

    public FixedPreloadSizeProvider(int i, int i2) {
        this.size = new int[]{i, i2};
    }

    public int[] getPreloadSize(T t, int i, int i2) {
        return this.size;
    }
}
