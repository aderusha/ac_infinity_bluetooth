package p014io.reactivex.internal.operators.flowable;

import java.util.concurrent.Callable;
import org.reactivestreams.Subscriber;
import p014io.reactivex.Flowable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.subscriptions.EmptySubscription;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableError */
public final class FlowableError<T> extends Flowable<T> {
    final Callable<? extends Throwable> errorSupplier;

    public FlowableError(Callable<? extends Throwable> callable) {
        this.errorSupplier = callable;
    }

    public void subscribeActual(Subscriber<? super T> subscriber) {
        try {
            th = (Throwable) ObjectHelper.requireNonNull(this.errorSupplier.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th) {
            th = th;
            Exceptions.throwIfFatal(th);
        }
        EmptySubscription.error(th, subscriber);
    }
}
