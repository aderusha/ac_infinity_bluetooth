package com.eternal.framework.http.interceptor.CacheInterceptor.Catch;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheManager {
    private static final String CACHE_DIR = "responses";
    private static final int DISK_CACHE_INDEX = 0;
    private static final long DISK_CACHE_SIZE = 10485760;
    public static final String TAG = "CacheManager";
    private static volatile CacheManager mCacheManager;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private DiskLruCache mDiskLruCache;

    public static CacheManager getInstance(Context context) {
        if (mCacheManager == null) {
            synchronized (CacheManager.class) {
                if (mCacheManager == null) {
                    mCacheManager = new CacheManager(context);
                }
            }
        }
        return mCacheManager;
    }

    public void delete(Context context) throws Exception {
        File diskCacheDir = getDiskCacheDir(context, CACHE_DIR);
        if (this.mDiskLruCache != null) {
            DiskLruCache.deleteContents(diskCacheDir);
        }
    }

    private CacheManager(Context context) {
        File diskCacheDir = getDiskCacheDir(context, CACHE_DIR);
        if (!diskCacheDir.exists()) {
            boolean mkdirs = diskCacheDir.mkdirs();
            Log.d(TAG, "!diskCacheDir.exists() --- diskCacheDir.mkdirs()=" + mkdirs);
        }
        if (diskCacheDir.getUsableSpace() > DISK_CACHE_SIZE) {
            try {
                this.mDiskLruCache = DiskLruCache.open(diskCacheDir, getAppVersion(context), 1, DISK_CACHE_SIZE);
                Log.d(TAG, "mDiskLruCache created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void putCache(String str, String str2) {
        DiskLruCache diskLruCache = this.mDiskLruCache;
        if (diskLruCache != null) {
            OutputStream outputStream = null;
            try {
                DiskLruCache.Editor edit = diskLruCache.edit(encryptMD5(str));
                if (edit != null) {
                    outputStream = edit.newOutputStream(0);
                    outputStream.write(str2.getBytes());
                    outputStream.flush();
                    edit.commit();
                }
                this.mDiskLruCache.flush();
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Throwable th) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }

    public void setCache(final String str, final String str2) {
        this.cachedThreadPool.submit(new Runnable() {
            public void run() {
                CacheManager.this.putCache(str, str2);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x005a A[SYNTHETIC, Splitter:B:38:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0064 A[SYNTHETIC, Splitter:B:43:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0071 A[SYNTHETIC, Splitter:B:51:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x007b A[SYNTHETIC, Splitter:B:56:0x007b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCache(java.lang.String r7) {
        /*
            r6 = this;
            com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache r0 = r6.mDiskLruCache
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.String r7 = encryptMD5(r7)     // Catch:{ IOException -> 0x0052, all -> 0x004f }
            com.eternal.framework.http.interceptor.CacheInterceptor.Catch.DiskLruCache$Snapshot r7 = r0.get(r7)     // Catch:{ IOException -> 0x0052, all -> 0x004f }
            if (r7 == 0) goto L_0x006c
            r0 = 0
            java.io.InputStream r7 = r7.getInputStream(r0)     // Catch:{ IOException -> 0x0052, all -> 0x004f }
            java.io.FileInputStream r7 = (java.io.FileInputStream) r7     // Catch:{ IOException -> 0x0052, all -> 0x004f }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            r2.<init>()     // Catch:{ IOException -> 0x004c, all -> 0x0049 }
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x0047 }
        L_0x0020:
            int r4 = r7.read(r3)     // Catch:{ IOException -> 0x0047 }
            r5 = -1
            if (r4 == r5) goto L_0x002b
            r2.write(r3, r0, r4)     // Catch:{ IOException -> 0x0047 }
            goto L_0x0020
        L_0x002b:
            byte[] r0 = r2.toByteArray()     // Catch:{ IOException -> 0x0047 }
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x0047 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0047 }
            if (r7 == 0) goto L_0x003e
            r7.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r7 = move-exception
            r7.printStackTrace()
        L_0x003e:
            r2.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0046:
            return r3
        L_0x0047:
            r0 = move-exception
            goto L_0x0055
        L_0x0049:
            r0 = move-exception
            r2 = r1
            goto L_0x006e
        L_0x004c:
            r0 = move-exception
            r2 = r1
            goto L_0x0055
        L_0x004f:
            r0 = move-exception
            r2 = r1
            goto L_0x006f
        L_0x0052:
            r0 = move-exception
            r7 = r1
            r2 = r7
        L_0x0055:
            r0.printStackTrace()     // Catch:{ all -> 0x006d }
            if (r7 == 0) goto L_0x0062
            r7.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0062
        L_0x005e:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0062:
            if (r2 == 0) goto L_0x006c
            r2.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x006c
        L_0x0068:
            r7 = move-exception
            r7.printStackTrace()
        L_0x006c:
            return r1
        L_0x006d:
            r0 = move-exception
        L_0x006e:
            r1 = r7
        L_0x006f:
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ IOException -> 0x0075 }
            goto L_0x0079
        L_0x0075:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0079:
            if (r2 == 0) goto L_0x0083
            r2.close()     // Catch:{ IOException -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0083:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.http.interceptor.CacheInterceptor.Catch.CacheManager.getCache(java.lang.String):java.lang.String");
    }

    public void getCache(final String str, final CacheCallback cacheCallback) {
        this.cachedThreadPool.submit(new Runnable() {
            public void run() {
                cacheCallback.onGetCache(CacheManager.this.getCache(str));
            }
        });
    }

    public boolean removeCache(String str) {
        DiskLruCache diskLruCache = this.mDiskLruCache;
        if (diskLruCache == null) {
            return false;
        }
        try {
            return diskLruCache.remove(encryptMD5(str));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private File getDiskCacheDir(Context context, String str) {
        String path = context.getCacheDir().getPath();
        return new File(path + File.separator + str);
    }

    public static String encryptMD5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes(Key.STRING_CHARSET_NAME));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                byte b2 = b & 255;
                if (b2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b2));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    private int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo == null) {
                return 0;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
