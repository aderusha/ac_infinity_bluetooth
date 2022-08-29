package com.jakewharton.rxbinding2.view;

import android.view.View;
import android.view.ViewGroup;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class ViewGroupHierarchyChangeEventObservable extends Observable<ViewGroupHierarchyChangeEvent> {
    private final ViewGroup viewGroup;

    ViewGroupHierarchyChangeEventObservable(ViewGroup viewGroup2) {
        this.viewGroup = viewGroup2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super ViewGroupHierarchyChangeEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.viewGroup, observer);
            observer.onSubscribe(listener);
            this.viewGroup.setOnHierarchyChangeListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements ViewGroup.OnHierarchyChangeListener {
        private final Observer<? super ViewGroupHierarchyChangeEvent> observer;
        private final ViewGroup viewGroup;

        Listener(ViewGroup viewGroup2, Observer<? super ViewGroupHierarchyChangeEvent> observer2) {
            this.viewGroup = viewGroup2;
            this.observer = observer2;
        }

        public void onChildViewAdded(View view, View view2) {
            if (!isDisposed()) {
                this.observer.onNext(ViewGroupHierarchyChildViewAddEvent.create(this.viewGroup, view2));
            }
        }

        public void onChildViewRemoved(View view, View view2) {
            if (!isDisposed()) {
                this.observer.onNext(ViewGroupHierarchyChildViewRemoveEvent.create(this.viewGroup, view2));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.viewGroup.setOnHierarchyChangeListener((ViewGroup.OnHierarchyChangeListener) null);
        }
    }
}
