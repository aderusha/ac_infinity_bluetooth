package com.eternal.start;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import com.eternal.base.global.ActivityEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NotificationsCheckUtil {
    public static boolean areNotificationsEnabled(Context context) {
        NotificationManagerCompat.from(context).areNotificationsEnabled();
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return isEnableV19(context);
        }
        return isEnableV26(context);
    }

    private static boolean isEnableV19(Context context) {
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            if (((Integer) cls.getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException | Exception | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    private static boolean isEnableV26(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(ActivityEvent.NOTIFICATION);
            Method declaredMethod = notificationManager.getClass().getDeclaredMethod("getService", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(notificationManager, new Object[0]);
            Method declaredMethod2 = invoke.getClass().getDeclaredMethod("areNotificationsEnabledForPackage", new Class[]{String.class, Integer.TYPE});
            declaredMethod2.setAccessible(true);
            return ((Boolean) declaredMethod2.invoke(invoke, new Object[]{packageName, Integer.valueOf(i)})).booleanValue();
        } catch (Exception unused) {
            return true;
        }
    }
}
