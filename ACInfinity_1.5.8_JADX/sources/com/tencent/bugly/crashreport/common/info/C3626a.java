package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.text.TextUtils;
import androidx.core.p003os.EnvironmentCompat;
import com.eternal.export.CSVUtil;
import com.tencent.bugly.C3612b;
import com.tencent.bugly.crashreport.C3614a;
import com.tencent.bugly.proguard.C3695ab;
import com.tencent.bugly.proguard.C3749y;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* renamed from: com.tencent.bugly.crashreport.common.info.a */
/* compiled from: BUGLY */
public final class C3626a {

    /* renamed from: F */
    private static final Map<String, String> f449F = new HashMap();

    /* renamed from: aa */
    private static C3626a f450aa = null;

    /* renamed from: A */
    public HashMap<String, String> f451A = new HashMap<>();

    /* renamed from: B */
    public List<String> f452B = new ArrayList();

    /* renamed from: C */
    public boolean f453C = false;

    /* renamed from: D */
    public C3614a f454D = null;

    /* renamed from: E */
    public final SharedPreferences f455E;

    /* renamed from: G */
    private final Context f456G;

    /* renamed from: H */
    private String f457H;

    /* renamed from: I */
    private String f458I;

    /* renamed from: J */
    private String f459J;

    /* renamed from: K */
    private String f460K = EnvironmentCompat.MEDIA_UNKNOWN;

    /* renamed from: L */
    private String f461L = "";

    /* renamed from: M */
    private String f462M = null;

    /* renamed from: N */
    private long f463N = -1;

    /* renamed from: O */
    private long f464O = -1;

    /* renamed from: P */
    private long f465P = -1;

    /* renamed from: Q */
    private String f466Q = null;

    /* renamed from: R */
    private String f467R = null;

    /* renamed from: S */
    private Map<String, PlugInBean> f468S = null;

    /* renamed from: T */
    private boolean f469T = false;

    /* renamed from: U */
    private String f470U = null;

    /* renamed from: V */
    private String f471V = null;

    /* renamed from: W */
    private Boolean f472W = null;

    /* renamed from: X */
    private String f473X = null;

    /* renamed from: Y */
    private Map<String, PlugInBean> f474Y = null;

    /* renamed from: Z */
    private Map<String, PlugInBean> f475Z = null;

    /* renamed from: a */
    public final long f476a = System.currentTimeMillis();

    /* renamed from: ab */
    private int f477ab = -1;

    /* renamed from: ac */
    private int f478ac = -1;

    /* renamed from: ad */
    private final Map<String, String> f479ad = new HashMap();

    /* renamed from: ae */
    private final Map<String, String> f480ae = new HashMap();

    /* renamed from: af */
    private final Map<String, String> f481af = new HashMap();

    /* renamed from: ag */
    private SharedPreferences f482ag;

    /* renamed from: ah */
    private boolean f483ah = true;

    /* renamed from: ai */
    private boolean f484ai = true;

    /* renamed from: aj */
    private boolean f485aj = false;

    /* renamed from: ak */
    private final Object f486ak = new Object();

    /* renamed from: al */
    private final Object f487al = new Object();

    /* renamed from: am */
    private final Object f488am = new Object();

    /* renamed from: an */
    private final Object f489an = new Object();

    /* renamed from: ao */
    private final Object f490ao = new Object();

    /* renamed from: ap */
    private final Object f491ap = new Object();

    /* renamed from: aq */
    private final Object f492aq = new Object();

    /* renamed from: ar */
    private final List<Integer> f493ar = new ArrayList();

    /* renamed from: b */
    public final byte f494b;

    /* renamed from: c */
    public String f495c;

    /* renamed from: d */
    public final String f496d;

    /* renamed from: e */
    public boolean f497e = true;

    /* renamed from: f */
    public String f498f = "4.0.4";

    /* renamed from: g */
    public final String f499g;

    /* renamed from: h */
    public long f500h;

    /* renamed from: i */
    public String f501i = null;

    /* renamed from: j */
    public String f502j = null;

    /* renamed from: k */
    public String f503k = null;

    /* renamed from: l */
    public String f504l = null;

    /* renamed from: m */
    public String f505m = null;

