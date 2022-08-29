package com.jakewharton.rxbinding2.view;

import android.view.MenuItem;
import java.util.Objects;

final class AutoValue_MenuItemActionViewCollapseEvent extends MenuItemActionViewCollapseEvent {
    private final MenuItem menuItem;

    AutoValue_MenuItemActionViewCollapseEvent(MenuItem menuItem2) {
        Objects.requireNonNull(menuItem2, "Null menuItem");
        this.menuItem = menuItem2;
    }

    public MenuItem menuItem() {
        return this.menuItem;
    }

    public String toString() {
        return "MenuItemActionViewCollapseEvent{menuItem=" + this.menuItem + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MenuItemActionViewCollapseEvent) {
            return this.menuItem.equals(((MenuItemActionViewCollapseEvent) obj).menuItem());
        }
        return false;
    }

    public int hashCode() {
        return this.menuItem.hashCode() ^ 1000003;
    }
}
