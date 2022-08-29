package p018me.leolin.shortcutbadger;

import android.content.ComponentName;
import android.content.Context;
import java.util.List;

/* renamed from: me.leolin.shortcutbadger.Badger */
public interface Badger {
    void executeBadge(Context context, ComponentName componentName, int i) throws ShortcutBadgeException;

    List<String> getSupportLaunchers();
}
