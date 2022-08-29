package p014io.reactivex.internal.subscribers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.internal.util.NotificationLite;

/* renamed from: io.reactivex.internal.subscribers.BlockingSubscriber */
public final class BlockingSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
    public static final Object TERMINATED = new Object();
    private static final long serialVersionUID = -4875965440900746268L;
    final Queue<Object> queue;

    public BlockingSubscriber(Queue<Object> queue2) {
        this.queue = queue2;
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            this.queue.offer(NotificationLite.subscription(this));
        }
    }

    public void onNext(T t) {
        this.queue.offer(NotificationLite.next(t));
    }

    public void onError(Throwable th) {
        this.queue.offer(NotificationLite.error(th));
    }

    public void onComplete() {
        this.queue.offer(NotificationLite.complete());
    }

    public void request(long j) {
        ((Subscription) get()).request(j);
    }

    public void cancel() {
        if (SubscriptionHelper.cancel(this)) {
            this.queue.offer(TERMINATED);
        }
    }

    public boolean isCancelled() {
        return get() == SubscriptionHelper.CANCELLED;
    }
}
