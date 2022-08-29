package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.support.p000v4.media.session.PlaybackStateCompat;
import com.tencent.bugly.C3612b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.tencent.bugly.proguard.v */
/* compiled from: BUGLY */
public final class C3743v {

    /* renamed from: a */
    private static C3743v f1023a;

    /* renamed from: b */
    private final C3730o f1024b;

    /* renamed from: c */
    private final Context f1025c;

    /* renamed from: d */
    private Map<Integer, Long> f1026d = new HashMap();

    /* renamed from: e */
    private long f1027e;

    /* renamed from: f */
    private long f1028f;

    /* renamed from: g */
    private LinkedBlockingQueue<Runnable> f1029g = new LinkedBlockingQueue<>();

    /* renamed from: h */
    private LinkedBlockingQueue<Runnable> f1030h = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */

    /* renamed from: i */
    public final Object f1031i = new Object();

    /* renamed from: j */
    private int f1032j = 0;

    /* renamed from: b */
    static /* synthetic */ int m910b(C3743v vVar) {
        int i = vVar.f1032j - 1;
        vVar.f1032j = i;
        return i;
    }

    private C3743v(Context context) {
        this.f1025c = context;
        this.f1024b = C3730o.m839a();
    }

    /* renamed from: a */
    public static synchronized C3743v m904a(Context context) {
        C3743v vVar;
        synchronized (C3743v.class) {
            if (f1023a == null) {
                f1023a = new C3743v(context);
            }
            vVar = f1023a;
        }
        return vVar;
    }

