package com.jakewharton.rxbinding2.widget;

import android.widget.AbsListView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class AbsListViewScrollEventObservable extends Observable<AbsListViewScrollEvent> {
    private final AbsListView view;

    AbsListViewScrollEventObservable(AbsListView absListView) {
        this.view = absListView;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super AbsListViewScrollEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            observer.onSubscribe(listener);
            this.view.setOnScrollListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements AbsListView.OnScrollListener {
        private int currentScrollState = 0;
        private final Observer<? super AbsListViewScrollEvent> observer;
        private final AbsListView view;

        Listener(AbsListView absListView, Observer<? super AbsListViewScrollEvent> observer2) {
            this.view = absListView;
            this.observer = observer2;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            this.currentScrollState = i;
            if (!isDisposed()) {
                AbsListView absListView2 = this.view;
                this.observer.onNext(AbsListViewScrollEvent.create(absListView2, i, absListView2.getFirstVisiblePosition(), this.view.getChildCount(), this.view.getCount()));
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.observer.onNext(AbsListViewScrollEvent.create(this.view, this.currentScrollState, i, i2, i3));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnScrollListener((AbsListView.OnScrollListener) null);
        }
    }
}
