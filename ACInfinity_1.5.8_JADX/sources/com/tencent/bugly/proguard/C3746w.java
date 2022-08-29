package com.tencent.bugly.proguard;

import android.content.Context;
import android.util.Pair;
import com.bumptech.glide.load.Key;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import java.util.Map;
import java.util.UUID;

/* renamed from: com.tencent.bugly.proguard.w */
/* compiled from: BUGLY */
public final class C3746w implements Runnable {

    /* renamed from: a */
    private int f1037a;

    /* renamed from: b */
    private int f1038b;

    /* renamed from: c */
    private final Context f1039c;

    /* renamed from: d */
    private final int f1040d;

    /* renamed from: e */
    private final byte[] f1041e;

    /* renamed from: f */
    private final C3626a f1042f;

    /* renamed from: g */
    private final C3644a f1043g;

    /* renamed from: h */
    private final C3735s f1044h;

    /* renamed from: i */
    private final C3743v f1045i;

    /* renamed from: j */
    private final int f1046j;

    /* renamed from: k */
    private final C3742u f1047k;

    /* renamed from: l */
    private final C3742u f1048l;

    /* renamed from: m */
    private String f1049m;

    /* renamed from: n */
    private final String f1050n;

    /* renamed from: o */
    private final Map<String, String> f1051o;

    /* renamed from: p */
    private int f1052p;

    /* renamed from: q */
    private long f1053q;

    /* renamed from: r */
    private long f1054r;

    /* renamed from: s */
    private boolean f1055s;

