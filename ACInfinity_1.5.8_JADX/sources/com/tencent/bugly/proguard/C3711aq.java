package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.aq */
/* compiled from: BUGLY */
public final class C3711aq extends C3723j implements Cloneable {

    /* renamed from: m */
    private static C3710ap f898m = new C3710ap();

    /* renamed from: n */
    private static Map<String, String> f899n = null;

    /* renamed from: o */
    private static /* synthetic */ boolean f900o = true;

    /* renamed from: a */
    public boolean f901a = true;

    /* renamed from: b */
    public boolean f902b = true;

    /* renamed from: c */
    public boolean f903c = true;

    /* renamed from: d */
    public String f904d = "";

    /* renamed from: e */
    public String f905e = "";

    /* renamed from: f */
    public C3710ap f906f = null;

    /* renamed from: g */
    public Map<String, String> f907g = null;

    /* renamed from: h */
    public long f908h = 0;

    /* renamed from: i */
    public int f909i = 0;

    /* renamed from: j */
    private String f910j = "";

    /* renamed from: k */
    private String f911k = "";

    /* renamed from: l */
    private int f912l = 0;

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        C3711aq aqVar = (C3711aq) obj;
        if (!C3724k.m813a(this.f901a, aqVar.f901a) || !C3724k.m813a(this.f902b, aqVar.f902b) || !C3724k.m813a(this.f903c, aqVar.f903c) || !C3724k.m812a((Object) this.f904d, (Object) aqVar.f904d) || !C3724k.m812a((Object) this.f905e, (Object) aqVar.f905e) || !C3724k.m812a((Object) this.f906f, (Object) aqVar.f906f) || !C3724k.m812a((Object) this.f907g, (Object) aqVar.f907g) || !C3724k.m811a(this.f908h, aqVar.f908h) || !C3724k.m812a((Object) this.f910j, (Object) aqVar.f910j) || !C3724k.m812a((Object) this.f911k, (Object) aqVar.f911k) || !C3724k.m810a(this.f912l, aqVar.f912l) || !C3724k.m810a(this.f909i, aqVar.f909i)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f900o) {
                return null;
            }
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24279a(this.f901a, 0);
        iVar.mo24279a(this.f902b, 1);
        iVar.mo24279a(this.f903c, 2);
        String str = this.f904d;
        if (str != null) {
            iVar.mo24275a(str, 3);
        }
        String str2 = this.f905e;
        if (str2 != null) {
            iVar.mo24275a(str2, 4);
        }
        C3710ap apVar = this.f906f;
        if (apVar != null) {
            iVar.mo24273a((C3723j) apVar, 5);
        }
        Map<String, String> map = this.f907g;
        if (map != null) {
            iVar.mo24277a(map, 6);
        }
        iVar.mo24272a(this.f908h, 7);
        String str3 = this.f910j;
        if (str3 != null) {
            iVar.mo24275a(str3, 8);
        }
        String str4 = this.f911k;
        if (str4 != null) {
            iVar.mo24275a(str4, 9);
        }
        iVar.mo24271a(this.f912l, 10);
        iVar.mo24271a(this.f909i, 11);
    }

    static {
        HashMap hashMap = new HashMap();
        f899n = hashMap;
        hashMap.put("", "");
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        this.f901a = hVar.mo24265a(0, true);
        this.f902b = hVar.mo24265a(1, true);
        this.f903c = hVar.mo24265a(2, true);
        this.f904d = hVar.mo24266b(3, false);
        this.f905e = hVar.mo24266b(4, false);
        this.f906f = (C3710ap) hVar.mo24260a((C3723j) f898m, 5, false);
        this.f907g = (Map) hVar.mo24261a(f899n, 6, false);
        this.f908h = hVar.mo24259a(this.f908h, 7, false);
        this.f910j = hVar.mo24266b(8, false);
        this.f911k = hVar.mo24266b(9, false);
        this.f912l = hVar.mo24257a(this.f912l, 10, false);
        this.f909i = hVar.mo24257a(this.f909i, 11, false);
    }

    /* renamed from: a */
    public final void mo24245a(StringBuilder sb, int i) {
        C3750z zVar = new C3750z(sb, i);
        zVar.mo24361a(this.f901a, "enable");
        zVar.mo24361a(this.f902b, "enableUserInfo");
        zVar.mo24361a(this.f903c, "enableQuery");
        zVar.mo24357a(this.f904d, "url");
        zVar.mo24357a(this.f905e, "expUrl");
        zVar.mo24355a((C3723j) this.f906f, "security");
        zVar.mo24359a(this.f907g, "valueMap");
        zVar.mo24354a(this.f908h, "strategylastUpdateTime");
        zVar.mo24357a(this.f910j, "httpsUrl");
        zVar.mo24357a(this.f911k, "httpsExpUrl");
        zVar.mo24353a(this.f912l, "eventRecordCount");
        zVar.mo24353a(this.f909i, "eventTimeInterval");
    }
}
