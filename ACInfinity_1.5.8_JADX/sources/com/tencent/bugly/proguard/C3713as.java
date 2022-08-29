package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.as */
/* compiled from: BUGLY */
public final class C3713as extends C3723j implements Cloneable {

    /* renamed from: f */
    private static ArrayList<C3712ar> f922f;

    /* renamed from: g */
    private static Map<String, String> f923g;

    /* renamed from: a */
    public byte f924a = 0;

    /* renamed from: b */
    public String f925b = "";

    /* renamed from: c */
    public String f926c = "";

    /* renamed from: d */
    public ArrayList<C3712ar> f927d = null;

    /* renamed from: e */
    public Map<String, String> f928e = null;

    /* renamed from: a */
    public final void mo24245a(StringBuilder sb, int i) {
    }

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24270a(this.f924a, 0);
        String str = this.f925b;
        if (str != null) {
            iVar.mo24275a(str, 1);
        }
        String str2 = this.f926c;
        if (str2 != null) {
            iVar.mo24275a(str2, 2);
        }
        ArrayList<C3712ar> arrayList = this.f927d;
        if (arrayList != null) {
            iVar.mo24276a(arrayList, 3);
        }
        Map<String, String> map = this.f928e;
        if (map != null) {
            iVar.mo24277a(map, 4);
        }
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        this.f924a = hVar.mo24256a(this.f924a, 0, true);
        this.f925b = hVar.mo24266b(1, false);
        this.f926c = hVar.mo24266b(2, false);
        if (f922f == null) {
            f922f = new ArrayList<>();
            f922f.add(new C3712ar());
        }
        this.f927d = (ArrayList) hVar.mo24261a(f922f, 3, false);
        if (f923g == null) {
            HashMap hashMap = new HashMap();
            f923g = hashMap;
            hashMap.put("", "");
        }
        this.f928e = (Map) hVar.mo24261a(f923g, 4, false);
    }
}
