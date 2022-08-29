package com.jakewharton.rxbinding2.widget;

import android.widget.SeekBar;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class SeekBarChangeEventObservable extends InitialValueObservable<SeekBarChangeEvent> {
    private final SeekBar view;

    SeekBarChangeEventObservable(SeekBar seekBar) {
        this.view = seekBar;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super SeekBarChangeEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnSeekBarChangeListener(listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    public SeekBarChangeEvent getInitialValue() {
        SeekBar seekBar = this.view;
        return SeekBarProgressChangeEvent.create(seekBar, seekBar.getProgress(), false);
    }

    static final class Listener extends MainThreadDisposable implements SeekBar.OnSeekBarChangeListener {
        private final Observer<? super SeekBarChangeEvent> observer;
        private final SeekBar view;

        Listener(SeekBar seekBar, Observer<? super SeekBarChangeEvent> observer2) {
            this.view = seekBar;
            this.observer = observer2;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (!isDisposed()) {
                this.observer.onNext(SeekBarProgressChangeEvent.create(seekBar, i, z));
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            if (!isDisposed()) {
                this.observer.onNext(SeekBarStartChangeEvent.create(seekBar));
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (!isDisposed()) {
                this.observer.onNext(SeekBarStopChangeEvent.create(seekBar));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
        }
    }
}
