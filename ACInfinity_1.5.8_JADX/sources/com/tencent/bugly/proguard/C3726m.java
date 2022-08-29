package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.C3626a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.m */
/* compiled from: BUGLY */
public final class C3726m {

    /* renamed from: a */
    public static final long f962a = System.currentTimeMillis();

    /* renamed from: b */
    private static C3726m f963b;

    /* renamed from: c */
    private Context f964c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public String f965d = C3626a.m339b().f496d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Map<Integer, Map<String, C3725l>> f966e = new HashMap();
    /* access modifiers changed from: private */

    /* renamed from: f */
    public SharedPreferences f967f;

    /* renamed from: a */
    static /* synthetic */ boolean m821a(C3725l lVar, C3725l lVar2) {
        return lVar.f961g == lVar2.f961g && lVar.f956b != null && lVar.f956b.equalsIgnoreCase(lVar2.f956b);
    }

    /* renamed from: b */
    static /* synthetic */ boolean m824b(C3725l lVar, C3725l lVar2) {
        if (lVar.f959e == null || lVar.f959e.equalsIgnoreCase(lVar2.f959e)) {
            return (lVar.f960f != null && !lVar.f960f.equalsIgnoreCase(lVar2.f960f)) || lVar.f958d <= 0;
        }
        return true;
    }

    private C3726m(Context context) {
        this.f964c = context;
        this.f967f = context.getSharedPreferences("crashrecord", 0);
    }

    /* renamed from: a */
    public static synchronized C3726m m816a(Context context) {
        C3726m mVar;
        synchronized (C3726m.class) {
            if (f963b == null) {
                f963b = new C3726m(context);
            }
            mVar = f963b;
        }
        return mVar;
    }

