package com.telink.p010lt.util;

import android.util.Log;

/* renamed from: com.telink.lt.util.TelinkLog */
public class TelinkLog {
    public static boolean ENABLE = true;
    public static final String TAG = "TelinkBluetoothSDK";

    public static boolean isLoggable(int i) {
        if (ENABLE) {
            return Log.isLoggable(TAG, i);
        }
        return false;
    }

    public static String getStackTraceString(Throwable th) {
        if (ENABLE) {
            return Log.getStackTraceString(th);
        }
        return th.getMessage();
    }

    public static int println(int i, String str) {
        if (ENABLE) {
            return Log.println(i, TAG, str);
        }
        return 0;
    }

    /* renamed from: v */
    public static int m271v(String str) {
        if (ENABLE) {
            return Log.v(TAG, str);
        }
        return 0;
    }

    /* renamed from: v */
    public static int m272v(String str, Throwable th) {
        if (ENABLE) {
            return Log.v(TAG, str, th);
        }
        return 0;
    }

    /* renamed from: d */
    public static int m265d(String str) {
        if (ENABLE) {
            return Log.d(TAG, str);
        }
        return 0;
    }

    /* renamed from: d */
    public static int m266d(String str, Throwable th) {
        if (ENABLE) {
            return Log.d(TAG, str, th);
        }
        return 0;
    }

    /* renamed from: i */
    public static int m269i(String str) {
        if (ENABLE) {
            return Log.i(TAG, str);
        }
        return 0;
    }

    /* renamed from: i */
    public static int m270i(String str, Throwable th) {
        if (ENABLE) {
            return Log.i(TAG, str, th);
        }
        return 0;
    }

    /* renamed from: w */
    public static int m273w(String str) {
        if (ENABLE) {
            return Log.w(TAG, str);
        }
        return 0;
    }

    /* renamed from: w */
    public static int m274w(String str, Throwable th) {
        if (ENABLE) {
            return Log.w(TAG, str, th);
        }
        return 0;
    }

    /* renamed from: w */
    public static int m275w(Throwable th) {
        if (ENABLE) {
            return Log.w(TAG, th);
        }
        return 0;
    }

    /* renamed from: e */
    public static int m267e(String str) {
        if (ENABLE) {
            return Log.w(TAG, str);
        }
        return 0;
    }

    /* renamed from: e */
    public static int m268e(String str, Throwable th) {
        if (ENABLE) {
            return Log.e(TAG, str, th);
        }
        return 0;
    }
}
