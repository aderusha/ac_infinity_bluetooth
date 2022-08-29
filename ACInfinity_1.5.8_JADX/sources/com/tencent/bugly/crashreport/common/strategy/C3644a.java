package com.tencent.bugly.crashreport.common.strategy;

import android.content.Context;
import android.os.Parcelable;
import com.tencent.bugly.C3611a;
import com.tencent.bugly.crashreport.biz.C3622b;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3711aq;
import com.tencent.bugly.proguard.C3729n;
import com.tencent.bugly.proguard.C3730o;
import com.tencent.bugly.proguard.C3733q;
import com.tencent.bugly.proguard.C3747x;
import com.tencent.bugly.proguard.C3749y;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.crashreport.common.strategy.a */
/* compiled from: BUGLY */
public final class C3644a {

    /* renamed from: a */
    public static int f544a = 1000;

    /* renamed from: b */
    private static C3644a f545b;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static String f546h;

    /* renamed from: c */
    private final List<C3611a> f547c;

    /* renamed from: d */
    private final C3747x f548d;

    /* renamed from: e */
    private final StrategyBean f549e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public StrategyBean f550f = null;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Context f551g;

    private C3644a(Context context, List<C3611a> list) {
        String str;
        this.f551g = context;
        if (C3626a.m337a(context) != null) {
            String str2 = C3626a.m337a(context).f516x;
            if ("oversea".equals(str2)) {
                str = "https://astat.bugly.qcloud.com/rqd/async";
            } else {
                str = "na_https".equals(str2) ? "https://astat.bugly.cros.wr.pvp.net/:8180/rqd/async" : str;
            }
            StrategyBean.f522a = str;
            StrategyBean.f523b = str;
        }
        this.f549e = new StrategyBean();
        this.f547c = list;
        this.f548d = C3747x.m926a();
    }

    /* renamed from: a */
    public static synchronized C3644a m427a(Context context, List<C3611a> list) {
        C3644a aVar;
        synchronized (C3644a.class) {
            if (f545b == null) {
                f545b = new C3644a(context, list);
            }
            aVar = f545b;
        }
        return aVar;
    }

    /* renamed from: a */
    public final void mo24099a(long j) {
        this.f548d.mo24345a(new Thread() {
            public final void run() {
                try {
                    Map<String, byte[]> a = C3730o.m839a().mo24296a(C3644a.f544a, (C3729n) null, true);
                    if (a != null) {
                        byte[] bArr = a.get("device");
                        byte[] bArr2 = a.get("gateway");
                        if (bArr != null) {
                            C3626a.m337a(C3644a.this.f551g).mo24071f(new String(bArr));
                        }
                        if (bArr2 != null) {
                            C3626a.m337a(C3644a.this.f551g).mo24069e(new String(bArr2));
                        }
                    }
                    StrategyBean unused = C3644a.this.f550f = C3644a.m430d();
                    if (C3644a.this.f550f != null) {
                        if (C3695ab.m679a(C3644a.f546h) || !C3695ab.m694c(C3644a.f546h)) {
                            C3644a.this.f550f.f537p = StrategyBean.f522a;
                            C3644a.this.f550f.f538q = StrategyBean.f523b;
                        } else {
                            C3644a.this.f550f.f537p = C3644a.f546h;
                            C3644a.this.f550f.f538q = C3644a.f546h;
                        }
                    }
                } catch (Throwable th) {
                    if (!C3749y.m935a(th)) {
                        th.printStackTrace();
                    }
                }
                C3644a aVar = C3644a.this;
                aVar.mo24100a(aVar.f550f, false);
            }
        }, j);
    }

    /* renamed from: a */
    public static synchronized C3644a m426a() {
        C3644a aVar;
        synchronized (C3644a.class) {
            aVar = f545b;
        }
        return aVar;
    }

    /* renamed from: b */
    public final synchronized boolean mo24102b() {
        return this.f550f != null;
    }