    public C3746w(Context context, int i, int i2, byte[] bArr, String str, String str2, C3742u uVar, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, uVar, 2, 30000, z2, (Map<String, String>) null);
    }

    public C3746w(Context context, int i, int i2, byte[] bArr, String str, String str2, C3742u uVar, int i3, int i4, boolean z, Map<String, String> map) {
        this.f1037a = 2;
        this.f1038b = 30000;
        this.f1049m = null;
        this.f1052p = 0;
        this.f1053q = 0;
        this.f1054r = 0;
        this.f1055s = false;
        this.f1039c = context;
        this.f1042f = C3626a.m337a(context);
        this.f1041e = bArr;
        this.f1043g = C3644a.m426a();
        this.f1044h = C3735s.m869a(context);
        this.f1045i = C3743v.m903a();
        this.f1046j = i;
        this.f1049m = str;
        this.f1050n = str2;
        this.f1047k = uVar;
        this.f1048l = null;
        this.f1040d = i2;
        if (i3 > 0) {
            this.f1037a = i3;
        }
        if (i4 > 0) {
            this.f1038b = i4;
        }
        this.f1055s = z;
        this.f1051o = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m922a(com.tencent.bugly.proguard.C3709ao r5, boolean r6, int r7, java.lang.String r8) {
        /*
            r4 = this;
            int r5 = r4.f1040d
            r0 = 630(0x276, float:8.83E-43)
            if (r5 == r0) goto L_0x001a
            r0 = 640(0x280, float:8.97E-43)
            if (r5 == r0) goto L_0x0017
            r0 = 830(0x33e, float:1.163E-42)
            if (r5 == r0) goto L_0x001a
            r0 = 840(0x348, float:1.177E-42)
            if (r5 == r0) goto L_0x0017
            java.lang.String r5 = java.lang.String.valueOf(r5)
            goto L_0x001c
        L_0x0017:
            java.lang.String r5 = "userinfo"
            goto L_0x001c
        L_0x001a:
            java.lang.String r5 = "crash"
        L_0x001c:
            r0 = 1
            r1 = 0
            if (r6 == 0) goto L_0x002a
            java.lang.Object[] r7 = new java.lang.Object[r0]
            r7[r1] = r5
            java.lang.String r5 = "[Upload] Success: %s"
            com.tencent.bugly.proguard.C3749y.m934a(r5, r7)
            goto L_0x003d
        L_0x002a:
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r2[r1] = r7
            r2[r0] = r5
            r5 = 2
            r2[r5] = r8
            java.lang.String r5 = "[Upload] Failed to upload(%d) %s: %s"
            com.tencent.bugly.proguard.C3749y.m941e(r5, r2)
        L_0x003d:
            long r0 = r4.f1053q
            long r2 = r4.f1054r
            long r0 = r0 + r2
            r2 = 0
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 <= 0) goto L_0x005d
            com.tencent.bugly.proguard.v r5 = r4.f1045i
            boolean r7 = r4.f1055s
            long r0 = r5.mo24333a((boolean) r7)
            long r2 = r4.f1053q
            long r0 = r0 + r2
            long r2 = r4.f1054r
            long r0 = r0 + r2
            com.tencent.bugly.proguard.v r5 = r4.f1045i
            boolean r7 = r4.f1055s
            r5.mo24337a((long) r0, (boolean) r7)
        L_0x005d:
            com.tencent.bugly.proguard.u r5 = r4.f1047k
            if (r5 == 0) goto L_0x0064
            r5.mo24028a(r6, r8)
        L_0x0064:
            com.tencent.bugly.proguard.u r5 = r4.f1048l
            if (r5 == 0) goto L_0x006b
            r5.mo24028a(r6, r8)
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3746w.m922a(com.tencent.bugly.proguard.ao, boolean, int, java.lang.String):void");
    }

    /* renamed from: a */
    private static boolean m923a(C3709ao aoVar, C3626a aVar, C3644a aVar2) {
        if (aoVar == null) {
            C3749y.m940d("resp == null!", new Object[0]);
            return false;
        } else if (aoVar.f888a != 0) {
            C3749y.m941e("resp result error %d", Byte.valueOf(aoVar.f888a));
            return false;
        } else {
            try {
                if (!C3695ab.m679a(aoVar.f892e) && !C3626a.m339b().mo24081m().equals(aoVar.f892e)) {
                    C3730o.m839a().mo24298a(C3644a.f544a, "device", aoVar.f892e.getBytes(Key.STRING_CHARSET_NAME), (C3729n) null, true);
                    aVar.mo24071f(aoVar.f892e);
                }
            } catch (Throwable th) {
                C3749y.m935a(th);
            }
            aVar.f500h = aoVar.f891d;
            if (aoVar.f889b == 510) {
                if (aoVar.f890c == null) {
                    C3749y.m941e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(aoVar.f889b));
                    return false;
                }
                C3711aq aqVar = (C3711aq) C3691a.m622a(aoVar.f890c, C3711aq.class);
                if (aqVar == null) {
                    C3749y.m941e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(aoVar.f889b));
                    return false;
                }
                aVar2.mo24101a(aqVar);
            }
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x01e6 A[Catch:{ all -> 0x01f1 }, LOOP:0: B:36:0x00cc->B:68:0x01e6, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01ea A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r12 = this;
            r0 = 0
            r12.f1052p = r0     // Catch:{ all -> 0x01f1 }
            r1 = 0
            r12.f1053q = r1     // Catch:{ all -> 0x01f1 }
            r12.f1054r = r1     // Catch:{ all -> 0x01f1 }
            android.content.Context r1 = r12.f1039c     // Catch:{ all -> 0x01f1 }
            java.lang.String r1 = com.tencent.bugly.crashreport.common.info.C3627b.m393b(r1)     // Catch:{ all -> 0x01f1 }
            r2 = 0
            if (r1 != 0) goto L_0x0015
            java.lang.String r1 = "network is not available"
            goto L_0x003e
        L_0x0015:
            byte[] r1 = r12.f1041e     // Catch:{ all -> 0x01f1 }
            if (r1 == 0) goto L_0x003c
            int r1 = r1.length     // Catch:{ all -> 0x01f1 }
            if (r1 != 0) goto L_0x001d
            goto L_0x003c
        L_0x001d:
            android.content.Context r1 = r12.f1039c     // Catch:{ all -> 0x01f1 }
            if (r1 == 0) goto L_0x0039
            com.tencent.bugly.crashreport.common.info.a r1 = r12.f1042f     // Catch:{ all -> 0x01f1 }
            if (r1 == 0) goto L_0x0039
            com.tencent.bugly.crashreport.common.strategy.a r1 = r12.f1043g     // Catch:{ all -> 0x01f1 }
            if (r1 == 0) goto L_0x0039
            com.tencent.bugly.proguard.s r3 = r12.f1044h     // Catch:{ all -> 0x01f1 }
            if (r3 != 0) goto L_0x002e
            goto L_0x0039
        L_0x002e:
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r1 = r1.mo24103c()     // Catch:{ all -> 0x01f1 }
            if (r1 != 0) goto L_0x0037
            java.lang.String r1 = "illegal local strategy"
            goto L_0x003e
        L_0x0037:
            r1 = r2
            goto L_0x003e
        L_0x0039:
            java.lang.String r1 = "illegal access error"
            goto L_0x003e
        L_0x003c:
            java.lang.String r1 = "request package is empty!"
        L_0x003e:
            if (r1 == 0) goto L_0x0044
            r12.m922a(r2, r0, r0, r1)     // Catch:{ all -> 0x01f1 }
            return
        L_0x0044:
            byte[] r1 = r12.f1041e     // Catch:{ all -> 0x01f1 }
            r3 = 2
            byte[] r1 = com.tencent.bugly.proguard.C3695ab.m682a((byte[]) r1, (int) r3)     // Catch:{ all -> 0x01f1 }
            if (r1 != 0) goto L_0x0053
            java.lang.String r1 = "failed to zip request body"
            r12.m922a(r2, r0, r0, r1)     // Catch:{ all -> 0x01f1 }
            return
        L_0x0053:
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ all -> 0x01f1 }
            r5 = 10
            r4.<init>(r5)     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = "tls"
            java.lang.String r6 = "1"
            r4.put(r5, r6)     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = "prodId"
            com.tencent.bugly.crashreport.common.info.a r6 = r12.f1042f     // Catch:{ all -> 0x01f1 }
            java.lang.String r6 = r6.mo24070f()     // Catch:{ all -> 0x01f1 }
            r4.put(r5, r6)     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = "bundleId"
            com.tencent.bugly.crashreport.common.info.a r6 = r12.f1042f     // Catch:{ all -> 0x01f1 }
            java.lang.String r6 = r6.f495c     // Catch:{ all -> 0x01f1 }
            r4.put(r5, r6)     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = "appVer"
            com.tencent.bugly.crashreport.common.info.a r6 = r12.f1042f     // Catch:{ all -> 0x01f1 }
            java.lang.String r6 = r6.f501i     // Catch:{ all -> 0x01f1 }
            r4.put(r5, r6)     // Catch:{ all -> 0x01f1 }
            java.util.Map<java.lang.String, java.lang.String> r5 = r12.f1051o     // Catch:{ all -> 0x01f1 }
            if (r5 == 0) goto L_0x0085
            r4.putAll(r5)     // Catch:{ all -> 0x01f1 }
        L_0x0085:
            java.lang.String r5 = "cmd"
            int r6 = r12.f1040d     // Catch:{ all -> 0x01f1 }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x01f1 }
            r4.put(r5, r6)     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = "platformId"
            r6 = 1
            java.lang.String r7 = java.lang.Byte.toString(r6)     // Catch:{ all -> 0x01f1 }
            r4.put(r5, r7)     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = "sdkVer"
            com.tencent.bugly.crashreport.common.info.a r7 = r12.f1042f     // Catch:{ all -> 0x01f1 }
            java.lang.String r7 = r7.f498f     // Catch:{ all -> 0x01f1 }
            r4.put(r5, r7)     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = "strategylastUpdateTime"
            com.tencent.bugly.crashreport.common.strategy.a r7 = r12.f1043g     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r7 = r7.mo24103c()     // Catch:{ all -> 0x01f1 }
            long r7 = r7.f535n     // Catch:{ all -> 0x01f1 }
            java.lang.String r7 = java.lang.Long.toString(r7)     // Catch:{ all -> 0x01f1 }
            r4.put(r5, r7)     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.v r5 = r12.f1045i     // Catch:{ all -> 0x01f1 }
            int r7 = r12.f1046j     // Catch:{ all -> 0x01f1 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01f1 }
            r5.mo24334a((int) r7, (long) r8)     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.u r5 = r12.f1047k     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.u r5 = r12.f1048l     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = r12.f1049m     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.crashreport.common.strategy.a r7 = r12.f1043g     // Catch:{ all -> 0x01f1 }
            r7.mo24103c()     // Catch:{ all -> 0x01f1 }
            r7 = 0
            r8 = 0
        L_0x00cc:
            int r9 = r7 + 1
            int r10 = r12.f1037a     // Catch:{ all -> 0x01f1 }
            if (r7 >= r10) goto L_0x01eb
            if (r9 <= r6) goto L_0x00f8
            java.lang.String r7 = "[Upload] Failed to upload last time, wait and try(%d) again."
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ all -> 0x01f1 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x01f1 }
            r8[r0] = r10     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.C3749y.m940d(r7, r8)     // Catch:{ all -> 0x01f1 }
            int r7 = r12.f1038b     // Catch:{ all -> 0x01f1 }
            long r7 = (long) r7     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.C3695ab.m687b((long) r7)     // Catch:{ all -> 0x01f1 }
            int r7 = r12.f1037a     // Catch:{ all -> 0x01f1 }
            if (r9 != r7) goto L_0x00f8
            java.lang.String r5 = "[Upload] Use the back-up url at the last time: %s"
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x01f1 }
            java.lang.String r8 = r12.f1050n     // Catch:{ all -> 0x01f1 }
            r7[r0] = r8     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.C3749y.m940d(r5, r7)     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = r12.f1050n     // Catch:{ all -> 0x01f1 }
        L_0x00f8:
            java.lang.String r7 = "[Upload] Send %d bytes"
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ all -> 0x01f1 }
            int r10 = r1.length     // Catch:{ all -> 0x01f1 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x01f1 }
            r8[r0] = r10     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.C3749y.m939c(r7, r8)     // Catch:{ all -> 0x01f1 }
            java.lang.String r5 = m921a((java.lang.String) r5)     // Catch:{ all -> 0x01f1 }
            java.lang.String r7 = "[Upload] Upload to %s with cmd %d (pid=%d | tid=%d)."
            r8 = 4
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x01f1 }
            r8[r0] = r5     // Catch:{ all -> 0x01f1 }
            int r10 = r12.f1040d     // Catch:{ all -> 0x01f1 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x01f1 }
            r8[r6] = r10     // Catch:{ all -> 0x01f1 }
            int r10 = android.os.Process.myPid()     // Catch:{ all -> 0x01f1 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x01f1 }
            r8[r3] = r10     // Catch:{ all -> 0x01f1 }
            r10 = 3
            int r11 = android.os.Process.myTid()     // Catch:{ all -> 0x01f1 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x01f1 }
            r8[r10] = r11     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.C3749y.m939c(r7, r8)     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.s r7 = r12.f1044h     // Catch:{ all -> 0x01f1 }
            byte[] r7 = r7.mo24310a((java.lang.String) r5, (byte[]) r1, (com.tencent.bugly.proguard.C3746w) r12, (java.util.Map<java.lang.String, java.lang.String>) r4)     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.s r8 = r12.f1044h     // Catch:{ all -> 0x01f1 }
            java.util.Map<java.lang.String, java.lang.String> r8 = r8.f1006a     // Catch:{ all -> 0x01f1 }
            android.util.Pair r10 = r12.m920a(r7, r8)     // Catch:{ all -> 0x01f1 }
            java.lang.Object r11 = r10.first     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x01f1 }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x01f1 }
            if (r11 != 0) goto L_0x0153
            java.lang.Object r7 = r10.second     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x01f1 }
        L_0x014d:
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x01f1 }
            goto L_0x01e4
        L_0x0153:
            android.util.Pair r8 = r12.m919a((java.util.Map<java.lang.String, java.lang.String>) r8)     // Catch:{ all -> 0x01f1 }
            java.lang.Object r10 = r8.first     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ all -> 0x01f1 }
            boolean r10 = r10.booleanValue()     // Catch:{ all -> 0x01f1 }
            if (r10 != 0) goto L_0x0166
            java.lang.Object r7 = r8.second     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x01f1 }
            goto L_0x014d
        L_0x0166:
            byte[] r8 = com.tencent.bugly.proguard.C3695ab.m692b((byte[]) r7, (int) r3)     // Catch:{ all -> 0x01f1 }
            if (r8 == 0) goto L_0x016d
            r7 = r8
        L_0x016d:
            com.tencent.bugly.proguard.ao r7 = com.tencent.bugly.proguard.C3691a.m630b(r7)     // Catch:{ all -> 0x01f1 }
            if (r7 != 0) goto L_0x0186
            java.lang.String r7 = "failed to decode response package"
            r12.m922a(r2, r0, r6, r7)     // Catch:{ all -> 0x01f1 }
            android.util.Pair r7 = new android.util.Pair     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x01f1 }
            r7.<init>(r8, r10)     // Catch:{ all -> 0x01f1 }
            goto L_0x01d3
        L_0x0186:
            java.lang.String r8 = "[Upload] Response cmd is: %d, length of sBuffer is: %d"
            java.lang.Object[] r10 = new java.lang.Object[r3]     // Catch:{ all -> 0x01f1 }
            int r11 = r7.f889b     // Catch:{ all -> 0x01f1 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x01f1 }
            r10[r0] = r11     // Catch:{ all -> 0x01f1 }
            byte[] r11 = r7.f890c     // Catch:{ all -> 0x01f1 }
            if (r11 != 0) goto L_0x0198
            r11 = 0
            goto L_0x019b
        L_0x0198:
            byte[] r11 = r7.f890c     // Catch:{ all -> 0x01f1 }
            int r11 = r11.length     // Catch:{ all -> 0x01f1 }
        L_0x019b:
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x01f1 }
            r10[r6] = r11     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.proguard.C3749y.m939c(r8, r10)     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.crashreport.common.info.a r8 = r12.f1042f     // Catch:{ all -> 0x01f1 }
            com.tencent.bugly.crashreport.common.strategy.a r10 = r12.f1043g     // Catch:{ all -> 0x01f1 }
            boolean r8 = m923a(r7, r8, r10)     // Catch:{ all -> 0x01f1 }
            if (r8 != 0) goto L_0x01c1
            java.lang.String r8 = "failed to process response package"
            r12.m922a(r7, r0, r3, r8)     // Catch:{ all -> 0x01f1 }
            android.util.Pair r7 = new android.util.Pair     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x01f1 }
            r7.<init>(r8, r10)     // Catch:{ all -> 0x01f1 }
            goto L_0x01d3
        L_0x01c1:
            java.lang.String r8 = "successfully uploaded"
            r12.m922a(r7, r6, r3, r8)     // Catch:{ all -> 0x01f1 }
            android.util.Pair r7 = new android.util.Pair     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x01f1 }
            r7.<init>(r8, r10)     // Catch:{ all -> 0x01f1 }
        L_0x01d3:
            java.lang.Object r8 = r7.first     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x01f1 }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x01f1 }
            if (r8 != 0) goto L_0x01e3
            java.lang.Object r7 = r7.second     // Catch:{ all -> 0x01f1 }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x01f1 }
            goto L_0x014d
        L_0x01e3:
            r7 = 0
        L_0x01e4:
            if (r7 == 0) goto L_0x01ea
            r7 = r9
            r8 = 1
            goto L_0x00cc
        L_0x01ea:
            return
        L_0x01eb:
            java.lang.String r1 = "failed after many attempts"
            r12.m922a(r2, r0, r8, r1)     // Catch:{ all -> 0x01f1 }
            return
        L_0x01f1:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x01fb
            r0.printStackTrace()
        L_0x01fb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3746w.run():void");
    }

    /* renamed from: a */
    private Pair<Boolean, Boolean> m920a(byte[] bArr, Map<String, String> map) {
        if (bArr == null) {
            C3749y.m941e("[Upload] Failed to upload(%d): %s", 1, "Failed to upload for no response!");
            return new Pair<>(false, true);
        }
        C3749y.m939c("[Upload] Received %d bytes", Integer.valueOf(bArr.length));
        if (bArr.length != 0) {
            return new Pair<>(true, true);
        }
        m922a((C3709ao) null, false, 1, "response data from server is empty");
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                C3749y.m939c("[Upload] HTTP headers from server: key = %s, value = %s", next.getKey(), next.getValue());
            }
        }
        return new Pair<>(false, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ca  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<java.lang.Boolean, java.lang.Boolean> m919a(java.util.Map<java.lang.String, java.lang.String> r11) {
        /*
            r10 = this;
            java.lang.String r0 = "status"
            r1 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
            r4 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
            if (r11 == 0) goto L_0x005c
            int r6 = r11.size()
            if (r6 != 0) goto L_0x0019
            goto L_0x005c
        L_0x0019:
            boolean r6 = r11.containsKey(r0)
            java.lang.String r7 = "[Upload] Headers does not contain %s"
            if (r6 != 0) goto L_0x0029
            java.lang.Object[] r6 = new java.lang.Object[r1]
            r6[r4] = r0
            com.tencent.bugly.proguard.C3749y.m940d(r7, r6)
            goto L_0x0063
        L_0x0029:
            java.lang.String r6 = "Bugly-Version"
            boolean r8 = r11.containsKey(r6)
            if (r8 != 0) goto L_0x0039
            java.lang.Object[] r8 = new java.lang.Object[r1]
            r8[r4] = r6
            com.tencent.bugly.proguard.C3749y.m940d(r7, r8)
            goto L_0x0063
        L_0x0039:
            java.lang.Object r6 = r11.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r7 = "bugly"
            boolean r7 = r6.contains(r7)
            if (r7 != 0) goto L_0x0051
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r7[r4] = r6
            java.lang.String r6 = "[Upload] Bugly version is not valid: %s"
            com.tencent.bugly.proguard.C3749y.m940d(r6, r7)
            goto L_0x0063
        L_0x0051:
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r7[r4] = r6
            java.lang.String r6 = "[Upload] Bugly version from headers is: %s"
            com.tencent.bugly.proguard.C3749y.m939c(r6, r7)
            r6 = 1
            goto L_0x0064
        L_0x005c:
            java.lang.Object[] r6 = new java.lang.Object[r4]
            java.lang.String r7 = "[Upload] Headers is empty."
            com.tencent.bugly.proguard.C3749y.m940d(r7, r6)
        L_0x0063:
            r6 = 0
        L_0x0064:
            java.lang.String r7 = "[Upload] Failed to upload(%d): %s"
            r8 = 2
            if (r6 != 0) goto L_0x00ca
            java.lang.Object[] r0 = new java.lang.Object[r8]
            int r6 = android.os.Process.myPid()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r0[r4] = r6
            int r6 = android.os.Process.myTid()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r0[r1] = r6
            java.lang.String r6 = "[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d)."
            com.tencent.bugly.proguard.C3749y.m939c(r6, r0)
            java.lang.Object[] r0 = new java.lang.Object[r8]
            r0[r4] = r2
            java.lang.String r2 = "[Upload] Failed to upload for no status header."
            r0[r1] = r2
            com.tencent.bugly.proguard.C3749y.m941e(r7, r0)
            if (r11 == 0) goto L_0x00bf
            java.util.Set r11 = r11.entrySet()
            java.util.Iterator r11 = r11.iterator()
        L_0x0099:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x00bf
            java.lang.Object r0 = r11.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object[] r6 = new java.lang.Object[r8]
            java.lang.Object r7 = r0.getKey()
            r6[r4] = r7
            java.lang.Object r0 = r0.getValue()
            r6[r1] = r0
            java.lang.String r0 = "[key]: %s, [value]: %s"
            java.lang.String r0 = java.lang.String.format(r0, r6)
            java.lang.Object[] r6 = new java.lang.Object[r4]
            com.tencent.bugly.proguard.C3749y.m939c(r0, r6)
            goto L_0x0099
        L_0x00bf:
            java.lang.Object[] r11 = new java.lang.Object[r4]
            com.tencent.bugly.proguard.C3749y.m939c(r2, r11)
            android.util.Pair r11 = new android.util.Pair
            r11.<init>(r5, r3)
            return r11
        L_0x00ca:
            r6 = -1
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x0117 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0117 }
            int r6 = java.lang.Integer.parseInt(r11)     // Catch:{ all -> 0x0117 }
            java.lang.String r11 = "[Upload] Status from server is %d (pid=%d | tid=%d)."
            r0 = 3
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0117 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0117 }
            r0[r4] = r9     // Catch:{ all -> 0x0117 }
            int r9 = android.os.Process.myPid()     // Catch:{ all -> 0x0117 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0117 }
            r0[r1] = r9     // Catch:{ all -> 0x0117 }
            int r9 = android.os.Process.myTid()     // Catch:{ all -> 0x0117 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0117 }
            r0[r8] = r9     // Catch:{ all -> 0x0117 }
            com.tencent.bugly.proguard.C3749y.m939c(r11, r0)     // Catch:{ all -> 0x0117 }
            if (r6 == 0) goto L_0x0111
            r11 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "status of server is "
            r0.<init>(r2)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r10.m922a(r11, r4, r1, r0)
            android.util.Pair r11 = new android.util.Pair
            r11.<init>(r5, r5)
            return r11
        L_0x0111:
            android.util.Pair r11 = new android.util.Pair
            r11.<init>(r3, r3)
            return r11
        L_0x0117:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "[Upload] Failed to upload for format of status header is invalid: "
            r11.<init>(r0)
            java.lang.String r0 = java.lang.Integer.toString(r6)
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            java.lang.Object[] r0 = new java.lang.Object[r8]
            r0[r4] = r2
            r0[r1] = r11
            com.tencent.bugly.proguard.C3749y.m941e(r7, r0)
            android.util.Pair r11 = new android.util.Pair
            r11.<init>(r5, r3)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3746w.m919a(java.util.Map):android.util.Pair");
    }

    /* renamed from: a */
    public final void mo24341a(long j) {
        this.f1052p++;
        this.f1053q += j;
    }

    /* renamed from: b */
    public final void mo24342b(long j) {
        this.f1054r += j;
    }

    /* renamed from: a */
    private static String m921a(String str) {
        if (C3695ab.m679a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", new Object[]{str, UUID.randomUUID().toString()});
        } catch (Throwable th) {
            C3749y.m935a(th);
            return str;
        }
    }
}
