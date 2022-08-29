package com.jakewharton.rxbinding2.widget;

import android.widget.TextSwitcher;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.functions.Consumer;

public final class RxTextSwitcher {
    public static Consumer<? super CharSequence> text(final TextSwitcher textSwitcher) {
        Preconditions.checkNotNull(textSwitcher, "view == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                textSwitcher.setText(charSequence);
            }
        };
    }

    public static Consumer<? super CharSequence> currentText(final TextSwitcher textSwitcher) {
        Preconditions.checkNotNull(textSwitcher, "view == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                textSwitcher.setCurrentText(charSequence);
            }
        };
    }

    private RxTextSwitcher() {
        throw new AssertionError("No instances.");
    }
}
