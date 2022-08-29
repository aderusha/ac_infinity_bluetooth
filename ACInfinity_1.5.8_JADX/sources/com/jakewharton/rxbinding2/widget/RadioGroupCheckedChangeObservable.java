package com.jakewharton.rxbinding2.widget;

import android.widget.RadioGroup;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class RadioGroupCheckedChangeObservable extends InitialValueObservable<Integer> {
    private final RadioGroup view;

    RadioGroupCheckedChangeObservable(RadioGroup radioGroup) {
        this.view = radioGroup;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super Integer> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnCheckedChangeListener(listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    public Integer getInitialValue() {
        return Integer.valueOf(this.view.getCheckedRadioButtonId());
    }

    static final class Listener extends MainThreadDisposable implements RadioGroup.OnCheckedChangeListener {
        private int lastChecked = -1;
        private final Observer<? super Integer> observer;
        private final RadioGroup view;

        Listener(RadioGroup radioGroup, Observer<? super Integer> observer2) {
            this.view = radioGroup;
            this.observer = observer2;
        }

        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (!isDisposed() && i != this.lastChecked) {
                this.lastChecked = i;
                this.observer.onNext(Integer.valueOf(i));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) null);
        }
    }
}
