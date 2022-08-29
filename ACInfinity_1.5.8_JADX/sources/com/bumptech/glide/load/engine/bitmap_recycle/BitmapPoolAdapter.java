package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

public class BitmapPoolAdapter implements BitmapPool {
    public void clearMemory() {
    }

    public long getMaxSize() {
        return 0;
    }

    public void setSizeMultiplier(float f) {
    }

    public void trimMemory(int i) {
    }

    public void put(Bitmap bitmap) {
        bitmap.recycle();
    }

    public Bitmap get(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        return get(i, i2, config);
    }
}
