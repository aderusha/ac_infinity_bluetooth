package com.tencent.bugly.crashreport.biz;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.biz.C3616a;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3747x;
import com.tencent.bugly.proguard.C3749y;

/* renamed from: com.tencent.bugly.crashreport.biz.b */
/* compiled from: BUGLY */
public class C3622b {

    /* renamed from: a */
    public static C3616a f430a = null;

    /* renamed from: b */
    private static boolean f431b = false;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static int f432c = 10;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static long f433d = 300000;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static long f434e = 30000;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static long f435f = 0;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static int f436g = 0;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static long f437h = 0;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public static long f438i = 0;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static long f439j = 0;

    /* renamed from: k */
    private static Application.ActivityLifecycleCallbacks f440k = null;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public static Class<?> f441l = null;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public static boolean f442m = true;

    /* renamed from: a */
    static /* synthetic */ String m304a(String str, String str2) {
        return C3695ab.m659a() + "  " + str + "  " + str2 + "\n";
    }

    /* renamed from: g */
    static /* synthetic */ int m320g() {
        int i = f436g;
        f436g = i + 1;
        return i;
    }

    /* JADX WARNING: type inference failed for: r12v11, types: [android.content.Context] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0069  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m316c(android.content.Context r12, com.tencent.bugly.BuglyStrategy r13) {
        /*
            r0 = 1
            r1 = 0
            if (r13 == 0) goto L_0x000d
            boolean r2 = r13.recordUserInfoOnceADay()
            boolean r13 = r13.isEnableUserInfo()
            goto L_0x000f
        L_0x000d:
            r13 = 1
            r2 = 0
        L_0x000f:
            r3 = 0
            if (r2 == 0) goto L_0x006a
            com.tencent.bugly.crashreport.common.info.a r13 = com.tencent.bugly.crashreport.common.info.C3626a.m337a((android.content.Context) r12)
            java.lang.String r2 = r13.f496d
            com.tencent.bugly.crashreport.biz.a r5 = f430a
            java.util.List r2 = r5.mo24024a((java.lang.String) r2)
            if (r2 == 0) goto L_0x0065
            r5 = 0
        L_0x0022:
            int r6 = r2.size()
            if (r5 >= r6) goto L_0x0065
            java.lang.Object r6 = r2.get(r5)
            com.tencent.bugly.crashreport.biz.UserInfoBean r6 = (com.tencent.bugly.crashreport.biz.UserInfoBean) r6
            java.lang.String r7 = r6.f411n
            java.lang.String r8 = r13.f501i
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0062
            int r7 = r6.f399b
            if (r7 != r0) goto L_0x0062
            long r7 = com.tencent.bugly.proguard.C3695ab.m683b()
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 <= 0) goto L_0x0065
            long r9 = r6.f402e
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 < 0) goto L_0x0062
            long r5 = r6.f403f
            int r13 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r13 > 0) goto L_0x0060
            com.tencent.bugly.crashreport.biz.a r13 = f430a
            com.tencent.bugly.proguard.x r2 = com.tencent.bugly.proguard.C3747x.m926a()
            if (r2 == 0) goto L_0x0060
            com.tencent.bugly.crashreport.biz.a$2 r5 = new com.tencent.bugly.crashreport.biz.a$2
            r5.<init>()
            r2.mo24344a(r5)
        L_0x0060:
            r13 = 0
            goto L_0x0066
        L_0x0062:
            int r5 = r5 + 1
            goto L_0x0022
        L_0x0065:
            r13 = 1
        L_0x0066:
            if (r13 != 0) goto L_0x0069
            return
        L_0x0069:
            r13 = 0
        L_0x006a:
            com.tencent.bugly.crashreport.common.info.a r2 = com.tencent.bugly.crashreport.common.info.C3626a.m339b()
            if (r2 == 0) goto L_0x0079
            boolean r5 = m313b((android.content.Context) r12)
            if (r5 == 0) goto L_0x0079
            r2.mo24053a((int) r1, (boolean) r0)
        L_0x0079:
            if (r13 == 0) goto L_0x00ae
            r13 = 0
            int r2 = android.os.Build.VERSION.SDK_INT
            r5 = 14
            if (r2 < r5) goto L_0x00ae
            android.content.Context r2 = r12.getApplicationContext()
            boolean r2 = r2 instanceof android.app.Application
            if (r2 == 0) goto L_0x0091
            android.content.Context r12 = r12.getApplicationContext()
            r13 = r12
            android.app.Application r13 = (android.app.Application) r13
        L_0x0091:
            if (r13 == 0) goto L_0x00ae
            android.app.Application$ActivityLifecycleCallbacks r12 = f440k     // Catch:{ Exception -> 0x00a4 }
            if (r12 != 0) goto L_0x009e
            com.tencent.bugly.crashreport.biz.b$a r12 = new com.tencent.bugly.crashreport.biz.b$a     // Catch:{ Exception -> 0x00a4 }
            r12.<init>()     // Catch:{ Exception -> 0x00a4 }
            f440k = r12     // Catch:{ Exception -> 0x00a4 }
        L_0x009e:
            android.app.Application$ActivityLifecycleCallbacks r12 = f440k     // Catch:{ Exception -> 0x00a4 }
            r13.registerActivityLifecycleCallbacks(r12)     // Catch:{ Exception -> 0x00a4 }
            goto L_0x00ae
        L_0x00a4:
            r12 = move-exception
            boolean r13 = com.tencent.bugly.proguard.C3749y.m935a(r12)
            if (r13 != 0) goto L_0x00ae
            r12.printStackTrace()
        L_0x00ae:
            boolean r12 = f442m
            if (r12 == 0) goto L_0x00da
            long r12 = java.lang.System.currentTimeMillis()
            f438i = r12
            com.tencent.bugly.crashreport.biz.a r12 = f430a
            r12.mo24026a((int) r0, (boolean) r1, (long) r3)
            java.lang.Object[] r12 = new java.lang.Object[r1]
            java.lang.String r13 = "[session] launch app, new start"
            com.tencent.bugly.proguard.C3749y.m934a(r13, r12)
            com.tencent.bugly.crashreport.biz.a r12 = f430a
            r12.mo24025a()
            com.tencent.bugly.crashreport.biz.a r12 = f430a
            r0 = 21600000(0x1499700, double:1.0671818E-316)
            com.tencent.bugly.proguard.x r13 = com.tencent.bugly.proguard.C3747x.m926a()
            com.tencent.bugly.crashreport.biz.a$c r2 = new com.tencent.bugly.crashreport.biz.a$c
            r2.<init>(r0)
            r13.mo24345a(r2, r0)
        L_0x00da:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.C3622b.m316c(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    /* renamed from: b */
    private static boolean m313b(Context context) {
        try {
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo next : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (next.pid == myPid && next.importance == 100) {
                    return true;
                }
            }
        } catch (Throwable th) {
            C3749y.m938b(th);
        }
        return false;
    }

    /* renamed from: a */
    public static void m308a(final Context context, final BuglyStrategy buglyStrategy) {
        long j;
        if (!f431b) {
            boolean z = C3626a.m337a(context).f497e;
            f442m = z;
            f430a = new C3616a(context, z);
            f431b = true;
            if (buglyStrategy != null) {
                f441l = buglyStrategy.getUserInfoActivity();
                j = buglyStrategy.getAppReportDelay();
            } else {
                j = 0;
            }
            if (j <= 0) {
                m316c(context, buglyStrategy);
            } else {
                C3747x.m926a().mo24345a(new Runnable() {
                    public final void run() {
                        C3622b.m316c(context, buglyStrategy);
                    }
                }, j);
            }
        }
    }

    /* renamed from: a */
    public static void m306a(long j) {
        if (j < 0) {
            j = C3644a.m426a().mo24103c().f536o;
        }
        f435f = j;
    }

    /* renamed from: a */
    public static void m309a(StrategyBean strategyBean, boolean z) {
        C3747x a;
        C3616a aVar = f430a;
        if (!(aVar == null || z || (a = C3747x.m926a()) == null)) {
            a.mo24344a(new Runnable() {
                public final void run() {
                    try {
                        C3616a.this.m298c();
                    } catch (Throwable th) {
                        C3749y.m935a(th);
                    }
                }
            });
        }
        if (strategyBean != null) {
            if (strategyBean.f536o > 0) {
                f434e = strategyBean.f536o;
            }
            if (strategyBean.f541t > 0) {
                f432c = strategyBean.f541t;
            }
            if (strategyBean.f542u > 0) {
                f433d = strategyBean.f542u;
            }
        }
    }

    /* renamed from: a */
    public static void m305a() {
        C3616a aVar = f430a;
        if (aVar != null) {
            aVar.mo24026a(2, false, 0);
        }
    }

    /* JADX WARNING: type inference failed for: r3v4, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m307a(android.content.Context r3) {
        /*
            boolean r0 = f431b
            if (r0 == 0) goto L_0x0034
            if (r3 != 0) goto L_0x0007
            goto L_0x0034
        L_0x0007:
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 14
            if (r1 < r2) goto L_0x0031
            android.content.Context r1 = r3.getApplicationContext()
            boolean r1 = r1 instanceof android.app.Application
            if (r1 == 0) goto L_0x001d
            android.content.Context r3 = r3.getApplicationContext()
            r0 = r3
            android.app.Application r0 = (android.app.Application) r0
        L_0x001d:
            if (r0 == 0) goto L_0x0031
            android.app.Application$ActivityLifecycleCallbacks r3 = f440k     // Catch:{ Exception -> 0x0027 }
            if (r3 == 0) goto L_0x0031
            r0.unregisterActivityLifecycleCallbacks(r3)     // Catch:{ Exception -> 0x0027 }
            goto L_0x0031
        L_0x0027:
            r3 = move-exception
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r3)
            if (r0 != 0) goto L_0x0031
            r3.printStackTrace()
        L_0x0031:
            r3 = 0
            f431b = r3
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.C3622b.m307a(android.content.Context):void");
    }

    /* renamed from: com.tencent.bugly.crashreport.biz.b$a */
    /* compiled from: BUGLY */
    static class C3624a implements Application.ActivityLifecycleCallbacks {
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        C3624a() {
        }

        public final void onActivityStopped(Activity activity) {
            C3749y.m939c(">>> %s onStop <<<", activity.getClass().getName());
            C3626a.m339b().mo24053a(activity.hashCode(), false);
        }

        public final void onActivityStarted(Activity activity) {
            C3749y.m939c(">>> %s onStart <<<", activity.getClass().getName());
            C3626a.m339b().mo24053a(activity.hashCode(), true);
        }

        public final void onActivityResumed(Activity activity) {
            String name = activity.getClass().getName();
            if (C3622b.f441l == null || C3622b.f441l.getName().equals(name)) {
                C3749y.m939c(">>> %s onResumed <<<", name);
                C3626a b = C3626a.m339b();
                if (b != null) {
                    b.f452B.add(C3622b.m304a(name, "onResumed"));
                    b.f507o = name;
                    b.f508p = System.currentTimeMillis();
                    b.f511s = b.f508p - C3622b.f438i;
                    long d = b.f508p - C3622b.f437h;
                    if (d > (C3622b.f435f > 0 ? C3622b.f435f : C3622b.f434e)) {
                        b.mo24066d();
                        C3622b.m320g();
                        C3749y.m934a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(d / 1000), Long.valueOf(C3622b.f434e / 1000));
                        if (C3622b.f436g % C3622b.f432c == 0) {
                            C3622b.f430a.mo24026a(4, C3622b.f442m, 0);
                            return;
                        }
                        C3622b.f430a.mo24026a(4, false, 0);
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - C3622b.f439j > C3622b.f433d) {
                            long unused = C3622b.f439j = currentTimeMillis;
                            C3749y.m934a("add a timer to upload hot start user info", new Object[0]);
                            if (C3622b.f442m) {
                                C3747x.m926a().mo24345a(new C3616a.C3619a((UserInfoBean) null, true), C3622b.f433d);
                            }
                        }
                    }
                }
            }
        }

        public final void onActivityPaused(Activity activity) {
            String name = activity.getClass().getName();
            if (C3622b.f441l == null || C3622b.f441l.getName().equals(name)) {
                C3749y.m939c(">>> %s onPaused <<<", name);
                C3626a b = C3626a.m339b();
                if (b != null) {
                    b.f452B.add(C3622b.m304a(name, "onPaused"));
                    b.f509q = System.currentTimeMillis();
                    b.f510r = b.f509q - b.f508p;
                    long unused = C3622b.f437h = b.f509q;
                    if (b.f510r < 0) {
                        b.f510r = 0;
                    }
                    b.f507o = "background";
                }
            }
        }

        public final void onActivityDestroyed(Activity activity) {
            String name = activity.getClass().getName();
            if (C3622b.f441l == null || C3622b.f441l.getName().equals(name)) {
                C3749y.m939c(">>> %s onDestroyed <<<", name);
                C3626a b = C3626a.m339b();
                if (b != null) {
                    b.f452B.add(C3622b.m304a(name, "onDestroyed"));
                }
            }
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            String name = activity.getClass().getName();
            if (C3622b.f441l == null || C3622b.f441l.getName().equals(name)) {
                C3749y.m939c(">>> %s onCreated <<<", name);
                C3626a b = C3626a.m339b();
                if (b != null) {
                    b.f452B.add(C3622b.m304a(name, "onCreated"));
                }
            }
        }
    }
}
