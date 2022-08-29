package com.trello.rxlifecycle2;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import p014io.reactivex.Observable;

public interface LifecycleProvider<E> {
    @CheckReturnValue
    @Nonnull
    <T> LifecycleTransformer<T> bindToLifecycle();

    @CheckReturnValue
    @Nonnull
    <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull E e);

    @CheckReturnValue
    @Nonnull
    Observable<E> lifecycle();
}
