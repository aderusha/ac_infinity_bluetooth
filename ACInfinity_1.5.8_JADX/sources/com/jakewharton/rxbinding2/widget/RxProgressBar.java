package com.jakewharton.rxbinding2.widget;

import android.widget.ProgressBar;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.functions.Consumer;

public final class RxProgressBar {
    public static Consumer<? super Integer> incrementProgressBy(final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                progressBar.incrementProgressBy(num.intValue());
            }
        };
    }

    public static Consumer<? super Integer> incrementSecondaryProgressBy(final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                progressBar.incrementSecondaryProgressBy(num.intValue());
            }
        };
    }

    public static Consumer<? super Boolean> indeterminate(final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                progressBar.setIndeterminate(bool.booleanValue());
            }
        };
    }

    public static Consumer<? super Integer> max(final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                progressBar.setMax(num.intValue());
            }
        };
    }

    public static Consumer<? super Integer> progress(final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                progressBar.setProgress(num.intValue());
            }
        };
    }

    public static Consumer<? super Integer> secondaryProgress(final ProgressBar progressBar) {
        Preconditions.checkNotNull(progressBar, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                progressBar.setSecondaryProgress(num.intValue());
            }
        };
    }

    private RxProgressBar() {
        throw new AssertionError("No instances.");
    }
}