    /* renamed from: n */
    public List<String> f506n = null;

    /* renamed from: o */
    public String f507o = EnvironmentCompat.MEDIA_UNKNOWN;

    /* renamed from: p */
    public long f508p = 0;

    /* renamed from: q */
    public long f509q = 0;

    /* renamed from: r */
    public long f510r = 0;

    /* renamed from: s */
    public long f511s = 0;

    /* renamed from: t */
    public boolean f512t = false;

    /* renamed from: u */
    public String f513u = null;

    /* renamed from: v */
    public String f514v = null;

    /* renamed from: w */
    public String f515w = null;

    /* renamed from: x */
    public String f516x = "";

    /* renamed from: y */
    public boolean f517y = false;

    /* renamed from: z */
    public boolean f518z = false;

    @Deprecated
    /* renamed from: r */
    public static String m340r() {
        return "";
    }

    private C3626a(Context context) {
        this.f456G = C3695ab.m653a(context);
        this.f494b = 1;
        PackageInfo b = AppInfo.m329b(context);
        if (b != null) {
            try {
                String str = b.versionName;
                this.f501i = str;
                this.f513u = str;
                this.f514v = Integer.toString(b.versionCode);
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
        this.f495c = AppInfo.m327a(context);
        this.f496d = AppInfo.m326a(Process.myPid());
        this.f502j = AppInfo.m330c(context);
        this.f499g = "Android " + C3627b.m392b() + ",level " + C3627b.m394c();
        Map<String, String> d = AppInfo.m331d(context);
        if (d != null) {
            try {
                this.f506n = AppInfo.m328a(d);
                String str2 = d.get("BUGLY_APPID");
                if (str2 != null) {
                    this.f471V = str2;
                    mo24065c("APP_ID", str2);
                }
                String str3 = d.get("BUGLY_APP_VERSION");
                if (str3 != null) {
                    this.f501i = str3;
                }
                String str4 = d.get("BUGLY_APP_CHANNEL");
                if (str4 != null) {
                    this.f503k = str4;
                }
                String str5 = d.get("BUGLY_ENABLE_DEBUG");
                if (str5 != null) {
                    this.f512t = str5.equalsIgnoreCase("true");
                }
                String str6 = d.get("com.tencent.rdm.uuid");
                if (str6 != null) {
                    this.f515w = str6;
                }
                String str7 = d.get("BUGLY_APP_BUILD_NO");
                if (!TextUtils.isEmpty(str7)) {
                    Integer.parseInt(str7);
                }
                String str8 = d.get("BUGLY_AREA");
                if (str8 != null) {
                    this.f516x = str8;
                }
            } catch (Throwable th2) {
                if (!C3749y.m935a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.f518z = true;
                C3749y.m939c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th3) {
            if (C3612b.f392c) {
                th3.printStackTrace();
            }
        }
        this.f455E = C3695ab.m654a("BUGLY_COMMON_VALUES", context);
        this.f482ag = C3695ab.m654a("BUGLY_RESERVED_VALUES", context);
        this.f467R = C3627b.m390a(context);
        m336J();
        C3749y.m939c("com info create end", new Object[0]);
    }

    /* renamed from: J */
    private void m336J() {
        try {
            for (Map.Entry next : this.f482ag.getAll().entrySet()) {
                C3749y.m939c("put reserved request data from sp, key:%s value:%s", next.getKey(), next.getValue());
                m338a((String) next.getKey(), next.getValue().toString(), false);
            }
            for (Map.Entry next2 : f449F.entrySet()) {
                C3749y.m939c("put reserved request data from cache, key:%s value:%s", next2.getKey(), next2.getValue());
                m338a((String) next2.getKey(), (String) next2.getValue(), true);
            }
            f449F.clear();
        } catch (Throwable th) {
            C3749y.m938b(th);
        }
    }

    /* renamed from: a */
    public final boolean mo24058a() {
        boolean z = this.f493ar.size() > 0;
        C3749y.m939c("isAppForeground:%s", Boolean.valueOf(z));
        return z;
    }

    /* renamed from: a */
    public final void mo24053a(int i, boolean z) {
        boolean z2 = false;
        C3749y.m939c("setActivityForeState, hash:%s isFore:%s", Integer.valueOf(i), Boolean.valueOf(z));
        if (z) {
            this.f493ar.add(Integer.valueOf(i));
        } else {
            this.f493ar.remove(Integer.valueOf(i));
            this.f493ar.remove(0);
        }
        C3614a aVar = this.f454D;
        if (aVar != null) {
            if (this.f493ar.size() > 0) {
                z2 = true;
            }
            aVar.setNativeIsAppForeground(z2);
        }
    }

    /* renamed from: a */
    public static synchronized C3626a m337a(Context context) {
        C3626a aVar;
        synchronized (C3626a.class) {
            if (f450aa == null) {
                f450aa = new C3626a(context);
            }
            aVar = f450aa;
        }
        return aVar;
    }

    /* renamed from: b */
    public static synchronized C3626a m339b() {
        C3626a aVar;
        synchronized (C3626a.class) {
            aVar = f450aa;
        }
        return aVar;
    }

    /* renamed from: c */
    public final String mo24063c() {
        return this.f498f;
    }

    /* renamed from: d */
    public final void mo24066d() {
        synchronized (this.f486ak) {
            this.f457H = UUID.randomUUID().toString();
        }
    }

    /* renamed from: e */
    public final String mo24068e() {
        String str;
        synchronized (this.f486ak) {
            if (this.f457H == null) {
                synchronized (this.f486ak) {
                    this.f457H = UUID.randomUUID().toString();
                }
            }
            str = this.f457H;
        }
        return str;
    }

    /* renamed from: f */
    public final String mo24070f() {
        if (!C3695ab.m679a((String) null)) {
            return null;
        }
        return this.f471V;
    }

    /* renamed from: a */
    public final void mo24054a(String str) {
        this.f471V = str;
        mo24065c("APP_ID", str);
    }

    /* renamed from: g */
    public final String mo24072g() {
        String str;
        synchronized (this.f491ap) {
            str = this.f460K;
        }
        return str;
    }

    /* renamed from: b */
    public final void mo24060b(String str) {
        synchronized (this.f491ap) {
            if (str == null) {
                str = "10000";
            }
            this.f460K = str;
        }
    }

    /* renamed from: a */
    public final void mo24056a(boolean z) {
        this.f469T = z;
    }

    /* renamed from: a */
    public final void mo24057a(boolean z, boolean z2) {
        this.f483ah = z;
        this.f484ai = z2;
    }

    /* renamed from: b */
    public final void mo24062b(boolean z) {
        this.f485aj = z;
    }

    /* renamed from: h */
    public final boolean mo24075h() {
        return this.f483ah;
    }

    /* renamed from: i */
    public final boolean mo24077i() {
        return this.f484ai;
    }

    /* renamed from: j */
    public final boolean mo24078j() {
        return this.f485aj;
    }

    /* renamed from: k */
    public final String mo24079k() {
        String str = this.f459J;
        if (str != null) {
            return str;
        }
        String c = C3695ab.m693c("deviceId", (String) null);
        this.f459J = c;
        if (c != null) {
            return c;
        }
        if (TextUtils.isEmpty(this.f462M)) {
            this.f462M = C3695ab.m693c("androidid", (String) null);
        }
        String str2 = this.f462M;
        this.f459J = str2;
        if (TextUtils.isEmpty(str2)) {
            String uuid = UUID.randomUUID().toString();
            if (!C3695ab.m679a(uuid)) {
                uuid = uuid.replaceAll("-", "");
            }
            this.f459J = uuid;
        }
        String str3 = this.f459J;
        if (str3 == null) {
            return "";
        }
        C3695ab.m690b("deviceId", str3);
        return this.f459J;
    }

    /* renamed from: c */
    public final void mo24064c(String str) {
        this.f459J = str;
        if (!TextUtils.isEmpty(str)) {
            C3695ab.m690b("deviceId", str);
        }
        synchronized (this.f492aq) {
            this.f480ae.put("E8", str);
        }
    }

    /* renamed from: l */
    public final String mo24080l() {
        String str = this.f458I;
        if (str != null) {
            return str;
        }
        String c = C3695ab.m693c("deviceModel", (String) null);
        this.f458I = c;
        if (c != null) {
            C3749y.m939c("collect device model from sp:%s", c);
            return this.f458I;
        } else if (!this.f469T) {
            C3749y.m939c("not allow collect device model", new Object[0]);
            return "fail";
        } else {
            String a = C3627b.m389a();
            this.f458I = a;
            C3749y.m939c("collect device model:%s", a);
            C3695ab.m690b("deviceModel", this.f458I);
            return this.f458I;
        }
    }

    /* renamed from: d */
    public final void mo24067d(String str) {
        C3749y.m934a("change deviceModel，old:%s new:%s", this.f458I, str);
        this.f458I = str;
        if (!TextUtils.isEmpty(str)) {
            C3695ab.m690b("deviceModel", str);
        }
    }

    /* renamed from: e */
    public final synchronized void mo24069e(String str) {
    }

    /* renamed from: m */
    public final synchronized String mo24081m() {
        return this.f461L;
    }

    /* renamed from: f */
    public final synchronized void mo24071f(String str) {
        this.f461L = str;
    }

    /* renamed from: n */
    public final long mo24082n() {
        if (this.f463N <= 0) {
            this.f463N = C3627b.m396d();
        }
        return this.f463N;
    }

    /* renamed from: o */
    public final long mo24083o() {
        if (this.f464O <= 0) {
            this.f464O = C3627b.m403i();
        }
        return this.f464O;
    }

    /* renamed from: p */
    public final long mo24084p() {
        if (this.f465P <= 0) {
            this.f465P = C3627b.m405k();
        }
        return this.f465P;
    }

    /* renamed from: q */
    public final String mo24085q() {
        if (!TextUtils.isEmpty(this.f466Q)) {
            C3749y.m939c("get cpu type from so:%s", this.f466Q);
            return this.f466Q;
        } else if (TextUtils.isEmpty(this.f467R)) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } else {
            C3749y.m939c("get cpu type from lib dir:%s", this.f467R);
            return this.f467R;
        }
    }

    /* renamed from: g */
    public final void mo24073g(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f466Q = str.trim();
        }
    }

    /* renamed from: a */
    public final void mo24055a(String str, String str2) {
        if (str != null && str2 != null) {
            synchronized (this.f487al) {
                this.f451A.put(str, str2);
            }
        }
    }

    /* renamed from: s */
    public final String mo24086s() {
        try {
            Map<String, ?> all = this.f456G.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.f487al) {
                    for (Map.Entry next : all.entrySet()) {
                        try {
                            this.f451A.put(next.getKey(), next.getValue().toString());
                        } catch (Throwable th) {
                            C3749y.m935a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            C3749y.m935a(th2);
        }
        if (!this.f451A.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry next2 : this.f451A.entrySet()) {
                sb.append("[");
                sb.append((String) next2.getKey());
                sb.append(CSVUtil.COLUMN_SEPARATOR);
                sb.append((String) next2.getValue());
                sb.append("] ");
            }
            C3749y.m939c("SDK_INFO = %s", sb.toString());
            mo24065c("SDK_INFO", sb.toString());
            return sb.toString();
        }
        C3749y.m939c("SDK_INFO is empty", new Object[0]);
        return null;
    }

    /* renamed from: t */
    public final synchronized Map<String, PlugInBean> mo24087t() {
        return null;
    }

    /* renamed from: u */
    public final String mo24088u() {
        if (this.f470U == null) {
            this.f470U = C3627b.m407m();
        }
        return this.f470U;
    }

    /* renamed from: v */
    public final Boolean mo24089v() {
        if (this.f472W == null) {
            this.f472W = Boolean.valueOf(C3627b.m408n());
        }
        return this.f472W;
    }

    /* renamed from: w */
    public final String mo24090w() {
        if (this.f473X == null) {
            String str = C3627b.m395c(this.f456G);
            this.f473X = str;
            C3749y.m934a("ROM ID: %s", str);
        }
        return this.f473X;
    }

    /* renamed from: x */
    public final Map<String, String> mo24091x() {
        synchronized (this.f488am) {
            if (this.f479ad.size() <= 0) {
                return null;
            }
            HashMap hashMap = new HashMap(this.f479ad);
            return hashMap;
        }
    }

    /* renamed from: h */
    public final String mo24074h(String str) {
        String remove;
        if (C3695ab.m679a(str)) {
            C3749y.m940d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.f488am) {
            remove = this.f479ad.remove(str);
        }
        return remove;
    }

    /* renamed from: y */
    public final void mo24092y() {
        synchronized (this.f488am) {
            this.f479ad.clear();
        }
    }

    /* renamed from: i */
    public final String mo24076i(String str) {
        String str2;
        if (C3695ab.m679a(str)) {
            C3749y.m940d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.f488am) {
            str2 = this.f479ad.get(str);
        }
        return str2;
    }

    /* renamed from: b */
    public final void mo24061b(String str, String str2) {
        if (C3695ab.m679a(str) || C3695ab.m679a(str2)) {
            C3749y.m940d("key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.f488am) {
            this.f479ad.put(str, str2);
        }
    }

    /* renamed from: z */
    public final int mo24093z() {
        int size;
        synchronized (this.f488am) {
            size = this.f479ad.size();
        }
        return size;
    }

    /* renamed from: A */
    public final Set<String> mo24046A() {
        Set<String> keySet;
        synchronized (this.f488am) {
            keySet = this.f479ad.keySet();
        }
        return keySet;
    }

    /* renamed from: a */
    private void m338a(String str, String str2, boolean z) {
        if (C3695ab.m679a(str)) {
            C3749y.m940d("key should not be empty %s", str);
            return;
        }
        C3749y.m939c("putExtraRequestData key:%s value:%s save:%s", str, str2, Boolean.valueOf(z));
        synchronized (this.f492aq) {
            if (TextUtils.isEmpty(str2)) {
                this.f480ae.remove(str);
                this.f482ag.edit().remove(str).apply();
            } else {
                this.f480ae.put(str, str2);
                if (z) {
                    this.f482ag.edit().putString(str, str2).apply();
                }
            }
        }
    }

    /* renamed from: B */
    public final Map<String, String> mo24047B() {
        synchronized (this.f492aq) {
            if (this.f480ae.size() <= 0) {
                return null;
            }
            HashMap hashMap = new HashMap(this.f480ae);
            return hashMap;
        }
    }

    /* renamed from: c */
    public final void mo24065c(String str, String str2) {
        if (C3695ab.m679a(str) || C3695ab.m679a(str2)) {
            C3749y.m940d("server key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.f489an) {
            this.f481af.put(str, str2);
        }
    }

    /* renamed from: C */
    public final Map<String, String> mo24048C() {
        synchronized (this.f489an) {
            if (this.f481af.size() <= 0) {
                return null;
            }
            HashMap hashMap = new HashMap(this.f481af);
            return hashMap;
        }
    }

    /* renamed from: a */
    public final void mo24052a(int i) {
        synchronized (this.f490ao) {
            int i2 = this.f477ab;
            if (i2 != i) {
                this.f477ab = i;
                C3749y.m934a("user scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.f477ab));
            }
        }
    }

    /* renamed from: D */
    public final int mo24049D() {
        int i;
        synchronized (this.f490ao) {
            i = this.f477ab;
        }
        return i;
    }

    /* renamed from: b */
    public final void mo24059b(int i) {
        int i2 = this.f478ac;
        if (i2 != 24096) {
            this.f478ac = 24096;
            C3749y.m934a("server scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.f478ac));
        }
    }

    /* renamed from: E */
    public final int mo24050E() {
        return this.f478ac;
    }

    /* renamed from: F */
    public final synchronized Map<String, PlugInBean> mo24051F() {
        return null;
    }

    /* renamed from: G */
    public static int m333G() {
        return C3627b.m394c();
    }

    @Deprecated
    /* renamed from: H */
    public static boolean m334H() {
        C3749y.m934a("Detect if the emulator is unavailable", new Object[0]);
        return false;
    }

    @Deprecated
    /* renamed from: I */
    public static boolean m335I() {
        C3749y.m934a("Detect if the device hook is unavailable", new Object[0]);
        return false;
    }
}
