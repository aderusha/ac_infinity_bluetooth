package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.util.Pools;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.List;

class EngineJob<R> implements DecodeJob.Callback<R>, FactoryPools.Poolable {
    private static final EngineResourceFactory DEFAULT_FACTORY = new EngineResourceFactory();
    private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper(), new MainThreadCallback());
    private static final int MSG_CANCELLED = 3;
    private static final int MSG_COMPLETE = 1;
    private static final int MSG_EXCEPTION = 2;
    private final GlideExecutor animationExecutor;
    private final List<ResourceCallback> cbs;
    private DataSource dataSource;
    private DecodeJob<R> decodeJob;
    private final GlideExecutor diskCacheExecutor;
    private EngineResource<?> engineResource;
    private final EngineResourceFactory engineResourceFactory;
    private GlideException exception;
    private boolean hasLoadFailed;
    private boolean hasResource;
    private List<ResourceCallback> ignoredCallbacks;
    private boolean isCacheable;
    private volatile boolean isCancelled;
    private Key key;
    private final EngineJobListener listener;
    private boolean onlyRetrieveFromCache;
    private final Pools.Pool<EngineJob<?>> pool;
    private Resource<?> resource;
    private final GlideExecutor sourceExecutor;
    private final GlideExecutor sourceUnlimitedExecutor;
    private final StateVerifier stateVerifier;
    private boolean useAnimationPool;
    private boolean useUnlimitedSourceGeneratorPool;

    EngineJob(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, EngineJobListener engineJobListener, Pools.Pool<EngineJob<?>> pool2) {
        this(glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, engineJobListener, pool2, DEFAULT_FACTORY);
    }

    EngineJob(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, EngineJobListener engineJobListener, Pools.Pool<EngineJob<?>> pool2, EngineResourceFactory engineResourceFactory2) {
        this.cbs = new ArrayList(2);
        this.stateVerifier = StateVerifier.newInstance();
        this.diskCacheExecutor = glideExecutor;
        this.sourceExecutor = glideExecutor2;
        this.sourceUnlimitedExecutor = glideExecutor3;
        this.animationExecutor = glideExecutor4;
        this.listener = engineJobListener;
        this.pool = pool2;
        this.engineResourceFactory = engineResourceFactory2;
    }

    /* access modifiers changed from: package-private */
    public EngineJob<R> init(Key key2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.key = key2;
        this.isCacheable = z;
        this.useUnlimitedSourceGeneratorPool = z2;
        this.useAnimationPool = z3;
        this.onlyRetrieveFromCache = z4;
        return this;
    }

    public void start(DecodeJob<R> decodeJob2) {
        GlideExecutor glideExecutor;
        this.decodeJob = decodeJob2;
        if (decodeJob2.willDecodeFromCache()) {
            glideExecutor = this.diskCacheExecutor;
        } else {
            glideExecutor = getActiveSourceExecutor();
        }
        glideExecutor.execute(decodeJob2);
    }

    /* access modifiers changed from: package-private */
    public void addCallback(ResourceCallback resourceCallback) {
        Util.assertMainThread();
        this.stateVerifier.throwIfRecycled();
        if (this.hasResource) {
            resourceCallback.onResourceReady(this.engineResource, this.dataSource);
        } else if (this.hasLoadFailed) {
            resourceCallback.onLoadFailed(this.exception);
        } else {
            this.cbs.add(resourceCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public void removeCallback(ResourceCallback resourceCallback) {
        Util.assertMainThread();
        this.stateVerifier.throwIfRecycled();
        if (this.hasResource || this.hasLoadFailed) {
            addIgnoredCallback(resourceCallback);
            return;
        }
        this.cbs.remove(resourceCallback);
        if (this.cbs.isEmpty()) {
            cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onlyRetrieveFromCache() {
        return this.onlyRetrieveFromCache;
    }

    private GlideExecutor getActiveSourceExecutor() {
        if (this.useUnlimitedSourceGeneratorPool) {
            return this.sourceUnlimitedExecutor;
        }
        return this.useAnimationPool ? this.animationExecutor : this.sourceExecutor;
    }

    private void addIgnoredCallback(ResourceCallback resourceCallback) {
        if (this.ignoredCallbacks == null) {
            this.ignoredCallbacks = new ArrayList(2);
        }
        if (!this.ignoredCallbacks.contains(resourceCallback)) {
            this.ignoredCallbacks.add(resourceCallback);
        }
    }

    private boolean isInIgnoredCallbacks(ResourceCallback resourceCallback) {
        List<ResourceCallback> list = this.ignoredCallbacks;
        return list != null && list.contains(resourceCallback);
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        if (!this.hasLoadFailed && !this.hasResource && !this.isCancelled) {
            this.isCancelled = true;
            this.decodeJob.cancel();
            this.listener.onEngineJobCancelled(this, this.key);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCancelled() {
        return this.isCancelled;
    }

    /* access modifiers changed from: package-private */
    public void handleResultOnMainThread() {
        this.stateVerifier.throwIfRecycled();
        if (this.isCancelled) {
            this.resource.recycle();
            release(false);
        } else if (this.cbs.isEmpty()) {
            throw new IllegalStateException("Received a resource without any callbacks to notify");
        } else if (!this.hasResource) {
            EngineResource<?> build = this.engineResourceFactory.build(this.resource, this.isCacheable);
            this.engineResource = build;
            this.hasResource = true;
            build.acquire();
            this.listener.onEngineJobComplete(this, this.key, this.engineResource);
            int size = this.cbs.size();
            for (int i = 0; i < size; i++) {
                ResourceCallback resourceCallback = this.cbs.get(i);
                if (!isInIgnoredCallbacks(resourceCallback)) {
                    this.engineResource.acquire();
                    resourceCallback.onResourceReady(this.engineResource, this.dataSource);
                }
            }
            this.engineResource.release();
            release(false);
        } else {
            throw new IllegalStateException("Already have resource");
        }
    }

    /* access modifiers changed from: package-private */
    public void handleCancelledOnMainThread() {
        this.stateVerifier.throwIfRecycled();
        if (this.isCancelled) {
            this.listener.onEngineJobCancelled(this, this.key);
            release(false);
            return;
        }
        throw new IllegalStateException("Not cancelled");
    }

    private void release(boolean z) {
        Util.assertMainThread();
        this.cbs.clear();
        this.key = null;
        this.engineResource = null;
        this.resource = null;
        List<ResourceCallback> list = this.ignoredCallbacks;
        if (list != null) {
            list.clear();
        }
        this.hasLoadFailed = false;
        this.isCancelled = false;
        this.hasResource = false;
        this.decodeJob.release(z);
        this.decodeJob = null;
        this.exception = null;
        this.dataSource = null;
        this.pool.release(this);
    }

    public void onResourceReady(Resource<R> resource2, DataSource dataSource2) {
        this.resource = resource2;
        this.dataSource = dataSource2;
        MAIN_THREAD_HANDLER.obtainMessage(1, this).sendToTarget();
    }

    public void onLoadFailed(GlideException glideException) {
        this.exception = glideException;
        MAIN_THREAD_HANDLER.obtainMessage(2, this).sendToTarget();
    }

    public void reschedule(DecodeJob<?> decodeJob2) {
        getActiveSourceExecutor().execute(decodeJob2);
    }

    /* access modifiers changed from: package-private */
    public void handleExceptionOnMainThread() {
        this.stateVerifier.throwIfRecycled();
        if (this.isCancelled) {
            release(false);
        } else if (this.cbs.isEmpty()) {
            throw new IllegalStateException("Received an exception without any callbacks to notify");
        } else if (!this.hasLoadFailed) {
            this.hasLoadFailed = true;
            this.listener.onEngineJobComplete(this, this.key, (EngineResource<?>) null);
            for (ResourceCallback next : this.cbs) {
                if (!isInIgnoredCallbacks(next)) {
                    next.onLoadFailed(this.exception);
                }
            }
            release(false);
        } else {
            throw new IllegalStateException("Already failed once");
        }
    }

    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    static class EngineResourceFactory {
        EngineResourceFactory() {
        }

        public <R> EngineResource<R> build(Resource<R> resource, boolean z) {
            return new EngineResource<>(resource, z, true);
        }
    }

    private static class MainThreadCallback implements Handler.Callback {
        MainThreadCallback() {
        }

        public boolean handleMessage(Message message) {
            EngineJob engineJob = (EngineJob) message.obj;
            int i = message.what;
            if (i == 1) {
                engineJob.handleResultOnMainThread();
            } else if (i == 2) {
                engineJob.handleExceptionOnMainThread();
            } else if (i == 3) {
                engineJob.handleCancelledOnMainThread();
            } else {
                throw new IllegalStateException("Unrecognized message: " + message.what);
            }
            return true;
        }
    }
}
