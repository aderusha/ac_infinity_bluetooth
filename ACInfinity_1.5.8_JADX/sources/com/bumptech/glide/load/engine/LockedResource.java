package com.bumptech.glide.load.engine;

import androidx.core.util.Pools;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.StateVerifier;

final class LockedResource<Z> implements Resource<Z>, FactoryPools.Poolable {
    private static final Pools.Pool<LockedResource<?>> POOL = FactoryPools.threadSafe(20, new FactoryPools.Factory<LockedResource<?>>() {
        public LockedResource<?> create() {
            return new LockedResource<>();
        }
    });
    private boolean isLocked;
    private boolean isRecycled;
    private final StateVerifier stateVerifier = StateVerifier.newInstance();
    private Resource<Z> toWrap;

    static <Z> LockedResource<Z> obtain(Resource<Z> resource) {
        LockedResource<Z> lockedResource = (LockedResource) Preconditions.checkNotNull(POOL.acquire());
        lockedResource.init(resource);
        return lockedResource;
    }

    LockedResource() {
    }

    private void init(Resource<Z> resource) {
        this.isRecycled = false;
        this.isLocked = true;
        this.toWrap = resource;
    }

    private void release() {
        this.toWrap = null;
        POOL.release(this);
    }

    /* access modifiers changed from: package-private */
    public synchronized void unlock() {
        this.stateVerifier.throwIfRecycled();
        if (this.isLocked) {
            this.isLocked = false;
            if (this.isRecycled) {
                recycle();
            }
        } else {
            throw new IllegalStateException("Already unlocked");
        }
    }

    public Class<Z> getResourceClass() {
        return this.toWrap.getResourceClass();
    }

    public Z get() {
        return this.toWrap.get();
    }

    public int getSize() {
        return this.toWrap.getSize();
    }

    public synchronized void recycle() {
        this.stateVerifier.throwIfRecycled();
        this.isRecycled = true;
        if (!this.isLocked) {
            this.toWrap.recycle();
            release();
        }
    }

    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }
}
