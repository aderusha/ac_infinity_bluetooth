package p014io.reactivex.internal.observers;

import p014io.reactivex.internal.fuseable.QueueDisposable;

/* renamed from: io.reactivex.internal.observers.BasicQueueDisposable */
public abstract class BasicQueueDisposable<T> implements QueueDisposable<T> {
    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }

    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