    /* renamed from: c */
    public final StrategyBean mo24103c() {
        StrategyBean strategyBean = this.f550f;
        if (strategyBean != null) {
            if (!C3695ab.m694c(strategyBean.f537p)) {
                this.f550f.f537p = StrategyBean.f522a;
            }
            if (!C3695ab.m694c(this.f550f.f538q)) {
                this.f550f.f538q = StrategyBean.f523b;
            }
            return this.f550f;
        }
        if (!C3695ab.m679a(f546h) && C3695ab.m694c(f546h)) {
            this.f549e.f537p = f546h;
            this.f549e.f538q = f546h;
        }
        return this.f549e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo24100a(StrategyBean strategyBean, boolean z) {
        C3749y.m939c("[Strategy] Notify %s", C3622b.class.getName());
        C3622b.m309a(strategyBean, z);
        for (C3611a next : this.f547c) {
            try {
                C3749y.m939c("[Strategy] Notify %s", next.getClass().getName());
                next.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m428a(String str) {
        if (C3695ab.m679a(str) || !C3695ab.m694c(str)) {
            C3749y.m940d("URL user set is invalid.", new Object[0]);
        } else {
            f546h = str;
        }
    }

    /* renamed from: a */
    public final void mo24101a(C3711aq aqVar) {
        if (aqVar != null) {
            if (this.f550f == null || aqVar.f908h != this.f550f.f535n) {
                StrategyBean strategyBean = new StrategyBean();
                strategyBean.f526e = aqVar.f901a;
                strategyBean.f528g = aqVar.f903c;
                strategyBean.f527f = aqVar.f902b;
                if (C3695ab.m679a(f546h) || !C3695ab.m694c(f546h)) {
                    if (C3695ab.m694c(aqVar.f904d)) {
                        C3749y.m939c("[Strategy] Upload url changes to %s", aqVar.f904d);
                        strategyBean.f537p = aqVar.f904d;
                    }
                    if (C3695ab.m694c(aqVar.f905e)) {
                        C3749y.m939c("[Strategy] Exception upload url changes to %s", aqVar.f905e);
                        strategyBean.f538q = aqVar.f905e;
                    }
                }
                if (aqVar.f906f != null && !C3695ab.m679a(aqVar.f906f.f896a)) {
                    strategyBean.f539r = aqVar.f906f.f896a;
                }
                if (aqVar.f908h != 0) {
                    strategyBean.f535n = aqVar.f908h;
                }
                if (!(aqVar == null || aqVar.f907g == null || aqVar.f907g.size() <= 0)) {
                    strategyBean.f540s = aqVar.f907g;
                    String str = aqVar.f907g.get("B11");
                    strategyBean.f529h = str != null && str.equals("1");
                    String str2 = aqVar.f907g.get("B3");
                    if (str2 != null) {
                        strategyBean.f543v = Long.parseLong(str2);
                    }
                    strategyBean.f536o = (long) aqVar.f909i;
                    strategyBean.f542u = (long) aqVar.f909i;
                    String str3 = aqVar.f907g.get("B27");
                    if (str3 != null && str3.length() > 0) {
                        try {
                            int parseInt = Integer.parseInt(str3);
                            if (parseInt > 0) {
                                strategyBean.f541t = parseInt;
                            }
                        } catch (Exception e) {
                            if (!C3749y.m935a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                    String str4 = aqVar.f907g.get("B25");
                    strategyBean.f531j = str4 != null && str4.equals("1");
                }
                C3749y.m934a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", Boolean.valueOf(strategyBean.f526e), Boolean.valueOf(strategyBean.f528g), Boolean.valueOf(strategyBean.f527f), Boolean.valueOf(strategyBean.f529h), Boolean.valueOf(strategyBean.f530i), Boolean.valueOf(strategyBean.f533l), Boolean.valueOf(strategyBean.f534m), Long.valueOf(strategyBean.f536o), Boolean.valueOf(strategyBean.f531j), Long.valueOf(strategyBean.f535n));
                this.f550f = strategyBean;
                if (!C3695ab.m694c(aqVar.f904d)) {
                    C3749y.m939c("[Strategy] download url is null", new Object[0]);
                    this.f550f.f537p = "";
                }
                if (!C3695ab.m694c(aqVar.f905e)) {
                    C3749y.m939c("[Strategy] download crashurl is null", new Object[0]);
                    this.f550f.f538q = "";
                }
                C3730o.m839a().mo24300b(2);
                C3733q qVar = new C3733q();
                qVar.f999b = 2;
                qVar.f998a = strategyBean.f524c;
                qVar.f1002e = strategyBean.f525d;
                qVar.f1004g = C3695ab.m680a((Parcelable) strategyBean);
                C3730o.m839a().mo24299a(qVar);
                mo24100a(strategyBean, true);
            }
        }
    }

    /* renamed from: d */
    public static StrategyBean m430d() {
        List<C3733q> a = C3730o.m839a().mo24295a(2);
        if (a == null || a.size() <= 0) {
            return null;
        }
        C3733q qVar = a.get(0);
        if (qVar.f1004g != null) {
            return (StrategyBean) C3695ab.m658a(qVar.f1004g, StrategyBean.CREATOR);
        }
        return null;
    }
}
