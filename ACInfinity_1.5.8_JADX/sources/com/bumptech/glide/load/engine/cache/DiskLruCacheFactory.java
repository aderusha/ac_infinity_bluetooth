package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;

public class DiskLruCacheFactory implements DiskCache.Factory {
    private final CacheDirectoryGetter cacheDirectoryGetter;
    private final long diskCacheSize;

    public interface CacheDirectoryGetter {
        File getCacheDirectory();
    }

    public DiskLruCacheFactory(final String str, long j) {
        this((CacheDirectoryGetter) new CacheDirectoryGetter() {
            public File getCacheDirectory() {
                return new File(str);
            }
        }, j);
    }

    public DiskLruCacheFactory(final String str, final String str2, long j) {
        this((CacheDirectoryGetter) new CacheDirectoryGetter() {
            public File getCacheDirectory() {
                return new File(str, str2);
            }
        }, j);
    }

    public DiskLruCacheFactory(CacheDirectoryGetter cacheDirectoryGetter2, long j) {
        this.diskCacheSize = j;
        this.cacheDirectoryGetter = cacheDirectoryGetter2;
    }

    public DiskCache build() {
        File cacheDirectory = this.cacheDirectoryGetter.getCacheDirectory();
        if (cacheDirectory == null) {
            return null;
        }
        if (cacheDirectory.mkdirs() || (cacheDirectory.exists() && cacheDirectory.isDirectory())) {
            return DiskLruCacheWrapper.create(cacheDirectory, this.diskCacheSize);
        }
        return null;
    }
}
