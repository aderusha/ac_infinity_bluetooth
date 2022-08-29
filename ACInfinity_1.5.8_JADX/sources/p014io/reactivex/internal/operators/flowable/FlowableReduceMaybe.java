package p014io.reactivex.internal.operators.flowable;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.BiFunction;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.fuseable.FuseToFlowable;
import p014io.reactivex.internal.fuseable.HasUpstreamPublisher;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableReduceMaybe */
public final class FlowableReduceMaybe<T> extends Maybe<T> implements HasUpstreamPublisher<T>, FuseToFlowable<T> {
    final BiFunction<T, T, T> reducer;
    final Flowable<T> source;

    public FlowableReduceMaybe(Flowable<T> flowable, BiFunction<T, T, T> biFunction) {
        this.source = flowable;
        this.reducer = biFunction;
    }

    public Publisher<T> source() {
        return this.source;
    }

    public Flowable<T> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableReduce(this.source, this.reducer));
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new ReduceSubscriber(maybeObserver, this.reducer));
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableReduceMaybe$ReduceSubscriber */
    static final class ReduceSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        boolean done;
        final MaybeObserver<? super T> downstream;
        final BiFunction<T, T, T> reducer;
        Subscription upstream;
        T value;

        ReduceSubscriber(MaybeObserver<? super T> maybeObserver, BiFunction<T, T, T> biFunction) {
            this.downstream = maybeObserver;
            this.reducer = biFunction;
        }

        public void dispose() {
            this.upstream.cancel();
            this.done = true;
        }

        public boolean isDisposed() {
            return this.done;
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
                T t2 = this.value;
                if (t2 == null) {
                    this.value = t;
                    return;
                }
                try {
                    this.value = ObjectHelper.requireNonNull(this.reducer.apply(t2, t), "The reducer returned a null value");
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
                T t = this.value;
                if (t != null) {
                    this.downstream.onSuccess(t);
                } else {
                    this.downstream.onComplete();
                }
            }
        }
    }
}
