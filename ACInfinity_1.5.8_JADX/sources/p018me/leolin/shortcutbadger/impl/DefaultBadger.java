package p018me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import java.util.Arrays;
import java.util.List;
import p018me.leolin.shortcutbadger.Badger;
import p018me.leolin.shortcutbadger.ShortcutBadgeException;
import p018me.leolin.shortcutbadger.util.BroadcastHelper;

/* renamed from: me.leolin.shortcutbadger.impl.DefaultBadger */
public class DefaultBadger implements Badger {
    private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
    private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
    private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
    private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";

    public void executeBadge(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra(INTENT_EXTRA_BADGE_COUNT, i);
        intent.putExtra(INTENT_EXTRA_PACKAGENAME, componentName.getPackageName());
        intent.putExtra(INTENT_EXTRA_ACTIVITY_NAME, componentName.getClassName());
        BroadcastHelper.sendDefaultIntentExplicitly(context, intent);
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"fr.neamar.kiss", "com.quaap.launchtime", "com.quaap.launchtime_official"});
    }

    /* access modifiers changed from: package-private */
    public boolean isSupported(Context context) {
        return BroadcastHelper.resolveBroadcast(context, new Intent("android.intent.action.BADGE_COUNT_UPDATE")).size() > 0 || (Build.VERSION.SDK_INT >= 26 && BroadcastHelper.resolveBroadcast(context, new Intent(IntentConstants.DEFAULT_OREO_INTENT_ACTION)).size() > 0);
    }
}
