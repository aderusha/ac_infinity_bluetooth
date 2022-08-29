package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.util.LruCache;

public class LruResourceCache extends LruCache<Key, Resource<?>> implements MemoryCache {
    private MemoryCache.ResourceRemovedListener listener;

    public /* bridge */ /* synthetic */ Resource put(Key key, Resource resource) {
        return (Resource) super.put(key, resource);
    }

    public /* bridge */ /* synthetic */ Resource remove(Key key) {
        return (Resource) super.remove(key);
    }

    public LruResourceCache(long j) {
        super(j);
    }

    public void setResourceRemovedListener(MemoryCache.ResourceRemovedListener resourceRemovedListener) {
        this.listener = resourceRemovedListener;
    }

    /* access modifiers changed from: protected */
    public void onItemEvicted(Key key, Resource<?> resource) {
        MemoryCache.ResourceRemovedListener resourceRemovedListener = this.listener;
        if (resourceRemovedListener != null && resource != null) {
            resourceRemovedListener.onResourceRemoved(resource);
        }
    }

    /* access modifiers changed from: protected */
    public int getSize(Resource<?> resource) {
        if (resource == null) {
            return super.getSize(null);
        }
        return resource.getSize();
    }

    public void trimMemory(int i) {
        if (i >= 40) {
            clearMemory();
        } else if (i >= 20 || i == 15) {
            trimToSize(getMaxSize() / 2);
        }
    }
}
