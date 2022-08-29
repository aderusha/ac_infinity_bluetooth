package com.eternal.framework.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.core.view.ViewCompat;
import com.eternal.framework.utils.compression.Luban;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class ImageUtils {
    private static final float MAX_SIZE = 200.0f;
    public static final int REQUEST_CODE_GETIMAGE_BYCAMERA = 1;
    public static final int REQUEST_CODE_GETIMAGE_BYCROP = 2;
    public static final int REQUEST_CODE_GETIMAGE_BYSDCARD = 0;
    public static final int REQUEST_CODE_GETIMAGE_BYSDCARD_info = 4;
    public static final int REQUEST_CODE_GETIMAGE_IMAGEPAVER = 3;
    public static final String SDCARD = "/sdcard";
    public static final String SDCARD_MNT = "/mnt/sdcard";
    static Bitmap bitmap;

    public static void saveImage(Context context, String str, Bitmap bitmap2) throws IOException {
        saveImage(context, str, bitmap2, 100);
    }

    public static void saveImage(Context context, String str, Bitmap bitmap2, int i) throws IOException {
        if (bitmap2 != null && str != null && context != null) {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            openFileOutput.write(byteArrayOutputStream.toByteArray());
            openFileOutput.close();
        }
    }

    public static void saveImageToSD(Context context, String str, Bitmap bitmap2, int i) throws IOException {
        if (bitmap2 != null) {
            File file = new File(str.substring(0, str.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            bitmap2.compress(Bitmap.CompressFormat.JPEG, i, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            if (context != null) {
                scanPhoto(context, str);
            }
        }
    }

    public static void saveBackgroundImage(Context context, String str, Bitmap bitmap2, int i) throws IOException {
        if (bitmap2 != null) {
            File file = new File(str.substring(0, str.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            bitmap2.compress(Bitmap.CompressFormat.PNG, i, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            if (context != null) {
                scanPhoto(context, str);
            }
        }
    }

    private static void scanPhoto(Context context, String str) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        context.sendBroadcast(intent);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: java.io.FileInputStream} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getBitmap(android.content.Context r1, java.lang.String r2) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = r1.openFileInput(r2)     // Catch:{ FileNotFoundException -> 0x001c, OutOfMemoryError -> 0x0016, all -> 0x0014 }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ FileNotFoundException -> 0x0012, OutOfMemoryError -> 0x0010 }
        L_0x0009:
            r1.close()     // Catch:{ Exception -> 0x0022 }
            goto L_0x0022
        L_0x000d:
            r2 = move-exception
            r0 = r1
            goto L_0x0023
        L_0x0010:
            r2 = move-exception
            goto L_0x0018
        L_0x0012:
            r2 = move-exception
            goto L_0x001e
        L_0x0014:
            r2 = move-exception
            goto L_0x0023
        L_0x0016:
            r2 = move-exception
            r1 = r0
        L_0x0018:
            r2.printStackTrace()     // Catch:{ all -> 0x000d }
            goto L_0x0009
        L_0x001c:
            r2 = move-exception
            r1 = r0
        L_0x001e:
            r2.printStackTrace()     // Catch:{ all -> 0x000d }
            goto L_0x0009
        L_0x0022:
            return r0
        L_0x0023:
            r0.close()     // Catch:{ Exception -> 0x0026 }
        L_0x0026:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.utils.ImageUtils.getBitmap(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }

    public static Bitmap getBitmapByPath(String str) {
        return getBitmapByPath(str, (BitmapFactory.Options) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.io.FileInputStream} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getBitmapByPath(java.lang.String r2, android.graphics.BitmapFactory.Options r3) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ FileNotFoundException -> 0x001f, OutOfMemoryError -> 0x0019, all -> 0x0017 }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x001f, OutOfMemoryError -> 0x0019, all -> 0x0017 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x001f, OutOfMemoryError -> 0x0019, all -> 0x0017 }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x001f, OutOfMemoryError -> 0x0019, all -> 0x0017 }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r2, r0, r3)     // Catch:{ FileNotFoundException -> 0x0015, OutOfMemoryError -> 0x0013 }
        L_0x000f:
            r2.close()     // Catch:{ Exception -> 0x0025 }
            goto L_0x0025
        L_0x0013:
            r3 = move-exception
            goto L_0x001b
        L_0x0015:
            r3 = move-exception
            goto L_0x0021
        L_0x0017:
            r3 = move-exception
            goto L_0x0028
        L_0x0019:
            r3 = move-exception
            r2 = r0
        L_0x001b:
            r3.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x000f
        L_0x001f:
            r3 = move-exception
            r2 = r0
        L_0x0021:
            r3.printStackTrace()     // Catch:{ all -> 0x0026 }
            goto L_0x000f
        L_0x0025:
            return r0
        L_0x0026:
            r3 = move-exception
            r0 = r2
        L_0x0028:
            r0.close()     // Catch:{ Exception -> 0x002b }
        L_0x002b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.utils.ImageUtils.getBitmapByPath(java.lang.String, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getBitmapByFile(java.io.File r2) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x001a, OutOfMemoryError -> 0x0014, all -> 0x0012 }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x001a, OutOfMemoryError -> 0x0014, all -> 0x0012 }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch:{ FileNotFoundException -> 0x0010, OutOfMemoryError -> 0x000e }
        L_0x000a:
            r1.close()     // Catch:{ Exception -> 0x0020 }
            goto L_0x0020
        L_0x000e:
            r2 = move-exception
            goto L_0x0016
        L_0x0010:
            r2 = move-exception
            goto L_0x001c
        L_0x0012:
            r2 = move-exception
            goto L_0x0023
        L_0x0014:
            r2 = move-exception
            r1 = r0
        L_0x0016:
            r2.printStackTrace()     // Catch:{ all -> 0x0021 }
            goto L_0x000a
        L_0x001a:
            r2 = move-exception
            r1 = r0
        L_0x001c:
            r2.printStackTrace()     // Catch:{ all -> 0x0021 }
            goto L_0x000a
        L_0x0020:
            return r0
        L_0x0021:
            r2 = move-exception
            r0 = r1
        L_0x0023:
            r0.close()     // Catch:{ Exception -> 0x0026 }
        L_0x0026:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.utils.ImageUtils.getBitmapByFile(java.io.File):android.graphics.Bitmap");
    }

    public static String getTempFileName() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SS").format(new Timestamp(System.currentTimeMillis()));
    }

    public static String getCamerPath() {
        return Environment.getExternalStorageDirectory() + File.separator + "FounderNews" + File.separator;
    }

    public static String getAbsolutePathFromNoStandardUri(Uri uri) {
        String decode = Uri.decode(uri.toString());
        String str = "file:///sdcard" + File.separator;
        String str2 = "file:///mnt/sdcard" + File.separator;
        if (decode.startsWith(str)) {
            return Environment.getExternalStorageDirectory().getPath() + File.separator + decode.substring(str.length());
        } else if (!decode.startsWith(str2)) {
            return null;
        } else {
            return Environment.getExternalStorageDirectory().getPath() + File.separator + decode.substring(str2.length());
        }
    }

    public static String getAbsoluteImagePath(Activity activity, Uri uri) {
        Cursor managedQuery = activity.managedQuery(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null);
        if (managedQuery != null) {
            int columnIndexOrThrow = managedQuery.getColumnIndexOrThrow("_data");
            if (managedQuery.getCount() > 0 && managedQuery.moveToFirst()) {
                return managedQuery.getString(columnIndexOrThrow);
            }
        }
        return "";
    }

    public static Bitmap loadImgThumbnail(Activity activity, String str, int i) {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor managedQuery = activity.managedQuery(uri, new String[]{"_id", "_display_name"}, "_display_name='" + str + "'", (String[]) null, (String) null);
        if (managedQuery == null || managedQuery.getCount() <= 0 || !managedQuery.moveToFirst()) {
            return null;
        }
        ContentResolver contentResolver = activity.getContentResolver();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        return MediaStore.Images.Thumbnails.getThumbnail(contentResolver, (long) managedQuery.getInt(0), i, options);
    }

    public static Bitmap loadImgThumbnail(String str, int i, int i2) {
        return zoomBitmap(getBitmapByPath(str), i, i2);
    }

    public static String getLatestImage(Activity activity) {
        Activity activity2 = activity;
        Cursor managedQuery = activity2.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data"}, (String) null, (String[]) null, "_id desc");
        if (managedQuery != null && managedQuery.getCount() > 0) {
            managedQuery.moveToFirst();
            managedQuery.moveToFirst();
            if (!managedQuery.isAfterLast()) {
                return managedQuery.getString(1);
            }
        }
        return null;
    }

    public static int[] scaleImageSize(int[] iArr, int i) {
        if (iArr[0] <= i && iArr[1] <= i) {
            return iArr;
        }
        double max = ((double) i) / ((double) Math.max(iArr[0], iArr[1]));
        return new int[]{(int) (((double) iArr[0]) * max), (int) (((double) iArr[1]) * max)};
    }

    public static void createImageThumbnail(Context context, String str, String str2, int i, int i2) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bitmapByPath = getBitmapByPath(str, options);
        if (bitmapByPath != null) {
            int[] scaleImageSize = scaleImageSize(new int[]{bitmapByPath.getWidth(), bitmapByPath.getHeight()}, i);
            saveImageToSD((Context) null, str2, zoomBitmap(bitmapByPath, scaleImageSize[0], scaleImageSize[1]), i2);
        }
    }

    public static Bitmap zoomBitmap(Bitmap bitmap2, int i, int i2) {
        if (bitmap2 == null) {
            return null;
        }
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap2, 0, 0, width, height, matrix, true);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap2, int i, int i2) {
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) i) / ((float) width), ((float) i2) / ((float) height));
        return Bitmap.createBitmap(bitmap2, 0, 0, width, height, matrix, true);
    }

    public static Bitmap reDrawBitMap(Activity activity, Bitmap bitmap2) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        bitmap2.getHeight();
        int width = bitmap2.getWidth();
        float f = width >= i2 ? ((float) i2) / ((float) width) : 1.0f;
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap.Config config;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (drawable.getOpacity() != -1) {
            config = Bitmap.Config.ARGB_8888;
        } else {
            config = Bitmap.Config.RGB_565;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap2, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap2, rect, rect, paint);
        return createBitmap;
    }

    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap2) {
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        int i = height / 2;
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2, 0, i, width, i, matrix, false);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, i + height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap2);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        float f = (float) height;
        float f2 = (float) (height + 4);
        Canvas canvas2 = canvas;
        float f3 = f;
        float f4 = (float) width;
        canvas2.drawRect(0.0f, f3, f4, f2, new Paint());
        canvas.drawBitmap(createBitmap, 0.0f, f2, (Paint) null);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, (float) bitmap2.getHeight(), 0.0f, (float) (createBitmap2.getHeight() + 4), 1895825407, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas2.drawRect(0.0f, f3, f4, (float) (createBitmap2.getHeight() + 4), paint);
        return createBitmap2;
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap2) {
        return new BitmapDrawable(bitmap2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x001d A[SYNTHETIC, Splitter:B:16:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0024 A[SYNTHETIC, Splitter:B:24:0x0024] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getImageType(java.io.File r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x0027
            boolean r1 = r2.exists()
            if (r1 != 0) goto L_0x000a
            goto L_0x0027
        L_0x000a:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0021, all -> 0x001a }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0021, all -> 0x001a }
            java.lang.String r2 = getImageType((java.io.InputStream) r1)     // Catch:{ IOException -> 0x0022, all -> 0x0017 }
            r1.close()     // Catch:{ IOException -> 0x0016 }
        L_0x0016:
            return r2
        L_0x0017:
            r2 = move-exception
            r0 = r1
            goto L_0x001b
        L_0x001a:
            r2 = move-exception
        L_0x001b:
            if (r0 == 0) goto L_0x0020
            r0.close()     // Catch:{ IOException -> 0x0020 }
        L_0x0020:
            throw r2
        L_0x0021:
            r1 = r0
        L_0x0022:
            if (r1 == 0) goto L_0x0027
            r1.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.utils.ImageUtils.getImageType(java.io.File):java.lang.String");
    }

    public static String getImageType(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[8];
            inputStream.read(bArr);
            return getImageType(bArr);
        } catch (IOException unused) {
            return null;
        }
    }

    public static String getImageType(byte[] bArr) {
        if (isJPEG(bArr)) {
            return "image/jpeg";
        }
        if (isGIF(bArr)) {
            return "image/gif";
        }
        if (isPNG(bArr)) {
            return "image/png";
        }
        if (isBMP(bArr)) {
            return "application/x-bmp";
        }
        return null;
    }

    private static boolean isJPEG(byte[] bArr) {
        if (bArr.length >= 2 && bArr[0] == -1 && bArr[1] == -40) {
            return true;
        }
        return false;
    }

    private static boolean isGIF(byte[] bArr) {
        if (bArr.length < 6 || bArr[0] != 71 || bArr[1] != 73 || bArr[2] != 70 || bArr[3] != 56) {
            return false;
        }
        if ((bArr[4] == 55 || bArr[4] == 57) && bArr[5] == 97) {
            return true;
        }
        return false;
    }

    private static boolean isPNG(byte[] bArr) {
        if (bArr.length >= 8 && bArr[0] == -119 && bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71 && bArr[4] == 13 && bArr[5] == 10 && bArr[6] == 26 && bArr[7] == 10) {
            return true;
        }
        return false;
    }

    private static boolean isBMP(byte[] bArr) {
        if (bArr.length >= 2 && bArr[0] == 66 && bArr[1] == 77) {
            return true;
        }
        return false;
    }

    public static String getImagePath(Uri uri, Activity activity) {
        Cursor query = activity.getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return uri.toString();
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndexOrThrow("_data"));
        query.close();
        return string;
    }

    public static Bitmap loadPicasaImageFromGalley(final Uri uri, final Activity activity) {
        Cursor query = activity.getContentResolver().query(uri, new String[]{"_data", "_display_name"}, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return null;
        }
        query.moveToFirst();
        if (query.getColumnIndex("_display_name") != -1) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ImageUtils.bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }).start();
        }
        query.close();
        return bitmap;
    }

    public static File compressBitmap(String str, String str2, String str3) throws IOException {
        if (!TextUtils.isEmpty(str)) {
            return convertToFile(compressImage(revitionImageSize(new File(str))), str2, str3);
        }
        return null;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap2, float f, float f2) {
        float width = (float) bitmap2.getWidth();
        float height = (float) bitmap2.getHeight();
        Matrix matrix = new Matrix();
        float f3 = 1.0f;
        float f4 = f < width ? f / width : 1.0f;
        if (f2 < height) {
            f3 = f2 / height;
        }
        matrix.postScale(f4, f3);
        return Bitmap.createBitmap(bitmap2, 0, 0, (int) width, (int) height, matrix, true);
    }

    public static Bitmap revitionImageSize(File file) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(bufferedInputStream, (Rect) null, options);
        bufferedInputStream.close();
        while (true) {
            if (options.outWidth / i > 600 || options.outHeight / i > 600) {
                i++;
            } else {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                options.inSampleSize = i;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeStream(bufferedInputStream2, (Rect) null, options);
            }
        }
    }

    public static Bitmap compressImage(Bitmap bitmap2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (((float) (byteArrayOutputStream.toByteArray().length / 1024)) > MAX_SIZE) {
            byteArrayOutputStream.reset();
            bitmap2.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 10;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), (Rect) null, (BitmapFactory.Options) null);
    }

    public static File convertToFile(Bitmap bitmap2, String str, String str2) throws IOException {
        File createFile = createFile(checkTargetCacheDir(str), str2, ".jpg");
        if (!createFile.exists() ? createFile.createNewFile() : false) {
            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(createFile));
        }
        return createFile;
    }

    public static File checkTargetCacheDir(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public static File createFile(File file, String str, String str2) {
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.ENGLISH);
        return new File(file, str + simpleDateFormat.format(new Date(System.currentTimeMillis())) + str2);
    }

    public static void compressWithRx(List<String> list, Observer observer) {
        Luban.get(Utils.getContext()).load(list).putGear(3).asListObservable().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).doOnError(new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends File>>() {
            public ObservableSource<? extends File> apply(Throwable th) throws Exception {
                return Observable.empty();
            }
        }).subscribe(observer);
    }

    public static void compressWithRx(String str, Consumer consumer) {
        Luban.get(Utils.getContext()).load(str).putGear(3).asObservable().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).doOnError(new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends File>>() {
            public ObservableSource<? extends File> apply(Throwable th) throws Exception {
                return Observable.empty();
            }
        }).subscribe(consumer);
    }
}
