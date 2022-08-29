package p014io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableCount */
public final class FlowableCount<T> extends AbstractFlowableWithUpstream<T, Long> {
    public FlowableCount(Flowable<T> flowable) {
        super(flowable);
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super Long> subscriber) {
        this.source.subscribe(new CountSubscriber(subscriber));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableCount$CountSubscriber */
    static final class CountSubscriber extends DeferredScalarSubscription<Long> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = 4973004223787171406L;
        long count;
        Subscription upstream;

        CountSubscriber(Subscriber<? super Long> subscriber) {
            super(subscriber);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            this.count++;
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            complete(Long.valueOf(this.count));
        }

        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
