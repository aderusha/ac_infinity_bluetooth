package com.jakewharton.rxbinding2.internal;

import android.os.Looper;
import java.util.Objects;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposables;

public final class Preconditions {
    public static void checkNotNull(Object obj, String str) {
        Objects.requireNonNull(obj, str);
    }

    public static boolean checkMainThread(Observer<?> observer) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return true;
        }
        observer.onSubscribe(Disposables.empty());
        observer.onError(new IllegalStateException("Expected to be called on the main thread but was " + Thread.currentThread().getName()));
        return false;
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}
