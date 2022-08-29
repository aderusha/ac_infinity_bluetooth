package com.tencent.bugly.proguard;

/* renamed from: com.tencent.bugly.proguard.ak */
/* compiled from: BUGLY */
public final class C3705ak extends C3723j implements Cloneable {

    /* renamed from: d */
    private static byte[] f825d;

    /* renamed from: a */
    private byte f826a = 0;

    /* renamed from: b */
    private String f827b = "";

    /* renamed from: c */
    private byte[] f828c = null;

    /* renamed from: a */
    public final void mo24245a(StringBuilder sb, int i) {
    }

    public C3705ak() {
    }

    public C3705ak(byte b, String str, byte[] bArr) {
        this.f826a = b;
        this.f827b = str;
        this.f828c = bArr;
    }

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24270a(this.f826a, 0);
        iVar.mo24275a(this.f827b, 1);
        byte[] bArr = this.f828c;
        if (bArr != null) {
            iVar.mo24280a(bArr, 2);
        }
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        this.f826a = hVar.mo24256a(this.f826a, 0, true);
        this.f827b = hVar.mo24266b(1, true);
        if (f825d == null) {
            byte[] bArr = new byte[1];
            f825d = bArr;
            bArr[0] = 0;
        }
        this.f828c = hVar.mo24267c(2, false);
    }
}
