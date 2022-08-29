package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.bugly.C3611a;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.o */
/* compiled from: BUGLY */
public final class C3730o {

    /* renamed from: a */
    private static C3730o f973a = null;

    /* renamed from: b */
    private static C3732p f974b = null;

    /* renamed from: c */
    private static boolean f975c = false;

    private C3730o(Context context, List<C3611a> list) {
        f974b = new C3732p(context, list);
    }

    /* renamed from: a */
    public static synchronized C3730o m840a(Context context, List<C3611a> list) {
        C3730o oVar;
        synchronized (C3730o.class) {
            if (f973a == null) {
                f973a = new C3730o(context, list);
            }
            oVar = f973a;
        }
        return oVar;
    }

    /* renamed from: a */
    public static synchronized C3730o m839a() {
        C3730o oVar;
        synchronized (C3730o.class) {
            oVar = f973a;
        }
        return oVar;
    }

    /* renamed from: a */
    public final long mo24292a(String str, ContentValues contentValues, C3729n nVar, boolean z) {
        return m836a(str, contentValues, (C3729n) null);
    }

    /* renamed from: a */
    public final Cursor mo24293a(String str, String[] strArr, String str2, String[] strArr2, C3729n nVar, boolean z) {
        return mo24294a(false, str, strArr, str2, (String[]) null, (String) null, (String) null, (String) null, (String) null, (C3729n) null, true);
    }

