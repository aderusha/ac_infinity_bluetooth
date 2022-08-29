package p014io.reactivex.observables;

import java.util.concurrent.TimeUnit;
import p014io.reactivex.Observable;
import p014io.reactivex.Scheduler;
import p014io.reactivex.annotations.CheckReturnValue;
import p014io.reactivex.annotations.SchedulerSupport;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.internal.functions.Functions;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.operators.observable.ObservableAutoConnect;
import p014io.reactivex.internal.operators.observable.ObservableRefCount;
import p014io.reactivex.internal.util.ConnectConsumer;
import p014io.reactivex.plugins.RxJavaPlugins;
import p014io.reactivex.schedulers.Schedulers;

/* renamed from: io.reactivex.observables.ConnectableObservable */
public abstract class ConnectableObservable<T> extends Observable<T> {
    public abstract void connect(Consumer<? super Disposable> consumer);

    public final Disposable connect() {
        ConnectConsumer connectConsumer = new ConnectConsumer();
        connect(connectConsumer);
        return connectConsumer.disposable;
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public Observable<T> refCount() {
        return RxJavaPlugins.onAssembly(new ObservableRefCount(this));
    }

    @CheckReturnValue
    @SchedulerSupport("none")
    public final Observable<T> refCount(int i) {
        return refCount(i, 0, TimeUnit.NANOSECONDS, Schedulers.trampoline());
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> refCount(long j, TimeUnit timeUnit) {
        return refCount(1, j, timeUnit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> refCount(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return refCount(1, j, timeUnit, scheduler);
    }

    @CheckReturnValue
    @SchedulerSupport("io.reactivex:computation")
    public final Observable<T> refCount(int i, long j, TimeUnit timeUnit) {
        return refCount(i, j, timeUnit, Schedulers.computation());
    }

    @CheckReturnValue
    @SchedulerSupport("custom")
    public final Observable<T> refCount(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        ObjectHelper.verifyPositive(i, "subscriberCount");
        ObjectHelper.requireNonNull(timeUnit, "unit is null");
        ObjectHelper.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new ObservableRefCount(this, i, j, timeUnit, scheduler));
    }

    public Observable<T> autoConnect() {
        return autoConnect(1);
    }

    public Observable<T> autoConnect(int i) {
        return autoConnect(i, Functions.emptyConsumer());
    }

    public Observable<T> autoConnect(int i, Consumer<? super Disposable> consumer) {
        if (i > 0) {
            return RxJavaPlugins.onAssembly(new ObservableAutoConnect(this, i, consumer));
        }
        connect(consumer);
        return RxJavaPlugins.onAssembly(this);
    }
}
