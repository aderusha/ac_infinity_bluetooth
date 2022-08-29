package com.tencent.bugly.crashreport.crash.anr;

/* renamed from: com.tencent.bugly.crashreport.crash.anr.c */
/* compiled from: BUGLY */
public final class C3661c {

    /* renamed from: a */
    private String f656a = "";

    /* renamed from: b */
    private long f657b;

    /* renamed from: c */
    private String f658c = "";

    /* renamed from: d */
    private boolean f659d = false;

    public C3661c(String str, long j) {
        this.f658c = str == null ? "" : str;
        this.f657b = j;
        this.f659d = false;
    }

    /* renamed from: a */
    public final String mo24128a() {
        return this.f658c;
    }

    /* renamed from: a */
    public final void mo24129a(String str) {
        if (str == null) {
            str = "";
        }
        this.f656a = str;
    }

    /* renamed from: b */
    public final String mo24131b() {
        return this.f656a;
    }

    /* renamed from: c */
    public final long mo24132c() {
        return this.f657b;
    }

    /* renamed from: d */
    public final boolean mo24133d() {
        return this.f659d;
    }

    /* renamed from: a */
    public final void mo24130a(boolean z) {
        this.f659d = z;
    }
}
