package p014io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Predicate;
import p014io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableAny */
public final class FlowableAny<T> extends AbstractFlowableWithUpstream<T, Boolean> {
    final Predicate<? super T> predicate;

    public FlowableAny(Flowable<T> flowable, Predicate<? super T> predicate2) {
        super(flowable);
        this.predicate = predicate2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super Boolean> subscriber) {
        this.source.subscribe(new AnySubscriber(subscriber, this.predicate));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableAny$AnySubscriber */
    static final class AnySubscriber<T> extends DeferredScalarSubscription<Boolean> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -2311252482644620661L;
        boolean done;
        final Predicate<? super T> predicate;
        Subscription upstream;

        AnySubscriber(Subscriber<? super Boolean> subscriber, Predicate<? super T> predicate2) {
            super(subscriber);
            this.predicate = predicate2;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    if (this.predicate.test(t)) {
                        this.done = true;
                        this.upstream.cancel();
                        complete(true);
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.upstream.cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                complete(false);
            }
        }

        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
