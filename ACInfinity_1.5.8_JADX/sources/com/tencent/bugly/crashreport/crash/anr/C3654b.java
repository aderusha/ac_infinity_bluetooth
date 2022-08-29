package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.info.C3627b;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.crashreport.crash.C3662b;
import com.tencent.bugly.crashreport.crash.C3678c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.C3691a;
import com.tencent.bugly.proguard.C3692aa;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3697ad;
import com.tencent.bugly.proguard.C3730o;
import com.tencent.bugly.proguard.C3747x;
import com.tencent.bugly.proguard.C3749y;
import com.tencent.bugly.proguard.C3750z;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.tencent.bugly.crashreport.crash.anr.b */
/* compiled from: BUGLY */
public final class C3654b {

    /* renamed from: m */
    private static C3654b f635m;

    /* renamed from: a */
    private final AtomicBoolean f636a = new AtomicBoolean(false);

    /* renamed from: b */
    private final Context f637b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final ActivityManager f638c;

    /* renamed from: d */
    private final C3626a f639d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final C3747x f640e;

    /* renamed from: f */
    private final C3662b f641f;

    /* renamed from: g */
    private final Object f642g = new Object();
    /* access modifiers changed from: private */

    /* renamed from: h */
    public String f643h;

    /* renamed from: i */
    private FileObserver f644i;

    /* renamed from: j */
    private boolean f645j = true;

    /* renamed from: k */
    private C3697ad f646k;

    /* renamed from: l */
    private int f647l;

    /* renamed from: n */
    private long f648n = 0;

