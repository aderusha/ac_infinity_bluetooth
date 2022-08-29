package com.jakewharton.rxbinding2.widget;

import android.widget.SearchView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.functions.Consumer;

public final class RxSearchView {
    public static InitialValueObservable<SearchViewQueryTextEvent> queryTextChangeEvents(SearchView searchView) {
        Preconditions.checkNotNull(searchView, "view == null");
        return new SearchViewQueryTextChangeEventsObservable(searchView);
    }

    public static InitialValueObservable<CharSequence> queryTextChanges(SearchView searchView) {
        Preconditions.checkNotNull(searchView, "view == null");
        return new SearchViewQueryTextChangesObservable(searchView);
    }

    public static Consumer<? super CharSequence> query(final SearchView searchView, final boolean z) {
        Preconditions.checkNotNull(searchView, "view == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                searchView.setQuery(charSequence, z);
            }
        };
    }

    private RxSearchView() {
        throw new AssertionError("No instances.");
    }
}
