package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.al */
/* compiled from: BUGLY */
public final class C3706al extends C3723j {

    /* renamed from: A */
    private static ArrayList<C3705ak> f829A = new ArrayList<>();

    /* renamed from: B */
    private static Map<String, String> f830B;

    /* renamed from: C */
    private static Map<String, String> f831C;

    /* renamed from: v */
    private static Map<String, String> f832v;

    /* renamed from: w */
    private static C3704aj f833w = new C3704aj();

    /* renamed from: x */
    private static C3703ai f834x = new C3703ai();

    /* renamed from: y */
    private static ArrayList<C3703ai> f835y = new ArrayList<>();

    /* renamed from: z */
    private static ArrayList<C3703ai> f836z = new ArrayList<>();

    /* renamed from: a */
    public String f837a = "";

    /* renamed from: b */
    public long f838b = 0;

    /* renamed from: c */
    public String f839c = "";

    /* renamed from: d */
    public String f840d = "";

    /* renamed from: e */
    public String f841e = "";

    /* renamed from: f */
    public String f842f = "";

    /* renamed from: g */
    public String f843g = "";

    /* renamed from: h */
    public Map<String, String> f844h = null;

    /* renamed from: i */
    public String f845i = "";

    /* renamed from: j */
    public C3704aj f846j = null;

    /* renamed from: k */
    public int f847k = 0;

    /* renamed from: l */
    public String f848l = "";

    /* renamed from: m */
    public String f849m = "";

    /* renamed from: n */
    public C3703ai f850n = null;

    /* renamed from: o */
    public ArrayList<C3703ai> f851o = null;

    /* renamed from: p */
    public ArrayList<C3703ai> f852p = null;

    /* renamed from: q */
    public ArrayList<C3705ak> f853q = null;

    /* renamed from: r */
    public Map<String, String> f854r = null;

    /* renamed from: s */
    public Map<String, String> f855s = null;

    /* renamed from: t */
    private String f856t = "";

    /* renamed from: u */
    private boolean f857u = true;

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24275a(this.f837a, 0);
        iVar.mo24272a(this.f838b, 1);
        iVar.mo24275a(this.f839c, 2);
        String str = this.f840d;
        if (str != null) {
            iVar.mo24275a(str, 3);
        }
        String str2 = this.f841e;
        if (str2 != null) {
            iVar.mo24275a(str2, 4);
        }
        String str3 = this.f842f;
        if (str3 != null) {
            iVar.mo24275a(str3, 5);
        }
        String str4 = this.f843g;
        if (str4 != null) {
            iVar.mo24275a(str4, 6);
        }
        Map<String, String> map = this.f844h;
        if (map != null) {
            iVar.mo24277a(map, 7);
        }
        String str5 = this.f845i;
        if (str5 != null) {
            iVar.mo24275a(str5, 8);
        }
        C3704aj ajVar = this.f846j;
        if (ajVar != null) {
            iVar.mo24273a((C3723j) ajVar, 9);
        }
        iVar.mo24271a(this.f847k, 10);
        String str6 = this.f848l;
        if (str6 != null) {
            iVar.mo24275a(str6, 11);
        }
        String str7 = this.f849m;
        if (str7 != null) {
            iVar.mo24275a(str7, 12);
        }
        C3703ai aiVar = this.f850n;
        if (aiVar != null) {
            iVar.mo24273a((C3723j) aiVar, 13);
        }
        ArrayList<C3703ai> arrayList = this.f851o;
        if (arrayList != null) {
            iVar.mo24276a(arrayList, 14);
        }
        ArrayList<C3703ai> arrayList2 = this.f852p;
        if (arrayList2 != null) {
            iVar.mo24276a(arrayList2, 15);
        }
        ArrayList<C3705ak> arrayList3 = this.f853q;
        if (arrayList3 != null) {
            iVar.mo24276a(arrayList3, 16);
        }
        Map<String, String> map2 = this.f854r;
        if (map2 != null) {
            iVar.mo24277a(map2, 17);
        }
        Map<String, String> map3 = this.f855s;
        if (map3 != null) {
            iVar.mo24277a(map3, 18);
        }
        String str8 = this.f856t;
        if (str8 != null) {
            iVar.mo24275a(str8, 19);
        }
        iVar.mo24279a(this.f857u, 20);
    }

    static {
        HashMap hashMap = new HashMap();
        f832v = hashMap;
        hashMap.put("", "");
        f835y.add(new C3703ai());
        f836z.add(new C3703ai());
        f829A.add(new C3705ak());
        HashMap hashMap2 = new HashMap();
        f830B = hashMap2;
        hashMap2.put("", "");
        HashMap hashMap3 = new HashMap();
        f831C = hashMap3;
        hashMap3.put("", "");
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        this.f837a = hVar.mo24266b(0, true);
        this.f838b = hVar.mo24259a(this.f838b, 1, true);
        this.f839c = hVar.mo24266b(2, true);
        this.f840d = hVar.mo24266b(3, false);
        this.f841e = hVar.mo24266b(4, false);
        this.f842f = hVar.mo24266b(5, false);
        this.f843g = hVar.mo24266b(6, false);
        this.f844h = (Map) hVar.mo24261a(f832v, 7, false);
        this.f845i = hVar.mo24266b(8, false);
        this.f846j = (C3704aj) hVar.mo24260a((C3723j) f833w, 9, false);
        this.f847k = hVar.mo24257a(this.f847k, 10, false);
        this.f848l = hVar.mo24266b(11, false);
        this.f849m = hVar.mo24266b(12, false);
        this.f850n = (C3703ai) hVar.mo24260a((C3723j) f834x, 13, false);
        this.f851o = (ArrayList) hVar.mo24261a(f835y, 14, false);
        this.f852p = (ArrayList) hVar.mo24261a(f836z, 15, false);
        this.f853q = (ArrayList) hVar.mo24261a(f829A, 16, false);
        this.f854r = (Map) hVar.mo24261a(f830B, 17, false);
        this.f855s = (Map) hVar.mo24261a(f831C, 18, false);
        this.f856t = hVar.mo24266b(19, false);
        this.f857u = hVar.mo24265a(20, false);
    }
}
