package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.EngineResource;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

final class ActiveResources {
    private static final int MSG_CLEAN_REF = 1;
    final Map<Key, ResourceWeakReference> activeEngineResources = new HashMap();

    /* renamed from: cb */
    private volatile DequeuedResourceCallback f84cb;
    private Thread cleanReferenceQueueThread;
    private final boolean isActiveResourceRetentionAllowed;
    private volatile boolean isShutdown;
    private EngineResource.ResourceListener listener;
    private final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ActiveResources.this.cleanupActiveReference((ResourceWeakReference) message.obj);
            return true;
        }
    });
    private ReferenceQueue<EngineResource<?>> resourceReferenceQueue;

    interface DequeuedResourceCallback {
        void onResourceDequeued();
    }

    ActiveResources(boolean z) {
        this.isActiveResourceRetentionAllowed = z;
    }

    /* access modifiers changed from: package-private */
    public void setListener(EngineResource.ResourceListener resourceListener) {
        this.listener = resourceListener;
    }

    /* access modifiers changed from: package-private */
    public void activate(Key key, EngineResource<?> engineResource) {
        ResourceWeakReference put = this.activeEngineResources.put(key, new ResourceWeakReference(key, engineResource, getReferenceQueue(), this.isActiveResourceRetentionAllowed));
        if (put != null) {
            put.reset();
        }
    }

    /* access modifiers changed from: package-private */
    public void deactivate(Key key) {
        ResourceWeakReference remove = this.activeEngineResources.remove(key);
        if (remove != null) {
            remove.reset();
        }
    }

    /* access modifiers changed from: package-private */
    public EngineResource<?> get(Key key) {
        ResourceWeakReference resourceWeakReference = this.activeEngineResources.get(key);
        if (resourceWeakReference == null) {
            return null;
        }
        EngineResource<?> engineResource = (EngineResource) resourceWeakReference.get();
        if (engineResource == null) {
            cleanupActiveReference(resourceWeakReference);
        }
        return engineResource;
    }

    /* access modifiers changed from: package-private */
    public void cleanupActiveReference(ResourceWeakReference resourceWeakReference) {
        Util.assertMainThread();
        this.activeEngineResources.remove(resourceWeakReference.key);
        if (resourceWeakReference.isCacheable && resourceWeakReference.resource != null) {
            EngineResource engineResource = new EngineResource(resourceWeakReference.resource, true, false);
            engineResource.setResourceListener(resourceWeakReference.key, this.listener);
            this.listener.onResourceReleased(resourceWeakReference.key, engineResource);
        }
    }

    private ReferenceQueue<EngineResource<?>> getReferenceQueue() {
        if (this.resourceReferenceQueue == null) {
            this.resourceReferenceQueue = new ReferenceQueue<>();
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Process.setThreadPriority(10);
                    ActiveResources.this.cleanReferenceQueue();
                }
            }, "glide-active-resources");
            this.cleanReferenceQueueThread = thread;
            thread.start();
        }
        return this.resourceReferenceQueue;
    }

    /* access modifiers changed from: package-private */
    public void cleanReferenceQueue() {
        while (!this.isShutdown) {
            try {
                this.mainHandler.obtainMessage(1, (ResourceWeakReference) this.resourceReferenceQueue.remove()).sendToTarget();
                DequeuedResourceCallback dequeuedResourceCallback = this.f84cb;
                if (dequeuedResourceCallback != null) {
                    dequeuedResourceCallback.onResourceDequeued();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setDequeuedResourceCallback(DequeuedResourceCallback dequeuedResourceCallback) {
        this.f84cb = dequeuedResourceCallback;
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        this.isShutdown = true;
        Thread thread = this.cleanReferenceQueueThread;
        if (thread != null) {
            thread.interrupt();
            try {
                this.cleanReferenceQueueThread.join(TimeUnit.SECONDS.toMillis(5));
                if (this.cleanReferenceQueueThread.isAlive()) {
                    throw new RuntimeException("Failed to join in time");
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static final class ResourceWeakReference extends WeakReference<EngineResource<?>> {
        final boolean isCacheable;
        final Key key;
        Resource<?> resource;

        ResourceWeakReference(Key key2, EngineResource<?> engineResource, ReferenceQueue<? super EngineResource<?>> referenceQueue, boolean z) {
            super(engineResource, referenceQueue);
            this.key = (Key) Preconditions.checkNotNull(key2);
            this.resource = (!engineResource.isCacheable() || !z) ? null : (Resource) Preconditions.checkNotNull(engineResource.getResource());
            this.isCacheable = engineResource.isCacheable();
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.resource = null;
            clear();
        }
    }
}
