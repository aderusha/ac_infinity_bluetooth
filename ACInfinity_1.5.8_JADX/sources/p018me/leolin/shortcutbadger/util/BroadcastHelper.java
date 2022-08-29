package p018me.leolin.shortcutbadger.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import java.util.Collections;
import java.util.List;
import p018me.leolin.shortcutbadger.ShortcutBadgeException;
import p018me.leolin.shortcutbadger.impl.IntentConstants;

/* renamed from: me.leolin.shortcutbadger.util.BroadcastHelper */
public class BroadcastHelper {
    public static List<ResolveInfo> resolveBroadcast(Context context, Intent intent) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        return queryBroadcastReceivers != null ? queryBroadcastReceivers : Collections.emptyList();
    }

    public static void sendIntentExplicitly(Context context, Intent intent) throws ShortcutBadgeException {
        List<ResolveInfo> resolveBroadcast = resolveBroadcast(context, intent);
        if (resolveBroadcast.size() != 0) {
            for (ResolveInfo next : resolveBroadcast) {
                Intent intent2 = new Intent(intent);
                if (next != null) {
                    intent2.setPackage(next.resolvePackageName);
                    context.sendBroadcast(intent2);
                }
            }
            return;
        }
        throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
    }

    public static void sendDefaultIntentExplicitly(Context context, Intent intent) throws ShortcutBadgeException {
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 26) {
            Intent intent2 = new Intent(intent);
            intent2.setAction(IntentConstants.DEFAULT_OREO_INTENT_ACTION);
            try {
                sendIntentExplicitly(context, intent2);
                z = true;
            } catch (ShortcutBadgeException unused) {
            }
        }
        if (!z) {
            sendIntentExplicitly(context, intent);
        }
    }
}
