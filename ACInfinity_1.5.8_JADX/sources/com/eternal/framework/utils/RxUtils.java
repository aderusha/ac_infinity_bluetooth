package com.eternal.framework.utils;

import android.content.Context;
import androidx.fragment.app.Fragment;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import java.lang.reflect.InvocationTargetException;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.ObservableTransformer;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class RxUtils {
    private static final String METHOD = "handleException";

    public static <T> LifecycleTransformer<T> bindToLifecycle(Context context) {
        if (context instanceof LifecycleProvider) {
            return ((LifecycleProvider) context).bindToLifecycle();
        }
        throw new IllegalArgumentException("context not the LifecycleProvider type");
    }

    public static LifecycleTransformer bindToLifecycle(Fragment fragment) {
        if (fragment instanceof LifecycleProvider) {
            return ((LifecycleProvider) fragment).bindToLifecycle();
        }
        throw new IllegalArgumentException("fragment not the LifecycleProvider type");
    }

    public static <T> LifecycleTransformer<T> bindToLifecycle(LifecycleProvider lifecycleProvider) {
        return lifecycleProvider.bindToLifecycle();
    }

    public static <T> ObservableTransformer<T, T> schedulersTransformer() {
        return new ObservableTransformer<T, T>() {
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> exceptionTransformer(final Class cls) {
        return new ObservableTransformer<T, T>() {
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.onErrorResumeNext((Function<? super Throwable, ? extends ObservableSource<? extends T>>) new Function<Throwable, ObservableSource<? extends T>>() {
                    public ObservableSource<? extends T> apply(Throwable th) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
                        return Observable.error((Throwable) cls.getMethod(RxUtils.METHOD, new Class[]{Throwable.class}).invoke(cls, new Object[]{th}));
                    }
                });
            }
        };
    }
}
