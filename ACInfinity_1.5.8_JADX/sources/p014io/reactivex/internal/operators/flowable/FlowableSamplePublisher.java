package p014io.reactivex.internal.operators.flowable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.exceptions.MissingBackpressureException;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.internal.util.BackpressureHelper;
import p014io.reactivex.subscribers.SerializedSubscriber;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableSamplePublisher */
public final class FlowableSamplePublisher<T> extends Flowable<T> {
    final boolean emitLast;
    final Publisher<?> other;
    final Publisher<T> source;

    public FlowableSamplePublisher(Publisher<T> publisher, Publisher<?> publisher2, boolean z) {
        this.source = publisher;
        this.other = publisher2;
        this.emitLast = z;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        if (this.emitLast) {
            this.source.subscribe(new SampleMainEmitLast(serializedSubscriber, this.other));
        } else {
            this.source.subscribe(new SampleMainNoLast(serializedSubscriber, this.other));
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableSamplePublisher$SamplePublisherSubscriber */
    static abstract class SamplePublisherSubscriber<T> extends AtomicReference<T> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -3517602651313910099L;
        final Subscriber<? super T> downstream;
        final AtomicReference<Subscription> other = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final Publisher<?> sampler;
        Subscription upstream;

        /* access modifiers changed from: package-private */
        public abstract void completion();

        /* access modifiers changed from: package-private */
        public abstract void run();

        SamplePublisherSubscriber(Subscriber<? super T> subscriber, Publisher<?> publisher) {
            this.downstream = subscriber;
            this.sampler = publisher;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new SamplerSubscriber(this));
                    subscription.request(Long.MAX_VALUE);
                }
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            this.downstream.onError(th);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            completion();
        }

        /* access modifiers changed from: package-private */
        public void setOther(Subscription subscription) {
            SubscriptionHelper.setOnce(this.other, subscription, Long.MAX_VALUE);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.other);
            this.upstream.cancel();
        }

        public void error(Throwable th) {
            this.upstream.cancel();
            this.downstream.onError(th);
        }

        public void complete() {
            this.upstream.cancel();
            completion();
        }

        /* access modifiers changed from: package-private */
        public void emit() {
            Object andSet = getAndSet((Object) null);
            if (andSet == null) {
                return;
            }
            if (this.requested.get() != 0) {
                this.downstream.onNext(andSet);
                BackpressureHelper.produced(this.requested, 1);
                return;
            }
            cancel();
            this.downstream.onError(new MissingBackpressureException("Couldn't emit value due to lack of requests!"));
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableSamplePublisher$SamplerSubscriber */
    static final class SamplerSubscriber<T> implements FlowableSubscriber<Object> {
        final SamplePublisherSubscriber<T> parent;

        SamplerSubscriber(SamplePublisherSubscriber<T> samplePublisherSubscriber) {
            this.parent = samplePublisherSubscriber;
        }

        public void onSubscribe(Subscription subscription) {
            this.parent.setOther(subscription);
        }

        public void onNext(Object obj) {
            this.parent.run();
        }

        public void onError(Throwable th) {
            this.parent.error(th);
        }

        public void onComplete() {
            this.parent.complete();
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableSamplePublisher$SampleMainNoLast */
    static final class SampleMainNoLast<T> extends SamplePublisherSubscriber<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        SampleMainNoLast(Subscriber<? super T> subscriber, Publisher<?> publisher) {
            super(subscriber, publisher);
        }

        /* access modifiers changed from: package-private */
        public void completion() {
            this.downstream.onComplete();
        }

        /* access modifiers changed from: package-private */
        public void run() {
            emit();
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableSamplePublisher$SampleMainEmitLast */
    static final class SampleMainEmitLast<T> extends SamplePublisherSubscriber<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        SampleMainEmitLast(Subscriber<? super T> subscriber, Publisher<?> publisher) {
            super(subscriber, publisher);
        }

        /* access modifiers changed from: package-private */
        public void completion() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.downstream.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean z = this.done;
                    emit();
                    if (z) {
                        this.downstream.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }
}
