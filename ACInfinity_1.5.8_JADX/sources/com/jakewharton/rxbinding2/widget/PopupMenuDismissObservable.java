package com.jakewharton.rxbinding2.widget;

import android.widget.PopupMenu;
import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class PopupMenuDismissObservable extends Observable<Object> {
    private final PopupMenu view;

    PopupMenuDismissObservable(PopupMenu popupMenu) {
        this.view = popupMenu;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super Object> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnDismissListener(listener);
            observer.onSubscribe(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements PopupMenu.OnDismissListener {
        private final Observer<? super Object> observer;
        private final PopupMenu view;

        Listener(PopupMenu popupMenu, Observer<? super Object> observer2) {
            this.view = popupMenu;
            this.observer = observer2;
        }

        public void onDismiss(PopupMenu popupMenu) {
            if (!isDisposed()) {
                this.observer.onNext(Notification.INSTANCE);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnDismissListener((PopupMenu.OnDismissListener) null);
        }
    }
}
