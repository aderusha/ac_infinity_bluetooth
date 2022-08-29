package com.zhihu.matisse.internal.loader;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.loader.content.CursorLoader;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import java.util.HashMap;
import java.util.HashSet;

public class AlbumLoader extends CursorLoader {
    private static final String BUCKET_ORDER_BY = "datetaken DESC";
    private static final String[] COLUMNS = {"_id", COLUMN_BUCKET_ID, COLUMN_BUCKET_DISPLAY_NAME, "mime_type", COLUMN_URI, "count"};
    private static final String COLUMN_BUCKET_DISPLAY_NAME = "bucket_display_name";
    private static final String COLUMN_BUCKET_ID = "bucket_id";
    public static final String COLUMN_COUNT = "count";
    public static final String COLUMN_URI = "uri";
    private static final String[] PROJECTION = {"_id", COLUMN_BUCKET_ID, COLUMN_BUCKET_DISPLAY_NAME, "mime_type", "COUNT(*) AS count"};
    private static final String[] PROJECTION_29 = {"_id", COLUMN_BUCKET_ID, COLUMN_BUCKET_DISPLAY_NAME, "mime_type"};
    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");
    private static final String SELECTION = "(media_type=? OR media_type=?) AND _size>0) GROUP BY (bucket_id";
    private static final String SELECTION_29 = "(media_type=? OR media_type=?) AND _size>0";
    private static final String[] SELECTION_ARGS = {String.valueOf(1), String.valueOf(3)};
    private static final String SELECTION_FOR_SINGLE_MEDIA_GIF_TYPE = "media_type=? AND _size>0 AND mime_type=?) GROUP BY (bucket_id";
    private static final String SELECTION_FOR_SINGLE_MEDIA_GIF_TYPE_29 = "media_type=? AND _size>0 AND mime_type=?";
    private static final String SELECTION_FOR_SINGLE_MEDIA_TYPE = "media_type=? AND _size>0) GROUP BY (bucket_id";
    private static final String SELECTION_FOR_SINGLE_MEDIA_TYPE_29 = "media_type=? AND _size>0";

    public void onContentChanged() {
    }

    private static String[] getSelectionArgsForSingleMediaType(int i) {
        return new String[]{String.valueOf(i)};
    }

