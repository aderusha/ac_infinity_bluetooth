package com.easysocket.utils;

import android.util.Log;
import com.easysocket.EasySocket;
import java.io.PrintStream;

public class LogUtil {
    public static final String LOGTAG = "easysocket";
    public static boolean debugEnabled = EasySocket.getInstance().getDefOptions().isDebug();

    private static String getDebugInfo() {
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        return stackTrace[2].getClassName() + " " + stackTrace[2].getMethodName() + "():" + stackTrace[2].getLineNumber() + " ";
    }

    private static String getLogInfoByArray(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String append : strArr) {
            sb.append(append);
            sb.append(" ");
        }
        return sb.toString();
    }

    /* renamed from: i */
    public static void m51i(String... strArr) {
        if (debugEnabled) {
            m50i(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    /* renamed from: e */
    public static void m48e(Throwable th) {
        if (debugEnabled) {
            Log.e(LOGTAG, getDebugInfo(), th);
        }
    }

    /* renamed from: e */
    public static void m49e(String... strArr) {
        if (debugEnabled) {
            m47e(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    /* renamed from: d */
    public static void m46d(String... strArr) {
        if (debugEnabled) {
            m45d(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    /* renamed from: v */
    public static void m53v(String... strArr) {
        if (debugEnabled) {
            m52v(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    /* renamed from: w */
    public static void m55w(String... strArr) {
        if (debugEnabled) {
            m54w(LOGTAG, getDebugInfo() + getLogInfoByArray(strArr));
        }
    }

    /* renamed from: i */
    private static void m50i(String str, String str2) {
        PrintStream printStream = System.out;
        printStream.println(str + "：" + str2);
    }

    /* renamed from: d */
    private static void m45d(String str, String str2) {
        PrintStream printStream = System.out;
        printStream.println(str + "：" + str2);
    }

    /* renamed from: v */
    private static void m52v(String str, String str2) {
        PrintStream printStream = System.out;
        printStream.println(str + "：" + str2);
    }

    /* renamed from: e */
    private static void m47e(String str, String str2) {
        PrintStream printStream = System.err;
        printStream.println(str + "：" + str2);
    }

    /* renamed from: w */
    private static void m54w(String str, String str2) {
        PrintStream printStream = System.err;
        printStream.println(str + "：" + str2);
    }
}
