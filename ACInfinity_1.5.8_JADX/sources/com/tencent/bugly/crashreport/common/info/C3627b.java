package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3749y;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.tencent.bugly.crashreport.common.info.b */
/* compiled from: BUGLY */
public class C3627b {

    /* renamed from: a */
    private static final ArrayList<C3630a> f519a = new ArrayList<C3630a>() {
        {
            add(new C3641l((byte) 0));
            add(new C3635f((byte) 0));
            add(new C3636g((byte) 0));
            add(new C3642m((byte) 0));
            add(new C3637h((byte) 0));
            add(new C3638i((byte) 0));
            add(new C3640k((byte) 0));
            add(new C3634e((byte) 0));
            add(new C3639j((byte) 0));
            add(new C3631b((byte) 0));
            add(new C3633d((byte) 0));
            add(new C3632c((byte) 0));
        }
    };

    /* renamed from: b */
    private static final Map<Integer, String> f520b = new HashMap<Integer, String>() {
        {
            put(1, "GPRS");
            put(2, "EDGE");
            put(3, "UMTS");
            put(8, "HSDPA");
            put(9, "HSUPA");
            put(10, "HSPA");
            put(4, "CDMA");
            put(5, "EVDO_0");
            put(6, "EVDO_A");
            put(7, "1xRTT");
            put(11, "iDen");
            put(12, "EVDO_B");
            put(13, "LTE");
            put(14, "eHRPD");
            put(15, "HSPA+");
        }
    };

