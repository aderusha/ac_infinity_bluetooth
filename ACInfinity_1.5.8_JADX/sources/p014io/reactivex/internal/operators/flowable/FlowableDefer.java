package p014io.reactivex.internal.operators.flowable;

import java.util.concurrent.Callable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import p014io.reactivex.Flowable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.subscriptions.EmptySubscription;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableDefer */
public final class FlowableDefer<T> extends Flowable<T> {
    final Callable<? extends Publisher<? extends T>> supplier;

    public FlowableDefer(Callable<? extends Publisher<? extends T>> callable) {
        this.supplier = callable;
    }

    public void subscribeActual(Subscriber<? super T> subscriber) {
        try {
            ((Publisher) ObjectHelper.requireNonNull(this.supplier.call(), "The publisher supplied is null")).subscribe(subscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, subscriber);
        }
    }
}
