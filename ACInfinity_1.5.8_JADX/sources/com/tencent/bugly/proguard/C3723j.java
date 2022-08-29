package com.tencent.bugly.proguard;

import java.io.Serializable;

/* renamed from: com.tencent.bugly.proguard.j */
/* compiled from: BUGLY */
public abstract class C3723j implements Serializable {
    /* renamed from: a */
    public abstract void mo24243a(C3720h hVar);

    /* renamed from: a */
    public abstract void mo24244a(C3722i iVar);

    /* renamed from: a */
    public abstract void mo24245a(StringBuilder sb, int i);

    public String toString() {
        StringBuilder sb = new StringBuilder();
        mo24245a(sb, 0);
        return sb.toString();
    }
}
