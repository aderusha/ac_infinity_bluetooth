package p014io.reactivex.internal.observers;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.observers.SubscriberCompletableObserver */
public final class SubscriberCompletableObserver<T> implements CompletableObserver, Subscription {
    final Subscriber<? super T> subscriber;
    Disposable upstream;

    public void request(long j) {
    }

    public SubscriberCompletableObserver(Subscriber<? super T> subscriber2) {
        this.subscriber = subscriber2;
    }

    public void onComplete() {
        this.subscriber.onComplete();
    }

    public void onError(Throwable th) {
        this.subscriber.onError(th);
    }

    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.upstream, disposable)) {
            this.upstream = disposable;
            this.subscriber.onSubscribe(this);
        }
    }

    public void cancel() {
        this.upstream.dispose();
    }
}
