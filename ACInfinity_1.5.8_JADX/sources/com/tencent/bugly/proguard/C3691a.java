package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.os.Process;
import android.text.TextUtils;
import com.eternal.export.CSVUtil;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C3626a;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.tencent.bugly.proguard.a */
/* compiled from: BUGLY */
public class C3691a {

    /* renamed from: e */
    private static Proxy f773e;

    /* renamed from: a */
    protected HashMap<String, HashMap<String, byte[]>> f774a = new HashMap<>();

    /* renamed from: b */
    protected String f775b;

    /* renamed from: c */
    C3720h f776c;

    /* renamed from: d */
    private HashMap<String, Object> f777d;

    C3691a() {
        new HashMap();
        this.f777d = new HashMap<>();
        this.f775b = "GBK";
        this.f776c = new C3720h();
    }

    /* renamed from: a */
    public static void m624a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            f773e = null;
        } else {
            f773e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0050 A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m627a(android.app.ActivityManager r5) {
        /*
            r0 = 1
            r1 = 0
            if (r5 != 0) goto L_0x000d
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "is proc running, ActivityManager is null"
            com.tencent.bugly.proguard.C3749y.m939c(r3, r2)
        L_0x000b:
            r2 = 1
            goto L_0x0044
        L_0x000d:
            java.util.List r2 = r5.getRunningAppProcesses()
            if (r2 != 0) goto L_0x001c
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "running proc info list is empty, my proc not running."
            com.tencent.bugly.proguard.C3749y.m939c(r3, r2)
        L_0x001a:
            r2 = 0
            goto L_0x0044
        L_0x001c:
            int r3 = android.os.Process.myPid()
            java.util.Iterator r2 = r2.iterator()
        L_0x0024:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x003c
            java.lang.Object r4 = r2.next()
            android.app.ActivityManager$RunningAppProcessInfo r4 = (android.app.ActivityManager.RunningAppProcessInfo) r4
            int r4 = r4.pid
            if (r4 != r3) goto L_0x0024
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "my proc is running."
            com.tencent.bugly.proguard.C3749y.m939c(r3, r2)
            goto L_0x000b
        L_0x003c:
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "proc not in running proc info list, my proc not running."
            com.tencent.bugly.proguard.C3749y.m939c(r3, r2)
            goto L_0x001a
        L_0x0044:
            if (r2 == 0) goto L_0x0047
            return r1
        L_0x0047:
            r2 = 0
            android.app.ActivityManager$ProcessErrorStateInfo r5 = m618a((android.app.ActivityManager) r5, (long) r2)
            if (r5 == 0) goto L_0x0050
            return r0
        L_0x0050:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3691a.m627a(android.app.ActivityManager):boolean");
    }

    /* renamed from: a */
    public static void m625a(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            f773e = null;
        } else {
            f773e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(inetAddress, i));
        }
    }

    /* renamed from: a */
    public void mo24220a(String str) {
        this.f775b = str;
    }

    /* renamed from: a */
    public static C3712ar m620a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        C3712ar arVar = new C3712ar();
        arVar.f914a = userInfoBean.f402e;
        arVar.f918e = userInfoBean.f407j;
        arVar.f917d = userInfoBean.f400c;
        arVar.f916c = userInfoBean.f401d;
        arVar.f920g = userInfoBean.f412o == 1;
        int i = userInfoBean.f399b;
        if (i == 1) {
            arVar.f915b = 1;
        } else if (i == 2) {
            arVar.f915b = 4;
        } else if (i == 3) {
            arVar.f915b = 2;
        } else if (i == 4) {
            arVar.f915b = 3;
        } else if (i == 8) {
            arVar.f915b = 8;
        } else if (userInfoBean.f399b < 10 || userInfoBean.f399b >= 20) {
            C3749y.m941e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.f399b));
            return null;
        } else {
            arVar.f915b = (byte) userInfoBean.f399b;
        }
        arVar.f919f = new HashMap();
        if (userInfoBean.f413p >= 0) {
            Map<String, String> map = arVar.f919f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.f413p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.f414q >= 0) {
            Map<String, String> map2 = arVar.f919f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.f414q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.f415r != null && userInfoBean.f415r.size() > 0) {
            for (Map.Entry next : userInfoBean.f415r.entrySet()) {
                Map<String, String> map3 = arVar.f919f;
                map3.put("C03_" + ((String) next.getKey()), next.getValue());
            }
        }
        if (userInfoBean.f416s != null && userInfoBean.f416s.size() > 0) {
            for (Map.Entry next2 : userInfoBean.f416s.entrySet()) {
                Map<String, String> map4 = arVar.f919f;
                map4.put("C04_" + ((String) next2.getKey()), next2.getValue());
            }
        }
        Map<String, String> map5 = arVar.f919f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.f409l);
        map5.put("A36", sb3.toString());
        Map<String, String> map6 = arVar.f919f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.f404g);
        map6.put("F02", sb4.toString());
        Map<String, String> map7 = arVar.f919f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.f405h);
        map7.put("F03", sb5.toString());
        Map<String, String> map8 = arVar.f919f;
        map8.put("F04", userInfoBean.f407j);
        Map<String, String> map9 = arVar.f919f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.f406i);
        map9.put("F05", sb6.toString());
        Map<String, String> map10 = arVar.f919f;
        map10.put("F06", userInfoBean.f410m);
        Map<String, String> map11 = arVar.f919f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.f408k);
        map11.put("F10", sb7.toString());
        C3749y.m939c("summary type %d vm:%d", Byte.valueOf(arVar.f915b), Integer.valueOf(arVar.f919f.size()));
        return arVar;
    }

    /* renamed from: b */
    public static Proxy m631b() {
        return f773e;
    }

    /* renamed from: a */
    public static ActivityManager.ProcessErrorStateInfo m618a(ActivityManager activityManager, long j) {
        ActivityManager.ProcessErrorStateInfo processErrorStateInfo;
        if (activityManager == null) {
            C3749y.m939c("get anr state, ActivityManager is null", new Object[0]);
            return null;
        }
        C3749y.m939c("get anr state, timeout:%d", Long.valueOf(j));
        long j2 = j / 500;
        int i = 0;
        while (true) {
            List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState == null || processesInErrorState.isEmpty()) {
                C3749y.m939c("error state info list is null", new Object[0]);
            } else {
                int myPid = Process.myPid();
                Iterator<ActivityManager.ProcessErrorStateInfo> it = processesInErrorState.iterator();
                while (it.hasNext()) {
                    processErrorStateInfo = it.next();
                    if (processErrorStateInfo.pid == myPid) {
                        C3749y.m939c("found current proc in the error state", new Object[0]);
                        break;
                    }
                }
                C3749y.m939c("current proc not in the error state", new Object[0]);
            }
            processErrorStateInfo = null;
            if (processErrorStateInfo == null) {
                C3749y.m939c("found proc state is null", new Object[0]);
            } else if (processErrorStateInfo.condition == 2) {
                C3749y.m939c("found proc state is anr! proc:%s", processErrorStateInfo.processName);
                return processErrorStateInfo;
            } else if (processErrorStateInfo.condition == 1) {
                C3749y.m939c("found proc state is crashed!", new Object[0]);
                return null;
            }
            int i2 = i + 1;
            if (((long) i) < j2) {
                C3749y.m939c("try the %s times:", Integer.valueOf(i2));
                C3695ab.m687b(500);
                i = i2;
            } else {
                ActivityManager.ProcessErrorStateInfo processErrorStateInfo2 = new ActivityManager.ProcessErrorStateInfo();
                processErrorStateInfo2.pid = Process.myPid();
                processErrorStateInfo2.processName = AppInfo.m326a(Process.myPid());
                processErrorStateInfo2.shortMsg = "Find process anr, but unable to get anr message.";
                return processErrorStateInfo2;
            }
        }
    }

    /* renamed from: a */
    public static String m623a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            String str = "map";
            if (i < arrayList.size()) {
                String str2 = arrayList.get(i);
                if (str2.equals("java.lang.Integer") || str2.equals("int")) {
                    str = "int32";
                } else if (str2.equals("java.lang.Boolean") || str2.equals("boolean")) {
                    str = "bool";
                } else if (str2.equals("java.lang.Byte") || str2.equals("byte")) {
                    str = "char";
                } else if (str2.equals("java.lang.Double") || str2.equals("double")) {
                    str = "double";
                } else if (str2.equals("java.lang.Float") || str2.equals("float")) {
                    str = "float";
                } else if (str2.equals("java.lang.Long") || str2.equals("long")) {
                    str = "int64";
                } else if (str2.equals("java.lang.Short") || str2.equals("short")) {
                    str = "short";
                } else if (str2.equals("java.lang.Character")) {
                    throw new IllegalArgumentException("can not support java.lang.Character");
                } else if (str2.equals("java.lang.String")) {
                    str = "string";
                } else if (str2.equals("java.util.List")) {
                    str = "list";
                } else if (!str2.equals("java.util.Map")) {
                    str = str2;
                }
                arrayList.set(i, str);
                i++;
            } else {
                Collections.reverse(arrayList);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    String str3 = arrayList.get(i2);
                    if (str3.equals("list")) {
                        int i3 = i2 - 1;
                        arrayList.set(i3, "<" + arrayList.get(i3));
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals(str)) {
                        int i4 = i2 - 1;
                        arrayList.set(i4, "<" + arrayList.get(i4) + CSVUtil.COLUMN_SEPARATOR);
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals("Array")) {
                        int i5 = i2 - 1;
                        arrayList.set(i5, "<" + arrayList.get(i5));
                        arrayList.set(0, arrayList.get(0) + ">");
                    }
                }
                Collections.reverse(arrayList);
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(it.next());
                }
                return stringBuffer.toString();
            }
        }
    }

    /* renamed from: a */
    public <T> void mo24221a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (!(t instanceof Set)) {
            C3722i iVar = new C3722i();
            iVar.mo24268a(this.f775b);
            iVar.mo24274a((Object) t, 0);
            byte[] a = C3724k.m814a(iVar.mo24269a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            m626a((ArrayList<String>) arrayList, (Object) t);
            hashMap.put(m623a((ArrayList<String>) arrayList), a);
            this.f777d.remove(str);
            this.f774a.put(str, hashMap);
        } else {
            throw new IllegalArgumentException("can not support Set");
        }
    }

    /* renamed from: a */
    public static C3713as m621a(List<UserInfoBean> list, int i) {
        C3626a b;
        if (list == null || list.size() == 0 || (b = C3626a.m339b()) == null) {
            return null;
        }
        b.mo24086s();
        C3713as asVar = new C3713as();
        asVar.f925b = b.f496d;
        asVar.f926c = b.mo24079k();
        ArrayList<C3712ar> arrayList = new ArrayList<>();
        for (UserInfoBean a : list) {
            C3712ar a2 = m620a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        asVar.f927d = arrayList;
        asVar.f928e = new HashMap();
        Map<String, String> map = asVar.f928e;
        StringBuilder sb = new StringBuilder();
        b.getClass();
        map.put("A7", sb.toString());
        Map<String, String> map2 = asVar.f928e;
        map2.put("A6", C3626a.m340r());
        Map<String, String> map3 = asVar.f928e;
        map3.put("A5", b.mo24085q());
        Map<String, String> map4 = asVar.f928e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(b.mo24083o());
        map4.put("A2", sb2.toString());
        Map<String, String> map5 = asVar.f928e;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(b.mo24083o());
        map5.put("A1", sb3.toString());
        Map<String, String> map6 = asVar.f928e;
        map6.put("A24", b.f499g);
        Map<String, String> map7 = asVar.f928e;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(b.mo24084p());
        map7.put("A17", sb4.toString());
        Map<String, String> map8 = asVar.f928e;
        map8.put("A15", b.mo24088u());
        Map<String, String> map9 = asVar.f928e;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(b.mo24089v());
        map9.put("A13", sb5.toString());
        Map<String, String> map10 = asVar.f928e;
        map10.put("F08", b.f513u);
        Map<String, String> map11 = asVar.f928e;
        map11.put("F09", b.f514v);
        Map<String, String> C = b.mo24048C();
        if (C != null && C.size() > 0) {
            for (Map.Entry next : C.entrySet()) {
                Map<String, String> map12 = asVar.f928e;
                map12.put("C04_" + ((String) next.getKey()), next.getValue());
            }
        }
        if (i == 1) {
            asVar.f924a = 1;
        } else if (i != 2) {
            C3749y.m941e("unknown up type %d ", Integer.valueOf(i));
            return null;
        } else {
            asVar.f924a = 2;
        }
        return asVar;
    }

    /* renamed from: a */
    public static <T extends C3723j> T m622a(byte[] bArr, Class<T> cls) {
        if (bArr != null && bArr.length > 0) {
            try {
                T t = (C3723j) cls.newInstance();
                C3720h hVar = new C3720h(bArr);
                hVar.mo24258a("utf-8");
                t.mo24243a(hVar);
                return t;
            } catch (Throwable th) {
                if (!C3749y.m938b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x01a0, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x01a5, code lost:
        if (com.tencent.bugly.proguard.C3749y.m938b(r5) == false) goto L_0x01a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01a7, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x01aa, code lost:
        return null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.bugly.proguard.C3708an m619a(android.content.Context r5, int r6, byte[] r7) {
        /*
            com.tencent.bugly.crashreport.common.info.a r0 = com.tencent.bugly.crashreport.common.info.C3626a.m339b()
            com.tencent.bugly.crashreport.common.strategy.a r1 = com.tencent.bugly.crashreport.common.strategy.C3644a.m426a()
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r1 = r1.mo24103c()
            r2 = 0
            if (r0 == 0) goto L_0x01ab
            if (r1 != 0) goto L_0x0013
            goto L_0x01ab
        L_0x0013:
            com.tencent.bugly.proguard.an r3 = new com.tencent.bugly.proguard.an     // Catch:{ all -> 0x01a0 }
            r3.<init>()     // Catch:{ all -> 0x01a0 }
            monitor-enter(r0)     // Catch:{ all -> 0x01a0 }
            r4 = 1
            r3.f862a = r4     // Catch:{ all -> 0x019d }
            java.lang.String r4 = r0.mo24070f()     // Catch:{ all -> 0x019d }
            r3.f863b = r4     // Catch:{ all -> 0x019d }
            java.lang.String r4 = r0.f495c     // Catch:{ all -> 0x019d }
            r3.f864c = r4     // Catch:{ all -> 0x019d }
            java.lang.String r4 = r0.f501i     // Catch:{ all -> 0x019d }
            r3.f865d = r4     // Catch:{ all -> 0x019d }
            java.lang.String r4 = r0.f503k     // Catch:{ all -> 0x019d }
            r3.f866e = r4     // Catch:{ all -> 0x019d }
            java.lang.String r4 = r0.f498f     // Catch:{ all -> 0x019d }
            r3.f867f = r4     // Catch:{ all -> 0x019d }
            r3.f868g = r6     // Catch:{ all -> 0x019d }
            if (r7 != 0) goto L_0x003c
            java.lang.String r6 = ""
            byte[] r7 = r6.getBytes()     // Catch:{ all -> 0x019d }
        L_0x003c:
            r3.f869h = r7     // Catch:{ all -> 0x019d }
            java.lang.String r6 = r0.mo24080l()     // Catch:{ all -> 0x019d }
            r3.f870i = r6     // Catch:{ all -> 0x019d }
            java.lang.String r6 = r0.f499g     // Catch:{ all -> 0x019d }
            r3.f871j = r6     // Catch:{ all -> 0x019d }
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x019d }
            r6.<init>()     // Catch:{ all -> 0x019d }
            r3.f872k = r6     // Catch:{ all -> 0x019d }
            java.lang.String r6 = r0.mo24068e()     // Catch:{ all -> 0x019d }
            r3.f873l = r6     // Catch:{ all -> 0x019d }
            long r6 = r1.f535n     // Catch:{ all -> 0x019d }
            r3.f874m = r6     // Catch:{ all -> 0x019d }
            java.lang.String r6 = r0.mo24079k()     // Catch:{ all -> 0x019d }
            r3.f876o = r6     // Catch:{ all -> 0x019d }
            java.lang.String r5 = com.tencent.bugly.crashreport.common.info.C3627b.m393b(r5)     // Catch:{ all -> 0x019d }
            r3.f877p = r5     // Catch:{ all -> 0x019d }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x019d }
            r3.f878q = r5     // Catch:{ all -> 0x019d }
            java.lang.String r5 = r0.mo24081m()     // Catch:{ all -> 0x019d }
            r3.f879r = r5     // Catch:{ all -> 0x019d }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x019d }
            r5.<init>()     // Catch:{ all -> 0x019d }
            java.lang.String r6 = r0.mo24079k()     // Catch:{ all -> 0x019d }
            r5.append(r6)     // Catch:{ all -> 0x019d }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x019d }
            r3.f880s = r5     // Catch:{ all -> 0x019d }
            java.lang.String r5 = r3.f877p     // Catch:{ all -> 0x019d }
            r3.f881t = r5     // Catch:{ all -> 0x019d }
            r0.getClass()     // Catch:{ all -> 0x019d }
            java.lang.String r5 = "com.tencent.bugly"
            r3.f875n = r5     // Catch:{ all -> 0x019d }
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r6 = "A26"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x019d }
            r7.<init>()     // Catch:{ all -> 0x019d }
            java.lang.String r1 = r0.mo24090w()     // Catch:{ all -> 0x019d }
            r7.append(r1)     // Catch:{ all -> 0x019d }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x019d }
            r5.put(r6, r7)     // Catch:{ all -> 0x019d }
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r6 = "A62"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x019d }
            r7.<init>()     // Catch:{ all -> 0x019d }
            boolean r1 = com.tencent.bugly.crashreport.common.info.C3626a.m334H()     // Catch:{ all -> 0x019d }
            r7.append(r1)     // Catch:{ all -> 0x019d }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x019d }
            r5.put(r6, r7)     // Catch:{ all -> 0x019d }
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r6 = "A63"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x019d }
            r7.<init>()     // Catch:{ all -> 0x019d }
            boolean r1 = com.tencent.bugly.crashreport.common.info.C3626a.m335I()     // Catch:{ all -> 0x019d }
            r7.append(r1)     // Catch:{ all -> 0x019d }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x019d }
            r5.put(r6, r7)     // Catch:{ all -> 0x019d }
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r6 = "F11"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x019d }
            r7.<init>()     // Catch:{ all -> 0x019d }
            boolean r1 = r0.f518z     // Catch:{ all -> 0x019d }
            r7.append(r1)     // Catch:{ all -> 0x019d }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x019d }
            r5.put(r6, r7)     // Catch:{ all -> 0x019d }
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r6 = "F12"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x019d }
            r7.<init>()     // Catch:{ all -> 0x019d }
            boolean r1 = r0.f517y     // Catch:{ all -> 0x019d }
            r7.append(r1)     // Catch:{ all -> 0x019d }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x019d }
            r5.put(r6, r7)     // Catch:{ all -> 0x019d }
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r6 = "D3"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x019d }
            r7.<init>()     // Catch:{ all -> 0x019d }
            java.lang.String r1 = r0.f502j     // Catch:{ all -> 0x019d }
            r7.append(r1)     // Catch:{ all -> 0x019d }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x019d }
            r5.put(r6, r7)     // Catch:{ all -> 0x019d }
            java.util.List<com.tencent.bugly.a> r5 = com.tencent.bugly.C3612b.f391b     // Catch:{ all -> 0x019d }
            if (r5 == 0) goto L_0x013a
            java.util.List<com.tencent.bugly.a> r5 = com.tencent.bugly.C3612b.f391b     // Catch:{ all -> 0x019d }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x019d }
        L_0x011c:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x019d }
            if (r6 == 0) goto L_0x013a
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x019d }
            com.tencent.bugly.a r6 = (com.tencent.bugly.C3611a) r6     // Catch:{ all -> 0x019d }
            java.lang.String r7 = r6.versionKey     // Catch:{ all -> 0x019d }
            if (r7 == 0) goto L_0x011c
            java.lang.String r7 = r6.version     // Catch:{ all -> 0x019d }
            if (r7 == 0) goto L_0x011c
            java.util.Map<java.lang.String, java.lang.String> r7 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r1 = r6.versionKey     // Catch:{ all -> 0x019d }
            java.lang.String r6 = r6.version     // Catch:{ all -> 0x019d }
            r7.put(r1, r6)     // Catch:{ all -> 0x019d }
            goto L_0x011c
        L_0x013a:
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r6 = "G15"
            java.lang.String r7 = "G15"
            java.lang.String r1 = ""
            java.lang.String r7 = com.tencent.bugly.proguard.C3695ab.m693c(r7, r1)     // Catch:{ all -> 0x019d }
            r5.put(r6, r7)     // Catch:{ all -> 0x019d }
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r6 = "G10"
            java.lang.String r7 = "G10"
            java.lang.String r1 = ""
            java.lang.String r7 = com.tencent.bugly.proguard.C3695ab.m693c(r7, r1)     // Catch:{ all -> 0x019d }
            r5.put(r6, r7)     // Catch:{ all -> 0x019d }
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.f872k     // Catch:{ all -> 0x019d }
            java.lang.String r6 = "D4"
            java.lang.String r7 = "D4"
            java.lang.String r1 = "0"
            java.lang.String r7 = com.tencent.bugly.proguard.C3695ab.m693c(r7, r1)     // Catch:{ all -> 0x019d }
            r5.put(r6, r7)     // Catch:{ all -> 0x019d }
            monitor-exit(r0)     // Catch:{ all -> 0x019d }
            java.util.Map r5 = r0.mo24047B()     // Catch:{ all -> 0x01a0 }
            if (r5 == 0) goto L_0x019c
            java.util.Set r5 = r5.entrySet()     // Catch:{ all -> 0x01a0 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x01a0 }
        L_0x0176:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x01a0 }
            if (r6 == 0) goto L_0x019c
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x01a0 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ all -> 0x01a0 }
            java.lang.Object r7 = r6.getValue()     // Catch:{ all -> 0x01a0 }
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ all -> 0x01a0 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x01a0 }
            if (r7 != 0) goto L_0x0176
            java.util.Map<java.lang.String, java.lang.String> r7 = r3.f872k     // Catch:{ all -> 0x01a0 }
            java.lang.Object r0 = r6.getKey()     // Catch:{ all -> 0x01a0 }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x01a0 }
            r7.put(r0, r6)     // Catch:{ all -> 0x01a0 }
            goto L_0x0176
        L_0x019c:
            return r3
        L_0x019d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x01a0 }
            throw r5     // Catch:{ all -> 0x01a0 }
        L_0x01a0:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.C3749y.m938b(r5)
            if (r6 != 0) goto L_0x01aa
            r5.printStackTrace()
        L_0x01aa:
            return r2
        L_0x01ab:
            java.lang.String r5 = "Can not create request pkg for parameters is invalid."
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            com.tencent.bugly.proguard.C3749y.m941e(r5, r6)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3691a.m619a(android.content.Context, int, byte[]):com.tencent.bugly.proguard.an");
    }

    /* renamed from: a */
    private void m626a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                m626a(arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add("?");
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                m626a(arrayList, list.get(0));
            } else {
                arrayList.add("?");
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                m626a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    /* renamed from: a */
    public byte[] mo24223a() {
        C3722i iVar = new C3722i(0);
        iVar.mo24268a(this.f775b);
        iVar.mo24277a(this.f774a, 0);
        return C3724k.m814a(iVar.mo24269a());
    }

    /* renamed from: a */
    public static byte[] m629a(Object obj) {
        try {
            C3716d dVar = new C3716d();
            dVar.mo24250c();
            dVar.mo24220a("utf-8");
            dVar.mo24251a(1);
            dVar.mo24252b("RqdServer");
            dVar.mo24253c("sync");
            dVar.mo24221a("detail", obj);
            return dVar.mo24223a();
        } catch (Throwable th) {
            if (C3749y.m938b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void mo24222a(byte[] bArr) {
        this.f776c.mo24264a(bArr);
        this.f776c.mo24258a(this.f775b);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.f774a = this.f776c.mo24262a(hashMap, 0, false);
    }

    /* renamed from: b */
    public static C3709ao m630b(byte[] bArr) {
        if (bArr != null) {
            try {
                C3716d dVar = new C3716d();
                dVar.mo24250c();
                dVar.mo24220a("utf-8");
                dVar.mo24222a(bArr);
                Object b = dVar.mo24249b("detail", new C3709ao());
                if (C3709ao.class.isInstance(b)) {
                    return C3709ao.class.cast(b);
                }
                return null;
            } catch (Throwable th) {
                if (!C3749y.m938b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static byte[] m628a(C3723j jVar) {
        try {
            C3722i iVar = new C3722i();
            iVar.mo24268a("utf-8");
            jVar.mo24244a(iVar);
            return iVar.mo24281b();
        } catch (Throwable th) {
            if (C3749y.m938b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
