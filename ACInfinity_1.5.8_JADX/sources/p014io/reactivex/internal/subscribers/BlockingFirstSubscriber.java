package p014io.reactivex.internal.subscribers;

import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.subscribers.BlockingFirstSubscriber */
public final class BlockingFirstSubscriber<T> extends BlockingBaseSubscriber<T> {
    public void onNext(T t) {
        if (this.value == null) {
            this.value = t;
            this.upstream.cancel();
            countDown();
        }
    }

    public void onError(Throwable th) {
        if (this.value == null) {
            this.error = th;
        } else {
            RxJavaPlugins.onError(th);
        }
        countDown();
    }
}
