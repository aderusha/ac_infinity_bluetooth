package com.trello.rxlifecycle2;

import com.trello.rxlifecycle2.internal.Preconditions;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import p014io.reactivex.Observable;
import p014io.reactivex.functions.BiFunction;
import p014io.reactivex.functions.Function;
import p014io.reactivex.functions.Predicate;

public class RxLifecycle {
    private RxLifecycle() {
        throw new AssertionError("No instances");
    }

    @CheckReturnValue
    @Nonnull
    public static <T, R> LifecycleTransformer<T> bindUntilEvent(@Nonnull Observable<R> observable, @Nonnull R r) {
        Preconditions.checkNotNull(observable, "lifecycle == null");
        Preconditions.checkNotNull(r, "event == null");
        return bind(takeUntilEvent(observable, r));
    }

    private static <R> Observable<R> takeUntilEvent(Observable<R> observable, final R r) {
        return observable.filter(new Predicate<R>() {
            public boolean test(R r) throws Exception {
                return r.equals(r);
            }
        });
    }

    @CheckReturnValue
    @Nonnull
    public static <T, R> LifecycleTransformer<T> bind(@Nonnull Observable<R> observable) {
        return new LifecycleTransformer<>(observable);
    }

    @CheckReturnValue
    @Nonnull
    public static <T, R> LifecycleTransformer<T> bind(@Nonnull Observable<R> observable, @Nonnull Function<R, R> function) {
        Preconditions.checkNotNull(observable, "lifecycle == null");
        Preconditions.checkNotNull(function, "correspondingEvents == null");
        return bind(takeUntilCorrespondingEvent(observable.share(), function));
    }

    private static <R> Observable<Boolean> takeUntilCorrespondingEvent(Observable<R> observable, Function<R, R> function) {
        return Observable.combineLatest(observable.take(1).map(function), observable.skip(1), new BiFunction<R, R, Boolean>() {
            public Boolean apply(R r, R r2) throws Exception {
                return Boolean.valueOf(r2.equals(r));
            }
        }).onErrorReturn(Functions.RESUME_FUNCTION).filter(Functions.SHOULD_COMPLETE);
    }
}
