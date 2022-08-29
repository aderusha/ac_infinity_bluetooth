package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.os.Build;
import androidx.room.RoomDatabase;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.C3614a;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.C3662b;
import com.tencent.bugly.crashreport.crash.C3678c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3747x;
import com.tencent.bugly.proguard.C3749y;
import java.io.File;

/* compiled from: BUGLY */
public class NativeCrashHandler implements C3614a {

    /* renamed from: a */
    private static NativeCrashHandler f751a = null;

    /* renamed from: b */
    private static int f752b = 1;

    /* renamed from: m */
    private static boolean f753m = false;

    /* renamed from: n */
    private static boolean f754n = false;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public static boolean f755p = true;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final Context f756c;

    /* renamed from: d */
    private final C3626a f757d;

    /* renamed from: e */
    private final C3747x f758e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public NativeExceptionHandler f759f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public String f760g;

    /* renamed from: h */
    private final boolean f761h;

    /* renamed from: i */
    private boolean f762i = false;

    /* renamed from: j */
    private boolean f763j = false;

    /* renamed from: k */
    private boolean f764k = false;

    /* renamed from: l */
    private boolean f765l = false;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public C3662b f766o;

    private native String getSoCpuAbi();

    /* access modifiers changed from: protected */
    public native boolean appendNativeLog(String str, String str2, String str3);

    /* access modifiers changed from: protected */
    public native boolean appendWholeNativeLog(String str);

    /* access modifiers changed from: protected */
    public native String getNativeKeyValueList();

    /* access modifiers changed from: protected */
    public native String getNativeLog();

    /* access modifiers changed from: protected */
    public native boolean putNativeKeyValue(String str, String str2);

    /* access modifiers changed from: protected */
    public native String regist(String str, boolean z, int i);

    /* access modifiers changed from: protected */
    public native String removeNativeKeyValue(String str);

    /* access modifiers changed from: protected */
    public native void setNativeInfo(int i, String str);

    /* access modifiers changed from: protected */
    public native void testCrash();

    /* access modifiers changed from: protected */
    public native String unregist();

