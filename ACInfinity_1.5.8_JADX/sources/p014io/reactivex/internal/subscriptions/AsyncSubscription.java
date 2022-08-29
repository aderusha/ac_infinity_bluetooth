package p014io.reactivex.internal.subscriptions;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.subscriptions.AsyncSubscription */
public final class AsyncSubscription extends AtomicLong implements Subscription, Disposable {
    private static final long serialVersionUID = 7028635084060361255L;
    final AtomicReference<Subscription> actual;
    final AtomicReference<Disposable> resource;

    public AsyncSubscription() {
        this.resource = new AtomicReference<>();
        this.actual = new AtomicReference<>();
    }

    public AsyncSubscription(Disposable disposable) {
        this();
        this.resource.lazySet(disposable);
    }

    public void request(long j) {
        SubscriptionHelper.deferredRequest(this.actual, this, j);
    }

    public void cancel() {
        dispose();
    }

    public void dispose() {
        SubscriptionHelper.cancel(this.actual);
        DisposableHelper.dispose(this.resource);
    }

    public boolean isDisposed() {
        return this.actual.get() == SubscriptionHelper.CANCELLED;
    }

    public boolean setResource(Disposable disposable) {
        return DisposableHelper.set(this.resource, disposable);
    }

    public boolean replaceResource(Disposable disposable) {
        return DisposableHelper.replace(this.resource, disposable);
    }

    public void setSubscription(Subscription subscription) {
        SubscriptionHelper.deferredSetOnce(this.actual, this, subscription);
    }
}
