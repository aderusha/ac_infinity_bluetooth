package com.tencent.bugly;

import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.C3678c;

/* compiled from: BUGLY */
public class CrashModule extends C3611a {
    public static final int MODULE_ID = 1004;

    /* renamed from: c */
    private static int f384c;

    /* renamed from: e */
    private static CrashModule f385e = new CrashModule();

    /* renamed from: a */
    private long f386a;

    /* renamed from: b */
    private BuglyStrategy.C3610a f387b;

    /* renamed from: d */
    private boolean f388d = false;

    public static CrashModule getInstance() {
        f385e.f389id = 1004;
        return f385e;
    }

    public synchronized boolean hasInitialized() {
        return this.f388d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cc, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void init(android.content.Context r12, boolean r13, com.tencent.bugly.BuglyStrategy r14) {
        /*
            r11 = this;
            monitor-enter(r11)
            if (r12 == 0) goto L_0x00cb
            boolean r0 = r11.f388d     // Catch:{ all -> 0x00c8 }
            if (r0 == 0) goto L_0x0009
            goto L_0x00cb
        L_0x0009:
            java.lang.String r0 = "Initializing crash module."
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.proguard.C3749y.m934a(r0, r2)     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.proguard.m r0 = com.tencent.bugly.proguard.C3726m.m815a()     // Catch:{ all -> 0x00c8 }
            int r2 = f384c     // Catch:{ all -> 0x00c8 }
            r3 = 1
            int r2 = r2 + r3
            f384c = r2     // Catch:{ all -> 0x00c8 }
            r4 = 1004(0x3ec, float:1.407E-42)
            r0.mo24284a((int) r4, (int) r2)     // Catch:{ all -> 0x00c8 }
            r11.f388d = r3     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.crashreport.CrashReport.setContext(r12)     // Catch:{ all -> 0x00c8 }
            r11.m276a(r12, r14)     // Catch:{ all -> 0x00c8 }
            r5 = 1004(0x3ec, float:1.407E-42)
            com.tencent.bugly.BuglyStrategy$a r8 = r11.f387b     // Catch:{ all -> 0x00c8 }
            r9 = 0
            r10 = 0
            r6 = r12
            r7 = r13
            com.tencent.bugly.crashreport.crash.c r13 = com.tencent.bugly.crashreport.crash.C3678c.m541a(r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x00c8 }
            r13.mo24154f()     // Catch:{ all -> 0x00c8 }
            if (r14 == 0) goto L_0x0058
            int r0 = r14.getCallBackType()     // Catch:{ all -> 0x00c8 }
            r13.mo24143a((int) r0)     // Catch:{ all -> 0x00c8 }
            boolean r0 = r14.getCloseErrorCallback()     // Catch:{ all -> 0x00c8 }
            r13.mo24148a((boolean) r0)     // Catch:{ all -> 0x00c8 }
            boolean r0 = r14.isUploadSpotCrash()     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.crashreport.crash.C3678c.f687l = r0     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.crashreport.common.info.a r0 = com.tencent.bugly.crashreport.common.info.C3626a.m337a((android.content.Context) r12)     // Catch:{ all -> 0x00c8 }
            boolean r2 = r14.isEnableRecordAnrMainStack()     // Catch:{ all -> 0x00c8 }
            r0.mo24062b((boolean) r2)     // Catch:{ all -> 0x00c8 }
        L_0x0058:
            if (r14 == 0) goto L_0x0063
            boolean r0 = r14.isEnableCatchAnrTrace()     // Catch:{ all -> 0x00c8 }
            if (r0 == 0) goto L_0x0063
            r13.mo24159k()     // Catch:{ all -> 0x00c8 }
        L_0x0063:
            r13.mo24163o()     // Catch:{ all -> 0x00c8 }
            if (r14 == 0) goto L_0x007a
            boolean r0 = r14.isEnableNativeCrashMonitor()     // Catch:{ all -> 0x00c8 }
            if (r0 == 0) goto L_0x006f
            goto L_0x007a
        L_0x006f:
            java.lang.String r0 = "[crash] Closed native crash monitor!"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.proguard.C3749y.m934a(r0, r2)     // Catch:{ all -> 0x00c8 }
            r13.mo24155g()     // Catch:{ all -> 0x00c8 }
            goto L_0x007d
        L_0x007a:
            r13.mo24156h()     // Catch:{ all -> 0x00c8 }
        L_0x007d:
            if (r14 == 0) goto L_0x0091
            boolean r0 = r14.isEnableANRCrashMonitor()     // Catch:{ all -> 0x00c8 }
            if (r0 == 0) goto L_0x0086
            goto L_0x0091
        L_0x0086:
            java.lang.String r0 = "[crash] Closed ANR monitor!"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.proguard.C3749y.m934a(r0, r1)     // Catch:{ all -> 0x00c8 }
            r13.mo24158j()     // Catch:{ all -> 0x00c8 }
            goto L_0x0094
        L_0x0091:
            r13.mo24157i()     // Catch:{ all -> 0x00c8 }
        L_0x0094:
            if (r14 == 0) goto L_0x009c
            boolean r0 = r14.isMerged()     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.crashreport.crash.C3678c.f679d = r0     // Catch:{ all -> 0x00c8 }
        L_0x009c:
            if (r14 == 0) goto L_0x00a3
            long r0 = r14.getAppReportDelay()     // Catch:{ all -> 0x00c8 }
            goto L_0x00a5
        L_0x00a3:
            r0 = 0
        L_0x00a5:
            r13.mo24144a((long) r0)     // Catch:{ all -> 0x00c8 }
            r13.mo24162n()     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.crashreport.crash.C3681d.m572a((android.content.Context) r12)     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver r13 = com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver.getInstance()     // Catch:{ all -> 0x00c8 }
            java.lang.String r14 = "android.net.conn.CONNECTIVITY_CHANGE"
            r13.addFilter(r14)     // Catch:{ all -> 0x00c8 }
            r13.register(r12)     // Catch:{ all -> 0x00c8 }
            com.tencent.bugly.proguard.m r12 = com.tencent.bugly.proguard.C3726m.m815a()     // Catch:{ all -> 0x00c8 }
            int r13 = f384c     // Catch:{ all -> 0x00c8 }
            int r13 = r13 - r3
            f384c = r13     // Catch:{ all -> 0x00c8 }
            r12.mo24284a((int) r4, (int) r13)     // Catch:{ all -> 0x00c8 }
            monitor-exit(r11)
            return
        L_0x00c8:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        L_0x00cb:
            monitor-exit(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.CrashModule.init(android.content.Context, boolean, com.tencent.bugly.BuglyStrategy):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m276a(android.content.Context r7, com.tencent.bugly.BuglyStrategy r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r8 != 0) goto L_0x0005
            monitor-exit(r6)
            return
        L_0x0005:
            java.lang.String r0 = r8.getLibBuglySOFilePath()     // Catch:{ all -> 0x0052 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0052 }
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0020
            com.tencent.bugly.crashreport.common.info.a r7 = com.tencent.bugly.crashreport.common.info.C3626a.m337a((android.content.Context) r7)     // Catch:{ all -> 0x0052 }
            r7.f504l = r0     // Catch:{ all -> 0x0052 }
            java.lang.String r7 = "setted libBugly.so file path :%s"
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
            r1[r3] = r0     // Catch:{ all -> 0x0052 }
            com.tencent.bugly.proguard.C3749y.m934a(r7, r1)     // Catch:{ all -> 0x0052 }
        L_0x0020:
            com.tencent.bugly.BuglyStrategy$a r7 = r8.getCrashHandleCallback()     // Catch:{ all -> 0x0052 }
            if (r7 == 0) goto L_0x0033
            com.tencent.bugly.BuglyStrategy$a r7 = r8.getCrashHandleCallback()     // Catch:{ all -> 0x0052 }
            r6.f387b = r7     // Catch:{ all -> 0x0052 }
            java.lang.String r7 = "setted CrashHanldeCallback"
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ all -> 0x0052 }
            com.tencent.bugly.proguard.C3749y.m934a(r7, r0)     // Catch:{ all -> 0x0052 }
        L_0x0033:
            long r0 = r8.getAppReportDelay()     // Catch:{ all -> 0x0052 }
            r4 = 0
            int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r7 <= 0) goto L_0x0050
            long r7 = r8.getAppReportDelay()     // Catch:{ all -> 0x0052 }
            r6.f386a = r7     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = "setted delay: %d"
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0052 }
            r1[r3] = r7     // Catch:{ all -> 0x0052 }
            com.tencent.bugly.proguard.C3749y.m934a(r0, r1)     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r6)
            return
        L_0x0052:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.CrashModule.m276a(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
        C3678c a;
        if (strategyBean != null && (a = C3678c.m540a()) != null) {
            a.mo24145a(strategyBean);
        }
    }

    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