    /* renamed from: a */
    public static synchronized C3726m m815a() {
        C3726m mVar;
        synchronized (C3726m.class) {
            mVar = f963b;
        }
        return mVar;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0077, code lost:
        return true;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean m823b(int r13) {
        /*
            r12 = this;
            monitor-enter(r12)
            r0 = 0
            java.util.List r1 = r12.m827c((int) r13)     // Catch:{ Exception -> 0x0082 }
            if (r1 != 0) goto L_0x000a
            monitor-exit(r12)
            return r0
        L_0x000a:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0082 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x0082 }
            r4.<init>()     // Catch:{ Exception -> 0x0082 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x0082 }
            r5.<init>()     // Catch:{ Exception -> 0x0082 }
            java.util.Iterator r6 = r1.iterator()     // Catch:{ Exception -> 0x0082 }
        L_0x001c:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x0082 }
            r8 = 86400000(0x5265c00, double:4.2687272E-316)
            if (r7 == 0) goto L_0x004b
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x0082 }
            com.tencent.bugly.proguard.l r7 = (com.tencent.bugly.proguard.C3725l) r7     // Catch:{ Exception -> 0x0082 }
            java.lang.String r10 = r7.f956b     // Catch:{ Exception -> 0x0082 }
            if (r10 == 0) goto L_0x0040
            java.lang.String r10 = r7.f956b     // Catch:{ Exception -> 0x0082 }
            java.lang.String r11 = r12.f965d     // Catch:{ Exception -> 0x0082 }
            boolean r10 = r10.equalsIgnoreCase(r11)     // Catch:{ Exception -> 0x0082 }
            if (r10 == 0) goto L_0x0040
            int r10 = r7.f958d     // Catch:{ Exception -> 0x0082 }
            if (r10 <= 0) goto L_0x0040
            r4.add(r7)     // Catch:{ Exception -> 0x0082 }
        L_0x0040:
            long r10 = r7.f957c     // Catch:{ Exception -> 0x0082 }
            long r10 = r10 + r8
            int r8 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r8 >= 0) goto L_0x001c
            r5.add(r7)     // Catch:{ Exception -> 0x0082 }
            goto L_0x001c
        L_0x004b:
            java.util.Collections.sort(r4)     // Catch:{ Exception -> 0x0082 }
            int r6 = r4.size()     // Catch:{ Exception -> 0x0082 }
            r7 = 2
            if (r6 < r7) goto L_0x0078
            int r5 = r4.size()     // Catch:{ Exception -> 0x0082 }
            r6 = 1
            if (r5 <= 0) goto L_0x0076
            int r5 = r4.size()     // Catch:{ Exception -> 0x0082 }
            int r5 = r5 - r6
            java.lang.Object r4 = r4.get(r5)     // Catch:{ Exception -> 0x0082 }
            com.tencent.bugly.proguard.l r4 = (com.tencent.bugly.proguard.C3725l) r4     // Catch:{ Exception -> 0x0082 }
            long r4 = r4.f957c     // Catch:{ Exception -> 0x0082 }
            long r4 = r4 + r8
            int r7 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x0076
            r1.clear()     // Catch:{ Exception -> 0x0082 }
            r12.m819a((int) r13, r1)     // Catch:{ Exception -> 0x0082 }
            monitor-exit(r12)
            return r0
        L_0x0076:
            monitor-exit(r12)
            return r6
        L_0x0078:
            r1.removeAll(r5)     // Catch:{ Exception -> 0x0082 }
            r12.m819a((int) r13, r1)     // Catch:{ Exception -> 0x0082 }
            monitor-exit(r12)
            return r0
        L_0x0080:
            r13 = move-exception
            goto L_0x008b
        L_0x0082:
            java.lang.String r13 = "isFrequentCrash failed"
            java.lang.Object[] r1 = new java.lang.Object[r0]     // Catch:{ all -> 0x0080 }
            com.tencent.bugly.proguard.C3749y.m941e(r13, r1)     // Catch:{ all -> 0x0080 }
            monitor-exit(r12)
            return r0
        L_0x008b:
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3726m.m823b(int):boolean");
    }

    /* renamed from: a */
    public final void mo24284a(int i, final int i2) {
        C3747x.m926a().mo24344a(new Runnable(1004) {
            public final void run() {
                C3725l lVar;
                try {
                    if (!TextUtils.isEmpty(C3726m.this.f965d)) {
                        List<C3725l> a = C3726m.this.m827c(1004);
                        if (a == null) {
                            a = new ArrayList<>();
                        }
                        if (C3726m.this.f966e.get(Integer.valueOf(1004)) == null) {
                            C3726m.this.f966e.put(Integer.valueOf(1004), new HashMap());
                        }
                        if (((Map) C3726m.this.f966e.get(Integer.valueOf(1004))).get(C3726m.this.f965d) == null) {
                            lVar = new C3725l();
                            lVar.f955a = (long) 1004;
                            lVar.f961g = C3726m.f962a;
                            lVar.f956b = C3726m.this.f965d;
                            lVar.f960f = C3626a.m339b().f501i;
                            lVar.f959e = C3626a.m339b().f498f;
                            lVar.f957c = System.currentTimeMillis();
                            lVar.f958d = i2;
                            ((Map) C3726m.this.f966e.get(Integer.valueOf(1004))).put(C3726m.this.f965d, lVar);
                        } else {
                            lVar = (C3725l) ((Map) C3726m.this.f966e.get(Integer.valueOf(1004))).get(C3726m.this.f965d);
                            lVar.f958d = i2;
                        }
                        ArrayList arrayList = new ArrayList();
                        boolean z = false;
                        for (C3725l lVar2 : a) {
                            if (C3726m.m821a(lVar2, lVar)) {
                                z = true;
                                lVar2.f958d = lVar.f958d;
                            }
                            if (C3726m.m824b(lVar2, lVar)) {
                                arrayList.add(lVar2);
                            }
                        }
                        a.removeAll(arrayList);
                        if (!z) {
                            a.add(lVar);
                        }
                        C3726m.this.m819a(1004, a);
                    }
                } catch (Exception unused) {
                    C3749y.m941e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        if (r6 != null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0052, code lost:
        if (r6 != null) goto L_0x0046;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0058 A[SYNTHETIC, Splitter:B:35:0x0058] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized <T extends java.util.List<?>> T m827c(int r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x005e }
            android.content.Context r3 = r5.f964c     // Catch:{ Exception -> 0x005e }
            java.lang.String r4 = "crashrecord"
            java.io.File r3 = r3.getDir(r4, r1)     // Catch:{ Exception -> 0x005e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005e }
            r4.<init>()     // Catch:{ Exception -> 0x005e }
            r4.append(r6)     // Catch:{ Exception -> 0x005e }
            java.lang.String r6 = r4.toString()     // Catch:{ Exception -> 0x005e }
            r2.<init>(r3, r6)     // Catch:{ Exception -> 0x005e }
            boolean r6 = r2.exists()     // Catch:{ Exception -> 0x005e }
            if (r6 != 0) goto L_0x0024
            monitor-exit(r5)
            return r0
        L_0x0024:
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x004a, ClassNotFoundException -> 0x003c, all -> 0x0039 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x004a, ClassNotFoundException -> 0x003c, all -> 0x0039 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x004a, ClassNotFoundException -> 0x003c, all -> 0x0039 }
            r6.<init>(r3)     // Catch:{ IOException -> 0x004a, ClassNotFoundException -> 0x003c, all -> 0x0039 }
            java.lang.Object r2 = r6.readObject()     // Catch:{ IOException -> 0x004b, ClassNotFoundException -> 0x003d }
            java.util.List r2 = (java.util.List) r2     // Catch:{ IOException -> 0x004b, ClassNotFoundException -> 0x003d }
            r6.close()     // Catch:{ Exception -> 0x005e }
            monitor-exit(r5)
            return r2
        L_0x0039:
            r2 = move-exception
            r6 = r0
            goto L_0x0056
        L_0x003c:
            r6 = r0
        L_0x003d:
            java.lang.String r2 = "get object error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ all -> 0x0055 }
            com.tencent.bugly.proguard.C3749y.m934a(r2, r3)     // Catch:{ all -> 0x0055 }
            if (r6 == 0) goto L_0x0065
        L_0x0046:
            r6.close()     // Catch:{ Exception -> 0x005e }
            goto L_0x0065
        L_0x004a:
            r6 = r0
        L_0x004b:
            java.lang.String r2 = "open record file error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ all -> 0x0055 }
            com.tencent.bugly.proguard.C3749y.m934a(r2, r3)     // Catch:{ all -> 0x0055 }
            if (r6 == 0) goto L_0x0065
            goto L_0x0046
        L_0x0055:
            r2 = move-exception
        L_0x0056:
            if (r6 == 0) goto L_0x005b
            r6.close()     // Catch:{ Exception -> 0x005e }
        L_0x005b:
            throw r2     // Catch:{ Exception -> 0x005e }
        L_0x005c:
            r6 = move-exception
            goto L_0x0067
        L_0x005e:
            java.lang.String r6 = "readCrashRecord error"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x005c }
            com.tencent.bugly.proguard.C3749y.m941e(r6, r1)     // Catch:{ all -> 0x005c }
        L_0x0065:
            monitor-exit(r5)
            return r0
        L_0x0067:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3726m.m827c(int):java.util.List");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046 A[SYNTHETIC, Splitter:B:22:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004a A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004f A[SYNTHETIC, Splitter:B:28:0x004f] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized <T extends java.util.List<?>> void m819a(int r5, T r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r6 != 0) goto L_0x0005
            monitor-exit(r4)
            return
        L_0x0005:
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0055 }
            android.content.Context r2 = r4.f964c     // Catch:{ Exception -> 0x0055 }
            java.lang.String r3 = "crashrecord"
            java.io.File r2 = r2.getDir(r3, r0)     // Catch:{ Exception -> 0x0055 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0055 }
            r3.<init>()     // Catch:{ Exception -> 0x0055 }
            r3.append(r5)     // Catch:{ Exception -> 0x0055 }
            java.lang.String r5 = r3.toString()     // Catch:{ Exception -> 0x0055 }
            r1.<init>(r2, r5)     // Catch:{ Exception -> 0x0055 }
            r5 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0037, all -> 0x0033 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0037, all -> 0x0033 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x0037, all -> 0x0033 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0037, all -> 0x0033 }
            r2.writeObject(r6)     // Catch:{ IOException -> 0x0031 }
            r2.close()     // Catch:{ Exception -> 0x0055 }
            goto L_0x005c
        L_0x0031:
            r5 = move-exception
            goto L_0x003a
        L_0x0033:
            r6 = move-exception
            r2 = r5
            r5 = r6
            goto L_0x004d
        L_0x0037:
            r6 = move-exception
            r2 = r5
            r5 = r6
        L_0x003a:
            r5.printStackTrace()     // Catch:{ all -> 0x004c }
            java.lang.String r5 = "open record file error"
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch:{ all -> 0x004c }
            com.tencent.bugly.proguard.C3749y.m934a(r5, r6)     // Catch:{ all -> 0x004c }
            if (r2 == 0) goto L_0x004a
            r2.close()     // Catch:{ Exception -> 0x0055 }
            goto L_0x005c
        L_0x004a:
            monitor-exit(r4)
            return
        L_0x004c:
            r5 = move-exception
        L_0x004d:
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ Exception -> 0x0055 }
        L_0x0052:
            throw r5     // Catch:{ Exception -> 0x0055 }
        L_0x0053:
            r5 = move-exception
            goto L_0x005e
        L_0x0055:
            java.lang.String r5 = "writeCrashRecord error"
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch:{ all -> 0x0053 }
            com.tencent.bugly.proguard.C3749y.m941e(r5, r6)     // Catch:{ all -> 0x0053 }
        L_0x005c:
            monitor-exit(r4)
            return
        L_0x005e:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3726m.m819a(int, java.util.List):void");
    }

    /* renamed from: a */
    public final synchronized boolean mo24285a(final int i) {
        boolean z;
        z = true;
        try {
            SharedPreferences sharedPreferences = this.f967f;
            z = sharedPreferences.getBoolean(i + "_" + this.f965d, true);
            C3747x.m926a().mo24344a(new Runnable() {
                public final void run() {
                    boolean b = C3726m.this.m823b(i);
                    SharedPreferences.Editor edit = C3726m.this.f967f.edit();
                    edit.putBoolean(i + "_" + C3726m.this.f965d, !b).commit();
                }
            });
        } catch (Exception unused) {
            C3749y.m941e("canInit error", new Object[0]);
            return z;
        }
        return z;
    }
}
