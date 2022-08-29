package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

/* renamed from: com.tencent.bugly.proguard.y */
/* compiled from: BUGLY */
public final class C3749y {

    /* renamed from: a */
    public static String f1059a = "CrashReport";

    /* renamed from: b */
    public static boolean f1060b = false;

    /* renamed from: c */
    private static String f1061c = "CrashReportInfo";

    /* renamed from: a */
    private static boolean m932a(int i, String str, Object... objArr) {
        if (!f1060b) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (!(objArr == null || objArr.length == 0)) {
            str = String.format(Locale.US, str, objArr);
        }
        if (i == 0) {
            Log.i(f1059a, str);
            return true;
        } else if (i == 1) {
            Log.d(f1059a, str);
            return true;
        } else if (i == 2) {
            Log.w(f1059a, str);
            return true;
        } else if (i == 3) {
            Log.e(f1059a, str);
            return true;
        } else if (i != 5) {
            return false;
        } else {
            Log.i(f1061c, str);
            return true;
        }
    }

    /* renamed from: a */
    public static boolean m934a(String str, Object... objArr) {
        return m932a(0, str, objArr);
    }

    /* renamed from: a */
    public static boolean m933a(Class cls, String str, Object... objArr) {
        return m932a(0, String.format(Locale.US, "[%s] %s", new Object[]{cls.getSimpleName(), str}), objArr);
    }

    /* renamed from: b */
    public static boolean m937b(String str, Object... objArr) {
        return m932a(5, str, objArr);
    }

    /* renamed from: c */
    public static boolean m939c(String str, Object... objArr) {
        return m932a(1, str, objArr);
    }

    /* renamed from: b */
    public static boolean m936b(Class cls, String str, Object... objArr) {
        return m932a(1, String.format(Locale.US, "[%s] %s", new Object[]{cls.getSimpleName(), str}), objArr);
    }

    /* renamed from: d */
    public static boolean m940d(String str, Object... objArr) {
        return m932a(2, str, objArr);
    }

    /* renamed from: a */
    public static boolean m935a(Throwable th) {
        if (!f1060b) {
            return false;
        }
        return m932a(2, C3695ab.m665a(th), new Object[0]);
    }

    /* renamed from: e */
    public static boolean m941e(String str, Object... objArr) {
        return m932a(3, str, objArr);
    }

    /* renamed from: b */
    public static boolean m938b(Throwable th) {
        if (!f1060b) {
            return false;
        }
        return m932a(3, C3695ab.m665a(th), new Object[0]);
    }
}
