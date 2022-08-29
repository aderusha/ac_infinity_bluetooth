package p014io.reactivex.internal.operators.flowable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.Scheduler;
import p014io.reactivex.exceptions.MissingBackpressureException;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.disposables.SequentialDisposable;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.internal.util.BackpressureHelper;
import p014io.reactivex.subscribers.SerializedSubscriber;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableSampleTimed */
public final class FlowableSampleTimed<T> extends AbstractFlowableWithUpstream<T, T> {
    final boolean emitLast;
    final long period;
    final Scheduler scheduler;
    final TimeUnit unit;

    public FlowableSampleTimed(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler2, boolean z) {
        super(flowable);
        this.period = j;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
        this.emitLast = z;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        if (this.emitLast) {
            this.source.subscribe(new SampleTimedEmitLast(serializedSubscriber, this.period, this.unit, this.scheduler));
        } else {
            this.source.subscribe(new SampleTimedNoLast(serializedSubscriber, this.period, this.unit, this.scheduler));
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableSampleTimed$SampleTimedSubscriber */
    static abstract class SampleTimedSubscriber<T> extends AtomicReference<T> implements FlowableSubscriber<T>, Subscription, Runnable {
        private static final long serialVersionUID = -3517602651313910099L;
        final Subscriber<? super T> downstream;
        final long period;
        final AtomicLong requested = new AtomicLong();
        final Scheduler scheduler;
        final SequentialDisposable timer = new SequentialDisposable();
        final TimeUnit unit;
        Subscription upstream;

        /* access modifiers changed from: package-private */
        public abstract void complete();

        SampleTimedSubscriber(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler scheduler2) {
            this.downstream = subscriber;
            this.period = j;
            this.unit = timeUnit;
            this.scheduler = scheduler2;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                SequentialDisposable sequentialDisposable = this.timer;
                Scheduler scheduler2 = this.scheduler;
                long j = this.period;
                sequentialDisposable.replace(scheduler2.schedulePeriodicallyDirect(this, j, j, this.unit));
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            cancelTimer();
            this.downstream.onError(th);
        }

        public void onComplete() {
            cancelTimer();
            complete();
        }

        /* access modifiers changed from: package-private */
        public void cancelTimer() {
            DisposableHelper.dispose(this.timer);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
            }
        }

        public void cancel() {
            cancelTimer();
            this.upstream.cancel();
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

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableSampleTimed$SampleTimedNoLast */
    static final class SampleTimedNoLast<T> extends SampleTimedSubscriber<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        SampleTimedNoLast(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler scheduler) {
            super(subscriber, j, timeUnit, scheduler);
        }

        /* access modifiers changed from: package-private */
        public void complete() {
            this.downstream.onComplete();
        }

        public void run() {
            emit();
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableSampleTimed$SampleTimedEmitLast */
    static final class SampleTimedEmitLast<T> extends SampleTimedSubscriber<T> {
        private static final long serialVersionUID = -7139995637533111443L;
        final AtomicInteger wip = new AtomicInteger(1);

        SampleTimedEmitLast(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler scheduler) {
            super(subscriber, j, timeUnit, scheduler);
        }

        /* access modifiers changed from: package-private */
        public void complete() {
            emit();
            if (this.wip.decrementAndGet() == 0) {
                this.downstream.onComplete();
            }
        }

        public void run() {
            if (this.wip.incrementAndGet() == 2) {
                emit();
                if (this.wip.decrementAndGet() == 0) {
                    this.downstream.onComplete();
                }
            }
        }
    }
}
