package com.jakewharton.rxbinding2.widget;

import android.database.DataSetObserver;
import android.widget.Adapter;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class AdapterDataChangeObservable<T extends Adapter> extends InitialValueObservable<T> {
    private final T adapter;

    AdapterDataChangeObservable(T t) {
        this.adapter = t;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super T> observer) {
        if (Preconditions.checkMainThread(observer)) {
            ObserverDisposable observerDisposable = new ObserverDisposable(this.adapter, observer);
            this.adapter.registerDataSetObserver(observerDisposable.dataSetObserver);
            observer.onSubscribe(observerDisposable);
        }
    }

    /* access modifiers changed from: protected */
    public T getInitialValue() {
        return this.adapter;
    }

    static final class ObserverDisposable<T extends Adapter> extends MainThreadDisposable {
        private final T adapter;
        final DataSetObserver dataSetObserver;

        ObserverDisposable(final T t, final Observer<? super T> observer) {
            this.adapter = t;
            this.dataSetObserver = new DataSetObserver() {
                public void onChanged() {
                    if (!ObserverDisposable.this.isDisposed()) {
                        observer.onNext(t);
                    }
                }
            };
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.adapter.unregisterDataSetObserver(this.dataSetObserver);
        }
    }
}
