package com.jakewharton.rxbinding2.view;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import com.jakewharton.rxbinding2.internal.Functions;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Predicate;

public final class RxMenuItem {
    public static Observable<Object> clicks(MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new MenuItemClickOnSubscribe(menuItem, Functions.PREDICATE_ALWAYS_TRUE);
    }

    public static Observable<Object> clicks(MenuItem menuItem, Predicate<? super MenuItem> predicate) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new MenuItemClickOnSubscribe(menuItem, predicate);
    }

    public static Observable<MenuItemActionViewEvent> actionViewEvents(MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new MenuItemActionViewEventObservable(menuItem, Functions.PREDICATE_ALWAYS_TRUE);
    }

    public static Observable<MenuItemActionViewEvent> actionViewEvents(MenuItem menuItem, Predicate<? super MenuItemActionViewEvent> predicate) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        Preconditions.checkNotNull(predicate, "handled == null");
        return new MenuItemActionViewEventObservable(menuItem, predicate);
    }

    @Deprecated
    public static Consumer<? super Boolean> checked(final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                menuItem.setChecked(bool.booleanValue());
            }
        };
    }

    @Deprecated
    public static Consumer<? super Boolean> enabled(final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                menuItem.setEnabled(bool.booleanValue());
            }
        };
    }

    @Deprecated
    public static Consumer<? super Drawable> icon(final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Consumer<Drawable>() {
            public void accept(Drawable drawable) {
                menuItem.setIcon(drawable);
            }
        };
    }

    @Deprecated
    public static Consumer<? super Integer> iconRes(final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                menuItem.setIcon(num.intValue());
            }
        };
    }

    @Deprecated
    public static Consumer<? super CharSequence> title(final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                menuItem.setTitle(charSequence);
            }
        };
    }

    @Deprecated
    public static Consumer<? super Integer> titleRes(final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                menuItem.setTitle(num.intValue());
            }
        };
    }

    @Deprecated
    public static Consumer<? super Boolean> visible(final MenuItem menuItem) {
        Preconditions.checkNotNull(menuItem, "menuItem == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                menuItem.setVisible(bool.booleanValue());
            }
        };
    }

    private RxMenuItem() {
        throw new AssertionError("No instances.");
    }
}
