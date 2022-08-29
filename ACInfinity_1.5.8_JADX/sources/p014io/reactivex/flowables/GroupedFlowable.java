package p014io.reactivex.flowables;

import p014io.reactivex.Flowable;

/* renamed from: io.reactivex.flowables.GroupedFlowable */
public abstract class GroupedFlowable<K, T> extends Flowable<T> {
    final K key;

    protected GroupedFlowable(K k) {
        this.key = k;
    }

    public K getKey() {
        return this.key;
    }
}
