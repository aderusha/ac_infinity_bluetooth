package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: BUGLY */
public class PlugInBean implements Parcelable {
    public static final Parcelable.Creator<PlugInBean> CREATOR = new Parcelable.Creator<PlugInBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PlugInBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PlugInBean[i];
        }
    };

    /* renamed from: a */
    public final String f446a;

    /* renamed from: b */
    public final String f447b;

    /* renamed from: c */
    public final String f448c;

    public int describeContents() {
        return 0;
    }

    public PlugInBean(String str, String str2, String str3) {
        this.f446a = str;
        this.f447b = str2;
        this.f448c = str3;
    }

    public String toString() {
        return "plid:" + this.f446a + " plV:" + this.f447b + " plUUID:" + this.f448c;
    }

    public PlugInBean(Parcel parcel) {
        this.f446a = parcel.readString();
        this.f447b = parcel.readString();
        this.f448c = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f446a);
        parcel.writeString(this.f447b);
        parcel.writeString(this.f448c);
    }
}
