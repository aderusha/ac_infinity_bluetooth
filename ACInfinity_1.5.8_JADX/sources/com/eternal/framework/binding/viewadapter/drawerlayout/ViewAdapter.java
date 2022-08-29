package com.eternal.framework.binding.viewadapter.drawerlayout;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class ViewAdapter {
    public static void openDrawer(DrawerLayout drawerLayout, boolean z) {
        if (z) {
            drawerLayout.openDrawer((int) GravityCompat.START);
        } else {
            drawerLayout.closeDrawers();
        }
    }
}
