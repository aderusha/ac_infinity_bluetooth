package com.jakewharton.rxbinding2.view;

import android.view.MenuItem;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;
import p014io.reactivex.functions.Predicate;

final class MenuItemActionViewEventObservable extends Observable<MenuItemActionViewEvent> {
    private final Predicate<? super MenuItemActionViewEvent> handled;
    private final MenuItem menuItem;

    MenuItemActionViewEventObservable(MenuItem menuItem2, Predicate<? super MenuItemActionViewEvent> predicate) {
        this.menuItem = menuItem2;
        this.handled = predicate;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super MenuItemActionViewEvent> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.menuItem, this.handled, observer);
            observer.onSubscribe(listener);
            this.menuItem.setOnActionExpandListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements MenuItem.OnActionExpandListener {
        private final Predicate<? super MenuItemActionViewEvent> handled;
        private final MenuItem menuItem;
        private final Observer<? super MenuItemActionViewEvent> observer;

        Listener(MenuItem menuItem2, Predicate<? super MenuItemActionViewEvent> predicate, Observer<? super MenuItemActionViewEvent> observer2) {
            this.menuItem = menuItem2;
            this.handled = predicate;
            this.observer = observer2;
        }

        public boolean onMenuItemActionExpand(MenuItem menuItem2) {
            return onEvent(MenuItemActionViewExpandEvent.create(menuItem2));
        }

        public boolean onMenuItemActionCollapse(MenuItem menuItem2) {
            return onEvent(MenuItemActionViewCollapseEvent.create(menuItem2));
        }

        private boolean onEvent(MenuItemActionViewEvent menuItemActionViewEvent) {
            if (isDisposed()) {
                return false;
            }
            try {
                if (!this.handled.test(menuItemActionViewEvent)) {
                    return false;
                }
                this.observer.onNext(menuItemActionViewEvent);
                return true;
            } catch (Exception e) {
                this.observer.onError(e);
                dispose();
                return false;
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.menuItem.setOnActionExpandListener((MenuItem.OnActionExpandListener) null);
        }
    }
}