    /* renamed from: a */
    public final Cursor mo24294a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, C3729n nVar, boolean z2) {
        if (!z2) {
            C3731a aVar = new C3731a(3, nVar);
            aVar.mo24302a(false, str, strArr, str2, strArr2, (String) null, (String) null, str5, str6);
            C3747x.m926a().mo24344a(aVar);
            return null;
        }
        C3729n nVar2 = nVar;
        return m838a(false, str, strArr, str2, strArr2, (String) null, (String) null, str5, str6, nVar);
    }

    /* renamed from: a */
    public final int mo24291a(String str, String str2, String[] strArr, C3729n nVar, boolean z) {
        return m834a(str, str2, (String[]) null, (C3729n) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        if (r11 != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        java.lang.Long.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        if (r11 != null) goto L_0x0031;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long m836a(java.lang.String r9, android.content.ContentValues r10, com.tencent.bugly.proguard.C3729n r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            r0 = -1
            com.tencent.bugly.proguard.p r2 = f974b     // Catch:{ all -> 0x0037 }
            android.database.sqlite.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch:{ all -> 0x0037 }
            if (r2 == 0) goto L_0x002f
            if (r10 == 0) goto L_0x002f
            java.lang.String r3 = "_id"
            long r2 = r2.replace(r9, r3, r10)     // Catch:{ all -> 0x0037 }
            r4 = 0
            r10 = 0
            r6 = 1
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 < 0) goto L_0x0025
            java.lang.String r4 = "[Database] insert %s success."
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ all -> 0x0037 }
            r5[r10] = r9     // Catch:{ all -> 0x0037 }
            com.tencent.bugly.proguard.C3749y.m939c(r4, r5)     // Catch:{ all -> 0x0037 }
            goto L_0x002e
        L_0x0025:
            java.lang.String r4 = "[Database] replace %s error."
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ all -> 0x0037 }
            r5[r10] = r9     // Catch:{ all -> 0x0037 }
            com.tencent.bugly.proguard.C3749y.m940d(r4, r5)     // Catch:{ all -> 0x0037 }
        L_0x002e:
            r0 = r2
        L_0x002f:
            if (r11 == 0) goto L_0x0044
        L_0x0031:
            java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0035 }
            goto L_0x0044
        L_0x0035:
            r9 = move-exception
            goto L_0x004d
        L_0x0037:
            r9 = move-exception
            boolean r10 = com.tencent.bugly.proguard.C3749y.m935a(r9)     // Catch:{ all -> 0x0046 }
            if (r10 != 0) goto L_0x0041
            r9.printStackTrace()     // Catch:{ all -> 0x0046 }
        L_0x0041:
            if (r11 == 0) goto L_0x0044
            goto L_0x0031
        L_0x0044:
            monitor-exit(r8)
            return r0
        L_0x0046:
            r9 = move-exception
            if (r11 == 0) goto L_0x004c
            java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0035 }
        L_0x004c:
            throw r9     // Catch:{ all -> 0x0035 }
        L_0x004d:
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.m836a(java.lang.String, android.content.ContentValues, com.tencent.bugly.proguard.n):long");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        throw r0;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.database.Cursor m838a(boolean r13, java.lang.String r14, java.lang.String[] r15, java.lang.String r16, java.lang.String[] r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, com.tencent.bugly.proguard.C3729n r22) {
        /*
            r12 = this;
            monitor-enter(r12)
            r1 = 0
            com.tencent.bugly.proguard.p r0 = f974b     // Catch:{ all -> 0x001e }
            android.database.sqlite.SQLiteDatabase r2 = r0.getWritableDatabase()     // Catch:{ all -> 0x001e }
            if (r2 == 0) goto L_0x0028
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r8 = r18
            r9 = r19
            r10 = r20
            r11 = r21
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x001e }
            goto L_0x0028
        L_0x001e:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C3749y.m935a(r0)     // Catch:{ all -> 0x002a }
            if (r2 != 0) goto L_0x0028
            r0.printStackTrace()     // Catch:{ all -> 0x002a }
        L_0x0028:
            monitor-exit(r12)
            return r1
        L_0x002a:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002c }
        L_0x002c:
            r0 = move-exception
            r1 = r0
            monitor-exit(r12)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.m838a(boolean, java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.bugly.proguard.n):android.database.Cursor");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        if (r6 != null) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r6 != null) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        java.lang.Integer.valueOf(r0);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int m834a(java.lang.String r3, java.lang.String r4, java.lang.String[] r5, com.tencent.bugly.proguard.C3729n r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            com.tencent.bugly.proguard.p r1 = f974b     // Catch:{ all -> 0x0016 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x0016 }
            if (r1 == 0) goto L_0x000e
            int r0 = r1.delete(r3, r4, r5)     // Catch:{ all -> 0x0016 }
        L_0x000e:
            if (r6 == 0) goto L_0x0023
        L_0x0010:
            java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0014 }
            goto L_0x0023
        L_0x0014:
            r3 = move-exception
            goto L_0x002c
        L_0x0016:
            r3 = move-exception
            boolean r4 = com.tencent.bugly.proguard.C3749y.m935a(r3)     // Catch:{ all -> 0x0025 }
            if (r4 != 0) goto L_0x0020
            r3.printStackTrace()     // Catch:{ all -> 0x0025 }
        L_0x0020:
            if (r6 == 0) goto L_0x0023
            goto L_0x0010
        L_0x0023:
            monitor-exit(r2)
            return r0
        L_0x0025:
            r3 = move-exception
            if (r6 == 0) goto L_0x002b
            java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0014 }
        L_0x002b:
            throw r3     // Catch:{ all -> 0x0014 }
        L_0x002c:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.m834a(java.lang.String, java.lang.String, java.lang.String[], com.tencent.bugly.proguard.n):int");
    }

    /* renamed from: a */
    public final boolean mo24298a(int i, String str, byte[] bArr, C3729n nVar, boolean z) {
        if (z) {
            return m845a(i, str, bArr, (C3729n) null);
        }
        C3731a aVar = new C3731a(4, (C3729n) null);
        aVar.mo24301a(i, str, bArr);
        C3747x.m926a().mo24344a(aVar);
        return true;
    }

    /* renamed from: a */
    public final Map<String, byte[]> mo24296a(int i, C3729n nVar, boolean z) {
        return m842a(i, (C3729n) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        if (r8 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r8 != null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0019, code lost:
        java.lang.Boolean.valueOf(r0);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m845a(int r5, java.lang.String r6, byte[] r7, com.tencent.bugly.proguard.C3729n r8) {
        /*
            r4 = this;
            r0 = 0
            com.tencent.bugly.proguard.q r1 = new com.tencent.bugly.proguard.q     // Catch:{ all -> 0x001d }
            r1.<init>()     // Catch:{ all -> 0x001d }
            long r2 = (long) r5     // Catch:{ all -> 0x001d }
            r1.f998a = r2     // Catch:{ all -> 0x001d }
            r1.f1003f = r6     // Catch:{ all -> 0x001d }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x001d }
            r1.f1002e = r5     // Catch:{ all -> 0x001d }
            r1.f1004g = r7     // Catch:{ all -> 0x001d }
            boolean r0 = r4.m849b((com.tencent.bugly.proguard.C3733q) r1)     // Catch:{ all -> 0x001d }
            if (r8 == 0) goto L_0x002a
        L_0x0019:
            java.lang.Boolean.valueOf(r0)
            goto L_0x002a
        L_0x001d:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.C3749y.m935a(r5)     // Catch:{ all -> 0x002b }
            if (r6 != 0) goto L_0x0027
            r5.printStackTrace()     // Catch:{ all -> 0x002b }
        L_0x0027:
            if (r8 == 0) goto L_0x002a
            goto L_0x0019
        L_0x002a:
            return r0
        L_0x002b:
            r5 = move-exception
            if (r8 == 0) goto L_0x0031
            java.lang.Boolean.valueOf(r0)
        L_0x0031:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.m845a(int, java.lang.String, byte[], com.tencent.bugly.proguard.n):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, byte[]> m842a(int r3, com.tencent.bugly.proguard.C3729n r4) {
        /*
            r2 = this;
            r4 = 0
            java.util.List r3 = r2.m851c((int) r3)     // Catch:{ all -> 0x002b }
            if (r3 == 0) goto L_0x0035
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x002b }
            r0.<init>()     // Catch:{ all -> 0x002b }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0028 }
        L_0x0010:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0028 }
            if (r4 == 0) goto L_0x0026
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0028 }
            com.tencent.bugly.proguard.q r4 = (com.tencent.bugly.proguard.C3733q) r4     // Catch:{ all -> 0x0028 }
            byte[] r1 = r4.f1004g     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0010
            java.lang.String r4 = r4.f1003f     // Catch:{ all -> 0x0028 }
            r0.put(r4, r1)     // Catch:{ all -> 0x0028 }
            goto L_0x0010
        L_0x0026:
            r4 = r0
            goto L_0x0035
        L_0x0028:
            r3 = move-exception
            r4 = r0
            goto L_0x002c
        L_0x002b:
            r3 = move-exception
        L_0x002c:
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r3)     // Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x0035
            r3.printStackTrace()     // Catch:{ all -> 0x0036 }
        L_0x0035:
            return r4
        L_0x0036:
            r3 = move-exception
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.m842a(int, com.tencent.bugly.proguard.n):java.util.Map");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return false;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean mo24299a(com.tencent.bugly.proguard.C3733q r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            if (r8 != 0) goto L_0x0006
            monitor-exit(r7)
            return r0
        L_0x0006:
            com.tencent.bugly.proguard.p r1 = f974b     // Catch:{ all -> 0x0036 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0034
            android.content.ContentValues r2 = m850c((com.tencent.bugly.proguard.C3733q) r8)     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x0034
            java.lang.String r3 = "t_lr"
            java.lang.String r4 = "_id"
            long r1 = r1.replace(r3, r4, r2)     // Catch:{ all -> 0x0036 }
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0032
            java.lang.String r3 = "[Database] insert %s success."
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0036 }
            java.lang.String r6 = "t_lr"
            r5[r0] = r6     // Catch:{ all -> 0x0036 }
            com.tencent.bugly.proguard.C3749y.m939c(r3, r5)     // Catch:{ all -> 0x0036 }
            r8.f998a = r1     // Catch:{ all -> 0x0036 }
            monitor-exit(r7)
            return r4
        L_0x0032:
            monitor-exit(r7)
            return r0
        L_0x0034:
            monitor-exit(r7)
            return r0
        L_0x0036:
            r8 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r8)     // Catch:{ all -> 0x0042 }
            if (r1 != 0) goto L_0x0040
            r8.printStackTrace()     // Catch:{ all -> 0x0042 }
        L_0x0040:
            monitor-exit(r7)
            return r0
        L_0x0042:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.mo24299a(com.tencent.bugly.proguard.q):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return false;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean m849b(com.tencent.bugly.proguard.C3733q r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            if (r8 != 0) goto L_0x0006
            monitor-exit(r7)
            return r0
        L_0x0006:
            com.tencent.bugly.proguard.p r1 = f974b     // Catch:{ all -> 0x0036 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0034
            android.content.ContentValues r2 = m852d(r8)     // Catch:{ all -> 0x0036 }
            if (r2 == 0) goto L_0x0034
            java.lang.String r3 = "t_pf"
            java.lang.String r4 = "_id"
            long r1 = r1.replace(r3, r4, r2)     // Catch:{ all -> 0x0036 }
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0032
            java.lang.String r3 = "[Database] insert %s success."
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0036 }
            java.lang.String r6 = "t_pf"
            r5[r0] = r6     // Catch:{ all -> 0x0036 }
            com.tencent.bugly.proguard.C3749y.m939c(r3, r5)     // Catch:{ all -> 0x0036 }
            r8.f998a = r1     // Catch:{ all -> 0x0036 }
            monitor-exit(r7)
            return r4
        L_0x0032:
            monitor-exit(r7)
            return r0
        L_0x0034:
            monitor-exit(r7)
            return r0
        L_0x0036:
            r8 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r8)     // Catch:{ all -> 0x0042 }
            if (r1 != 0) goto L_0x0040
            r8.printStackTrace()     // Catch:{ all -> 0x0042 }
        L_0x0040:
            monitor-exit(r7)
            return r0
        L_0x0042:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.m849b(com.tencent.bugly.proguard.q):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009f, code lost:
        return r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00aa A[Catch:{ all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00af A[SYNTHETIC, Splitter:B:47:0x00af] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<com.tencent.bugly.proguard.C3733q> mo24295a(int r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            com.tencent.bugly.proguard.p r0 = f974b     // Catch:{ all -> 0x00bc }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ all -> 0x00bc }
            r9 = 0
            if (r0 == 0) goto L_0x00ba
            if (r12 < 0) goto L_0x0020
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x001c }
            java.lang.String r2 = "_tp = "
            r1.<init>(r2)     // Catch:{ all -> 0x001c }
            r1.append(r12)     // Catch:{ all -> 0x001c }
            java.lang.String r12 = r1.toString()     // Catch:{ all -> 0x001c }
            r4 = r12
            goto L_0x0021
        L_0x001c:
            r12 = move-exception
            r0 = r9
            goto L_0x00a4
        L_0x0020:
            r4 = r9
        L_0x0021:
            java.lang.String r2 = "t_lr"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r0
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x001c }
            if (r12 != 0) goto L_0x0036
            if (r12 == 0) goto L_0x0034
            r12.close()     // Catch:{ all -> 0x00bc }
        L_0x0034:
            monitor-exit(r11)
            return r9
        L_0x0036:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a0 }
            r1.<init>()     // Catch:{ all -> 0x00a0 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00a0 }
            r2.<init>()     // Catch:{ all -> 0x00a0 }
        L_0x0040:
            boolean r3 = r12.moveToNext()     // Catch:{ all -> 0x00a0 }
            r4 = 0
            if (r3 == 0) goto L_0x0071
            com.tencent.bugly.proguard.q r3 = m841a((android.database.Cursor) r12)     // Catch:{ all -> 0x00a0 }
            if (r3 == 0) goto L_0x0051
            r2.add(r3)     // Catch:{ all -> 0x00a0 }
            goto L_0x0040
        L_0x0051:
            java.lang.String r3 = "_id"
            int r3 = r12.getColumnIndex(r3)     // Catch:{ all -> 0x0069 }
            long r5 = r12.getLong(r3)     // Catch:{ all -> 0x0069 }
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch:{ all -> 0x0069 }
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch:{ all -> 0x0069 }
            r1.append(r5)     // Catch:{ all -> 0x0069 }
            goto L_0x0040
        L_0x0069:
            java.lang.String r3 = "[Database] unknown id."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00a0 }
            com.tencent.bugly.proguard.C3749y.m940d(r3, r4)     // Catch:{ all -> 0x00a0 }
            goto L_0x0040
        L_0x0071:
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00a0 }
            int r3 = r1.length()     // Catch:{ all -> 0x00a0 }
            if (r3 <= 0) goto L_0x0099
            r3 = 4
            java.lang.String r1 = r1.substring(r3)     // Catch:{ all -> 0x00a0 }
            java.lang.String r3 = "t_lr"
            int r0 = r0.delete(r3, r1, r9)     // Catch:{ all -> 0x00a0 }
            java.lang.String r1 = "[Database] deleted %s illegal data %d"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00a0 }
            java.lang.String r5 = "t_lr"
            r3[r4] = r5     // Catch:{ all -> 0x00a0 }
            r4 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00a0 }
            r3[r4] = r0     // Catch:{ all -> 0x00a0 }
            com.tencent.bugly.proguard.C3749y.m940d(r1, r3)     // Catch:{ all -> 0x00a0 }
        L_0x0099:
            if (r12 == 0) goto L_0x009e
            r12.close()     // Catch:{ all -> 0x00bc }
        L_0x009e:
            monitor-exit(r11)
            return r2
        L_0x00a0:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
        L_0x00a4:
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r12)     // Catch:{ all -> 0x00b3 }
            if (r1 != 0) goto L_0x00ad
            r12.printStackTrace()     // Catch:{ all -> 0x00b3 }
        L_0x00ad:
            if (r0 == 0) goto L_0x00ba
            r0.close()     // Catch:{ all -> 0x00bc }
            goto L_0x00ba
        L_0x00b3:
            r12 = move-exception
            if (r0 == 0) goto L_0x00b9
            r0.close()     // Catch:{ all -> 0x00bc }
        L_0x00b9:
            throw r12     // Catch:{ all -> 0x00bc }
        L_0x00ba:
            monitor-exit(r11)
            return r9
        L_0x00bc:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.mo24295a(int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007a, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo24297a(java.util.List<com.tencent.bugly.proguard.C3733q> r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x0079
            int r0 = r5.size()     // Catch:{ all -> 0x0076 }
            if (r0 != 0) goto L_0x000a
            goto L_0x0079
        L_0x000a:
            com.tencent.bugly.proguard.p r0 = f974b     // Catch:{ all -> 0x0076 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ all -> 0x0076 }
            if (r0 == 0) goto L_0x0074
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0076 }
            r1.<init>()     // Catch:{ all -> 0x0076 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0076 }
        L_0x001b:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x0076 }
            if (r2 == 0) goto L_0x0037
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x0076 }
            com.tencent.bugly.proguard.q r2 = (com.tencent.bugly.proguard.C3733q) r2     // Catch:{ all -> 0x0076 }
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch:{ all -> 0x0076 }
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch:{ all -> 0x0076 }
            long r2 = r2.f998a     // Catch:{ all -> 0x0076 }
            r1.append(r2)     // Catch:{ all -> 0x0076 }
            goto L_0x001b
        L_0x0037:
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0076 }
            int r2 = r5.length()     // Catch:{ all -> 0x0076 }
            if (r2 <= 0) goto L_0x0046
            r2 = 4
            java.lang.String r5 = r5.substring(r2)     // Catch:{ all -> 0x0076 }
        L_0x0046:
            r2 = 0
            r1.setLength(r2)     // Catch:{ all -> 0x0076 }
            java.lang.String r1 = "t_lr"
            r3 = 0
            int r5 = r0.delete(r1, r5, r3)     // Catch:{ all -> 0x0066 }
            java.lang.String r0 = "[Database] deleted %s data %d"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0066 }
            java.lang.String r3 = "t_lr"
            r1[r2] = r3     // Catch:{ all -> 0x0066 }
            r2 = 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0066 }
            r1[r2] = r5     // Catch:{ all -> 0x0066 }
            com.tencent.bugly.proguard.C3749y.m939c(r0, r1)     // Catch:{ all -> 0x0066 }
            monitor-exit(r4)
            return
        L_0x0066:
            r5 = move-exception
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r5)     // Catch:{ all -> 0x0072 }
            if (r0 != 0) goto L_0x0070
            r5.printStackTrace()     // Catch:{ all -> 0x0072 }
        L_0x0070:
            monitor-exit(r4)
            return
        L_0x0072:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0076 }
        L_0x0074:
            monitor-exit(r4)
            return
        L_0x0076:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x0079:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.mo24297a(java.util.List):void");
    }

    /* renamed from: b */
    public final synchronized void mo24300b(int i) {
        String str;
        SQLiteDatabase writableDatabase = f974b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (!C3749y.m935a(th)) {
                        th.printStackTrace();
                    }
                    return;
                }
            } else {
                str = null;
            }
            C3749y.m939c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, (String[]) null)));
        }
    }

    /* renamed from: c */
    private static ContentValues m850c(C3733q qVar) {
        if (qVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (qVar.f998a > 0) {
                contentValues.put("_id", Long.valueOf(qVar.f998a));
            }
            contentValues.put("_tp", Integer.valueOf(qVar.f999b));
            contentValues.put("_pc", qVar.f1000c);
            contentValues.put("_th", qVar.f1001d);
            contentValues.put("_tm", Long.valueOf(qVar.f1002e));
            if (qVar.f1004g != null) {
                contentValues.put("_dt", qVar.f1004g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static C3733q m841a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C3733q qVar = new C3733q();
            qVar.f998a = cursor.getLong(cursor.getColumnIndex("_id"));
            qVar.f999b = cursor.getInt(cursor.getColumnIndex("_tp"));
            qVar.f1000c = cursor.getString(cursor.getColumnIndex("_pc"));
            qVar.f1001d = cursor.getString(cursor.getColumnIndex("_th"));
            qVar.f1002e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.f1004g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a0, code lost:
        return r4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ab A[Catch:{ all -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b0 A[SYNTHETIC, Splitter:B:42:0x00b0] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.List<com.tencent.bugly.proguard.C3733q> m851c(int r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 0
            com.tencent.bugly.proguard.p r1 = f974b     // Catch:{ all -> 0x00a3 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x00a3 }
            if (r1 == 0) goto L_0x00b3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = "_id = "
            r2.<init>(r3)     // Catch:{ all -> 0x00a3 }
            r2.append(r12)     // Catch:{ all -> 0x00a3 }
            java.lang.String r10 = r2.toString()     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = "t_pf"
            r4 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r1
            r5 = r10
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00a3 }
            if (r2 != 0) goto L_0x002e
            if (r2 == 0) goto L_0x002c
            r2.close()     // Catch:{ all -> 0x00bc }
        L_0x002c:
            monitor-exit(r11)
            return r0
        L_0x002e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r3.<init>()     // Catch:{ all -> 0x00a1 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x00a1 }
            r4.<init>()     // Catch:{ all -> 0x00a1 }
        L_0x0038:
            boolean r5 = r2.moveToNext()     // Catch:{ all -> 0x00a1 }
            r6 = 0
            if (r5 == 0) goto L_0x0069
            com.tencent.bugly.proguard.q r5 = m848b((android.database.Cursor) r2)     // Catch:{ all -> 0x00a1 }
            if (r5 == 0) goto L_0x0049
            r4.add(r5)     // Catch:{ all -> 0x00a1 }
            goto L_0x0038
        L_0x0049:
            java.lang.String r5 = "_tp"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ all -> 0x0061 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ all -> 0x0061 }
            java.lang.String r7 = " or _tp"
            r3.append(r7)     // Catch:{ all -> 0x0061 }
            java.lang.String r7 = " = "
            r3.append(r7)     // Catch:{ all -> 0x0061 }
            r3.append(r5)     // Catch:{ all -> 0x0061 }
            goto L_0x0038
        L_0x0061:
            java.lang.String r5 = "[Database] unknown id."
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x00a1 }
            com.tencent.bugly.proguard.C3749y.m940d(r5, r6)     // Catch:{ all -> 0x00a1 }
            goto L_0x0038
        L_0x0069:
            int r5 = r3.length()     // Catch:{ all -> 0x00a1 }
            if (r5 <= 0) goto L_0x009a
            java.lang.String r5 = " and _id"
            r3.append(r5)     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = " = "
            r3.append(r5)     // Catch:{ all -> 0x00a1 }
            r3.append(r12)     // Catch:{ all -> 0x00a1 }
            r12 = 4
            java.lang.String r12 = r10.substring(r12)     // Catch:{ all -> 0x00a1 }
            java.lang.String r3 = "t_pf"
            int r12 = r1.delete(r3, r12, r0)     // Catch:{ all -> 0x00a1 }
            java.lang.String r1 = "[Database] deleted %s illegal data %d."
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = "t_pf"
            r3[r6] = r5     // Catch:{ all -> 0x00a1 }
            r5 = 1
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x00a1 }
            r3[r5] = r12     // Catch:{ all -> 0x00a1 }
            com.tencent.bugly.proguard.C3749y.m940d(r1, r3)     // Catch:{ all -> 0x00a1 }
        L_0x009a:
            if (r2 == 0) goto L_0x009f
            r2.close()     // Catch:{ all -> 0x00bc }
        L_0x009f:
            monitor-exit(r11)
            return r4
        L_0x00a1:
            r12 = move-exception
            goto L_0x00a5
        L_0x00a3:
            r12 = move-exception
            r2 = r0
        L_0x00a5:
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r12)     // Catch:{ all -> 0x00b5 }
            if (r1 != 0) goto L_0x00ae
            r12.printStackTrace()     // Catch:{ all -> 0x00b5 }
        L_0x00ae:
            if (r2 == 0) goto L_0x00b3
            r2.close()     // Catch:{ all -> 0x00bc }
        L_0x00b3:
            monitor-exit(r11)
            return r0
        L_0x00b5:
            r12 = move-exception
            if (r2 == 0) goto L_0x00bb
            r2.close()     // Catch:{ all -> 0x00bc }
        L_0x00bb:
            throw r12     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.m851c(int):java.util.List");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005c, code lost:
        if (r7 != null) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        java.lang.Boolean.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        if (r7 != null) goto L_0x005e;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean m844a(int r5, java.lang.String r6, com.tencent.bugly.proguard.C3729n r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            com.tencent.bugly.proguard.p r1 = f974b     // Catch:{ all -> 0x0064 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getWritableDatabase()     // Catch:{ all -> 0x0064 }
            if (r1 == 0) goto L_0x005c
            boolean r2 = com.tencent.bugly.proguard.C3695ab.m679a((java.lang.String) r6)     // Catch:{ all -> 0x0064 }
            if (r2 == 0) goto L_0x001f
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = "_id = "
            r6.<init>(r2)     // Catch:{ all -> 0x0064 }
            r6.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0064 }
            goto L_0x003f
        L_0x001f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            java.lang.String r3 = "_id = "
            r2.<init>(r3)     // Catch:{ all -> 0x0064 }
            r2.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = " and _tp"
            r2.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = " = \""
            r2.append(r5)     // Catch:{ all -> 0x0064 }
            r2.append(r6)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = "\""
            r2.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0064 }
        L_0x003f:
            java.lang.String r6 = "t_pf"
            r2 = 0
            int r5 = r1.delete(r6, r5, r2)     // Catch:{ all -> 0x0064 }
            java.lang.String r6 = "[Database] deleted %s data %d"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = "t_pf"
            r1[r0] = r2     // Catch:{ all -> 0x0064 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0064 }
            r3 = 1
            r1[r3] = r2     // Catch:{ all -> 0x0064 }
            com.tencent.bugly.proguard.C3749y.m939c(r6, r1)     // Catch:{ all -> 0x0064 }
            if (r5 <= 0) goto L_0x005c
            r0 = 1
        L_0x005c:
            if (r7 == 0) goto L_0x0071
        L_0x005e:
            java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0062 }
            goto L_0x0071
        L_0x0062:
            r5 = move-exception
            goto L_0x007a
        L_0x0064:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.C3749y.m935a(r5)     // Catch:{ all -> 0x0073 }
            if (r6 != 0) goto L_0x006e
            r5.printStackTrace()     // Catch:{ all -> 0x0073 }
        L_0x006e:
            if (r7 == 0) goto L_0x0071
            goto L_0x005e
        L_0x0071:
            monitor-exit(r4)
            return r0
        L_0x0073:
            r5 = move-exception
            if (r7 == 0) goto L_0x0079
            java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0062 }
        L_0x0079:
            throw r5     // Catch:{ all -> 0x0062 }
        L_0x007a:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3730o.m844a(int, java.lang.String, com.tencent.bugly.proguard.n):boolean");
    }

    /* renamed from: d */
    private static ContentValues m852d(C3733q qVar) {
        if (qVar != null && !C3695ab.m679a(qVar.f1003f)) {
            try {
                ContentValues contentValues = new ContentValues();
                if (qVar.f998a > 0) {
                    contentValues.put("_id", Long.valueOf(qVar.f998a));
                }
                contentValues.put("_tp", qVar.f1003f);
                contentValues.put("_tm", Long.valueOf(qVar.f1002e));
                if (qVar.f1004g != null) {
                    contentValues.put("_dt", qVar.f1004g);
                }
                return contentValues;
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: b */
    private static C3733q m848b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C3733q qVar = new C3733q();
            qVar.f998a = cursor.getLong(cursor.getColumnIndex("_id"));
            qVar.f1002e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.f1003f = cursor.getString(cursor.getColumnIndex("_tp"));
            qVar.f1004g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: com.tencent.bugly.proguard.o$a */
    /* compiled from: BUGLY */
    class C3731a extends Thread {

        /* renamed from: a */
        private int f976a;

        /* renamed from: b */
        private C3729n f977b;

        /* renamed from: c */
        private String f978c;

        /* renamed from: d */
        private ContentValues f979d;

        /* renamed from: e */
        private boolean f980e;

        /* renamed from: f */
        private String[] f981f;

        /* renamed from: g */
        private String f982g;

        /* renamed from: h */
        private String[] f983h;

        /* renamed from: i */
        private String f984i;

        /* renamed from: j */
        private String f985j;

        /* renamed from: k */
        private String f986k;

        /* renamed from: l */
        private String f987l;

        /* renamed from: m */
        private String f988m;

        /* renamed from: n */
        private String[] f989n;

        /* renamed from: o */
        private int f990o;

        /* renamed from: p */
        private String f991p;

        /* renamed from: q */
        private byte[] f992q;

        public C3731a(int i, C3729n nVar) {
            this.f976a = i;
            this.f977b = nVar;
        }

        /* renamed from: a */
        public final void mo24302a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.f980e = z;
            this.f978c = str;
            this.f981f = strArr;
            this.f982g = str2;
            this.f983h = strArr2;
            this.f984i = str3;
            this.f985j = str4;
            this.f986k = str5;
            this.f987l = str6;
        }

        /* renamed from: a */
        public final void mo24301a(int i, String str, byte[] bArr) {
            this.f990o = i;
            this.f991p = str;
            this.f992q = bArr;
        }

        public final void run() {
            switch (this.f976a) {
                case 1:
                    long unused = C3730o.this.m836a(this.f978c, this.f979d, this.f977b);
                    return;
                case 2:
                    int unused2 = C3730o.this.m834a(this.f978c, this.f988m, this.f989n, this.f977b);
                    return;
                case 3:
                    Cursor a = C3730o.this.m838a(this.f980e, this.f978c, this.f981f, this.f982g, this.f983h, this.f984i, this.f985j, this.f986k, this.f987l, this.f977b);
                    if (a != null) {
                        a.close();
                        return;
                    }
                    return;
                case 4:
                    boolean unused3 = C3730o.this.m845a(this.f990o, this.f991p, this.f992q, this.f977b);
                    return;
                case 5:
                    Map unused4 = C3730o.this.m842a(this.f990o, this.f977b);
                    return;
                case 6:
                    boolean unused5 = C3730o.this.m844a(this.f990o, this.f991p, this.f977b);
                    return;
                default:
                    return;
            }
        }
    }
}
