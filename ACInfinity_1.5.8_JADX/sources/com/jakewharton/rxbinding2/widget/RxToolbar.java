package com.jakewharton.rxbinding2.widget;

import android.view.MenuItem;
import android.widget.Toolbar;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.functions.Consumer;

public final class RxToolbar {
    public static Observable<MenuItem> itemClicks(Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new ToolbarItemClickObservable(toolbar);
    }

    public static Observable<Object> navigationClicks(Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new ToolbarNavigationClickObservable(toolbar);
    }

    public static Consumer<? super CharSequence> title(final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                toolbar.setTitle(charSequence);
            }
        };
    }

    public static Consumer<? super Integer> titleRes(final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                toolbar.setTitle(num.intValue());
            }
        };
    }

    public static Consumer<? super CharSequence> subtitle(final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                toolbar.setSubtitle(charSequence);
            }
        };
    }

    public static Consumer<? super Integer> subtitleRes(final Toolbar toolbar) {
        Preconditions.checkNotNull(toolbar, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                toolbar.setSubtitle(num.intValue());
            }
        };
    }

    private RxToolbar() {
        throw new AssertionError("No instances.");
    }
}
