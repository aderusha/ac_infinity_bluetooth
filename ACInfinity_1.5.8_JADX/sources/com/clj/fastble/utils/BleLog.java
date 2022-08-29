package com.clj.fastble.utils;

import android.util.Log;

public final class BleLog {
    private static String defaultTag = "FastBle";
    public static boolean isPrint = false;

    /* renamed from: d */
    public static void m41d(String str) {
        if (isPrint && str != null) {
            Log.d(defaultTag, str);
        }
    }

    /* renamed from: i */
    public static void m43i(String str) {
        if (isPrint && str != null) {
            Log.i(defaultTag, str);
        }
    }

    /* renamed from: w */
    public static void m44w(String str) {
        if (isPrint && str != null) {
            Log.w(defaultTag, str);
        }
    }

    /* renamed from: e */
    public static void m42e(String str) {
        if (isPrint && str != null) {
            Log.e(defaultTag, str);
        }
    }
}
