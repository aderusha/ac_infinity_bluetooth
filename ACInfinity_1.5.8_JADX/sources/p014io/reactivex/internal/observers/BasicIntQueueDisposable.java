package p014io.reactivex.internal.observers;

import java.util.concurrent.atomic.AtomicInteger;
import p014io.reactivex.internal.fuseable.QueueDisposable;

/* renamed from: io.reactivex.internal.observers.BasicIntQueueDisposable */
public abstract class BasicIntQueueDisposable<T> extends AtomicInteger implements QueueDisposable<T> {
    private static final long serialVersionUID = -1001730202384742097L;

    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called");
    }

    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
