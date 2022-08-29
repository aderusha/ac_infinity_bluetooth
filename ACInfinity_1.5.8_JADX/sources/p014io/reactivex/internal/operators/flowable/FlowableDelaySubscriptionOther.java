package p014io.reactivex.internal.operators.flowable;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableDelaySubscriptionOther */
public final class FlowableDelaySubscriptionOther<T, U> extends Flowable<T> {
    final Publisher<? extends T> main;
    final Publisher<U> other;

    public FlowableDelaySubscriptionOther(Publisher<? extends T> publisher, Publisher<U> publisher2) {
        this.main = publisher;
        this.other = publisher2;
    }

    public void subscribeActual(Subscriber<? super T> subscriber) {
        MainSubscriber mainSubscriber = new MainSubscriber(subscriber, this.main);
        subscriber.onSubscribe(mainSubscriber);
        this.other.subscribe(mainSubscriber.other);
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableDelaySubscriptionOther$MainSubscriber */
    static final class MainSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 2259811067697317255L;
        final Subscriber<? super T> downstream;
        final Publisher<? extends T> main;
        final MainSubscriber<T>.OtherSubscriber other = new OtherSubscriber();
        final AtomicReference<Subscription> upstream = new AtomicReference<>();

        MainSubscriber(Subscriber<? super T> subscriber, Publisher<? extends T> publisher) {
            this.downstream = subscriber;
            this.main = publisher;
        }

        /* access modifiers changed from: package-private */
        public void next() {
            this.main.subscribe(this);
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                SubscriptionHelper.deferredRequest(this.upstream, this, j);
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.other);
            SubscriptionHelper.cancel(this.upstream);
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this, subscription);
        }

        /* renamed from: io.reactivex.internal.operators.flowable.FlowableDelaySubscriptionOther$MainSubscriber$OtherSubscriber */
        final class OtherSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
            private static final long serialVersionUID = -3892798459447644106L;

            OtherSubscriber() {
            }

            public void onSubscribe(Subscription subscription) {
                if (SubscriptionHelper.setOnce(this, subscription)) {
                    subscription.request(Long.MAX_VALUE);
                }
            }

            public void onNext(Object obj) {
                Subscription subscription = (Subscription) get();
                if (subscription != SubscriptionHelper.CANCELLED) {
                    lazySet(SubscriptionHelper.CANCELLED);
                    subscription.cancel();
                    MainSubscriber.this.next();
                }
            }

            public void onError(Throwable th) {
                if (((Subscription) get()) != SubscriptionHelper.CANCELLED) {
                    MainSubscriber.this.downstream.onError(th);
                } else {
                    RxJavaPlugins.onError(th);
                }
            }

            public void onComplete() {
                if (((Subscription) get()) != SubscriptionHelper.CANCELLED) {
                    MainSubscriber.this.next();
                }
            }
        }
    }
}