    private static String[] getSelectionArgsForSingleMediaGifType(int i) {
        return new String[]{String.valueOf(i), "image/gif"};
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private AlbumLoader(android.content.Context r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            r7 = this;
            android.net.Uri r2 = QUERY_URI
            boolean r0 = beforeAndroidTen()
            if (r0 == 0) goto L_0x000b
            java.lang.String[] r0 = PROJECTION
            goto L_0x000d
        L_0x000b:
            java.lang.String[] r0 = PROJECTION_29
        L_0x000d:
            r3 = r0
            java.lang.String r6 = "datetaken DESC"
            r0 = r7
            r1 = r8
            r4 = r9
            r5 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zhihu.matisse.internal.loader.AlbumLoader.<init>(android.content.Context, java.lang.String, java.lang.String[]):void");
    }

    public static CursorLoader newInstance(Context context) {
        String[] strArr;
        String str;
        if (SelectionSpec.getInstance().onlyShowGif()) {
            str = beforeAndroidTen() ? SELECTION_FOR_SINGLE_MEDIA_GIF_TYPE : SELECTION_FOR_SINGLE_MEDIA_GIF_TYPE_29;
            strArr = getSelectionArgsForSingleMediaGifType(1);
        } else if (SelectionSpec.getInstance().onlyShowImages()) {
            str = beforeAndroidTen() ? SELECTION_FOR_SINGLE_MEDIA_TYPE : SELECTION_FOR_SINGLE_MEDIA_TYPE_29;
            strArr = getSelectionArgsForSingleMediaType(1);
        } else if (SelectionSpec.getInstance().onlyShowVideos()) {
            str = beforeAndroidTen() ? SELECTION_FOR_SINGLE_MEDIA_TYPE : SELECTION_FOR_SINGLE_MEDIA_TYPE_29;
            strArr = getSelectionArgsForSingleMediaType(3);
        } else {
            str = beforeAndroidTen() ? SELECTION : SELECTION_29;
            strArr = SELECTION_ARGS;
        }
        return new AlbumLoader(context, str, strArr);
    }

    public Cursor loadInBackground() {
        int i;
        Uri uri;
        String str;
        long j;
        int i2;
        Uri uri2;
        String str2;
        char c;
        Cursor loadInBackground = super.loadInBackground();
        String[] strArr = COLUMNS;
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        if (beforeAndroidTen()) {
            MatrixCursor matrixCursor2 = new MatrixCursor(strArr);
            if (loadInBackground != null) {
                i2 = 0;
                while (loadInBackground.moveToNext()) {
                    long j2 = loadInBackground.getLong(loadInBackground.getColumnIndex("_id"));
                    long j3 = loadInBackground.getLong(loadInBackground.getColumnIndex(COLUMN_BUCKET_ID));
                    String string = loadInBackground.getString(loadInBackground.getColumnIndex(COLUMN_BUCKET_DISPLAY_NAME));
                    String string2 = loadInBackground.getString(loadInBackground.getColumnIndex("mime_type"));
                    Uri uri3 = getUri(loadInBackground);
                    int i3 = loadInBackground.getInt(loadInBackground.getColumnIndex("count"));
                    matrixCursor2.addRow(new String[]{Long.toString(j2), Long.toString(j3), string, string2, uri3.toString(), String.valueOf(i3)});
                    i2 += i3;
                }
                uri2 = loadInBackground.moveToFirst() ? getUri(loadInBackground) : null;
            } else {
                uri2 = null;
                i2 = 0;
            }
            String[] strArr2 = new String[6];
            strArr2[0] = Album.ALBUM_ID_ALL;
            strArr2[1] = Album.ALBUM_ID_ALL;
            strArr2[2] = Album.ALBUM_NAME_ALL;
            strArr2[3] = null;
            if (uri2 == null) {
                c = 4;
                str2 = null;
            } else {
                str2 = uri2.toString();
                c = 4;
            }
            strArr2[c] = str2;
            strArr2[5] = String.valueOf(i2);
            matrixCursor.addRow(strArr2);
            return new MergeCursor(new Cursor[]{matrixCursor, matrixCursor2});
        }
        HashMap hashMap = new HashMap();
        if (loadInBackground != null) {
            while (loadInBackground.moveToNext()) {
                long j4 = loadInBackground.getLong(loadInBackground.getColumnIndex(COLUMN_BUCKET_ID));
                Long l = (Long) hashMap.get(Long.valueOf(j4));
                if (l == null) {
                    j = 1L;
                } else {
                    j = Long.valueOf(l.longValue() + 1);
                }
                hashMap.put(Long.valueOf(j4), j);
            }
        }
        MatrixCursor matrixCursor3 = new MatrixCursor(COLUMNS);
        if (loadInBackground == null || !loadInBackground.moveToFirst()) {
            uri = null;
            i = 0;
        } else {
            Uri uri4 = getUri(loadInBackground);
            HashSet hashSet = new HashSet();
            i = 0;
            while (true) {
                long j5 = loadInBackground.getLong(loadInBackground.getColumnIndex(COLUMN_BUCKET_ID));
                if (!hashSet.contains(Long.valueOf(j5))) {
                    long j6 = loadInBackground.getLong(loadInBackground.getColumnIndex("_id"));
                    String string3 = loadInBackground.getString(loadInBackground.getColumnIndex(COLUMN_BUCKET_DISPLAY_NAME));
                    String string4 = loadInBackground.getString(loadInBackground.getColumnIndex("mime_type"));
                    Uri uri5 = getUri(loadInBackground);
                    long longValue = ((Long) hashMap.get(Long.valueOf(j5))).longValue();
                    matrixCursor3.addRow(new String[]{Long.toString(j6), Long.toString(j5), string3, string4, uri5.toString(), String.valueOf(longValue)});
                    hashSet.add(Long.valueOf(j5));
                    i = (int) (((long) i) + longValue);
                }
                if (!loadInBackground.moveToNext()) {
                    break;
                }
            }
            uri = uri4;
        }
        String[] strArr3 = new String[6];
        strArr3[0] = Album.ALBUM_ID_ALL;
        strArr3[1] = Album.ALBUM_ID_ALL;
        strArr3[2] = Album.ALBUM_NAME_ALL;
        strArr3[3] = null;
        if (uri == null) {
            str = null;
        } else {
            str = uri.toString();
        }
        strArr3[4] = str;
        strArr3[5] = String.valueOf(i);
        matrixCursor.addRow(strArr3);
        return new MergeCursor(new Cursor[]{matrixCursor, matrixCursor3});
    }

    private static Uri getUri(Cursor cursor) {
        Uri uri;
        long j = cursor.getLong(cursor.getColumnIndex("_id"));
        String string = cursor.getString(cursor.getColumnIndex("mime_type"));
        if (MimeType.isImage(string)) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (MimeType.isVideo(string)) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = MediaStore.Files.getContentUri("external");
        }
        return ContentUris.withAppendedId(uri, j);
    }

    private static boolean beforeAndroidTen() {
        return Build.VERSION.SDK_INT < 29;
    }
}
