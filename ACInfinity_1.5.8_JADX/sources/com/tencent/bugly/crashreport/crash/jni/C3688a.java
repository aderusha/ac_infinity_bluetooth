package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.info.C3627b;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.crashreport.crash.C3662b;
import com.tencent.bugly.crashreport.crash.C3678c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C3692aa;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3749y;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.crashreport.crash.jni.a */
/* compiled from: BUGLY */
public final class C3688a implements NativeExceptionHandler {

    /* renamed from: a */
    private final Context f768a;

    /* renamed from: b */
    private final C3662b f769b;

    /* renamed from: c */
    private final C3626a f770c;

    /* renamed from: d */
    private final C3644a f771d;

    public C3688a(Context context, C3626a aVar, C3662b bVar, C3644a aVar2) {
        this.f768a = context;
        this.f769b = bVar;
        this.f770c = aVar;
        this.f771d = aVar2;
    }

    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        int i;
        String str12;
        int indexOf;
        String str13 = str;
        String str14 = str8;
        byte[] bArr2 = bArr;
        boolean m = C3678c.m540a().mo24161m();
        if (m) {
            C3749y.m941e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f588b = 1;
        crashDetailBean.f591e = this.f770c.mo24079k();
        crashDetailBean.f592f = this.f770c.f501i;
        crashDetailBean.f593g = this.f770c.mo24088u();
        crashDetailBean.f599m = this.f770c.mo24072g();
        crashDetailBean.f600n = str3;
        String str15 = "";
        crashDetailBean.f601o = m ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : str15;
        crashDetailBean.f602p = str4;
        if (str5 != null) {
            str15 = str5;
        }
        crashDetailBean.f603q = str15;
        crashDetailBean.f604r = j;
        crashDetailBean.f607u = C3695ab.m667a(crashDetailBean.f603q.getBytes());
        crashDetailBean.f560A = str13;
        crashDetailBean.f561B = str2;
        crashDetailBean.f571L = this.f770c.mo24090w();
        crashDetailBean.f594h = this.f770c.mo24087t();
        crashDetailBean.f595i = this.f770c.mo24051F();
        crashDetailBean.f608v = str14;
        NativeCrashHandler instance = NativeCrashHandler.getInstance();
        String dumpFilePath = instance != null ? instance.getDumpFilePath() : null;
        String a = C3689b.m609a(dumpFilePath, str14);
        if (!C3695ab.m679a(a)) {
            crashDetailBean.f584Y = a;
        }
        crashDetailBean.f585Z = C3689b.m613b(dumpFilePath);
        crashDetailBean.f609w = C3689b.m608a(str9, C3678c.f680e, (String) null, false);
        crashDetailBean.f610x = C3689b.m608a(str10, C3678c.f680e, (String) null, true);
        crashDetailBean.f572M = str7;
        crashDetailBean.f573N = str6;
        crashDetailBean.f574O = str11;
        crashDetailBean.f565F = this.f770c.mo24083o();
        crashDetailBean.f566G = this.f770c.mo24082n();
        crashDetailBean.f567H = this.f770c.mo24084p();
        crashDetailBean.f568I = C3627b.m400f();
        crashDetailBean.f569J = C3627b.m401g();
        crashDetailBean.f570K = C3627b.m402h();
        if (z) {
            crashDetailBean.f562C = C3627b.m404j();
            crashDetailBean.f563D = C3627b.m398e();
            crashDetailBean.f564E = C3627b.m406l();
            if (crashDetailBean.f609w == null) {
                crashDetailBean.f609w = C3695ab.m660a(C3678c.f680e, (String) null);
            }
            crashDetailBean.f611y = C3692aa.m641a();
            crashDetailBean.f575P = this.f770c.f476a;
            crashDetailBean.f576Q = this.f770c.mo24058a();
            crashDetailBean.f612z = C3695ab.m672a(this.f770c.mo24075h(), C3678c.f681f, false);
            int indexOf2 = crashDetailBean.f603q.indexOf("java:\n");
            if (indexOf2 > 0 && (i = indexOf2 + 6) < crashDetailBean.f603q.length()) {
                String substring = crashDetailBean.f603q.substring(i, crashDetailBean.f603q.length() - 1);
                if (substring.length() > 0 && crashDetailBean.f612z.containsKey(crashDetailBean.f561B) && (indexOf = str12.indexOf(substring)) > 0) {
                    String substring2 = (str12 = crashDetailBean.f612z.get(crashDetailBean.f561B)).substring(indexOf);
                    crashDetailBean.f612z.put(crashDetailBean.f561B, substring2);
                    crashDetailBean.f603q = crashDetailBean.f603q.substring(0, i);
                    crashDetailBean.f603q += substring2;
                }
            }
            if (str13 == null) {
                crashDetailBean.f560A = this.f770c.f496d;
            }
            this.f769b.mo24140d(crashDetailBean);
            crashDetailBean.f579T = this.f770c.mo24049D();
            crashDetailBean.f580U = this.f770c.mo24050E();
            crashDetailBean.f581V = this.f770c.mo24091x();
            crashDetailBean.f582W = this.f770c.mo24048C();
        } else {
            crashDetailBean.f562C = -1;
            crashDetailBean.f563D = -1;
            crashDetailBean.f564E = -1;
            if (crashDetailBean.f609w == null) {
                crashDetailBean.f609w = "This crash occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.f575P = -1;
            crashDetailBean.f579T = -1;
            crashDetailBean.f580U = -1;
            crashDetailBean.f581V = map;
            crashDetailBean.f582W = this.f770c.mo24048C();
            crashDetailBean.f612z = null;
            if (str13 == null) {
                crashDetailBean.f560A = "unknown(record)";
            }
            if (bArr2 != null) {
                crashDetailBean.f611y = bArr2;
            }
        }
        return crashDetailBean;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        int i7 = i;
        C3749y.m934a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, (String[]) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x009c A[Catch:{ all -> 0x0224 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00ba A[Catch:{ all -> 0x0224 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ef A[Catch:{ all -> 0x0224 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0102 A[SYNTHETIC, Splitter:B:29:0x0102] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0145 A[Catch:{ all -> 0x0224 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x014a A[Catch:{ all -> 0x0224 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0150 A[Catch:{ all -> 0x0224 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01a3 A[Catch:{ all -> 0x0220 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01ac A[Catch:{ all -> 0x0220 }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleNativeException2(int r28, int r29, long r30, long r32, java.lang.String r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, int r38, java.lang.String r39, int r40, int r41, int r42, java.lang.String r43, java.lang.String r44, java.lang.String[] r45) {
        /*
            r27 = this;
            r14 = r27
            r0 = r29
            r13 = r35
            r1 = r40
            r12 = 0
            java.lang.Object[] r2 = new java.lang.Object[r12]
            java.lang.String r3 = "Native Crash Happen v2"
            com.tencent.bugly.proguard.C3749y.m934a(r3, r2)
            java.lang.String r2 = ")"
            java.lang.String r3 = "("
            if (r38 <= 0) goto L_0x0031
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0224 }
            r4.<init>()     // Catch:{ all -> 0x0224 }
            r5 = r34
            r4.append(r5)     // Catch:{ all -> 0x0224 }
            r4.append(r3)     // Catch:{ all -> 0x0224 }
            r6 = r39
            r4.append(r6)     // Catch:{ all -> 0x0224 }
            r4.append(r2)     // Catch:{ all -> 0x0224 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0224 }
            r11 = r4
            goto L_0x0036
        L_0x0031:
            r5 = r34
            r6 = r39
            r11 = r5
        L_0x0036:
            java.lang.String r10 = com.tencent.bugly.crashreport.crash.jni.C3689b.m607a((java.lang.String) r36)     // Catch:{ all -> 0x0224 }
            java.util.Map r4 = m602a(r45)     // Catch:{ all -> 0x0224 }
            java.lang.String r5 = "HasPendingException"
            java.lang.Object r5 = r4.get(r5)     // Catch:{ all -> 0x0224 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x0224 }
            r9 = 1
            if (r5 == 0) goto L_0x005b
            java.lang.String r7 = "true"
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0224 }
            if (r5 == 0) goto L_0x005b
            java.lang.String r5 = "Native crash happened with a Java pending exception."
            java.lang.Object[] r7 = new java.lang.Object[r12]     // Catch:{ all -> 0x0224 }
            com.tencent.bugly.proguard.C3749y.m934a(r5, r7)     // Catch:{ all -> 0x0224 }
            r18 = 1
            goto L_0x005d
        L_0x005b:
            r18 = 0
        L_0x005d:
            com.tencent.bugly.crashreport.common.info.a r5 = r14.f770c     // Catch:{ all -> 0x0224 }
            java.lang.String r7 = "ExceptionProcessName"
            java.lang.Object r7 = r4.get(r7)     // Catch:{ all -> 0x0224 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0224 }
            if (r7 == 0) goto L_0x007a
            int r8 = r7.length()     // Catch:{ all -> 0x0224 }
            if (r8 != 0) goto L_0x0070
            goto L_0x007a
        L_0x0070:
            java.lang.String r5 = "Name of crash process: %s"
            java.lang.Object[] r8 = new java.lang.Object[r9]     // Catch:{ all -> 0x0224 }
            r8[r12] = r7     // Catch:{ all -> 0x0224 }
            com.tencent.bugly.proguard.C3749y.m939c(r5, r8)     // Catch:{ all -> 0x0224 }
            goto L_0x007c
        L_0x007a:
            java.lang.String r7 = r5.f496d     // Catch:{ all -> 0x0224 }
        L_0x007c:
            r19 = r7
            java.lang.String r5 = "ExceptionThreadName"
            java.lang.Object r5 = r4.get(r5)     // Catch:{ all -> 0x0224 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x0224 }
            java.lang.String r7 = "crash thread name:%s tid:%s"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x0224 }
            r8[r12] = r5     // Catch:{ all -> 0x0224 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r29)     // Catch:{ all -> 0x0224 }
            r8[r9] = r15     // Catch:{ all -> 0x0224 }
            com.tencent.bugly.proguard.C3749y.m939c(r7, r8)     // Catch:{ all -> 0x0224 }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0224 }
            if (r7 == 0) goto L_0x00ba
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0224 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0224 }
            r7.<init>()     // Catch:{ all -> 0x0224 }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x0224 }
            r7.append(r5)     // Catch:{ all -> 0x0224 }
            r7.append(r3)     // Catch:{ all -> 0x0224 }
            r7.append(r0)     // Catch:{ all -> 0x0224 }
            r7.append(r2)     // Catch:{ all -> 0x0224 }
        L_0x00b5:
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x0224 }
            goto L_0x00cc
        L_0x00ba:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0224 }
            r7.<init>()     // Catch:{ all -> 0x0224 }
            r7.append(r5)     // Catch:{ all -> 0x0224 }
            r7.append(r3)     // Catch:{ all -> 0x0224 }
            r7.append(r0)     // Catch:{ all -> 0x0224 }
            r7.append(r2)     // Catch:{ all -> 0x0224 }
            goto L_0x00b5
        L_0x00cc:
            r7 = 1000(0x3e8, double:4.94E-321)
            long r15 = r30 * r7
            long r7 = r32 / r7
            long r7 = r7 + r15
            java.lang.String r5 = "SysLogPath"
            java.lang.Object r5 = r4.get(r5)     // Catch:{ all -> 0x0224 }
            r20 = r5
            java.lang.String r20 = (java.lang.String) r20     // Catch:{ all -> 0x0224 }
            java.lang.String r5 = "JniLogPath"
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x0224 }
            r21 = r4
            java.lang.String r21 = (java.lang.String) r21     // Catch:{ all -> 0x0224 }
            com.tencent.bugly.crashreport.common.strategy.a r4 = r14.f771d     // Catch:{ all -> 0x0224 }
            boolean r4 = r4.mo24102b()     // Catch:{ all -> 0x0224 }
            if (r4 != 0) goto L_0x00f6
            java.lang.String r4 = "no remote but still store!"
            java.lang.Object[] r5 = new java.lang.Object[r12]     // Catch:{ all -> 0x0224 }
            com.tencent.bugly.proguard.C3749y.m940d(r4, r5)     // Catch:{ all -> 0x0224 }
        L_0x00f6:
            com.tencent.bugly.crashreport.common.strategy.a r4 = r14.f771d     // Catch:{ all -> 0x0224 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r4 = r4.mo24103c()     // Catch:{ all -> 0x0224 }
            boolean r4 = r4.f526e     // Catch:{ all -> 0x0224 }
            java.lang.String r5 = "\n"
            if (r4 != 0) goto L_0x0143
            com.tencent.bugly.crashreport.common.strategy.a r4 = r14.f771d     // Catch:{ all -> 0x0224 }
            boolean r4 = r4.mo24102b()     // Catch:{ all -> 0x0224 }
            if (r4 == 0) goto L_0x0143
            java.lang.String r1 = "crash report was closed by remote , will not upload to Bugly , print local for helpful!"
            java.lang.Object[] r2 = new java.lang.Object[r12]     // Catch:{ all -> 0x0224 }
            com.tencent.bugly.proguard.C3749y.m941e(r1, r2)     // Catch:{ all -> 0x0224 }
            java.lang.String r1 = "NATIVE_CRASH"
            java.lang.String r2 = com.tencent.bugly.proguard.C3695ab.m659a()     // Catch:{ all -> 0x0224 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0224 }
            r3.<init>()     // Catch:{ all -> 0x0224 }
            r3.append(r11)     // Catch:{ all -> 0x0224 }
            r3.append(r5)     // Catch:{ all -> 0x0224 }
            r3.append(r13)     // Catch:{ all -> 0x0224 }
            r3.append(r5)     // Catch:{ all -> 0x0224 }
            r3.append(r10)     // Catch:{ all -> 0x0224 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0224 }
            r4 = 0
            r28 = r1
            r29 = r2
            r30 = r19
            r31 = r0
            r32 = r3
            r33 = r4
            com.tencent.bugly.crashreport.crash.C3662b.m495a((java.lang.String) r28, (java.lang.String) r29, (java.lang.String) r30, (java.lang.String) r31, (java.lang.String) r32, (com.tencent.bugly.crashreport.crash.CrashDetailBean) r33)     // Catch:{ all -> 0x0224 }
            com.tencent.bugly.proguard.C3695ab.m689b((java.lang.String) r37)     // Catch:{ all -> 0x0224 }
            return
        L_0x0143:
            if (r38 <= 0) goto L_0x014a
            java.lang.String r4 = "KERNEL"
            r22 = r4
            goto L_0x014c
        L_0x014a:
            r22 = r6
        L_0x014c:
            java.lang.String r4 = "UNKNOWN"
            if (r38 > 0) goto L_0x0178
            if (r1 <= 0) goto L_0x0156
            java.lang.String r4 = com.tencent.bugly.crashreport.common.info.AppInfo.m326a((int) r40)     // Catch:{ all -> 0x0224 }
        L_0x0156:
            java.lang.String r6 = java.lang.String.valueOf(r40)     // Catch:{ all -> 0x0224 }
            boolean r6 = r4.equals(r6)     // Catch:{ all -> 0x0224 }
            if (r6 != 0) goto L_0x0178
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0224 }
            r6.<init>()     // Catch:{ all -> 0x0224 }
            r6.append(r4)     // Catch:{ all -> 0x0224 }
            r6.append(r3)     // Catch:{ all -> 0x0224 }
            r6.append(r1)     // Catch:{ all -> 0x0224 }
            r6.append(r2)     // Catch:{ all -> 0x0224 }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x0224 }
            r23 = r1
            goto L_0x017a
        L_0x0178:
            r23 = r4
        L_0x017a:
            r15 = 0
            r16 = 0
            r17 = 1
            r1 = r27
            r2 = r19
            r3 = r0
            r6 = r5
            r4 = r7
            r8 = r6
            r6 = r11
            r7 = r35
            r24 = r8
            r8 = r10
            r9 = r22
            r25 = r10
            r10 = r23
            r26 = r11
            r11 = r37
            r12 = r20
            r13 = r21
            r14 = r44
            com.tencent.bugly.crashreport.crash.CrashDetailBean r1 = r1.packageCrashDatas(r2, r3, r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x0220 }
            if (r1 != 0) goto L_0x01ac
            java.lang.String r0 = "pkg crash datas fail!"
            r2 = 0
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0220 }
            com.tencent.bugly.proguard.C3749y.m941e(r0, r1)     // Catch:{ all -> 0x0220 }
            return
        L_0x01ac:
            r2 = 0
            java.lang.String r3 = "NATIVE_CRASH"
            java.lang.String r4 = com.tencent.bugly.proguard.C3695ab.m659a()     // Catch:{ all -> 0x0220 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0220 }
            r5.<init>()     // Catch:{ all -> 0x0220 }
            r6 = r26
            r5.append(r6)     // Catch:{ all -> 0x0220 }
            r6 = r24
            r5.append(r6)     // Catch:{ all -> 0x0220 }
            r7 = r35
            r5.append(r7)     // Catch:{ all -> 0x0220 }
            r5.append(r6)     // Catch:{ all -> 0x0220 }
            r6 = r25
            r5.append(r6)     // Catch:{ all -> 0x0220 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0220 }
            r28 = r3
            r29 = r4
            r30 = r19
            r31 = r0
            r32 = r5
            r33 = r1
            com.tencent.bugly.crashreport.crash.C3662b.m495a((java.lang.String) r28, (java.lang.String) r29, (java.lang.String) r30, (java.lang.String) r31, (java.lang.String) r32, (com.tencent.bugly.crashreport.crash.CrashDetailBean) r33)     // Catch:{ all -> 0x0220 }
            r3 = r27
            com.tencent.bugly.crashreport.crash.b r0 = r3.f769b     // Catch:{ all -> 0x021e }
            if (r0 != 0) goto L_0x01f0
            java.lang.String r0 = "crashHandler is null. Won't upload native crash."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x021e }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r1)     // Catch:{ all -> 0x021e }
            goto L_0x022f
        L_0x01f0:
            boolean r0 = r0.mo24138b((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1)     // Catch:{ all -> 0x021e }
            if (r0 != 0) goto L_0x01f8
            r12 = 1
            goto L_0x01f9
        L_0x01f8:
            r12 = 0
        L_0x01f9:
            r0 = 0
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler r2 = com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.getInstance()     // Catch:{ all -> 0x021e }
            if (r2 == 0) goto L_0x0204
            java.lang.String r0 = r2.getDumpFilePath()     // Catch:{ all -> 0x021e }
        L_0x0204:
            r2 = 1
            com.tencent.bugly.crashreport.crash.jni.C3689b.m611a((boolean) r2, (java.lang.String) r0)     // Catch:{ all -> 0x021e }
            if (r12 == 0) goto L_0x0211
            com.tencent.bugly.crashreport.crash.b r0 = r3.f769b     // Catch:{ all -> 0x021e }
            r4 = 3000(0xbb8, double:1.482E-320)
            r0.mo24135a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1, (long) r4, (boolean) r2)     // Catch:{ all -> 0x021e }
        L_0x0211:
            com.tencent.bugly.crashreport.crash.b r0 = r3.f769b     // Catch:{ all -> 0x021e }
            r0.mo24139c((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1)     // Catch:{ all -> 0x021e }
            com.tencent.bugly.crashreport.crash.c r0 = com.tencent.bugly.crashreport.crash.C3678c.m540a()     // Catch:{ all -> 0x021e }
            r0.mo24153e()     // Catch:{ all -> 0x021e }
            return
        L_0x021e:
            r0 = move-exception
            goto L_0x0226
        L_0x0220:
            r0 = move-exception
            r3 = r27
            goto L_0x0226
        L_0x0224:
            r0 = move-exception
            r3 = r14
        L_0x0226:
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x022f
            r0.printStackTrace()
        L_0x022f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.C3688a.handleNativeException2(int, int, long, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    /* renamed from: a */
    private static Map<String, String> m602a(String[] strArr) {
        HashMap hashMap = new HashMap(strArr == null ? 1 : strArr.length);
        if (strArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                if (str != null) {
                    C3749y.m934a("Extra message[%d]: %s", Integer.valueOf(i), str);
                    String[] split = str.split("=");
                    if (split.length == 2) {
                        hashMap.put(split[0], split[1]);
                    } else {
                        C3749y.m940d("bad extraMsg %s", str);
                    }
                }
            }
        } else {
            C3749y.m939c("not found extraMsg", new Object[0]);
        }
        return hashMap;
    }
}
