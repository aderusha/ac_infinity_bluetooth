package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.C3695ab;
import java.util.Map;

/* compiled from: BUGLY */
public class StrategyBean implements Parcelable {
    public static final Parcelable.Creator<StrategyBean> CREATOR = new Parcelable.Creator<StrategyBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new StrategyBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new StrategyBean[i];
        }
    };

    /* renamed from: a */
    public static String f522a = "https://android.bugly.qq.com/rqd/async";

    /* renamed from: b */
    public static String f523b = "https://android.bugly.qq.com/rqd/async";

    /* renamed from: c */
    public long f524c;

    /* renamed from: d */
    public long f525d;

    /* renamed from: e */
    public boolean f526e;

    /* renamed from: f */
    public boolean f527f;

    /* renamed from: g */
    public boolean f528g;

    /* renamed from: h */
    public boolean f529h;

    /* renamed from: i */
    public boolean f530i;

    /* renamed from: j */
    public boolean f531j;

    /* renamed from: k */
    public boolean f532k;

    /* renamed from: l */
    public boolean f533l;

    /* renamed from: m */
    public boolean f534m;

    /* renamed from: n */
    public long f535n;

    /* renamed from: o */
    public long f536o;

    /* renamed from: p */
    public String f537p;

    /* renamed from: q */
    public String f538q;

    /* renamed from: r */
    public String f539r;

    /* renamed from: s */
    public Map<String, String> f540s;

    /* renamed from: t */
    public int f541t;

    /* renamed from: u */
    public long f542u;

    /* renamed from: v */
    public long f543v;

    public int describeContents() {
        return 0;
    }

    public StrategyBean() {
        this.f524c = -1;
        this.f525d = -1;
        this.f526e = true;
        this.f527f = true;
        this.f528g = true;
        this.f529h = true;
        this.f530i = false;
        this.f531j = true;
        this.f532k = true;
        this.f533l = true;
        this.f534m = true;
        this.f536o = 30000;
        this.f537p = f522a;
        this.f538q = f523b;
        this.f541t = 10;
        this.f542u = 300000;
        this.f543v = -1;
        this.f525d = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L");
        sb.append("@)");
        sb.setLength(0);
        sb.append("*^@K#K");
        sb.append("@!");
        this.f539r = sb.toString();
    }

    public StrategyBean(Parcel parcel) {
        this.f524c = -1;
        this.f525d = -1;
        boolean z = true;
        this.f526e = true;
        this.f527f = true;
        this.f528g = true;
        this.f529h = true;
        this.f530i = false;
        this.f531j = true;
        this.f532k = true;
        this.f533l = true;
        this.f534m = true;
        this.f536o = 30000;
        this.f537p = f522a;
        this.f538q = f523b;
        this.f541t = 10;
        this.f542u = 300000;
        this.f543v = -1;
        try {
            this.f525d = parcel.readLong();
            this.f526e = parcel.readByte() == 1;
            this.f527f = parcel.readByte() == 1;
            this.f528g = parcel.readByte() == 1;
            this.f537p = parcel.readString();
            this.f538q = parcel.readString();
            this.f539r = parcel.readString();
            this.f540s = C3695ab.m686b(parcel);
            this.f529h = parcel.readByte() == 1;
            this.f530i = parcel.readByte() == 1;
            this.f533l = parcel.readByte() == 1;
            this.f534m = parcel.readByte() == 1;
            this.f536o = parcel.readLong();
            this.f531j = parcel.readByte() == 1;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.f532k = z;
            this.f535n = parcel.readLong();
            this.f541t = parcel.readInt();
            this.f542u = parcel.readLong();
            this.f543v = parcel.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f525d);
        parcel.writeByte(this.f526e ? (byte) 1 : 0);
        parcel.writeByte(this.f527f ? (byte) 1 : 0);
        parcel.writeByte(this.f528g ? (byte) 1 : 0);
        parcel.writeString(this.f537p);
        parcel.writeString(this.f538q);
        parcel.writeString(this.f539r);
        C3695ab.m688b(parcel, this.f540s);
        parcel.writeByte(this.f529h ? (byte) 1 : 0);
        parcel.writeByte(this.f530i ? (byte) 1 : 0);
        parcel.writeByte(this.f533l ? (byte) 1 : 0);
        parcel.writeByte(this.f534m ? (byte) 1 : 0);
        parcel.writeLong(this.f536o);
        parcel.writeByte(this.f531j ? (byte) 1 : 0);
        parcel.writeByte(this.f532k ? (byte) 1 : 0);
        parcel.writeLong(this.f535n);
        parcel.writeInt(this.f541t);
        parcel.writeLong(this.f542u);
        parcel.writeLong(this.f543v);
    }
}
