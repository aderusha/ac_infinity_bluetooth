package p014io.reactivex.internal.subscriptions;

import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Subscription;

/* renamed from: io.reactivex.internal.subscriptions.BooleanSubscription */
public final class BooleanSubscription extends AtomicBoolean implements Subscription {
    private static final long serialVersionUID = -8127758972444290902L;

    public void request(long j) {
        SubscriptionHelper.validate(j);
    }

    public void cancel() {
        lazySet(true);
    }

    public boolean isCancelled() {
        return get();
    }

    public String toString() {
        return "BooleanSubscription(cancelled=" + get() + ")";
    }
}
