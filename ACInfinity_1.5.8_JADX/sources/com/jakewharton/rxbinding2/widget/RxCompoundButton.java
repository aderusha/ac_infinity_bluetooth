package com.jakewharton.rxbinding2.widget;

import android.widget.CompoundButton;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.functions.Consumer;

public final class RxCompoundButton {
    public static InitialValueObservable<Boolean> checkedChanges(CompoundButton compoundButton) {
        Preconditions.checkNotNull(compoundButton, "view == null");
        return new CompoundButtonCheckedChangeObservable(compoundButton);
    }

    public static Consumer<? super Boolean> checked(final CompoundButton compoundButton) {
        Preconditions.checkNotNull(compoundButton, "view == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                compoundButton.setChecked(bool.booleanValue());
            }
        };
    }

    public static Consumer<? super Object> toggle(final CompoundButton compoundButton) {
        Preconditions.checkNotNull(compoundButton, "view == null");
        return new Consumer<Object>() {
            public void accept(Object obj) {
                compoundButton.toggle();
            }
        };
    }

    private RxCompoundButton() {
        throw new AssertionError("No instances.");
    }
}
