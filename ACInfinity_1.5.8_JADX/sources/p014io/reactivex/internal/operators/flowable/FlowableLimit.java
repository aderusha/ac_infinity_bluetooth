package p014io.reactivex.internal.operators.flowable;

import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.internal.subscriptions.EmptySubscription;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableLimit */
public final class FlowableLimit<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: n */
    final long f1112n;

    public FlowableLimit(Flowable<T> flowable, long j) {
        super(flowable);
        this.f1112n = j;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new LimitSubscriber(subscriber, this.f1112n));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableLimit$LimitSubscriber */
    static final class LimitSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 2288246011222124525L;
        final Subscriber<? super T> downstream;
        long remaining;
        Subscription upstream;

        LimitSubscriber(Subscriber<? super T> subscriber, long j) {
            this.downstream = subscriber;
            this.remaining = j;
            lazySet(j);
        }

        public void onSubscribe(Subscription subscription) {
            if (!SubscriptionHelper.validate(this.upstream, subscription)) {
                return;
            }
            if (this.remaining == 0) {
                subscription.cancel();
                EmptySubscription.complete(this.downstream);
                return;
            }
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
        }

        public void onNext(T t) {
            long j = this.remaining;
            if (j > 0) {
                long j2 = j - 1;
                this.remaining = j2;
                this.downstream.onNext(t);
                if (j2 == 0) {
                    this.upstream.cancel();
                    this.downstream.onComplete();
                }
            }
        }

        public void onError(Throwable th) {
            if (this.remaining > 0) {
                this.remaining = 0;
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            if (this.remaining > 0) {
                this.remaining = 0;
                this.downstream.onComplete();
            }
        }

        public void request(long j) {
            long j2;
            long j3;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 != 0) {
                        j3 = j2 <= j ? j2 : j;
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j2 - j3));
                this.upstream.request(j3);
            }
        }

        public void cancel() {
            this.upstream.cancel();
        }
    }
}