    /* renamed from: a */
    static /* synthetic */ void m459a(C3654b bVar, String str) {
        if (bVar.m466b(true)) {
            try {
                C3749y.m939c("read trace first dump for create time!", new Object[0]);
                TraceFileHelper.C3651a readFirstDumpInfo = TraceFileHelper.readFirstDumpInfo(str, false);
                long j = readFirstDumpInfo != null ? readFirstDumpInfo.f626c : -1;
                if (j == -1) {
                    C3749y.m940d("trace dump fail could not get time!", new Object[0]);
                    j = System.currentTimeMillis();
                }
                if (!bVar.m460a(j)) {
                    bVar.m457a(j, str);
                }
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
                C3749y.m941e("handle anr error %s", th.getClass().toString());
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ boolean m463a(String str) {
        return str.startsWith("manual_bugly_trace_") && str.endsWith(".txt");
    }

    /* renamed from: b */
    static /* synthetic */ void m465b(C3654b bVar) {
        long currentTimeMillis = (C3678c.f682g + System.currentTimeMillis()) - C3695ab.m683b();
        C3750z.m946a(bVar.f643h, "bugly_trace_", ".txt", currentTimeMillis);
        C3750z.m946a(bVar.f643h, "manual_bugly_trace_", ".txt", currentTimeMillis);
        C3750z.m946a(bVar.f643h, "main_stack_record_", ".txt", currentTimeMillis);
        C3750z.m946a(bVar.f643h, "main_stack_record_", ".txt.merged", currentTimeMillis);
    }

    /* renamed from: a */
    public static C3654b m454a(Context context, C3644a aVar, C3626a aVar2, C3747x xVar, C3730o oVar, C3662b bVar, BuglyStrategy.C3610a aVar3) {
        if (f635m == null) {
            f635m = new C3654b(context, aVar, aVar2, xVar, bVar);
        }
        return f635m;
    }

    private C3654b(Context context, C3644a aVar, C3626a aVar2, C3747x xVar, C3662b bVar) {
        Context a = C3695ab.m653a(context);
        this.f637b = a;
        this.f638c = (ActivityManager) a.getSystemService("activity");
        this.f643h = context.getDir("bugly", 0).getAbsolutePath();
        this.f639d = aVar2;
        this.f640e = xVar;
        this.f641f = bVar;
    }

    /* renamed from: a */
    private CrashDetailBean m453a(C3653a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.f562C = C3627b.m404j();
            crashDetailBean.f563D = C3627b.m398e();
            crashDetailBean.f564E = C3627b.m406l();
            crashDetailBean.f565F = this.f639d.mo24083o();
            crashDetailBean.f566G = this.f639d.mo24082n();
            crashDetailBean.f567H = this.f639d.mo24084p();
            crashDetailBean.f568I = C3627b.m400f();
            crashDetailBean.f569J = C3627b.m401g();
            crashDetailBean.f570K = C3627b.m402h();
            if (!C3627b.m409o()) {
                crashDetailBean.f609w = C3695ab.m660a(C3678c.f680e, (String) null);
            }
            crashDetailBean.f588b = 3;
            crashDetailBean.f591e = this.f639d.mo24079k();
            crashDetailBean.f592f = this.f639d.f501i;
            crashDetailBean.f593g = this.f639d.mo24088u();
            crashDetailBean.f599m = this.f639d.mo24072g();
            crashDetailBean.f600n = "ANR_EXCEPTION";
            crashDetailBean.f601o = aVar.f633f;
            crashDetailBean.f603q = aVar.f634g;
            crashDetailBean.f578S = new HashMap();
            crashDetailBean.f578S.put("BUGLY_CR_01", aVar.f632e);
            int i = -1;
            if (crashDetailBean.f603q != null) {
                i = crashDetailBean.f603q.indexOf("\n");
            }
            crashDetailBean.f602p = i > 0 ? crashDetailBean.f603q.substring(0, i) : "GET_FAIL";
            crashDetailBean.f604r = aVar.f630c;
            if (crashDetailBean.f603q != null) {
                crashDetailBean.f607u = C3695ab.m667a(crashDetailBean.f603q.getBytes());
            }
            crashDetailBean.f612z = aVar.f629b;
            crashDetailBean.f560A = aVar.f628a;
            crashDetailBean.f561B = "main(1)";
            crashDetailBean.f571L = this.f639d.mo24090w();
            crashDetailBean.f594h = this.f639d.mo24087t();
            crashDetailBean.f595i = this.f639d.mo24051F();
            crashDetailBean.f608v = aVar.f631d;
            crashDetailBean.f574O = this.f639d.f505m;
            crashDetailBean.f575P = this.f639d.f476a;
            crashDetailBean.f576Q = this.f639d.mo24058a();
            if (!C3627b.m409o()) {
                this.f641f.mo24140d(crashDetailBean);
            }
            crashDetailBean.f579T = this.f639d.mo24049D();
            crashDetailBean.f580U = this.f639d.mo24050E();
            crashDetailBean.f581V = this.f639d.mo24091x();
            crashDetailBean.f582W = this.f639d.mo24048C();
            crashDetailBean.f611y = C3692aa.m641a();
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    /* renamed from: a */
    private static boolean m464a(String str, String str2, String str3) {
        TraceFileHelper.C3651a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.f627d == null || readTargetDumpInfo.f627d.isEmpty()) {
            C3749y.m941e("not found trace dump for %s", str3);
            return false;
        }
        StringBuilder sb = new StringBuilder(1024);
        String[] strArr = readTargetDumpInfo.f627d.get("main");
        if (strArr != null && strArr.length >= 3) {
            sb.append("\"main\" tid=");
            sb.append(strArr[2]);
            sb.append(" :\n");
            sb.append(strArr[0]);
            sb.append("\n");
            sb.append(strArr[1]);
            sb.append("\n\n");
        }
        for (Map.Entry next : readTargetDumpInfo.f627d.entrySet()) {
            if (!((String) next.getKey()).equals("main") && next.getValue() != null && ((String[]) next.getValue()).length >= 3) {
                sb.append("\"");
                sb.append((String) next.getKey());
                sb.append("\" tid=");
                sb.append(((String[]) next.getValue())[2]);
                sb.append(" :\n");
                sb.append(((String[]) next.getValue())[0]);
                sb.append("\n");
                sb.append(((String[]) next.getValue())[1]);
                sb.append("\n\n");
            }
        }
        return C3750z.m949a(str2, sb.toString(), sb.length() * 2);
    }

    /* renamed from: a */
    public final boolean mo24120a() {
        return this.f636a.get();
    }

    /* renamed from: a */
    private static String m456a(List<C3661c> list, long j) {
        if (list == null || list.isEmpty()) {
            return "main thread stack not enable";
        }
        StringBuilder sb = new StringBuilder(4096);
        sb.append("\n>>>>> 以下为anr过程中主线程堆栈记录，可根据堆栈出现次数推测在该堆栈阻塞的时间，出现次数越多对anr贡献越大，越可能是造成anr的原因 >>>>>\n");
        sb.append("\n>>>>> Thread Stack Traces Records Start >>>>>\n");
        for (int i = 0; i < list.size(); i++) {
            C3661c cVar = list.get(i);
            sb.append("Thread name:");
            sb.append(cVar.mo24131b());
            sb.append("\n");
            long c = cVar.mo24132c() - j;
            String str = c <= 0 ? "before " : "after ";
            sb.append("Got ");
            sb.append(str);
            sb.append("anr:");
            sb.append(Math.abs(c));
            sb.append("ms\n");
            sb.append(cVar.mo24128a());
            sb.append("\n");
            if ((sb.length() << 1) >= 101376) {
                break;
            }
        }
        sb.append("\n<<<<< Thread Stack Traces Records End <<<<<\n");
        return sb.toString();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public boolean m466b(boolean z) {
        boolean compareAndSet = this.f636a.compareAndSet(!z, z);
        C3749y.m939c("tryChangeAnrState to %s, success:%s", Boolean.valueOf(z), Boolean.valueOf(compareAndSet));
        return compareAndSet;
    }

    /* renamed from: c */
    private synchronized void m468c() {
        if (m473e()) {
            C3749y.m940d("start when started!", new Object[0]);
            return;
        }
        C36551 r0 = new FileObserver("/data/anr/", 8) {
            public final void onEvent(int i, String str) {
                if (str != null) {
                    final String str2 = "/data/anr/" + str;
                    C3749y.m940d("watching file %s", str2);
                    if (!str2.contains("trace")) {
                        C3749y.m940d("not anr file %s", str2);
                        return;
                    }
                    C3654b.this.f640e.mo24344a(new Runnable() {
                        public final void run() {
                            C3654b.m459a(C3654b.this, str2);
                        }
                    });
                }
            }
        };
        this.f644i = r0;
        try {
            r0.startWatching();
            C3749y.m934a("start anr monitor!", new Object[0]);
            this.f640e.mo24344a(new Runnable() {
                public final void run() {
                    C3654b.m465b(C3654b.this);
                }
            });
        } catch (Throwable th) {
            this.f644i = null;
            C3749y.m940d("start anr monitor failed!", new Object[0]);
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: d */
    private synchronized void m471d() {
        if (!m473e()) {
            C3749y.m940d("close when closed!", new Object[0]);
            return;
        }
        try {
            this.f644i.stopWatching();
            this.f644i = null;
            C3749y.m940d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            C3749y.m940d("stop anr monitor failed!", new Object[0]);
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: e */
    private synchronized boolean m473e() {
        return this.f644i != null;
    }

    /* renamed from: c */
    private synchronized void m469c(boolean z) {
        if (Build.VERSION.SDK_INT <= 19) {
            if (z) {
                m468c();
            } else {
                m471d();
            }
        } else if (z) {
            m475g();
        } else {
            m476h();
        }
    }

    /* renamed from: f */
    private synchronized boolean m474f() {
        return this.f645j;
    }

    /* renamed from: d */
    private synchronized void m472d(boolean z) {
        if (this.f645j != z) {
            C3749y.m934a("user change anr %b", Boolean.valueOf(z));
            this.f645j = z;
        }
    }

    /* renamed from: a */
    public final void mo24119a(boolean z) {
        m472d(z);
        boolean f = m474f();
        C3644a a = C3644a.m426a();
        if (a != null) {
            f = f && a.mo24103c().f526e;
        }
        if (f != m473e()) {
            C3749y.m934a("anr changed to %b", Boolean.valueOf(f));
            m469c(f);
        }
    }

    /* renamed from: b */
    public final synchronized void mo24121b() {
        C3749y.m940d("customer decides whether to open or close.", new Object[0]);
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01a4 A[Catch:{ all -> 0x020d }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01ac A[Catch:{ all -> 0x020d }] */
    /* renamed from: a */
    public void m457a(long r19, java.lang.String r21) {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r0 = r21
            r4 = 0
            java.lang.String r5 = "anr time:%s"
            r6 = 1
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x020d }
            java.lang.Long r8 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x020d }
            r7[r4] = r8     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m939c(r5, r7)     // Catch:{ all -> 0x020d }
            java.lang.Object r5 = r1.f642g     // Catch:{ all -> 0x020d }
            monitor-enter(r5)     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.ad r7 = r1.f646k     // Catch:{ all -> 0x020a }
            if (r7 == 0) goto L_0x0028
            java.lang.String r7 = "Disable record main stack trace."
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ all -> 0x020a }
            com.tencent.bugly.proguard.C3749y.m939c(r7, r8)     // Catch:{ all -> 0x020a }
            com.tencent.bugly.proguard.ad r7 = r1.f646k     // Catch:{ all -> 0x020a }
            r7.mo24239d()     // Catch:{ all -> 0x020a }
        L_0x0028:
            monitor-exit(r5)     // Catch:{ all -> 0x020a }
            android.os.Looper r5 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x020d }
            java.lang.Thread r5 = r5.getThread()     // Catch:{ all -> 0x020d }
            java.lang.String r5 = com.tencent.bugly.proguard.C3695ab.m664a((java.lang.Thread) r5)     // Catch:{ all -> 0x020d }
            com.tencent.bugly.crashreport.common.info.a r7 = r1.f639d     // Catch:{ all -> 0x020d }
            boolean r7 = r7.mo24077i()     // Catch:{ all -> 0x020d }
            int r8 = com.tencent.bugly.crashreport.crash.C3678c.f681f     // Catch:{ all -> 0x020d }
            java.util.Map r7 = com.tencent.bugly.proguard.C3695ab.m672a((boolean) r7, (int) r8, (boolean) r4)     // Catch:{ all -> 0x020d }
            android.content.Context r8 = r1.f637b     // Catch:{ all -> 0x020d }
            boolean r9 = com.tencent.bugly.crashreport.common.info.C3627b.m397d(r8)     // Catch:{ all -> 0x020d }
            if (r9 != 0) goto L_0x0052
            boolean r8 = com.tencent.bugly.crashreport.common.info.C3627b.m399e(r8)     // Catch:{ all -> 0x020d }
            if (r8 == 0) goto L_0x0050
            goto L_0x0052
        L_0x0050:
            r8 = 0
            goto L_0x0053
        L_0x0052:
            r8 = 1
        L_0x0053:
            java.lang.String r9 = "isAnrCrashDevice:%s"
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch:{ all -> 0x020d }
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r8)     // Catch:{ all -> 0x020d }
            r10[r4] = r11     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m939c(r9, r10)     // Catch:{ all -> 0x020d }
            r9 = 0
            if (r8 == 0) goto L_0x006b
            android.app.ActivityManager r8 = r1.f638c     // Catch:{ all -> 0x020d }
            android.app.ActivityManager$ProcessErrorStateInfo r8 = com.tencent.bugly.proguard.C3691a.m618a((android.app.ActivityManager) r8, (long) r9)     // Catch:{ all -> 0x020d }
            goto L_0x0073
        L_0x006b:
            android.app.ActivityManager r8 = r1.f638c     // Catch:{ all -> 0x020d }
            r11 = 21000(0x5208, double:1.03754E-319)
            android.app.ActivityManager$ProcessErrorStateInfo r8 = com.tencent.bugly.proguard.C3691a.m618a((android.app.ActivityManager) r8, (long) r11)     // Catch:{ all -> 0x020d }
        L_0x0073:
            if (r8 != 0) goto L_0x0080
            java.lang.String r0 = "proc state is invisible or not my proc!"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m939c(r0, r2)     // Catch:{ all -> 0x020d }
            r1.m466b((boolean) r4)
            return
        L_0x0080:
            com.tencent.bugly.crashreport.crash.anr.a r11 = new com.tencent.bugly.crashreport.crash.anr.a     // Catch:{ all -> 0x020d }
            r11.<init>()     // Catch:{ all -> 0x020d }
            r11.f630c = r2     // Catch:{ all -> 0x020d }
            if (r8 == 0) goto L_0x008c
            java.lang.String r12 = r8.processName     // Catch:{ all -> 0x020d }
            goto L_0x0094
        L_0x008c:
            int r12 = android.os.Process.myPid()     // Catch:{ all -> 0x020d }
            java.lang.String r12 = com.tencent.bugly.crashreport.common.info.AppInfo.m326a((int) r12)     // Catch:{ all -> 0x020d }
        L_0x0094:
            r11.f628a = r12     // Catch:{ all -> 0x020d }
            if (r8 == 0) goto L_0x009b
            java.lang.String r12 = r8.shortMsg     // Catch:{ all -> 0x020d }
            goto L_0x009d
        L_0x009b:
            java.lang.String r12 = ""
        L_0x009d:
            r11.f633f = r12     // Catch:{ all -> 0x020d }
            if (r8 == 0) goto L_0x00a4
            java.lang.String r8 = r8.longMsg     // Catch:{ all -> 0x020d }
            goto L_0x00a6
        L_0x00a4:
            java.lang.String r8 = ""
        L_0x00a6:
            r11.f632e = r8     // Catch:{ all -> 0x020d }
            r11.f629b = r7     // Catch:{ all -> 0x020d }
            r11.f634g = r5     // Catch:{ all -> 0x020d }
            java.lang.String r5 = r11.f634g     // Catch:{ all -> 0x020d }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x020d }
            if (r5 == 0) goto L_0x00b8
            java.lang.String r5 = "main stack is null , some error may be encountered."
            r11.f634g = r5     // Catch:{ all -> 0x020d }
        L_0x00b8:
            java.lang.String r5 = "anr tm:%d\ntr:%s\nproc:%s\nmain stack:%s\nsMsg:%s\n lMsg:%s\n threads:%d"
            r7 = 7
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x020d }
            long r12 = r11.f630c     // Catch:{ all -> 0x020d }
            java.lang.Long r8 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x020d }
            r7[r4] = r8     // Catch:{ all -> 0x020d }
            java.lang.String r8 = r11.f631d     // Catch:{ all -> 0x020d }
            r7[r6] = r8     // Catch:{ all -> 0x020d }
            r8 = 2
            java.lang.String r12 = r11.f628a     // Catch:{ all -> 0x020d }
            r7[r8] = r12     // Catch:{ all -> 0x020d }
            r8 = 3
            java.lang.String r12 = r11.f634g     // Catch:{ all -> 0x020d }
            r7[r8] = r12     // Catch:{ all -> 0x020d }
            r8 = 4
            java.lang.String r12 = r11.f633f     // Catch:{ all -> 0x020d }
            r7[r8] = r12     // Catch:{ all -> 0x020d }
            r8 = 5
            java.lang.String r12 = r11.f632e     // Catch:{ all -> 0x020d }
            r7[r8] = r12     // Catch:{ all -> 0x020d }
            r8 = 6
            java.util.Map<java.lang.String, java.lang.String> r12 = r11.f629b     // Catch:{ all -> 0x020d }
            if (r12 != 0) goto L_0x00e4
            r12 = 0
            goto L_0x00ea
        L_0x00e4:
            java.util.Map<java.lang.String, java.lang.String> r12 = r11.f629b     // Catch:{ all -> 0x020d }
            int r12 = r12.size()     // Catch:{ all -> 0x020d }
        L_0x00ea:
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x020d }
            r7[r8] = r12     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m939c(r5, r7)     // Catch:{ all -> 0x020d }
            java.lang.String r5 = "found visible anr , start to upload!"
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m934a(r5, r7)     // Catch:{ all -> 0x020d }
            java.lang.String r5 = "trace file:%s"
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x020d }
            r7[r4] = r0     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m939c(r5, r7)     // Catch:{ all -> 0x020d }
            boolean r5 = android.text.TextUtils.isEmpty(r21)     // Catch:{ all -> 0x020d }
            if (r5 != 0) goto L_0x0197
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x020d }
            r5.<init>(r0)     // Catch:{ all -> 0x020d }
            boolean r5 = r5.exists()     // Catch:{ all -> 0x020d }
            if (r5 != 0) goto L_0x0116
            goto L_0x0197
        L_0x0116:
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x020d }
            java.lang.String r7 = r1.f643h     // Catch:{ all -> 0x020d }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x020d }
            java.lang.String r12 = "bugly_trace_"
            r8.<init>(r12)     // Catch:{ all -> 0x020d }
            r8.append(r2)     // Catch:{ all -> 0x020d }
            java.lang.String r12 = ".txt"
            r8.append(r12)     // Catch:{ all -> 0x020d }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x020d }
            r5.<init>(r7, r8)     // Catch:{ all -> 0x020d }
            java.lang.String r7 = "trace file exists"
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m939c(r7, r8)     // Catch:{ all -> 0x020d }
            java.lang.String r7 = "/data/anr/"
            boolean r7 = r0.startsWith(r7)     // Catch:{ all -> 0x020d }
            if (r7 == 0) goto L_0x0157
            java.lang.String r7 = r5.getAbsolutePath()     // Catch:{ all -> 0x020d }
            java.lang.String r8 = r11.f628a     // Catch:{ all -> 0x020d }
            boolean r0 = m464a((java.lang.String) r0, (java.lang.String) r7, (java.lang.String) r8)     // Catch:{ all -> 0x020d }
            java.lang.String r7 = "backup trace isOK:%s"
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ all -> 0x020d }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x020d }
            r8[r4] = r0     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m934a(r7, r8)     // Catch:{ all -> 0x020d }
            goto L_0x016d
        L_0x0157:
            java.io.File r7 = new java.io.File     // Catch:{ all -> 0x020d }
            r7.<init>(r0)     // Catch:{ all -> 0x020d }
            boolean r0 = r7.renameTo(r5)     // Catch:{ all -> 0x020d }
            java.lang.String r7 = "trace file rename :%s"
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ all -> 0x020d }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x020d }
            r8[r4] = r0     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m934a(r7, r8)     // Catch:{ all -> 0x020d }
        L_0x016d:
            r0 = 0
            java.lang.Object r7 = r1.f642g     // Catch:{ all -> 0x020d }
            monitor-enter(r7)     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.ad r8 = r1.f646k     // Catch:{ all -> 0x0194 }
            if (r8 == 0) goto L_0x0179
            java.util.List r0 = r8.mo24238c()     // Catch:{ all -> 0x0194 }
        L_0x0179:
            monitor-exit(r7)     // Catch:{ all -> 0x0194 }
            if (r0 == 0) goto L_0x018d
            java.lang.String r0 = m456a((java.util.List<com.tencent.bugly.crashreport.crash.anr.C3661c>) r0, (long) r2)     // Catch:{ all -> 0x020d }
            java.lang.String r7 = "save main stack trace"
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m939c(r7, r8)     // Catch:{ all -> 0x020d }
            r7 = 2147483647(0x7fffffff, double:1.060997895E-314)
            com.tencent.bugly.proguard.C3750z.m947a((java.io.File) r5, (java.lang.String) r0, (long) r7, (boolean) r6)     // Catch:{ all -> 0x020d }
        L_0x018d:
            java.lang.String r0 = r5.getAbsolutePath()     // Catch:{ all -> 0x020d }
            r11.f631d = r0     // Catch:{ all -> 0x020d }
            goto L_0x019e
        L_0x0194:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x020d }
            throw r0     // Catch:{ all -> 0x020d }
        L_0x0197:
            java.lang.String r0 = "trace file is null or not exists, just ignore"
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m939c(r0, r5)     // Catch:{ all -> 0x020d }
        L_0x019e:
            com.tencent.bugly.crashreport.crash.CrashDetailBean r0 = r1.m453a((com.tencent.bugly.crashreport.crash.anr.C3653a) r11)     // Catch:{ all -> 0x020d }
            if (r0 != 0) goto L_0x01ac
            java.lang.String r0 = "pack anr fail!"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m941e(r0, r2)     // Catch:{ all -> 0x020d }
            goto L_0x01ef
        L_0x01ac:
            com.tencent.bugly.crashreport.crash.c r5 = com.tencent.bugly.crashreport.crash.C3678c.m540a()     // Catch:{ all -> 0x020d }
            r5.mo24146a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r0)     // Catch:{ all -> 0x020d }
            long r7 = r0.f586a     // Catch:{ all -> 0x020d }
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 < 0) goto L_0x01c1
            java.lang.String r5 = "backup anr record success!"
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m934a(r5, r7)     // Catch:{ all -> 0x020d }
            goto L_0x01c8
        L_0x01c1:
            java.lang.String r5 = "backup anr record fail!"
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.C3749y.m940d(r5, r7)     // Catch:{ all -> 0x020d }
        L_0x01c8:
            java.lang.String r12 = "ANR"
            java.lang.String r13 = com.tencent.bugly.proguard.C3695ab.m661a((long) r19)     // Catch:{ all -> 0x020d }
            java.lang.String r14 = r11.f628a     // Catch:{ all -> 0x020d }
            java.lang.String r15 = "main"
            java.lang.String r2 = r11.f634g     // Catch:{ all -> 0x020d }
            r16 = r2
            r17 = r0
            com.tencent.bugly.crashreport.crash.C3662b.m495a((java.lang.String) r12, (java.lang.String) r13, (java.lang.String) r14, (java.lang.String) r15, (java.lang.String) r16, (com.tencent.bugly.crashreport.crash.CrashDetailBean) r17)     // Catch:{ all -> 0x020d }
            com.tencent.bugly.crashreport.crash.b r2 = r1.f641f     // Catch:{ all -> 0x020d }
            boolean r2 = r2.mo24137a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r0)     // Catch:{ all -> 0x020d }
            if (r2 != 0) goto L_0x01ea
            com.tencent.bugly.crashreport.crash.b r2 = r1.f641f     // Catch:{ all -> 0x020d }
            r7 = 3000(0xbb8, double:1.482E-320)
            r2.mo24135a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r0, (long) r7, (boolean) r6)     // Catch:{ all -> 0x020d }
        L_0x01ea:
            com.tencent.bugly.crashreport.crash.b r2 = r1.f641f     // Catch:{ all -> 0x020d }
            r2.mo24139c((com.tencent.bugly.crashreport.crash.CrashDetailBean) r0)     // Catch:{ all -> 0x020d }
        L_0x01ef:
            java.lang.Object r2 = r1.f642g     // Catch:{ all -> 0x020d }
            monitor-enter(r2)     // Catch:{ all -> 0x020d }
            com.tencent.bugly.proguard.ad r0 = r1.f646k     // Catch:{ all -> 0x0207 }
            if (r0 == 0) goto L_0x0202
            java.lang.String r0 = "Finish anr process."
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x0207 }
            com.tencent.bugly.proguard.C3749y.m939c(r0, r3)     // Catch:{ all -> 0x0207 }
            com.tencent.bugly.proguard.ad r0 = r1.f646k     // Catch:{ all -> 0x0207 }
            r0.mo24236b(r6)     // Catch:{ all -> 0x0207 }
        L_0x0202:
            monitor-exit(r2)     // Catch:{ all -> 0x0207 }
            r1.m466b((boolean) r4)
            return
        L_0x0207:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x020d }
            throw r0     // Catch:{ all -> 0x020d }
        L_0x020a:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x020d }
            throw r0     // Catch:{ all -> 0x020d }
        L_0x020d:
            r0 = move-exception
            com.tencent.bugly.proguard.C3749y.m938b(r0)     // Catch:{ all -> 0x0215 }
            r1.m466b((boolean) r4)
            return
        L_0x0215:
            r0 = move-exception
            r2 = r0
            r1.m466b((boolean) r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.C3654b.m457a(long, java.lang.String):void");
    }

    /* renamed from: g */
    private synchronized void m475g() {
        if (m473e()) {
            C3749y.m940d("start when started!", new Object[0]);
        } else if (!TextUtils.isEmpty(this.f643h)) {
            synchronized (this.f642g) {
                C3697ad adVar = this.f646k;
                if (adVar == null || !adVar.isAlive()) {
                    C3697ad adVar2 = new C3697ad();
                    this.f646k = adVar2;
                    adVar2.mo24234a(this.f639d.mo24078j());
                    this.f646k.mo24233a((C3697ad.C3698a) new C3697ad.C3698a() {
                        /* renamed from: a */
                        public final void mo24125a(boolean z, long j) {
                            if (z) {
                                if (C3654b.this.mo24120a()) {
                                    C3749y.m939c("anr is processing, return", new Object[0]);
                                    return;
                                }
                                C3749y.m939c("main thread blocked overdue, blockTime:%s", Long.valueOf(j));
                                if (!C3691a.m627a(C3654b.this.f638c)) {
                                    C3749y.m939c("proc is not in anr, wait next check", new Object[0]);
                                    return;
                                }
                                long currentTimeMillis = System.currentTimeMillis();
                                if (!C3654b.this.m460a(currentTimeMillis) && C3654b.this.m466b(true)) {
                                    C3749y.m939c("found anr", new Object[0]);
                                    NativeCrashHandler instance = NativeCrashHandler.getInstance();
                                    if (instance == null || !instance.isEnableCatchAnrTrace()) {
                                        C3749y.m939c("anr trace not enable", new Object[0]);
                                        String d = C3654b.this.f643h;
                                        File file = new File(d, "manual_bugly_trace_" + currentTimeMillis + ".txt");
                                        C3749y.m934a("create new trace file:%s", file.getAbsoluteFile());
                                        C3750z.m947a(file, "android trace not enable\n", 101376, true);
                                        return;
                                    }
                                    C3749y.m939c("anr trace enable, do dump trace", new Object[0]);
                                    instance.dumpAnrNativeStack();
                                }
                            }
                        }
                    });
                    C3697ad adVar3 = this.f646k;
                    StringBuilder sb = new StringBuilder("Bugly-ThreadMonitor");
                    int i = this.f647l;
                    this.f647l = i + 1;
                    sb.append(i);
                    adVar3.setName(sb.toString());
                    this.f646k.mo24237b();
                }
            }
            C36594 r0 = new FileObserver(this.f643h, 8) {
                public final void onEvent(int i, String str) {
                    if (str != null) {
                        C3749y.m940d("observe file, dir:%s fileName:%s", C3654b.this.f643h, str);
                        if (!C3654b.m463a(str)) {
                            C3749y.m939c("not manual trace file, ignore.", new Object[0]);
                        } else if (!C3654b.this.mo24120a()) {
                            C3749y.m939c("proc is not in anr, just ignore", new Object[0]);
                        } else {
                            long a = C3750z.m942a(str, "manual_bugly_trace_", ".txt");
                            C3654b bVar = C3654b.this;
                            bVar.m457a(a, C3654b.this.f643h + "/" + str);
                            C3749y.m939c("Finish handling one anr.", new Object[0]);
                        }
                    }
                }
            };
            this.f644i = r0;
            try {
                r0.startWatching();
                C3749y.m934a("startWatchingPrivateAnrDir! dumFilePath is %s", this.f643h);
                this.f640e.mo24344a(new Runnable() {
                    public final void run() {
                        C3654b.m465b(C3654b.this);
                    }
                });
            } catch (Throwable th) {
                this.f644i = null;
                C3749y.m940d("startWatchingPrivateAnrDir failed!", new Object[0]);
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m460a(long j) {
        if (Math.abs(j - this.f648n) < 10000) {
            C3749y.m940d("should not process ANR too Fre in %dms", 10000);
            return true;
        }
        this.f648n = j;
        return false;
    }

    /* renamed from: h */
    private synchronized void m476h() {
        if (!m473e()) {
            C3749y.m940d("close when closed!", new Object[0]);
            return;
        }
        synchronized (this.f642g) {
            C3697ad adVar = this.f646k;
            if (adVar != null) {
                adVar.mo24235a();
                this.f646k = null;
            }
        }
        C3749y.m934a("stopWatchingPrivateAnrDir", new Object[0]);
        try {
            this.f644i.stopWatching();
            this.f644i = null;
            C3749y.m940d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            C3749y.m940d("stop anr monitor failed!", new Object[0]);
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
        }
    }
}
