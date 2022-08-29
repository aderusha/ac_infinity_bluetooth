package com.eternal.framework.utils.compression;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import p014io.reactivex.Observable;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.functions.Predicate;
import p014io.reactivex.schedulers.Schedulers;

public class Luban {
    private static String DEFAULT_DISK_CACHE_DIR = "smartcity_disk_cache";
    private static final int FIRST_GEAR = 1;
    private static volatile Luban INSTANCE = null;
    private static final String TAG = "smartcity";
    public static final int THIRD_GEAR = 3;
    /* access modifiers changed from: private */
    public OnCompressListener compressListener;
    private String filename;
    private int gear = 3;
    private final File mCacheDir;
    private String mFile;
    private List<String> mListFile = new ArrayList();

    private Luban(File file) {
        this.mCacheDir = file;
    }

    private static synchronized File getPhotoCacheDir(Context context) {
        File photoCacheDir;
        synchronized (Luban.class) {
            photoCacheDir = getPhotoCacheDir(context, DEFAULT_DISK_CACHE_DIR);
        }
        return photoCacheDir;
    }

    private static File getPhotoCacheDir(Context context, String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (!file.mkdirs() && (!file.exists() || !file.isDirectory())) {
                return null;
            }
            File file2 = new File(cacheDir + "/.nomedia");
            if (file2.mkdirs() || (file2.exists() && file2.isDirectory())) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "default disk cache dir is null");
        }
        return null;
    }

    public static Luban get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Luban(getPhotoCacheDir(context));
        }
        return INSTANCE;
    }

    public Luban launch() {
        Preconditions.checkNotNull(this.mFile, "the image file cannot be null, please call .load() before this method!");
        OnCompressListener onCompressListener = this.compressListener;
        if (onCompressListener != null) {
            onCompressListener.onStart();
        }
        int i = this.gear;
        if (i == 1) {
            Observable.just(this.mFile).map(new Function<String, File>() {
                public File apply(String str) throws Exception {
                    return Luban.this.firstCompress(new File(str));
                }
            }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnError(new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    if (Luban.this.compressListener != null) {
                        Luban.this.compressListener.onError(th);
                    }
                }
            }).onErrorResumeNext(Observable.empty()).filter(new Predicate<File>() {
                public boolean test(File file) throws Exception {
                    return file != null;
                }
            }).subscribe(new Consumer<File>() {
                public void accept(File file) throws Exception {
                    if (Luban.this.compressListener != null) {
                        Luban.this.compressListener.onSuccess(file);
                    }
                }
            });
        } else if (i == 3) {
            Observable.just(this.mFile).map(new Function<String, File>() {
                public File apply(String str) throws Exception {
                    return Luban.this.thirdCompress(new File(str));
                }
            }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnError(new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    if (Luban.this.compressListener != null) {
                        Luban.this.compressListener.onError(th);
                    }
                }
            }).onErrorResumeNext(Observable.empty()).filter(new Predicate<File>() {
                public boolean test(File file) throws Exception {
                    return file != null;
                }
            }).subscribe(new Consumer<File>() {
                public void accept(File file) throws Exception {
                    if (Luban.this.compressListener != null) {
                        Luban.this.compressListener.onSuccess(file);
                    }
                }
            });
        }
        return this;
    }

    public Luban load(String str) {
        this.mFile = str;
        return this;
    }

    public Luban load(List<String> list) {
        this.mListFile = list;
        return this;
    }

    public Luban setCompressListener(OnCompressListener onCompressListener) {
        this.compressListener = onCompressListener;
        return this;
    }

    public Luban putGear(int i) {
        this.gear = i;
        return this;
    }

    public Luban setFilename(String str) {
        this.filename = str;
        return this;
    }

    public Observable<File> asObservable() {
        int i = this.gear;
        if (i == 1) {
            return Observable.just(this.mFile).map(new Function<String, File>() {
                public File apply(String str) throws Exception {
                    if (!TextUtils.isEmpty(str) && !str.contains("http")) {
                        File file = new File(str);
                        if (file.exists()) {
                            return Luban.this.firstCompress(file);
                        }
                    }
                    return null;
                }
            });
        }
        return i == 3 ? Observable.just(this.mFile).map(new Function<String, File>() {
            public File apply(String str) throws Exception {
                if (!TextUtils.isEmpty(str) && !str.contains("http")) {
                    File file = new File(str);
                    if (file.exists()) {
                        return Luban.this.thirdCompress(file);
                    }
                }
                return null;
            }
        }) : Observable.empty();
    }

    public Observable<File> asListObservable() {
        int i = this.gear;
        if (i == 1) {
            return Observable.fromIterable(this.mListFile).map(new Function<String, File>() {
                public File apply(String str) throws Exception {
                    if (TextUtils.isEmpty(str)) {
                        return null;
                    }
                    File file = new File(str);
                    if (file.exists()) {
                        return Luban.this.firstCompress(file);
                    }
                    return null;
                }
            });
        }
        return i == 3 ? Observable.fromIterable(this.mListFile).map(new Function<String, File>() {
            public File apply(String str) throws Exception {
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                File file = new File(str);
                if (file.exists()) {
                    return Luban.this.thirdCompress(file);
                }
                return null;
            }
        }) : Observable.empty();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e6, code lost:
        if (r3 < 100.0d) goto L_0x0159;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0102, code lost:
        if (r3 < 100.0d) goto L_0x0159;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0136, code lost:
        if (r3 < 100.0d) goto L_0x0159;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File thirdCompress(java.io.File r23) {
        /*
            r22 = this;
            r8 = r22
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.File r1 = r8.mCacheDir
            java.lang.String r1 = r1.getAbsolutePath()
            r0.append(r1)
            java.lang.String r1 = java.io.File.separator
            r0.append(r1)
            java.lang.String r1 = r8.filename
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0026
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            goto L_0x0028
        L_0x0026:
            java.lang.String r1 = r8.filename
        L_0x0028:
            r0.append(r1)
            java.lang.String r1 = ".jpg"
            r0.append(r1)
            java.lang.String r2 = r0.toString()
            java.lang.String r1 = r23.getAbsolutePath()
            int r5 = r8.getImageSpinAngle(r1)
            int[] r0 = r8.getImageSize(r1)
            r3 = 0
            r0 = r0[r3]
            int[] r3 = r8.getImageSize(r1)
            r4 = 1
            r3 = r3[r4]
            int r6 = r0 % 2
            if (r6 != r4) goto L_0x0050
            int r0 = r0 + 1
        L_0x0050:
            int r6 = r3 % 2
            if (r6 != r4) goto L_0x0056
            int r3 = r3 + 1
        L_0x0056:
            if (r0 <= r3) goto L_0x005a
            r6 = r3
            goto L_0x005b
        L_0x005a:
            r6 = r0
        L_0x005b:
            if (r0 <= r3) goto L_0x005f
            r7 = r0
            goto L_0x0060
        L_0x005f:
            r7 = r3
        L_0x0060:
            double r9 = (double) r6
            double r11 = (double) r7
            double r9 = r9 / r11
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r15 = 1024(0x400, double:5.06E-321)
            r17 = 4603241769126068224(0x3fe2000000000000, double:0.5625)
            r19 = 4636737291354636288(0x4059000000000000, double:100.0)
            int r21 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r21 > 0) goto L_0x0105
            int r13 = (r9 > r17 ? 1 : (r9 == r17 ? 0 : -1))
            if (r13 <= 0) goto L_0x0105
            r9 = 1664(0x680, float:2.332E-42)
            r10 = 4633641066610819072(0x404e000000000000, double:60.0)
            r12 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r7 >= r9) goto L_0x00a3
            long r17 = r23.length()
            long r17 = r17 / r15
            r14 = 150(0x96, double:7.4E-322)
            int r4 = (r17 > r14 ? 1 : (r17 == r14 ? 0 : -1))
            if (r4 >= 0) goto L_0x0088
            return r23
        L_0x0088:
            int r6 = r6 * r7
            double r6 = (double) r6
            r14 = 4655033164840828928(0x409a000000000000, double:1664.0)
            double r12 = java.lang.Math.pow(r14, r12)
            double r6 = r6 / r12
            r12 = 4639481672377565184(0x4062c00000000000, double:150.0)
            double r6 = r6 * r12
            int r4 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r4 >= 0) goto L_0x009e
            goto L_0x009f
        L_0x009e:
            r10 = r6
        L_0x009f:
            r4 = r3
            r3 = r0
            goto L_0x015d
        L_0x00a3:
            r0 = 4990(0x137e, float:6.992E-42)
            r14 = 4643985272004935680(0x4072c00000000000, double:300.0)
            if (r7 < r9) goto L_0x00ce
            if (r7 >= r0) goto L_0x00ce
            int r6 = r6 / 2
            int r7 = r7 / 2
            int r0 = r6 * r7
            double r3 = (double) r0
            r10 = 4657704978096324608(0x40a37e0000000000, double:2495.0)
            double r9 = java.lang.Math.pow(r10, r12)
            double r3 = r3 / r9
            double r3 = r3 * r14
            r9 = 4633641066610819072(0x404e000000000000, double:60.0)
            int r0 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x00c9
            r10 = r9
            goto L_0x00ca
        L_0x00c9:
            r10 = r3
        L_0x00ca:
            r3 = r6
            r4 = r7
            goto L_0x015d
        L_0x00ce:
            r9 = 4657847914607935488(0x40a4000000000000, double:2560.0)
            if (r7 < r0) goto L_0x00ee
            r0 = 10240(0x2800, float:1.4349E-41)
            if (r7 >= r0) goto L_0x00ee
            int r6 = r6 / 4
            int r7 = r7 / 4
            int r0 = r6 * r7
            double r3 = (double) r0
            double r9 = java.lang.Math.pow(r9, r12)
            double r3 = r3 / r9
            double r3 = r3 * r14
            int r0 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ea
            goto L_0x0159
        L_0x00ea:
            r19 = r3
            goto L_0x0159
        L_0x00ee:
            int r0 = r7 / 1280
            if (r0 != 0) goto L_0x00f3
            goto L_0x00f4
        L_0x00f3:
            r4 = r0
        L_0x00f4:
            int r6 = r6 / r4
            int r7 = r7 / r4
            int r0 = r6 * r7
            double r3 = (double) r0
            double r9 = java.lang.Math.pow(r9, r12)
            double r3 = r3 / r9
            double r3 = r3 * r14
            int r0 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ea
            goto L_0x0159
        L_0x0105:
            int r0 = (r9 > r17 ? 1 : (r9 == r17 ? 0 : -1))
            if (r0 > 0) goto L_0x0139
            r13 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r0 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r0 <= 0) goto L_0x0139
            r0 = 1280(0x500, float:1.794E-42)
            if (r7 >= r0) goto L_0x011f
            long r9 = r23.length()
            long r9 = r9 / r15
            r11 = 200(0xc8, double:9.9E-322)
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 >= 0) goto L_0x011f
            return r23
        L_0x011f:
            int r0 = r7 / 1280
            if (r0 != 0) goto L_0x0124
            goto L_0x0125
        L_0x0124:
            r4 = r0
        L_0x0125:
            int r6 = r6 / r4
            int r7 = r7 / r4
            int r0 = r6 * r7
            double r3 = (double) r0
            r9 = 4705170895067414528(0x414c200000000000, double:3686400.0)
            double r3 = r3 / r9
            r9 = 4645744490609377280(0x4079000000000000, double:400.0)
            double r3 = r3 * r9
            int r0 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ea
            goto L_0x0159
        L_0x0139:
            r3 = 4653344314980564992(0x4094000000000000, double:1280.0)
            double r9 = r3 / r9
            double r11 = r11 / r9
            double r11 = java.lang.Math.ceil(r11)
            int r0 = (int) r11
            int r6 = r6 / r0
            int r7 = r7 / r0
            int r0 = r6 * r7
            double r11 = (double) r0
            double r9 = r9 * r3
            double r11 = r11 / r9
            r3 = 4647503709213818880(0x407f400000000000, double:500.0)
            double r11 = r11 * r3
            int r0 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            if (r0 >= 0) goto L_0x0157
            goto L_0x0159
        L_0x0157:
            r19 = r11
        L_0x0159:
            r3 = r6
            r4 = r7
            r10 = r19
        L_0x015d:
            long r6 = (long) r10
            r0 = r22
            java.io.File r0 = r0.compress(r1, r2, r3, r4, r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.utils.compression.Luban.thirdCompress(java.io.File):java.io.File");
    }

    /* access modifiers changed from: private */
    public File firstCompress(File file) {
        long j;
        int i;
        int i2;
        long j2;
        int i3;
        String absolutePath = file.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        sb.append(this.mCacheDir.getAbsolutePath());
        sb.append(File.separator);
        sb.append(TextUtils.isEmpty(this.filename) ? Long.valueOf(System.currentTimeMillis()) : this.filename);
        sb.append(".jpg");
        String sb2 = sb.toString();
        long length = file.length() / 5;
        int imageSpinAngle = getImageSpinAngle(absolutePath);
        int[] imageSize = getImageSize(absolutePath);
        int i4 = 0;
        if (imageSize[0] <= imageSize[1]) {
            double d = ((double) imageSize[0]) / ((double) imageSize[1]);
            if (d <= 1.0d && d > 0.5625d) {
                int i5 = imageSize[0] > 1280 ? 1280 : imageSize[0];
                length = (long) 60;
                i3 = (imageSize[1] * i5) / imageSize[0];
                i4 = i5;
            } else if (d <= 0.5625d) {
                int i6 = imageSize[1] > 720 ? 720 : imageSize[1];
                i4 = (imageSize[0] * i6) / imageSize[1];
                i3 = i6;
            } else {
                i3 = 0;
                j2 = 0;
                j = j2;
                i2 = i4;
                i = i3;
            }
            j2 = length;
            j = j2;
            i2 = i4;
            i = i3;
        } else {
            double d2 = ((double) imageSize[1]) / ((double) imageSize[0]);
            if (d2 <= 1.0d && d2 > 0.5625d) {
                i = 1280;
                if (imageSize[1] <= 1280) {
                    i = imageSize[1];
                }
                i2 = (imageSize[0] * i) / imageSize[1];
                j = (long) 60;
            } else if (d2 <= 0.5625d) {
                int i7 = 720;
                if (imageSize[0] <= 720) {
                    i7 = imageSize[0];
                }
                j = length;
                int i8 = i7;
                i = (imageSize[1] * i7) / imageSize[0];
                i2 = i8;
            } else {
                i2 = 0;
                i = 0;
                j = 0;
            }
        }
        return compress(absolutePath, sb2, i2, i, imageSpinAngle, j);
    }

    public int[] getImageSize(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(str, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    private Bitmap compress(String str, int i, int i2) {
        int i3;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i2 || i5 > i) {
            int i6 = i4 / 2;
            int i7 = i5 / 2;
            i3 = 1;
            while (i6 / i3 > i2 && i7 / i3 > i) {
                i3 *= 2;
            }
        } else {
            i3 = 1;
        }
        options.inSampleSize = i3;
        options.inJustDecodeBounds = false;
        int ceil = (int) Math.ceil((double) (((float) options.outHeight) / ((float) i2)));
        int ceil2 = (int) Math.ceil((double) (((float) options.outWidth) / ((float) i)));
        if (ceil > 1 || ceil2 > 1) {
            if (ceil > ceil2) {
                options.inSampleSize = ceil;
            } else {
                options.inSampleSize = ceil2;
            }
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    private int getImageSpinAngle(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                return 180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return 270;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private File compress(String str, String str2, int i, int i2, int i3, long j) {
        return saveImage(str2, rotatingImage(i3, compress(str, i, i2)), j);
    }

    private static Bitmap rotatingImage(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private File saveImage(String str, Bitmap bitmap, long j) {
        Preconditions.checkNotNull(bitmap, "smartcitybitmap cannot be null");
        File file = new File(str.substring(0, str.lastIndexOf("/")));
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (((long) (byteArrayOutputStream.toByteArray().length / 1024)) > j && i > 6) {
            byteArrayOutputStream.reset();
            i -= 6;
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(str);
    }
}