    /* renamed from: c */
    private static final String[] f521c = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};

    /* renamed from: m */
    public static String m407m() {
        return "";
    }

    /* renamed from: a */
    public static String m389a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: b */
    public static String m392b() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: c */
    public static int m394c() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    /* renamed from: p */
    private static boolean m410p() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static String m391a(Context context, boolean z) {
        try {
            String property = System.getProperty("os.arch");
            return property;
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: a */
    public static String m390a(Context context) {
        if (!(context == null || context.getApplicationInfo() == null)) {
            String str = context.getApplicationInfo().nativeLibraryDir;
            if (TextUtils.isEmpty(str)) {
                return "fail";
            }
            if (str.endsWith("arm")) {
                return "armeabi-v7a";
            }
            if (str.endsWith("arm64")) {
                return "arm64-v8a";
            }
            if (str.endsWith("x86")) {
                return "x86";
            }
            if (str.endsWith("x86_64")) {
                return "x86_64";
            }
        }
        return "fail";
    }

    /* renamed from: d */
    public static long m396d() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* renamed from: e */
    public static long m398e() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* renamed from: f */
    public static long m400f() {
        return Debug.getPss() << 10;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043 A[SYNTHETIC, Splitter:B:20:0x0043] */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m401g() {
        /*
            r0 = 0
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x003a }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ all -> 0x003a }
            java.lang.String r5 = "/proc/self/status"
            r4.<init>(r5)     // Catch:{ all -> 0x003a }
            r3.<init>(r4)     // Catch:{ all -> 0x003a }
            java.lang.String r2 = r3.readLine()     // Catch:{ all -> 0x0038 }
        L_0x0013:
            if (r2 == 0) goto L_0x002f
            java.lang.String r4 = "VmSize"
            boolean r4 = r2.startsWith(r4)     // Catch:{ all -> 0x0038 }
            if (r4 == 0) goto L_0x002a
            java.lang.String r4 = "[^\\d]"
            java.lang.String r5 = ""
            java.lang.String r2 = r2.replaceAll(r4, r5)     // Catch:{ all -> 0x0038 }
            long r0 = java.lang.Long.parseLong(r2)     // Catch:{ all -> 0x0038 }
            goto L_0x002f
        L_0x002a:
            java.lang.String r2 = r3.readLine()     // Catch:{ all -> 0x0038 }
            goto L_0x0013
        L_0x002f:
            r3.close()     // Catch:{ all -> 0x0033 }
            goto L_0x0046
        L_0x0033:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0046
        L_0x0038:
            r2 = move-exception
            goto L_0x003e
        L_0x003a:
            r3 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
        L_0x003e:
            com.tencent.bugly.proguard.C3749y.m935a(r2)     // Catch:{ all -> 0x004a }
            if (r3 == 0) goto L_0x0046
            r3.close()     // Catch:{ all -> 0x0033 }
        L_0x0046:
            r2 = 10
            long r0 = r0 << r2
            return r0
        L_0x004a:
            r0 = move-exception
            if (r3 == 0) goto L_0x0055
            r3.close()     // Catch:{ all -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0055:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C3627b.m401g():long");
    }

    /* renamed from: h */
    public static long m402h() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0083 A[Catch:{ all -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0088 A[SYNTHETIC, Splitter:B:47:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0098 A[SYNTHETIC, Splitter:B:54:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m403i() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x0079 }
            r2.<init>(r0)     // Catch:{ all -> 0x0079 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ all -> 0x0074 }
            r3 = 2048(0x800, float:2.87E-42)
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = r0.readLine()     // Catch:{ all -> 0x0072 }
            if (r1 != 0) goto L_0x0034
            r0.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x0023
        L_0x0019:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x0023
            r0.printStackTrace()
        L_0x0023:
            r2.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x0031
        L_0x0027:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x0031
            r0.printStackTrace()
        L_0x0031:
            r0 = -1
            return r0
        L_0x0034:
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r1 = r1.split(r3, r4)     // Catch:{ all -> 0x0072 }
            r3 = 1
            r1 = r1[r3]     // Catch:{ all -> 0x0072 }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ all -> 0x0072 }
            java.lang.String r3 = "kb"
            java.lang.String r4 = ""
            java.lang.String r1 = r1.replace(r3, r4)     // Catch:{ all -> 0x0072 }
            java.lang.String r1 = r1.trim()     // Catch:{ all -> 0x0072 }
            long r3 = java.lang.Long.parseLong(r1)     // Catch:{ all -> 0x0072 }
            r1 = 10
            long r3 = r3 << r1
            r0.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x0063
        L_0x0059:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x0063
            r0.printStackTrace()
        L_0x0063:
            r2.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x0071
        L_0x0067:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x0071
            r0.printStackTrace()
        L_0x0071:
            return r3
        L_0x0072:
            r1 = move-exception
            goto L_0x007d
        L_0x0074:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x007d
        L_0x0079:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
        L_0x007d:
            boolean r3 = com.tencent.bugly.proguard.C3749y.m935a(r1)     // Catch:{ all -> 0x00a9 }
            if (r3 != 0) goto L_0x0086
            r1.printStackTrace()     // Catch:{ all -> 0x00a9 }
        L_0x0086:
            if (r0 == 0) goto L_0x0096
            r0.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0096
        L_0x008c:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x0096
            r0.printStackTrace()
        L_0x0096:
            if (r2 == 0) goto L_0x00a6
            r2.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a6
        L_0x009c:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x00a6
            r0.printStackTrace()
        L_0x00a6:
            r0 = -2
            return r0
        L_0x00a9:
            r1 = move-exception
            if (r0 == 0) goto L_0x00ba
            r0.close()     // Catch:{ IOException -> 0x00b0 }
            goto L_0x00ba
        L_0x00b0:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r3 != 0) goto L_0x00ba
            r0.printStackTrace()
        L_0x00ba:
            if (r2 == 0) goto L_0x00ca
            r2.close()     // Catch:{ IOException -> 0x00c0 }
            goto L_0x00ca
        L_0x00c0:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r2 != 0) goto L_0x00ca
            r0.printStackTrace()
        L_0x00ca:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C3627b.m403i():long");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.io.FileReader} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00fb A[Catch:{ all -> 0x0121 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0100 A[SYNTHETIC, Splitter:B:81:0x0100] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0110 A[SYNTHETIC, Splitter:B:88:0x0110] */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m404j() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "kb"
            java.lang.String r2 = ":\\s+"
            java.lang.String r3 = "/proc/meminfo"
            r4 = 0
            java.io.FileReader r5 = new java.io.FileReader     // Catch:{ all -> 0x00f3 }
            r5.<init>(r3)     // Catch:{ all -> 0x00f3 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x00f1 }
            r6 = 2048(0x800, float:2.87E-42)
            r3.<init>(r5, r6)     // Catch:{ all -> 0x00f1 }
            r3.readLine()     // Catch:{ all -> 0x00ee }
            java.lang.String r4 = r3.readLine()     // Catch:{ all -> 0x00ee }
            r6 = -1
            if (r4 != 0) goto L_0x003d
            r3.close()     // Catch:{ IOException -> 0x0024 }
            goto L_0x002e
        L_0x0024:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x002e
            r0.printStackTrace()
        L_0x002e:
            r5.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x003c
        L_0x0032:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x003c
            r0.printStackTrace()
        L_0x003c:
            return r6
        L_0x003d:
            r8 = 2
            java.lang.String[] r4 = r4.split(r2, r8)     // Catch:{ all -> 0x00ee }
            r9 = 1
            r4 = r4[r9]     // Catch:{ all -> 0x00ee }
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ all -> 0x00ee }
            java.lang.String r4 = r4.replace(r1, r0)     // Catch:{ all -> 0x00ee }
            java.lang.String r4 = r4.trim()     // Catch:{ all -> 0x00ee }
            r10 = 0
            long r12 = java.lang.Long.parseLong(r4)     // Catch:{ all -> 0x00ee }
            r4 = 10
            long r12 = r12 << r4
            long r12 = r12 + r10
            java.lang.String r10 = r3.readLine()     // Catch:{ all -> 0x00ee }
            if (r10 != 0) goto L_0x007e
            r3.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x006f
        L_0x0065:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x006f
            r0.printStackTrace()
        L_0x006f:
            r5.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x007d
        L_0x0073:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x007d
            r0.printStackTrace()
        L_0x007d:
            return r6
        L_0x007e:
            java.lang.String[] r10 = r10.split(r2, r8)     // Catch:{ all -> 0x00ee }
            r10 = r10[r9]     // Catch:{ all -> 0x00ee }
            java.lang.String r10 = r10.toLowerCase()     // Catch:{ all -> 0x00ee }
            java.lang.String r10 = r10.replace(r1, r0)     // Catch:{ all -> 0x00ee }
            java.lang.String r10 = r10.trim()     // Catch:{ all -> 0x00ee }
            long r10 = java.lang.Long.parseLong(r10)     // Catch:{ all -> 0x00ee }
            long r10 = r10 << r4
            long r12 = r12 + r10
            java.lang.String r10 = r3.readLine()     // Catch:{ all -> 0x00ee }
            if (r10 != 0) goto L_0x00b9
            r3.close()     // Catch:{ IOException -> 0x00a0 }
            goto L_0x00aa
        L_0x00a0:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x00aa
            r0.printStackTrace()
        L_0x00aa:
            r5.close()     // Catch:{ IOException -> 0x00ae }
            goto L_0x00b8
        L_0x00ae:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x00b8
            r0.printStackTrace()
        L_0x00b8:
            return r6
        L_0x00b9:
            java.lang.String[] r2 = r10.split(r2, r8)     // Catch:{ all -> 0x00ee }
            r2 = r2[r9]     // Catch:{ all -> 0x00ee }
            java.lang.String r2 = r2.toLowerCase()     // Catch:{ all -> 0x00ee }
            java.lang.String r0 = r2.replace(r1, r0)     // Catch:{ all -> 0x00ee }
            java.lang.String r0 = r0.trim()     // Catch:{ all -> 0x00ee }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ all -> 0x00ee }
            long r0 = r0 << r4
            long r12 = r12 + r0
            r3.close()     // Catch:{ IOException -> 0x00d5 }
            goto L_0x00df
        L_0x00d5:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x00df
            r0.printStackTrace()
        L_0x00df:
            r5.close()     // Catch:{ IOException -> 0x00e3 }
            goto L_0x00ed
        L_0x00e3:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x00ed
            r0.printStackTrace()
        L_0x00ed:
            return r12
        L_0x00ee:
            r0 = move-exception
            r4 = r3
            goto L_0x00f5
        L_0x00f1:
            r0 = move-exception
            goto L_0x00f5
        L_0x00f3:
            r0 = move-exception
            r5 = r4
        L_0x00f5:
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)     // Catch:{ all -> 0x0121 }
            if (r1 != 0) goto L_0x00fe
            r0.printStackTrace()     // Catch:{ all -> 0x0121 }
        L_0x00fe:
            if (r4 == 0) goto L_0x010e
            r4.close()     // Catch:{ IOException -> 0x0104 }
            goto L_0x010e
        L_0x0104:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x010e
            r0.printStackTrace()
        L_0x010e:
            if (r5 == 0) goto L_0x011e
            r5.close()     // Catch:{ IOException -> 0x0114 }
            goto L_0x011e
        L_0x0114:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x011e
            r0.printStackTrace()
        L_0x011e:
            r0 = -2
            return r0
        L_0x0121:
            r0 = move-exception
            if (r4 == 0) goto L_0x0132
            r4.close()     // Catch:{ IOException -> 0x0128 }
            goto L_0x0132
        L_0x0128:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C3749y.m935a(r1)
            if (r2 != 0) goto L_0x0132
            r1.printStackTrace()
        L_0x0132:
            if (r5 == 0) goto L_0x0142
            r5.close()     // Catch:{ IOException -> 0x0138 }
            goto L_0x0142
        L_0x0138:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C3749y.m935a(r1)
            if (r2 != 0) goto L_0x0142
            r1.printStackTrace()
        L_0x0142:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C3627b.m404j():long");
    }

    /* renamed from: k */
    public static long m405k() {
        if (!m410p()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return -2;
            }
            th.printStackTrace();
            return -2;
        }
    }

    /* renamed from: l */
    public static long m406l() {
        if (!m410p()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (C3749y.m935a(th)) {
                return -2;
            }
            th.printStackTrace();
            return -2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005e  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m393b(android.content.Context r4) {
        /*
            java.lang.String r0 = "unknown"
            java.lang.String r1 = "connectivity"
            java.lang.Object r1 = r4.getSystemService(r1)     // Catch:{ Exception -> 0x0057 }
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1     // Catch:{ Exception -> 0x0057 }
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()     // Catch:{ Exception -> 0x0057 }
            if (r1 != 0) goto L_0x0012
            r4 = 0
            return r4
        L_0x0012:
            int r2 = r1.getType()     // Catch:{ Exception -> 0x0057 }
            r3 = 1
            if (r2 != r3) goto L_0x001c
            java.lang.String r4 = "WIFI"
            goto L_0x0062
        L_0x001c:
            int r1 = r1.getType()     // Catch:{ Exception -> 0x0057 }
            if (r1 != 0) goto L_0x0061
            java.lang.String r1 = "phone"
            java.lang.Object r4 = r4.getSystemService(r1)     // Catch:{ Exception -> 0x0057 }
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch:{ Exception -> 0x0057 }
            if (r4 == 0) goto L_0x0061
            int r4 = r4.getNetworkType()     // Catch:{ Exception -> 0x0057 }
            java.util.Map<java.lang.Integer, java.lang.String> r1 = f520b     // Catch:{ Exception -> 0x0057 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0057 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ Exception -> 0x0057 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0057 }
            if (r1 != 0) goto L_0x0055
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0052 }
            java.lang.String r2 = "MOBILE("
            r0.<init>(r2)     // Catch:{ Exception -> 0x0052 }
            r0.append(r4)     // Catch:{ Exception -> 0x0052 }
            java.lang.String r4 = ")"
            r0.append(r4)     // Catch:{ Exception -> 0x0052 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0052 }
            goto L_0x0061
        L_0x0052:
            r4 = move-exception
            r0 = r1
            goto L_0x0058
        L_0x0055:
            r0 = r1
            goto L_0x0061
        L_0x0057:
            r4 = move-exception
        L_0x0058:
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r4)
            if (r1 != 0) goto L_0x0061
            r4.printStackTrace()
        L_0x0061:
            r4 = r0
        L_0x0062:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C3627b.m393b(android.content.Context):java.lang.String");
    }

    /* renamed from: c */
    public static String m395c(Context context) {
        Iterator<C3630a> it = f519a.iterator();
        while (it.hasNext()) {
            String a = it.next().mo24094a(context);
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
        }
        return null;
    }

    /* renamed from: d */
    public static boolean m397d(Context context) {
        if (TextUtils.isEmpty(new C3638i((byte) 0).mo24094a(context))) {
            return false;
        }
        return true;
    }

    /* renamed from: e */
    public static boolean m399e(Context context) {
        if (TextUtils.isEmpty(new C3640k((byte) 0).mo24094a(context))) {
            return false;
        }
        return true;
    }

    /* renamed from: n */
    public static boolean m408n() {
        boolean z;
        String[] strArr = f521c;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (new File(strArr[i]).exists()) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || z;
    }

    /* renamed from: o */
    public static boolean m409o() {
        float maxMemory = (float) (((double) Runtime.getRuntime().maxMemory()) / 1048576.0d);
        float f = (float) (((double) Runtime.getRuntime().totalMemory()) / 1048576.0d);
        float f2 = maxMemory - f;
        C3749y.m939c("maxMemory : %f", Float.valueOf(maxMemory));
        C3749y.m939c("totalMemory : %f", Float.valueOf(f));
        C3749y.m939c("freeMemory : %f", Float.valueOf(f2));
        if (f2 < 10.0f) {
            return true;
        }
        return false;
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$a */
    /* compiled from: BUGLY */
    static abstract class C3630a {
        /* renamed from: a */
        public abstract String mo24094a(Context context);

        private C3630a() {
        }

        /* synthetic */ C3630a(byte b) {
            this();
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$l */
    /* compiled from: BUGLY */
    static class C3641l extends C3630a {
        private C3641l() {
            super((byte) 0);
        }

        /* synthetic */ C3641l(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.miui.ui.version.name");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "XiaoMi/MIUI/" + a;
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$f */
    /* compiled from: BUGLY */
    static class C3635f extends C3630a {
        private C3635f() {
            super((byte) 0);
        }

        /* synthetic */ C3635f(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.build.version.emui");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "HuaWei/EMOTION/" + a;
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$g */
    /* compiled from: BUGLY */
    static class C3636g extends C3630a {
        private C3636g() {
            super((byte) 0);
        }

        /* synthetic */ C3636g(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.lenovo.series");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            String a2 = C3695ab.m662a(context, "ro.build.version.incremental");
            return "Lenovo/VIBE/" + a2;
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$m */
    /* compiled from: BUGLY */
    static class C3642m extends C3630a {
        private C3642m() {
            super((byte) 0);
        }

        /* synthetic */ C3642m(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.build.nubia.rom.name");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "Zte/NUBIA/" + a + "_" + C3695ab.m662a(context, "ro.build.nubia.rom.code");
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$h */
    /* compiled from: BUGLY */
    static class C3637h extends C3630a {
        private C3637h() {
            super((byte) 0);
        }

        /* synthetic */ C3637h(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.meizu.product.model");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "Meizu/FLYME/" + C3695ab.m662a(context, "ro.build.display.id");
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$i */
    /* compiled from: BUGLY */
    static class C3638i extends C3630a {
        private C3638i() {
            super((byte) 0);
        }

        /* synthetic */ C3638i(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.build.version.opporom");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "Oppo/COLOROS/" + a;
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$k */
    /* compiled from: BUGLY */
    static class C3640k extends C3630a {
        private C3640k() {
            super((byte) 0);
        }

        /* synthetic */ C3640k(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.vivo.os.build.display.id");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "vivo/FUNTOUCH/" + a;
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$e */
    /* compiled from: BUGLY */
    static class C3634e extends C3630a {
        private C3634e() {
            super((byte) 0);
        }

        /* synthetic */ C3634e(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.aa.romver");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "htc/" + a + "/" + C3695ab.m662a(context, "ro.build.description");
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$j */
    /* compiled from: BUGLY */
    static class C3639j extends C3630a {
        private C3639j() {
            super((byte) 0);
        }

        /* synthetic */ C3639j(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.lewa.version");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "tcl/" + a + "/" + C3695ab.m662a(context, "ro.build.display.id");
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$b */
    /* compiled from: BUGLY */
    static class C3631b extends C3630a {
        private C3631b() {
            super((byte) 0);
        }

        /* synthetic */ C3631b(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.gn.gnromvernumber");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "amigo/" + a + "/" + C3695ab.m662a(context, "ro.build.display.id");
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$d */
    /* compiled from: BUGLY */
    static class C3633d extends C3630a {
        private C3633d() {
            super((byte) 0);
        }

        /* synthetic */ C3633d(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            String a = C3695ab.m662a(context, "ro.build.tyd.kbstyle_version");
            if (C3695ab.m679a(a) || a.equals("fail")) {
                return null;
            }
            return "dido/" + a;
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.common.info.b$c */
    /* compiled from: BUGLY */
    static class C3632c extends C3630a {
        private C3632c() {
            super((byte) 0);
        }

        /* synthetic */ C3632c(byte b) {
            this();
        }

        /* renamed from: a */
        public final String mo24094a(Context context) {
            return C3695ab.m662a(context, "ro.build.fingerprint") + "/" + C3695ab.m662a(context, "ro.build.rom.id");
        }
    }
}
