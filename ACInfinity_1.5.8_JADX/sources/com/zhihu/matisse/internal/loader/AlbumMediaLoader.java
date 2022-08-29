package com.zhihu.matisse.internal.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.loader.content.CursorLoader;
import com.zhihu.matisse.internal.entity.Album;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;

public class AlbumMediaLoader extends CursorLoader {
    private static final String ORDER_BY = "datetaken DESC";
    private static final String[] PROJECTION = {"_id", "_display_name", "mime_type", "_size", "duration"};
    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");
    private static final String SELECTION_ALBUM = "(media_type=? OR media_type=?) AND  bucket_id=? AND _size>0";
    private static final String SELECTION_ALBUM_FOR_GIF = "media_type=? AND  bucket_id=? AND mime_type=? AND _size>0";
    private static final String SELECTION_ALBUM_FOR_SINGLE_MEDIA_TYPE = "media_type=? AND  bucket_id=? AND _size>0";
    private static final String SELECTION_ALL = "(media_type=? OR media_type=?) AND _size>0";
    private static final String[] SELECTION_ALL_ARGS = {String.valueOf(1), String.valueOf(3)};
    private static final String SELECTION_ALL_FOR_GIF = "media_type=? AND mime_type=? AND _size>0";
    private static final String SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE = "media_type=? AND _size>0";
    private final boolean mEnableCapture;

    public void onContentChanged() {
    }

    private static String[] getSelectionArgsForSingleMediaType(int i) {
        return new String[]{String.valueOf(i)};
    }

    private static String[] getSelectionAlbumArgs(String str) {
        return new String[]{String.valueOf(1), String.valueOf(3), str};
    }

    private static String[] getSelectionAlbumArgsForSingleMediaType(int i, String str) {
        return new String[]{String.valueOf(i), str};
    }

    private static String[] getSelectionArgsForGifType(int i) {
        return new String[]{String.valueOf(i), "image/gif"};
    }

    private static String[] getSelectionAlbumArgsForGifType(int i, String str) {
        return new String[]{String.valueOf(i), str, "image/gif"};
    }

    private AlbumMediaLoader(Context context, String str, String[] strArr, boolean z) {
        super(context, QUERY_URI, PROJECTION, str, strArr, ORDER_BY);
        this.mEnableCapture = z;
    }

    public static CursorLoader newInstance(Context context, Album album, boolean z) {
        String[] strArr;
        String str;
        String str2;
        if (album.isAll()) {
            boolean onlyShowGif = SelectionSpec.getInstance().onlyShowGif();
            str = SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE;
            if (onlyShowGif) {
                strArr = getSelectionArgsForGifType(1);
                str = SELECTION_ALL_FOR_GIF;
            } else if (SelectionSpec.getInstance().onlyShowImages()) {
                strArr = getSelectionArgsForSingleMediaType(1);
            } else if (SelectionSpec.getInstance().onlyShowVideos()) {
                strArr = getSelectionArgsForSingleMediaType(3);
            } else {
                strArr = SELECTION_ALL_ARGS;
                str = SELECTION_ALL;
            }
        } else {
            boolean onlyShowGif2 = SelectionSpec.getInstance().onlyShowGif();
            str = SELECTION_ALBUM_FOR_SINGLE_MEDIA_TYPE;
            if (onlyShowGif2) {
                strArr = getSelectionAlbumArgsForGifType(1, album.getId());
                str2 = SELECTION_ALBUM_FOR_GIF;
            } else {
                if (SelectionSpec.getInstance().onlyShowImages()) {
                    strArr = getSelectionAlbumArgsForSingleMediaType(1, album.getId());
                } else if (SelectionSpec.getInstance().onlyShowVideos()) {
                    strArr = getSelectionAlbumArgsForSingleMediaType(3, album.getId());
                } else {
                    strArr = getSelectionAlbumArgs(album.getId());
                    str2 = SELECTION_ALBUM;
                }
                z = false;
            }
            str = str2;
            z = false;
        }
        return new AlbumMediaLoader(context, str, strArr, z);
    }

    public Cursor loadInBackground() {
        Cursor loadInBackground = super.loadInBackground();
        if (!this.mEnableCapture || !MediaStoreCompat.hasCameraFeature(getContext())) {
            return loadInBackground;
        }
        MatrixCursor matrixCursor = new MatrixCursor(PROJECTION);
        matrixCursor.addRow(new Object[]{-1L, Item.ITEM_DISPLAY_NAME_CAPTURE, "", 0, 0});
        return new MergeCursor(new Cursor[]{matrixCursor, loadInBackground});
    }
}
