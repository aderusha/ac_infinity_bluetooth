package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import com.eternal.export.CSVUtil;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3703ai;
import com.tencent.bugly.proguard.C3705ak;
import com.tencent.bugly.proguard.C3729n;
import com.tencent.bugly.proguard.C3730o;
import com.tencent.bugly.proguard.C3737t;
import com.tencent.bugly.proguard.C3743v;
import com.tencent.bugly.proguard.C3749y;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* renamed from: com.tencent.bugly.crashreport.crash.b */
/* compiled from: BUGLY */
public final class C3662b {

    /* renamed from: a */
    private static int f660a;

    /* renamed from: g */
    private static final Map<Integer, Pair<String, String>> f661g = new HashMap<Integer, Pair<String, String>>() {
        {
            put(3, new Pair("203", "103"));
            put(7, new Pair("208", "108"));
            put(0, new Pair("200", "100"));
            put(1, new Pair("201", "101"));
            put(2, new Pair("202", "102"));
            put(4, new Pair("204", "104"));
            put(6, new Pair("206", "106"));
            put(5, new Pair("207", "107"));
        }
    };

    /* renamed from: h */
    private static final ArrayList<C3669a> f662h = new ArrayList<C3669a>() {
        {
            add(new C3670b((byte) 0));
            add(new C3671c((byte) 0));
            add(new C3672d((byte) 0));
            add(new C3673e((byte) 0));
            add(new C3676h((byte) 0));
            add(new C3677i((byte) 0));
            add(new C3674f((byte) 0));
            add(new C3675g((byte) 0));
        }
    };

    /* renamed from: i */
    private static final Map<Integer, Integer> f663i = new HashMap<Integer, Integer>() {
        {
            put(3, 4);
            put(7, 7);
            put(2, 1);
            put(0, 0);
            put(1, 2);
            put(4, 3);
            put(5, 5);
            put(6, 6);
        }
    };

    /* renamed from: j */
    private static final Map<Integer, String> f664j = new HashMap<Integer, String>() {
        {
            put(3, "BuglyAnrCrash");
            put(0, "BuglyJavaCrash");
            put(1, "BuglyNativeCrash");
        }
    };

    /* renamed from: k */
    private static final Map<Integer, String> f665k = new HashMap<Integer, String>() {
        {
            put(3, "BuglyAnrCrashReport");
            put(0, "BuglyJavaCrashReport");
            put(1, "BuglyNativeCrashReport");
        }
    };

    /* renamed from: b */
    private Context f666b;

    /* renamed from: c */
    private C3743v f667c;

    /* renamed from: d */
    private C3644a f668d;

    /* renamed from: e */
    private C3729n f669e;

    /* renamed from: f */
    private BuglyStrategy.C3610a f670f;

