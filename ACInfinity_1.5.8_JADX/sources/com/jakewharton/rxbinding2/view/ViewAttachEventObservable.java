package com.jakewharton.rxbinding2.view;

import android.view.View;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class ViewAttachEventObservable extends Observable<ViewAttachEvent> {
    private final View view;

    ViewAttachEventObservable(View view2) {
        this.view = view2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super ViewAttachEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.addOnAttachStateChangeListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements View.OnAttachStateChangeListener {
        private final Observer<? super ViewAttachEvent> observer;
        private final View view;

        Listener(View view2, Observer<? super ViewAttachEvent> observer2) {
            this.view = view2;
            this.observer = observer2;
        }

        public void onViewAttachedToWindow(View view2) {
            if (!isDisposed()) {
                this.observer.onNext(ViewAttachAttachedEvent.create(this.view));
            }
        }

        public void onViewDetachedFromWindow(View view2) {
            if (!isDisposed()) {
                this.observer.onNext(ViewAttachDetachedEvent.create(this.view));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.removeOnAttachStateChangeListener(this);
        }
    }
}
