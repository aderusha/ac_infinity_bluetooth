package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.anr.C3654b;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3729n;
import com.tencent.bugly.proguard.C3730o;
import com.tencent.bugly.proguard.C3733q;
import com.tencent.bugly.proguard.C3737t;
import com.tencent.bugly.proguard.C3743v;
import com.tencent.bugly.proguard.C3747x;
import com.tencent.bugly.proguard.C3749y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.tencent.bugly.crashreport.crash.c */
/* compiled from: BUGLY */
public final class C3678c {

    /* renamed from: a */
    public static int f676a = 0;

    /* renamed from: b */
    public static boolean f677b = false;

    /* renamed from: c */
    public static int f678c = 2;

    /* renamed from: d */
    public static boolean f679d = false;

    /* renamed from: e */
    public static int f680e = 20480;

    /* renamed from: f */
    public static int f681f = 20480;

    /* renamed from: g */
    public static long f682g = 604800000;

    /* renamed from: h */
    public static String f683h = null;

    /* renamed from: i */
    public static boolean f684i = false;

    /* renamed from: j */
    public static String f685j = null;

    /* renamed from: k */
    public static int f686k = 5000;

    /* renamed from: l */
    public static boolean f687l = true;

    /* renamed from: m */
    public static boolean f688m = false;

    /* renamed from: n */
    public static String f689n;

    /* renamed from: o */
    public static String f690o;

    /* renamed from: r */
    private static C3678c f691r;

    /* renamed from: p */
    public final C3662b f692p;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public final Context f693q;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public final C3684e f694s;

    /* renamed from: t */
    private final NativeCrashHandler f695t;

    /* renamed from: u */
    private C3644a f696u;

    /* renamed from: v */
    private C3747x f697v;

    /* renamed from: w */
    private final C3654b f698w;

    /* renamed from: x */
    private Boolean f699x;

    /* renamed from: y */
    private int f700y = 31;

    /* renamed from: z */
    private boolean f701z = false;

    private C3678c(int i, Context context, C3747x xVar, boolean z, BuglyStrategy.C3610a aVar, C3729n nVar, String str) {
        f676a = i;
        Context a = C3695ab.m653a(context);
        this.f693q = a;
        this.f696u = C3644a.m426a();
        this.f697v = xVar;
        C3743v a2 = C3743v.m903a();
        C3730o a3 = C3730o.m839a();
        C3662b bVar = new C3662b(i, a, a2, a3, this.f696u, aVar, nVar);
        this.f692p = bVar;
        C3626a a4 = C3626a.m337a(a);
        this.f694s = new C3684e(a, bVar, this.f696u, a4);
        NativeCrashHandler instance = NativeCrashHandler.getInstance(a, a4, bVar, this.f696u, xVar, z, str);
        this.f695t = instance;
        a4.f454D = instance;
        this.f698w = C3654b.m454a(a, this.f696u, a4, xVar, a3, bVar, aVar);
    }

    /* renamed from: a */
    public static synchronized C3678c m541a(int i, Context context, boolean z, BuglyStrategy.C3610a aVar, C3729n nVar, String str) {
        C3678c cVar;
        synchronized (C3678c.class) {
            if (f691r == null) {
                f691r = new C3678c(1004, context, C3747x.m926a(), z, aVar, (C3729n) null, (String) null);
            }
            cVar = f691r;
        }
        return cVar;
    }

    /* renamed from: a */
    public static synchronized C3678c m540a() {
        C3678c cVar;
        synchronized (C3678c.class) {
            cVar = f691r;
        }
        return cVar;
    }

    /* renamed from: a */
    public final void mo24145a(StrategyBean strategyBean) {
        this.f694s.mo24175a(strategyBean);
        this.f695t.onStrategyChanged(strategyBean);
        this.f698w.mo24121b();
    }

