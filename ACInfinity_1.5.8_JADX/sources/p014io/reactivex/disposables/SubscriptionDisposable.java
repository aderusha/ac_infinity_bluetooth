package p014io.reactivex.disposables;

import org.reactivestreams.Subscription;

/* renamed from: io.reactivex.disposables.SubscriptionDisposable */
final class SubscriptionDisposable extends ReferenceDisposable<Subscription> {
    private static final long serialVersionUID = -707001650852963139L;

    SubscriptionDisposable(Subscription subscription) {
        super(subscription);
    }

    /* access modifiers changed from: protected */
    public void onDisposed(Subscription subscription) {
        subscription.cancel();
    }
}
