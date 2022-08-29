package com.jakewharton.rxbinding2.view;

import android.view.View;
import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class ViewAttachesObservable extends Observable<Object> {
    private final boolean callOnAttach;
    private final View view;

    ViewAttachesObservable(View view2, boolean z) {
        this.view = view2;
        this.callOnAttach = z;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super Object> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, this.callOnAttach, observer);
            observer.onSubscribe(listener);
            this.view.addOnAttachStateChangeListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements View.OnAttachStateChangeListener {
        private final boolean callOnAttach;
        private final Observer<? super Object> observer;
        private final View view;

        Listener(View view2, boolean z, Observer<? super Object> observer2) {
            this.view = view2;
            this.callOnAttach = z;
            this.observer = observer2;
        }

        public void onViewAttachedToWindow(View view2) {
            if (this.callOnAttach && !isDisposed()) {
                this.observer.onNext(Notification.INSTANCE);
            }
        }

        public void onViewDetachedFromWindow(View view2) {
            if (!this.callOnAttach && !isDisposed()) {
                this.observer.onNext(Notification.INSTANCE);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.removeOnAttachStateChangeListener(this);
        }
    }
}
