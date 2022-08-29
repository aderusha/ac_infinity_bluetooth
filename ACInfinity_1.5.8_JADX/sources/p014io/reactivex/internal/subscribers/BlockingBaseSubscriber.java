package p014io.reactivex.internal.subscribers;

import java.util.concurrent.CountDownLatch;
import org.reactivestreams.Subscription;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.internal.util.BlockingHelper;
import p014io.reactivex.internal.util.ExceptionHelper;

/* renamed from: io.reactivex.internal.subscribers.BlockingBaseSubscriber */
public abstract class BlockingBaseSubscriber<T> extends CountDownLatch implements FlowableSubscriber<T> {
    volatile boolean cancelled;
    Throwable error;
    Subscription upstream;
    T value;

    public BlockingBaseSubscriber() {
        super(1);
    }

    public final void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            if (!this.cancelled) {
                subscription.request(Long.MAX_VALUE);
                if (this.cancelled) {
                    this.upstream = SubscriptionHelper.CANCELLED;
                    subscription.cancel();
                }
            }
        }
    }

    public final void onComplete() {
        countDown();
    }

    public final T blockingGet() {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (InterruptedException e) {
                Subscription subscription = this.upstream;
                this.upstream = SubscriptionHelper.CANCELLED;
                if (subscription != null) {
                    subscription.cancel();
                }
                throw ExceptionHelper.wrapOrThrow(e);
            }
        }
        Throwable th = this.error;
        if (th == null) {
            return this.value;
        }
        throw ExceptionHelper.wrapOrThrow(th);
    }
}
