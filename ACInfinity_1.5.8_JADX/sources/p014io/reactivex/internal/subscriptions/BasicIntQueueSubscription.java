package p014io.reactivex.internal.subscriptions;

import java.util.concurrent.atomic.AtomicInteger;
import p014io.reactivex.internal.fuseable.QueueSubscription;

/* renamed from: io.reactivex.internal.subscriptions.BasicIntQueueSubscription */
public abstract class BasicIntQueueSubscription<T> extends AtomicInteger implements QueueSubscription<T> {
    private static final long serialVersionUID = -6671519529404341862L;

    public final boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public final boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
