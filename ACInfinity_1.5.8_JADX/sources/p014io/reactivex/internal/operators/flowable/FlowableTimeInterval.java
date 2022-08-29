package p014io.reactivex.internal.operators.flowable;

import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.Scheduler;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.schedulers.Timed;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableTimeInterval */
public final class FlowableTimeInterval<T> extends AbstractFlowableWithUpstream<T, Timed<T>> {
    final Scheduler scheduler;
    final TimeUnit unit;

    public FlowableTimeInterval(Flowable<T> flowable, TimeUnit timeUnit, Scheduler scheduler2) {
        super(flowable);
        this.scheduler = scheduler2;
        this.unit = timeUnit;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super Timed<T>> subscriber) {
        this.source.subscribe(new TimeIntervalSubscriber(subscriber, this.unit, this.scheduler));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableTimeInterval$TimeIntervalSubscriber */
    static final class TimeIntervalSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super Timed<T>> downstream;
        long lastTime;
        final Scheduler scheduler;
        final TimeUnit unit;
        Subscription upstream;

        TimeIntervalSubscriber(Subscriber<? super Timed<T>> subscriber, TimeUnit timeUnit, Scheduler scheduler2) {
            this.downstream = subscriber;
            this.scheduler = scheduler2;
            this.unit = timeUnit;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.lastTime = this.scheduler.now(this.unit);
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long now = this.scheduler.now(this.unit);
            long j = this.lastTime;
            this.lastTime = now;
            this.downstream.onNext(new Timed(t, now - j, this.unit));
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        public void cancel() {
            this.upstream.cancel();
        }
    }
}
