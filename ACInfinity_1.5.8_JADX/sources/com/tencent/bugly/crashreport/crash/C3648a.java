package com.tencent.bugly.crashreport.crash;

/* renamed from: com.tencent.bugly.crashreport.crash.a */
/* compiled from: BUGLY */
public final class C3648a implements Comparable<C3648a> {

    /* renamed from: a */
    public long f613a = -1;

    /* renamed from: b */
    public long f614b = -1;

    /* renamed from: c */
    public String f615c = null;

    /* renamed from: d */
    public boolean f616d = false;

    /* renamed from: e */
    public boolean f617e = false;

    /* renamed from: f */
    public int f618f = 0;

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        int i;
        C3648a aVar = (C3648a) obj;
        if (aVar == null || this.f614b - aVar.f614b > 0) {
            return 1;
        }
        return i < 0 ? -1 : 0;
    }
}
