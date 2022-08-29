package p014io.reactivex.internal.operators.maybe;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import p014io.reactivex.Flowable;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import p014io.reactivex.internal.subscriptions.SubscriptionHelper;
import p014io.reactivex.internal.util.BackpressureHelper;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeFlatMapIterableFlowable */
public final class MaybeFlatMapIterableFlowable<T, R> extends Flowable<R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;
    final MaybeSource<T> source;

    public MaybeFlatMapIterableFlowable(MaybeSource<T> maybeSource, Function<? super T, ? extends Iterable<? extends R>> function) {
        this.source = maybeSource;
        this.mapper = function;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.source.subscribe(new FlatMapIterableObserver(subscriber, this.mapper));
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeFlatMapIterableFlowable$FlatMapIterableObserver */
    static final class FlatMapIterableObserver<T, R> extends BasicIntQueueSubscription<R> implements MaybeObserver<T> {
        private static final long serialVersionUID = -8938804753851907758L;
        volatile boolean cancelled;
        final Subscriber<? super R> downstream;

        /* renamed from: it */
        volatile Iterator<? extends R> f1129it;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;
        final AtomicLong requested = new AtomicLong();
        Disposable upstream;

        FlatMapIterableObserver(Subscriber<? super R> subscriber, Function<? super T, ? extends Iterable<? extends R>> function) {
            this.downstream = subscriber;
            this.mapper = function;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            try {
                Iterator<? extends R> it = ((Iterable) this.mapper.apply(t)).iterator();
                if (!it.hasNext()) {
                    this.downstream.onComplete();
                    return;
                }
                this.f1129it = it;
                drain();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        /* access modifiers changed from: package-private */
        public void fastPath(Subscriber<? super R> subscriber, Iterator<? extends R> it) {
            while (!this.cancelled) {
                try {
                    subscriber.onNext(it.next());
                    if (!this.cancelled) {
                        try {
                            if (!it.hasNext()) {
                                subscriber.onComplete();
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            subscriber.onError(th);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    subscriber.onError(th2);
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super R> subscriber = this.downstream;
                Iterator<? extends R> it = this.f1129it;
                if (!this.outputFused || it == null) {
                    int i = 1;
                    while (true) {
                        if (it != null) {
                            long j = this.requested.get();
                            if (j == Long.MAX_VALUE) {
                                fastPath(subscriber, it);
                                return;
                            }
                            long j2 = 0;
                            while (j2 != j) {
                                if (!this.cancelled) {
                                    try {
                                        subscriber.onNext(ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value"));
                                        if (!this.cancelled) {
                                            j2++;
                                            try {
                                                if (!it.hasNext()) {
                                                    subscriber.onComplete();
                                                    return;
                                                }
                                            } catch (Throwable th) {
                                                Exceptions.throwIfFatal(th);
                                                subscriber.onError(th);
                                                return;
                                            }
                                        } else {
                                            return;
                                        }
                                    } catch (Throwable th2) {
                                        Exceptions.throwIfFatal(th2);
                                        subscriber.onError(th2);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }
                            if (j2 != 0) {
                                BackpressureHelper.produced(this.requested, j2);
                            }
                        }
                        i = addAndGet(-i);
                        if (i != 0) {
                            if (it == null) {
                                it = this.f1129it;
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    subscriber.onNext(null);
                    subscriber.onComplete();
                }
            }
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public void clear() {
            this.f1129it = null;
        }

        public boolean isEmpty() {
            return this.f1129it == null;
        }

        public R poll() throws Exception {
            Iterator<? extends R> it = this.f1129it;
            if (it == null) {
                return null;
            }
            R requireNonNull = ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value");
            if (!it.hasNext()) {
                this.f1129it = null;
            }
            return requireNonNull;
        }
    }
}
