package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.common.info.C3626a;
import com.tencent.bugly.crashreport.common.strategy.C3644a;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3729n;
import com.tencent.bugly.proguard.C3730o;
import com.tencent.bugly.proguard.C3747x;
import com.tencent.bugly.proguard.C3749y;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.tencent.bugly.crashreport.biz.a */
/* compiled from: BUGLY */
public final class C3616a {

    /* renamed from: a */
    private Context f417a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public long f418b;

    /* renamed from: c */
    private int f419c;

    /* renamed from: d */
    private boolean f420d = true;

    /* renamed from: a */
    static /* synthetic */ void m292a(C3616a aVar, UserInfoBean userInfoBean) {
        C3626a b;
        if (userInfoBean != null && (b = C3626a.m339b()) != null) {
            userInfoBean.f407j = b.mo24068e();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m293a(C3616a aVar, UserInfoBean userInfoBean, boolean z) {
        List<UserInfoBean> a;
        if (userInfoBean == null) {
            return;
        }
        if (z || userInfoBean.f399b == 1 || (a = aVar.mo24024a(C3626a.m337a(aVar.f417a).f496d)) == null || a.size() < 20) {
            long a2 = C3730o.m839a().mo24292a("t_ui", m289a(userInfoBean), (C3729n) null, true);
            if (a2 >= 0) {
                C3749y.m939c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(a2));
                userInfoBean.f398a = a2;
                return;
            }
            return;
        }
        C3749y.m934a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(a.size()));
    }

    public C3616a(Context context, boolean z) {
        this.f417a = context;
        this.f420d = z;
    }

    /* renamed from: a */
    public final void mo24026a(int i, boolean z, long j) {
        C3644a a = C3644a.m426a();
        int i2 = 0;
        if (a == null || a.mo24103c().f527f || i == 1 || i == 3) {
            if (i == 1 || i == 3) {
                this.f419c++;
            }
            C3626a a2 = C3626a.m337a(this.f417a);
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.f399b = i;
            userInfoBean.f400c = a2.f496d;
            userInfoBean.f401d = a2.mo24072g();
            userInfoBean.f402e = System.currentTimeMillis();
            userInfoBean.f403f = -1;
            userInfoBean.f411n = a2.f501i;
            if (i == 1) {
                i2 = 1;
            }
            userInfoBean.f412o = i2;
            userInfoBean.f409l = a2.mo24058a();
            userInfoBean.f410m = a2.f507o;
            userInfoBean.f404g = a2.f508p;
            userInfoBean.f405h = a2.f509q;
            userInfoBean.f406i = a2.f510r;
            userInfoBean.f408k = a2.f511s;
            userInfoBean.f415r = a2.mo24091x();
            userInfoBean.f416s = a2.mo24048C();
            userInfoBean.f413p = a2.mo24049D();
            userInfoBean.f414q = a2.mo24050E();
            C3747x.m926a().mo24345a(new C3619a(userInfoBean, z), 0);
            return;
        }
        C3749y.m941e("UserInfo is disable", new Object[0]);
    }

    /* renamed from: a */
    public final void mo24025a() {
        this.f418b = C3695ab.m683b() + 86400000;
        C3747x.m926a().mo24345a(new C3620b(), (this.f418b - System.currentTimeMillis()) + 5000);
    }

    /* renamed from: com.tencent.bugly.crashreport.biz.a$a */
    /* compiled from: BUGLY */
    class C3619a implements Runnable {

        /* renamed from: a */
        private boolean f424a;

        /* renamed from: b */
        private UserInfoBean f425b;

        public C3619a(UserInfoBean userInfoBean, boolean z) {
            this.f425b = userInfoBean;
            this.f424a = z;
        }

