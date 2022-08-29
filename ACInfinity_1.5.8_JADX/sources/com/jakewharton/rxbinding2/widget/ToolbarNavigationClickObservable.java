package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.Toolbar;
import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class ToolbarNavigationClickObservable extends Observable<Object> {
    private final Toolbar view;

    ToolbarNavigationClickObservable(Toolbar toolbar) {
        this.view = toolbar;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super Object> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setNavigationOnClickListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements View.OnClickListener {
        private final Observer<? super Object> observer;
        private final Toolbar view;

        Listener(Toolbar toolbar, Observer<? super Object> observer2) {
            this.view = toolbar;
            this.observer = observer2;
        }

        public void onClick(View view2) {
            if (!isDisposed()) {
                this.observer.onNext(Notification.INSTANCE);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setNavigationOnClickListener((View.OnClickListener) null);
        }
    }
}
