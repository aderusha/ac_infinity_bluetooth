package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class AdapterViewItemSelectionObservable extends InitialValueObservable<Integer> {
    private final AdapterView<?> view;

    AdapterViewItemSelectionObservable(AdapterView<?> adapterView) {
        this.view = adapterView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super Integer> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnItemSelectedListener(listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    public Integer getInitialValue() {
        return Integer.valueOf(this.view.getSelectedItemPosition());
    }

    static final class Listener extends MainThreadDisposable implements AdapterView.OnItemSelectedListener {
        private final Observer<? super Integer> observer;
        private final AdapterView<?> view;

        Listener(AdapterView<?> adapterView, Observer<? super Integer> observer2) {
            this.view = adapterView;
            this.observer = observer2;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view2, int i, long j) {
            if (!isDisposed()) {
                this.observer.onNext(Integer.valueOf(i));
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            if (!isDisposed()) {
                this.observer.onNext(-1);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
        }
    }
}
