package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.info.C3627b;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C3692aa;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3747x;
import com.tencent.bugly.proguard.C3749y;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.crashreport.crash.d */
/* compiled from: BUGLY */
public final class C3681d {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static C3681d f711a;

    /* renamed from: b */
    private C3644a f712b;

    /* renamed from: c */
    private C3626a f713c;

    /* renamed from: d */
    private C3662b f714d;

    /* renamed from: e */
    private Context f715e;

    /* renamed from: a */
    static /* synthetic */ void m573a(C3681d dVar) {
        C3749y.m939c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class<?> cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            dVar.f713c.getClass();
            C3695ab.m674a(cls, "sdkPackageName", "com.tencent.bugly", (Object) null);
            C3749y.m939c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable unused) {
            C3749y.m934a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m574a(C3681d dVar, Thread thread, int i, String str, String str2, String str3, Map map) {
        String str4;
        String str5;
        C3681d dVar2 = dVar;
        int i2 = i;
        String str6 = str;
        String str7 = str2;
        String str8 = str3;
        Map map2 = map;
        Thread currentThread = thread == null ? Thread.currentThread() : thread;
        int i3 = 5;
        if (i2 == 4) {
            str4 = "Unity";
        } else if (i2 == 5 || i2 == 6) {
            str4 = "Cocos";
        } else if (i2 != 8) {
            C3749y.m940d("[ExtraCrashManager] Unknown extra crash type: %d", Integer.valueOf(i));
            return;
        } else {
            str4 = "H5";
        }
        C3749y.m941e("[ExtraCrashManager] %s Crash Happen", str4);
        if (!dVar2.f712b.mo24102b()) {
            C3749y.m940d("[ExtraCrashManager] There is no remote strategy, but still store it.", new Object[0]);
        }
        StrategyBean c = dVar2.f712b.mo24103c();
        if (!c.f526e) {
            if (dVar2.f712b.mo24102b()) {
                C3749y.m941e("[ExtraCrashManager] Crash report was closed by remote. Will not upload to Bugly , print local for helpful!", new Object[0]);
                String a = C3695ab.m659a();
                String str9 = dVar2.f713c.f496d;
                String name = currentThread.getName();
                C3662b.m495a(str4, a, str9, name, str6 + "\n" + str7 + "\n" + str8, (CrashDetailBean) null);
                C3749y.m941e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
        }
        if (i2 == 5 || i2 == 6) {
            try {
                if (!c.f531j) {
                    C3749y.m941e("[ExtraCrashManager] %s report is disabled.", str4);
                    return;
                }
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
                return;
            } finally {
                C3749y.m941e("[ExtraCrashManager] Successfully handled.", new Object[0]);
            }
        } else if (i2 == 8) {
            if (!c.f532k) {
                C3749y.m941e("[ExtraCrashManager] %s report is disabled.", str4);
                C3749y.m941e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
        }
        if (i2 != 8) {
            i3 = i2;
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f562C = C3627b.m404j();
        crashDetailBean.f563D = C3627b.m398e();
        crashDetailBean.f564E = C3627b.m406l();
        crashDetailBean.f565F = dVar2.f713c.mo24083o();
        crashDetailBean.f566G = dVar2.f713c.mo24082n();
        crashDetailBean.f567H = dVar2.f713c.mo24084p();
        crashDetailBean.f568I = C3627b.m400f();
        crashDetailBean.f569J = C3627b.m401g();
        crashDetailBean.f570K = C3627b.m402h();
        Context context = dVar2.f715e;
        crashDetailBean.f609w = C3695ab.m660a(C3678c.f680e, (String) null);
        crashDetailBean.f588b = i3;
        crashDetailBean.f591e = dVar2.f713c.mo24079k();
        crashDetailBean.f592f = dVar2.f713c.f501i;
        crashDetailBean.f593g = dVar2.f713c.mo24088u();
        crashDetailBean.f599m = dVar2.f713c.mo24072g();
        crashDetailBean.f600n = str6;
        crashDetailBean.f601o = str7;
        String str10 = "";
        if (str8 != null) {
            String[] split = str8.split("\n");
            if (split.length > 0) {
                str10 = split[0];
            }
            str5 = str8;
        } else {
            str5 = str10;
        }
        crashDetailBean.f602p = str10;
        crashDetailBean.f603q = str5;
        crashDetailBean.f604r = System.currentTimeMillis();
        crashDetailBean.f607u = C3695ab.m667a(crashDetailBean.f603q.getBytes());
        crashDetailBean.f612z = C3695ab.m672a(dVar2.f713c.mo24075h(), C3678c.f681f, false);
        crashDetailBean.f560A = dVar2.f713c.f496d;
        crashDetailBean.f561B = currentThread.getName() + "(" + currentThread.getId() + ")";
        crashDetailBean.f571L = dVar2.f713c.mo24090w();
        crashDetailBean.f594h = dVar2.f713c.mo24087t();
        crashDetailBean.f575P = dVar2.f713c.f476a;
        crashDetailBean.f576Q = dVar2.f713c.mo24058a();
        if (!C3678c.m540a().mo24164p()) {
            dVar2.f714d.mo24140d(crashDetailBean);
        }
        crashDetailBean.f579T = dVar2.f713c.mo24049D();
        crashDetailBean.f580U = dVar2.f713c.mo24050E();
        crashDetailBean.f581V = dVar2.f713c.mo24091x();
        crashDetailBean.f582W = dVar2.f713c.mo24048C();
        crashDetailBean.f611y = C3692aa.m641a();
        if (crashDetailBean.f577R == null) {
            crashDetailBean.f577R = new LinkedHashMap();
        }
        if (map2 != null) {
            crashDetailBean.f577R.putAll(map2);
        }
        String a2 = C3695ab.m659a();
        String str11 = dVar2.f713c.f496d;
        String name2 = currentThread.getName();
        C3662b.m495a(str4, a2, str11, name2, str6 + "\n" + str7 + "\n" + str8, crashDetailBean);
        if (!dVar2.f714d.mo24137a(crashDetailBean)) {
            dVar2.f714d.mo24135a(crashDetailBean, 3000, false);
        }
        C3749y.m941e("[ExtraCrashManager] Successfully handled.", new Object[0]);
    }

    private C3681d(Context context) {
        C3678c a = C3678c.m540a();
        if (a != null) {
            this.f712b = C3644a.m426a();
            this.f713c = C3626a.m337a(context);
            this.f714d = a.f692p;
            this.f715e = context;
            C3747x.m926a().mo24344a(new Runnable() {
                public final void run() {
                    C3681d.m573a(C3681d.this);
                }
            });
        }
    }

    /* renamed from: a */
    public static C3681d m572a(Context context) {
        if (f711a == null) {
            f711a = new C3681d(context);
        }
        return f711a;
    }

    /* renamed from: a */
    public static void m575a(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        final Thread thread2 = thread;
        final int i2 = i;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        C3747x.m926a().mo24344a(new Runnable() {
            public final void run() {
                try {
                    if (C3681d.f711a == null) {
                        C3749y.m941e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    } else {
                        C3681d.m574a(C3681d.f711a, thread2, i2, str4, str5, str6, map2);
                    }
                } catch (Throwable th) {
                    if (!C3749y.m938b(th)) {
                        th.printStackTrace();
                    }
                    C3749y.m941e("[ExtraCrashManager] Crash error %s %s %s", str4, str5, str6);
                }
            }
        });
    }
}
