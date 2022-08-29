package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.i */
/* compiled from: BUGLY */
public final class C3722i {

    /* renamed from: a */
    private ByteBuffer f953a;

    /* renamed from: b */
    private String f954b;

    public C3722i(int i) {
        this.f954b = "GBK";
        this.f953a = ByteBuffer.allocate(i);
    }

    public C3722i() {
        this(128);
    }

    /* renamed from: a */
    public final ByteBuffer mo24269a() {
        return this.f953a;
    }

    /* renamed from: b */
    public final byte[] mo24281b() {
        byte[] bArr = new byte[this.f953a.position()];
        System.arraycopy(this.f953a.array(), 0, bArr, 0, this.f953a.position());
        return bArr;
    }

    /* renamed from: a */
    private void m791a(int i) {
        if (this.f953a.remaining() < i) {
            ByteBuffer allocate = ByteBuffer.allocate((this.f953a.capacity() + i) << 1);
            allocate.put(this.f953a.array(), 0, this.f953a.position());
            this.f953a = allocate;
        }
    }

    /* renamed from: b */
    private void m792b(byte b, int i) {
        if (i < 15) {
            this.f953a.put((byte) (b | (i << 4)));
        } else if (i < 256) {
            this.f953a.put((byte) (b | 240));
            this.f953a.put((byte) i);
        } else {
            throw new C3714b("tag is too large: " + i);
        }
    }

    /* renamed from: a */
    public final void mo24279a(boolean z, int i) {
        mo24270a(z ? (byte) 1 : 0, i);
    }

    /* renamed from: a */
    public final void mo24270a(byte b, int i) {
        m791a(3);
        if (b == 0) {
            m792b((byte) 12, i);
            return;
        }
        m792b((byte) 0, i);
        this.f953a.put(b);
    }

    /* renamed from: a */
    public final void mo24278a(short s, int i) {
        m791a(4);
        if (s < -128 || s > 127) {
            m792b((byte) 1, i);
            this.f953a.putShort(s);
            return;
        }
        mo24270a((byte) s, i);
    }

    /* renamed from: a */
    public final void mo24271a(int i, int i2) {
        m791a(6);
        if (i < -32768 || i > 32767) {
            m792b((byte) 2, i2);
            this.f953a.putInt(i);
            return;
        }
        mo24278a((short) i, i2);
    }

    /* renamed from: a */
    public final void mo24272a(long j, int i) {
        m791a(10);
        if (j < -2147483648L || j > 2147483647L) {
            m792b((byte) 3, i);
            this.f953a.putLong(j);
            return;
        }
        mo24271a((int) j, i);
    }

    /* renamed from: a */
    public final void mo24275a(String str, int i) {
        byte[] bArr;
        try {
            bArr = str.getBytes(this.f954b);
        } catch (UnsupportedEncodingException unused) {
            bArr = str.getBytes();
        }
        m791a(bArr.length + 10);
        if (bArr.length > 255) {
            m792b((byte) 7, i);
            this.f953a.putInt(bArr.length);
            this.f953a.put(bArr);
            return;
        }
        m792b((byte) 6, i);
        this.f953a.put((byte) bArr.length);
        this.f953a.put(bArr);
    }

    /* renamed from: a */
    public final <K, V> void mo24277a(Map<K, V> map, int i) {
        int i2;
        m791a(8);
        m792b((byte) 8, i);
        if (map == null) {
            i2 = 0;
        } else {
            i2 = map.size();
        }
        mo24271a(i2, 0);
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                mo24274a(next.getKey(), 0);
                mo24274a(next.getValue(), 1);
            }
        }
    }

    /* renamed from: a */
    public final void mo24280a(byte[] bArr, int i) {
        m791a(bArr.length + 8);
        m792b((byte) 13, i);
        m792b((byte) 0, 0);
        mo24271a(bArr.length, 0);
        this.f953a.put(bArr);
    }

    /* renamed from: a */
    public final <T> void mo24276a(Collection<T> collection, int i) {
        int i2;
        m791a(8);
        m792b((byte) 9, i);
        if (collection == null) {
            i2 = 0;
        } else {
            i2 = collection.size();
        }
        mo24271a(i2, 0);
        if (collection != null) {
            for (T a : collection) {
                mo24274a((Object) a, 0);
            }
        }
    }

    /* renamed from: a */
    public final void mo24273a(C3723j jVar, int i) {
        m791a(2);
        m792b((byte) 10, i);
        jVar.mo24244a(this);
        m791a(2);
        m792b((byte) 11, 0);
    }

    /* renamed from: a */
    public final void mo24274a(Object obj, int i) {
        if (obj instanceof Byte) {
            mo24270a(((Byte) obj).byteValue(), i);
        } else if (obj instanceof Boolean) {
            mo24270a(((Boolean) obj).booleanValue() ? (byte) 1 : 0, i);
        } else if (obj instanceof Short) {
            mo24278a(((Short) obj).shortValue(), i);
        } else if (obj instanceof Integer) {
            mo24271a(((Integer) obj).intValue(), i);
        } else if (obj instanceof Long) {
            mo24272a(((Long) obj).longValue(), i);
        } else if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            m791a(6);
            m792b((byte) 4, i);
            this.f953a.putFloat(floatValue);
        } else if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            m791a(10);
            m792b((byte) 5, i);
            this.f953a.putDouble(doubleValue);
        } else if (obj instanceof String) {
            mo24275a((String) obj, i);
        } else if (obj instanceof Map) {
            mo24277a((Map) obj, i);
        } else if (obj instanceof List) {
            mo24276a((List) obj, i);
        } else if (obj instanceof C3723j) {
            m791a(2);
            m792b((byte) 10, i);
            ((C3723j) obj).mo24244a(this);
            m791a(2);
            m792b((byte) 11, 0);
        } else if (obj instanceof byte[]) {
            mo24280a((byte[]) obj, i);
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            m791a(8);
            m792b((byte) 9, i);
            mo24271a(zArr.length, 0);
            for (boolean z : zArr) {
                mo24270a(z ? (byte) 1 : 0, 0);
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            m791a(8);
            m792b((byte) 9, i);
            mo24271a(sArr.length, 0);
            for (short a : sArr) {
                mo24278a(a, 0);
            }
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            m791a(8);
            m792b((byte) 9, i);
            mo24271a(iArr.length, 0);
            for (int a2 : iArr) {
                mo24271a(a2, 0);
            }
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            m791a(8);
            m792b((byte) 9, i);
            mo24271a(jArr.length, 0);
            for (long a3 : jArr) {
                mo24272a(a3, 0);
            }
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            m791a(8);
            m792b((byte) 9, i);
            mo24271a(fArr.length, 0);
            for (float putFloat : fArr) {
                m791a(6);
                m792b((byte) 4, 0);
                this.f953a.putFloat(putFloat);
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            m791a(8);
            m792b((byte) 9, i);
            mo24271a(dArr.length, 0);
            for (double putDouble : dArr) {
                m791a(10);
                m792b((byte) 5, 0);
                this.f953a.putDouble(putDouble);
            }
        } else if (obj.getClass().isArray()) {
            Object[] objArr = (Object[]) obj;
            m791a(8);
            m792b((byte) 9, i);
            mo24271a(objArr.length, 0);
            for (Object a4 : objArr) {
                mo24274a(a4, 0);
            }
        } else if (obj instanceof Collection) {
            mo24276a((Collection) obj, i);
        } else {
            throw new C3714b("write object error: unsupport type. " + obj.getClass());
        }
    }

    /* renamed from: a */
    public final int mo24268a(String str) {
        this.f954b = str;
        return 0;
    }
}
