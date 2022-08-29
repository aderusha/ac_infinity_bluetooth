package com.zhihu.matisse.internal.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import com.alibaba.android.arouter.utils.Consts;
import com.eternal.export.CSVUtil;
import com.zhihu.matisse.C3757R;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public final class PhotoMetadataUtils {
    private static final int MAX_WIDTH = 1600;
    private static final String SCHEME_CONTENT = "content";
    private static final String TAG = "PhotoMetadataUtils";

    private PhotoMetadataUtils() {
        throw new AssertionError("oops! the utility class is about to be instantiated...");
    }

    public static int getPixelsCount(ContentResolver contentResolver, Uri uri) {
        Point bitmapBound = getBitmapBound(contentResolver, uri);
        return bitmapBound.x * bitmapBound.y;
    }

    public static Point getBitmapSize(Uri uri, Activity activity) {
        ContentResolver contentResolver = activity.getContentResolver();
        Point bitmapBound = getBitmapBound(contentResolver, uri);
        int i = bitmapBound.x;
        int i2 = bitmapBound.y;
        if (shouldRotate(contentResolver, uri)) {
            i = bitmapBound.y;
            i2 = bitmapBound.x;
        }
        if (i2 == 0) {
            return new Point(MAX_WIDTH, MAX_WIDTH);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f = (float) i;
        float f2 = ((float) displayMetrics.widthPixels) / f;
        float f3 = (float) i2;
        float f4 = ((float) displayMetrics.heightPixels) / f3;
        if (f2 > f4) {
            return new Point((int) (f * f2), (int) (f3 * f4));
        }
        return new Point((int) (f * f2), (int) (f3 * f4));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0038, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002b */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0033 A[SYNTHETIC, Splitter:B:19:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003e A[SYNTHETIC, Splitter:B:25:0x003e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Point getBitmapBound(android.content.ContentResolver r3, android.net.Uri r4) {
        /*
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch:{ FileNotFoundException -> 0x002b }
            r1.<init>()     // Catch:{ FileNotFoundException -> 0x002b }
            r2 = 1
            r1.inJustDecodeBounds = r2     // Catch:{ FileNotFoundException -> 0x002b }
            java.io.InputStream r3 = r3.openInputStream(r4)     // Catch:{ FileNotFoundException -> 0x002b }
            android.graphics.BitmapFactory.decodeStream(r3, r0, r1)     // Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
            int r4 = r1.outWidth     // Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
            int r0 = r1.outHeight     // Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
            android.graphics.Point r1 = new android.graphics.Point     // Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
            r1.<init>(r4, r0)     // Catch:{ FileNotFoundException -> 0x0027, all -> 0x0024 }
            if (r3 == 0) goto L_0x0023
            r3.close()     // Catch:{ IOException -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0023:
            return r1
        L_0x0024:
            r4 = move-exception
            r0 = r3
            goto L_0x003c
        L_0x0027:
            r0 = r3
            goto L_0x002b
        L_0x0029:
            r4 = move-exception
            goto L_0x003c
        L_0x002b:
            android.graphics.Point r3 = new android.graphics.Point     // Catch:{ all -> 0x0029 }
            r4 = 0
            r3.<init>(r4, r4)     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x003b
            r0.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r4 = move-exception
            r4.printStackTrace()
        L_0x003b:
            return r3
        L_0x003c:
            if (r0 == 0) goto L_0x0046
            r0.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0046:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zhihu.matisse.internal.utils.PhotoMetadataUtils.getBitmapBound(android.content.ContentResolver, android.net.Uri):android.graphics.Point");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getPath(android.content.ContentResolver r9, android.net.Uri r10) {
        /*
            java.lang.String r0 = "_data"
            r1 = 0
            if (r10 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r2 = r10.getScheme()
            java.lang.String r3 = "content"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0046
            java.lang.String[] r5 = new java.lang.String[]{r0}     // Catch:{ all -> 0x003f }
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r9
            r4 = r10
            android.database.Cursor r9 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x003f }
            if (r9 == 0) goto L_0x0039
            boolean r10 = r9.moveToFirst()     // Catch:{ all -> 0x0036 }
            if (r10 != 0) goto L_0x0028
            goto L_0x0039
        L_0x0028:
            int r10 = r9.getColumnIndex(r0)     // Catch:{ all -> 0x0036 }
            java.lang.String r10 = r9.getString(r10)     // Catch:{ all -> 0x0036 }
            if (r9 == 0) goto L_0x0035
            r9.close()
        L_0x0035:
            return r10
        L_0x0036:
            r10 = move-exception
            r1 = r9
            goto L_0x0040
        L_0x0039:
            if (r9 == 0) goto L_0x003e
            r9.close()
        L_0x003e:
            return r1
        L_0x003f:
            r10 = move-exception
        L_0x0040:
            if (r1 == 0) goto L_0x0045
            r1.close()
        L_0x0045:
            throw r10
        L_0x0046:
            java.lang.String r9 = r10.getPath()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zhihu.matisse.internal.utils.PhotoMetadataUtils.getPath(android.content.ContentResolver, android.net.Uri):java.lang.String");
    }

    public static IncapableCause isAcceptable(Context context, Item item) {
        if (!isSelectableType(context, item)) {
            return new IncapableCause(context.getString(C3757R.string.error_file_type));
        }
        if (SelectionSpec.getInstance().filters == null) {
            return null;
        }
        for (Filter filter : SelectionSpec.getInstance().filters) {
            IncapableCause filter2 = filter.filter(context, item);
            if (filter2 != null) {
                return filter2;
            }
        }
        return null;
    }

    private static boolean isSelectableType(Context context, Item item) {
        if (context == null) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        for (MimeType checkType : SelectionSpec.getInstance().mimeTypeSet) {
            if (checkType.checkType(contentResolver, item.getContentUri())) {
                return true;
            }
        }
        return false;
    }

    private static boolean shouldRotate(ContentResolver contentResolver, Uri uri) {
        try {
            int attributeInt = ExifInterfaceCompat.newInstance(getPath(contentResolver, uri)).getAttributeInt("Orientation", -1);
            if (attributeInt == 6 || attributeInt == 8) {
                return true;
            }
            return false;
        } catch (IOException unused) {
            String str = TAG;
            Log.e(str, "could not read exif info of the image: " + uri);
            return false;
        }
    }

    public static float getSizeInMB(long j) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        decimalFormat.applyPattern("0.0");
        String format = decimalFormat.format((double) ((((float) j) / 1024.0f) / 1024.0f));
        String str = TAG;
        Log.e(str, "getSizeInMB: " + format);
        return Float.valueOf(format.replaceAll(CSVUtil.COLUMN_SEPARATOR, Consts.DOT)).floatValue();
    }
}
