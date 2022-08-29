package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;
import p014io.reactivex.functions.Predicate;

final class AdapterViewItemLongClickEventObservable extends Observable<AdapterViewItemLongClickEvent> {
    private final Predicate<? super AdapterViewItemLongClickEvent> handled;
    private final AdapterView<?> view;

    AdapterViewItemLongClickEventObservable(AdapterView<?> adapterView, Predicate<? super AdapterViewItemLongClickEvent> predicate) {
        this.view = adapterView;
        this.handled = predicate;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super AdapterViewItemLongClickEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer, this.handled);
            observer.onSubscribe(listener);
            this.view.setOnItemLongClickListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements AdapterView.OnItemLongClickListener {
        private final Predicate<? super AdapterViewItemLongClickEvent> handled;
        private final Observer<? super AdapterViewItemLongClickEvent> observer;
        private final AdapterView<?> view;

        Listener(AdapterView<?> adapterView, Observer<? super AdapterViewItemLongClickEvent> observer2, Predicate<? super AdapterViewItemLongClickEvent> predicate) {
            this.view = adapterView;
            this.observer = observer2;
            this.handled = predicate;
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view2, int i, long j) {
            if (isDisposed()) {
                return false;
            }
            AdapterViewItemLongClickEvent create = AdapterViewItemLongClickEvent.create(adapterView, view2, i, j);
            try {
                if (!this.handled.test(create)) {
                    return false;
                }
                this.observer.onNext(create);
                return true;
            } catch (Exception e) {
                this.observer.onError(e);
                dispose();
                return false;
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) null);
        }
    }
}
