package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.ao */
/* compiled from: BUGLY */
public final class C3709ao extends C3723j {

    /* renamed from: i */
    private static byte[] f886i;

    /* renamed from: j */
    private static Map<String, String> f887j;

    /* renamed from: a */
    public byte f888a = 0;

    /* renamed from: b */
    public int f889b = 0;

    /* renamed from: c */
    public byte[] f890c = null;

    /* renamed from: d */
    public long f891d = 0;

    /* renamed from: e */
    public String f892e = "";

    /* renamed from: f */
    private String f893f = "";

    /* renamed from: g */
    private String f894g = "";

    /* renamed from: h */
    private Map<String, String> f895h = null;

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24270a(this.f888a, 0);
        iVar.mo24271a(this.f889b, 1);
        byte[] bArr = this.f890c;
        if (bArr != null) {
            iVar.mo24280a(bArr, 2);
        }
        String str = this.f893f;
        if (str != null) {
            iVar.mo24275a(str, 3);
        }
        iVar.mo24272a(this.f891d, 4);
        String str2 = this.f894g;
        if (str2 != null) {
            iVar.mo24275a(str2, 5);
        }
        String str3 = this.f892e;
        if (str3 != null) {
            iVar.mo24275a(str3, 6);
        }
        Map<String, String> map = this.f895h;
        if (map != null) {
            iVar.mo24277a(map, 7);
        }
    }

    static {
        byte[] bArr = new byte[1];
        f886i = bArr;
        bArr[0] = 0;
        HashMap hashMap = new HashMap();
        f887j = hashMap;
        hashMap.put("", "");
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        this.f888a = hVar.mo24256a(this.f888a, 0, true);
        this.f889b = hVar.mo24257a(this.f889b, 1, true);
        this.f890c = hVar.mo24267c(2, false);
        this.f893f = hVar.mo24266b(3, false);
        this.f891d = hVar.mo24259a(this.f891d, 4, false);
        this.f894g = hVar.mo24266b(5, false);
        this.f892e = hVar.mo24266b(6, false);
        this.f895h = (Map) hVar.mo24261a(f887j, 7, false);
    }
}
