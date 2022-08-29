package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.Looper;
import com.tencent.bugly.crashreport.crash.anr.C3661c;
import java.util.List;

/* renamed from: com.tencent.bugly.proguard.ad */
/* compiled from: BUGLY */
public final class C3697ad extends Thread {

    /* renamed from: a */
    private boolean f810a = false;

    /* renamed from: b */
    private boolean f811b = true;

    /* renamed from: c */
    private boolean f812c = false;

    /* renamed from: d */
    private int f813d = 1;

    /* renamed from: e */
    private C3696ac f814e;

    /* renamed from: f */
    private C3698a f815f;

    /* renamed from: g */
    private boolean f816g = true;

    /* renamed from: com.tencent.bugly.proguard.ad$a */
    /* compiled from: BUGLY */
    public interface C3698a {
        /* renamed from: a */
        void mo24125a(boolean z, long j);
    }

    /* renamed from: a */
    public final void mo24234a(boolean z) {
        this.f816g = z;
        C3749y.m939c("set record stack trace enable:" + z, new Object[0]);
    }

    /* renamed from: a */
    public final boolean mo24235a() {
        this.f810a = true;
        if (!isAlive()) {
            return false;
        }
        try {
            interrupt();
        } catch (Exception e) {
            C3749y.m938b(e);
        }
        C3749y.m940d("MainHandlerChecker is reset to null.", new Object[0]);
        this.f814e = null;
        return true;
    }

    /* renamed from: b */
    public final boolean mo24237b() {
        Handler handler = new Handler(Looper.getMainLooper());
        C3696ac acVar = this.f814e;
        if (acVar != null) {
            acVar.mo24227a(5000);
        } else {
            this.f814e = new C3696ac(handler, handler.getLooper().getThread().getName(), 5000);
        }
        if (isAlive()) {
            return false;
        }
        try {
            start();
            return true;
        } catch (Exception e) {
            C3749y.m938b(e);
            return false;
        }
    }

    /* renamed from: a */
    public final void mo24233a(C3698a aVar) {
        this.f815f = aVar;
    }

    public final void run() {
        long currentTimeMillis = System.currentTimeMillis();
        while (!this.f810a) {
            try {
                C3696ac acVar = this.f814e;
                boolean z = false;
                if (acVar == null) {
                    C3749y.m939c("Main handler checker is null. Stop thread monitor.", new Object[0]);
                    return;
                }
                acVar.mo24226a();
                m702a(acVar);
                boolean z2 = true;
                if (this.f816g) {
                    if (this.f811b) {
                        long c = acVar.mo24230c();
                        if (c > 1510) {
                            if (c < 199990) {
                                if (c <= 5010) {
                                    this.f813d = 1;
                                    C3749y.m939c("timeSinceMsgSent in [2s, 5s], record stack", new Object[0]);
                                    z = true;
                                } else {
                                    int i = this.f813d + 1;
                                    this.f813d = i;
                                    if ((i & (i - 1)) != 0) {
                                        z2 = false;
                                    }
                                    if (z2) {
                                        C3749y.m939c("timeSinceMsgSent in (5s, 200s), should record stack:true", new Object[0]);
                                    }
                                    z = z2;
                                }
                            }
                        }
                    }
                }
                if (z) {
                    acVar.mo24231d();
                }
                C3698a aVar = this.f815f;
                if (aVar != null && this.f811b) {
                    aVar.mo24125a(acVar.mo24229b(), acVar.mo24230c());
                }
                C3695ab.m687b(500 - ((System.currentTimeMillis() - currentTimeMillis) % 500));
            } catch (Exception e) {
                C3749y.m938b(e);
            } catch (OutOfMemoryError e2) {
                C3749y.m938b(e2);
            }
        }
    }

    /* renamed from: c */
    public final List<C3661c> mo24238c() {
        return this.f814e.mo24228b(200000);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m702a(com.tencent.bugly.proguard.C3696ac r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.f811b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            boolean r0 = r2.f812c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            boolean r3 = r3.mo24229b()     // Catch:{ all -> 0x0020 }
            if (r3 != 0) goto L_0x001e
            java.lang.String r3 = "Restart getting main stack trace."
            r0 = 0
            java.lang.Object[] r1 = new java.lang.Object[r0]     // Catch:{ all -> 0x0020 }
            com.tencent.bugly.proguard.C3749y.m939c(r3, r1)     // Catch:{ all -> 0x0020 }
            r3 = 1
            r2.f811b = r3     // Catch:{ all -> 0x0020 }
            r2.f812c = r0     // Catch:{ all -> 0x0020 }
        L_0x001e:
            monitor-exit(r2)
            return
        L_0x0020:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3697ad.m702a(com.tencent.bugly.proguard.ac):void");
    }

    /* renamed from: d */
    public final synchronized void mo24239d() {
        this.f811b = false;
        C3749y.m939c("Record stack trace is disabled.", new Object[0]);
    }

    /* renamed from: b */
    public final synchronized void mo24236b(boolean z) {
        this.f812c = true;
    }
}
