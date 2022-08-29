package p018me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import java.util.Collections;
import java.util.List;
import p018me.leolin.shortcutbadger.Badger;
import p018me.leolin.shortcutbadger.ShortcutBadgeException;
import p018me.leolin.shortcutbadger.util.BroadcastHelper;

/* renamed from: me.leolin.shortcutbadger.impl.OPPOHomeBader */
public class OPPOHomeBader implements Badger {
    private static final String INTENT_ACTION = "com.oppo.unsettledevent";
    private static final String INTENT_EXTRA_BADGEUPGRADE_COUNT = "app_badge_count";
    private static final String INTENT_EXTRA_BADGE_COUNT = "number";
    private static final String INTENT_EXTRA_BADGE_UPGRADENUMBER = "upgradeNumber";
    private static final String INTENT_EXTRA_PACKAGENAME = "pakeageName";
    private static final String PROVIDER_CONTENT_URI = "content://com.android.badge/badge";
    private int mCurrentTotalCount = -1;

    public void executeBadge(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        if (this.mCurrentTotalCount != i) {
            this.mCurrentTotalCount = i;
            if (Build.VERSION.SDK_INT >= 11) {
                executeBadgeByContentProvider(context, i);
            } else {
                executeBadgeByBroadcast(context, componentName, i);
            }
        }
    }

    public List<String> getSupportLaunchers() {
        return Collections.singletonList("com.oppo.launcher");
    }

    private void executeBadgeByBroadcast(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        if (i == 0) {
            i = -1;
        }
        Intent intent = new Intent(INTENT_ACTION);
        intent.putExtra(INTENT_EXTRA_PACKAGENAME, componentName.getPackageName());
        intent.putExtra(INTENT_EXTRA_BADGE_COUNT, i);
        intent.putExtra(INTENT_EXTRA_BADGE_UPGRADENUMBER, i);
        BroadcastHelper.sendIntentExplicitly(context, intent);
    }

    private void executeBadgeByContentProvider(Context context, int i) throws ShortcutBadgeException {
        try {
            Bundle bundle = new Bundle();
            bundle.putInt(INTENT_EXTRA_BADGEUPGRADE_COUNT, i);
            context.getContentResolver().call(Uri.parse(PROVIDER_CONTENT_URI), "setAppBadgeCount", (String) null, bundle);
        } catch (Throwable unused) {
            throw new ShortcutBadgeException("Unable to execute Badge By Content Provider");
        }
    }
}
