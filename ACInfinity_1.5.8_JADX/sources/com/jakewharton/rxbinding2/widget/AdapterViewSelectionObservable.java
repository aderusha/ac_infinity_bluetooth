package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class AdapterViewSelectionObservable extends InitialValueObservable<AdapterViewSelectionEvent> {
    private final AdapterView<?> view;

    AdapterViewSelectionObservable(AdapterView<?> adapterView) {
        this.view = adapterView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super AdapterViewSelectionEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnItemSelectedListener(listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    public AdapterViewSelectionEvent getInitialValue() {
        int selectedItemPosition = this.view.getSelectedItemPosition();
        if (selectedItemPosition == -1) {
            return AdapterViewNothingSelectionEvent.create(this.view);
        }
        return AdapterViewItemSelectionEvent.create(this.view, this.view.getSelectedView(), selectedItemPosition, this.view.getSelectedItemId());
    }

    static final class Listener extends MainThreadDisposable implements AdapterView.OnItemSelectedListener {
        private final Observer<? super AdapterViewSelectionEvent> observer;
        private final AdapterView<?> view;

        Listener(AdapterView<?> adapterView, Observer<? super AdapterViewSelectionEvent> observer2) {
            this.view = adapterView;
            this.observer = observer2;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view2, int i, long j) {
            if (!isDisposed()) {
                this.observer.onNext(AdapterViewItemSelectionEvent.create(adapterView, view2, i, j));
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            if (!isDisposed()) {
                this.observer.onNext(AdapterViewNothingSelectionEvent.create(adapterView));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
        }
    }
}