    /* renamed from: a */
    public static synchronized C3743v m903a() {
        C3743v vVar;
        synchronized (C3743v.class) {
            vVar = f1023a;
        }
        return vVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo24335a(int r13, com.tencent.bugly.proguard.C3708an r14, java.lang.String r15, java.lang.String r16, com.tencent.bugly.proguard.C3742u r17, long r18, boolean r20) {
        /*
            r12 = this;
            r0 = r14
            int r3 = r0.f868g
            byte[] r4 = com.tencent.bugly.proguard.C3691a.m629a((java.lang.Object) r14)
            com.tencent.bugly.proguard.w r10 = new com.tencent.bugly.proguard.w     // Catch:{ all -> 0x0025 }
            r11 = r12
            android.content.Context r1 = r11.f1025c     // Catch:{ all -> 0x0023 }
            r8 = 1
            r0 = r10
            r2 = r13
            r5 = r15
            r6 = r16
            r7 = r17
            r9 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0023 }
            r7 = 1
            r8 = 1
            r5 = r12
            r6 = r10
            r9 = r18
            r5.m907a(r6, r7, r8, r9)     // Catch:{ all -> 0x0023 }
            return
        L_0x0023:
            r0 = move-exception
            goto L_0x0027
        L_0x0025:
            r0 = move-exception
            r11 = r12
        L_0x0027:
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x0030
            r0.printStackTrace()
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3743v.mo24335a(int, com.tencent.bugly.proguard.an, java.lang.String, java.lang.String, com.tencent.bugly.proguard.u, long, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo24336a(int r15, com.tencent.bugly.proguard.C3708an r16, java.lang.String r17, java.lang.String r18, com.tencent.bugly.proguard.C3742u r19, boolean r20) {
        /*
            r14 = this;
            r0 = r16
            int r3 = r0.f868g
            byte[] r4 = com.tencent.bugly.proguard.C3691a.m629a((java.lang.Object) r16)
            com.tencent.bugly.proguard.w r12 = new com.tencent.bugly.proguard.w     // Catch:{ all -> 0x0029 }
            r13 = r14
            android.content.Context r1 = r13.f1025c     // Catch:{ all -> 0x0027 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r0 = r12
            r2 = r15
            r5 = r17
            r6 = r18
            r7 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0027 }
            r8 = 0
            r9 = 0
            r5 = r14
            r6 = r12
            r7 = r20
            r5.m907a(r6, r7, r8, r9)     // Catch:{ all -> 0x0027 }
            return
        L_0x0027:
            r0 = move-exception
            goto L_0x002b
        L_0x0029:
            r0 = move-exception
            r13 = r14
        L_0x002b:
            boolean r1 = com.tencent.bugly.proguard.C3749y.m935a(r0)
            if (r1 != 0) goto L_0x0034
            r0.printStackTrace()
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3743v.mo24336a(int, com.tencent.bugly.proguard.an, java.lang.String, java.lang.String, com.tencent.bugly.proguard.u, boolean):void");
    }

    /* renamed from: a */
    public final long mo24333a(boolean z) {
        long j;
        long b = C3695ab.m683b();
        int i = z ? 5 : 3;
        List<C3733q> a = this.f1024b.mo24295a(i);
        if (a == null || a.size() <= 0) {
            j = z ? this.f1028f : this.f1027e;
        } else {
            j = 0;
            try {
                C3733q qVar = a.get(0);
                if (qVar.f1002e >= b) {
                    j = C3695ab.m684b(qVar.f1004g);
                    if (i == 3) {
                        this.f1027e = j;
                    } else {
                        this.f1028f = j;
                    }
                    a.remove(qVar);
                }
            } catch (Throwable th) {
                C3749y.m935a(th);
            }
            if (a.size() > 0) {
                this.f1024b.mo24297a(a);
            }
        }
        C3749y.m939c("[UploadManager] Local network consume: %d KB", Long.valueOf(j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
        return j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final synchronized void mo24337a(long j, boolean z) {
        int i = z ? 5 : 3;
        C3733q qVar = new C3733q();
        qVar.f999b = i;
        qVar.f1002e = C3695ab.m683b();
        qVar.f1000c = "";
        qVar.f1001d = "";
        qVar.f1004g = C3695ab.m695c(j);
        this.f1024b.mo24300b(i);
        this.f1024b.mo24299a(qVar);
        if (z) {
            this.f1028f = j;
        } else {
            this.f1027e = j;
        }
        C3749y.m939c("[UploadManager] Network total consume: %d KB", Long.valueOf(j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
    }

    /* renamed from: a */
    public final synchronized void mo24334a(int i, long j) {
        if (i >= 0) {
            this.f1026d.put(Integer.valueOf(i), Long.valueOf(j));
            C3733q qVar = new C3733q();
            qVar.f999b = i;
            qVar.f1002e = j;
            qVar.f1000c = "";
            qVar.f1001d = "";
            qVar.f1004g = new byte[0];
            this.f1024b.mo24300b(i);
            this.f1024b.mo24299a(qVar);
            C3749y.m939c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i), C3695ab.m661a(j));
            return;
        }
        C3749y.m941e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i));
    }

    /* renamed from: a */
    public final synchronized long mo24332a(int i) {
        if (i >= 0) {
            Long l = this.f1026d.get(Integer.valueOf(i));
            if (l != null) {
                return l.longValue();
            }
        } else {
            C3749y.m941e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i));
        }
        return 0;
    }

    /* renamed from: b */
    public final boolean mo24338b(int i) {
        if (C3612b.f392c) {
            C3749y.m939c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() - mo24332a(i);
        C3749y.m939c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf(currentTimeMillis / 1000), Integer.valueOf(i));
        if (currentTimeMillis >= 30000) {
            return true;
        }
        C3749y.m934a("[UploadManager] Data only be uploaded once in %d seconds.", 30L);
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005c, code lost:
        m906a(r3, (java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable>) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005f, code lost:
        if (r5 <= 0) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0061, code lost:
        com.tencent.bugly.proguard.C3749y.m939c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", java.lang.Integer.valueOf(r5), java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0083, code lost:
        r10 = com.tencent.bugly.proguard.C3747x.m926a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0087, code lost:
        if (r10 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0089, code lost:
        r10.mo24344a(new com.tencent.bugly.proguard.C3743v.C37452(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m911c(int r10) {
        /*
            r9 = this;
            com.tencent.bugly.proguard.x r10 = com.tencent.bugly.proguard.C3747x.m926a()
            java.util.concurrent.LinkedBlockingQueue r0 = new java.util.concurrent.LinkedBlockingQueue
            r0.<init>()
            java.util.concurrent.LinkedBlockingQueue r1 = new java.util.concurrent.LinkedBlockingQueue
            r1.<init>()
            java.lang.Object r2 = r9.f1031i
            monitor-enter(r2)
            java.lang.String r3 = "[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)"
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0092 }
            int r6 = android.os.Process.myPid()     // Catch:{ all -> 0x0092 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0092 }
            r7 = 0
            r5[r7] = r6     // Catch:{ all -> 0x0092 }
            int r6 = android.os.Process.myTid()     // Catch:{ all -> 0x0092 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0092 }
            r8 = 1
            r5[r8] = r6     // Catch:{ all -> 0x0092 }
            com.tencent.bugly.proguard.C3749y.m939c(r3, r5)     // Catch:{ all -> 0x0092 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r3 = r9.f1029g     // Catch:{ all -> 0x0092 }
            int r3 = r3.size()     // Catch:{ all -> 0x0092 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r5 = r9.f1030h     // Catch:{ all -> 0x0092 }
            int r5 = r5.size()     // Catch:{ all -> 0x0092 }
            if (r3 != 0) goto L_0x0048
            if (r5 != 0) goto L_0x0048
            java.lang.String r10 = "[UploadManager] There is no upload task in queue."
            java.lang.Object[] r0 = new java.lang.Object[r7]     // Catch:{ all -> 0x0092 }
            com.tencent.bugly.proguard.C3749y.m939c(r10, r0)     // Catch:{ all -> 0x0092 }
            monitor-exit(r2)     // Catch:{ all -> 0x0092 }
            return
        L_0x0048:
            if (r10 == 0) goto L_0x0050
            boolean r10 = r10.mo24347c()     // Catch:{ all -> 0x0092 }
            if (r10 != 0) goto L_0x0051
        L_0x0050:
            r5 = 0
        L_0x0051:
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r10 = r9.f1029g     // Catch:{ all -> 0x0092 }
            m908a(r10, r0, r3)     // Catch:{ all -> 0x0092 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r10 = r9.f1030h     // Catch:{ all -> 0x0092 }
            m908a(r10, r1, r5)     // Catch:{ all -> 0x0092 }
            monitor-exit(r2)     // Catch:{ all -> 0x0092 }
            r9.m906a((int) r3, (java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable>) r0)
            if (r5 <= 0) goto L_0x0083
            java.lang.String r10 = "[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)"
            r0 = 3
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            r0[r7] = r2
            int r2 = android.os.Process.myPid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0[r8] = r2
            int r2 = android.os.Process.myTid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0[r4] = r2
            com.tencent.bugly.proguard.C3749y.m939c(r10, r0)
        L_0x0083:
            com.tencent.bugly.proguard.x r10 = com.tencent.bugly.proguard.C3747x.m926a()
            if (r10 == 0) goto L_0x0091
            com.tencent.bugly.proguard.v$2 r0 = new com.tencent.bugly.proguard.v$2
            r0.<init>(r9, r5, r1)
            r10.mo24344a(r0)
        L_0x0091:
            return
        L_0x0092:
            r10 = move-exception
            monitor-exit(r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3743v.m911c(int):void");
    }

    /* renamed from: a */
    private static void m908a(LinkedBlockingQueue<Runnable> linkedBlockingQueue, LinkedBlockingQueue<Runnable> linkedBlockingQueue2, int i) {
        int i2 = 0;
        while (i2 < i) {
            Runnable peek = linkedBlockingQueue.peek();
            if (peek != null) {
                try {
                    linkedBlockingQueue2.put(peek);
                    linkedBlockingQueue.poll();
                } catch (Throwable th) {
                    C3749y.m941e("[UploadManager] Failed to add upload task to temp urgent queue: %s", th.getMessage());
                }
                i2++;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    private void m906a(int i, LinkedBlockingQueue<Runnable> linkedBlockingQueue) {
        C3747x a = C3747x.m926a();
        if (i > 0) {
            C3749y.m939c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(i), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        }
        int i2 = 0;
        while (i2 < i) {
            final Runnable poll = linkedBlockingQueue.poll();
            if (poll != null) {
                synchronized (this.f1031i) {
                    if (this.f1032j < 2 || a == null) {
                        C3749y.m934a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
                        if (C3695ab.m668a((Runnable) new Runnable() {
                            public final void run() {
                                poll.run();
                                synchronized (C3743v.this.f1031i) {
                                    C3743v.m910b(C3743v.this);
                                }
                            }
                        }, "BUGLY_ASYNC_UPLOAD") != null) {
                            synchronized (this.f1031i) {
                                this.f1032j++;
                            }
                        } else {
                            C3749y.m940d("[UploadManager] Failed to start a thread to execute asynchronous upload task,will try again next time.", new Object[0]);
                            m909a(poll, true);
                        }
                    } else {
                        a.mo24344a(poll);
                    }
                }
                i2++;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    private boolean m909a(Runnable runnable, boolean z) {
        if (runnable == null) {
            C3749y.m934a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            C3749y.m939c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f1031i) {
                if (z) {
                    this.f1029g.put(runnable);
                } else {
                    this.f1030h.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            C3749y.m941e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private void m907a(Runnable runnable, boolean z, boolean z2, long j) {
        if (runnable == null) {
            C3749y.m940d("[UploadManager] Upload task should not be null", new Object[0]);
        }
        C3749y.m939c("[UploadManager] Add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (!z2) {
            m909a(runnable, z);
            m911c(0);
        } else if (runnable == null) {
            C3749y.m940d("[UploadManager] Upload task should not be null", new Object[0]);
        } else {
            C3749y.m939c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            Thread a = C3695ab.m668a(runnable, "BUGLY_SYNC_UPLOAD");
            if (a == null) {
                C3749y.m941e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
                m909a(runnable, true);
                return;
            }
            try {
                a.join(j);
            } catch (Throwable th) {
                C3749y.m941e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
                m909a(runnable, true);
                m911c(0);
            }
        }
    }
}
