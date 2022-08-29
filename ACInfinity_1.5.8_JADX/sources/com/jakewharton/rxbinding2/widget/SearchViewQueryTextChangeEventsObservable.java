package com.jakewharton.rxbinding2.widget;

import android.widget.SearchView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class SearchViewQueryTextChangeEventsObservable extends InitialValueObservable<SearchViewQueryTextEvent> {
    private final SearchView view;

    SearchViewQueryTextChangeEventsObservable(SearchView searchView) {
        this.view = searchView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super SearchViewQueryTextEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer);
            this.view.setOnQueryTextListener(listener);
            observer.onSubscribe(listener);
        }
    }

    /* access modifiers changed from: protected */
    public SearchViewQueryTextEvent getInitialValue() {
        SearchView searchView = this.view;
        return SearchViewQueryTextEvent.create(searchView, searchView.getQuery(), false);
    }

    static final class Listener extends MainThreadDisposable implements SearchView.OnQueryTextListener {
        private final Observer<? super SearchViewQueryTextEvent> observer;
        private final SearchView view;

        Listener(SearchView searchView, Observer<? super SearchViewQueryTextEvent> observer2) {
            this.view = searchView;
            this.observer = observer2;
        }

        public boolean onQueryTextChange(String str) {
            if (isDisposed()) {
                return false;
            }
            this.observer.onNext(SearchViewQueryTextEvent.create(this.view, str, false));
            return true;
        }

        public boolean onQueryTextSubmit(String str) {
            if (isDisposed()) {
                return false;
            }
            this.observer.onNext(SearchViewQueryTextEvent.create(this.view, str, true));
            return true;
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnQueryTextListener((SearchView.OnQueryTextListener) null);
        }
    }
}
