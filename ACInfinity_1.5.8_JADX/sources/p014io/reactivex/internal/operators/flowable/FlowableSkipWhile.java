package p014io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Predicate;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableSkipWhile */
public final class FlowableSkipWhile<T> extends AbstractFlowableWithUpstream<T, T> {
    final Predicate<? super T> predicate;

    public FlowableSkipWhile(Flowable<T> flowable, Predicate<? super T> predicate2) {
        super(flowable);
        this.predicate = predicate2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe(new SkipWhileSubscriber(subscriber, this.predicate));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableSkipWhile$SkipWhileSubscriber */
    static final class SkipWhileSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> downstream;
        boolean notSkipping;
        final Predicate<? super T> predicate;
        Subscription upstream;

        SkipWhileSubscriber(Subscriber<? super T> subscriber, Predicate<? super T> predicate2) {
            this.downstream = subscriber;
            this.predicate = predicate2;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.notSkipping) {
                this.downstream.onNext(t);
                return;
            }
            try {
                if (this.predicate.test(t)) {
                    this.upstream.request(1);
                    return;
                }
                this.notSkipping = true;
                this.downstream.onNext(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.cancel();
                this.downstream.onError(th);
            }
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
