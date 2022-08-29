package com.eternal.framework.utils;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import java.util.Objects;

public final class Utils {
    private static Application application;
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(Application application2) {
        application = application2;
        context = application2.getApplicationContext();
    }

    public static Context getContext() {
        Context context2 = context;
        Objects.requireNonNull(context2, "should be initialized in application");
        return context2;
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static Application getApp() {
        Application application2 = application;
        Objects.requireNonNull(application2, "should be initialized in application");
        return application2;
    }

    public static String getString(int i) {
        return application.getString(i);
    }

    public static String getString(int i, Object... objArr) {
        return application.getString(i, objArr);
    }

    public static ComponentName startService(Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            return application.startForegroundService(intent);
        }
        return application.startService(intent);
    }
}
