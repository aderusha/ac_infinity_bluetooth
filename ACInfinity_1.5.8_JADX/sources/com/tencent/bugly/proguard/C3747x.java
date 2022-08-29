package com.tencent.bugly.proguard;

import com.tencent.bugly.C3612b;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.tencent.bugly.proguard.x */
/* compiled from: BUGLY */
public final class C3747x {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final AtomicInteger f1056a = new AtomicInteger(1);

    /* renamed from: b */
    private static C3747x f1057b;

    /* renamed from: c */
    private ScheduledExecutorService f1058c = null;

    protected C3747x() {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3, new ThreadFactory(this) {
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("BuglyThread-" + C3747x.f1056a.getAndIncrement());
                return thread;
            }
        });
        this.f1058c = newScheduledThreadPool;
        if (newScheduledThreadPool == null || newScheduledThreadPool.isShutdown()) {
            C3749y.m940d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    /* renamed from: a */
    public static synchronized C3747x m926a() {
        C3747x xVar;
        synchronized (C3747x.class) {
            if (f1057b == null) {
                f1057b = new C3747x();
            }
            xVar = f1057b;
        }
        return xVar;
    }

    /* renamed from: a */
    public final synchronized boolean mo24345a(Runnable runnable, long j) {
        if (!mo24347c()) {
            C3749y.m940d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        } else if (runnable == null) {
            C3749y.m940d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            return false;
        } else {
            if (j <= 0) {
                j = 0;
            }
            C3749y.m939c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
            try {
                this.f1058c.schedule(runnable, j, TimeUnit.MILLISECONDS);
                return true;
            } catch (Throwable th) {
                if (C3612b.f392c) {
                    th.printStackTrace();
                }
                return false;
            }
        }
    }

    /* renamed from: a */
    public final synchronized boolean mo24344a(Runnable runnable) {
        if (!mo24347c()) {
            C3749y.m940d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            return false;
        } else if (runnable == null) {
            C3749y.m940d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            return false;
        } else {
            C3749y.m939c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
            try {
                this.f1058c.execute(runnable);
                return true;
            } catch (Throwable th) {
                if (C3612b.f392c) {
                    th.printStackTrace();
                }
                return false;
            }
        }
    }

    /* renamed from: b */
    public final synchronized void mo24346b() {
        ScheduledExecutorService scheduledExecutorService = this.f1058c;
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            C3749y.m939c("[AsyncTaskHandler] Close async handler.", new Object[0]);
            this.f1058c.shutdownNow();
        }
    }

    /* renamed from: c */
    public final synchronized boolean mo24347c() {
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = this.f1058c;
        return scheduledExecutorService != null && !scheduledExecutorService.isShutdown();
    }
}
