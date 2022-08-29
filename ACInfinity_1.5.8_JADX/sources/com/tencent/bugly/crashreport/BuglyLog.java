package com.tencent.bugly.crashreport;

import android.util.Log;
import com.tencent.bugly.C3612b;
import com.tencent.bugly.proguard.C3692aa;

/* compiled from: BUGLY */
public class BuglyLog {
    /* renamed from: v */
    public static void m286v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C3612b.f392c) {
            Log.v(str, str2);
        }
        C3692aa.m639a("V", str, str2);
    }

    /* renamed from: d */
    public static void m282d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C3612b.f392c) {
            Log.d(str, str2);
        }
        C3692aa.m639a("D", str, str2);
    }

    /* renamed from: i */
    public static void m285i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C3612b.f392c) {
            Log.i(str, str2);
        }
        C3692aa.m639a("I", str, str2);
    }

    /* renamed from: w */
    public static void m287w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C3612b.f392c) {
            Log.w(str, str2);
        }
        C3692aa.m639a("W", str, str2);
    }

    /* renamed from: e */
    public static void m283e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C3612b.f392c) {
            Log.e(str, str2);
        }
        C3692aa.m639a("E", str, str2);
    }

    /* renamed from: e */
    public static void m284e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C3612b.f392c) {
            Log.e(str, str2, th);
        }
        C3692aa.m640a("E", str, th);
    }

    public static void setCache(int i) {
        C3692aa.m637a(i);
    }
}
