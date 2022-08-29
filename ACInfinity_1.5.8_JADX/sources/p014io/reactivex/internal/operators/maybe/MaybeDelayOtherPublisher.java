package p014io.reactivex.internal.operators.maybe;

import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import p014io.reactivex.FlowableSubscriber;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeDelayOtherPublisher */
public final class MaybeDelayOtherPublisher<T, U> extends AbstractMaybeWithUpstream<T, T> {
    final Publisher<U> other;

    public MaybeDelayOtherPublisher(MaybeSource<T> maybeSource, Publisher<U> publisher) {
        super(maybeSource);
        this.other = publisher;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new DelayMaybeObserver(maybeObserver, this.other));
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeDelayOtherPublisher$DelayMaybeObserver */
    static final class DelayMaybeObserver<T, U> implements MaybeObserver<T>, Disposable {
        final OtherSubscriber<T> other;
        final Publisher<U> otherSource;
        Disposable upstream;

        DelayMaybeObserver(MaybeObserver<? super T> maybeObserver, Publisher<U> publisher) {
            this.other = new OtherSubscriber<>(maybeObserver);
            this.otherSource = publisher;
        }

        public void dispose() {
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
            SubscriptionHelper.cancel(this.other);
        }

        public boolean isDisposed() {
            return this.other.get() == SubscriptionHelper.CANCELLED;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.other.downstream.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.upstream = DisposableHelper.DISPOSED;
            this.other.value = t;
            subscribeNext();
        }

        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.other.error = th;
            subscribeNext();
        }

        public void onComplete() {
            this.upstream = DisposableHelper.DISPOSED;
            subscribeNext();
        }

        /* access modifiers changed from: package-private */
        public void subscribeNext() {
            this.otherSource.subscribe(this.other);
        }
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeDelayOtherPublisher$OtherSubscriber */
    static final class OtherSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = -1215060610805418006L;
        final MaybeObserver<? super T> downstream;
        Throwable error;
        T value;

        OtherSubscriber(MaybeObserver<? super T> maybeObserver) {
            this.downstream = maybeObserver;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this, subscription, Long.MAX_VALUE);
        }

        public void onNext(Object obj) {
            Subscription subscription = (Subscription) get();
            if (subscription != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                subscription.cancel();
                onComplete();
            }
        }

        public void onError(Throwable th) {
            Throwable th2 = this.error;
            if (th2 == null) {
                this.downstream.onError(th);
                return;
            }
            this.downstream.onError(new CompositeException(th2, th));
        }

        public void onComplete() {
            Throwable th = this.error;
            if (th != null) {
                this.downstream.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.downstream.onSuccess(t);
            } else {
                this.downstream.onComplete();
            }
        }
    }
}
