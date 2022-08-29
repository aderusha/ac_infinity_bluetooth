package com.tencent.bugly.proguard;

import androidx.core.app.NotificationCompat;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.f */
/* compiled from: BUGLY */
public final class C3718f extends C3723j {

    /* renamed from: k */
    private static byte[] f936k = null;

    /* renamed from: l */
    private static Map<String, String> f937l = null;

    /* renamed from: m */
    private static /* synthetic */ boolean f938m = true;

    /* renamed from: a */
    public short f939a = 0;

    /* renamed from: b */
    public int f940b = 0;

    /* renamed from: c */
    public String f941c = null;

    /* renamed from: d */
    public String f942d = null;

    /* renamed from: e */
    public byte[] f943e;

    /* renamed from: f */
    private byte f944f = 0;

    /* renamed from: g */
    private int f945g = 0;

    /* renamed from: h */
    private int f946h = 0;

    /* renamed from: i */
    private Map<String, String> f947i;

    /* renamed from: j */
    private Map<String, String> f948j;

    public final boolean equals(Object obj) {
        C3718f fVar = (C3718f) obj;
        return C3724k.m810a(1, (int) fVar.f939a) && C3724k.m810a(1, (int) fVar.f944f) && C3724k.m810a(1, fVar.f945g) && C3724k.m810a(1, fVar.f940b) && C3724k.m812a((Object) 1, (Object) fVar.f941c) && C3724k.m812a((Object) 1, (Object) fVar.f942d) && C3724k.m812a((Object) 1, (Object) fVar.f943e) && C3724k.m810a(1, fVar.f946h) && C3724k.m812a((Object) 1, (Object) fVar.f947i) && C3724k.m812a((Object) 1, (Object) fVar.f948j);
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (f938m) {
                return null;
            }
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24278a(this.f939a, 1);
        iVar.mo24270a(this.f944f, 2);
        iVar.mo24271a(this.f945g, 3);
        iVar.mo24271a(this.f940b, 4);
        iVar.mo24275a(this.f941c, 5);
        iVar.mo24275a(this.f942d, 6);
        iVar.mo24280a(this.f943e, 7);
        iVar.mo24271a(this.f946h, 8);
        iVar.mo24277a(this.f947i, 9);
        iVar.mo24277a(this.f948j, 10);
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        try {
            this.f939a = hVar.mo24263a(this.f939a, 1, true);
            this.f944f = hVar.mo24256a(this.f944f, 2, true);
            this.f945g = hVar.mo24257a(this.f945g, 3, true);
            this.f940b = hVar.mo24257a(this.f940b, 4, true);
            this.f941c = hVar.mo24266b(5, true);
            this.f942d = hVar.mo24266b(6, true);
            if (f936k == null) {
                f936k = new byte[]{0};
            }
            this.f943e = hVar.mo24267c(7, true);
            this.f946h = hVar.mo24257a(this.f946h, 8, true);
            if (f937l == null) {
                HashMap hashMap = new HashMap();
                f937l = hashMap;
                hashMap.put("", "");
            }
            this.f947i = (Map) hVar.mo24261a(f937l, 9, true);
            if (f937l == null) {
                HashMap hashMap2 = new HashMap();
                f937l = hashMap2;
                hashMap2.put("", "");
            }
            this.f948j = (Map) hVar.mo24261a(f937l, 10, true);
        } catch (Exception e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("RequestPacket decode error " + C3717e.m760a(this.f943e));
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public final void mo24245a(StringBuilder sb, int i) {
        C3750z zVar = new C3750z(sb, i);
        zVar.mo24360a(this.f939a, "iVersion");
        zVar.mo24349a(this.f944f, "cPacketType");
        zVar.mo24353a(this.f945g, "iMessageType");
        zVar.mo24353a(this.f940b, "iRequestId");
        zVar.mo24357a(this.f941c, "sServantName");
        zVar.mo24357a(this.f942d, "sFuncName");
        zVar.mo24362a(this.f943e, "sBuffer");
        zVar.mo24353a(this.f946h, "iTimeout");
        zVar.mo24359a(this.f947i, "context");
        zVar.mo24359a(this.f948j, NotificationCompat.CATEGORY_STATUS);
    }
}