    /* renamed from: a */
    static /* synthetic */ void m494a(C3662b bVar, List list, boolean z, long j, String str, String str2) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                CrashDetailBean crashDetailBean = (CrashDetailBean) it.next();
                String str3 = f665k.get(Integer.valueOf(crashDetailBean.f588b));
                if (!TextUtils.isEmpty(str3)) {
                    arrayList.add(new C3737t.C3741c(crashDetailBean.f589c, str3, crashDetailBean.f604r, z, j, str, str2));
                }
            }
            C3737t.m875a().mo24315a((List<C3737t.C3741c>) arrayList);
        }
    }

    public C3662b(int i, Context context, C3743v vVar, C3730o oVar, C3644a aVar, BuglyStrategy.C3610a aVar2, C3729n nVar) {
        f660a = i;
        this.f666b = context;
        this.f667c = vVar;
        this.f668d = aVar;
        this.f670f = aVar2;
        this.f669e = nVar;
    }

    /* renamed from: a */
    private static List<C3648a> m490a(List<C3648a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (C3648a next : list) {
            if (next.f616d && next.f614b <= currentTimeMillis - 86400000) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private CrashDetailBean m488a(List<C3648a> list, CrashDetailBean crashDetailBean) {
        List<CrashDetailBean> c;
        if (list == null || list.isEmpty()) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2 = null;
        ArrayList arrayList = new ArrayList(10);
        for (C3648a next : list) {
            if (next.f617e) {
                arrayList.add(next);
            }
        }
        if (!arrayList.isEmpty() && (c = m514c((List<C3648a>) arrayList)) != null && !c.isEmpty()) {
            Collections.sort(c);
            crashDetailBean2 = c.get(0);
            m492a(crashDetailBean2, c);
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.f596j = true;
            crashDetailBean.f606t = 0;
            crashDetailBean.f605s = "";
            crashDetailBean2 = crashDetailBean;
        }
        m508b(crashDetailBean2, list);
        if (crashDetailBean2.f604r != crashDetailBean.f604r) {
            String str = crashDetailBean2.f605s;
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.f604r);
            if (!str.contains(sb.toString())) {
                crashDetailBean2.f606t++;
                crashDetailBean2.f605s += crashDetailBean.f604r + "\n";
            }
        }
        return crashDetailBean2;
    }

    /* renamed from: a */
    private static void m492a(CrashDetailBean crashDetailBean, List<CrashDetailBean> list) {
        String[] split;
        StringBuilder sb = new StringBuilder(128);
        for (int i = 1; i < list.size(); i++) {
            CrashDetailBean crashDetailBean2 = list.get(i);
            if (!(crashDetailBean2.f605s == null || (split = crashDetailBean2.f605s.split("\n")) == null)) {
                for (String str : split) {
                    if (!crashDetailBean.f605s.contains(str)) {
                        crashDetailBean.f606t++;
                        sb.append(str);
                        sb.append("\n");
                    }
                }
            }
        }
        crashDetailBean.f605s += sb.toString();
    }

    /* renamed from: b */
    private static void m508b(CrashDetailBean crashDetailBean, List<C3648a> list) {
        StringBuilder sb = new StringBuilder(64);
        for (C3648a next : list) {
            if (!next.f617e && !next.f616d) {
                String str = crashDetailBean.f605s;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(next.f614b);
                if (!str.contains(sb2.toString())) {
                    crashDetailBean.f606t++;
                    sb.append(next.f614b);
                    sb.append("\n");
                }
            }
        }
        crashDetailBean.f605s += sb.toString();
    }

    /* renamed from: a */
    public final boolean mo24137a(CrashDetailBean crashDetailBean) {
        return mo24138b(crashDetailBean);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0044 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0045  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo24138b(com.tencent.bugly.crashreport.crash.CrashDetailBean r18) {
        /*
            r17 = this;
            r1 = r18
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x000e
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r1 = "CrashBean is null, won't handle."
            com.tencent.bugly.proguard.C3749y.m940d(r1, r0)
            return r2
        L_0x000e:
            java.lang.String r0 = r1.f603q
            java.lang.String r4 = com.tencent.bugly.crashreport.crash.C3678c.f689n
            if (r4 == 0) goto L_0x0038
            java.lang.String r4 = com.tencent.bugly.crashreport.crash.C3678c.f689n
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x0038
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r5 = com.tencent.bugly.crashreport.crash.C3678c.f689n
            r4[r3] = r5
            java.lang.String r5 = "Crash filter for crash stack is: %s"
            com.tencent.bugly.proguard.C3749y.m939c(r5, r4)
            java.lang.String r4 = com.tencent.bugly.crashreport.crash.C3678c.f689n
            boolean r0 = r0.contains(r4)
            if (r0 == 0) goto L_0x0038
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r4 = "This crash contains the filter string set. It will not be record and upload."
            com.tencent.bugly.proguard.C3749y.m940d(r4, r0)
            r0 = 1
            goto L_0x0039
        L_0x0038:
            r0 = 0
        L_0x0039:
            if (r0 == 0) goto L_0x003c
            return r2
        L_0x003c:
            java.lang.String r0 = r1.f603q
            boolean r0 = m505a((java.lang.String) r0)
            if (r0 == 0) goto L_0x0045
            return r2
        L_0x0045:
            int r0 = r1.f588b
            r4 = 2
            if (r0 == r4) goto L_0x0073
            com.tencent.bugly.proguard.q r0 = new com.tencent.bugly.proguard.q
            r0.<init>()
            r0.f999b = r2
            java.lang.String r5 = r1.f560A
            r0.f1000c = r5
            java.lang.String r5 = r1.f561B
            r0.f1001d = r5
            long r5 = r1.f604r
            r0.f1002e = r5
            com.tencent.bugly.proguard.o r5 = com.tencent.bugly.proguard.C3730o.m839a()
            r5.mo24300b((int) r2)
            com.tencent.bugly.proguard.o r5 = com.tencent.bugly.proguard.C3730o.m839a()
            r5.mo24299a((com.tencent.bugly.proguard.C3733q) r0)
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r5 = "[crash] a crash occur, handling..."
            com.tencent.bugly.proguard.C3749y.m937b(r5, r0)
            goto L_0x007a
        L_0x0073:
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r5 = "[crash] a caught exception occur, handling..."
            com.tencent.bugly.proguard.C3749y.m937b(r5, r0)
        L_0x007a:
            java.util.List r5 = r17.m507b()
            java.util.ArrayList r6 = new java.util.ArrayList
            r0 = 10
            r6.<init>(r0)
            if (r5 == 0) goto L_0x0103
            int r0 = r5.size()
            if (r0 <= 0) goto L_0x0103
            java.util.List r0 = m490a((java.util.List<com.tencent.bugly.crashreport.crash.C3648a>) r5)
            r6.addAll(r0)
            r5.removeAll(r6)
            int r0 = r5.size()
            long r7 = (long) r0
            r9 = 20
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 <= 0) goto L_0x00fa
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r7 = "_id in "
            r0.append(r7)
            java.lang.String r7 = "("
            r0.append(r7)
            java.lang.String r7 = "SELECT _id"
            r0.append(r7)
            java.lang.String r7 = " FROM t_cr"
            r0.append(r7)
            java.lang.String r7 = " order by _id"
            r0.append(r7)
            java.lang.String r7 = " limit 5"
            r0.append(r7)
            java.lang.String r7 = ")"
            r0.append(r7)
            java.lang.String r10 = r0.toString()
            r0.setLength(r3)
            com.tencent.bugly.proguard.o r8 = com.tencent.bugly.proguard.C3730o.m839a()     // Catch:{ all -> 0x00f0 }
            java.lang.String r9 = "t_cr"
            r11 = 0
            r12 = 0
            r13 = 1
            int r0 = r8.mo24291a((java.lang.String) r9, (java.lang.String) r10, (java.lang.String[]) r11, (com.tencent.bugly.proguard.C3729n) r12, (boolean) r13)     // Catch:{ all -> 0x00f0 }
            java.lang.String r7 = "deleted first record %s data %d"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00f0 }
            java.lang.String r8 = "t_cr"
            r4[r3] = r8     // Catch:{ all -> 0x00f0 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00f0 }
            r4[r2] = r0     // Catch:{ all -> 0x00f0 }
            com.tencent.bugly.proguard.C3749y.m939c(r7, r4)     // Catch:{ all -> 0x00f0 }
            goto L_0x00fa
        L_0x00f0:
            r0 = move-exception
            boolean r4 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r4 != 0) goto L_0x00fa
            r0.printStackTrace()
        L_0x00fa:
            r4 = r17
            boolean r0 = r4.m513b((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1, (java.util.List<com.tencent.bugly.crashreport.crash.C3648a>) r5, (java.util.List<com.tencent.bugly.crashreport.crash.C3648a>) r6)
            if (r0 == 0) goto L_0x0105
            return r2
        L_0x0103:
            r4 = r17
        L_0x0105:
            r17.mo24141e((com.tencent.bugly.crashreport.crash.CrashDetailBean) r18)
            java.util.Map<java.lang.Integer, java.lang.String> r0 = f664j
            int r2 = r1.f588b
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r0 = r0.get(r2)
            r9 = r0
            java.lang.String r9 = (java.lang.String) r9
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 != 0) goto L_0x0135
            com.tencent.bugly.proguard.t r0 = com.tencent.bugly.proguard.C3737t.m875a()
            com.tencent.bugly.proguard.t$c r2 = new com.tencent.bugly.proguard.t$c
            java.lang.String r8 = r1.f589c
            long r10 = r1.f604r
            r12 = 1
            r13 = 0
            r16 = 0
            java.lang.String r15 = "realtime"
            r7 = r2
            r7.<init>(r8, r9, r10, r12, r13, r15, r16)
            r0.mo24314a((com.tencent.bugly.proguard.C3737t.C3741c) r2)
        L_0x0135:
            m516d((java.util.List<com.tencent.bugly.crashreport.crash.C3648a>) r6)
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r1 = "[crash] save crash success"
            com.tencent.bugly.proguard.C3749y.m937b(r1, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C3662b.mo24138b(com.tencent.bugly.crashreport.crash.CrashDetailBean):boolean");
    }

    /* renamed from: a */
    private static boolean m505a(String str) {
        if (C3678c.f690o != null && !C3678c.f690o.isEmpty()) {
            try {
                C3749y.m939c("Crash regular filter for crash stack is: %s", C3678c.f690o);
                if (Pattern.compile(C3678c.f690o).matcher(str).find()) {
                    C3749y.m940d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                    return true;
                }
            } catch (Exception e) {
                C3749y.m935a(e);
                C3749y.m940d("Failed to compile " + C3678c.f690o, new Object[0]);
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m504a(CrashDetailBean crashDetailBean, List<C3648a> list, List<C3648a> list2) {
        boolean z = false;
        for (C3648a next : list) {
            if (crashDetailBean.f607u.equals(next.f615c)) {
                if (next.f617e) {
                    z = true;
                }
                list2.add(next);
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0036, code lost:
        if (r0.size() >= com.tencent.bugly.crashreport.crash.C3678c.f678c) goto L_0x0038;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m513b(com.tencent.bugly.crashreport.crash.CrashDetailBean r9, java.util.List<com.tencent.bugly.crashreport.crash.C3648a> r10, java.util.List<com.tencent.bugly.crashreport.crash.C3648a> r11) {
        /*
            r8 = this;
            int r0 = r9.f588b
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x000b
            if (r0 != r1) goto L_0x0009
            goto L_0x000b
        L_0x0009:
            r3 = 0
            goto L_0x000c
        L_0x000b:
            r3 = 1
        L_0x000c:
            r4 = 3
            if (r0 != r4) goto L_0x0011
            r0 = 1
            goto L_0x0012
        L_0x0011:
            r0 = 0
        L_0x0012:
            boolean r4 = com.tencent.bugly.C3612b.f392c
            if (r4 != 0) goto L_0x001f
            if (r0 != 0) goto L_0x001c
            if (r3 != 0) goto L_0x001c
            r0 = 1
            goto L_0x0020
        L_0x001c:
            boolean r0 = com.tencent.bugly.crashreport.crash.C3678c.f679d
            goto L_0x0020
        L_0x001f:
            r0 = 0
        L_0x0020:
            if (r0 != 0) goto L_0x0023
            return r2
        L_0x0023:
            java.util.ArrayList r0 = new java.util.ArrayList
            r3 = 10
            r0.<init>(r3)
            boolean r10 = m504a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r9, (java.util.List<com.tencent.bugly.crashreport.crash.C3648a>) r10, (java.util.List<com.tencent.bugly.crashreport.crash.C3648a>) r0)
            if (r10 != 0) goto L_0x0038
            int r10 = r0.size()     // Catch:{ Exception -> 0x006d }
            int r3 = com.tencent.bugly.crashreport.crash.C3678c.f678c     // Catch:{ Exception -> 0x006d }
            if (r10 < r3) goto L_0x0078
        L_0x0038:
            java.lang.String r10 = "same crash occur too much do merged!"
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x006d }
            com.tencent.bugly.proguard.C3749y.m934a(r10, r3)     // Catch:{ Exception -> 0x006d }
            com.tencent.bugly.crashreport.crash.CrashDetailBean r9 = r8.m488a((java.util.List<com.tencent.bugly.crashreport.crash.C3648a>) r0, (com.tencent.bugly.crashreport.crash.CrashDetailBean) r9)     // Catch:{ Exception -> 0x006d }
            java.util.Iterator r10 = r0.iterator()     // Catch:{ Exception -> 0x006d }
        L_0x0047:
            boolean r0 = r10.hasNext()     // Catch:{ Exception -> 0x006d }
            if (r0 == 0) goto L_0x005f
            java.lang.Object r0 = r10.next()     // Catch:{ Exception -> 0x006d }
            com.tencent.bugly.crashreport.crash.a r0 = (com.tencent.bugly.crashreport.crash.C3648a) r0     // Catch:{ Exception -> 0x006d }
            long r3 = r0.f613a     // Catch:{ Exception -> 0x006d }
            long r5 = r9.f586a     // Catch:{ Exception -> 0x006d }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0047
            r11.add(r0)     // Catch:{ Exception -> 0x006d }
            goto L_0x0047
        L_0x005f:
            r8.mo24141e((com.tencent.bugly.crashreport.crash.CrashDetailBean) r9)     // Catch:{ Exception -> 0x006d }
            m516d((java.util.List<com.tencent.bugly.crashreport.crash.C3648a>) r11)     // Catch:{ Exception -> 0x006d }
            java.lang.String r9 = "[crash] save crash success. For this device crash many times, it will not upload crashes immediately"
            java.lang.Object[] r10 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x006d }
            com.tencent.bugly.proguard.C3749y.m937b(r9, r10)     // Catch:{ Exception -> 0x006d }
            return r1
        L_0x006d:
            r9 = move-exception
            com.tencent.bugly.proguard.C3749y.m935a(r9)
            java.lang.Object[] r9 = new java.lang.Object[r2]
            java.lang.String r10 = "Failed to merge crash."
            com.tencent.bugly.proguard.C3749y.m940d(r10, r9)
        L_0x0078:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C3662b.m513b(com.tencent.bugly.crashreport.crash.CrashDetailBean, java.util.List, java.util.List):boolean");
    }

    /* renamed from: a */
    public final List<CrashDetailBean> mo24134a() {
        StrategyBean c = C3644a.m426a().mo24103c();
        if (c == null) {
            C3749y.m940d("have not synced remote!", new Object[0]);
            return null;
        } else if (!c.f526e) {
            C3749y.m940d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            C3749y.m937b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            long b = C3695ab.m683b();
            List<C3648a> b2 = m507b();
            C3749y.m939c("Size of crash list loaded from DB: %s", Integer.valueOf(b2.size()));
            if (b2 == null || b2.size() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList.addAll(m490a(b2));
            b2.removeAll(arrayList);
            Iterator<C3648a> it = b2.iterator();
            while (it.hasNext()) {
                C3648a next = it.next();
                if (next.f614b < b - C3678c.f682g) {
                    arrayList2.add(next);
                    it.remove();
                    arrayList.add(next);
                } else if (next.f616d) {
                    if (next.f614b >= currentTimeMillis - 86400000) {
                        it.remove();
                    } else if (!next.f617e) {
                        it.remove();
                        arrayList.add(next);
                    }
                } else if (((long) next.f618f) >= 3 && next.f614b < currentTimeMillis - 86400000) {
                    it.remove();
                    arrayList.add(next);
                }
            }
            m512b((List<C3648a>) arrayList2);
            if (arrayList.size() > 0) {
                m516d((List<C3648a>) arrayList);
            }
            ArrayList arrayList3 = new ArrayList();
            List<CrashDetailBean> c2 = m514c(b2);
            if (c2 != null && c2.size() > 0) {
                String str = C3626a.m339b().f501i;
                Iterator<CrashDetailBean> it2 = c2.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean next2 = it2.next();
                    if (!str.equals(next2.f592f)) {
                        it2.remove();
                        arrayList3.add(next2);
                    }
                }
            }
            if (arrayList3.size() > 0) {
                m517e((List<CrashDetailBean>) arrayList3);
            }
            return c2;
        }
    }

    /* renamed from: b */
    private void m512b(List<C3648a> list) {
        List<CrashDetailBean> c = m514c(list);
        if (c != null && !c.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (CrashDetailBean next : c) {
                String str = f665k.get(Integer.valueOf(next.f588b));
                if (!TextUtils.isEmpty(str)) {
                    C3749y.m939c("find expired data,crashId:%s eventType:%s", next.f589c, str);
                    arrayList.add(new C3737t.C3741c(next.f589c, str, next.f604r, false, 0, "expired", (String) null));
                }
            }
            C3737t.m875a().mo24315a((List<C3737t.C3741c>) arrayList);
        }
    }

    /* renamed from: c */
    public final void mo24139c(CrashDetailBean crashDetailBean) {
        int i = crashDetailBean.f588b;
        if (i != 0) {
            if (i != 1) {
                if (i == 3 && !C3678c.m540a().mo24166r()) {
                    return;
                }
            } else if (!C3678c.m540a().mo24165q()) {
                return;
            }
        } else if (!C3678c.m540a().mo24165q()) {
            return;
        }
        if (this.f669e != null) {
            C3749y.m939c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
            int i2 = crashDetailBean.f588b;
        }
    }

    /* renamed from: a */
    public final void mo24135a(CrashDetailBean crashDetailBean, long j, boolean z) {
        if (C3678c.f687l) {
            C3749y.m934a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            mo24136a(arrayList, 3000, z, crashDetailBean.f588b == 7, z);
            return;
        }
        C3749y.m934a("do not upload spot crash right now, crash would be uploaded when app next start", new Object[0]);
    }

    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v11, types: [com.tencent.bugly.proguard.aj, com.tencent.bugly.proguard.ai] */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0253 A[Catch:{ all -> 0x02a5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x025b A[Catch:{ all -> 0x02a5 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo24136a(java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> r20, long r21, boolean r23, boolean r24, boolean r25) {
        /*
            r19 = this;
            r7 = r19
            android.content.Context r0 = r7.f666b
            com.tencent.bugly.crashreport.common.info.a r0 = com.tencent.bugly.crashreport.common.info.C3626a.m337a((android.content.Context) r0)
            boolean r0 = r0.f497e
            r8 = 0
            if (r0 != 0) goto L_0x0015
            java.lang.Object[] r0 = new java.lang.Object[r8]
            java.lang.String r1 = "warn: not upload process"
            com.tencent.bugly.proguard.C3749y.m940d(r1, r0)
            return
        L_0x0015:
            com.tencent.bugly.proguard.v r0 = r7.f667c
            if (r0 != 0) goto L_0x0021
            java.lang.Object[] r0 = new java.lang.Object[r8]
            java.lang.String r1 = "warn: upload manager is null"
            com.tencent.bugly.proguard.C3749y.m940d(r1, r0)
            return
        L_0x0021:
            if (r25 != 0) goto L_0x0033
            int r1 = com.tencent.bugly.crashreport.crash.C3678c.f676a
            boolean r0 = r0.mo24338b((int) r1)
            if (r0 != 0) goto L_0x0033
            java.lang.Object[] r0 = new java.lang.Object[r8]
            java.lang.String r1 = "warn: not crashHappen or not should upload"
            com.tencent.bugly.proguard.C3749y.m940d(r1, r0)
            return
        L_0x0033:
            com.tencent.bugly.crashreport.common.strategy.a r0 = r7.f668d
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r0 = r0.mo24103c()
            boolean r1 = r0.f526e
            if (r1 != 0) goto L_0x004c
            java.lang.Object[] r0 = new java.lang.Object[r8]
            java.lang.String r1 = "remote report is disable!"
            com.tencent.bugly.proguard.C3749y.m940d(r1, r0)
            java.lang.Object[] r0 = new java.lang.Object[r8]
            java.lang.String r1 = "[crash] server closed bugly in this app. please check your appid if is correct, and re-install it"
            com.tencent.bugly.proguard.C3749y.m937b(r1, r0)
            return
        L_0x004c:
            if (r20 == 0) goto L_0x02bf
            int r1 = r20.size()
            if (r1 != 0) goto L_0x0056
            goto L_0x02bf
        L_0x0056:
            r9 = 1
            java.lang.String r13 = r0.f538q     // Catch:{ all -> 0x02a5 }
            java.lang.String r14 = com.tencent.bugly.crashreport.common.strategy.StrategyBean.f523b     // Catch:{ all -> 0x02a5 }
            android.content.Context r0 = r7.f666b     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.crashreport.common.info.a r1 = com.tencent.bugly.crashreport.common.info.C3626a.m339b()     // Catch:{ all -> 0x02a5 }
            r2 = 0
            if (r0 == 0) goto L_0x0248
            if (r20 == 0) goto L_0x0248
            int r3 = r20.size()     // Catch:{ all -> 0x02a5 }
            if (r3 == 0) goto L_0x0248
            if (r1 != 0) goto L_0x0070
            goto L_0x0248
        L_0x0070:
            com.tencent.bugly.proguard.am r3 = new com.tencent.bugly.proguard.am     // Catch:{ all -> 0x02a5 }
            r3.<init>()     // Catch:{ all -> 0x02a5 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x02a5 }
            r4.<init>()     // Catch:{ all -> 0x02a5 }
            r3.f859a = r4     // Catch:{ all -> 0x02a5 }
            java.util.Iterator r4 = r20.iterator()     // Catch:{ all -> 0x02a5 }
        L_0x0080:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x02a5 }
            if (r5 == 0) goto L_0x0244
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.crashreport.crash.CrashDetailBean r5 = (com.tencent.bugly.crashreport.crash.CrashDetailBean) r5     // Catch:{ all -> 0x02a5 }
            java.util.ArrayList<com.tencent.bugly.proguard.al> r6 = r3.f859a     // Catch:{ all -> 0x02a5 }
            if (r0 == 0) goto L_0x0231
            if (r5 == 0) goto L_0x0231
            if (r1 != 0) goto L_0x0096
            goto L_0x0231
        L_0x0096:
            com.tencent.bugly.proguard.al r10 = new com.tencent.bugly.proguard.al     // Catch:{ all -> 0x02a5 }
            r10.<init>()     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = m519g(r5)     // Catch:{ all -> 0x02a5 }
            r10.f837a = r11     // Catch:{ all -> 0x02a5 }
            long r11 = r5.f604r     // Catch:{ all -> 0x02a5 }
            r10.f838b = r11     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = r5.f600n     // Catch:{ all -> 0x02a5 }
            r10.f839c = r11     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = r5.f601o     // Catch:{ all -> 0x02a5 }
            r10.f840d = r11     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = r5.f602p     // Catch:{ all -> 0x02a5 }
            r10.f841e = r11     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = r5.f603q     // Catch:{ all -> 0x02a5 }
            r10.f843g = r11     // Catch:{ all -> 0x02a5 }
            java.util.Map<java.lang.String, java.lang.String> r11 = r5.f612z     // Catch:{ all -> 0x02a5 }
            r10.f844h = r11     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = r5.f589c     // Catch:{ all -> 0x02a5 }
            r10.f845i = r11     // Catch:{ all -> 0x02a5 }
            r10.f846j = r2     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = r5.f599m     // Catch:{ all -> 0x02a5 }
            r10.f848l = r11     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = r5.f591e     // Catch:{ all -> 0x02a5 }
            r10.f849m = r11     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = r5.f561B     // Catch:{ all -> 0x02a5 }
            r10.f842f = r11     // Catch:{ all -> 0x02a5 }
            r10.f850n = r2     // Catch:{ all -> 0x02a5 }
            java.util.ArrayList r11 = m520h(r5)     // Catch:{ all -> 0x02a5 }
            r10.f852p = r11     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = "libInfo %s"
            java.lang.Object[] r12 = new java.lang.Object[r9]     // Catch:{ all -> 0x02a5 }
            java.util.ArrayList<com.tencent.bugly.proguard.ai> r15 = r10.f851o     // Catch:{ all -> 0x02a5 }
            r12[r8] = r15     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.proguard.C3749y.m939c(r11, r12)     // Catch:{ all -> 0x02a5 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x02a5 }
            r12 = 20
            r11.<init>(r12)     // Catch:{ all -> 0x02a5 }
            m496a((java.util.ArrayList<com.tencent.bugly.proguard.C3705ak>) r11, (com.tencent.bugly.crashreport.crash.CrashDetailBean) r5)     // Catch:{ all -> 0x02a5 }
            java.lang.String r12 = r5.f609w     // Catch:{ all -> 0x02a5 }
            m498a((java.util.ArrayList<com.tencent.bugly.proguard.C3705ak>) r11, (java.lang.String) r12)     // Catch:{ all -> 0x02a5 }
            java.lang.String r12 = r5.f610x     // Catch:{ all -> 0x02a5 }
            m510b((java.util.ArrayList<com.tencent.bugly.proguard.C3705ak>) r11, (java.lang.String) r12)     // Catch:{ all -> 0x02a5 }
            java.lang.String r12 = r5.f584Y     // Catch:{ all -> 0x02a5 }
            m515c(r11, r12)     // Catch:{ all -> 0x02a5 }
            java.lang.String r12 = r5.f585Z     // Catch:{ all -> 0x02a5 }
            m499a((java.util.ArrayList<com.tencent.bugly.proguard.C3705ak>) r11, (java.lang.String) r12, (android.content.Context) r0)     // Catch:{ all -> 0x02a5 }
            byte[] r12 = r5.f611y     // Catch:{ all -> 0x02a5 }
            m501a((java.util.ArrayList<com.tencent.bugly.proguard.C3705ak>) r11, (byte[]) r12)     // Catch:{ all -> 0x02a5 }
            m497a((java.util.ArrayList<com.tencent.bugly.proguard.C3705ak>) r11, (com.tencent.bugly.crashreport.crash.CrashDetailBean) r5, (android.content.Context) r0)     // Catch:{ all -> 0x02a5 }
            m509b((java.util.ArrayList<com.tencent.bugly.proguard.C3705ak>) r11, (com.tencent.bugly.crashreport.crash.CrashDetailBean) r5, (android.content.Context) r0)     // Catch:{ all -> 0x02a5 }
            java.util.List<java.lang.String> r12 = r1.f452B     // Catch:{ all -> 0x02a5 }
            m500a((java.util.ArrayList<com.tencent.bugly.proguard.C3705ak>) r11, (java.util.List<java.lang.String>) r12)     // Catch:{ all -> 0x02a5 }
            byte[] r12 = r5.f583X     // Catch:{ all -> 0x02a5 }
            m511b((java.util.ArrayList<com.tencent.bugly.proguard.C3705ak>) r11, (byte[]) r12)     // Catch:{ all -> 0x02a5 }
            r10.f853q = r11     // Catch:{ all -> 0x02a5 }
            boolean r11 = r5.f596j     // Catch:{ all -> 0x02a5 }
            if (r11 == 0) goto L_0x011b
            int r11 = r5.f606t     // Catch:{ all -> 0x02a5 }
            r10.f847k = r11     // Catch:{ all -> 0x02a5 }
        L_0x011b:
            java.util.Map r11 = m491a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r5, (com.tencent.bugly.crashreport.common.info.C3626a) r1)     // Catch:{ all -> 0x02a5 }
            r10.f854r = r11     // Catch:{ all -> 0x02a5 }
            java.util.HashMap r11 = new java.util.HashMap     // Catch:{ all -> 0x02a5 }
            r11.<init>()     // Catch:{ all -> 0x02a5 }
            r10.f855s = r11     // Catch:{ all -> 0x02a5 }
            java.util.Map<java.lang.String, java.lang.String> r11 = r5.f577R     // Catch:{ all -> 0x02a5 }
            if (r11 == 0) goto L_0x014e
            java.util.Map<java.lang.String, java.lang.String> r11 = r5.f577R     // Catch:{ all -> 0x02a5 }
            int r11 = r11.size()     // Catch:{ all -> 0x02a5 }
            if (r11 <= 0) goto L_0x014e
            java.util.Map<java.lang.String, java.lang.String> r11 = r10.f855s     // Catch:{ all -> 0x02a5 }
            java.util.Map<java.lang.String, java.lang.String> r12 = r5.f577R     // Catch:{ all -> 0x02a5 }
            r11.putAll(r12)     // Catch:{ all -> 0x02a5 }
            java.lang.String r11 = "setted message size %d"
            java.lang.Object[] r12 = new java.lang.Object[r9]     // Catch:{ all -> 0x02a5 }
            java.util.Map<java.lang.String, java.lang.String> r15 = r10.f855s     // Catch:{ all -> 0x02a5 }
            int r15 = r15.size()     // Catch:{ all -> 0x02a5 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x02a5 }
            r12[r8] = r15     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.proguard.C3749y.m934a(r11, r12)     // Catch:{ all -> 0x02a5 }
        L_0x014e:
            java.util.Map<java.lang.String, java.lang.String> r11 = r10.f855s     // Catch:{ all -> 0x02a5 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x02a5 }
            java.lang.String r15 = "pss:"
            r12.<init>(r15)     // Catch:{ all -> 0x02a5 }
            r15 = r3
            long r2 = r5.f568I     // Catch:{ all -> 0x02a5 }
            r12.append(r2)     // Catch:{ all -> 0x02a5 }
            java.lang.String r2 = " vss:"
            r12.append(r2)     // Catch:{ all -> 0x02a5 }
            long r2 = r5.f569J     // Catch:{ all -> 0x02a5 }
            r12.append(r2)     // Catch:{ all -> 0x02a5 }
            java.lang.String r2 = " javaHeap:"
            r12.append(r2)     // Catch:{ all -> 0x02a5 }
            long r2 = r5.f570K     // Catch:{ all -> 0x02a5 }
            r12.append(r2)     // Catch:{ all -> 0x02a5 }
            java.lang.String r2 = r12.toString()     // Catch:{ all -> 0x02a5 }
            java.lang.Object[] r3 = new java.lang.Object[r8]     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.proguard.C3749y.m939c(r2, r3)     // Catch:{ all -> 0x02a5 }
            java.lang.String r2 = "SDK_UPLOAD_U1"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02a5 }
            r3.<init>()     // Catch:{ all -> 0x02a5 }
            r12 = r10
            long r9 = r5.f568I     // Catch:{ all -> 0x02a5 }
            r3.append(r9)     // Catch:{ all -> 0x02a5 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x02a5 }
            r11.put(r2, r3)     // Catch:{ all -> 0x02a5 }
            java.lang.String r2 = "SDK_UPLOAD_U2"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02a5 }
            r3.<init>()     // Catch:{ all -> 0x02a5 }
            long r9 = r5.f569J     // Catch:{ all -> 0x02a5 }
            r3.append(r9)     // Catch:{ all -> 0x02a5 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x02a5 }
            r11.put(r2, r3)     // Catch:{ all -> 0x02a5 }
            java.lang.String r2 = "SDK_UPLOAD_U3"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02a5 }
            r3.<init>()     // Catch:{ all -> 0x02a5 }
            long r9 = r5.f570K     // Catch:{ all -> 0x02a5 }
            r3.append(r9)     // Catch:{ all -> 0x02a5 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x02a5 }
            r11.put(r2, r3)     // Catch:{ all -> 0x02a5 }
            java.lang.String r2 = "%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d"
            r3 = 12
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x02a5 }
            java.lang.String r9 = r5.f600n     // Catch:{ all -> 0x02a5 }
            r3[r8] = r9     // Catch:{ all -> 0x02a5 }
            java.lang.String r9 = r5.f589c     // Catch:{ all -> 0x02a5 }
            r10 = 1
            r3[r10] = r9     // Catch:{ all -> 0x02a5 }
            r9 = 2
            java.lang.String r10 = r1.mo24068e()     // Catch:{ all -> 0x02a5 }
            r3[r9] = r10     // Catch:{ all -> 0x02a5 }
            long r10 = r5.f604r     // Catch:{ all -> 0x02a5 }
            long r8 = r5.f575P     // Catch:{ all -> 0x02a5 }
            long r10 = r10 - r8
            r8 = 1000(0x3e8, double:4.94E-321)
            long r10 = r10 / r8
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x02a5 }
            r9 = 3
            r3[r9] = r8     // Catch:{ all -> 0x02a5 }
            r8 = 4
            boolean r9 = r5.f597k     // Catch:{ all -> 0x02a5 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ all -> 0x02a5 }
            r3[r8] = r9     // Catch:{ all -> 0x02a5 }
            r8 = 5
            boolean r9 = r5.f576Q     // Catch:{ all -> 0x02a5 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ all -> 0x02a5 }
            r3[r8] = r9     // Catch:{ all -> 0x02a5 }
            r8 = 6
            boolean r9 = r5.f596j     // Catch:{ all -> 0x02a5 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch:{ all -> 0x02a5 }
            r3[r8] = r9     // Catch:{ all -> 0x02a5 }
            r8 = 7
            int r9 = r5.f588b     // Catch:{ all -> 0x02a5 }
            r10 = 1
            if (r9 != r10) goto L_0x01fc
            r10 = 1
            goto L_0x01fd
        L_0x01fc:
            r10 = 0
        L_0x01fd:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x02a5 }
            r3[r8] = r9     // Catch:{ all -> 0x02a5 }
            r8 = 8
            int r9 = r5.f606t     // Catch:{ all -> 0x02a5 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x02a5 }
            r3[r8] = r9     // Catch:{ all -> 0x02a5 }
            r8 = 9
            java.lang.String r9 = r5.f605s     // Catch:{ all -> 0x02a5 }
            r3[r8] = r9     // Catch:{ all -> 0x02a5 }
            r8 = 10
            boolean r5 = r5.f590d     // Catch:{ all -> 0x02a5 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x02a5 }
            r3[r8] = r5     // Catch:{ all -> 0x02a5 }
            r5 = 11
            r8 = r12
            java.util.Map<java.lang.String, java.lang.String> r9 = r8.f854r     // Catch:{ all -> 0x02a5 }
            int r9 = r9.size()     // Catch:{ all -> 0x02a5 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x02a5 }
            r3[r5] = r9     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.proguard.C3749y.m939c(r2, r3)     // Catch:{ all -> 0x02a5 }
            r10 = r8
            goto L_0x023b
        L_0x0231:
            r15 = r3
            java.lang.String r2 = "enExp args == null"
            r3 = 0
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.proguard.C3749y.m940d(r2, r5)     // Catch:{ all -> 0x02a5 }
            r10 = 0
        L_0x023b:
            r6.add(r10)     // Catch:{ all -> 0x02a5 }
            r3 = r15
            r2 = 0
            r8 = 0
            r9 = 1
            goto L_0x0080
        L_0x0244:
            r15 = r3
            r2 = r15
            r1 = 0
            goto L_0x0251
        L_0x0248:
            java.lang.String r0 = "enEXPPkg args == null!"
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r2)     // Catch:{ all -> 0x02a5 }
            r2 = 0
        L_0x0251:
            if (r2 != 0) goto L_0x025b
            java.lang.String r0 = "create eupPkg fail!"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r2)     // Catch:{ all -> 0x02a5 }
            return
        L_0x025b:
            byte[] r0 = com.tencent.bugly.proguard.C3691a.m628a((com.tencent.bugly.proguard.C3723j) r2)     // Catch:{ all -> 0x02a5 }
            if (r0 != 0) goto L_0x026a
            java.lang.String r0 = "send encode fail!"
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r2)     // Catch:{ all -> 0x02a5 }
            return
        L_0x026a:
            android.content.Context r1 = r7.f666b     // Catch:{ all -> 0x02a5 }
            r2 = 830(0x33e, float:1.163E-42)
            com.tencent.bugly.proguard.an r12 = com.tencent.bugly.proguard.C3691a.m619a(r1, r2, r0)     // Catch:{ all -> 0x02a5 }
            if (r12 != 0) goto L_0x027d
            java.lang.String r0 = "request package is null."
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r2)     // Catch:{ all -> 0x02a5 }
            return
        L_0x027d:
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x02a5 }
            com.tencent.bugly.crashreport.crash.b$6 r15 = new com.tencent.bugly.crashreport.crash.b$6     // Catch:{ all -> 0x02a5 }
            r1 = r15
            r2 = r19
            r5 = r20
            r6 = r23
            r1.<init>(r3, r5, r6)     // Catch:{ all -> 0x02a5 }
            if (r23 == 0) goto L_0x029b
            com.tencent.bugly.proguard.v r10 = r7.f667c     // Catch:{ all -> 0x02a5 }
            int r11 = f660a     // Catch:{ all -> 0x02a5 }
            r16 = r21
            r18 = r24
            r10.mo24335a(r11, r12, r13, r14, r15, r16, r18)     // Catch:{ all -> 0x02a5 }
            goto L_0x02be
        L_0x029b:
            com.tencent.bugly.proguard.v r10 = r7.f667c     // Catch:{ all -> 0x02a5 }
            int r11 = f660a     // Catch:{ all -> 0x02a5 }
            r16 = 0
            r10.mo24336a(r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x02a5 }
            return
        L_0x02a5:
            r0 = move-exception
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = r0.toString()
            r3 = 0
            r1[r3] = r2
            java.lang.String r2 = "req cr error %s"
            com.tencent.bugly.proguard.C3749y.m941e(r2, r1)
            boolean r1 = com.tencent.bugly.proguard.C3749y.m938b(r0)
            if (r1 != 0) goto L_0x02be
            r0.printStackTrace()
        L_0x02be:
            return
        L_0x02bf:
            r3 = 0
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r1 = "warn: crashList is null or crashList num is 0"
            com.tencent.bugly.proguard.C3749y.m940d(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C3662b.mo24136a(java.util.List, long, boolean, boolean, boolean):void");
    }

    /* renamed from: a */
    public static void m503a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            C3749y.m939c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean next : list) {
                C3749y.m939c("pre uid:%s uc:%d re:%b me:%b", next.f589c, Integer.valueOf(next.f598l), Boolean.valueOf(next.f590d), Boolean.valueOf(next.f596j));
                next.f598l++;
                next.f590d = z;
                C3749y.m939c("set uid:%s uc:%d re:%b me:%b", next.f589c, Integer.valueOf(next.f598l), Boolean.valueOf(next.f590d), Boolean.valueOf(next.f596j));
            }
            for (CrashDetailBean a : list) {
                C3678c.m540a().mo24146a(a);
            }
            C3749y.m939c("update state size %d", Integer.valueOf(list.size()));
        }
        if (!z) {
            C3749y.m937b("[crash] upload fail.", new Object[0]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c1 A[Catch:{ all -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d7 A[Catch:{ all -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f0 A[Catch:{ all -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f8 A[Catch:{ all -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x012e A[Catch:{ all -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo24140d(com.tencent.bugly.crashreport.crash.CrashDetailBean r9) {
        /*
            r8 = this;
            if (r9 != 0) goto L_0x0003
            return
        L_0x0003:
            com.tencent.bugly.BuglyStrategy$a r0 = r8.f670f
            if (r0 != 0) goto L_0x000c
            com.tencent.bugly.proguard.n r0 = r8.f669e
            if (r0 != 0) goto L_0x000c
            return
        L_0x000c:
            java.util.ArrayList<com.tencent.bugly.crashreport.crash.b$a> r0 = f662h
            java.util.Iterator r0 = r0.iterator()
        L_0x0012:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x002a
            java.lang.Object r1 = r0.next()
            com.tencent.bugly.crashreport.crash.b$a r1 = (com.tencent.bugly.crashreport.crash.C3662b.C3669a) r1
            boolean r3 = com.tencent.bugly.crashreport.crash.C3662b.C3669a.m530a(r1, r9)
            if (r3 == 0) goto L_0x0012
            boolean r0 = r1.mo24142a()
            goto L_0x002b
        L_0x002a:
            r0 = 0
        L_0x002b:
            if (r0 != 0) goto L_0x0035
            java.lang.Object[] r9 = new java.lang.Object[r2]
            java.lang.String r0 = "Should not call back."
            com.tencent.bugly.proguard.C3749y.m939c(r0, r9)
            return
        L_0x0035:
            r0 = 1
            java.util.Map<java.lang.Integer, java.lang.Integer> r1 = f663i     // Catch:{ all -> 0x0159 }
            int r3 = r9.f588b     // Catch:{ all -> 0x0159 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0159 }
            boolean r3 = r1.containsKey(r3)     // Catch:{ all -> 0x0159 }
            if (r3 != 0) goto L_0x005a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = "Cannot get crash type for crashBean type:"
            r1.<init>(r3)     // Catch:{ all -> 0x0159 }
            int r9 = r9.f588b     // Catch:{ all -> 0x0159 }
            r1.append(r9)     // Catch:{ all -> 0x0159 }
            java.lang.String r9 = r1.toString()     // Catch:{ all -> 0x0159 }
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m940d(r9, r1)     // Catch:{ all -> 0x0159 }
            return
        L_0x005a:
            int r3 = r9.f588b     // Catch:{ all -> 0x0159 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0159 }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ all -> 0x0159 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x0159 }
            int r1 = r1.intValue()     // Catch:{ all -> 0x0159 }
            int r3 = r9.f588b     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.n r3 = r8.f669e     // Catch:{ all -> 0x0159 }
            r4 = 0
            if (r3 == 0) goto L_0x009a
            java.lang.String r3 = "Calling 'onCrashHandleStart' of RQD crash listener."
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m939c(r3, r5)     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = "Calling 'getCrashExtraMessage' of RQD crash listener."
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m939c(r3, r5)     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.n r3 = r8.f669e     // Catch:{ all -> 0x0159 }
            java.lang.String r5 = r9.f600n     // Catch:{ all -> 0x0159 }
            java.lang.String r5 = r9.f602p     // Catch:{ all -> 0x0159 }
            java.lang.String r5 = r9.f603q     // Catch:{ all -> 0x0159 }
            long r5 = r9.f604r     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r3.mo24289b()     // Catch:{ all -> 0x0159 }
            if (r3 == 0) goto L_0x00b2
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ all -> 0x0159 }
            r5.<init>(r0)     // Catch:{ all -> 0x0159 }
            java.lang.String r6 = "userData"
            r5.put(r6, r3)     // Catch:{ all -> 0x0159 }
            goto L_0x00b3
        L_0x009a:
            com.tencent.bugly.BuglyStrategy$a r3 = r8.f670f     // Catch:{ all -> 0x0159 }
            if (r3 == 0) goto L_0x00b2
            java.lang.String r3 = "Calling 'onCrashHandleStart' of Bugly crash listener."
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m939c(r3, r5)     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.BuglyStrategy$a r3 = r8.f670f     // Catch:{ all -> 0x0159 }
            java.lang.String r5 = r9.f600n     // Catch:{ all -> 0x0159 }
            java.lang.String r6 = r9.f601o     // Catch:{ all -> 0x0159 }
            java.lang.String r7 = r9.f603q     // Catch:{ all -> 0x0159 }
            java.util.Map r5 = r3.onCrashHandleStart(r1, r5, r6, r7)     // Catch:{ all -> 0x0159 }
            goto L_0x00b3
        L_0x00b2:
            r5 = r4
        L_0x00b3:
            m493a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r9, (java.util.Map<java.lang.String, java.lang.String>) r5)     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = "[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()"
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m934a(r3, r5)     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.n r3 = r8.f669e     // Catch:{ all -> 0x0159 }
            if (r3 == 0) goto L_0x00d7
            java.lang.String r1 = "Calling 'getCrashExtraData' of RQD crash listener."
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m939c(r1, r3)     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.n r1 = r8.f669e     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f600n     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f602p     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f603q     // Catch:{ all -> 0x0159 }
            long r3 = r9.f604r     // Catch:{ all -> 0x0159 }
            byte[] r4 = r1.mo24288a()     // Catch:{ all -> 0x0159 }
            goto L_0x00ee
        L_0x00d7:
            com.tencent.bugly.BuglyStrategy$a r3 = r8.f670f     // Catch:{ all -> 0x0159 }
            if (r3 == 0) goto L_0x00ee
            java.lang.String r3 = "Calling 'onCrashHandleStart2GetExtraDatas' of Bugly crash listener."
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m939c(r3, r4)     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.BuglyStrategy$a r3 = r8.f670f     // Catch:{ all -> 0x0159 }
            java.lang.String r4 = r9.f600n     // Catch:{ all -> 0x0159 }
            java.lang.String r5 = r9.f601o     // Catch:{ all -> 0x0159 }
            java.lang.String r6 = r9.f603q     // Catch:{ all -> 0x0159 }
            byte[] r4 = r3.onCrashHandleStart2GetExtraDatas(r1, r4, r5, r6)     // Catch:{ all -> 0x0159 }
        L_0x00ee:
            if (r4 != 0) goto L_0x00f8
            java.lang.String r1 = "extra user byte is null. CrashBean won't have userExtraByteDatas."
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m940d(r1, r3)     // Catch:{ all -> 0x0159 }
            goto L_0x012a
        L_0x00f8:
            int r1 = r4.length     // Catch:{ all -> 0x0159 }
            r3 = 100000(0x186a0, float:1.4013E-40)
            if (r1 > r3) goto L_0x0101
            r9.f583X = r4     // Catch:{ all -> 0x0159 }
            goto L_0x011c
        L_0x0101:
            java.lang.String r1 = "extra bytes size %d is over limit %d will drop over part"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0159 }
            int r6 = r4.length     // Catch:{ all -> 0x0159 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0159 }
            r5[r2] = r6     // Catch:{ all -> 0x0159 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0159 }
            r5[r0] = r6     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m940d(r1, r5)     // Catch:{ all -> 0x0159 }
            byte[] r1 = java.util.Arrays.copyOf(r4, r3)     // Catch:{ all -> 0x0159 }
            r9.f583X = r1     // Catch:{ all -> 0x0159 }
        L_0x011c:
            java.lang.String r1 = "add extra bytes %d "
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ all -> 0x0159 }
            int r4 = r4.length     // Catch:{ all -> 0x0159 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0159 }
            r3[r2] = r4     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m934a(r1, r3)     // Catch:{ all -> 0x0159 }
        L_0x012a:
            com.tencent.bugly.proguard.n r1 = r8.f669e     // Catch:{ all -> 0x0159 }
            if (r1 == 0) goto L_0x0158
            java.lang.String r1 = "Calling 'onCrashSaving' of RQD crash listener."
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m939c(r1, r3)     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.n r1 = r8.f669e     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f600n     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f601o     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f602p     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f603q     // Catch:{ all -> 0x0159 }
            long r3 = r9.f604r     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f599m     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f591e     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f589c     // Catch:{ all -> 0x0159 }
            java.lang.String r3 = r9.f560A     // Catch:{ all -> 0x0159 }
            java.lang.String r9 = r9.f561B     // Catch:{ all -> 0x0159 }
            boolean r9 = r1.mo24290c()     // Catch:{ all -> 0x0159 }
            if (r9 != 0) goto L_0x0158
            java.lang.String r9 = "Crash listener 'onCrashSaving' return 'false' thus will not handle this crash."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0159 }
            com.tencent.bugly.proguard.C3749y.m940d(r9, r1)     // Catch:{ all -> 0x0159 }
        L_0x0158:
            return
        L_0x0159:
            r9 = move-exception
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.Class r1 = r9.getClass()
            java.lang.String r1 = r1.getName()
            r0[r2] = r1
            java.lang.String r1 = "crash handle callback something wrong! %s"
            com.tencent.bugly.proguard.C3749y.m940d(r1, r0)
            boolean r0 = com.tencent.bugly.proguard.C3749y.m935a(r9)
            if (r0 != 0) goto L_0x0174
            r9.printStackTrace()
        L_0x0174:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C3662b.mo24140d(com.tencent.bugly.crashreport.crash.CrashDetailBean):void");
    }

    /* renamed from: f */
    private static ContentValues m518f(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.f586a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.f586a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.f604r));
            contentValues.put("_s1", crashDetailBean.f607u);
            int i = 1;
            contentValues.put("_up", Integer.valueOf(crashDetailBean.f590d ? 1 : 0));
            if (!crashDetailBean.f596j) {
                i = 0;
            }
            contentValues.put("_me", Integer.valueOf(i));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.f598l));
            contentValues.put("_dt", C3695ab.m680a((Parcelable) crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static CrashDetailBean m487a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) C3695ab.m658a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.f586a = j;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: e */
    public final void mo24141e(CrashDetailBean crashDetailBean) {
        ContentValues f;
        if (crashDetailBean != null && (f = m518f(crashDetailBean)) != null) {
            long a = C3730o.m839a().mo24292a("t_cr", f, (C3729n) null, true);
            if (a >= 0) {
                C3749y.m939c("insert %s success!", "t_cr");
                crashDetailBean.f586a = a;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f8 A[Catch:{ all -> 0x0101 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00fd A[DONT_GENERATE] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> m514c(java.util.List<com.tencent.bugly.crashreport.crash.C3648a> r15) {
        /*
            r14 = this;
            r0 = 0
            if (r15 == 0) goto L_0x0108
            int r1 = r15.size()
            if (r1 != 0) goto L_0x000b
            goto L_0x0108
        L_0x000b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "_id in "
            r1.append(r2)
            java.lang.String r3 = "("
            r1.append(r3)
            java.util.Iterator r15 = r15.iterator()
        L_0x001e:
            boolean r4 = r15.hasNext()
            java.lang.String r5 = ","
            if (r4 == 0) goto L_0x0035
            java.lang.Object r4 = r15.next()
            com.tencent.bugly.crashreport.crash.a r4 = (com.tencent.bugly.crashreport.crash.C3648a) r4
            long r6 = r4.f613a
            r1.append(r6)
            r1.append(r5)
            goto L_0x001e
        L_0x0035:
            java.lang.String r15 = r1.toString()
            boolean r15 = r15.contains(r5)
            r4 = 0
            if (r15 == 0) goto L_0x004e
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            int r6 = r1.lastIndexOf(r5)
            java.lang.String r1 = r1.substring(r4, r6)
            r15.<init>(r1)
            r1 = r15
        L_0x004e:
            java.lang.String r15 = ")"
            r1.append(r15)
            java.lang.String r9 = r1.toString()
            r1.setLength(r4)
            com.tencent.bugly.proguard.o r6 = com.tencent.bugly.proguard.C3730o.m839a()     // Catch:{ all -> 0x00f0 }
            java.lang.String r7 = "t_cr"
            r8 = 0
            r10 = 0
            r11 = 0
            r12 = 1
            android.database.Cursor r6 = r6.mo24293a(r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x00f0 }
            if (r6 != 0) goto L_0x0070
            if (r6 == 0) goto L_0x006f
            r6.close()
        L_0x006f:
            return r0
        L_0x0070:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x00ee }
            r7.<init>()     // Catch:{ all -> 0x00ee }
            r1.append(r2)     // Catch:{ all -> 0x00ee }
            r1.append(r3)     // Catch:{ all -> 0x00ee }
            r2 = 0
        L_0x007c:
            boolean r3 = r6.moveToNext()     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x00a7
            com.tencent.bugly.crashreport.crash.CrashDetailBean r3 = m487a((android.database.Cursor) r6)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x008c
            r7.add(r3)     // Catch:{ all -> 0x00ee }
            goto L_0x007c
        L_0x008c:
            java.lang.String r3 = "_id"
            int r3 = r6.getColumnIndex(r3)     // Catch:{ all -> 0x009f }
            long r8 = r6.getLong(r3)     // Catch:{ all -> 0x009f }
            r1.append(r8)     // Catch:{ all -> 0x009f }
            r1.append(r5)     // Catch:{ all -> 0x009f }
            int r2 = r2 + 1
            goto L_0x007c
        L_0x009f:
            java.lang.String r3 = "unknown id!"
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ all -> 0x00ee }
            com.tencent.bugly.proguard.C3749y.m940d(r3, r8)     // Catch:{ all -> 0x00ee }
            goto L_0x007c
        L_0x00a7:
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x00ee }
            boolean r3 = r3.contains(r5)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x00bf
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ee }
            int r5 = r1.lastIndexOf(r5)     // Catch:{ all -> 0x00ee }
            java.lang.String r1 = r1.substring(r4, r5)     // Catch:{ all -> 0x00ee }
            r3.<init>(r1)     // Catch:{ all -> 0x00ee }
            r1 = r3
        L_0x00bf:
            r1.append(r15)     // Catch:{ all -> 0x00ee }
            java.lang.String r10 = r1.toString()     // Catch:{ all -> 0x00ee }
            if (r2 <= 0) goto L_0x00e8
            com.tencent.bugly.proguard.o r8 = com.tencent.bugly.proguard.C3730o.m839a()     // Catch:{ all -> 0x00ee }
            java.lang.String r9 = "t_cr"
            r11 = 0
            r12 = 0
            r13 = 1
            int r15 = r8.mo24291a((java.lang.String) r9, (java.lang.String) r10, (java.lang.String[]) r11, (com.tencent.bugly.proguard.C3729n) r12, (boolean) r13)     // Catch:{ all -> 0x00ee }
            java.lang.String r1 = "deleted %s illegal data %d"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00ee }
            java.lang.String r3 = "t_cr"
            r2[r4] = r3     // Catch:{ all -> 0x00ee }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x00ee }
            r3 = 1
            r2[r3] = r15     // Catch:{ all -> 0x00ee }
            com.tencent.bugly.proguard.C3749y.m940d(r1, r2)     // Catch:{ all -> 0x00ee }
        L_0x00e8:
            if (r6 == 0) goto L_0x00ed
            r6.close()
        L_0x00ed:
            return r7
        L_0x00ee:
            r15 = move-exception
            goto L_0x00f2
        L_0x00f0:
            r15 = move-exception
            r6 = r0
        L_0x00f2:
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r15)     // Catch:{ all -> 0x0101 }
            if (r1 != 0) goto L_0x00fb
            r15.printStackTrace()     // Catch:{ all -> 0x0101 }
        L_0x00fb:
            if (r6 == 0) goto L_0x0100
            r6.close()
        L_0x0100:
            return r0
        L_0x0101:
            r15 = move-exception
            if (r6 == 0) goto L_0x0107
            r6.close()
        L_0x0107:
            throw r15
        L_0x0108:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C3662b.m514c(java.util.List):java.util.List");
    }

    /* renamed from: b */
    private static C3648a m506b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C3648a aVar = new C3648a();
            aVar.f613a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.f614b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.f615c = cursor.getString(cursor.getColumnIndex("_s1"));
            boolean z = false;
            aVar.f616d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) == 1) {
                z = true;
            }
            aVar.f617e = z;
            aVar.f618f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.List<com.tencent.bugly.crashreport.crash.a>] */
    /* JADX WARNING: type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cc A[Catch:{ all -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d1 A[DONT_GENERATE] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.tencent.bugly.crashreport.crash.C3648a> m507b() {
        /*
            r16 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.lang.String r3 = "_id"
            java.lang.String r4 = "_tm"
            java.lang.String r5 = "_s1"
            java.lang.String r6 = "_up"
            java.lang.String r7 = "_me"
            java.lang.String r8 = "_uc"
            java.lang.String[] r11 = new java.lang.String[]{r3, r4, r5, r6, r7, r8}     // Catch:{ all -> 0x00c5 }
            com.tencent.bugly.proguard.o r9 = com.tencent.bugly.proguard.C3730o.m839a()     // Catch:{ all -> 0x00c5 }
            java.lang.String r10 = "t_cr"
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 1
            android.database.Cursor r3 = r9.mo24293a(r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x00c5 }
            if (r3 != 0) goto L_0x002c
            if (r3 == 0) goto L_0x002b
            r3.close()
        L_0x002b:
            return r2
        L_0x002c:
            int r0 = r3.getCount()     // Catch:{ all -> 0x00c2 }
            if (r0 > 0) goto L_0x0038
            if (r3 == 0) goto L_0x0037
            r3.close()
        L_0x0037:
            return r1
        L_0x0038:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c2 }
            r0.<init>()     // Catch:{ all -> 0x00c2 }
            java.lang.String r2 = "_id in "
            r0.append(r2)     // Catch:{ all -> 0x00c2 }
            java.lang.String r2 = "("
            r0.append(r2)     // Catch:{ all -> 0x00c2 }
            r2 = 0
            r4 = 0
        L_0x0049:
            boolean r5 = r3.moveToNext()     // Catch:{ all -> 0x00c2 }
            java.lang.String r6 = ","
            if (r5 == 0) goto L_0x0076
            com.tencent.bugly.crashreport.crash.a r5 = m506b((android.database.Cursor) r3)     // Catch:{ all -> 0x00c2 }
            if (r5 == 0) goto L_0x005b
            r1.add(r5)     // Catch:{ all -> 0x00c2 }
            goto L_0x0049
        L_0x005b:
            java.lang.String r5 = "_id"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ all -> 0x006e }
            long r7 = r3.getLong(r5)     // Catch:{ all -> 0x006e }
            r0.append(r7)     // Catch:{ all -> 0x006e }
            r0.append(r6)     // Catch:{ all -> 0x006e }
            int r4 = r4 + 1
            goto L_0x0049
        L_0x006e:
            java.lang.String r5 = "unknown id!"
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x00c2 }
            com.tencent.bugly.proguard.C3749y.m940d(r5, r6)     // Catch:{ all -> 0x00c2 }
            goto L_0x0049
        L_0x0076:
            java.lang.String r5 = r0.toString()     // Catch:{ all -> 0x00c2 }
            boolean r5 = r5.contains(r6)     // Catch:{ all -> 0x00c2 }
            if (r5 == 0) goto L_0x008e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c2 }
            int r6 = r0.lastIndexOf(r6)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = r0.substring(r2, r6)     // Catch:{ all -> 0x00c2 }
            r5.<init>(r0)     // Catch:{ all -> 0x00c2 }
            r0 = r5
        L_0x008e:
            java.lang.String r5 = ")"
            r0.append(r5)     // Catch:{ all -> 0x00c2 }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x00c2 }
            r0.setLength(r2)     // Catch:{ all -> 0x00c2 }
            if (r4 <= 0) goto L_0x00bc
            com.tencent.bugly.proguard.o r6 = com.tencent.bugly.proguard.C3730o.m839a()     // Catch:{ all -> 0x00c2 }
            java.lang.String r7 = "t_cr"
            r9 = 0
            r10 = 0
            r11 = 1
            int r0 = r6.mo24291a((java.lang.String) r7, (java.lang.String) r8, (java.lang.String[]) r9, (com.tencent.bugly.proguard.C3729n) r10, (boolean) r11)     // Catch:{ all -> 0x00c2 }
            java.lang.String r4 = "deleted %s illegal data %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00c2 }
            java.lang.String r6 = "t_cr"
            r5[r2] = r6     // Catch:{ all -> 0x00c2 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00c2 }
            r2 = 1
            r5[r2] = r0     // Catch:{ all -> 0x00c2 }
            com.tencent.bugly.proguard.C3749y.m940d(r4, r5)     // Catch:{ all -> 0x00c2 }
        L_0x00bc:
            if (r3 == 0) goto L_0x00c1
            r3.close()
        L_0x00c1:
            return r1
        L_0x00c2:
            r0 = move-exception
            r2 = r3
            goto L_0x00c6
        L_0x00c5:
            r0 = move-exception
        L_0x00c6:
            boolean r3 = com.tencent.bugly.proguard.C3749y.m935a(r0)     // Catch:{ all -> 0x00d5 }
            if (r3 != 0) goto L_0x00cf
            r0.printStackTrace()     // Catch:{ all -> 0x00d5 }
        L_0x00cf:
            if (r2 == 0) goto L_0x00d4
            r2.close()
        L_0x00d4:
            return r1
        L_0x00d5:
            r0 = move-exception
            if (r2 == 0) goto L_0x00db
            r2.close()
        L_0x00db:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C3662b.m507b():java.util.List");
    }

    /* renamed from: d */
    private static void m516d(List<C3648a> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("_id in ");
            sb.append("(");
            for (C3648a aVar : list) {
                sb.append(aVar.f613a);
                sb.append(CSVUtil.COLUMN_SEPARATOR);
            }
            StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(CSVUtil.COLUMN_SEPARATOR)));
            sb2.append(")");
            String sb3 = sb2.toString();
            sb2.setLength(0);
            try {
                C3749y.m939c("deleted %s data %d", "t_cr", Integer.valueOf(C3730o.m839a().mo24291a("t_cr", sb3, (String[]) null, (C3729n) null, true)));
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: e */
    private static void m517e(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (CrashDetailBean crashDetailBean : list) {
                        sb.append(" or _id");
                        sb.append(" = ");
                        sb.append(crashDetailBean.f586a);
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        sb2 = sb2.substring(4);
                    }
                    sb.setLength(0);
                    C3749y.m939c("deleted %s data %d", "t_cr", Integer.valueOf(C3730o.m839a().mo24291a("t_cr", sb2, (String[]) null, (C3729n) null, true)));
                }
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0090 A[Catch:{ all -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0095 A[SYNTHETIC, Splitter:B:35:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a9 A[DONT_GENERATE] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.tencent.bugly.proguard.C3705ak m489a(java.lang.String r6, android.content.Context r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "del tmp"
            r1 = 0
            r2 = 0
            if (r8 == 0) goto L_0x00d2
            if (r7 != 0) goto L_0x000a
            goto L_0x00d2
        L_0x000a:
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r4[r2] = r8
            java.lang.String r5 = "zip %s"
            com.tencent.bugly.proguard.C3749y.m939c(r5, r4)
            java.io.File r4 = new java.io.File
            r4.<init>(r8)
            java.io.File r8 = new java.io.File
            java.io.File r7 = r7.getCacheDir()
            r8.<init>(r7, r6)
            r6 = 5000(0x1388, float:7.006E-42)
            boolean r6 = com.tencent.bugly.proguard.C3695ab.m677a((java.io.File) r4, (java.io.File) r8, (int) r6)
            if (r6 != 0) goto L_0x0032
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r7 = "zip fail!"
            com.tencent.bugly.proguard.C3749y.m940d(r7, r6)
            return r1
        L_0x0032:
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream
            r6.<init>()
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ all -> 0x0088 }
            r7.<init>(r8)     // Catch:{ all -> 0x0088 }
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x0086 }
        L_0x0040:
            int r5 = r7.read(r4)     // Catch:{ all -> 0x0086 }
            if (r5 <= 0) goto L_0x004d
            r6.write(r4, r2, r5)     // Catch:{ all -> 0x0086 }
            r6.flush()     // Catch:{ all -> 0x0086 }
            goto L_0x0040
        L_0x004d:
            byte[] r6 = r6.toByteArray()     // Catch:{ all -> 0x0086 }
            java.lang.String r4 = "read bytes :%d"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0086 }
            int r5 = r6.length     // Catch:{ all -> 0x0086 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0086 }
            r3[r2] = r5     // Catch:{ all -> 0x0086 }
            com.tencent.bugly.proguard.C3749y.m939c(r4, r3)     // Catch:{ all -> 0x0086 }
            com.tencent.bugly.proguard.ak r3 = new com.tencent.bugly.proguard.ak     // Catch:{ all -> 0x0086 }
            r4 = 2
            java.lang.String r5 = r8.getName()     // Catch:{ all -> 0x0086 }
            r3.<init>(r4, r5, r6)     // Catch:{ all -> 0x0086 }
            r7.close()     // Catch:{ IOException -> 0x006d }
            goto L_0x0077
        L_0x006d:
            r6 = move-exception
            boolean r7 = com.tencent.bugly.proguard.C3749y.m935a(r6)
            if (r7 != 0) goto L_0x0077
            r6.printStackTrace()
        L_0x0077:
            boolean r6 = r8.exists()
            if (r6 == 0) goto L_0x0085
            java.lang.Object[] r6 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.C3749y.m939c(r0, r6)
            r8.delete()
        L_0x0085:
            return r3
        L_0x0086:
            r6 = move-exception
            goto L_0x008a
        L_0x0088:
            r6 = move-exception
            r7 = r1
        L_0x008a:
            boolean r3 = com.tencent.bugly.proguard.C3749y.m935a(r6)     // Catch:{ all -> 0x00b2 }
            if (r3 != 0) goto L_0x0093
            r6.printStackTrace()     // Catch:{ all -> 0x00b2 }
        L_0x0093:
            if (r7 == 0) goto L_0x00a3
            r7.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x00a3
        L_0x0099:
            r6 = move-exception
            boolean r7 = com.tencent.bugly.proguard.C3749y.m935a(r6)
            if (r7 != 0) goto L_0x00a3
            r6.printStackTrace()
        L_0x00a3:
            boolean r6 = r8.exists()
            if (r6 == 0) goto L_0x00b1
            java.lang.Object[] r6 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.C3749y.m939c(r0, r6)
            r8.delete()
        L_0x00b1:
            return r1
        L_0x00b2:
            r6 = move-exception
            if (r7 == 0) goto L_0x00c3
            r7.close()     // Catch:{ IOException -> 0x00b9 }
            goto L_0x00c3
        L_0x00b9:
            r7 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r7)
            if (r1 != 0) goto L_0x00c3
            r7.printStackTrace()
        L_0x00c3:
            boolean r7 = r8.exists()
            if (r7 == 0) goto L_0x00d1
            java.lang.Object[] r7 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.C3749y.m939c(r0, r7)
            r8.delete()
        L_0x00d1:
            throw r6
        L_0x00d2:
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r7 = "rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}"
            com.tencent.bugly.proguard.C3749y.m940d(r7, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C3662b.m489a(java.lang.String, android.content.Context, java.lang.String):com.tencent.bugly.proguard.ak");
    }

    /* renamed from: a */
    public static void m495a(String str, String str2, String str3, String str4, String str5, CrashDetailBean crashDetailBean) {
        String str6;
        C3626a b = C3626a.m339b();
        if (b != null) {
            C3749y.m941e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
            C3749y.m941e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
            C3749y.m941e("# PKG NAME: %s", b.f495c);
            C3749y.m941e("# APP VER: %s", b.f501i);
            C3749y.m941e("# SDK VER: %s", b.f498f);
            C3749y.m941e("# LAUNCH TIME: %s", C3695ab.m666a(new Date(C3626a.m339b().f476a)));
            C3749y.m941e("# CRASH TYPE: %s", str);
            C3749y.m941e("# CRASH TIME: %s", str2);
            C3749y.m941e("# CRASH PROCESS: %s", str3);
            C3749y.m941e("# CRASH FOREGROUND: %s", Boolean.valueOf(b.mo24058a()));
            C3749y.m941e("# CRASH THREAD: %s", str4);
            if (crashDetailBean != null) {
                C3749y.m941e("# REPORT ID: %s", crashDetailBean.f589c);
                Object[] objArr = new Object[2];
                objArr[0] = b.mo24080l();
                objArr[1] = b.mo24089v().booleanValue() ? "ROOTED" : "UNROOT";
                C3749y.m941e("# CRASH DEVICE: %s %s", objArr);
                C3749y.m941e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f562C), Long.valueOf(crashDetailBean.f563D), Long.valueOf(crashDetailBean.f564E));
                C3749y.m941e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f565F), Long.valueOf(crashDetailBean.f566G), Long.valueOf(crashDetailBean.f567H));
                if (!C3695ab.m679a(crashDetailBean.f573N)) {
                    C3749y.m941e("# EXCEPTION FIRED BY %s %s", crashDetailBean.f573N, crashDetailBean.f572M);
                } else if (crashDetailBean.f588b == 3) {
                    Object[] objArr2 = new Object[1];
                    if (crashDetailBean.f578S == null) {
                        str6 = "null";
                    } else {
                        str6 = crashDetailBean.f578S.get("BUGLY_CR_01");
                    }
                    objArr2[0] = str6;
                    C3749y.m941e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
                }
            }
            if (!C3695ab.m679a(str5)) {
                C3749y.m941e("# CRASH STACK: ", new Object[0]);
                C3749y.m941e(str5, new Object[0]);
            }
            C3749y.m941e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }

    /* renamed from: a */
    private static void m493a(CrashDetailBean crashDetailBean, Map<String, String> map) {
        String str;
        if (map == null || map.isEmpty()) {
            C3749y.m940d("extra map is empty. CrashBean won't have userDatas.", new Object[0]);
            return;
        }
        crashDetailBean.f577R = new LinkedHashMap(map.size());
        for (Map.Entry next : map.entrySet()) {
            if (!C3695ab.m679a((String) next.getKey())) {
                String str2 = (String) next.getKey();
                if (str2.length() > 100) {
                    str2 = str2.substring(0, 100);
                    C3749y.m940d("setted key length is over limit %d substring to %s", 100, str2);
                }
                if (C3695ab.m679a((String) next.getValue()) || ((String) next.getValue()).length() <= 100000) {
                    str = (String) next.getValue();
                } else {
                    str = ((String) next.getValue()).substring(((String) next.getValue()).length() - BuglyStrategy.C3610a.MAX_USERDATA_VALUE_LENGTH);
                    C3749y.m940d("setted %s value length is over limit %d substring", str2, Integer.valueOf(BuglyStrategy.C3610a.MAX_USERDATA_VALUE_LENGTH));
                }
                crashDetailBean.f577R.put(str2, str);
                C3749y.m934a("add setted key %s value size:%d", str2, Integer.valueOf(str.length()));
            }
        }
    }

    /* renamed from: g */
    private static String m519g(CrashDetailBean crashDetailBean) {
        try {
            Pair pair = f661g.get(Integer.valueOf(crashDetailBean.f588b));
            if (pair == null) {
                C3749y.m941e("crash type error! %d", Integer.valueOf(crashDetailBean.f588b));
                return "";
            } else if (crashDetailBean.f596j) {
                return (String) pair.first;
            } else {
                return (String) pair.second;
            }
        } catch (Exception e) {
            C3749y.m935a(e);
            return "";
        }
    }

    /* renamed from: h */
    private static ArrayList<C3703ai> m520h(CrashDetailBean crashDetailBean) {
        if (crashDetailBean.f594h == null || crashDetailBean.f594h.isEmpty()) {
            return null;
        }
        ArrayList<C3703ai> arrayList = new ArrayList<>(crashDetailBean.f594h.size());
        for (Map.Entry next : crashDetailBean.f594h.entrySet()) {
            C3703ai aiVar = new C3703ai();
            aiVar.f817a = ((PlugInBean) next.getValue()).f446a;
            aiVar.f818b = ((PlugInBean) next.getValue()).f448c;
            aiVar.f819c = ((PlugInBean) next.getValue()).f447b;
            arrayList.add(aiVar);
        }
        return arrayList;
    }

    /* renamed from: a */
    private static void m496a(ArrayList<C3705ak> arrayList, CrashDetailBean crashDetailBean) {
        if (crashDetailBean.f596j && crashDetailBean.f605s != null && crashDetailBean.f605s.length() > 0) {
            try {
                arrayList.add(new C3705ak((byte) 1, "alltimes.txt", crashDetailBean.f605s.getBytes("utf-8")));
            } catch (Exception e) {
                e.printStackTrace();
                C3749y.m935a(e);
            }
        }
    }

    /* renamed from: a */
    private static void m498a(ArrayList<C3705ak> arrayList, String str) {
        if (str != null) {
            try {
                arrayList.add(new C3705ak((byte) 1, "log.txt", str.getBytes("utf-8")));
            } catch (Exception e) {
                e.printStackTrace();
                C3749y.m935a(e);
            }
        }
    }

    /* renamed from: b */
    private static void m510b(ArrayList<C3705ak> arrayList, String str) {
        if (str != null) {
            try {
                arrayList.add(new C3705ak((byte) 1, "jniLog.txt", str.getBytes("utf-8")));
            } catch (Exception e) {
                e.printStackTrace();
                C3749y.m935a(e);
            }
        }
    }

    /* renamed from: c */
    private static void m515c(ArrayList<C3705ak> arrayList, String str) {
        if (!C3695ab.m679a(str)) {
            try {
                C3705ak akVar = new C3705ak((byte) 1, "crashInfos.txt", str.getBytes("utf-8"));
                C3749y.m939c("attach crash infos", new Object[0]);
                arrayList.add(akVar);
            } catch (Exception e) {
                e.printStackTrace();
                C3749y.m935a(e);
            }
        }
    }

    /* renamed from: a */
    private static void m499a(ArrayList<C3705ak> arrayList, String str, Context context) {
        if (str != null) {
            try {
                C3705ak a = m489a("backupRecord.zip", context, str);
                if (a != null) {
                    C3749y.m939c("attach backup record", new Object[0]);
                    arrayList.add(a);
                }
            } catch (Exception e) {
                C3749y.m935a(e);
            }
        }
    }

    /* renamed from: a */
    private static void m501a(ArrayList<C3705ak> arrayList, byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            try {
                C3705ak akVar = new C3705ak((byte) 2, "buglylog.zip", bArr);
                C3749y.m939c("attach user log", new Object[0]);
                arrayList.add(akVar);
            } catch (Exception e) {
                C3749y.m935a(e);
            }
        }
    }

    /* renamed from: a */
    private static void m497a(ArrayList<C3705ak> arrayList, CrashDetailBean crashDetailBean, Context context) {
        C3705ak a;
        if (crashDetailBean.f588b == 3) {
            C3749y.m939c("crashBean.anrMessages:%s", crashDetailBean.f578S);
            try {
                if (crashDetailBean.f578S != null && crashDetailBean.f578S.containsKey("BUGLY_CR_01")) {
                    if (!TextUtils.isEmpty(crashDetailBean.f578S.get("BUGLY_CR_01"))) {
                        arrayList.add(new C3705ak((byte) 1, "anrMessage.txt", crashDetailBean.f578S.get("BUGLY_CR_01").getBytes("utf-8")));
                        C3749y.m939c("attach anr message", new Object[0]);
                    }
                    crashDetailBean.f578S.remove("BUGLY_CR_01");
                }
                if (crashDetailBean.f608v != null && (a = m489a("trace.zip", context, crashDetailBean.f608v)) != null) {
                    C3749y.m939c("attach traces", new Object[0]);
                    arrayList.add(a);
                }
            } catch (Exception e) {
                e.printStackTrace();
                C3749y.m935a(e);
            }
        }
    }

    /* renamed from: b */
    private static void m509b(ArrayList<C3705ak> arrayList, CrashDetailBean crashDetailBean, Context context) {
        if (crashDetailBean.f588b == 1 && crashDetailBean.f608v != null) {
            try {
                C3705ak a = m489a("tomb.zip", context, crashDetailBean.f608v);
                if (a != null) {
                    C3749y.m939c("attach tombs", new Object[0]);
                    arrayList.add(a);
                }
            } catch (Exception e) {
                C3749y.m935a(e);
            }
        }
    }

    /* renamed from: a */
    private static void m500a(ArrayList<C3705ak> arrayList, List<String> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String append : list) {
                sb.append(append);
            }
            try {
                arrayList.add(new C3705ak((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
                C3749y.m939c("attach pageTracingList", new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    private static void m511b(ArrayList<C3705ak> arrayList, byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            try {
                arrayList.add(new C3705ak((byte) 1, "userExtraByteData", bArr));
                C3749y.m939c("attach extraData", new Object[0]);
            } catch (Exception e) {
                C3749y.m935a(e);
            }
        }
    }

    /* renamed from: a */
    private static Map<String, String> m491a(CrashDetailBean crashDetailBean, C3626a aVar) {
        HashMap hashMap = new HashMap(30);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.f562C);
            hashMap.put("A9", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.f563D);
            hashMap.put("A11", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(crashDetailBean.f564E);
            hashMap.put("A10", sb3.toString());
            hashMap.put("A23", crashDetailBean.f592f);
            StringBuilder sb4 = new StringBuilder();
            aVar.getClass();
            hashMap.put("A7", sb4.toString());
            hashMap.put("A6", C3626a.m340r());
            hashMap.put("A5", aVar.mo24085q());
            hashMap.put("A22", aVar.mo24079k());
            StringBuilder sb5 = new StringBuilder();
            sb5.append(crashDetailBean.f566G);
            hashMap.put("A2", sb5.toString());
            StringBuilder sb6 = new StringBuilder();
            sb6.append(crashDetailBean.f565F);
            hashMap.put("A1", sb6.toString());
            hashMap.put("A24", aVar.f499g);
            StringBuilder sb7 = new StringBuilder();
            sb7.append(crashDetailBean.f567H);
            hashMap.put("A17", sb7.toString());
            hashMap.put("A25", aVar.mo24079k());
            hashMap.put("A15", aVar.mo24088u());
            StringBuilder sb8 = new StringBuilder();
            sb8.append(aVar.mo24089v());
            hashMap.put("A13", sb8.toString());
            hashMap.put("A34", crashDetailBean.f560A);
            if (aVar.f515w != null) {
                hashMap.put("productIdentify", aVar.f515w);
            }
            hashMap.put("A26", URLEncoder.encode(crashDetailBean.f571L, "utf-8"));
            boolean z = true;
            if (crashDetailBean.f588b == 1) {
                hashMap.put("A27", crashDetailBean.f573N);
                hashMap.put("A28", crashDetailBean.f572M);
                StringBuilder sb9 = new StringBuilder();
                sb9.append(crashDetailBean.f597k);
                hashMap.put("A29", sb9.toString());
            }
            hashMap.put("A30", crashDetailBean.f574O);
            StringBuilder sb10 = new StringBuilder();
            sb10.append(crashDetailBean.f575P);
            hashMap.put("A18", sb10.toString());
            StringBuilder sb11 = new StringBuilder();
            if (crashDetailBean.f576Q) {
                z = false;
            }
            sb11.append(z);
            hashMap.put("A36", sb11.toString());
            StringBuilder sb12 = new StringBuilder();
            sb12.append(aVar.f508p);
            hashMap.put("F02", sb12.toString());
            StringBuilder sb13 = new StringBuilder();
            sb13.append(aVar.f509q);
            hashMap.put("F03", sb13.toString());
            hashMap.put("F04", aVar.mo24068e());
            StringBuilder sb14 = new StringBuilder();
            sb14.append(aVar.f510r);
            hashMap.put("F05", sb14.toString());
            hashMap.put("F06", aVar.f507o);
            hashMap.put("F08", aVar.f513u);
            hashMap.put("F09", aVar.f514v);
            StringBuilder sb15 = new StringBuilder();
            sb15.append(aVar.f511s);
            hashMap.put("F10", sb15.toString());
            m502a((Map<String, String>) hashMap, crashDetailBean);
        } catch (Exception e) {
            e.printStackTrace();
            C3749y.m935a(e);
        }
        return hashMap;
    }

    /* renamed from: a */
    private static void m502a(Map<String, String> map, CrashDetailBean crashDetailBean) {
        if (crashDetailBean.f579T >= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.f579T);
            map.put("C01", sb.toString());
        }
        if (crashDetailBean.f580U >= 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.f580U);
            map.put("C02", sb2.toString());
        }
        if (crashDetailBean.f581V != null && crashDetailBean.f581V.size() > 0) {
            for (Map.Entry next : crashDetailBean.f581V.entrySet()) {
                map.put("C03_" + ((String) next.getKey()), next.getValue());
            }
        }
        if (crashDetailBean.f582W != null && crashDetailBean.f582W.size() > 0) {
            for (Map.Entry next2 : crashDetailBean.f582W.entrySet()) {
                map.put("C04_" + ((String) next2.getKey()), next2.getValue());
            }
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.b$a */
    /* compiled from: BUGLY */
    static abstract class C3669a {

        /* renamed from: a */
        private final int f675a;

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public abstract boolean mo24142a();

        /* synthetic */ C3669a(int i, byte b) {
            this(i);
        }

        /* renamed from: a */
        static /* synthetic */ boolean m530a(C3669a aVar, CrashDetailBean crashDetailBean) {
            return aVar.f675a == crashDetailBean.f588b;
        }

        private C3669a(int i) {
            this.f675a = i;
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.b$b */
    /* compiled from: BUGLY */
    static class C3670b extends C3669a {
        /* synthetic */ C3670b(byte b) {
            this();
        }

        private C3670b() {
            super(3, (byte) 0);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final boolean mo24142a() {
            return C3678c.m540a().mo24166r();
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.b$c */
    /* compiled from: BUGLY */
    static class C3671c extends C3669a {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final boolean mo24142a() {
            return true;
        }

        /* synthetic */ C3671c(byte b) {
            this();
        }

        private C3671c() {
            super(7, (byte) 0);
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.b$d */
    /* compiled from: BUGLY */
    static class C3672d extends C3669a {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final boolean mo24142a() {
            return true;
        }

        /* synthetic */ C3672d(byte b) {
            this();
        }

        private C3672d() {
            super(2, (byte) 0);
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.b$e */
    /* compiled from: BUGLY */
    static class C3673e extends C3669a {
        /* synthetic */ C3673e(byte b) {
            this();
        }

        private C3673e() {
            super(0, (byte) 0);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final boolean mo24142a() {
            return C3678c.m540a().mo24165q();
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.b$h */
    /* compiled from: BUGLY */
    static class C3676h extends C3669a {
        /* synthetic */ C3676h(byte b) {
            this();
        }

        private C3676h() {
            super(1, (byte) 0);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final boolean mo24142a() {
            return C3678c.m540a().mo24165q();
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.b$i */
    /* compiled from: BUGLY */
    static class C3677i extends C3669a {
        /* synthetic */ C3677i(byte b) {
            this();
        }

        private C3677i() {
            super(4, (byte) 0);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final boolean mo24142a() {
            return C3678c.m540a().mo24167s();
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.b$f */
    /* compiled from: BUGLY */
    static class C3674f extends C3669a {
        /* synthetic */ C3674f(byte b) {
            this();
        }

        private C3674f() {
            super(5, (byte) 0);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final boolean mo24142a() {
            return C3678c.m540a().mo24168t();
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.b$g */
    /* compiled from: BUGLY */
    static class C3675g extends C3669a {
        /* synthetic */ C3675g(byte b) {
            this();
        }

        private C3675g() {
            super(6, (byte) 0);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final boolean mo24142a() {
            return C3678c.m540a().mo24169u();
        }
    }
}
