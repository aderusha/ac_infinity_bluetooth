package com.jakewharton.rxbinding2.view;

import android.view.ViewGroup;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;

public final class RxViewGroup {
    public static Observable<ViewGroupHierarchyChangeEvent> changeEvents(ViewGroup viewGroup) {
        Preconditions.checkNotNull(viewGroup, "viewGroup == null");
        return new ViewGroupHierarchyChangeEventObservable(viewGroup);
    }

    private RxViewGroup() {
        throw new AssertionError("No instances.");
    }
}
