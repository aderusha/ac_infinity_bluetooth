package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.C3695ab;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Parcelable.Creator<CrashDetailBean> CREATOR = new Parcelable.Creator<CrashDetailBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new CrashDetailBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new CrashDetailBean[i];
        }
    };

    /* renamed from: A */
    public String f560A;

    /* renamed from: B */
    public String f561B;

    /* renamed from: C */
    public long f562C;

    /* renamed from: D */
    public long f563D;

    /* renamed from: E */
    public long f564E;

    /* renamed from: F */
    public long f565F;

    /* renamed from: G */
    public long f566G;

    /* renamed from: H */
    public long f567H;

    /* renamed from: I */
    public long f568I;

    /* renamed from: J */
    public long f569J;

    /* renamed from: K */
    public long f570K;

    /* renamed from: L */
    public String f571L;

    /* renamed from: M */
    public String f572M;

    /* renamed from: N */
    public String f573N;

    /* renamed from: O */
    public String f574O;

    /* renamed from: P */
    public long f575P;

    /* renamed from: Q */
    public boolean f576Q;

    /* renamed from: R */
    public Map<String, String> f577R;

    /* renamed from: S */
    public Map<String, String> f578S;

    /* renamed from: T */
    public int f579T;

    /* renamed from: U */
    public int f580U;

    /* renamed from: V */
    public Map<String, String> f581V;

    /* renamed from: W */
    public Map<String, String> f582W;

    /* renamed from: X */
    public byte[] f583X;

    /* renamed from: Y */
    public String f584Y;

    /* renamed from: Z */
    public String f585Z;

    /* renamed from: a */
    public long f586a;

    /* renamed from: aa */
    private String f587aa;

    /* renamed from: b */
    public int f588b;

    /* renamed from: c */
    public String f589c;

    /* renamed from: d */
    public boolean f590d;

    /* renamed from: e */
    public String f591e;

    /* renamed from: f */
    public String f592f;

    /* renamed from: g */
    public String f593g;

    /* renamed from: h */
    public Map<String, PlugInBean> f594h;

    /* renamed from: i */
    public Map<String, PlugInBean> f595i;

    /* renamed from: j */
    public boolean f596j;

    /* renamed from: k */
    public boolean f597k;

    /* renamed from: l */
    public int f598l;

    /* renamed from: m */
    public String f599m;

    /* renamed from: n */
    public String f600n;

    /* renamed from: o */
    public String f601o;

    /* renamed from: p */
    public String f602p;

    /* renamed from: q */
    public String f603q;

    /* renamed from: r */
    public long f604r;

    /* renamed from: s */
    public String f605s;

    /* renamed from: t */
    public int f606t;

    /* renamed from: u */
    public String f607u;

    /* renamed from: v */
    public String f608v;

    /* renamed from: w */
    public String f609w;

    /* renamed from: x */
    public String f610x;

    /* renamed from: y */
    public byte[] f611y;

    /* renamed from: z */
    public Map<String, String> f612z;

    public int describeContents() {
        return 0;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        int i;
        CrashDetailBean crashDetailBean = (CrashDetailBean) obj;
        if (crashDetailBean == null || this.f604r - crashDetailBean.f604r > 0) {
            return 1;
        }
        return i < 0 ? -1 : 0;
    }

    public CrashDetailBean() {
        this.f586a = -1;
        this.f588b = 0;
        this.f589c = UUID.randomUUID().toString();
        this.f590d = false;
        this.f591e = "";
        this.f592f = "";
        this.f593g = "";
        this.f594h = null;
        this.f595i = null;
        this.f596j = false;
        this.f597k = false;
        this.f598l = 0;
        this.f599m = "";
        this.f600n = "";
        this.f601o = "";
        this.f602p = "";
        this.f603q = "";
        this.f604r = -1;
        this.f605s = null;
        this.f606t = 0;
        this.f607u = "";
        this.f608v = "";
        this.f609w = null;
        this.f610x = null;
        this.f611y = null;
        this.f612z = null;
        this.f560A = "";
        this.f561B = "";
        this.f562C = -1;
        this.f563D = -1;
        this.f564E = -1;
        this.f565F = -1;
        this.f566G = -1;
        this.f567H = -1;
        this.f568I = -1;
        this.f569J = -1;
        this.f570K = -1;
        this.f571L = "";
        this.f587aa = "";
        this.f572M = "";
        this.f573N = "";
        this.f574O = "";
        this.f575P = -1;
        this.f576Q = false;
        this.f577R = null;
        this.f578S = null;
        this.f579T = -1;
        this.f580U = -1;
        this.f581V = null;
        this.f582W = null;
        this.f583X = null;
        this.f584Y = null;
        this.f585Z = null;
    }

    public CrashDetailBean(Parcel parcel) {
        this.f586a = -1;
        boolean z = false;
        this.f588b = 0;
        this.f589c = UUID.randomUUID().toString();
        this.f590d = false;
        this.f591e = "";
        this.f592f = "";
        this.f593g = "";
        this.f594h = null;
        this.f595i = null;
        this.f596j = false;
        this.f597k = false;
        this.f598l = 0;
        this.f599m = "";
        this.f600n = "";
        this.f601o = "";
        this.f602p = "";
        this.f603q = "";
        this.f604r = -1;
        this.f605s = null;
        this.f606t = 0;
        this.f607u = "";
        this.f608v = "";
        this.f609w = null;
        this.f610x = null;
        this.f611y = null;
        this.f612z = null;
        this.f560A = "";
        this.f561B = "";
        this.f562C = -1;
        this.f563D = -1;
        this.f564E = -1;
        this.f565F = -1;
        this.f566G = -1;
        this.f567H = -1;
        this.f568I = -1;
        this.f569J = -1;
        this.f570K = -1;
        this.f571L = "";
        this.f587aa = "";
        this.f572M = "";
        this.f573N = "";
        this.f574O = "";
        this.f575P = -1;
        this.f576Q = false;
        this.f577R = null;
        this.f578S = null;
        this.f579T = -1;
        this.f580U = -1;
        this.f581V = null;
        this.f582W = null;
        this.f583X = null;
        this.f584Y = null;
        this.f585Z = null;
        this.f588b = parcel.readInt();
        this.f589c = parcel.readString();
        this.f590d = parcel.readByte() == 1;
        this.f591e = parcel.readString();
        this.f592f = parcel.readString();
        this.f593g = parcel.readString();
        this.f596j = parcel.readByte() == 1;
        this.f597k = parcel.readByte() == 1;
        this.f598l = parcel.readInt();
        this.f599m = parcel.readString();
        this.f600n = parcel.readString();
        this.f601o = parcel.readString();
        this.f602p = parcel.readString();
        this.f603q = parcel.readString();
        this.f604r = parcel.readLong();
        this.f605s = parcel.readString();
        this.f606t = parcel.readInt();
        this.f607u = parcel.readString();
        this.f608v = parcel.readString();
        this.f609w = parcel.readString();
        this.f612z = C3695ab.m686b(parcel);
        this.f560A = parcel.readString();
        this.f561B = parcel.readString();
        this.f562C = parcel.readLong();
        this.f563D = parcel.readLong();
        this.f564E = parcel.readLong();
        this.f565F = parcel.readLong();
        this.f566G = parcel.readLong();
        this.f567H = parcel.readLong();
        this.f571L = parcel.readString();
        this.f587aa = parcel.readString();
        this.f572M = parcel.readString();
        this.f573N = parcel.readString();
        this.f574O = parcel.readString();
        this.f575P = parcel.readLong();
        this.f576Q = parcel.readByte() == 1 ? true : z;
        this.f577R = C3695ab.m686b(parcel);
        this.f594h = C3695ab.m671a(parcel);
        this.f595i = C3695ab.m671a(parcel);
        this.f579T = parcel.readInt();
        this.f580U = parcel.readInt();
        this.f581V = C3695ab.m686b(parcel);
        this.f582W = C3695ab.m686b(parcel);
        this.f583X = parcel.createByteArray();
        this.f611y = parcel.createByteArray();
        this.f584Y = parcel.readString();
        this.f585Z = parcel.readString();
        this.f610x = parcel.readString();
        this.f568I = parcel.readLong();
        this.f569J = parcel.readLong();
        this.f570K = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f588b);
        parcel.writeString(this.f589c);
        parcel.writeByte(this.f590d ? (byte) 1 : 0);
        parcel.writeString(this.f591e);
        parcel.writeString(this.f592f);
        parcel.writeString(this.f593g);
        parcel.writeByte(this.f596j ? (byte) 1 : 0);
        parcel.writeByte(this.f597k ? (byte) 1 : 0);
        parcel.writeInt(this.f598l);
        parcel.writeString(this.f599m);
        parcel.writeString(this.f600n);
        parcel.writeString(this.f601o);
        parcel.writeString(this.f602p);
        parcel.writeString(this.f603q);
        parcel.writeLong(this.f604r);
        parcel.writeString(this.f605s);
        parcel.writeInt(this.f606t);
        parcel.writeString(this.f607u);
        parcel.writeString(this.f608v);
        parcel.writeString(this.f609w);
        C3695ab.m688b(parcel, this.f612z);
        parcel.writeString(this.f560A);
        parcel.writeString(this.f561B);
        parcel.writeLong(this.f562C);
        parcel.writeLong(this.f563D);
        parcel.writeLong(this.f564E);
        parcel.writeLong(this.f565F);
        parcel.writeLong(this.f566G);
        parcel.writeLong(this.f567H);
        parcel.writeString(this.f571L);
        parcel.writeString(this.f587aa);
        parcel.writeString(this.f572M);
        parcel.writeString(this.f573N);
        parcel.writeString(this.f574O);
        parcel.writeLong(this.f575P);
        parcel.writeByte(this.f576Q ? (byte) 1 : 0);
        C3695ab.m688b(parcel, this.f577R);
        C3695ab.m673a(parcel, this.f594h);
        C3695ab.m673a(parcel, this.f595i);
        parcel.writeInt(this.f579T);
        parcel.writeInt(this.f580U);
        C3695ab.m688b(parcel, this.f581V);
        C3695ab.m688b(parcel, this.f582W);
        parcel.writeByteArray(this.f583X);
        parcel.writeByteArray(this.f611y);
        parcel.writeString(this.f584Y);
        parcel.writeString(this.f585Z);
        parcel.writeString(this.f610x);
        parcel.writeLong(this.f568I);
        parcel.writeLong(this.f569J);
        parcel.writeLong(this.f570K);
    }
}
