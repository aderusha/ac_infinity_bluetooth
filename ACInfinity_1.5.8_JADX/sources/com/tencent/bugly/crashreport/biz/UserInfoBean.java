package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.p003os.EnvironmentCompat;
import com.tencent.bugly.proguard.C3695ab;
import java.util.Map;

/* compiled from: BUGLY */
public class UserInfoBean implements Parcelable {
    public static final Parcelable.Creator<UserInfoBean> CREATOR = new Parcelable.Creator<UserInfoBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new UserInfoBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new UserInfoBean[i];
        }
    };

    /* renamed from: a */
    public long f398a;

    /* renamed from: b */
    public int f399b;

    /* renamed from: c */
    public String f400c;

    /* renamed from: d */
    public String f401d;

    /* renamed from: e */
    public long f402e;

    /* renamed from: f */
    public long f403f;

    /* renamed from: g */
    public long f404g;

    /* renamed from: h */
    public long f405h;

    /* renamed from: i */
    public long f406i;

    /* renamed from: j */
    public String f407j;

    /* renamed from: k */
    public long f408k;

    /* renamed from: l */
    public boolean f409l;

    /* renamed from: m */
    public String f410m;

    /* renamed from: n */
    public String f411n;

    /* renamed from: o */
    public int f412o;

    /* renamed from: p */
    public int f413p;

    /* renamed from: q */
    public int f414q;

    /* renamed from: r */
    public Map<String, String> f415r;

    /* renamed from: s */
    public Map<String, String> f416s;

    public int describeContents() {
        return 0;
    }

    public UserInfoBean() {
        this.f408k = 0;
        this.f409l = false;
        this.f410m = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f413p = -1;
        this.f414q = -1;
        this.f415r = null;
        this.f416s = null;
    }

    public UserInfoBean(Parcel parcel) {
        this.f408k = 0;
        boolean z = false;
        this.f409l = false;
        this.f410m = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f413p = -1;
        this.f414q = -1;
        this.f415r = null;
        this.f416s = null;
        this.f399b = parcel.readInt();
        this.f400c = parcel.readString();
        this.f401d = parcel.readString();
        this.f402e = parcel.readLong();
        this.f403f = parcel.readLong();
        this.f404g = parcel.readLong();
        this.f405h = parcel.readLong();
        this.f406i = parcel.readLong();
        this.f407j = parcel.readString();
        this.f408k = parcel.readLong();
        this.f409l = parcel.readByte() == 1 ? true : z;
        this.f410m = parcel.readString();
        this.f413p = parcel.readInt();
        this.f414q = parcel.readInt();
        this.f415r = C3695ab.m686b(parcel);
        this.f416s = C3695ab.m686b(parcel);
        this.f411n = parcel.readString();
        this.f412o = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f399b);
        parcel.writeString(this.f400c);
        parcel.writeString(this.f401d);
        parcel.writeLong(this.f402e);
        parcel.writeLong(this.f403f);
        parcel.writeLong(this.f404g);
        parcel.writeLong(this.f405h);
        parcel.writeLong(this.f406i);
        parcel.writeString(this.f407j);
        parcel.writeLong(this.f408k);
        parcel.writeByte(this.f409l ? (byte) 1 : 0);
        parcel.writeString(this.f410m);
        parcel.writeInt(this.f413p);
        parcel.writeInt(this.f414q);
        C3695ab.m688b(parcel, this.f415r);
        C3695ab.m688b(parcel, this.f416s);
        parcel.writeString(this.f411n);
        parcel.writeInt(this.f412o);
    }
}
