package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.h */
/* compiled from: BUGLY */
public final class C3720h {

    /* renamed from: a */
    private ByteBuffer f949a;

    /* renamed from: b */
    private String f950b = "GBK";

    /* renamed from: com.tencent.bugly.proguard.h$a */
    /* compiled from: BUGLY */
    public static class C3721a {

        /* renamed from: a */
        public byte f951a;

        /* renamed from: b */
        public int f952b;
    }

    public C3720h() {
    }

    public C3720h(byte[] bArr) {
        this.f949a = ByteBuffer.wrap(bArr);
    }

    public C3720h(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.f949a = wrap;
        wrap.position(4);
    }

    /* renamed from: a */
    public final void mo24264a(byte[] bArr) {
        ByteBuffer byteBuffer = this.f949a;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        this.f949a = ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private static int m766a(C3721a aVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        aVar.f951a = (byte) (b & 15);
        aVar.f952b = (b & 240) >> 4;
        if (aVar.f952b != 15) {
            return 1;
        }
        aVar.f952b = byteBuffer.get();
        return 2;
    }

    /* renamed from: a */
    private boolean m770a(int i) {
        try {
            C3721a aVar = new C3721a();
            while (true) {
                int a = m766a(aVar, this.f949a.duplicate());
                if (i <= aVar.f952b) {
                    break;
                } else if (aVar.f951a == 11) {
                    break;
                } else {
                    ByteBuffer byteBuffer = this.f949a;
                    byteBuffer.position(byteBuffer.position() + a);
                    m769a(aVar.f951a);
                }
            }
            if (i == aVar.f952b) {
                return true;
            }
            return false;
        } catch (C3719g | BufferUnderflowException unused) {
        }
    }

    /* renamed from: a */
    private void m768a() {
        C3721a aVar = new C3721a();
        do {
            m766a(aVar, this.f949a);
            m769a(aVar.f951a);
        } while (aVar.f951a != 11);
    }

    /* renamed from: a */
    private void m769a(byte b) {
        int i = 0;
        switch (b) {
            case 0:
                ByteBuffer byteBuffer = this.f949a;
                byteBuffer.position(byteBuffer.position() + 1);
                return;
            case 1:
                ByteBuffer byteBuffer2 = this.f949a;
                byteBuffer2.position(byteBuffer2.position() + 2);
                return;
            case 2:
                ByteBuffer byteBuffer3 = this.f949a;
                byteBuffer3.position(byteBuffer3.position() + 4);
                return;
            case 3:
                ByteBuffer byteBuffer4 = this.f949a;
                byteBuffer4.position(byteBuffer4.position() + 8);
                return;
            case 4:
                ByteBuffer byteBuffer5 = this.f949a;
                byteBuffer5.position(byteBuffer5.position() + 4);
                return;
            case 5:
                ByteBuffer byteBuffer6 = this.f949a;
                byteBuffer6.position(byteBuffer6.position() + 8);
                return;
            case 6:
                int i2 = this.f949a.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                ByteBuffer byteBuffer7 = this.f949a;
                byteBuffer7.position(byteBuffer7.position() + i2);
                return;
            case 7:
                int i3 = this.f949a.getInt();
                ByteBuffer byteBuffer8 = this.f949a;
                byteBuffer8.position(byteBuffer8.position() + i3);
                return;
            case 8:
                int a = mo24257a(0, 0, true);
                while (i < (a << 1)) {
                    C3721a aVar = new C3721a();
                    m766a(aVar, this.f949a);
                    m769a(aVar.f951a);
                    i++;
                }
                return;
            case 9:
                int a2 = mo24257a(0, 0, true);
                while (i < a2) {
                    C3721a aVar2 = new C3721a();
                    m766a(aVar2, this.f949a);
                    m769a(aVar2.f951a);
                    i++;
                }
                return;
            case 10:
                m768a();
                return;
            case 11:
            case 12:
                return;
            case 13:
                C3721a aVar3 = new C3721a();
                m766a(aVar3, this.f949a);
                if (aVar3.f951a == 0) {
                    int a3 = mo24257a(0, 0, true);
                    ByteBuffer byteBuffer9 = this.f949a;
                    byteBuffer9.position(byteBuffer9.position() + a3);
                    return;
                }
                throw new C3719g("skipField with invalid type, type value: " + b + ", " + aVar3.f951a);
            default:
                throw new C3719g("invalid type.");
        }
    }

    /* renamed from: a */
    public final boolean mo24265a(int i, boolean z) {
        return mo24256a((byte) 0, i, z) != 0;
    }

    /* renamed from: a */
    public final byte mo24256a(byte b, int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            byte b2 = aVar.f951a;
            if (b2 == 0) {
                return this.f949a.get();
            }
            if (b2 == 12) {
                return 0;
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return b;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final short mo24263a(short s, int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            byte b = aVar.f951a;
            if (b == 0) {
                return (short) this.f949a.get();
            }
            if (b == 1) {
                return this.f949a.getShort();
            }
            if (b == 12) {
                return 0;
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return s;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final int mo24257a(int i, int i2, boolean z) {
        if (m770a(i2)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            byte b = aVar.f951a;
            if (b == 0) {
                return this.f949a.get();
            }
            if (b == 1) {
                return this.f949a.getShort();
            }
            if (b == 2) {
                return this.f949a.getInt();
            }
            if (b == 12) {
                return 0;
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return i;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final long mo24259a(long j, int i, boolean z) {
        int i2;
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            byte b = aVar.f951a;
            if (b == 0) {
                i2 = this.f949a.get();
            } else if (b == 1) {
                i2 = this.f949a.getShort();
            } else if (b == 2) {
                i2 = this.f949a.getInt();
            } else if (b == 3) {
                return this.f949a.getLong();
            } else {
                if (b == 12) {
                    return 0;
                }
                throw new C3719g("type mismatch.");
            }
            return (long) i2;
        } else if (!z) {
            return j;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: a */
    private float m765a(float f, int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            byte b = aVar.f951a;
            if (b == 4) {
                return this.f949a.getFloat();
            }
            if (b == 12) {
                return 0.0f;
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return f;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: a */
    private double m764a(double d, int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            byte b = aVar.f951a;
            if (b == 4) {
                return (double) this.f949a.getFloat();
            }
            if (b == 5) {
                return this.f949a.getDouble();
            }
            if (b == 12) {
                return 0.0d;
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return d;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: b */
    public final String mo24266b(int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            byte b = aVar.f951a;
            if (b == 6) {
                int i2 = this.f949a.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                byte[] bArr = new byte[i2];
                this.f949a.get(bArr);
                try {
                    return new String(bArr, this.f950b);
                } catch (UnsupportedEncodingException unused) {
                    return new String(bArr);
                }
            } else if (b == 7) {
                int i3 = this.f949a.getInt();
                if (i3 > 104857600 || i3 < 0) {
                    throw new C3719g("String too long: " + i3);
                }
                byte[] bArr2 = new byte[i3];
                this.f949a.get(bArr2);
                try {
                    return new String(bArr2, this.f950b);
                } catch (UnsupportedEncodingException unused2) {
                    return new String(bArr2);
                }
            } else {
                throw new C3719g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final <K, V> HashMap<K, V> mo24262a(Map<K, V> map, int i, boolean z) {
        return (HashMap) m767a(new HashMap(), map, i, z);
    }

    /* renamed from: a */
    private <K, V> Map<K, V> m767a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Map.Entry next = map2.entrySet().iterator().next();
        Object key = next.getKey();
        Object value = next.getValue();
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            if (aVar.f951a == 8) {
                int a = mo24257a(0, 0, true);
                if (a >= 0) {
                    for (int i2 = 0; i2 < a; i2++) {
                        map.put(mo24261a(key, 0, true), mo24261a(value, 1, true));
                    }
                } else {
                    throw new C3719g("size invalid: " + a);
                }
            } else {
                throw new C3719g("type mismatch.");
            }
        } else if (z) {
            throw new C3719g("require field not exist.");
        }
        return map;
    }

    /* renamed from: d */
    private boolean[] m773d(int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            if (aVar.f951a == 9) {
                int a = mo24257a(0, 0, true);
                if (a >= 0) {
                    boolean[] zArr = new boolean[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        zArr[i2] = mo24256a((byte) 0, 0, true) != 0;
                    }
                    return zArr;
                }
                throw new C3719g("size invalid: " + a);
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: c */
    public final byte[] mo24267c(int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            byte b = aVar.f951a;
            if (b == 9) {
                int a = mo24257a(0, 0, true);
                if (a >= 0) {
                    byte[] bArr = new byte[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        bArr[i2] = mo24256a(bArr[0], 0, true);
                    }
                    return bArr;
                }
                throw new C3719g("size invalid: " + a);
            } else if (b == 13) {
                C3721a aVar2 = new C3721a();
                m766a(aVar2, this.f949a);
                if (aVar2.f951a == 0) {
                    int a2 = mo24257a(0, 0, true);
                    if (a2 >= 0) {
                        byte[] bArr2 = new byte[a2];
                        this.f949a.get(bArr2);
                        return bArr2;
                    }
                    throw new C3719g("invalid size, tag: " + i + ", type: " + aVar.f951a + ", " + aVar2.f951a + ", size: " + a2);
                }
                throw new C3719g("type mismatch, tag: " + i + ", type: " + aVar.f951a + ", " + aVar2.f951a);
            } else {
                throw new C3719g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: e */
    private short[] m774e(int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            if (aVar.f951a == 9) {
                int a = mo24257a(0, 0, true);
                if (a >= 0) {
                    short[] sArr = new short[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        sArr[i2] = mo24263a(sArr[0], 0, true);
                    }
                    return sArr;
                }
                throw new C3719g("size invalid: " + a);
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: f */
    private int[] m775f(int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            if (aVar.f951a == 9) {
                int a = mo24257a(0, 0, true);
                if (a >= 0) {
                    int[] iArr = new int[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        iArr[i2] = mo24257a(iArr[0], 0, true);
                    }
                    return iArr;
                }
                throw new C3719g("size invalid: " + a);
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: g */
    private long[] m776g(int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            if (aVar.f951a == 9) {
                int a = mo24257a(0, 0, true);
                if (a >= 0) {
                    long[] jArr = new long[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        jArr[i2] = mo24259a(jArr[0], 0, true);
                    }
                    return jArr;
                }
                throw new C3719g("size invalid: " + a);
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: h */
    private float[] m777h(int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            if (aVar.f951a == 9) {
                int a = mo24257a(0, 0, true);
                if (a >= 0) {
                    float[] fArr = new float[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        fArr[i2] = m765a(fArr[0], 0, true);
                    }
                    return fArr;
                }
                throw new C3719g("size invalid: " + a);
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: i */
    private double[] m778i(int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            if (aVar.f951a == 9) {
                int a = mo24257a(0, 0, true);
                if (a >= 0) {
                    double[] dArr = new double[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        dArr[i2] = m764a(dArr[0], 0, true);
                    }
                    return dArr;
                }
                throw new C3719g("size invalid: " + a);
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: a */
    private <T> T[] m771a(T[] tArr, int i, boolean z) {
        if (tArr != null && tArr.length != 0) {
            return m772b(tArr[0], i, z);
        }
        throw new C3719g("unable to get type of key and value.");
    }

    /* renamed from: b */
    private <T> T[] m772b(T t, int i, boolean z) {
        if (m770a(i)) {
            C3721a aVar = new C3721a();
            m766a(aVar, this.f949a);
            if (aVar.f951a == 9) {
                int a = mo24257a(0, 0, true);
                if (a >= 0) {
                    T[] tArr = (Object[]) Array.newInstance(t.getClass(), a);
                    for (int i2 = 0; i2 < a; i2++) {
                        tArr[i2] = mo24261a(t, 0, true);
                    }
                    return tArr;
                }
                throw new C3719g("size invalid: " + a);
            }
            throw new C3719g("type mismatch.");
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final C3723j mo24260a(C3723j jVar, int i, boolean z) {
        if (m770a(i)) {
            try {
                C3723j jVar2 = (C3723j) jVar.getClass().newInstance();
                C3721a aVar = new C3721a();
                m766a(aVar, this.f949a);
                if (aVar.f951a == 10) {
                    jVar2.mo24243a(this);
                    m768a();
                    return jVar2;
                }
                throw new C3719g("type mismatch.");
            } catch (Exception e) {
                throw new C3719g(e.getMessage());
            }
        } else if (!z) {
            return null;
        } else {
            throw new C3719g("require field not exist.");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [int] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> java.lang.Object mo24261a(T r3, int r4, boolean r5) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof java.lang.Byte
            r1 = 0
            if (r0 == 0) goto L_0x000e
            byte r3 = r2.mo24256a((byte) r1, (int) r4, (boolean) r5)
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)
            return r3
        L_0x000e:
            boolean r0 = r3 instanceof java.lang.Boolean
            if (r0 == 0) goto L_0x001e
            byte r3 = r2.mo24256a((byte) r1, (int) r4, (boolean) r5)
            if (r3 == 0) goto L_0x0019
            r1 = 1
        L_0x0019:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
            return r3
        L_0x001e:
            boolean r0 = r3 instanceof java.lang.Short
            if (r0 == 0) goto L_0x002b
            short r3 = r2.mo24263a((short) r1, (int) r4, (boolean) r5)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            return r3
        L_0x002b:
            boolean r0 = r3 instanceof java.lang.Integer
            if (r0 == 0) goto L_0x0038
            int r3 = r2.mo24257a((int) r1, (int) r4, (boolean) r5)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            return r3
        L_0x0038:
            boolean r0 = r3 instanceof java.lang.Long
            if (r0 == 0) goto L_0x0047
            r0 = 0
            long r3 = r2.mo24259a((long) r0, (int) r4, (boolean) r5)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            return r3
        L_0x0047:
            boolean r0 = r3 instanceof java.lang.Float
            if (r0 == 0) goto L_0x0055
            r3 = 0
            float r3 = r2.m765a((float) r3, (int) r4, (boolean) r5)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            return r3
        L_0x0055:
            boolean r0 = r3 instanceof java.lang.Double
            if (r0 == 0) goto L_0x0064
            r0 = 0
            double r3 = r2.m764a((double) r0, (int) r4, (boolean) r5)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            return r3
        L_0x0064:
            boolean r0 = r3 instanceof java.lang.String
            if (r0 == 0) goto L_0x0071
            java.lang.String r3 = r2.mo24266b(r4, r5)
            java.lang.String r3 = java.lang.String.valueOf(r3)
            return r3
        L_0x0071:
            boolean r0 = r3 instanceof java.util.Map
            if (r0 == 0) goto L_0x0083
            java.util.Map r3 = (java.util.Map) r3
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.Map r3 = r2.m767a(r0, r3, r4, r5)
            java.util.HashMap r3 = (java.util.HashMap) r3
            return r3
        L_0x0083:
            boolean r0 = r3 instanceof java.util.List
            if (r0 == 0) goto L_0x00b5
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto L_0x00af
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x0092
            goto L_0x00af
        L_0x0092:
            java.lang.Object r3 = r3.get(r1)
            java.lang.Object[] r3 = r2.m772b(r3, r4, r5)
            if (r3 != 0) goto L_0x009e
            r3 = 0
            return r3
        L_0x009e:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L_0x00a3:
            int r5 = r3.length
            if (r1 >= r5) goto L_0x00ae
            r5 = r3[r1]
            r4.add(r5)
            int r1 = r1 + 1
            goto L_0x00a3
        L_0x00ae:
            return r4
        L_0x00af:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            return r3
        L_0x00b5:
            boolean r0 = r3 instanceof com.tencent.bugly.proguard.C3723j
            if (r0 == 0) goto L_0x00c0
            com.tencent.bugly.proguard.j r3 = (com.tencent.bugly.proguard.C3723j) r3
            com.tencent.bugly.proguard.j r3 = r2.mo24260a((com.tencent.bugly.proguard.C3723j) r3, (int) r4, (boolean) r5)
            return r3
        L_0x00c0:
            java.lang.Class r0 = r3.getClass()
            boolean r0 = r0.isArray()
            if (r0 == 0) goto L_0x0115
            boolean r0 = r3 instanceof byte[]
            if (r0 != 0) goto L_0x0110
            boolean r0 = r3 instanceof java.lang.Byte[]
            if (r0 == 0) goto L_0x00d3
            goto L_0x0110
        L_0x00d3:
            boolean r0 = r3 instanceof boolean[]
            if (r0 == 0) goto L_0x00dc
            boolean[] r3 = r2.m773d(r4, r5)
            return r3
        L_0x00dc:
            boolean r0 = r3 instanceof short[]
            if (r0 == 0) goto L_0x00e5
            short[] r3 = r2.m774e(r4, r5)
            return r3
        L_0x00e5:
            boolean r0 = r3 instanceof int[]
            if (r0 == 0) goto L_0x00ee
            int[] r3 = r2.m775f(r4, r5)
            return r3
        L_0x00ee:
            boolean r0 = r3 instanceof long[]
            if (r0 == 0) goto L_0x00f7
            long[] r3 = r2.m776g(r4, r5)
            return r3
        L_0x00f7:
            boolean r0 = r3 instanceof float[]
            if (r0 == 0) goto L_0x0100
            float[] r3 = r2.m777h(r4, r5)
            return r3
        L_0x0100:
            boolean r0 = r3 instanceof double[]
            if (r0 == 0) goto L_0x0109
            double[] r3 = r2.m778i(r4, r5)
            return r3
        L_0x0109:
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            java.lang.Object[] r3 = r2.m771a((T[]) r3, (int) r4, (boolean) r5)
            return r3
        L_0x0110:
            byte[] r3 = r2.mo24267c(r4, r5)
            return r3
        L_0x0115:
            com.tencent.bugly.proguard.g r3 = new com.tencent.bugly.proguard.g
            java.lang.String r4 = "read object error: unsupport type."
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C3720h.mo24261a(java.lang.Object, int, boolean):java.lang.Object");
    }

    /* renamed from: a */
    public final int mo24258a(String str) {
        this.f950b = str;
        return 0;
    }
}