        public final void run() {
            try {
                UserInfoBean userInfoBean = this.f425b;
                if (userInfoBean != null) {
                    C3616a.m292a(C3616a.this, userInfoBean);
                    C3749y.m939c("[UserInfo] Record user info.", new Object[0]);
                    C3616a.m293a(C3616a.this, this.f425b, false);
                }
                if (this.f424a) {
                    C3616a aVar = C3616a.this;
                    C3747x a = C3747x.m926a();
                    if (a != null) {
                        a.mo24344a(new Runnable() {
                            public final void run() {
                                try {
                                    C3616a.this.m298c();
                                } catch (Throwable th) {
                                    C3749y.m935a(th);
                                }
                            }
                        });
                    }
                }
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0029 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002b A[SYNTHETIC, Splitter:B:19:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006f  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m298c() {
        /*
            r13 = this;
            monitor-enter(r13)
            boolean r0 = r13.f420d     // Catch:{ all -> 0x00eb }
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0009
        L_0x0007:
            r0 = 0
            goto L_0x0027
        L_0x0009:
            com.tencent.bugly.proguard.v r0 = com.tencent.bugly.proguard.C3743v.m903a()     // Catch:{ all -> 0x00eb }
            if (r0 != 0) goto L_0x0010
            goto L_0x0007
        L_0x0010:
            com.tencent.bugly.crashreport.common.strategy.a r3 = com.tencent.bugly.crashreport.common.strategy.C3644a.m426a()     // Catch:{ all -> 0x00eb }
            if (r3 != 0) goto L_0x0017
            goto L_0x0007
        L_0x0017:
            boolean r3 = r3.mo24102b()     // Catch:{ all -> 0x00eb }
            if (r3 == 0) goto L_0x0026
            r3 = 1001(0x3e9, float:1.403E-42)
            boolean r0 = r0.mo24338b((int) r3)     // Catch:{ all -> 0x00eb }
            if (r0 != 0) goto L_0x0026
            goto L_0x0007
        L_0x0026:
            r0 = 1
        L_0x0027:
            if (r0 != 0) goto L_0x002b
            monitor-exit(r13)
            return
        L_0x002b:
            android.content.Context r0 = r13.f417a     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.crashreport.common.info.a r0 = com.tencent.bugly.crashreport.common.info.C3626a.m337a((android.content.Context) r0)     // Catch:{ all -> 0x00eb }
            java.lang.String r0 = r0.f496d     // Catch:{ all -> 0x00eb }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x00eb }
            r3.<init>()     // Catch:{ all -> 0x00eb }
            java.util.List r0 = r13.mo24024a((java.lang.String) r0)     // Catch:{ all -> 0x00eb }
            if (r0 == 0) goto L_0x005e
            m295a((java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean>) r0, (java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean>) r3)     // Catch:{ all -> 0x00eb }
            m297b(r0, r3)     // Catch:{ all -> 0x00eb }
            r4 = 600000(0x927c0, double:2.964394E-318)
            int r4 = m288a((java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean>) r0, (long) r4)     // Catch:{ all -> 0x00eb }
            r5 = 15
            if (r4 <= r5) goto L_0x0063
            java.lang.String r5 = "[UserInfo] Upload user info too many times in 10 min: %d"
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ all -> 0x00eb }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x00eb }
            r6[r2] = r4     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.proguard.C3749y.m940d(r5, r6)     // Catch:{ all -> 0x00eb }
            r4 = 0
            goto L_0x0064
        L_0x005e:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x00eb }
            r0.<init>()     // Catch:{ all -> 0x00eb }
        L_0x0063:
            r4 = 1
        L_0x0064:
            int r5 = r3.size()     // Catch:{ all -> 0x00eb }
            if (r5 <= 0) goto L_0x006d
            m294a((java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean>) r3)     // Catch:{ all -> 0x00eb }
        L_0x006d:
            if (r4 == 0) goto L_0x00e2
            int r3 = r0.size()     // Catch:{ all -> 0x00eb }
            if (r3 != 0) goto L_0x0076
            goto L_0x00e2
        L_0x0076:
            java.lang.String r3 = "[UserInfo] Upload user info(size: %d)"
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ all -> 0x00eb }
            int r5 = r0.size()     // Catch:{ all -> 0x00eb }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00eb }
            r4[r2] = r5     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.proguard.C3749y.m939c(r3, r4)     // Catch:{ all -> 0x00eb }
            int r3 = r13.f419c     // Catch:{ all -> 0x00eb }
            if (r3 != r1) goto L_0x008d
            r3 = 1
            goto L_0x008e
        L_0x008d:
            r3 = 2
        L_0x008e:
            com.tencent.bugly.proguard.as r3 = com.tencent.bugly.proguard.C3691a.m621a((java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean>) r0, (int) r3)     // Catch:{ all -> 0x00eb }
            if (r3 != 0) goto L_0x009d
            java.lang.String r0 = "[UserInfo] Failed to create UserInfoPackage."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r1)     // Catch:{ all -> 0x00eb }
            monitor-exit(r13)
            return
        L_0x009d:
            byte[] r3 = com.tencent.bugly.proguard.C3691a.m628a((com.tencent.bugly.proguard.C3723j) r3)     // Catch:{ all -> 0x00eb }
            if (r3 != 0) goto L_0x00ac
            java.lang.String r0 = "[UserInfo] Failed to encode data."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r1)     // Catch:{ all -> 0x00eb }
            monitor-exit(r13)
            return
        L_0x00ac:
            android.content.Context r4 = r13.f417a     // Catch:{ all -> 0x00eb }
            r5 = 840(0x348, float:1.177E-42)
            com.tencent.bugly.proguard.an r8 = com.tencent.bugly.proguard.C3691a.m619a(r4, r5, r3)     // Catch:{ all -> 0x00eb }
            if (r8 != 0) goto L_0x00bf
            java.lang.String r0 = "[UserInfo] Request package is null."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.proguard.C3749y.m940d(r0, r1)     // Catch:{ all -> 0x00eb }
            monitor-exit(r13)
            return
        L_0x00bf:
            com.tencent.bugly.crashreport.biz.a$1 r11 = new com.tencent.bugly.crashreport.biz.a$1     // Catch:{ all -> 0x00eb }
            r11.<init>(r0)     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.crashreport.common.strategy.a r0 = com.tencent.bugly.crashreport.common.strategy.C3644a.m426a()     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r0 = r0.mo24103c()     // Catch:{ all -> 0x00eb }
            java.lang.String r9 = r0.f537p     // Catch:{ all -> 0x00eb }
            java.lang.String r10 = com.tencent.bugly.crashreport.common.strategy.StrategyBean.f522a     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.proguard.v r6 = com.tencent.bugly.proguard.C3743v.m903a()     // Catch:{ all -> 0x00eb }
            r7 = 1001(0x3e9, float:1.403E-42)
            int r0 = r13.f419c     // Catch:{ all -> 0x00eb }
            if (r0 != r1) goto L_0x00dc
            r12 = 1
            goto L_0x00dd
        L_0x00dc:
            r12 = 0
        L_0x00dd:
            r6.mo24336a(r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x00eb }
            monitor-exit(r13)
            return
        L_0x00e2:
            java.lang.String r0 = "[UserInfo] There is no user info in local database."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x00eb }
            com.tencent.bugly.proguard.C3749y.m939c(r0, r1)     // Catch:{ all -> 0x00eb }
            monitor-exit(r13)
            return
        L_0x00eb:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.C3616a.m298c():void");
    }

    /* renamed from: a */
    private static void m295a(List<UserInfoBean> list, List<UserInfoBean> list2) {
        int size = list.size() - 20;
        if (size > 0) {
            int i = 0;
            while (i < list.size() - 1) {
                int i2 = i + 1;
                for (int i3 = i2; i3 < list.size(); i3++) {
                    if (list.get(i).f402e > list.get(i3).f402e) {
                        list.set(i, list.get(i3));
                        list.set(i3, list.get(i));
                    }
                }
                i = i2;
            }
            for (int i4 = 0; i4 < size; i4++) {
                list2.add(list.get(i4));
            }
        }
    }

    /* renamed from: b */
    private static void m297b(List<UserInfoBean> list, List<UserInfoBean> list2) {
        Iterator<UserInfoBean> it = list.iterator();
        while (it.hasNext()) {
            UserInfoBean next = it.next();
            if (next.f403f != -1) {
                it.remove();
                if (next.f402e < C3695ab.m683b()) {
                    list2.add(next);
                }
            }
        }
    }

    /* renamed from: a */
    private static int m288a(List<UserInfoBean> list, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        for (UserInfoBean next : list) {
            if (next.f402e > currentTimeMillis - 600000 && (next.f399b == 1 || next.f399b == 4 || next.f399b == 3)) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: b */
    public final void mo24027b() {
        C3747x a = C3747x.m926a();
        if (a != null) {
            a.mo24344a(new Runnable() {
                public final void run() {
                    try {
                        C3616a.this.m298c();
                    } catch (Throwable th) {
                        C3749y.m935a(th);
                    }
                }
            });
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.biz.a$b */
    /* compiled from: BUGLY */
    class C3620b implements Runnable {
        C3620b() {
        }

        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < C3616a.this.f418b) {
                C3747x.m926a().mo24345a(new C3620b(), (C3616a.this.f418b - currentTimeMillis) + 5000);
                return;
            }
            C3616a.this.mo24026a(3, false, 0);
            C3616a.this.mo24025a();
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.biz.a$c */
    /* compiled from: BUGLY */
    class C3621c implements Runnable {

        /* renamed from: a */
        private long f428a = 21600000;

        public C3621c(long j) {
            this.f428a = j;
        }

        public final void run() {
            C3616a aVar = C3616a.this;
            C3747x a = C3747x.m926a();
            if (a != null) {
                a.mo24344a(new Runnable() {
                    public final void run() {
                        try {
                            C3616a.this.m298c();
                        } catch (Throwable th) {
                            C3749y.m935a(th);
                        }
                    }
                });
            }
            C3616a aVar2 = C3616a.this;
            long j = this.f428a;
            C3747x.m926a().mo24345a(new C3621c(j), j);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ad A[Catch:{ all -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b2 A[DONT_GENERATE] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean> mo24024a(java.lang.String r12) {
        /*
            r11 = this;
            r0 = 0
            boolean r1 = com.tencent.bugly.proguard.C3695ab.m679a((java.lang.String) r12)     // Catch:{ all -> 0x00a5 }
            if (r1 == 0) goto L_0x0009
            r4 = r0
            goto L_0x001d
        L_0x0009:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = "_pc = '"
            r1.<init>(r2)     // Catch:{ all -> 0x00a5 }
            r1.append(r12)     // Catch:{ all -> 0x00a5 }
            java.lang.String r12 = "'"
            r1.append(r12)     // Catch:{ all -> 0x00a5 }
            java.lang.String r12 = r1.toString()     // Catch:{ all -> 0x00a5 }
            r4 = r12
        L_0x001d:
            com.tencent.bugly.proguard.o r1 = com.tencent.bugly.proguard.C3730o.m839a()     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = "t_ui"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 1
            android.database.Cursor r12 = r1.mo24293a(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00a5 }
            if (r12 != 0) goto L_0x0033
            if (r12 == 0) goto L_0x0032
            r12.close()
        L_0x0032:
            return r0
        L_0x0033:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            r1.<init>()     // Catch:{ all -> 0x00a3 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00a3 }
            r2.<init>()     // Catch:{ all -> 0x00a3 }
        L_0x003d:
            boolean r3 = r12.moveToNext()     // Catch:{ all -> 0x00a3 }
            r4 = 0
            if (r3 == 0) goto L_0x006e
            com.tencent.bugly.crashreport.biz.UserInfoBean r3 = m290a((android.database.Cursor) r12)     // Catch:{ all -> 0x00a3 }
            if (r3 == 0) goto L_0x004e
            r2.add(r3)     // Catch:{ all -> 0x00a3 }
            goto L_0x003d
        L_0x004e:
            java.lang.String r3 = "_id"
            int r3 = r12.getColumnIndex(r3)     // Catch:{ all -> 0x0066 }
            long r5 = r12.getLong(r3)     // Catch:{ all -> 0x0066 }
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch:{ all -> 0x0066 }
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch:{ all -> 0x0066 }
            r1.append(r5)     // Catch:{ all -> 0x0066 }
            goto L_0x003d
        L_0x0066:
            java.lang.String r3 = "[Database] unknown id."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x00a3 }
            com.tencent.bugly.proguard.C3749y.m940d(r3, r4)     // Catch:{ all -> 0x00a3 }
            goto L_0x003d
        L_0x006e:
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00a3 }
            int r3 = r1.length()     // Catch:{ all -> 0x00a3 }
            if (r3 <= 0) goto L_0x009d
            r3 = 4
            java.lang.String r7 = r1.substring(r3)     // Catch:{ all -> 0x00a3 }
            com.tencent.bugly.proguard.o r5 = com.tencent.bugly.proguard.C3730o.m839a()     // Catch:{ all -> 0x00a3 }
            java.lang.String r6 = "t_ui"
            r8 = 0
            r9 = 0
            r10 = 1
            int r1 = r5.mo24291a((java.lang.String) r6, (java.lang.String) r7, (java.lang.String[]) r8, (com.tencent.bugly.proguard.C3729n) r9, (boolean) r10)     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = "[Database] deleted %s error data %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00a3 }
            java.lang.String r6 = "t_ui"
            r5[r4] = r6     // Catch:{ all -> 0x00a3 }
            r4 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x00a3 }
            r5[r4] = r1     // Catch:{ all -> 0x00a3 }
            com.tencent.bugly.proguard.C3749y.m940d(r3, r5)     // Catch:{ all -> 0x00a3 }
        L_0x009d:
            if (r12 == 0) goto L_0x00a2
            r12.close()
        L_0x00a2:
            return r2
        L_0x00a3:
            r1 = move-exception
            goto L_0x00a7
        L_0x00a5:
            r1 = move-exception
            r12 = r0
        L_0x00a7:
            boolean r2 = com.tencent.bugly.proguard.C3749y.m935a(r1)     // Catch:{ all -> 0x00b6 }
            if (r2 != 0) goto L_0x00b0
            r1.printStackTrace()     // Catch:{ all -> 0x00b6 }
        L_0x00b0:
            if (r12 == 0) goto L_0x00b5
            r12.close()
        L_0x00b5:
            return r0
        L_0x00b6:
            r0 = move-exception
            if (r12 == 0) goto L_0x00bc
            r12.close()
        L_0x00bc:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.C3616a.mo24024a(java.lang.String):java.util.List");
    }

    /* renamed from: a */
    private static void m294a(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < list.size() && i < 50) {
                sb.append(" or _id");
                sb.append(" = ");
                sb.append(list.get(i).f398a);
                i++;
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            String str = sb2;
            sb.setLength(0);
            try {
                C3749y.m939c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(C3730o.m839a().mo24291a("t_ui", str, (String[]) null, (C3729n) null, true)));
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private static ContentValues m289a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.f398a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.f398a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.f402e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f403f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.f399b));
            contentValues.put("_pc", userInfoBean.f400c);
            contentValues.put("_dt", C3695ab.m680a((Parcelable) userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static UserInfoBean m290a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) C3695ab.m658a(blob, UserInfoBean.CREATOR);
            if (userInfoBean != null) {
                userInfoBean.f398a = j;
            }
            return userInfoBean;
        } catch (Throwable th) {
            if (!C3749y.m935a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
