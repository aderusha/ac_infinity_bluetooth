package com.tencent.bugly.proguard;

/* renamed from: com.tencent.bugly.proguard.ai */
/* compiled from: BUGLY */
public final class C3703ai extends C3723j implements Cloneable {

    /* renamed from: a */
    public String f817a = "";

    /* renamed from: b */
    public String f818b = "";

    /* renamed from: c */
    public String f819c = "";

    /* renamed from: d */
    private String f820d = "";

    /* renamed from: e */
    private String f821e = "";

    /* renamed from: a */
    public final void mo24245a(StringBuilder sb, int i) {
    }

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24275a(this.f817a, 0);
        String str = this.f820d;
        if (str != null) {
            iVar.mo24275a(str, 1);
        }
        String str2 = this.f818b;
        if (str2 != null) {
            iVar.mo24275a(str2, 2);
        }
        String str3 = this.f821e;
        if (str3 != null) {
            iVar.mo24275a(str3, 3);
        }
        String str4 = this.f819c;
        if (str4 != null) {
            iVar.mo24275a(str4, 4);
        }
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        this.f817a = hVar.mo24266b(0, true);
        this.f820d = hVar.mo24266b(1, false);
        this.f818b = hVar.mo24266b(2, false);
        this.f821e = hVar.mo24266b(3, false);
        this.f819c = hVar.mo24266b(4, false);
    }
}
