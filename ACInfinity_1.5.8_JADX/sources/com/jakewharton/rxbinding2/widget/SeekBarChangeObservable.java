package com.jakewharton.rxbinding2.widget;

import android.widget.SeekBar;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class SeekBarChangeObservable extends InitialValueObservable<Integer> {
    private final Boolean shouldBeFromUser;
    private final SeekBar view;

    SeekBarChangeObservable(SeekBar seekBar, Boolean bool) {
        this.view = seekBar;
        this.shouldBeFromUser = bool;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super Integer> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, this.shouldBeFromUser, observer);
            this.view.setOnSeekBarChangeListener(listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    public Integer getInitialValue() {
        return Integer.valueOf(this.view.getProgress());
    }

    static final class Listener extends MainThreadDisposable implements SeekBar.OnSeekBarChangeListener {
        private final Observer<? super Integer> observer;
        private final Boolean shouldBeFromUser;
        private final SeekBar view;

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        Listener(SeekBar seekBar, Boolean bool, Observer<? super Integer> observer2) {
            this.view = seekBar;
            this.shouldBeFromUser = bool;
            this.observer = observer2;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (!isDisposed()) {
                Boolean bool = this.shouldBeFromUser;
                if (bool == null || bool.booleanValue() == z) {
                    this.observer.onNext(Integer.valueOf(i));
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
        }
    }
}
