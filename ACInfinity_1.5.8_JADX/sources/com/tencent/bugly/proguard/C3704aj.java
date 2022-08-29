package com.tencent.bugly.proguard;

import java.util.ArrayList;

/* renamed from: com.tencent.bugly.proguard.aj */
/* compiled from: BUGLY */
public final class C3704aj extends C3723j implements Cloneable {

    /* renamed from: c */
    private static ArrayList<String> f822c;

    /* renamed from: a */
    private String f823a = "";

    /* renamed from: b */
    private ArrayList<String> f824b = null;

    /* renamed from: a */
    public final void mo24245a(StringBuilder sb, int i) {
    }

    /* renamed from: a */
    public final void mo24244a(C3722i iVar) {
        iVar.mo24275a(this.f823a, 0);
        ArrayList<String> arrayList = this.f824b;
        if (arrayList != null) {
            iVar.mo24276a(arrayList, 1);
        }
    }

    /* renamed from: a */
    public final void mo24243a(C3720h hVar) {
        this.f823a = hVar.mo24266b(0, true);
        if (f822c == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            f822c = arrayList;
            arrayList.add("");
        }
        this.f824b = (ArrayList) hVar.mo24261a(f822c, 1, false);
    }
}
