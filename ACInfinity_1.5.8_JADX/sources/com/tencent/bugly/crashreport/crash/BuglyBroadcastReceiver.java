package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3749y;

/* compiled from: BUGLY */
public class BuglyBroadcastReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static BuglyBroadcastReceiver f553d;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public IntentFilter f554a = new IntentFilter();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Context f555b;

    /* renamed from: c */
    private String f556c;

    /* renamed from: e */
    private boolean f557e = true;

    public static synchronized BuglyBroadcastReceiver getInstance() {
        BuglyBroadcastReceiver buglyBroadcastReceiver;
        synchronized (BuglyBroadcastReceiver.class) {
            if (f553d == null) {
                f553d = new BuglyBroadcastReceiver();
            }
            buglyBroadcastReceiver = f553d;
        }
        return buglyBroadcastReceiver;
    }

    public synchronized void addFilter(String str) {
        if (!this.f554a.hasAction(str)) {
            this.f554a.addAction(str);
        }
        C3749y.m939c("add action %s", str);
    }

    public synchronized void register(Context context) {
        this.f555b = context;
        C3695ab.m678a((Runnable) new Runnable() {
            public final void run() {
                try {
                    C3749y.m933a((Class) BuglyBroadcastReceiver.f553d.getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
                    synchronized (this) {
                        BuglyBroadcastReceiver.this.f555b.registerReceiver(BuglyBroadcastReceiver.f553d, BuglyBroadcastReceiver.this.f554a, "com.tencent.bugly.BuglyBroadcastReceiver.permission", (Handler) null);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public synchronized void unregister(Context context) {
        try {
            C3749y.m933a((Class) getClass(), "Unregister broadcast receiver of Bugly.", new Object[0]);
            context.unregisterReceiver(this);
            this.f555b = context;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            m439a(context, intent);
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b0, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00be, code lost:
        return false;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean m439a(android.content.Context r8, android.content.Intent r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = 0
            if (r8 == 0) goto L_0x00bd
            if (r9 == 0) goto L_0x00bd
            java.lang.String r9 = r9.getAction()     // Catch:{ all -> 0x00ba }
            java.lang.String r1 = "android.net.conn.CONNECTIVITY_CHANGE"
            boolean r9 = r9.equals(r1)     // Catch:{ all -> 0x00ba }
            if (r9 != 0) goto L_0x0014
            goto L_0x00bd
        L_0x0014:
            boolean r9 = r7.f557e     // Catch:{ all -> 0x00ba }
            r1 = 1
            if (r9 == 0) goto L_0x001d
            r7.f557e = r0     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r1
        L_0x001d:
            android.content.Context r9 = r7.f555b     // Catch:{ all -> 0x00ba }
            java.lang.String r9 = com.tencent.bugly.crashreport.common.info.C3627b.m393b(r9)     // Catch:{ all -> 0x00ba }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "is Connect BC "
            r2.<init>(r3)     // Catch:{ all -> 0x00ba }
            r2.append(r9)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00ba }
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.proguard.C3749y.m939c(r2, r3)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = "network %s changed to %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ba }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r4.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r5 = r7.f556c     // Catch:{ all -> 0x00ba }
            r4.append(r5)     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00ba }
            r3[r0] = r4     // Catch:{ all -> 0x00ba }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r4.<init>()     // Catch:{ all -> 0x00ba }
            r4.append(r9)     // Catch:{ all -> 0x00ba }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00ba }
            r3[r1] = r4     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.proguard.C3749y.m934a(r2, r3)     // Catch:{ all -> 0x00ba }
            if (r9 != 0) goto L_0x0063
            r8 = 0
            r7.f556c = r8     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r1
        L_0x0063:
            java.lang.String r2 = r7.f556c     // Catch:{ all -> 0x00ba }
            r7.f556c = r9     // Catch:{ all -> 0x00ba }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.crashreport.common.strategy.a r5 = com.tencent.bugly.crashreport.common.strategy.C3644a.m426a()     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.proguard.v r6 = com.tencent.bugly.proguard.C3743v.m903a()     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.crashreport.common.info.a r8 = com.tencent.bugly.crashreport.common.info.C3626a.m337a((android.content.Context) r8)     // Catch:{ all -> 0x00ba }
            if (r5 == 0) goto L_0x00b1
            if (r6 == 0) goto L_0x00b1
            if (r8 != 0) goto L_0x007e
            goto L_0x00b1
        L_0x007e:
            boolean r8 = r9.equals(r2)     // Catch:{ all -> 0x00ba }
            if (r8 != 0) goto L_0x00af
            int r8 = com.tencent.bugly.crashreport.crash.C3678c.f676a     // Catch:{ all -> 0x00ba }
            long r8 = r6.mo24332a((int) r8)     // Catch:{ all -> 0x00ba }
            long r3 = r3 - r8
            r8 = 30000(0x7530, double:1.4822E-319)
            int r2 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x00af
            java.lang.String r8 = "try to upload crash on network changed."
            java.lang.Object[] r9 = new java.lang.Object[r0]     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.proguard.C3749y.m934a(r8, r9)     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.crashreport.crash.c r8 = com.tencent.bugly.crashreport.crash.C3678c.m540a()     // Catch:{ all -> 0x00ba }
            if (r8 == 0) goto L_0x00a3
            r2 = 0
            r8.mo24144a((long) r2)     // Catch:{ all -> 0x00ba }
        L_0x00a3:
            java.lang.String r8 = "try to upload userinfo on network changed."
            java.lang.Object[] r9 = new java.lang.Object[r0]     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.proguard.C3749y.m934a(r8, r9)     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.crashreport.biz.a r8 = com.tencent.bugly.crashreport.biz.C3622b.f430a     // Catch:{ all -> 0x00ba }
            r8.mo24027b()     // Catch:{ all -> 0x00ba }
        L_0x00af:
            monitor-exit(r7)
            return r1
        L_0x00b1:
            java.lang.String r8 = "not inited BC not work"
            java.lang.Object[] r9 = new java.lang.Object[r0]     // Catch:{ all -> 0x00ba }
            com.tencent.bugly.proguard.C3749y.m940d(r8, r9)     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r1
        L_0x00ba:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00bd:
            monitor-exit(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver.m439a(android.content.Context, android.content.Intent):boolean");
    }
}
