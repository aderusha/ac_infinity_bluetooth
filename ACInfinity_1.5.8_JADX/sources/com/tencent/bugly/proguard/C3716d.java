package com.tencent.bugly.proguard;

import com.alibaba.android.arouter.utils.Consts;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* renamed from: com.tencent.bugly.proguard.d */
/* compiled from: BUGLY */
public final class C3716d extends C3715c {

    /* renamed from: f */
    private static HashMap<String, byte[]> f932f;

    /* renamed from: g */
    private static HashMap<String, HashMap<String, byte[]>> f933g;

    /* renamed from: e */
    private C3718f f934e;

    public C3716d() {
        C3718f fVar = new C3718f();
        this.f934e = fVar;
        fVar.f939a = 2;
    }

    /* renamed from: a */
    public final <T> void mo24221a(String str, T t) {
        if (!str.startsWith(Consts.DOT)) {
            super.mo24221a(str, t);
            return;
        }
        throw new IllegalArgumentException("put name can not startwith . , now is " + str);
    }

    /* renamed from: c */
    public final void mo24250c() {
        super.mo24250c();
        this.f934e.f939a = 3;
    }

    /* renamed from: a */
    public final byte[] mo24223a() {
        if (this.f934e.f939a != 2) {
            if (this.f934e.f941c == null) {
                this.f934e.f941c = "";
            }
            if (this.f934e.f942d == null) {
                this.f934e.f942d = "";
            }
        } else if (this.f934e.f941c.equals("")) {
            throw new IllegalArgumentException("servantName can not is null");
        } else if (this.f934e.f942d.equals("")) {
            throw new IllegalArgumentException("funcName can not is null");
        }
        C3722i iVar = new C3722i(0);
        iVar.mo24268a(this.f775b);
        if (this.f934e.f939a == 2) {
            iVar.mo24277a(this.f774a, 0);
        } else {
            iVar.mo24277a(this.f929d, 0);
        }
        this.f934e.f943e = C3724k.m814a(iVar.mo24269a());
        C3722i iVar2 = new C3722i(0);
        iVar2.mo24268a(this.f775b);
        this.f934e.mo24244a(iVar2);
        byte[] a = C3724k.m814a(iVar2.mo24269a());
        int length = a.length + 4;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length).put(a).flip();
        return allocate.array();
    }

    /* renamed from: a */
    public final void mo24222a(byte[] bArr) {
        if (bArr.length >= 4) {
            try {
                C3720h hVar = new C3720h(bArr, 4);
                hVar.mo24258a(this.f775b);
                this.f934e.mo24243a(hVar);
                if (this.f934e.f939a == 3) {
                    C3720h hVar2 = new C3720h(this.f934e.f943e);
                    hVar2.mo24258a(this.f775b);
                    if (f932f == null) {
                        HashMap<String, byte[]> hashMap = new HashMap<>();
                        f932f = hashMap;
                        hashMap.put("", new byte[0]);
                    }
                    this.f929d = hVar2.mo24262a(f932f, 0, false);
                    return;
                }
                C3720h hVar3 = new C3720h(this.f934e.f943e);
                hVar3.mo24258a(this.f775b);
                if (f933g == null) {
                    f933g = new HashMap<>();
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("", new byte[0]);
                    f933g.put("", hashMap2);
                }
                this.f774a = hVar3.mo24262a(f933g, 0, false);
                new HashMap();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("decode package must include size head");
        }
    }

    /* renamed from: b */
    public final void mo24252b(String str) {
        this.f934e.f941c = str;
    }

    /* renamed from: c */
    public final void mo24253c(String str) {
        this.f934e.f942d = str;
    }

    /* renamed from: a */
    public final void mo24251a(int i) {
        this.f934e.f940b = 1;
    }
}
