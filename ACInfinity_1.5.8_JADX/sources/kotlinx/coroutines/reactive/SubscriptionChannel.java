package kotlinx.coroutines.reactive;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.LinkedListChannel;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u001c2\b\u0012\u0004\u0012\u00028\u00000\u001dB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0014\u0010\fJ\u000f\u0010\u0015\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0015\u0010\fJ\u0017\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001a¨\u0006\u001b"}, mo27512d2 = {"Lkotlinx/coroutines/reactive/SubscriptionChannel;", "T", "", "request", "<init>", "(I)V", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "closed", "", "onClosedIdempotent", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)V", "onComplete", "()V", "", "e", "onError", "(Ljava/lang/Throwable;)V", "t", "onNext", "(Ljava/lang/Object;)V", "onReceiveDequeued", "onReceiveEnqueued", "Lorg/reactivestreams/Subscription;", "s", "onSubscribe", "(Lorg/reactivestreams/Subscription;)V", "I", "kotlinx-coroutines-reactive", "Lkotlinx/coroutines/channels/LinkedListChannel;", "Lorg/reactivestreams/Subscriber;"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: Channel.kt */
final class SubscriptionChannel<T> extends LinkedListChannel<T> implements Subscriber<T> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _requested$FU;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _subscription$FU;
    private volatile /* synthetic */ int _requested;
    private volatile /* synthetic */ Object _subscription;
    private final int request;

    static {
        Class<SubscriptionChannel> cls = SubscriptionChannel.class;
        _subscription$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_subscription");
        _requested$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_requested");
    }

    public SubscriptionChannel(int i) {
        super((Function1) null);
        this.request = i;
        if (i >= 0) {
            this._subscription = null;
            this._requested = 0;
            return;
        }
        throw new IllegalArgumentException(("Invalid request size: " + i).toString());
    }

    public void onReceiveDequeued() {
        _requested$FU.incrementAndGet(this);
    }

    public void onClosedIdempotent(LockFreeLinkedListNode lockFreeLinkedListNode) {
        Subscription subscription = (Subscription) _subscription$FU.getAndSet(this, (Object) null);
        if (subscription != null) {
            subscription.cancel();
        }
    }

    public void onSubscribe(Subscription subscription) {
        this._subscription = subscription;
        while (!isClosedForSend()) {
            int i = this._requested;
            int i2 = this.request;
            if (i < i2) {
                if (_requested$FU.compareAndSet(this, i, i2)) {
                    subscription.request((long) (this.request - i));
                    return;
                }
            } else {
                return;
            }
        }
        subscription.cancel();
    }

    public void onNext(T t) {
        _requested$FU.decrementAndGet(this);
        offer(t);
    }

    public void onComplete() {
        close((Throwable) null);
    }

    public void onError(Throwable th) {
        close(th);
    }

    public void onReceiveEnqueued() {
        Subscription subscription;
        int i;
        while (true) {
            int i2 = this._requested;
            subscription = (Subscription) this._subscription;
            i = i2 - 1;
            if (subscription != null && i < 0) {
                int i3 = this.request;
                if (i2 == i3 || _requested$FU.compareAndSet(this, i2, i3)) {
                    subscription.request((long) (this.request - i));
                }
            } else if (_requested$FU.compareAndSet(this, i2, i)) {
                return;
            }
        }
        subscription.request((long) (this.request - i));
    }
}
