package com.jakewharton.rxbinding2.widget;

import android.widget.RadioGroup;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.functions.Consumer;

public final class RxRadioGroup {
    public static InitialValueObservable<Integer> checkedChanges(RadioGroup radioGroup) {
        Preconditions.checkNotNull(radioGroup, "view == null");
        return new RadioGroupCheckedChangeObservable(radioGroup);
    }

    public static Consumer<? super Integer> checked(final RadioGroup radioGroup) {
        Preconditions.checkNotNull(radioGroup, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                if (num.intValue() == -1) {
                    radioGroup.clearCheck();
                } else {
                    radioGroup.check(num.intValue());
                }
            }
        };
    }

    private RxRadioGroup() {
        throw new AssertionError("No instances.");
    }
}
