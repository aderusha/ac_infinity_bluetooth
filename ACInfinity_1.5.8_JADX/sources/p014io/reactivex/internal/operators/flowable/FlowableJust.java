package p014io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;
import p014io.reactivex.Flowable;
import p014io.reactivex.internal.fuseable.ScalarCallable;
import p014io.reactivex.internal.subscriptions.ScalarSubscription;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableJust */
public final class FlowableJust<T> extends Flowable<T> implements ScalarCallable<T> {
    private final T value;

    public FlowableJust(T t) {
        this.value = t;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        subscriber.onSubscribe(new ScalarSubscription(subscriber, this.value));
    }

    public T call() {
        return this.value;
    }
}
