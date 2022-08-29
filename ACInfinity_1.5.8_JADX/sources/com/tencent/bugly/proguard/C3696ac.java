package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.SystemClock;
import com.tencent.bugly.crashreport.crash.anr.C3661c;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.tencent.bugly.proguard.ac */
/* compiled from: BUGLY */
public final class C3696ac implements Runnable {

    /* renamed from: a */
    private final Handler f804a;

    /* renamed from: b */
    private final List<C3661c> f805b = new LinkedList();

    /* renamed from: c */
    private long f806c;

    /* renamed from: d */
    private final long f807d;

    /* renamed from: e */
    private boolean f808e;

    /* renamed from: f */
    private long f809f;

    C3696ac(Handler handler, String str, long j) {
        this.f804a = handler;
        this.f806c = 5000;
        this.f807d = 5000;
        this.f808e = true;
    }

    /* renamed from: a */
    public final void mo24226a() {
        if (this.f808e) {
            this.f808e = false;
            this.f809f = SystemClock.uptimeMillis();
            this.f804a.post(this);
        }
    }

    /* renamed from: b */
    public final boolean mo24229b() {
        return !this.f808e && SystemClock.uptimeMillis() >= this.f809f + this.f806c;
    }

    public final void run() {
        this.f808e = true;
        this.f806c = this.f807d;
    }

    /* renamed from: a */
    public final void mo24227a(long j) {
        this.f806c = 5000;
    }

    /* renamed from: c */
    public final long mo24230c() {
        return SystemClock.uptimeMillis() - this.f809f;
    }

    /* renamed from: b */
    public final List<C3661c> mo24228b(long j) {
        ArrayList arrayList;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.f805b) {
            arrayList = new ArrayList(this.f805b.size());
            for (int i = 0; i < this.f805b.size(); i++) {
                C3661c cVar = this.f805b.get(i);
                if (!cVar.mo24133d() && currentTimeMillis - cVar.mo24132c() < 200000) {
                    arrayList.add(cVar);
                    cVar.mo24130a(true);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: d */
    public final void mo24231d() {
        StringBuilder sb = new StringBuilder(1024);
        System.nanoTime();
        try {
            StackTraceElement[] stackTrace = this.f804a.getLooper().getThread().getStackTrace();
            if (stackTrace.length == 0) {
                sb.append("Thread does not have stack trace.\n");
            } else {
                for (StackTraceElement append : stackTrace) {
                    sb.append(append);
                    sb.append("\n");
                }
            }
        } catch (SecurityException e) {
            sb.append("getStackTrace() encountered:\n");
            sb.append(e.getMessage());
            sb.append("\n");
            C3749y.m935a(e);
        }
        System.nanoTime();
        C3661c cVar = new C3661c(sb.toString(), System.currentTimeMillis());
        cVar.mo24129a(this.f804a.getLooper().getThread().getName());
        synchronized (this.f805b) {
            while (this.f805b.size() >= 32) {
                this.f805b.remove(0);
            }
            this.f805b.add(cVar);
        }
    }
}
