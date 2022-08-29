package com.zhihu.matisse;

import android.content.ContentResolver;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.collection.ArraySet;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;

public enum MimeType {
    JPEG("image/jpeg", arraySetOf("jpg", "jpeg")),
    PNG("image/png", arraySetOf("png")),
    GIF("image/gif", arraySetOf("gif")),
    BMP("image/x-ms-bmp", arraySetOf("bmp")),
    WEBP("image/webp", arraySetOf("webp")),
    MPEG("video/mpeg", arraySetOf("mpeg", "mpg")),
    MP4("video/mp4", arraySetOf("mp4", "m4v")),
    QUICKTIME("video/quicktime", arraySetOf("mov")),
    THREEGPP("video/3gpp", arraySetOf("3gp", "3gpp")),
    THREEGPP2("video/3gpp2", arraySetOf("3g2", "3gpp2")),
    MKV("video/x-matroska", arraySetOf("mkv")),
    WEBM("video/webm", arraySetOf("webm")),
    TS("video/mp2ts", arraySetOf("ts")),
    AVI("video/avi", arraySetOf("avi"));
    
    private final Set<String> mExtensions;
    private final String mMimeTypeName;

    private MimeType(String str, Set<String> set) {
        this.mMimeTypeName = str;
        this.mExtensions = set;
    }

    public static Set<MimeType> ofAll() {
        return EnumSet.allOf(MimeType.class);
    }

    /* renamed from: of */
    public static Set<MimeType> m970of(MimeType mimeType, MimeType... mimeTypeArr) {
        return EnumSet.of(mimeType, mimeTypeArr);
    }

    public static Set<MimeType> ofImage() {
        return EnumSet.of(JPEG, PNG, GIF, BMP, WEBP);
    }

    public static Set<MimeType> ofImage(boolean z) {
        return EnumSet.of(GIF);
    }

    public static Set<MimeType> ofGif() {
        return ofImage(true);
    }

    public static Set<MimeType> ofVideo() {
        return EnumSet.of(MPEG, new MimeType[]{MP4, QUICKTIME, THREEGPP, THREEGPP2, MKV, WEBM, TS, AVI});
    }

    public static boolean isImage(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("image");
    }

    public static boolean isVideo(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("video");
    }

    public static boolean isGif(String str) {
        if (str == null) {
            return false;
        }
        return str.equals(GIF.toString());
    }

    private static Set<String> arraySetOf(String... strArr) {
        return new ArraySet(Arrays.asList(strArr));
    }

    public String toString() {
        return this.mMimeTypeName;
    }

    public boolean checkType(ContentResolver contentResolver, Uri uri) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        if (uri == null) {
            return false;
        }
        String extensionFromMimeType = singleton.getExtensionFromMimeType(contentResolver.getType(uri));
        String str = null;
        boolean z = false;
        for (String next : this.mExtensions) {
            if (next.equals(extensionFromMimeType)) {
                return true;
            }
            if (!z) {
                str = PhotoMetadataUtils.getPath(contentResolver, uri);
                if (!TextUtils.isEmpty(str)) {
                    str = str.toLowerCase(Locale.US);
                }
                z = true;
            }
            if (str != null && str.endsWith(next)) {
                return true;
            }
        }
        return false;
    }
}
