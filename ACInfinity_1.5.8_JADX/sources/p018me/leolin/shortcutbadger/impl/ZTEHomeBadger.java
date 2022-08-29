package p018me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import p018me.leolin.shortcutbadger.Badger;
import p018me.leolin.shortcutbadger.ShortcutBadgeException;

/* renamed from: me.leolin.shortcutbadger.impl.ZTEHomeBadger */
public class ZTEHomeBadger implements Badger {
    public void executeBadge(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        Bundle bundle = new Bundle();
        bundle.putInt("app_badge_count", i);
        bundle.putString("app_badge_component_name", componentName.flattenToString());
        if (Build.VERSION.SDK_INT >= 11) {
            context.getContentResolver().call(Uri.parse("content://com.android.launcher3.cornermark.unreadbadge"), "setAppUnreadCount", (String) null, bundle);
        }
    }

    public List<String> getSupportLaunchers() {
        return new ArrayList(0);
    }
}
