package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;

/* renamed from: com.tencent.bugly.proguard.aa */
/* compiled from: BUGLY */
public final class C3692aa {

    /* renamed from: a */
    public static boolean f778a = true;

    /* renamed from: b */
    private static boolean f779b = true;

    /* renamed from: c */
    private static SimpleDateFormat f780c = null;

    /* renamed from: d */
    private static int f781d = 30720;

    /* renamed from: e */
    private static StringBuilder f782e = null;

    /* renamed from: f */
    private static StringBuilder f783f = null;

    /* renamed from: g */
    private static boolean f784g = false;

    /* renamed from: h */
    private static C3694a f785h = null;

    /* renamed from: i */
    private static String f786i = null;

    /* renamed from: j */
    private static String f787j = null;

    /* renamed from: k */
    private static Context f788k = null;

    /* renamed from: l */
    private static String f789l = null;

    /* renamed from: m */
    private static boolean f790m = false;

    /* renamed from: n */
    private static boolean f791n = false;

    /* renamed from: o */
    private static ExecutorService f792o;

    /* renamed from: p */
    private static int f793p;

    /* renamed from: q */
    private static final Object f794q = new Object();

    static {
        try {
            f780c = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
            C3749y.m938b(th.getCause());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0071, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void m638a(android.content.Context r3) {
        /*
            java.lang.Class<com.tencent.bugly.proguard.aa> r0 = com.tencent.bugly.proguard.C3692aa.class
            monitor-enter(r0)
            boolean r1 = f790m     // Catch:{ all -> 0x0072 }
            if (r1 != 0) goto L_0x0070
            if (r3 == 0) goto L_0x0070
            boolean r1 = f778a     // Catch:{ all -> 0x0072 }
            if (r1 != 0) goto L_0x000e
            goto L_0x0070
        L_0x000e:
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newSingleThreadExecutor()     // Catch:{ all -> 0x006b }
            f792o = r1     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x006b }
            f783f = r1     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            r1.<init>(r2)     // Catch:{ all -> 0x006b }
            f782e = r1     // Catch:{ all -> 0x006b }
            f788k = r3     // Catch:{ all -> 0x006b }
            com.tencent.bugly.crashreport.common.info.a r3 = com.tencent.bugly.crashreport.common.info.C3626a.m337a((android.content.Context) r3)     // Catch:{ all -> 0x006b }
            java.lang.String r1 = r3.f496d     // Catch:{ all -> 0x006b }
            f786i = r1     // Catch:{ all -> 0x006b }
            r3.getClass()     // Catch:{ all -> 0x006b }
            java.lang.String r3 = ""
            f787j = r3     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            r3.<init>()     // Catch:{ all -> 0x006b }
            android.content.Context r1 = f788k     // Catch:{ all -> 0x006b }
            java.io.File r1 = r1.getFilesDir()     // Catch:{ all -> 0x006b }
            java.lang.String r1 = r1.getPath()     // Catch:{ all -> 0x006b }
            r3.append(r1)     // Catch:{ all -> 0x006b }
            java.lang.String r1 = "/buglylog_"
            r3.append(r1)     // Catch:{ all -> 0x006b }
            java.lang.String r1 = f786i     // Catch:{ all -> 0x006b }
            r3.append(r1)     // Catch:{ all -> 0x006b }
            java.lang.String r1 = "_"
            r3.append(r1)     // Catch:{ all -> 0x006b }
            java.lang.String r1 = f787j     // Catch:{ all -> 0x006b }
            r3.append(r1)     // Catch:{ all -> 0x006b }
            java.lang.String r1 = ".txt"
            r3.append(r1)     // Catch:{ all -> 0x006b }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x006b }
            f789l = r3     // Catch:{ all -> 0x006b }
            int r3 = android.os.Process.myPid()     // Catch:{ all -> 0x006b }
            f793p = r3     // Catch:{ all -> 0x006b }
        L_0x006b:
            r3 = 1
            f790m = r3     // Catch:{ all -> 0x0072 }
            monitor-exit(r0)
            return
        L_0x0070:
            monitor-exit(r0)
            return
        L_0x0072:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3692aa.m638a(android.content.Context):void");
    }

    /* renamed from: a */
    public static void m637a(int i) {
        synchronized (f794q) {
            f781d = i;
            if (i < 0) {
                f781d = 0;
            } else if (i > 30720) {
                f781d = 30720;
            }
        }
    }

    /* renamed from: a */
    public static void m640a(String str, String str2, Throwable th) {
        if (th != null) {
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            m639a(str, str2, message + 10 + C3695ab.m685b(th));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void m639a(final java.lang.String r3, final java.lang.String r4, final java.lang.String r5) {
        /*
            java.lang.Class<com.tencent.bugly.proguard.aa> r0 = com.tencent.bugly.proguard.C3692aa.class
            monitor-enter(r0)
            boolean r1 = f790m     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x001e
            boolean r1 = f778a     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x000c
            goto L_0x001e
        L_0x000c:
            java.util.concurrent.ExecutorService r1 = f792o     // Catch:{ Exception -> 0x0018 }
            com.tencent.bugly.proguard.aa$1 r2 = new com.tencent.bugly.proguard.aa$1     // Catch:{ Exception -> 0x0018 }
            r2.<init>(r3, r4, r5)     // Catch:{ Exception -> 0x0018 }
            r1.execute(r2)     // Catch:{ Exception -> 0x0018 }
            monitor-exit(r0)
            return
        L_0x0018:
            r3 = move-exception
            com.tencent.bugly.proguard.C3749y.m938b(r3)     // Catch:{ all -> 0x0020 }
            monitor-exit(r0)
            return
        L_0x001e:
            monitor-exit(r0)
            return
        L_0x0020:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3692aa.m639a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static synchronized void m644c(String str, String str2, String str3) {
        synchronized (C3692aa.class) {
            if (f779b) {
                m645d(str, str2, str3);
            } else {
                m646e(str, str2, str3);
            }
        }
    }

    /* renamed from: d */
    private static synchronized void m645d(String str, String str2, String str3) {
        synchronized (C3692aa.class) {
            String a = m636a(str, str2, str3, (long) Process.myTid());
            synchronized (f794q) {
                try {
                    f783f.append(a);
                    if (f783f.length() >= f781d) {
                        StringBuilder sb = f783f;
                        f783f = sb.delete(0, sb.indexOf("\u0001\r\n") + 1);
                    }
                } catch (Throwable th) {
                    if (!C3749y.m938b(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:19|20|21|(1:23)(2:24|(1:28))|29|(1:31)|32|33|34|35) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0076 */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void m646e(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.Class<com.tencent.bugly.proguard.aa> r0 = com.tencent.bugly.proguard.C3692aa.class
            monitor-enter(r0)
            int r1 = android.os.Process.myTid()     // Catch:{ all -> 0x007c }
            long r1 = (long) r1     // Catch:{ all -> 0x007c }
            java.lang.String r5 = m636a(r5, r6, r7, r1)     // Catch:{ all -> 0x007c }
            java.lang.Object r6 = f794q     // Catch:{ all -> 0x007c }
            monitor-enter(r6)     // Catch:{ all -> 0x007c }
            java.lang.StringBuilder r7 = f783f     // Catch:{ all -> 0x0076 }
            r7.append(r5)     // Catch:{ all -> 0x0076 }
            java.lang.StringBuilder r5 = f783f     // Catch:{ all -> 0x0076 }
            int r5 = r5.length()     // Catch:{ all -> 0x0076 }
            int r7 = f781d     // Catch:{ all -> 0x0076 }
            if (r5 > r7) goto L_0x0021
            monitor-exit(r6)     // Catch:{ all -> 0x0079 }
            monitor-exit(r0)
            return
        L_0x0021:
            boolean r5 = f784g     // Catch:{ all -> 0x0076 }
            if (r5 == 0) goto L_0x0028
            monitor-exit(r6)     // Catch:{ all -> 0x0079 }
            monitor-exit(r0)
            return
        L_0x0028:
            r5 = 1
            f784g = r5     // Catch:{ all -> 0x0076 }
            com.tencent.bugly.proguard.aa$a r5 = f785h     // Catch:{ all -> 0x0076 }
            if (r5 != 0) goto L_0x0039
            com.tencent.bugly.proguard.aa$a r5 = new com.tencent.bugly.proguard.aa$a     // Catch:{ all -> 0x0076 }
            java.lang.String r7 = f789l     // Catch:{ all -> 0x0076 }
            r5.<init>(r7)     // Catch:{ all -> 0x0076 }
            f785h = r5     // Catch:{ all -> 0x0076 }
            goto L_0x0060
        L_0x0039:
            java.io.File r5 = r5.f799b     // Catch:{ all -> 0x0076 }
            if (r5 == 0) goto L_0x005b
            com.tencent.bugly.proguard.aa$a r5 = f785h     // Catch:{ all -> 0x0076 }
            java.io.File r5 = r5.f799b     // Catch:{ all -> 0x0076 }
            long r1 = r5.length()     // Catch:{ all -> 0x0076 }
            java.lang.StringBuilder r5 = f783f     // Catch:{ all -> 0x0076 }
            int r5 = r5.length()     // Catch:{ all -> 0x0076 }
            long r3 = (long) r5     // Catch:{ all -> 0x0076 }
            long r1 = r1 + r3
            com.tencent.bugly.proguard.aa$a r5 = f785h     // Catch:{ all -> 0x0076 }
            long r3 = r5.f802e     // Catch:{ all -> 0x0076 }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0060
        L_0x005b:
            com.tencent.bugly.proguard.aa$a r5 = f785h     // Catch:{ all -> 0x0076 }
            boolean unused = r5.m648a()     // Catch:{ all -> 0x0076 }
        L_0x0060:
            com.tencent.bugly.proguard.aa$a r5 = f785h     // Catch:{ all -> 0x0076 }
            java.lang.StringBuilder r7 = f783f     // Catch:{ all -> 0x0076 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0076 }
            boolean r5 = r5.mo24225a((java.lang.String) r7)     // Catch:{ all -> 0x0076 }
            if (r5 == 0) goto L_0x0076
            java.lang.StringBuilder r5 = f783f     // Catch:{ all -> 0x0076 }
            r7 = 0
            r5.setLength(r7)     // Catch:{ all -> 0x0076 }
            f784g = r7     // Catch:{ all -> 0x0076 }
        L_0x0076:
            monitor-exit(r6)     // Catch:{ all -> 0x0079 }
            monitor-exit(r0)
            return
        L_0x0079:
            r5 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x007c }
            throw r5     // Catch:{ all -> 0x007c }
        L_0x007c:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3692aa.m646e(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    private static String m636a(String str, String str2, String str3, long j) {
        String str4;
        f782e.setLength(0);
        if (str3.length() > 30720) {
            str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = f780c;
        if (simpleDateFormat != null) {
            str4 = simpleDateFormat.format(date);
        } else {
            str4 = date.toString();
        }
        StringBuilder sb = f782e;
        sb.append(str4);
        sb.append(" ");
        sb.append(f793p);
        sb.append(" ");
        sb.append(j);
        sb.append(" ");
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        sb.append(": ");
        sb.append(str3);
        sb.append("\u0001\r\n");
        return f782e.toString();
    }

    /* renamed from: a */
    public static byte[] m641a() {
        if (!f779b) {
            return m643b();
        }
        if (!f778a) {
            return null;
        }
        return C3695ab.m681a((File) null, f783f.toString(), "BuglyLog.txt");
    }

    /* renamed from: b */
    private static byte[] m643b() {
        if (!f778a) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        synchronized (f794q) {
            C3694a aVar = f785h;
            if (aVar != null && aVar.f798a && f785h.f799b != null && f785h.f799b.length() > 0) {
                sb.append(C3695ab.m663a(f785h.f799b, 30720, true));
            }
            StringBuilder sb2 = f783f;
            if (sb2 != null && sb2.length() > 0) {
                sb.append(f783f.toString());
            }
        }
        return C3695ab.m681a((File) null, sb.toString(), "BuglyLog.txt");
    }

    /* renamed from: com.tencent.bugly.proguard.aa$a */
    /* compiled from: BUGLY */
    public static class C3694a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public boolean f798a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public File f799b;

        /* renamed from: c */
        private String f800c;

        /* renamed from: d */
        private long f801d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public long f802e = 30720;

        public C3694a(String str) {
            if (str != null && !str.equals("")) {
                this.f800c = str;
                this.f798a = m648a();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public boolean m648a() {
            try {
                File file = new File(this.f800c);
                this.f799b = file;
                if (file.exists() && !this.f799b.delete()) {
                    this.f798a = false;
                    return false;
                } else if (this.f799b.createNewFile()) {
                    return true;
                } else {
                    this.f798a = false;
                    return false;
                }
            } catch (Throwable th) {
                C3749y.m935a(th);
                this.f798a = false;
                return false;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x0036 A[SYNTHETIC, Splitter:B:19:0x0036] */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean mo24225a(java.lang.String r10) {
            /*
                r9 = this;
                boolean r0 = r9.f798a
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                r0 = 0
                java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x002e }
                java.io.File r3 = r9.f799b     // Catch:{ all -> 0x002e }
                r4 = 1
                r2.<init>(r3, r4)     // Catch:{ all -> 0x002e }
                java.lang.String r0 = "UTF-8"
                byte[] r10 = r10.getBytes(r0)     // Catch:{ all -> 0x002b }
                r2.write(r10)     // Catch:{ all -> 0x002b }
                r2.flush()     // Catch:{ all -> 0x002b }
                r2.close()     // Catch:{ all -> 0x002b }
                long r5 = r9.f801d     // Catch:{ all -> 0x002b }
                int r10 = r10.length     // Catch:{ all -> 0x002b }
                long r7 = (long) r10     // Catch:{ all -> 0x002b }
                long r5 = r5 + r7
                r9.f801d = r5     // Catch:{ all -> 0x002b }
                r9.f798a = r4     // Catch:{ all -> 0x002b }
                r2.close()     // Catch:{ IOException -> 0x002a }
            L_0x002a:
                return r4
            L_0x002b:
                r10 = move-exception
                r0 = r2
                goto L_0x002f
            L_0x002e:
                r10 = move-exception
            L_0x002f:
                com.tencent.bugly.proguard.C3749y.m935a(r10)     // Catch:{ all -> 0x003a }
                r9.f798a = r1     // Catch:{ all -> 0x003a }
                if (r0 == 0) goto L_0x0039
                r0.close()     // Catch:{ IOException -> 0x0039 }
            L_0x0039:
                return r1
            L_0x003a:
                r10 = move-exception
                if (r0 == 0) goto L_0x0040
                r0.close()     // Catch:{ IOException -> 0x0040 }
            L_0x0040:
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3692aa.C3694a.mo24225a(java.lang.String):boolean");
        }
    }
}
