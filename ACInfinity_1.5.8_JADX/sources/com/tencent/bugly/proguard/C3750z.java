package com.tencent.bugly.proguard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.z */
/* compiled from: BUGLY */
public class C3750z {

    /* renamed from: a */
    private StringBuilder f1062a;

    /* renamed from: b */
    private int f1063b = 0;

    /* renamed from: a */
    private void m945a(String str) {
        for (int i = 0; i < this.f1063b; i++) {
            this.f1062a.append(9);
        }
        if (str != null) {
            StringBuilder sb = this.f1062a;
            sb.append(str);
            sb.append(": ");
        }
    }

    public C3750z(StringBuilder sb, int i) {
        this.f1062a = sb;
        this.f1063b = i;
    }

    /* renamed from: a */
    public static boolean m947a(File file, String str, long j, boolean z) {
        if (file == null) {
            return false;
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, z));
            BufferedWriter bufferedWriter2 = bufferedWriter;
            boolean a = m948a((Writer) bufferedWriter2, str.toCharArray(), str.length(), file.length(), j);
            bufferedWriter.close();
            return a;
        } catch (Throwable th) {
            C3749y.m935a(th);
            return false;
        }
    }

    /* renamed from: a */
    public C3750z mo24361a(boolean z, String str) {
        m945a(str);
        StringBuilder sb = this.f1062a;
        sb.append(z ? 'T' : 'F');
        sb.append(10);
        return this;
    }

    /* renamed from: a */
    public C3750z mo24349a(byte b, String str) {
        m945a(str);
        StringBuilder sb = this.f1062a;
        sb.append(b);
        sb.append(10);
        return this;
    }

    /* renamed from: a */
    public C3750z mo24350a(char c, String str) {
        m945a(str);
        StringBuilder sb = this.f1062a;
        sb.append(c);
        sb.append(10);
        return this;
    }

    /* renamed from: a */
    public C3750z mo24360a(short s, String str) {
        m945a(str);
        StringBuilder sb = this.f1062a;
        sb.append(s);
        sb.append(10);
        return this;
    }

    /* renamed from: a */
    public C3750z mo24353a(int i, String str) {
        m945a(str);
        StringBuilder sb = this.f1062a;
        sb.append(i);
        sb.append(10);
        return this;
    }

    /* renamed from: a */
    private static boolean m948a(Writer writer, char[] cArr, int i, long j, long j2) {
        if (j >= j2) {
            return false;
        }
        if (((long) (i << 1)) + j <= j2) {
            try {
                writer.write(cArr, 0, i);
            } catch (IOException e) {
                C3749y.m935a(e);
                return false;
            }
        } else {
            writer.write(cArr, 0, (int) ((j2 - j) / 2));
        }
        writer.flush();
        return true;
    }

    /* renamed from: a */
    public C3750z mo24354a(long j, String str) {
        m945a(str);
        StringBuilder sb = this.f1062a;
        sb.append(j);
        sb.append(10);
        return this;
    }

    /* renamed from: a */
    public C3750z mo24352a(float f, String str) {
        m945a(str);
        StringBuilder sb = this.f1062a;
        sb.append(f);
        sb.append(10);
        return this;
    }

    /* renamed from: a */
    public C3750z mo24351a(double d, String str) {
        m945a(str);
        StringBuilder sb = this.f1062a;
        sb.append(d);
        sb.append(10);
        return this;
    }

    /* renamed from: a */
    public C3750z mo24357a(String str, String str2) {
        m945a(str2);
        if (str == null) {
            this.f1062a.append("null\n");
        } else {
            StringBuilder sb = this.f1062a;
            sb.append(str);
            sb.append(10);
        }
        return this;
    }

    /* renamed from: a */
    public static void m946a(String str, String str2, String str3, long j) {
        try {
            int i = 0;
            for (File next : m943a(str, str2, str3, j, false, (Comparator<File>) null)) {
                C3749y.m939c("File %s is to be deleted.", next.getName());
                if (next.delete()) {
                    i++;
                }
            }
            C3749y.m939c("Number of overdue trace files that has deleted: " + i, new Object[0]);
        } catch (Throwable th) {
            C3749y.m935a(th);
        }
    }

    /* renamed from: a */
    public C3750z mo24362a(byte[] bArr, String str) {
        m945a(str);
        if (bArr == null) {
            this.f1062a.append("null\n");
            return this;
        } else if (bArr.length == 0) {
            StringBuilder sb = this.f1062a;
            sb.append(bArr.length);
            sb.append(", []\n");
            return this;
        } else {
            StringBuilder sb2 = this.f1062a;
            sb2.append(bArr.length);
            sb2.append(", [\n");
            C3750z zVar = new C3750z(this.f1062a, this.f1063b + 1);
            for (byte a : bArr) {
                zVar.mo24349a(a, (String) null);
            }
            mo24350a(']', (String) null);
            return this;
        }
    }

    /* renamed from: a */
    private static List<File> m943a(String str, final String str2, final String str3, long j, boolean z, Comparator<File> comparator) {
        ArrayList arrayList = new ArrayList();
        if (str2 == null || str3 == null) {
            C3749y.m940d("prefix %s and/or postfix %s is null.", str2, str3);
            return arrayList;
        }
        long currentTimeMillis = System.currentTimeMillis();
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return arrayList;
        }
        try {
            File[] listFiles = file.listFiles(new FilenameFilter() {
                public final boolean accept(File file, String str) {
                    return str != null && str.startsWith(str2) && str.endsWith(str3);
                }
            });
            if (listFiles != null) {
                if (listFiles.length != 0) {
                    return m944a(listFiles, str2, str3, 0, currentTimeMillis - j);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            C3749y.m935a(th);
            return arrayList;
        }
    }

    /* renamed from: a */
    public C3750z mo24368a(short[] sArr, String str) {
        m945a(str);
        if (sArr == null) {
            this.f1062a.append("null\n");
            return this;
        } else if (sArr.length == 0) {
            StringBuilder sb = this.f1062a;
            sb.append(sArr.length);
            sb.append(", []\n");
            return this;
        } else {
            StringBuilder sb2 = this.f1062a;
            sb2.append(sArr.length);
            sb2.append(", [\n");
            C3750z zVar = new C3750z(this.f1062a, this.f1063b + 1);
            for (short a : sArr) {
                zVar.mo24360a(a, (String) null);
            }
            mo24350a(']', (String) null);
            return this;
        }
    }

    /* renamed from: a */
    public C3750z mo24365a(int[] iArr, String str) {
        m945a(str);
        if (iArr == null) {
            this.f1062a.append("null\n");
            return this;
        } else if (iArr.length == 0) {
            StringBuilder sb = this.f1062a;
            sb.append(iArr.length);
            sb.append(", []\n");
            return this;
        } else {
            StringBuilder sb2 = this.f1062a;
            sb2.append(iArr.length);
            sb2.append(", [\n");
            C3750z zVar = new C3750z(this.f1062a, this.f1063b + 1);
            for (int a : iArr) {
                zVar.mo24353a(a, (String) null);
            }
            mo24350a(']', (String) null);
            return this;
        }
    }

    /* renamed from: a */
    public C3750z mo24366a(long[] jArr, String str) {
        m945a(str);
        if (jArr == null) {
            this.f1062a.append("null\n");
            return this;
        } else if (jArr.length == 0) {
            StringBuilder sb = this.f1062a;
            sb.append(jArr.length);
            sb.append(", []\n");
            return this;
        } else {
            StringBuilder sb2 = this.f1062a;
            sb2.append(jArr.length);
            sb2.append(", [\n");
            C3750z zVar = new C3750z(this.f1062a, this.f1063b + 1);
            for (long a : jArr) {
                zVar.mo24354a(a, (String) null);
            }
            mo24350a(']', (String) null);
            return this;
        }
    }

    /* renamed from: a */
    private static List<File> m944a(File[] fileArr, String str, String str2, long j, long j2) {
        ArrayList arrayList = new ArrayList();
        for (File file : fileArr) {
            long a = m942a(file.getName(), str, str2);
            if (a >= 0 && j <= a && a <= j2) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public C3750z mo24364a(float[] fArr, String str) {
        m945a(str);
        if (fArr == null) {
            this.f1062a.append("null\n");
            return this;
        } else if (fArr.length == 0) {
            StringBuilder sb = this.f1062a;
            sb.append(fArr.length);
            sb.append(", []\n");
            return this;
        } else {
            StringBuilder sb2 = this.f1062a;
            sb2.append(fArr.length);
            sb2.append(", [\n");
            C3750z zVar = new C3750z(this.f1062a, this.f1063b + 1);
            for (float a : fArr) {
                zVar.mo24352a(a, (String) null);
            }
            mo24350a(']', (String) null);
            return this;
        }
    }

    /* renamed from: a */
    public static long m942a(String str, String str2, String str3) {
        if (str == null) {
            C3749y.m940d("File name is null.", new Object[0]);
            return -1;
        }
        try {
            if (str.startsWith(str2) && str.endsWith(str3)) {
                return Long.parseLong(str.substring(str2.length(), str.indexOf(str3)));
            }
        } catch (Throwable th) {
            C3749y.m935a(th);
        }
        return -1;
    }

    /* renamed from: a */
    public C3750z mo24363a(double[] dArr, String str) {
        m945a(str);
        if (dArr == null) {
            this.f1062a.append("null\n");
            return this;
        } else if (dArr.length == 0) {
            StringBuilder sb = this.f1062a;
            sb.append(dArr.length);
            sb.append(", []\n");
            return this;
        } else {
            StringBuilder sb2 = this.f1062a;
            sb2.append(dArr.length);
            sb2.append(", [\n");
            C3750z zVar = new C3750z(this.f1062a, this.f1063b + 1);
            for (double a : dArr) {
                zVar.mo24351a(a, (String) null);
            }
            mo24350a(']', (String) null);
            return this;
        }
    }

    /* renamed from: a */
    public static boolean m949a(String str, String str2, int i) {
        boolean z = true;
        C3749y.m939c("rqdp{  sv sd start} %s", str);
        if (str2 != null && str2.trim().length() > 0) {
            File file = new File(str);
            try {
                if (!file.exists()) {
                    if (file.getParentFile() != null) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                }
                long j = (long) i;
                if (file.length() >= j) {
                    z = false;
                }
                return m947a(file, str2, j, z);
            } catch (Throwable th) {
                if (!C3749y.m935a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public <K, V> C3750z mo24359a(Map<K, V> map, String str) {
        m945a(str);
        if (map == null) {
            this.f1062a.append("null\n");
            return this;
        } else if (map.isEmpty()) {
            StringBuilder sb = this.f1062a;
            sb.append(map.size());
            sb.append(", {}\n");
            return this;
        } else {
            StringBuilder sb2 = this.f1062a;
            sb2.append(map.size());
            sb2.append(", {\n");
            C3750z zVar = new C3750z(this.f1062a, this.f1063b + 1);
            C3750z zVar2 = new C3750z(this.f1062a, this.f1063b + 2);
            for (Map.Entry next : map.entrySet()) {
                zVar.mo24350a('(', (String) null);
                zVar2.mo24356a(next.getKey(), (String) null);
                zVar2.mo24356a(next.getValue(), (String) null);
                zVar.mo24350a(')', (String) null);
            }
            mo24350a('}', (String) null);
            return this;
        }
    }

    /* renamed from: a */
    public <T> C3750z mo24367a(T[] tArr, String str) {
        m945a(str);
        if (tArr == null) {
            this.f1062a.append("null\n");
            return this;
        } else if (tArr.length == 0) {
            StringBuilder sb = this.f1062a;
            sb.append(tArr.length);
            sb.append(", []\n");
            return this;
        } else {
            StringBuilder sb2 = this.f1062a;
            sb2.append(tArr.length);
            sb2.append(", [\n");
            C3750z zVar = new C3750z(this.f1062a, this.f1063b + 1);
            for (T a : tArr) {
                zVar.mo24356a(a, (String) null);
            }
            mo24350a(']', (String) null);
            return this;
        }
    }

    /* renamed from: a */
    public <T> C3750z mo24358a(Collection<T> collection, String str) {
        if (collection != null) {
            return mo24367a((T[]) collection.toArray(), str);
        }
        m945a(str);
        this.f1062a.append("null\t");
        return this;
    }

    /* renamed from: a */
    public <T> C3750z mo24356a(T t, String str) {
        if (t == null) {
            this.f1062a.append("null\n");
        } else if (t instanceof Byte) {
            mo24349a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            mo24361a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            mo24360a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            mo24353a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            mo24354a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            mo24352a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            mo24351a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            mo24357a((String) t, str);
        } else if (t instanceof Map) {
            mo24359a((Map) t, str);
        } else if (t instanceof List) {
            mo24358a((List) t, str);
        } else if (t instanceof C3723j) {
            mo24355a((C3723j) t, str);
        } else if (t instanceof byte[]) {
            mo24362a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            mo24356a((boolean[]) t, str);
        } else if (t instanceof short[]) {
            mo24368a((short[]) t, str);
        } else if (t instanceof int[]) {
            mo24365a((int[]) t, str);
        } else if (t instanceof long[]) {
            mo24366a((long[]) t, str);
        } else if (t instanceof float[]) {
            mo24364a((float[]) t, str);
        } else if (t instanceof double[]) {
            mo24363a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            mo24367a((T[]) (Object[]) t, str);
        } else {
            throw new C3714b("write object error: unsupport type.");
        }
        return this;
    }

    /* renamed from: a */
    public C3750z mo24355a(C3723j jVar, String str) {
        mo24350a('{', str);
        if (jVar == null) {
            StringBuilder sb = this.f1062a;
            sb.append(9);
            sb.append("null");
        } else {
            jVar.mo24245a(this.f1062a, this.f1063b + 1);
        }
        mo24350a('}', (String) null);
        return this;
    }
}
