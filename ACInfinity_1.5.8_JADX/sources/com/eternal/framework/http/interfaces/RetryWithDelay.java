package com.eternal.framework.http.interfaces;

import java.util.concurrent.TimeUnit;
import p014io.reactivex.Observable;
import p014io.reactivex.functions.Function;

public class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {
    /* access modifiers changed from: private */
    public final int maxRetries;
    private int retryCount = 0;
    /* access modifiers changed from: private */
    public final int retryDelayMillis;

    static /* synthetic */ int access$004(RetryWithDelay retryWithDelay) {
        int i = retryWithDelay.retryCount + 1;
        retryWithDelay.retryCount = i;
        return i;
    }

    public RetryWithDelay(int i, int i2) {
        this.maxRetries = i;
        this.retryDelayMillis = i2;
    }

    public Observable<?> apply(Observable<? extends Throwable> observable) {
        return observable.flatMap(new Function<Throwable, Observable<?>>() {
            public Observable<?> apply(Throwable th) {
                if (RetryWithDelay.access$004(RetryWithDelay.this) < RetryWithDelay.this.maxRetries) {
                    return Observable.timer((long) RetryWithDelay.this.retryDelayMillis, TimeUnit.MILLISECONDS);
                }
                return Observable.error(th);
            }
        });
    }
}
