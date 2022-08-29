package com.jakewharton.rxbinding2.widget;

import android.widget.RatingBar;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class RatingBarRatingChangeEventObservable extends InitialValueObservable<RatingBarChangeEvent> {
    private final RatingBar view;

    RatingBarRatingChangeEventObservable(RatingBar ratingBar) {
        this.view = ratingBar;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super RatingBarChangeEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnRatingBarChangeListener(listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    public RatingBarChangeEvent getInitialValue() {
        RatingBar ratingBar = this.view;
        return RatingBarChangeEvent.create(ratingBar, ratingBar.getRating(), false);
    }

    static final class Listener extends MainThreadDisposable implements RatingBar.OnRatingBarChangeListener {
        private final Observer<? super RatingBarChangeEvent> observer;
        private final RatingBar view;

        Listener(RatingBar ratingBar, Observer<? super RatingBarChangeEvent> observer2) {
            this.view = ratingBar;
            this.observer = observer2;
        }

        public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
            if (!isDisposed()) {
                this.observer.onNext(RatingBarChangeEvent.create(ratingBar, f, z));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnRatingBarChangeListener((RatingBar.OnRatingBarChangeListener) null);
        }
    }
}
