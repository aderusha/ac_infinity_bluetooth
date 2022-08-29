package p018me.leolin.shortcutbadger;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import p018me.leolin.shortcutbadger.impl.AdwHomeBadger;
import p018me.leolin.shortcutbadger.impl.ApexHomeBadger;
import p018me.leolin.shortcutbadger.impl.AsusHomeBadger;
import p018me.leolin.shortcutbadger.impl.DefaultBadger;
import p018me.leolin.shortcutbadger.impl.EverythingMeHomeBadger;
import p018me.leolin.shortcutbadger.impl.HuaweiHomeBadger;
import p018me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import p018me.leolin.shortcutbadger.impl.NovaHomeBadger;
import p018me.leolin.shortcutbadger.impl.OPPOHomeBader;
import p018me.leolin.shortcutbadger.impl.SamsungHomeBadger;
import p018me.leolin.shortcutbadger.impl.SonyHomeBadger;
import p018me.leolin.shortcutbadger.impl.VivoHomeBadger;
import p018me.leolin.shortcutbadger.impl.ZTEHomeBadger;
import p018me.leolin.shortcutbadger.impl.ZukHomeBadger;

/* renamed from: me.leolin.shortcutbadger.ShortcutBadger */
public final class ShortcutBadger {
    private static final List<Class<? extends Badger>> BADGERS;
    private static final String LOG_TAG = "ShortcutBadger";
    private static final int SUPPORTED_CHECK_ATTEMPTS = 3;
    private static ComponentName sComponentName;
    private static final Object sCounterSupportedLock = new Object();
    private static volatile Boolean sIsBadgeCounterSupported;
    private static Badger sShortcutBadger;

    static {
        LinkedList linkedList = new LinkedList();
        BADGERS = linkedList;
        linkedList.add(AdwHomeBadger.class);
        linkedList.add(ApexHomeBadger.class);
        linkedList.add(DefaultBadger.class);
        linkedList.add(NewHtcHomeBadger.class);
        linkedList.add(NovaHomeBadger.class);
        linkedList.add(SonyHomeBadger.class);
        linkedList.add(AsusHomeBadger.class);
        linkedList.add(HuaweiHomeBadger.class);
        linkedList.add(OPPOHomeBader.class);
        linkedList.add(SamsungHomeBadger.class);
        linkedList.add(ZukHomeBadger.class);
        linkedList.add(VivoHomeBadger.class);
        linkedList.add(ZTEHomeBadger.class);
        linkedList.add(EverythingMeHomeBadger.class);
    }

    public static boolean applyCount(Context context, int i) {
        try {
            applyCountOrThrow(context, i);
            return true;
        } catch (ShortcutBadgeException e) {
            if (!Log.isLoggable(LOG_TAG, 3)) {
                return false;
            }
            Log.d(LOG_TAG, "Unable to execute badge", e);
            return false;
        }
    }

    public static void applyCountOrThrow(Context context, int i) throws ShortcutBadgeException {
        if (sShortcutBadger != null || initBadger(context)) {
            try {
                sShortcutBadger.executeBadge(context, sComponentName, i);
            } catch (Exception e) {
                throw new ShortcutBadgeException("Unable to execute badge", e);
            }
        } else {
            throw new ShortcutBadgeException("No default launcher available");
        }
    }

    public static boolean removeCount(Context context) {
        return applyCount(context, 0);
    }

    public static void removeCountOrThrow(Context context) throws ShortcutBadgeException {
        applyCountOrThrow(context, 0);
    }

    public static boolean isBadgeCounterSupported(Context context) {
        if (sIsBadgeCounterSupported == null) {
            synchronized (sCounterSupportedLock) {
                if (sIsBadgeCounterSupported == null) {
                    String str = null;
                    int i = 0;
                    while (true) {
                        if (i >= 3) {
                            break;
                        }
                        try {
                            Log.i(LOG_TAG, "Checking if platform supports badge counters, attempt " + String.format("%d/%d.", new Object[]{Integer.valueOf(i + 1), 3}));
                            if (initBadger(context)) {
                                sShortcutBadger.executeBadge(context, sComponentName, 0);
                                sIsBadgeCounterSupported = true;
                                Log.i(LOG_TAG, "Badge counter is supported in this platform.");
                                break;
                            }
                            str = "Failed to initialize the badge counter.";
                            i++;
                        } catch (Exception e) {
                            str = e.getMessage();
                        }
                    }
                    if (sIsBadgeCounterSupported == null) {
                        Log.w(LOG_TAG, "Badge counter seems not supported for this platform: " + str);
                        sIsBadgeCounterSupported = false;
                    }
                }
            }
        }
        return sIsBadgeCounterSupported.booleanValue();
    }

    public static void applyNotification(Context context, Notification notification, int i) {
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            try {
                Object obj = notification.getClass().getDeclaredField("extraNotification").get(notification);
                obj.getClass().getDeclaredMethod("setMessageCount", new Class[]{Integer.TYPE}).invoke(obj, new Object[]{Integer.valueOf(i)});
            } catch (Exception e) {
                if (Log.isLoggable(LOG_TAG, 3)) {
                    Log.d(LOG_TAG, "Unable to execute badge", e);
                }
            }
        }
    }

    private static boolean initBadger(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            Log.e(LOG_TAG, "Unable to find launch intent for package " + context.getPackageName());
            return false;
        }
        sComponentName = launchIntentForPackage.getComponent();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 65536)) {
            String str = resolveInfo.activityInfo.packageName;
            Iterator<Class<? extends Badger>> it = BADGERS.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Badger badger = null;
                try {
                    badger = (Badger) it.next().newInstance();
                } catch (Exception unused) {
                }
                if (badger != null && badger.getSupportLaunchers().contains(str)) {
                    sShortcutBadger = badger;
                    break;
                }
            }
            if (sShortcutBadger != null) {
                break;
            }
        }
        if (sShortcutBadger != null) {
            return true;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("ZUK")) {
            sShortcutBadger = new ZukHomeBadger();
            return true;
        } else if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
            sShortcutBadger = new OPPOHomeBader();
            return true;
        } else if (Build.MANUFACTURER.equalsIgnoreCase("VIVO")) {
            sShortcutBadger = new VivoHomeBadger();
            return true;
        } else if (Build.MANUFACTURER.equalsIgnoreCase("ZTE")) {
            sShortcutBadger = new ZTEHomeBadger();
            return true;
        } else {
            sShortcutBadger = new DefaultBadger();
            return true;
        }
    }

    private ShortcutBadger() {
    }
}
