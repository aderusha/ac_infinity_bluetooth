package com.trello.rxlifecycle2.android;

import android.view.View;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.android.MainThreadDisposable;

final class ViewDetachesOnSubscribe implements ObservableOnSubscribe<Object> {
    static final Object SIGNAL = new Object();
    final View view;

    public ViewDetachesOnSubscribe(View view2) {
        this.view = view2;
    }

    public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
        MainThreadDisposable.verifyMainThread();
        EmitterListener emitterListener = new EmitterListener(observableEmitter);
        observableEmitter.setDisposable(emitterListener);
        this.view.addOnAttachStateChangeListener(emitterListener);
    }

    class EmitterListener extends MainThreadDisposable implements View.OnAttachStateChangeListener {
        final ObservableEmitter<Object> emitter;

        public void onViewAttachedToWindow(View view) {
        }

        public EmitterListener(ObservableEmitter<Object> observableEmitter) {
            this.emitter = observableEmitter;
        }

        public void onViewDetachedFromWindow(View view) {
            this.emitter.onNext(ViewDetachesOnSubscribe.SIGNAL);
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            ViewDetachesOnSubscribe.this.view.removeOnAttachStateChangeListener(this);
        }
    }
}
