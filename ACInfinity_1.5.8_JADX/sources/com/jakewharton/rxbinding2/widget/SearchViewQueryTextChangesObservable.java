package com.jakewharton.rxbinding2.widget;

import android.widget.SearchView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class SearchViewQueryTextChangesObservable extends InitialValueObservable<CharSequence> {
    private final SearchView view;

    SearchViewQueryTextChangesObservable(SearchView searchView) {
        this.view = searchView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super CharSequence> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnQueryTextListener(listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    public CharSequence getInitialValue() {
        return this.view.getQuery();
    }

    static final class Listener extends MainThreadDisposable implements SearchView.OnQueryTextListener {
        private final Observer<? super CharSequence> observer;
        private final SearchView view;

        public boolean onQueryTextSubmit(String str) {
            return false;
        }

        Listener(SearchView searchView, Observer<? super CharSequence> observer2) {
            this.view = searchView;
            this.observer = observer2;
        }

        public boolean onQueryTextChange(String str) {
            if (isDisposed()) {
                return false;
            }
            this.observer.onNext(str);
            return true;
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnQueryTextListener((SearchView.OnQueryTextListener) null);
        }
    }
}