    private NativeCrashHandler(Context context, C3626a aVar, C3662b bVar, C3747x xVar, boolean z, String str) {
        this.f756c = C3695ab.m653a(context);
        try {
            if (C3695ab.m679a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable unused) {
            str = "/data/data/" + C3626a.m337a(context).f495c + "/app_bugly";
        }
        this.f766o = bVar;
        this.f760g = str;
        this.f757d = aVar;
        this.f758e = xVar;
        this.f761h = z;
        this.f759f = new C3688a(context, aVar, bVar, C3644a.m426a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, C3626a aVar, C3662b bVar, C3644a aVar2, C3747x xVar, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (f751a == null) {
                f751a = new NativeCrashHandler(context, aVar, bVar, xVar, z, str);
            }
            nativeCrashHandler = f751a;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = f751a;
        }
        return nativeCrashHandler;
    }

    public synchronized String getDumpFilePath() {
        return this.f760g;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f760g = str;
    }

    public static void setShouldHandleInJava(boolean z) {
        f755p = z;
        NativeCrashHandler nativeCrashHandler = f751a;
        if (nativeCrashHandler != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(z);
            nativeCrashHandler.m591a((int) RoomDatabase.MAX_BIND_PARAMETER_CNT, sb.toString());
        }
    }

    public static boolean isShouldHandleInJava() {
        return f755p;
    }

    public String getRunningCpuAbi() {
        try {
            return getSoCpuAbi();
        } catch (Throwable unused) {
            C3749y.m940d("get so cpu abi failed，please upgrade bugly so version", new Object[0]);
            return "";
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(17:14|(1:16)(16:18|(1:20)|21|22|(1:24)|25|(1:27)|28|29|(1:31)(1:32)|33|(1:35)(1:36)|37|(1:39)|40|(1:42))|17|21|22|(0)|25|(0)|28|29|(0)(0)|33|(0)(0)|37|(0)|40|(0)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:7|8|9|(3:11|12|(17:14|(1:16)(16:18|(1:20)|21|22|(1:24)|25|(1:27)|28|29|(1:31)(1:32)|33|(1:35)(1:36)|37|(1:39)|40|(1:42))|17|21|22|(0)|25|(0)|28|29|(0)(0)|33|(0)(0)|37|(0)|40|(0)))(2:49|(7:51|52|53|(1:55)(1:56)|57|(1:59)|(6:61|(1:63)|64|(1:66)|67|(1:69))))|72|73|74|75) */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00fd, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01e0, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x008c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:72:0x01e1 */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e A[Catch:{ all -> 0x008c }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008a A[Catch:{ all -> 0x008c }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0090 A[Catch:{ all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0098 A[Catch:{ all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a3 A[Catch:{ all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ab A[Catch:{ all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ca A[Catch:{ all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f7 A[Catch:{ all -> 0x00fe }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m590a(boolean r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            boolean r0 = r10.f764k     // Catch:{ all -> 0x01e7 }
            r1 = 0
            if (r0 == 0) goto L_0x000f
            java.lang.String r11 = "[Native] Native crash report has already registered."
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x01e7 }
            com.tencent.bugly.proguard.C3749y.m940d(r11, r0)     // Catch:{ all -> 0x01e7 }
            monitor-exit(r10)
            return
        L_0x000f:
            boolean r0 = r10.f763j     // Catch:{ all -> 0x01e7 }
            r2 = 2
            r3 = 1
            if (r0 == 0) goto L_0x0107
            java.lang.String r0 = r10.f760g     // Catch:{ all -> 0x00fe }
            int r4 = f752b     // Catch:{ all -> 0x00fe }
            java.lang.String r11 = r10.regist(r0, r11, r4)     // Catch:{ all -> 0x00fe }
            if (r11 == 0) goto L_0x01e1
            java.lang.String r0 = "[Native] Native Crash Report enable."
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.proguard.C3749y.m934a(r0, r4)     // Catch:{ all -> 0x00fe }
            java.lang.String r0 = "[Native] Check extra jni for Bugly NDK v%s"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00fe }
            r4[r1] = r11     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.proguard.C3749y.m939c(r0, r4)     // Catch:{ all -> 0x00fe }
            java.lang.String r0 = "2.1.1"
            java.lang.String r4 = "."
            java.lang.String r5 = ""
            java.lang.String r0 = r0.replace(r4, r5)     // Catch:{ all -> 0x00fe }
            java.lang.String r4 = "2.3.0"
            java.lang.String r5 = "."
            java.lang.String r6 = ""
            java.lang.String r4 = r4.replace(r5, r6)     // Catch:{ all -> 0x00fe }
            java.lang.String r5 = "."
            java.lang.String r6 = ""
            java.lang.String r5 = r11.replace(r5, r6)     // Catch:{ all -> 0x00fe }
            int r6 = r5.length()     // Catch:{ all -> 0x00fe }
            if (r6 != r2) goto L_0x0063
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fe }
            r2.<init>()     // Catch:{ all -> 0x00fe }
            r2.append(r5)     // Catch:{ all -> 0x00fe }
            java.lang.String r5 = "0"
        L_0x005b:
            r2.append(r5)     // Catch:{ all -> 0x00fe }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x00fe }
            goto L_0x0074
        L_0x0063:
            int r2 = r5.length()     // Catch:{ all -> 0x00fe }
            if (r2 != r3) goto L_0x0074
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fe }
            r2.<init>()     // Catch:{ all -> 0x00fe }
            r2.append(r5)     // Catch:{ all -> 0x00fe }
            java.lang.String r5 = "00"
            goto L_0x005b
        L_0x0074:
            int r2 = java.lang.Integer.parseInt(r5)     // Catch:{ all -> 0x008c }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ all -> 0x008c }
            if (r2 < r0) goto L_0x0080
            f753m = r3     // Catch:{ all -> 0x008c }
        L_0x0080:
            int r0 = java.lang.Integer.parseInt(r5)     // Catch:{ all -> 0x008c }
            int r2 = java.lang.Integer.parseInt(r4)     // Catch:{ all -> 0x008c }
            if (r0 < r2) goto L_0x008c
            f754n = r3     // Catch:{ all -> 0x008c }
        L_0x008c:
            boolean r0 = f754n     // Catch:{ all -> 0x00fe }
            if (r0 == 0) goto L_0x0098
            java.lang.String r0 = "[Native] Info setting jni can be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.proguard.C3749y.m934a(r0, r2)     // Catch:{ all -> 0x00fe }
            goto L_0x009f
        L_0x0098:
            java.lang.String r0 = "[Native] Info setting jni can not be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r2)     // Catch:{ all -> 0x00fe }
        L_0x009f:
            boolean r0 = f753m     // Catch:{ all -> 0x00fe }
            if (r0 == 0) goto L_0x00ab
            java.lang.String r0 = "[Native] Extra jni can be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.proguard.C3749y.m934a(r0, r2)     // Catch:{ all -> 0x00fe }
            goto L_0x00b2
        L_0x00ab:
            java.lang.String r0 = "[Native] Extra jni can not be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r2)     // Catch:{ all -> 0x00fe }
        L_0x00b2:
            com.tencent.bugly.crashreport.common.info.a r0 = r10.f757d     // Catch:{ all -> 0x00fe }
            r0.f505m = r11     // Catch:{ all -> 0x00fe }
            java.lang.String r11 = "-"
            com.tencent.bugly.crashreport.common.info.a r0 = r10.f757d     // Catch:{ all -> 0x00fe }
            java.lang.String r0 = r0.f505m     // Catch:{ all -> 0x00fe }
            java.lang.String r11 = r11.concat(r0)     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.crashreport.common.info.a r0 = r10.f757d     // Catch:{ all -> 0x00fe }
            java.lang.String r0 = r0.f498f     // Catch:{ all -> 0x00fe }
            boolean r11 = r0.contains(r11)     // Catch:{ all -> 0x00fe }
            if (r11 != 0) goto L_0x00de
            com.tencent.bugly.crashreport.common.info.a r11 = r10.f757d     // Catch:{ all -> 0x00fe }
            java.lang.String r0 = r11.f498f     // Catch:{ all -> 0x00fe }
            java.lang.String r2 = "-"
            java.lang.String r0 = r0.concat(r2)     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.crashreport.common.info.a r2 = r10.f757d     // Catch:{ all -> 0x00fe }
            java.lang.String r2 = r2.f505m     // Catch:{ all -> 0x00fe }
            java.lang.String r0 = r0.concat(r2)     // Catch:{ all -> 0x00fe }
            r11.f498f = r0     // Catch:{ all -> 0x00fe }
        L_0x00de:
            java.lang.String r11 = "comInfo.sdkVersion %s"
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.crashreport.common.info.a r2 = r10.f757d     // Catch:{ all -> 0x00fe }
            java.lang.String r2 = r2.f498f     // Catch:{ all -> 0x00fe }
            r0[r1] = r2     // Catch:{ all -> 0x00fe }
            com.tencent.bugly.proguard.C3749y.m934a(r11, r0)     // Catch:{ all -> 0x00fe }
            r10.f764k = r3     // Catch:{ all -> 0x00fe }
            java.lang.String r11 = r10.getRunningCpuAbi()     // Catch:{ all -> 0x00fe }
            boolean r0 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x00fe }
            if (r0 != 0) goto L_0x00fc
            com.tencent.bugly.crashreport.common.info.a r0 = r10.f757d     // Catch:{ all -> 0x00fe }
            r0.mo24073g(r11)     // Catch:{ all -> 0x00fe }
        L_0x00fc:
            monitor-exit(r10)
            return
        L_0x00fe:
            java.lang.String r11 = "[Native] Failed to load Bugly SO file."
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x01e7 }
            com.tencent.bugly.proguard.C3749y.m939c(r11, r0)     // Catch:{ all -> 0x01e7 }
            goto L_0x01e1
        L_0x0107:
            boolean r0 = r10.f762i     // Catch:{ all -> 0x01e7 }
            if (r0 == 0) goto L_0x01e1
            java.lang.String r0 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r4 = "registNativeExceptionHandler2"
            r5 = 4
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ all -> 0x01e1 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r1] = r7     // Catch:{ all -> 0x01e1 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r3] = r7     // Catch:{ all -> 0x01e1 }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ all -> 0x01e1 }
            r6[r2] = r7     // Catch:{ all -> 0x01e1 }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ all -> 0x01e1 }
            r8 = 3
            r6[r8] = r7     // Catch:{ all -> 0x01e1 }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x01e1 }
            java.lang.String r7 = r10.f760g     // Catch:{ all -> 0x01e1 }
            r5[r1] = r7     // Catch:{ all -> 0x01e1 }
            android.content.Context r7 = r10.f756c     // Catch:{ all -> 0x01e1 }
            java.lang.String r7 = com.tencent.bugly.crashreport.common.info.C3627b.m391a(r7, r1)     // Catch:{ all -> 0x01e1 }
            r5[r3] = r7     // Catch:{ all -> 0x01e1 }
            r7 = 5
            if (r11 == 0) goto L_0x0136
            r9 = 1
            goto L_0x0137
        L_0x0136:
            r9 = 5
        L_0x0137:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x01e1 }
            r5[r2] = r9     // Catch:{ all -> 0x01e1 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x01e1 }
            r5[r8] = r9     // Catch:{ all -> 0x01e1 }
            r9 = 0
            java.lang.Object r0 = com.tencent.bugly.proguard.C3695ab.m657a(r0, r4, r9, r6, r5)     // Catch:{ all -> 0x01e1 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x01e1 }
            if (r0 != 0) goto L_0x017f
            java.lang.String r0 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r4 = "registNativeExceptionHandler"
            java.lang.Class[] r5 = new java.lang.Class[r8]     // Catch:{ all -> 0x01e1 }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r1] = r6     // Catch:{ all -> 0x01e1 }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r3] = r6     // Catch:{ all -> 0x01e1 }
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ all -> 0x01e1 }
            r5[r2] = r6     // Catch:{ all -> 0x01e1 }
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ all -> 0x01e1 }
            java.lang.String r8 = r10.f760g     // Catch:{ all -> 0x01e1 }
            r6[r1] = r8     // Catch:{ all -> 0x01e1 }
            android.content.Context r8 = r10.f756c     // Catch:{ all -> 0x01e1 }
            java.lang.String r8 = com.tencent.bugly.crashreport.common.info.C3627b.m391a(r8, r1)     // Catch:{ all -> 0x01e1 }
            r6[r3] = r8     // Catch:{ all -> 0x01e1 }
            com.tencent.bugly.crashreport.common.info.C3626a.m339b()     // Catch:{ all -> 0x01e1 }
            int r8 = com.tencent.bugly.crashreport.common.info.C3626a.m333G()     // Catch:{ all -> 0x01e1 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x01e1 }
            r6[r2] = r8     // Catch:{ all -> 0x01e1 }
            java.lang.Object r0 = com.tencent.bugly.proguard.C3695ab.m657a(r0, r4, r9, r5, r6)     // Catch:{ all -> 0x01e1 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x01e1 }
        L_0x017f:
            if (r0 == 0) goto L_0x01e1
            r10.f764k = r3     // Catch:{ all -> 0x01e1 }
            com.tencent.bugly.crashreport.common.info.a r2 = r10.f757d     // Catch:{ all -> 0x01e1 }
            r2.f505m = r0     // Catch:{ all -> 0x01e1 }
            java.lang.String r2 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r4 = "checkExtraJni"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ all -> 0x01e1 }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r1] = r6     // Catch:{ all -> 0x01e1 }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ all -> 0x01e1 }
            r6[r1] = r0     // Catch:{ all -> 0x01e1 }
            java.lang.Object r0 = com.tencent.bugly.proguard.C3695ab.m657a(r2, r4, r9, r5, r6)     // Catch:{ all -> 0x01e1 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01e1 }
            if (r0 == 0) goto L_0x01a3
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01e1 }
            f753m = r0     // Catch:{ all -> 0x01e1 }
        L_0x01a3:
            java.lang.String r0 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r2 = "enableHandler"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ all -> 0x01e1 }
            java.lang.Class r5 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x01e1 }
            r4[r1] = r5     // Catch:{ all -> 0x01e1 }
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ all -> 0x01e1 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x01e1 }
            r5[r1] = r6     // Catch:{ all -> 0x01e1 }
            com.tencent.bugly.proguard.C3695ab.m657a(r0, r2, r9, r4, r5)     // Catch:{ all -> 0x01e1 }
            if (r11 == 0) goto L_0x01bb
            r7 = 1
        L_0x01bb:
            java.lang.String r11 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r0 = "setLogMode"
            java.lang.Class[] r2 = new java.lang.Class[r3]     // Catch:{ all -> 0x01e1 }
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x01e1 }
            r2[r1] = r4     // Catch:{ all -> 0x01e1 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x01e1 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x01e1 }
            r3[r1] = r4     // Catch:{ all -> 0x01e1 }
            com.tencent.bugly.proguard.C3695ab.m657a(r11, r0, r9, r2, r3)     // Catch:{ all -> 0x01e1 }
            java.lang.String r11 = r10.getRunningCpuAbi()     // Catch:{ all -> 0x01e1 }
            boolean r0 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x01e1 }
            if (r0 != 0) goto L_0x01df
            com.tencent.bugly.crashreport.common.info.a r0 = r10.f757d     // Catch:{ all -> 0x01e1 }
            r0.mo24073g(r11)     // Catch:{ all -> 0x01e1 }
        L_0x01df:
            monitor-exit(r10)
            return
        L_0x01e1:
            r10.f763j = r1     // Catch:{ all -> 0x01e7 }
            r10.f762i = r1     // Catch:{ all -> 0x01e7 }
            monitor-exit(r10)
            return
        L_0x01e7:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.m590a(boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006c, code lost:
        return;
     */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x0034=Splitter:B:21:0x0034, B:27:0x006d=Splitter:B:27:0x006d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startNativeMonitor() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.f763j     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x006d
            boolean r0 = r3.f762i     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x000a
            goto L_0x006d
        L_0x000a:
            com.tencent.bugly.crashreport.common.info.a r0 = r3.f757d     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.f504l     // Catch:{ all -> 0x0074 }
            boolean r0 = com.tencent.bugly.proguard.C3695ab.m679a((java.lang.String) r0)     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x0016
            r0 = 1
            goto L_0x0017
        L_0x0016:
            r0 = 0
        L_0x0017:
            java.lang.String r1 = "Bugly-ext"
            com.tencent.bugly.crashreport.common.info.a r2 = r3.f757d     // Catch:{ all -> 0x0074 }
            java.lang.String r2 = r2.f504l     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x0025
            com.tencent.bugly.crashreport.common.info.a r2 = r3.f757d     // Catch:{ all -> 0x0074 }
            r2.getClass()     // Catch:{ all -> 0x0074 }
            goto L_0x0026
        L_0x0025:
            r1 = r2
        L_0x0026:
            boolean r0 = m593a((java.lang.String) r1, (boolean) r0)     // Catch:{ all -> 0x0074 }
            r3.f763j = r0     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x0034
            boolean r0 = r3.f762i     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x0034
            monitor-exit(r3)
            return
        L_0x0034:
            boolean r0 = r3.f761h     // Catch:{ all -> 0x0074 }
            r3.m590a((boolean) r0)     // Catch:{ all -> 0x0074 }
            boolean r0 = f753m     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x006b
            com.tencent.bugly.crashreport.common.info.a r0 = r3.f757d     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.f501i     // Catch:{ all -> 0x0074 }
            r3.setNativeAppVersion(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.f757d     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.f503k     // Catch:{ all -> 0x0074 }
            r3.setNativeAppChannel(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.f757d     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.f495c     // Catch:{ all -> 0x0074 }
            r3.setNativeAppPackage(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.f757d     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.mo24072g()     // Catch:{ all -> 0x0074 }
            r3.setNativeUserId(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.f757d     // Catch:{ all -> 0x0074 }
            boolean r0 = r0.mo24058a()     // Catch:{ all -> 0x0074 }
            r3.setNativeIsAppForeground(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.f757d     // Catch:{ all -> 0x0074 }
            long r0 = r0.f476a     // Catch:{ all -> 0x0074 }
            r3.setNativeLaunchTime(r0)     // Catch:{ all -> 0x0074 }
        L_0x006b:
            monitor-exit(r3)
            return
        L_0x006d:
            boolean r0 = r3.f761h     // Catch:{ all -> 0x0074 }
            r3.m590a((boolean) r0)     // Catch:{ all -> 0x0074 }
            monitor-exit(r3)
            return
        L_0x0074:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.startNativeMonitor():void");
    }

    public void checkUploadRecordCrash() {
        this.f758e.mo24344a(new Runnable() {
            public final void run() {
                if (!C3695ab.m675a(NativeCrashHandler.this.f756c, "native_record_lock", 10000)) {
                    C3749y.m934a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
                    return;
                }
                if (!NativeCrashHandler.f755p) {
                    boolean unused = NativeCrashHandler.this.m591a((int) RoomDatabase.MAX_BIND_PARAMETER_CNT, Bugly.SDK_IS_DEV);
                }
                CrashDetailBean a = C3689b.m603a(NativeCrashHandler.this.f756c, NativeCrashHandler.this.f760g, NativeCrashHandler.this.f759f);
                if (a != null) {
                    C3749y.m934a("[Native] Get crash from native record.", new Object[0]);
                    if (!NativeCrashHandler.this.f766o.mo24137a(a)) {
                        NativeCrashHandler.this.f766o.mo24135a(a, 3000, false);
                    }
                    C3689b.m611a(false, NativeCrashHandler.this.f760g);
                }
                NativeCrashHandler.this.mo24181a();
                C3695ab.m691b(NativeCrashHandler.this.f756c, "native_record_lock");
            }
        });
    }

    /* renamed from: a */
    private static boolean m593a(String str, boolean z) {
        boolean z2;
        try {
            C3749y.m934a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                C3749y.m934a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th) {
                th = th;
                z2 = true;
            }
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
            C3749y.m940d(th.getMessage(), new Object[0]);
            C3749y.m940d("[Native] Failed to load so: %s", str);
            return z2;
        }
    }

    /* renamed from: c */
    private synchronized void m598c() {
        if (!this.f764k) {
            C3749y.m940d("[Native] Native crash report has already unregistered.", new Object[0]);
            return;
        }
        try {
            if (unregist() != null) {
                C3749y.m934a("[Native] Successfully closed native crash report.", new Object[0]);
                this.f764k = false;
                return;
            }
        } catch (Throwable unused) {
            C3749y.m939c("[Native] Failed to close native crash report.", new Object[0]);
        }
        try {
            C3695ab.m657a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", (Object) null, new Class[]{Boolean.TYPE}, new Object[]{false});
            this.f764k = false;
            C3749y.m934a("[Native] Successfully closed native crash report.", new Object[0]);
            return;
        } catch (Throwable unused2) {
            C3749y.m939c("[Native] Failed to close native crash report.", new Object[0]);
            this.f763j = false;
            this.f762i = false;
            return;
        }
    }

    public void testNativeCrash() {
        if (!this.f763j) {
            C3749y.m940d("[Native] Bugly SO file has not been load.", new Object[0]);
        } else {
            testCrash();
        }
    }

    public void testNativeCrash(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        m591a(16, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z2);
        m591a(17, sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(z3);
        m591a(18, sb3.toString());
        testNativeCrash();
    }

    public void dumpAnrNativeStack() {
        m591a(19, "1");
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.f759f;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo24181a() {
        long b = C3695ab.m683b() - C3678c.f682g;
        long b2 = C3695ab.m683b() + 86400000;
        File file = new File(this.f760g);
        if (file.exists() && file.isDirectory()) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    return;
                }
                if (listFiles.length != 0) {
                    int i = 0;
                    int i2 = 0;
                    for (File file2 : listFiles) {
                        long lastModified = file2.lastModified();
                        if (lastModified < b || lastModified >= b2) {
                            C3749y.m934a("[Native] Delete record file: %s", file2.getAbsolutePath());
                            i++;
                            if (file2.delete()) {
                                i2++;
                            }
                        }
                    }
                    C3749y.m939c("[Native] Number of record files overdue: %d, has deleted: %d", Integer.valueOf(i), Integer.valueOf(i2));
                }
            } catch (Throwable th) {
                C3749y.m935a(th);
            }
        }
    }

    public void removeEmptyNativeRecordFiles() {
        C3689b.m616c(this.f760g);
    }

    /* renamed from: b */
    private synchronized void m595b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            m598c();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.f765l;
    }

    /* renamed from: c */
    private synchronized void m599c(boolean z) {
        if (this.f765l != z) {
            C3749y.m934a("user change native %b", Boolean.valueOf(z));
            this.f765l = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        m599c(z);
        boolean isUserOpened = isUserOpened();
        C3644a a = C3644a.m426a();
        if (a != null) {
            isUserOpened = isUserOpened && a.mo24103c().f526e;
        }
        if (isUserOpened != this.f764k) {
            C3749y.m934a("native changed to %b", Boolean.valueOf(isUserOpened));
            m595b(isUserOpened);
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.f526e != this.f764k) {
                C3749y.m940d("server native changed to %b", Boolean.valueOf(strategyBean.f526e));
            }
        }
        boolean z = C3644a.m426a().mo24103c().f526e && this.f765l;
        if (z != this.f764k) {
            C3749y.m934a("native changed to %b", Boolean.valueOf(z));
            m595b(z);
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        if (!((!this.f762i && !this.f763j) || !f753m || str == null || str2 == null || str3 == null)) {
            try {
                if (this.f763j) {
                    return appendNativeLog(str, str2, str3);
                }
                Boolean bool = (Boolean) C3695ab.m657a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", (Object) null, new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                f753m = false;
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public String getLogFromNative() {
        if ((!this.f762i && !this.f763j) || !f753m) {
            return null;
        }
        try {
            if (this.f763j) {
                return getNativeLog();
            }
            return (String) C3695ab.m657a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "getNativeLog", (Object) null, (Class<?>[]) null, (Object[]) null);
        } catch (UnsatisfiedLinkError unused) {
            f753m = false;
            return null;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if ((this.f762i || this.f763j) && f753m && str != null && str2 != null) {
            try {
                if (this.f763j) {
                    return putNativeKeyValue(str, str2);
                }
                Boolean bool = (Boolean) C3695ab.m657a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", (Object) null, new Class[]{String.class, String.class}, new Object[]{str, str2});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                f753m = false;
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m591a(int i, String str) {
        if (this.f763j && f754n) {
            try {
                setNativeInfo(i, str);
                return true;
            } catch (UnsatisfiedLinkError unused) {
                f754n = false;
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public boolean filterSigabrtSysLog() {
        return m591a(998, "true");
    }

    public boolean setNativeAppVersion(String str) {
        return m591a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return m591a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return m591a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return m591a(11, str);
    }

    public boolean setNativeIsAppForeground(boolean z) {
        return m591a(14, z ? "true" : Bugly.SDK_IS_DEV);
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return m591a(15, String.valueOf(j));
        } catch (NumberFormatException e) {
            if (C3749y.m935a(e)) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }

    public void enableCatchAnrTrace() {
        if (Build.VERSION.SDK_INT <= 31 && Build.VERSION.SDK_INT > 19) {
            f752b |= 2;
        }
    }

    public boolean isEnableCatchAnrTrace() {
        return (f752b & 2) == 2;
    }
}
