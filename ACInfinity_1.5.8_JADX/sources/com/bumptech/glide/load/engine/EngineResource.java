package com.bumptech.glide.load.engine;

import android.os.Looper;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;

class EngineResource<Z> implements Resource<Z> {
    private int acquired;
    private final boolean isCacheable;
    private final boolean isRecyclable;
    private boolean isRecycled;
    private Key key;
    private ResourceListener listener;
    private final Resource<Z> resource;

    interface ResourceListener {
        void onResourceReleased(Key key, EngineResource<?> engineResource);
    }

    EngineResource(Resource<Z> resource2, boolean z, boolean z2) {
        this.resource = (Resource) Preconditions.checkNotNull(resource2);
        this.isCacheable = z;
        this.isRecyclable = z2;
    }

    /* access modifiers changed from: package-private */
    public void setResourceListener(Key key2, ResourceListener resourceListener) {
        this.key = key2;
        this.listener = resourceListener;
    }

    /* access modifiers changed from: package-private */
    public Resource<Z> getResource() {
        return this.resource;
    }

    /* access modifiers changed from: package-private */
    public boolean isCacheable() {
        return this.isCacheable;
    }

    public Class<Z> getResourceClass() {
        return this.resource.getResourceClass();
    }

    public Z get() {
        return this.resource.get();
    }

    public int getSize() {
        return this.resource.getSize();
    }

    public void recycle() {
        if (this.acquired > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        } else if (!this.isRecycled) {
            this.isRecycled = true;
            if (this.isRecyclable) {
                this.resource.recycle();
            }
        } else {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
    }

    /* access modifiers changed from: package-private */
    public void acquire() {
        if (this.isRecycled) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        } else if (Looper.getMainLooper().equals(Looper.myLooper())) {
            this.acquired++;
        } else {
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        }
    }

    /* access modifiers changed from: package-private */
    public void release() {
        if (this.acquired <= 0) {
            throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
        } else if (Looper.getMainLooper().equals(Looper.myLooper())) {
            int i = this.acquired - 1;
            this.acquired = i;
            if (i == 0) {
                this.listener.onResourceReleased(this.key, this);
            }
        } else {
            throw new IllegalThreadStateException("Must call release on the main thread");
        }
    }

    public String toString() {
        return "EngineResource{isCacheable=" + this.isCacheable + ", listener=" + this.listener + ", key=" + this.key + ", acquired=" + this.acquired + ", isRecycled=" + this.isRecycled + ", resource=" + this.resource + '}';
    }
}
