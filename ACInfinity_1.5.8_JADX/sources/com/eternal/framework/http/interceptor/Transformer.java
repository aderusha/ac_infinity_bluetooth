package com.eternal.framework.http.interceptor;

import com.eternal.framework.http.interfaces.ILoadingView;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.ObservableTransformer;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class Transformer {
    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return switchSchedulers((ILoadingView) null);
    }

    public static <T> ObservableTransformer<T, T> switchSchedulers(final ILoadingView iLoadingView) {
        return new ObservableTransformer<T, T>() {
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.m983io()).unsubscribeOn(Schedulers.m983io()).doOnSubscribe(new Consumer<Disposable>() {
                    public void accept(Disposable disposable) throws Exception {
                        if (ILoadingView.this != null) {
                            ILoadingView.this.showLoadingView();
                        }
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).doFinally(new Action() {
                    public void run() throws Exception {
                        if (ILoadingView.this != null) {
                            ILoadingView.this.hideLoadingView();
                        }
                    }
                });
            }
        };
    }
}
