package com.bumptech.glide.load.model;

import androidx.core.util.Pools;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.Preconditions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MultiModelLoaderFactory {
    private static final Factory DEFAULT_FACTORY = new Factory();
    private static final ModelLoader<Object, Object> EMPTY_MODEL_LOADER = new EmptyModelLoader();
    private final Set<Entry<?, ?>> alreadyUsedEntries;
    private final List<Entry<?, ?>> entries;
    private final Factory factory;
    private final Pools.Pool<List<Throwable>> throwableListPool;

    public MultiModelLoaderFactory(Pools.Pool<List<Throwable>> pool) {
        this(pool, DEFAULT_FACTORY);
    }

    MultiModelLoaderFactory(Pools.Pool<List<Throwable>> pool, Factory factory2) {
        this.entries = new ArrayList();
        this.alreadyUsedEntries = new HashSet();
        this.throwableListPool = pool;
        this.factory = factory2;
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model, Data> void append(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        add(cls, cls2, modelLoaderFactory, true);
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model, Data> void prepend(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        add(cls, cls2, modelLoaderFactory, false);
    }

    private <Model, Data> void add(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory, boolean z) {
        Entry entry = new Entry(cls, cls2, modelLoaderFactory);
        List<Entry<?, ?>> list = this.entries;
        list.add(z ? list.size() : 0, entry);
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> replace(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        List<ModelLoaderFactory<? extends Model, ? extends Data>> remove;
        remove = remove(cls, cls2);
        append(cls, cls2, modelLoaderFactory);
        return remove;
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model, Data> List<ModelLoaderFactory<? extends Model, ? extends Data>> remove(Class<Model> cls, Class<Data> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<Entry<?, ?>> it = this.entries.iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            if (next.handles(cls, cls2)) {
                it.remove();
                arrayList.add(getFactory(next));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model> List<ModelLoader<Model, ?>> build(Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (Entry next : this.entries) {
                if (!this.alreadyUsedEntries.contains(next)) {
                    if (next.handles(cls)) {
                        this.alreadyUsedEntries.add(next);
                        arrayList.add(build((Entry<?, ?>) next));
                        this.alreadyUsedEntries.remove(next);
                    }
                }
            }
        } catch (Throwable th) {
            this.alreadyUsedEntries.clear();
            throw th;
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public synchronized List<Class<?>> getDataClasses(Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (Entry next : this.entries) {
            if (!arrayList.contains(next.dataClass) && next.handles(cls)) {
                arrayList.add(next.dataClass);
            }
        }
        return arrayList;
    }

    public synchronized <Model, Data> ModelLoader<Model, Data> build(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (Entry next : this.entries) {
                if (this.alreadyUsedEntries.contains(next)) {
                    z = true;
                } else if (next.handles(cls, cls2)) {
                    this.alreadyUsedEntries.add(next);
                    arrayList.add(build((Entry<?, ?>) next));
                    this.alreadyUsedEntries.remove(next);
                }
            }
            if (arrayList.size() > 1) {
                return this.factory.build(arrayList, this.throwableListPool);
            } else if (arrayList.size() == 1) {
                return (ModelLoader) arrayList.get(0);
            } else if (z) {
                return emptyModelLoader();
            } else {
                throw new Registry.NoModelLoaderAvailableException(cls, cls2);
            }
        } catch (Throwable th) {
            this.alreadyUsedEntries.clear();
            throw th;
        }
    }

    private <Model, Data> ModelLoaderFactory<Model, Data> getFactory(Entry<?, ?> entry) {
        return entry.factory;
    }

    private <Model, Data> ModelLoader<Model, Data> build(Entry<?, ?> entry) {
        return (ModelLoader) Preconditions.checkNotNull(entry.factory.build(this));
    }

    private static <Model, Data> ModelLoader<Model, Data> emptyModelLoader() {
        return EMPTY_MODEL_LOADER;
    }

    private static class Entry<Model, Data> {
        final Class<Data> dataClass;
        final ModelLoaderFactory<? extends Model, ? extends Data> factory;
        private final Class<Model> modelClass;

        public Entry(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
            this.modelClass = cls;
            this.dataClass = cls2;
            this.factory = modelLoaderFactory;
        }

        public boolean handles(Class<?> cls, Class<?> cls2) {
            return handles(cls) && this.dataClass.isAssignableFrom(cls2);
        }

        public boolean handles(Class<?> cls) {
            return this.modelClass.isAssignableFrom(cls);
        }
    }

    static class Factory {
        Factory() {
        }

        public <Model, Data> MultiModelLoader<Model, Data> build(List<ModelLoader<Model, Data>> list, Pools.Pool<List<Throwable>> pool) {
            return new MultiModelLoader<>(list, pool);
        }
    }

    private static class EmptyModelLoader implements ModelLoader<Object, Object> {
        public ModelLoader.LoadData<Object> buildLoadData(Object obj, int i, int i2, Options options) {
            return null;
        }

        public boolean handles(Object obj) {
            return false;
        }

        EmptyModelLoader() {
        }
    }
}
