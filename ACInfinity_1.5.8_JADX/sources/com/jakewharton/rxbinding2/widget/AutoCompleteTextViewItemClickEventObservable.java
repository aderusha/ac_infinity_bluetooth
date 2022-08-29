package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class AutoCompleteTextViewItemClickEventObservable extends Observable<AdapterViewItemClickEvent> {
    private final AutoCompleteTextView view;

    AutoCompleteTextViewItemClickEventObservable(AutoCompleteTextView autoCompleteTextView) {
        this.view = autoCompleteTextView;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super AdapterViewItemClickEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnItemClickListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements AdapterView.OnItemClickListener {
        private final Observer<? super AdapterViewItemClickEvent> observer;
        private final AutoCompleteTextView view;

        Listener(AutoCompleteTextView autoCompleteTextView, Observer<? super AdapterViewItemClickEvent> observer2) {
            this.view = autoCompleteTextView;
            this.observer = observer2;
        }

        public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
            if (!isDisposed()) {
                this.observer.onNext(AdapterViewItemClickEvent.create(adapterView, view2, i, j));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnItemClickListener((AdapterView.OnItemClickListener) null);
        }
    }
}
