package com.jakewharton.rxbinding2.view;

import android.view.MenuItem;

public abstract class MenuItemActionViewExpandEvent extends MenuItemActionViewEvent {
    public static MenuItemActionViewExpandEvent create(MenuItem menuItem) {
        return new AutoValue_MenuItemActionViewExpandEvent(menuItem);
    }

    MenuItemActionViewExpandEvent() {
    }
}
