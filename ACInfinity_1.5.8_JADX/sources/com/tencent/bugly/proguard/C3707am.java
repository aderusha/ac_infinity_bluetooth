package com.tencent.bugly.proguard;

import java.util.ArrayList;

/* renamed from: com.tencent.bugly.proguard.am */
/* compiled from: BUGLY */
public final class C3707am extends C3723j implements Cloneable {

    /* renamed from: b */
    private static ArrayList<C3706al> f858b;

    /* renamed from: a */
    public ArrayList<C3706al> f859a = null;

    /* renamed from: a */
    public final void mo24245a(StringBuilder sb, int i) {
    }

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24276a(this.f859a, 0);
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        if (f858b == null) {
            f858b = new ArrayList<>();
            f858b.add(new C3706al());
        }
        this.f859a = (ArrayList) hVar.mo24261a(f858b, 0, true);
    }
}
