package com.jakewharton.rxbinding2.widget;

import android.widget.Adapter;
import android.widget.AdapterView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Functions;
import com.jakewharton.rxbinding2.internal.Preconditions;
import java.util.concurrent.Callable;
import p014io.reactivex.Observable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Predicate;

public final class RxAdapterView {
    public static <T extends Adapter> InitialValueObservable<Integer> itemSelections(AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return new AdapterViewItemSelectionObservable(adapterView);
    }

    public static <T extends Adapter> InitialValueObservable<AdapterViewSelectionEvent> selectionEvents(AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return new AdapterViewSelectionObservable(adapterView);
    }

    public static <T extends Adapter> Observable<Integer> itemClicks(AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return new AdapterViewItemClickObservable(adapterView);
    }

    public static <T extends Adapter> Observable<AdapterViewItemClickEvent> itemClickEvents(AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return new AdapterViewItemClickEventObservable(adapterView);
    }

    public static <T extends Adapter> Observable<Integer> itemLongClicks(AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return itemLongClicks(adapterView, Functions.CALLABLE_ALWAYS_TRUE);
    }

    public static <T extends Adapter> Observable<Integer> itemLongClicks(AdapterView<T> adapterView, Callable<Boolean> callable) {
        Preconditions.checkNotNull(adapterView, "view == null");
        Preconditions.checkNotNull(callable, "handled == null");
        return new AdapterViewItemLongClickObservable(adapterView, callable);
    }

    public static <T extends Adapter> Observable<AdapterViewItemLongClickEvent> itemLongClickEvents(AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return itemLongClickEvents(adapterView, Functions.PREDICATE_ALWAYS_TRUE);
    }

    public static <T extends Adapter> Observable<AdapterViewItemLongClickEvent> itemLongClickEvents(AdapterView<T> adapterView, Predicate<? super AdapterViewItemLongClickEvent> predicate) {
        Preconditions.checkNotNull(adapterView, "view == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new AdapterViewItemLongClickEventObservable(adapterView, predicate);
    }

    public static <T extends Adapter> Consumer<? super Integer> selection(final AdapterView<T> adapterView) {
        Preconditions.checkNotNull(adapterView, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                adapterView.setSelection(num.intValue());
            }
        };
    }

    private RxAdapterView() {
        throw new AssertionError("No instances.");
    }
}
