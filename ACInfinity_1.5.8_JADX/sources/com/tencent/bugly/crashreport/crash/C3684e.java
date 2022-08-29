package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.info.C3627b;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.proguard.C3692aa;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3749y;
import java.lang.Thread;
import java.util.HashMap;

/* renamed from: com.tencent.bugly.crashreport.crash.e */
/* compiled from: BUGLY */
public final class C3684e implements Thread.UncaughtExceptionHandler {

    /* renamed from: h */
    private static String f723h;

    /* renamed from: i */
    private static final Object f724i = new Object();

    /* renamed from: a */
    private Context f725a;

    /* renamed from: b */
    private C3662b f726b;

    /* renamed from: c */
    private C3644a f727c;

    /* renamed from: d */
    private C3626a f728d;

    /* renamed from: e */
    private Thread.UncaughtExceptionHandler f729e;

    /* renamed from: f */
    private Thread.UncaughtExceptionHandler f730f;

    /* renamed from: g */
    private boolean f731g = false;

    /* renamed from: j */
    private int f732j;

    public C3684e(Context context, C3662b bVar, C3644a aVar, C3626a aVar2) {
        this.f725a = context;
        this.f726b = bVar;
        this.f727c = aVar;
        this.f728d = aVar2;
    }

    /* renamed from: a */
    public final synchronized void mo24174a() {
        if (this.f732j >= 10) {
            C3749y.m934a("java crash handler over %d, no need set.", 10);
            return;
        }
        this.f731g = true;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            if (!getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                    C3749y.m934a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.f730f = defaultUncaughtExceptionHandler;
                    this.f729e = defaultUncaughtExceptionHandler;
                } else {
                    C3749y.m934a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.f729e = defaultUncaughtExceptionHandler;
                }
            } else {
                return;
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.f732j++;
        C3749y.m934a("registered java monitor: %s", toString());
    }

    /* renamed from: b */
    public final synchronized void mo24177b() {
        this.f731g = false;
        C3749y.m934a("close java monitor!", new Object[0]);
        if ("bugly".equals(Thread.getDefaultUncaughtExceptionHandler().getClass().getName())) {
            C3749y.m934a("Java monitor to unregister: %s", toString());
            Thread.setDefaultUncaughtExceptionHandler(this.f729e);
            this.f732j--;
        }
    }

