package com.zhihu.matisse.internal.entity;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import com.zhihu.matisse.MimeType;

public class Item implements Parcelable {
    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel parcel) {
            return new Item(parcel);
        }

        public Item[] newArray(int i) {
            return new Item[i];
        }
    };
    public static final String ITEM_DISPLAY_NAME_CAPTURE = "Capture";
    public static final long ITEM_ID_CAPTURE = -1;
    public final long duration;

    /* renamed from: id */
    public final long f1071id;
    public final String mimeType;
    public final long size;
    public final Uri uri;

    public int describeContents() {
        return 0;
    }

    private Item(long j, String str, long j2, long j3) {
        Uri uri2;
        this.f1071id = j;
        this.mimeType = str;
        if (isImage()) {
            uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (isVideo()) {
            uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri2 = MediaStore.Files.getContentUri("external");
        }
        this.uri = ContentUris.withAppendedId(uri2, j);
        this.size = j2;
        this.duration = j3;
    }

    private Item(Parcel parcel) {
        this.f1071id = parcel.readLong();
        this.mimeType = parcel.readString();
        this.uri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.size = parcel.readLong();
        this.duration = parcel.readLong();
    }

    public static Item valueOf(Cursor cursor) {
        return new Item(cursor.getLong(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("mime_type")), cursor.getLong(cursor.getColumnIndex("_size")), cursor.getLong(cursor.getColumnIndex("duration")));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f1071id);
        parcel.writeString(this.mimeType);
        parcel.writeParcelable(this.uri, 0);
        parcel.writeLong(this.size);
        parcel.writeLong(this.duration);
    }

    public Uri getContentUri() {
        return this.uri;
    }

    public boolean isCapture() {
        return this.f1071id == -1;
    }

    public boolean isImage() {
        return MimeType.isImage(this.mimeType);
    }

    public boolean isGif() {
        return MimeType.isGif(this.mimeType);
    }

    public boolean isVideo() {
        return MimeType.isVideo(this.mimeType);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) {
            return false;
        }
        Item item = (Item) obj;
        if (this.f1071id != item.f1071id) {
            return false;
        }
        String str = this.mimeType;
        if ((str == null || !str.equals(item.mimeType)) && (this.mimeType != null || item.mimeType != null)) {
            return false;
        }
        Uri uri2 = this.uri;
        if (((uri2 != null && uri2.equals(item.uri)) || (this.uri == null && item.uri == null)) && this.size == item.size && this.duration == item.duration) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = Long.valueOf(this.f1071id).hashCode() + 31;
        String str = this.mimeType;
        if (str != null) {
            hashCode = (hashCode * 31) + str.hashCode();
        }
        return (((((hashCode * 31) + this.uri.hashCode()) * 31) + Long.valueOf(this.size).hashCode()) * 31) + Long.valueOf(this.duration).hashCode();
    }
}
