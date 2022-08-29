package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Set;

/* renamed from: com.tencent.bugly.proguard.c */
/* compiled from: BUGLY */
public class C3715c extends C3691a {

    /* renamed from: d */
    protected HashMap<String, byte[]> f929d = null;

    /* renamed from: e */
    private HashMap<String, Object> f930e = new HashMap<>();

    /* renamed from: f */
    private C3720h f931f = new C3720h();

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo24220a(String str) {
        super.mo24220a(str);
    }

    /* renamed from: c */
    public void mo24250c() {
        this.f929d = new HashMap<>();
    }

    /* renamed from: a */
    public <T> void mo24221a(String str, T t) {
        if (this.f929d == null) {
            super.mo24221a(str, t);
        } else if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (!(t instanceof Set)) {
            C3722i iVar = new C3722i();
            iVar.mo24268a(this.f775b);
            iVar.mo24274a((Object) t, 0);
            this.f929d.put(str, C3724k.m814a(iVar.mo24269a()));
        } else {
            throw new IllegalArgumentException("can not support Set");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> T mo24249b(java.lang.String r6, T r7) throws com.tencent.bugly.proguard.C3714b {
        /*
            r5 = this;
            java.util.HashMap<java.lang.String, byte[]> r0 = r5.f929d
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x0046
            boolean r0 = r0.containsKey(r6)
            if (r0 != 0) goto L_0x000e
            return r2
        L_0x000e:
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r5.f930e
            boolean r0 = r0.containsKey(r6)
            if (r0 == 0) goto L_0x001d
            java.util.HashMap<java.lang.String, java.lang.Object> r7 = r5.f930e
            java.lang.Object r6 = r7.get(r6)
            return r6
        L_0x001d:
            java.util.HashMap<java.lang.String, byte[]> r0 = r5.f929d
            java.lang.Object r0 = r0.get(r6)
            byte[] r0 = (byte[]) r0
            com.tencent.bugly.proguard.h r2 = r5.f931f     // Catch:{ Exception -> 0x003f }
            r2.mo24264a((byte[]) r0)     // Catch:{ Exception -> 0x003f }
            com.tencent.bugly.proguard.h r0 = r5.f931f     // Catch:{ Exception -> 0x003f }
            java.lang.String r2 = r5.f775b     // Catch:{ Exception -> 0x003f }
            r0.mo24258a((java.lang.String) r2)     // Catch:{ Exception -> 0x003f }
            com.tencent.bugly.proguard.h r0 = r5.f931f     // Catch:{ Exception -> 0x003f }
            java.lang.Object r7 = r0.mo24261a(r7, (int) r3, (boolean) r1)     // Catch:{ Exception -> 0x003f }
            if (r7 == 0) goto L_0x003e
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r5.f930e     // Catch:{ Exception -> 0x003f }
            r0.put(r6, r7)     // Catch:{ Exception -> 0x003f }
        L_0x003e:
            return r7
        L_0x003f:
            r6 = move-exception
            com.tencent.bugly.proguard.b r7 = new com.tencent.bugly.proguard.b
            r7.<init>((java.lang.Exception) r6)
            throw r7
        L_0x0046:
            java.util.HashMap r0 = r5.f774a
            boolean r0 = r0.containsKey(r6)
            if (r0 != 0) goto L_0x004f
            return r2
        L_0x004f:
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r5.f930e
            boolean r0 = r0.containsKey(r6)
            if (r0 == 0) goto L_0x005e
            java.util.HashMap<java.lang.String, java.lang.Object> r7 = r5.f930e
            java.lang.Object r6 = r7.get(r6)
            return r6
        L_0x005e:
            java.util.HashMap r0 = r5.f774a
            java.lang.Object r0 = r0.get(r6)
            java.util.HashMap r0 = (java.util.HashMap) r0
            byte[] r2 = new byte[r3]
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x0086
            java.lang.Object r0 = r0.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r0.getKey()
            java.lang.Object r0 = r0.getValue()
            r2 = r0
            byte[] r2 = (byte[]) r2
        L_0x0086:
            com.tencent.bugly.proguard.h r0 = r5.f931f     // Catch:{ Exception -> 0x009e }
            r0.mo24264a((byte[]) r2)     // Catch:{ Exception -> 0x009e }
            com.tencent.bugly.proguard.h r0 = r5.f931f     // Catch:{ Exception -> 0x009e }
            java.lang.String r2 = r5.f775b     // Catch:{ Exception -> 0x009e }
            r0.mo24258a((java.lang.String) r2)     // Catch:{ Exception -> 0x009e }
            com.tencent.bugly.proguard.h r0 = r5.f931f     // Catch:{ Exception -> 0x009e }
            java.lang.Object r7 = r0.mo24261a(r7, (int) r3, (boolean) r1)     // Catch:{ Exception -> 0x009e }
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r5.f930e     // Catch:{ Exception -> 0x009e }
            r0.put(r6, r7)     // Catch:{ Exception -> 0x009e }
            return r7
        L_0x009e:
            r6 = move-exception
            com.tencent.bugly.proguard.b r7 = new com.tencent.bugly.proguard.b
            r7.<init>((java.lang.Exception) r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3715c.mo24249b(java.lang.String, java.lang.Object):java.lang.Object");
    }

    /* renamed from: a */
    public byte[] mo24223a() {
        if (this.f929d == null) {
            return super.mo24223a();
        }
        C3722i iVar = new C3722i(0);
        iVar.mo24268a(this.f775b);
        iVar.mo24277a(this.f929d, 0);
        return C3724k.m814a(iVar.mo24269a());
    }

    /* renamed from: a */
    public void mo24222a(byte[] bArr) {
        try {
            super.mo24222a(bArr);
        } catch (Exception unused) {
            this.f931f.mo24264a(bArr);
            this.f931f.mo24258a(this.f775b);
            HashMap hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.f929d = this.f931f.mo24262a(hashMap, 0, false);
        }
    }
}
