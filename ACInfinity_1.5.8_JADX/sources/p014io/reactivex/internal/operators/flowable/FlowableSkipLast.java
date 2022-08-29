package p014io.reactivex.internal.operators.flowable;

import java.util.ArrayDeque;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableSkipLast */
public final class FlowableSkipLast<T> extends AbstractFlowableWithUpstream<T, T> {
    final int skip;

    public FlowableSkipLast(Flowable<T> flowable, int i) {
        super(flowable);
        this.skip = i;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new SkipLastSubscriber(subscriber, this.skip));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableSkipLast$SkipLastSubscriber */
    static final class SkipLastSubscriber<T> extends ArrayDeque<T> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -3807491841935125653L;
        final Subscriber<? super T> downstream;
        final int skip;
        Subscription upstream;

        SkipLastSubscriber(Subscriber<? super T> subscriber, int i) {
            super(i);
            this.downstream = subscriber;
            this.skip = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.skip == size()) {
                this.downstream.onNext(poll());
            } else {
                this.upstream.request(1);
            }
            offer(t);
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
