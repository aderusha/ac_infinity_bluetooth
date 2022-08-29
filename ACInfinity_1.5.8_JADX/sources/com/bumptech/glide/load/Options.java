package com.bumptech.glide.load;

import androidx.collection.ArrayMap;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import java.security.MessageDigest;

public final class Options implements Key {
    private final ArrayMap<Option<?>, Object> values = new CachedHashCodeArrayMap();

    public void putAll(Options options) {
        this.values.putAll(options.values);
    }

    public <T> Options set(Option<T> option, T t) {
        this.values.put(option, t);
        return this;
    }

    public <T> T get(Option<T> option) {
        return this.values.containsKey(option) ? this.values.get(option) : option.getDefaultValue();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Options) {
            return this.values.equals(((Options) obj).values);
        }
        return false;
    }

    public int hashCode() {
        return this.values.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        for (int i = 0; i < this.values.size(); i++) {
            updateDiskCacheKey(this.values.keyAt(i), this.values.valueAt(i), messageDigest);
        }
    }

    public String toString() {
        return "Options{values=" + this.values + '}';
    }

    private static <T> void updateDiskCacheKey(Option<T> option, Object obj, MessageDigest messageDigest) {
        option.update(obj, messageDigest);
    }
}
