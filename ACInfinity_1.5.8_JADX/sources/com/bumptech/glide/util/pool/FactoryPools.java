package com.bumptech.glide.util.pool;

import android.util.Log;
import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.List;

public final class FactoryPools {
    private static final int DEFAULT_POOL_SIZE = 20;
    private static final Resetter<Object> EMPTY_RESETTER = new Resetter<Object>() {
        public void reset(Object obj) {
        }
    };
    private static final String TAG = "FactoryPools";

    public interface Factory<T> {
        T create();
    }

    public interface Poolable {
        StateVerifier getVerifier();
    }

    public interface Resetter<T> {
        void reset(T t);
    }

    private FactoryPools() {
    }

    public static <T extends Poolable> Pools.Pool<T> simple(int i, Factory<T> factory) {
        return build(new Pools.SimplePool(i), factory);
    }

    public static <T extends Poolable> Pools.Pool<T> threadSafe(int i, Factory<T> factory) {
        return build(new Pools.SynchronizedPool(i), factory);
    }

    public static <T> Pools.Pool<List<T>> threadSafeList() {
        return threadSafeList(20);
    }

    public static <T> Pools.Pool<List<T>> threadSafeList(int i) {
        return build(new Pools.SynchronizedPool(i), new Factory<List<T>>() {
            public List<T> create() {
                return new ArrayList();
            }
        }, new Resetter<List<T>>() {
            public void reset(List<T> list) {
                list.clear();
            }
        });
    }

    private static <T extends Poolable> Pools.Pool<T> build(Pools.Pool<T> pool, Factory<T> factory) {
        return build(pool, factory, emptyResetter());
    }

    private static <T> Pools.Pool<T> build(Pools.Pool<T> pool, Factory<T> factory, Resetter<T> resetter) {
        return new FactoryPool(pool, factory, resetter);
    }

    private static <T> Resetter<T> emptyResetter() {
        return EMPTY_RESETTER;
    }

    private static final class FactoryPool<T> implements Pools.Pool<T> {
        private final Factory<T> factory;
        private final Pools.Pool<T> pool;
        private final Resetter<T> resetter;

        FactoryPool(Pools.Pool<T> pool2, Factory<T> factory2, Resetter<T> resetter2) {
            this.pool = pool2;
            this.factory = factory2;
            this.resetter = resetter2;
        }

        public T acquire() {
            T acquire = this.pool.acquire();
            if (acquire == null) {
                acquire = this.factory.create();
                if (Log.isLoggable(FactoryPools.TAG, 2)) {
                    Log.v(FactoryPools.TAG, "Created new " + acquire.getClass());
                }
            }
            if (acquire instanceof Poolable) {
                ((Poolable) acquire).getVerifier().setRecycled(false);
            }
            return acquire;
        }

        public boolean release(T t) {
            if (t instanceof Poolable) {
                ((Poolable) t).getVerifier().setRecycled(true);
            }
            this.resetter.reset(t);
            return this.pool.release(t);
        }
    }
}
