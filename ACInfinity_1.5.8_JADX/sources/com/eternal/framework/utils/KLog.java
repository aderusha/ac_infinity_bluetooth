package com.eternal.framework.utils;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KLog {

    /* renamed from: A */
    private static final int f185A = 6;

    /* renamed from: D */
    private static final int f186D = 2;
    private static final String DEFAULT_MESSAGE = "execute";

    /* renamed from: E */
    private static final int f187E = 5;

    /* renamed from: I */
    private static final int f188I = 3;
    private static boolean IS_SHOW_LOG = false;
    private static final int JSON = 7;
    private static final int JSON_INDENT = 4;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /* renamed from: V */
    private static final int f189V = 1;

    /* renamed from: W */
    private static final int f190W = 4;

    public static void init(boolean z) {
        IS_SHOW_LOG = z;
    }

    /* renamed from: v */
    public static void m70v() {
        printLog(1, (String) null, DEFAULT_MESSAGE);
    }

    /* renamed from: v */
    public static void m71v(Object obj) {
        printLog(1, (String) null, obj);
    }

    /* renamed from: v */
    public static void m72v(String str, String str2) {
        printLog(1, str, str2);
    }

    /* renamed from: d */
    public static void m61d() {
        printLog(2, (String) null, DEFAULT_MESSAGE);
    }

    /* renamed from: d */
    public static void m62d(Object obj) {
        printLog(2, (String) null, obj);
    }

    /* renamed from: d */
    public static void m63d(String str, Object obj) {
        printLog(2, str, obj);
    }

    /* renamed from: i */
    public static void m67i() {
        printLog(3, (String) null, DEFAULT_MESSAGE);
    }

    /* renamed from: i */
    public static void m68i(Object obj) {
        printLog(3, (String) null, obj);
    }

    /* renamed from: i */
    public static void m69i(String str, Object obj) {
        printLog(3, str, obj);
    }

    /* renamed from: w */
    public static void m73w() {
        printLog(4, (String) null, DEFAULT_MESSAGE);
    }

    /* renamed from: w */
    public static void m74w(Object obj) {
        printLog(4, (String) null, obj);
    }

    /* renamed from: w */
    public static void m75w(String str, Object obj) {
        printLog(4, str, obj);
    }

    /* renamed from: e */
    public static void m64e() {
        printLog(5, (String) null, DEFAULT_MESSAGE);
    }

    /* renamed from: e */
    public static void m65e(Object obj) {
        printLog(5, (String) null, obj);
    }

    /* renamed from: e */
    public static void m66e(String str, Object obj) {
        printLog(5, str, obj);
    }

    /* renamed from: a */
    public static void m58a() {
        printLog(6, (String) null, DEFAULT_MESSAGE);
    }

    /* renamed from: a */
    public static void m59a(Object obj) {
        printLog(6, (String) null, obj);
    }

    /* renamed from: a */
    public static void m60a(String str, Object obj) {
        printLog(6, str, obj);
    }

    public static void json(String str) {
        printLog(7, (String) null, str);
    }

    public static void json(String str, String str2) {
        printLog(7, str, str2);
    }

    private static void printLog(int i, String str, Object obj) {
        String str2;
        if (IS_SHOW_LOG) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String fileName = stackTrace[4].getFileName();
            String methodName = stackTrace[4].getMethodName();
            int lineNumber = stackTrace[4].getLineNumber();
            if (str == null) {
                str = fileName;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[ (");
            sb.append(fileName);
            sb.append(":");
            sb.append(lineNumber);
            sb.append(")#");
            sb.append(methodName.substring(0, 1).toUpperCase() + methodName.substring(1));
            sb.append(" ] ");
            if (obj == null) {
                str2 = "Log with null Object";
            } else {
                str2 = obj.toString();
            }
            if (!(str2 == null || i == 7)) {
                sb.append(str2);
            }
            String sb2 = sb.toString();
            switch (i) {
                case 1:
                    Log.v(str, sb2);
                    return;
                case 2:
                    Log.d(str, sb2);
                    return;
                case 3:
                    Log.i(str, sb2);
                    return;
                case 4:
                    Log.w(str, sb2);
                    return;
                case 5:
                    Log.e(str, sb2);
                    return;
                case 6:
                    Log.wtf(str, sb2);
                    return;
                case 7:
                    if (TextUtils.isEmpty(str2)) {
                        Log.d(str, "Empty or Null json content");
                        return;
                    }
                    String str3 = null;
                    try {
                        if (str2.startsWith("{")) {
                            str3 = new JSONObject(str2).toString(4);
                        } else if (str2.startsWith("[")) {
                            str3 = new JSONArray(str2).toString(4);
                        }
                        printLine(str, true);
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(sb2);
                        String str4 = LINE_SEPARATOR;
                        sb3.append(str4);
                        sb3.append(str3);
                        String[] split = sb3.toString().split(str4);
                        StringBuilder sb4 = new StringBuilder();
                        for (String append : split) {
                            sb4.append("║ ");
                            sb4.append(append);
                            sb4.append(LINE_SEPARATOR);
                        }
                        if (sb4.toString().length() > 3200) {
                            Log.w(str, "jsonContent.length = " + sb4.toString().length());
                            int length = sb4.toString().length() / 3200;
                            int i2 = 0;
                            while (i2 <= length) {
                                int i3 = i2 + 1;
                                int i4 = i3 * 3200;
                                if (i4 >= sb4.toString().length()) {
                                    Log.w(str, sb4.toString().substring(i2 * 3200));
                                } else {
                                    Log.w(str, sb4.toString().substring(i2 * 3200, i4));
                                }
                                i2 = i3;
                            }
                        } else {
                            Log.w(str, sb4.toString());
                        }
                        printLine(str, false);
                        return;
                    } catch (JSONException e) {
                        m66e(str, e.getCause().getMessage() + "\n" + str2);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private static void printLine(String str, boolean z) {
        if (z) {
            Log.w(str, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.w(str, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
