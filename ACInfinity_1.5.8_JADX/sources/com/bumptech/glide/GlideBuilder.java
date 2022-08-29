package com.bumptech.glide;

import android.content.Context;
import androidx.collection.ArrayMap;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.request.RequestOptions;
import java.util.Map;

public final class GlideBuilder {
    private GlideExecutor animationExecutor;
    private ArrayPool arrayPool;
    private BitmapPool bitmapPool;
    private ConnectivityMonitorFactory connectivityMonitorFactory;
    private RequestOptions defaultRequestOptions = new RequestOptions();
    private final Map<Class<?>, TransitionOptions<?, ?>> defaultTransitionOptions = new ArrayMap();
    private GlideExecutor diskCacheExecutor;
    private DiskCache.Factory diskCacheFactory;
    private Engine engine;
    private boolean isActiveResourceRetentionAllowed;
    private int logLevel = 4;
    private MemoryCache memoryCache;
    private MemorySizeCalculator memorySizeCalculator;
    private RequestManagerRetriever.RequestManagerFactory requestManagerFactory;
    private GlideExecutor sourceExecutor;

    public GlideBuilder setBitmapPool(BitmapPool bitmapPool2) {
        this.bitmapPool = bitmapPool2;
        return this;
    }

    public GlideBuilder setArrayPool(ArrayPool arrayPool2) {
        this.arrayPool = arrayPool2;
        return this;
    }

    public GlideBuilder setMemoryCache(MemoryCache memoryCache2) {
        this.memoryCache = memoryCache2;
        return this;
    }

    public GlideBuilder setDiskCache(DiskCache.Factory factory) {
        this.diskCacheFactory = factory;
        return this;
    }

    @Deprecated
    public GlideBuilder setResizeExecutor(GlideExecutor glideExecutor) {
        return setSourceExecutor(glideExecutor);
    }

    public GlideBuilder setSourceExecutor(GlideExecutor glideExecutor) {
        this.sourceExecutor = glideExecutor;
        return this;
    }

    public GlideBuilder setDiskCacheExecutor(GlideExecutor glideExecutor) {
        this.diskCacheExecutor = glideExecutor;
        return this;
    }

    public GlideBuilder setAnimationExecutor(GlideExecutor glideExecutor) {
        this.animationExecutor = glideExecutor;
        return this;
    }

    public GlideBuilder setDefaultRequestOptions(RequestOptions requestOptions) {
        this.defaultRequestOptions = requestOptions;
        return this;
    }

    public <T> GlideBuilder setDefaultTransitionOptions(Class<T> cls, TransitionOptions<?, T> transitionOptions) {
        this.defaultTransitionOptions.put(cls, transitionOptions);
        return this;
    }

    public GlideBuilder setMemorySizeCalculator(MemorySizeCalculator.Builder builder) {
        return setMemorySizeCalculator(builder.build());
    }

    public GlideBuilder setMemorySizeCalculator(MemorySizeCalculator memorySizeCalculator2) {
        this.memorySizeCalculator = memorySizeCalculator2;
        return this;
    }

    public GlideBuilder setConnectivityMonitorFactory(ConnectivityMonitorFactory connectivityMonitorFactory2) {
        this.connectivityMonitorFactory = connectivityMonitorFactory2;
        return this;
    }

    public GlideBuilder setLogLevel(int i) {
        if (i < 2 || i > 6) {
            throw new IllegalArgumentException("Log level must be one of Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, or Log.ERROR");
        }
        this.logLevel = i;
        return this;
    }

    public GlideBuilder setIsActiveResourceRetentionAllowed(boolean z) {
        this.isActiveResourceRetentionAllowed = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setRequestManagerFactory(RequestManagerRetriever.RequestManagerFactory requestManagerFactory2) {
        this.requestManagerFactory = requestManagerFactory2;
    }

    /* access modifiers changed from: package-private */
    public GlideBuilder setEngine(Engine engine2) {
        this.engine = engine2;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Glide build(Context context) {
        if (this.sourceExecutor == null) {
            this.sourceExecutor = GlideExecutor.newSourceExecutor();
        }
        if (this.diskCacheExecutor == null) {
            this.diskCacheExecutor = GlideExecutor.newDiskCacheExecutor();
        }
        if (this.animationExecutor == null) {
            this.animationExecutor = GlideExecutor.newAnimationExecutor();
        }
        if (this.memorySizeCalculator == null) {
            this.memorySizeCalculator = new MemorySizeCalculator.Builder(context).build();
        }
        if (this.connectivityMonitorFactory == null) {
            this.connectivityMonitorFactory = new DefaultConnectivityMonitorFactory();
        }
        if (this.bitmapPool == null) {
            int bitmapPoolSize = this.memorySizeCalculator.getBitmapPoolSize();
            if (bitmapPoolSize > 0) {
                this.bitmapPool = new LruBitmapPool((long) bitmapPoolSize);
            } else {
                this.bitmapPool = new BitmapPoolAdapter();
            }
        }
        if (this.arrayPool == null) {
            this.arrayPool = new LruArrayPool(this.memorySizeCalculator.getArrayPoolSizeInBytes());
        }
        if (this.memoryCache == null) {
            this.memoryCache = new LruResourceCache((long) this.memorySizeCalculator.getMemoryCacheSize());
        }
        if (this.diskCacheFactory == null) {
            this.diskCacheFactory = new InternalCacheDiskCacheFactory(context);
        }
        if (this.engine == null) {
            this.engine = new Engine(this.memoryCache, this.diskCacheFactory, this.diskCacheExecutor, this.sourceExecutor, GlideExecutor.newUnlimitedSourceExecutor(), GlideExecutor.newAnimationExecutor(), this.isActiveResourceRetentionAllowed);
        }
        return new Glide(context, this.engine, this.memoryCache, this.bitmapPool, this.arrayPool, new RequestManagerRetriever(this.requestManagerFactory), this.connectivityMonitorFactory, this.logLevel, this.defaultRequestOptions.lock(), this.defaultTransitionOptions);
    }
}