    /* renamed from: b */
    public final boolean mo24150b() {
        Boolean bool = this.f699x;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = C3626a.m339b().f496d;
        List<C3733q> a = C3730o.m839a().mo24295a(1);
        ArrayList arrayList = new ArrayList();
        if (a == null || a.size() <= 0) {
            this.f699x = false;
            return false;
        }
        for (C3733q next : a) {
            if (str.equals(next.f1000c)) {
                this.f699x = true;
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            C3730o.m839a().mo24297a((List<C3733q>) arrayList);
        }
        return true;
    }

    /* renamed from: c */
    public final synchronized void mo24151c() {
        this.f694s.mo24174a();
        this.f695t.setUserOpened(true);
        this.f698w.mo24119a(true);
    }

    /* renamed from: d */
    public final synchronized void mo24152d() {
        this.f694s.mo24177b();
        this.f695t.setUserOpened(false);
        this.f698w.mo24119a(false);
    }

    /* renamed from: e */
    public final void mo24153e() {
        this.f694s.mo24177b();
    }

    /* renamed from: f */
    public final void mo24154f() {
        this.f694s.mo24174a();
    }

    /* renamed from: g */
    public final void mo24155g() {
        this.f695t.setUserOpened(false);
    }

    /* renamed from: h */
    public final void mo24156h() {
        this.f695t.setUserOpened(true);
    }

    /* renamed from: i */
    public final void mo24157i() {
        this.f698w.mo24119a(true);
    }

    /* renamed from: j */
    public final void mo24158j() {
        this.f698w.mo24119a(false);
    }

    /* renamed from: k */
    public final void mo24159k() {
        this.f695t.enableCatchAnrTrace();
    }

    /* renamed from: a */
    public final synchronized void mo24149a(boolean z, boolean z2, boolean z3) {
        this.f695t.testNativeCrash(z, z2, z3);
    }

    /* renamed from: l */
    public final synchronized void mo24160l() {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i < 30) {
                try {
                    C3749y.m934a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i2));
                    C3695ab.m687b(5000);
                    i = i2;
                } catch (Throwable th) {
                    if (!C3749y.m935a(th)) {
                        th.printStackTrace();
                    }
                    return;
                }
            }
        }
    }

    /* renamed from: m */
    public final boolean mo24161m() {
        return this.f698w.mo24120a();
    }

    /* renamed from: a */
    public final void mo24147a(Thread thread, Throwable th, boolean z, String str, byte[] bArr, boolean z2, boolean z3) {
        final Thread thread2 = thread;
        final Throwable th2 = th;
        final boolean z4 = z2;
        this.f697v.mo24344a(new Runnable(false, (String) null, (byte[]) null, true) {
            public final void run() {
                try {
                    C3749y.m939c("post a throwable %b", Boolean.valueOf(false));
                    C3678c.this.f694s.mo24176a(thread2, th2, false, null, null, true);
                    if (z4) {
                        C3749y.m934a("clear user datas", new Object[0]);
                        C3626a.m337a(C3678c.this.f693q).mo24092y();
                    }
                } catch (Throwable th) {
                    if (!C3749y.m938b(th)) {
                        th.printStackTrace();
                    }
                    C3749y.m941e("java catch error: %s", th2.toString());
                }
            }
        });
    }

    /* renamed from: a */
    public final void mo24146a(CrashDetailBean crashDetailBean) {
        this.f692p.mo24141e(crashDetailBean);
    }

    /* renamed from: a */
    public final void mo24144a(long j) {
        C3747x.m926a().mo24345a(new Thread() {
            public final void run() {
                ArrayList arrayList;
                if (!C3695ab.m675a(C3678c.this.f693q, "local_crash_lock", 10000)) {
                    C3749y.m939c("Failed to lock file for uploading local crash.", new Object[0]);
                    return;
                }
                C3737t.m875a().mo24316b();
                List<CrashDetailBean> a = C3678c.this.f692p.mo24134a();
                if (a == null || a.size() <= 0) {
                    C3749y.m939c("no crash need to be uploaded at this start", new Object[0]);
                } else {
                    C3749y.m939c("Size of crash list: %s", Integer.valueOf(a.size()));
                    int size = a.size();
                    if (((long) size) > 20) {
                        ArrayList arrayList2 = new ArrayList();
                        Collections.sort(a);
                        for (int i = 0; ((long) i) < 20; i++) {
                            arrayList2.add(a.get((size - 1) - i));
                        }
                        arrayList = arrayList2;
                    } else {
                        arrayList = a;
                    }
                    C3678c.this.f692p.mo24136a(arrayList, 0, false, false, false);
                }
                C3695ab.m691b(C3678c.this.f693q, "local_crash_lock");
            }
        }, j);
    }

    /* renamed from: n */
    public final void mo24162n() {
        this.f695t.checkUploadRecordCrash();
    }

    /* renamed from: o */
    public final void mo24163o() {
        if (C3626a.m339b().f496d.equals(AppInfo.m327a(this.f693q))) {
            this.f695t.removeEmptyNativeRecordFiles();
        }
    }

    /* renamed from: a */
    public final void mo24143a(int i) {
        this.f700y = i;
    }

    /* renamed from: a */
    public final void mo24148a(boolean z) {
        this.f701z = z;
    }

    /* renamed from: p */
    public final boolean mo24164p() {
        return this.f701z;
    }

    /* renamed from: q */
    public final boolean mo24165q() {
        return (this.f700y & 16) > 0;
    }

    /* renamed from: r */
    public final boolean mo24166r() {
        return (this.f700y & 8) > 0;
    }

    /* renamed from: s */
    public final boolean mo24167s() {
        return (this.f700y & 4) > 0;
    }

    /* renamed from: t */
    public final boolean mo24168t() {
        return (this.f700y & 2) > 0;
    }

    /* renamed from: u */
    public final boolean mo24169u() {
        return (this.f700y & 1) > 0;
    }
}
