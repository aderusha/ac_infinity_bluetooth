package com.zhihu.matisse.internal.entity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.internal.loader.AlbumLoader;

public class Album implements Parcelable {
    public static final String ALBUM_ID_ALL = String.valueOf(-1);
    public static final String ALBUM_NAME_ALL = "All";
    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        public Album createFromParcel(Parcel parcel) {
            return new Album(parcel);
        }

        public Album[] newArray(int i) {
            return new Album[i];
        }
    };
    private long mCount;
    private final Uri mCoverUri;
    private final String mDisplayName;
    private final String mId;

    public int describeContents() {
        return 0;
    }

    public Album(String str, Uri uri, String str2, long j) {
        this.mId = str;
        this.mCoverUri = uri;
        this.mDisplayName = str2;
        this.mCount = j;
    }

    private Album(Parcel parcel) {
        this.mId = parcel.readString();
        this.mCoverUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.mDisplayName = parcel.readString();
        this.mCount = parcel.readLong();
    }

    public static Album valueOf(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex(AlbumLoader.COLUMN_URI));
        String string2 = cursor.getString(cursor.getColumnIndex("bucket_id"));
        if (string == null) {
            string = "";
        }
        return new Album(string2, Uri.parse(string), cursor.getString(cursor.getColumnIndex("bucket_display_name")), cursor.getLong(cursor.getColumnIndex("count")));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeParcelable(this.mCoverUri, 0);
        parcel.writeString(this.mDisplayName);
        parcel.writeLong(this.mCount);
    }

    public String getId() {
        return this.mId;
    }

    public Uri getCoverUri() {
        return this.mCoverUri;
    }

    public long getCount() {
        return this.mCount;
    }

    public void addCaptureCount() {
        this.mCount++;
    }

    public String getDisplayName(Context context) {
        if (isAll()) {
            return context.getString(C3757R.string.album_name_all);
        }
        return this.mDisplayName;
    }

    public boolean isAll() {
        return ALBUM_ID_ALL.equals(this.mId);
    }

    public boolean isEmpty() {
        return this.mCount == 0;
    }
}
