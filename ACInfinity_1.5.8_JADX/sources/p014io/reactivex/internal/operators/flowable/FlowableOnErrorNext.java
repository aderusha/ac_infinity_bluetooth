package p014io.reactivex.internal.operators.flowable;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.subscriptions.SubscriptionArbiter;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableOnErrorNext */
public final class FlowableOnErrorNext<T> extends AbstractFlowableWithUpstream<T, T> {
    final boolean allowFatal;
    final Function<? super Throwable, ? extends Publisher<? extends T>> nextSupplier;

    public FlowableOnErrorNext(Flowable<T> flowable, Function<? super Throwable, ? extends Publisher<? extends T>> function, boolean z) {
        super(flowable);
        this.nextSupplier = function;
        this.allowFatal = z;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> subscriber) {
        OnErrorNextSubscriber onErrorNextSubscriber = new OnErrorNextSubscriber(subscriber, this.nextSupplier, this.allowFatal);
        subscriber.onSubscribe(onErrorNextSubscriber);
        this.source.subscribe(onErrorNextSubscriber);
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableOnErrorNext$OnErrorNextSubscriber */
    static final class OnErrorNextSubscriber<T> extends SubscriptionArbiter implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 4063763155303814625L;
        final boolean allowFatal;
        boolean done;
        final Subscriber<? super T> downstream;
        final Function<? super Throwable, ? extends Publisher<? extends T>> nextSupplier;
        boolean once;
        long produced;

        OnErrorNextSubscriber(Subscriber<? super T> subscriber, Function<? super Throwable, ? extends Publisher<? extends T>> function, boolean z) {
            super(false);
            this.downstream = subscriber;
            this.nextSupplier = function;
            this.allowFatal = z;
        }

        public void onSubscribe(Subscription subscription) {
            setSubscription(subscription);
        }

        public void onNext(T t) {
            if (!this.done) {
                if (!this.once) {
                    this.produced++;
                }
                this.downstream.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (!this.once) {
                this.once = true;
                if (!this.allowFatal || (th instanceof Exception)) {
                    try {
                        Publisher publisher = (Publisher) ObjectHelper.requireNonNull(this.nextSupplier.apply(th), "The nextSupplier returned a null Publisher");
                        long j = this.produced;
                        if (j != 0) {
                            produced(j);
                        }
                        publisher.subscribe(this);
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        this.downstream.onError(new CompositeException(th, th2));
                    }
                } else {
                    this.downstream.onError(th);
                }
            } else if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.downstream.onError(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.once = true;
                this.downstream.onComplete();
            }
        }
    }
}
