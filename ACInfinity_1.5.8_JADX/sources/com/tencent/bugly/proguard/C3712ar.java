package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.ar */
/* compiled from: BUGLY */
public final class C3712ar extends C3723j {

    /* renamed from: i */
    private static Map<String, String> f913i;

    /* renamed from: a */
    public long f914a = 0;

    /* renamed from: b */
    public byte f915b = 0;

    /* renamed from: c */
    public String f916c = "";

    /* renamed from: d */
    public String f917d = "";

    /* renamed from: e */
    public String f918e = "";

    /* renamed from: f */
    public Map<String, String> f919f = null;

    /* renamed from: g */
    public boolean f920g = true;

    /* renamed from: h */
    private String f921h = "";

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24272a(this.f914a, 0);
        iVar.mo24270a(this.f915b, 1);
        String str = this.f916c;
        if (str != null) {
            iVar.mo24275a(str, 2);
        }
        String str2 = this.f917d;
        if (str2 != null) {
            iVar.mo24275a(str2, 3);
        }
        String str3 = this.f918e;
        if (str3 != null) {
            iVar.mo24275a(str3, 4);
        }
        Map<String, String> map = this.f919f;
        if (map != null) {
            iVar.mo24277a(map, 5);
        }
        String str4 = this.f921h;
        if (str4 != null) {
            iVar.mo24275a(str4, 6);
        }
        iVar.mo24279a(this.f920g, 7);
    }

    static {
        HashMap hashMap = new HashMap();
        f913i = hashMap;
        hashMap.put("", "");
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        this.f914a = hVar.mo24259a(this.f914a, 0, true);
        this.f915b = hVar.mo24256a(this.f915b, 1, true);
        this.f916c = hVar.mo24266b(2, false);
        this.f917d = hVar.mo24266b(3, false);
        this.f918e = hVar.mo24266b(4, false);
        this.f919f = (Map) hVar.mo24261a(f913i, 5, false);
        this.f921h = hVar.mo24266b(6, false);
        this.f920g = hVar.mo24265a(7, false);
    }
}
