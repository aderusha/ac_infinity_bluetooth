package com.bumptech.glide.load.engine;

import android.util.Log;
import androidx.core.util.Pools;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.EngineResource;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Engine implements EngineJobListener, MemoryCache.ResourceRemovedListener, EngineResource.ResourceListener {
    private static final int JOB_POOL_SIZE = 150;
    private static final String TAG = "Engine";
    private static final boolean VERBOSE_IS_LOGGABLE = Log.isLoggable(TAG, 2);
    private final ActiveResources activeResources;
    private final MemoryCache cache;
    private final DecodeJobFactory decodeJobFactory;
    private final LazyDiskCacheProvider diskCacheProvider;
    private final EngineJobFactory engineJobFactory;
    private final Jobs jobs;
    private final EngineKeyFactory keyFactory;
    private final ResourceRecycler resourceRecycler;

    public Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, boolean z) {
        this(memoryCache, factory, glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, (Jobs) null, (EngineKeyFactory) null, (ActiveResources) null, (EngineJobFactory) null, (DecodeJobFactory) null, (ResourceRecycler) null, z);
    }

    Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, Jobs jobs2, EngineKeyFactory engineKeyFactory, ActiveResources activeResources2, EngineJobFactory engineJobFactory2, DecodeJobFactory decodeJobFactory2, ResourceRecycler resourceRecycler2, boolean z) {
        this.cache = memoryCache;
        DiskCache.Factory factory2 = factory;
        LazyDiskCacheProvider lazyDiskCacheProvider = new LazyDiskCacheProvider(factory);
        this.diskCacheProvider = lazyDiskCacheProvider;
        ActiveResources activeResources3 = activeResources2 == null ? new ActiveResources(z) : activeResources2;
        this.activeResources = activeResources3;
        activeResources3.setListener(this);
        this.keyFactory = engineKeyFactory == null ? new EngineKeyFactory() : engineKeyFactory;
        this.jobs = jobs2 == null ? new Jobs() : jobs2;
        this.engineJobFactory = engineJobFactory2 == null ? new EngineJobFactory(glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, this) : engineJobFactory2;
        this.decodeJobFactory = decodeJobFactory2 == null ? new DecodeJobFactory(lazyDiskCacheProvider) : decodeJobFactory2;
        this.resourceRecycler = resourceRecycler2 == null ? new ResourceRecycler() : resourceRecycler2;
        memoryCache.setResourceRemovedListener(this);
    }

    public <R> LoadStatus load(GlideContext glideContext, Object obj, Key key, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, Options options, boolean z3, boolean z4, boolean z5, boolean z6, ResourceCallback resourceCallback) {
        boolean z7 = z3;
        ResourceCallback resourceCallback2 = resourceCallback;
        Util.assertMainThread();
        boolean z8 = VERBOSE_IS_LOGGABLE;
        long logTime = z8 ? LogTime.getLogTime() : 0;
        EngineKey buildKey = this.keyFactory.buildKey(obj, key, i, i2, map, cls, cls2, options);
        EngineResource<?> loadFromActiveResources = loadFromActiveResources(buildKey, z7);
        if (loadFromActiveResources != null) {
            resourceCallback2.onResourceReady(loadFromActiveResources, DataSource.MEMORY_CACHE);
            if (z8) {
                logWithTimeAndKey("Loaded resource from active resources", logTime, buildKey);
            }
            return null;
        }
        EngineResource<?> loadFromCache = loadFromCache(buildKey, z7);
        if (loadFromCache != null) {
            resourceCallback2.onResourceReady(loadFromCache, DataSource.MEMORY_CACHE);
            if (z8) {
                logWithTimeAndKey("Loaded resource from cache", logTime, buildKey);
            }
            return null;
        }
        EngineJob<?> engineJob = this.jobs.get(buildKey, z6);
        if (engineJob != null) {
            engineJob.addCallback(resourceCallback2);
            if (z8) {
                logWithTimeAndKey("Added to existing load", logTime, buildKey);
            }
            return new LoadStatus(resourceCallback2, engineJob);
        }
        EngineJob build = this.engineJobFactory.build(buildKey, z3, z4, z5, z6);
        DecodeJob<R> build2 = this.decodeJobFactory.build(glideContext, obj, buildKey, key, i, i2, cls, cls2, priority, diskCacheStrategy, map, z, z2, z6, options, build);
        this.jobs.put(buildKey, build);
        build.addCallback(resourceCallback2);
        build.start(build2);
        if (z8) {
            logWithTimeAndKey("Started new load", logTime, buildKey);
        }
        return new LoadStatus(resourceCallback2, build);
    }

    private static void logWithTimeAndKey(String str, long j, Key key) {
        Log.v(TAG, str + " in " + LogTime.getElapsedMillis(j) + "ms, key: " + key);
    }

    private EngineResource<?> loadFromActiveResources(Key key, boolean z) {
        if (!z) {
            return null;
        }
        EngineResource<?> engineResource = this.activeResources.get(key);
        if (engineResource != null) {
            engineResource.acquire();
        }
        return engineResource;
    }

    private EngineResource<?> loadFromCache(Key key, boolean z) {
        if (!z) {
            return null;
        }
        EngineResource<?> engineResourceFromCache = getEngineResourceFromCache(key);
        if (engineResourceFromCache != null) {
            engineResourceFromCache.acquire();
            this.activeResources.activate(key, engineResourceFromCache);
        }
        return engineResourceFromCache;
    }

    private EngineResource<?> getEngineResourceFromCache(Key key) {
        Resource<?> remove = this.cache.remove(key);
        if (remove == null) {
            return null;
        }
        if (remove instanceof EngineResource) {
            return (EngineResource) remove;
        }
        return new EngineResource<>(remove, true, true);
    }

    public void release(Resource<?> resource) {
        Util.assertMainThread();
        if (resource instanceof EngineResource) {
            ((EngineResource) resource).release();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    public void onEngineJobComplete(EngineJob<?> engineJob, Key key, EngineResource<?> engineResource) {
        Util.assertMainThread();
        if (engineResource != null) {
            engineResource.setResourceListener(key, this);
            if (engineResource.isCacheable()) {
                this.activeResources.activate(key, engineResource);
            }
        }
        this.jobs.removeIfCurrent(key, engineJob);
    }

    public void onEngineJobCancelled(EngineJob<?> engineJob, Key key) {
        Util.assertMainThread();
        this.jobs.removeIfCurrent(key, engineJob);
    }

    public void onResourceRemoved(Resource<?> resource) {
        Util.assertMainThread();
        this.resourceRecycler.recycle(resource);
    }

    public void onResourceReleased(Key key, EngineResource<?> engineResource) {
        Util.assertMainThread();
        this.activeResources.deactivate(key);
        if (engineResource.isCacheable()) {
            this.cache.put(key, engineResource);
        } else {
            this.resourceRecycler.recycle(engineResource);
        }
    }

    public void clearDiskCache() {
        this.diskCacheProvider.getDiskCache().clear();
    }

    public void shutdown() {
        this.engineJobFactory.shutdown();
        this.diskCacheProvider.clearDiskCacheIfCreated();
        this.activeResources.shutdown();
    }

    public static class LoadStatus {

        /* renamed from: cb */
        private final ResourceCallback f86cb;
        private final EngineJob<?> engineJob;

        LoadStatus(ResourceCallback resourceCallback, EngineJob<?> engineJob2) {
            this.f86cb = resourceCallback;
            this.engineJob = engineJob2;
        }

        public void cancel() {
            this.engineJob.removeCallback(this.f86cb);
        }
    }

    private static class LazyDiskCacheProvider implements DecodeJob.DiskCacheProvider {
        private volatile DiskCache diskCache;
        private final DiskCache.Factory factory;

        LazyDiskCacheProvider(DiskCache.Factory factory2) {
            this.factory = factory2;
        }

        /* access modifiers changed from: package-private */
        public synchronized void clearDiskCacheIfCreated() {
            if (this.diskCache != null) {
                this.diskCache.clear();
            }
        }

        public DiskCache getDiskCache() {
            if (this.diskCache == null) {
                synchronized (this) {
                    if (this.diskCache == null) {
                        this.diskCache = this.factory.build();
                    }
                    if (this.diskCache == null) {
                        this.diskCache = new DiskCacheAdapter();
                    }
                }
            }
            return this.diskCache;
        }
    }

    static class DecodeJobFactory {
        private int creationOrder;
        final DecodeJob.DiskCacheProvider diskCacheProvider;
        final Pools.Pool<DecodeJob<?>> pool = FactoryPools.simple(Engine.JOB_POOL_SIZE, new FactoryPools.Factory<DecodeJob<?>>() {
            public DecodeJob<?> create() {
                return new DecodeJob<>(DecodeJobFactory.this.diskCacheProvider, DecodeJobFactory.this.pool);
            }
        });

        DecodeJobFactory(DecodeJob.DiskCacheProvider diskCacheProvider2) {
            this.diskCacheProvider = diskCacheProvider2;
        }

        /* access modifiers changed from: package-private */
        public <R> DecodeJob<R> build(GlideContext glideContext, Object obj, EngineKey engineKey, Key key, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, boolean z3, Options options, DecodeJob.Callback<R> callback) {
            DecodeJob decodeJob = (DecodeJob) Preconditions.checkNotNull(this.pool.acquire());
            int i3 = this.creationOrder;
            int i4 = i3;
            this.creationOrder = i3 + 1;
            return decodeJob.init(glideContext, obj, engineKey, key, i, i2, cls, cls2, priority, diskCacheStrategy, map, z, z2, z3, options, callback, i4);
        }
    }

    static class EngineJobFactory {
        final GlideExecutor animationExecutor;
        final GlideExecutor diskCacheExecutor;
        final EngineJobListener listener;
        final Pools.Pool<EngineJob<?>> pool = FactoryPools.simple(Engine.JOB_POOL_SIZE, new FactoryPools.Factory<EngineJob<?>>() {
            public EngineJob<?> create() {
                return new EngineJob(EngineJobFactory.this.diskCacheExecutor, EngineJobFactory.this.sourceExecutor, EngineJobFactory.this.sourceUnlimitedExecutor, EngineJobFactory.this.animationExecutor, EngineJobFactory.this.listener, EngineJobFactory.this.pool);
            }
        });
        final GlideExecutor sourceExecutor;
        final GlideExecutor sourceUnlimitedExecutor;

        EngineJobFactory(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, EngineJobListener engineJobListener) {
            this.diskCacheExecutor = glideExecutor;
            this.sourceExecutor = glideExecutor2;
            this.sourceUnlimitedExecutor = glideExecutor3;
            this.animationExecutor = glideExecutor4;
            this.listener = engineJobListener;
        }

        /* access modifiers changed from: package-private */
        public void shutdown() {
            shutdownAndAwaitTermination(this.diskCacheExecutor);
            shutdownAndAwaitTermination(this.sourceExecutor);
            shutdownAndAwaitTermination(this.sourceUnlimitedExecutor);
            shutdownAndAwaitTermination(this.animationExecutor);
        }

        /* access modifiers changed from: package-private */
        public <R> EngineJob<R> build(Key key, boolean z, boolean z2, boolean z3, boolean z4) {
            return ((EngineJob) Preconditions.checkNotNull(this.pool.acquire())).init(key, z, z2, z3, z4);
        }

        private static void shutdownAndAwaitTermination(ExecutorService executorService) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                        throw new RuntimeException("Failed to shutdown");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