    /* renamed from: b */
    private CrashDetailBean m580b(Thread thread, Throwable th, boolean z, String str, byte[] bArr, boolean z2) {
        if (th == null) {
            C3749y.m940d("We can do nothing with a null throwable.", new Object[0]);
            return null;
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f604r = System.currentTimeMillis();
        crashDetailBean.f562C = C3627b.m404j();
        crashDetailBean.f563D = C3627b.m398e();
        crashDetailBean.f564E = C3627b.m406l();
        crashDetailBean.f565F = this.f728d.mo24083o();
        crashDetailBean.f566G = this.f728d.mo24082n();
        crashDetailBean.f567H = this.f728d.mo24084p();
        crashDetailBean.f568I = C3627b.m400f();
        crashDetailBean.f569J = C3627b.m401g();
        crashDetailBean.f570K = C3627b.m402h();
        crashDetailBean.f609w = C3695ab.m660a(C3678c.f680e, (String) null);
        crashDetailBean.f611y = C3692aa.m641a();
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(crashDetailBean.f611y == null ? 0 : crashDetailBean.f611y.length);
        C3749y.m934a("user log size:%d", objArr);
        crashDetailBean.f588b = z ? 0 : 2;
        crashDetailBean.f591e = this.f728d.mo24079k();
        crashDetailBean.f592f = this.f728d.f501i;
        crashDetailBean.f593g = this.f728d.mo24088u();
        crashDetailBean.f599m = this.f728d.mo24072g();
        crashDetailBean.f612z = C3695ab.m672a(z2, C3678c.f681f, false);
        crashDetailBean.f560A = this.f728d.f496d;
        crashDetailBean.f561B = thread.getName() + "(" + thread.getId() + ")";
        crashDetailBean.f571L = this.f728d.mo24090w();
        crashDetailBean.f594h = this.f728d.mo24087t();
        crashDetailBean.f595i = this.f728d.mo24051F();
        crashDetailBean.f575P = this.f728d.f476a;
        crashDetailBean.f576Q = this.f728d.mo24058a();
        m577a(crashDetailBean, th, z);
        if (z) {
            try {
                this.f726b.mo24140d(crashDetailBean);
            } catch (Throwable th2) {
                C3749y.m941e("handle crash error %s", th2.toString());
            }
        } else {
            boolean z3 = str != null && str.length() > 0;
            boolean z4 = bArr != null && bArr.length > 0;
            if (z3) {
                crashDetailBean.f577R = new HashMap(1);
                crashDetailBean.f577R.put("UserData", str);
            }
            if (z4) {
                crashDetailBean.f583X = bArr;
            }
        }
        crashDetailBean.f579T = this.f728d.mo24049D();
        crashDetailBean.f580U = this.f728d.mo24050E();
        crashDetailBean.f581V = this.f728d.mo24091x();
        crashDetailBean.f582W = this.f728d.mo24048C();
        return crashDetailBean;
    }

    /* renamed from: a */
    private static void m577a(CrashDetailBean crashDetailBean, Throwable th, boolean z) {
        String str;
        String name = th.getClass().getName();
        String b = m581b(th, 1000);
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(th.getStackTrace().length);
        objArr[1] = Boolean.valueOf(th.getCause() != null);
        C3749y.m941e("stack frame :%d, has cause %b", objArr);
        String str2 = "";
        String stackTraceElement = th.getStackTrace().length > 0 ? th.getStackTrace()[0].toString() : str2;
        Throwable th2 = th;
        while (th2 != null && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 == null || th2 == th) {
            crashDetailBean.f600n = name;
            if (C3678c.m540a().mo24161m() && z) {
                C3749y.m941e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
                str2 = " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
            }
            crashDetailBean.f601o = b + str2;
            crashDetailBean.f602p = stackTraceElement;
            str = m576a(th, C3678c.f681f);
            crashDetailBean.f603q = str;
        } else {
            crashDetailBean.f600n = th2.getClass().getName();
            crashDetailBean.f601o = m581b(th2, 1000);
            if (th2.getStackTrace().length > 0) {
                crashDetailBean.f602p = th2.getStackTrace()[0].toString();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(":");
            sb.append(b);
            sb.append("\n");
            sb.append(stackTraceElement);
            sb.append("\n......");
            sb.append("\nCaused by:\n");
            sb.append(crashDetailBean.f600n);
            sb.append(":");
            sb.append(crashDetailBean.f601o);
            sb.append("\n");
            str = m576a(th2, C3678c.f681f);
            sb.append(str);
            crashDetailBean.f603q = sb.toString();
        }
        crashDetailBean.f607u = C3695ab.m667a(crashDetailBean.f603q.getBytes());
        crashDetailBean.f612z.put(crashDetailBean.f561B, str);
    }

    /* renamed from: a */
    private static boolean m579a(Thread thread) {
        synchronized (f724i) {
            if (f723h != null && thread.getName().equals(f723h)) {
                return true;
            }
            f723h = thread.getName();
            return false;
        }
    }

    /* renamed from: a */
    public final void mo24176a(Thread thread, Throwable th, boolean z, String str, byte[] bArr, boolean z2) {
        Thread thread2 = thread;
        Throwable th2 = th;
        boolean z3 = z;
        if (z3) {
            C3749y.m941e("Java Crash Happen cause by %s(%d)", thread.getName(), Long.valueOf(thread.getId()));
            if (m579a(thread)) {
                C3749y.m934a("this class has handled this exception", new Object[0]);
                if (this.f730f != null) {
                    C3749y.m934a("call system handler", new Object[0]);
                    this.f730f.uncaughtException(thread2, th2);
                } else {
                    C3749y.m941e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        } else {
            C3749y.m941e("Java Catch Happen", new Object[0]);
        }
        try {
            if (!this.f731g) {
                C3749y.m939c("Java crash handler is disable. Just return.", new Object[0]);
                if (z3) {
                    Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f729e;
                    if (uncaughtExceptionHandler != null && m578a(uncaughtExceptionHandler)) {
                        C3749y.m941e("sys default last handle start!", new Object[0]);
                        this.f729e.uncaughtException(thread2, th2);
                        C3749y.m941e("sys default last handle end!", new Object[0]);
                    } else if (this.f730f != null) {
                        C3749y.m941e("system handle start!", new Object[0]);
                        this.f730f.uncaughtException(thread2, th2);
                        C3749y.m941e("system handle end!", new Object[0]);
                    } else {
                        C3749y.m941e("crashreport last handle start!", new Object[0]);
                        C3749y.m941e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C3749y.m941e("crashreport last handle end!", new Object[0]);
                    }
                }
            } else {
                if (!this.f727c.mo24102b()) {
                    C3749y.m940d("no remote but still store!", new Object[0]);
                }
                if (!this.f727c.mo24103c().f526e) {
                    if (this.f727c.mo24102b()) {
                        C3749y.m941e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                        C3662b.m495a(z3 ? "JAVA_CRASH" : "JAVA_CATCH", C3695ab.m659a(), this.f728d.f496d, thread.getName(), C3695ab.m665a(th), (CrashDetailBean) null);
                        if (z3) {
                            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.f729e;
                            if (uncaughtExceptionHandler2 != null && m578a(uncaughtExceptionHandler2)) {
                                C3749y.m941e("sys default last handle start!", new Object[0]);
                                this.f729e.uncaughtException(thread2, th2);
                                C3749y.m941e("sys default last handle end!", new Object[0]);
                                return;
                            } else if (this.f730f != null) {
                                C3749y.m941e("system handle start!", new Object[0]);
                                this.f730f.uncaughtException(thread2, th2);
                                C3749y.m941e("system handle end!", new Object[0]);
                                return;
                            } else {
                                C3749y.m941e("crashreport last handle start!", new Object[0]);
                                C3749y.m941e("current process die", new Object[0]);
                                Process.killProcess(Process.myPid());
                                System.exit(1);
                                C3749y.m941e("crashreport last handle end!", new Object[0]);
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
                CrashDetailBean b = m580b(thread, th, z, str, bArr, z2);
                if (b == null) {
                    C3749y.m941e("pkg crash datas fail!", new Object[0]);
                    if (z3) {
                        Thread.UncaughtExceptionHandler uncaughtExceptionHandler3 = this.f729e;
                        if (uncaughtExceptionHandler3 != null && m578a(uncaughtExceptionHandler3)) {
                            C3749y.m941e("sys default last handle start!", new Object[0]);
                            this.f729e.uncaughtException(thread2, th2);
                            C3749y.m941e("sys default last handle end!", new Object[0]);
                        } else if (this.f730f != null) {
                            C3749y.m941e("system handle start!", new Object[0]);
                            this.f730f.uncaughtException(thread2, th2);
                            C3749y.m941e("system handle end!", new Object[0]);
                        } else {
                            C3749y.m941e("crashreport last handle start!", new Object[0]);
                            C3749y.m941e("current process die", new Object[0]);
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                            C3749y.m941e("crashreport last handle end!", new Object[0]);
                        }
                    }
                } else {
                    C3662b.m495a(z3 ? "JAVA_CRASH" : "JAVA_CATCH", C3695ab.m659a(), this.f728d.f496d, thread.getName(), C3695ab.m665a(th), b);
                    if (!this.f726b.mo24137a(b)) {
                        this.f726b.mo24135a(b, 3000, z3);
                    }
                    if (z3) {
                        this.f726b.mo24139c(b);
                    }
                    if (z3) {
                        Thread.UncaughtExceptionHandler uncaughtExceptionHandler4 = this.f729e;
                        if (uncaughtExceptionHandler4 != null && m578a(uncaughtExceptionHandler4)) {
                            C3749y.m941e("sys default last handle start!", new Object[0]);
                            this.f729e.uncaughtException(thread2, th2);
                            C3749y.m941e("sys default last handle end!", new Object[0]);
                        } else if (this.f730f != null) {
                            C3749y.m941e("system handle start!", new Object[0]);
                            this.f730f.uncaughtException(thread2, th2);
                            C3749y.m941e("system handle end!", new Object[0]);
                        } else {
                            C3749y.m941e("crashreport last handle start!", new Object[0]);
                            C3749y.m941e("current process die", new Object[0]);
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                            C3749y.m941e("crashreport last handle end!", new Object[0]);
                        }
                    }
                }
            }
        } catch (Throwable th3) {
            if (z3) {
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler5 = this.f729e;
                if (uncaughtExceptionHandler5 != null && m578a(uncaughtExceptionHandler5)) {
                    C3749y.m941e("sys default last handle start!", new Object[0]);
                    this.f729e.uncaughtException(thread2, th2);
                    C3749y.m941e("sys default last handle end!", new Object[0]);
                } else if (this.f730f != null) {
                    C3749y.m941e("system handle start!", new Object[0]);
                    this.f730f.uncaughtException(thread2, th2);
                    C3749y.m941e("system handle end!", new Object[0]);
                } else {
                    C3749y.m941e("crashreport last handle start!", new Object[0]);
                    C3749y.m941e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    C3749y.m941e("crashreport last handle end!", new Object[0]);
                }
            }
            throw th3;
        }
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        synchronized (f724i) {
            mo24176a(thread, th, true, (String) null, (byte[]) null, this.f728d.mo24075h());
        }
    }

    /* renamed from: a */
    private static boolean m578a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && "uncaughtException".equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo24175a(com.tencent.bugly.crashreport.common.strategy.StrategyBean r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x002a
            boolean r0 = r5.f526e     // Catch:{ all -> 0x0027 }
            boolean r1 = r4.f731g     // Catch:{ all -> 0x0027 }
            if (r0 == r1) goto L_0x002a
            java.lang.String r0 = "java changed to %b"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0027 }
            r2 = 0
            boolean r3 = r5.f526e     // Catch:{ all -> 0x0027 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x0027 }
            r1[r2] = r3     // Catch:{ all -> 0x0027 }
            com.tencent.bugly.proguard.C3749y.m934a(r0, r1)     // Catch:{ all -> 0x0027 }
            boolean r5 = r5.f526e     // Catch:{ all -> 0x0027 }
            if (r5 == 0) goto L_0x0023
            r4.mo24174a()     // Catch:{ all -> 0x0027 }
            monitor-exit(r4)
            return
        L_0x0023:
            r4.mo24177b()     // Catch:{ all -> 0x0027 }
            goto L_0x002a
        L_0x0027:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x002a:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C3684e.mo24175a(com.tencent.bugly.crashreport.common.strategy.StrategyBean):void");
    }

    /* renamed from: a */
    private static String m576a(Throwable th, int i) {
        if (th == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                int i2 = 0;
                while (i2 < length) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    if (i <= 0 || sb.length() < i) {
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                        i2++;
                    } else {
                        sb.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        return sb.toString();
                    }
                }
            }
        } catch (Throwable th2) {
            C3749y.m941e("gen stack error %s", th2.toString());
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static String m581b(Throwable th, int i) {
        String message = th.getMessage();
        if (message == null) {
            return "";
        }
        if (message.length() <= 1000) {
            return message;
        }
        return message.substring(0, 1000) + "\n[Message over limit size:1000" + ", has been cutted!]";
    }
}
