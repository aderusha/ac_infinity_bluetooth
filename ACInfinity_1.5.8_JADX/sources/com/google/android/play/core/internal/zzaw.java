package com.google.android.play.core.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitcompat.zze;
import com.google.android.play.core.splitcompat.zzr;
import com.google.android.play.core.splitinstall.zzf;
import com.google.android.play.core.splitinstall.zzh;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzaw implements zzh {
    private final Context zza;
    private final zze zzb;
    /* access modifiers changed from: private */
    public final zzay zzc;
    private final Executor zzd;
    private final zzr zze;

    public zzaw(Context context, Executor executor, zzay zzay, zze zze2, zzr zzr, byte[] bArr) {
        this.zza = context;
        this.zzb = zze2;
        this.zzc = zzay;
        this.zzd = executor;
        this.zze = zzr;
    }

    static /* bridge */ /* synthetic */ void zzb(zzaw zzaw, List list, zzf zzf) {
        Integer zze2 = zzaw.zze(list);
        if (zze2 != null) {
            if (zze2.intValue() == 0) {
                zzf.zzc();
            } else {
                zzf.zzb(zze2.intValue());
            }
        }
    }

    static /* bridge */ /* synthetic */ void zzc(zzaw zzaw, zzf zzf) {
        try {
            if (!SplitCompat.zzd(zzce.zza(zzaw.zza))) {
                Log.e("SplitCompat", "Emulating splits failed.");
                zzf.zzb(-12);
                return;
            }
            Log.i("SplitCompat", "Splits installed.");
            zzf.zza();
        } catch (Exception e) {
            Log.e("SplitCompat", "Error emulating splits.", e);
            zzf.zzb(-12);
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x00a1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00a6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:78:0x0121 */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0124 A[Catch:{ all -> 0x001b, all -> 0x0121, Exception -> 0x0128 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Integer zze(java.util.List r15) {
        /*
            r14 = this;
            java.lang.String r0 = "SplitCompat"
            r1 = -13
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0128 }
            com.google.android.play.core.splitcompat.zze r3 = r14.zzb     // Catch:{ Exception -> 0x0128 }
            java.io.File r3 = r3.zzd()     // Catch:{ Exception -> 0x0128 }
            java.lang.String r4 = "rw"
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0128 }
            java.nio.channels.FileChannel r2 = r2.getChannel()     // Catch:{ Exception -> 0x0128 }
            r3 = 0
            java.nio.channels.FileLock r4 = r2.tryLock()     // Catch:{ OverlappingFileLockException -> 0x001e }
            goto L_0x001f
        L_0x001b:
            r15 = move-exception
            goto L_0x011c
        L_0x001e:
            r4 = r3
        L_0x001f:
            if (r4 == 0) goto L_0x0122
            r3 = 0
            r5 = -11
            java.lang.String r6 = "Copying splits."
            android.util.Log.i(r0, r6)     // Catch:{ Exception -> 0x010c }
            java.util.Iterator r15 = r15.iterator()     // Catch:{ Exception -> 0x010c }
        L_0x002d:
            boolean r6 = r15.hasNext()     // Catch:{ Exception -> 0x010c }
            if (r6 == 0) goto L_0x00a7
            java.lang.Object r6 = r15.next()     // Catch:{ Exception -> 0x010c }
            android.content.Intent r6 = (android.content.Intent) r6     // Catch:{ Exception -> 0x010c }
            java.lang.String r7 = "split_id"
            java.lang.String r7 = r6.getStringExtra(r7)     // Catch:{ Exception -> 0x010c }
            android.content.Context r8 = r14.zza     // Catch:{ Exception -> 0x010c }
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ Exception -> 0x010c }
            android.net.Uri r6 = r6.getData()     // Catch:{ Exception -> 0x010c }
            java.lang.String r9 = "r"
            android.content.res.AssetFileDescriptor r6 = r8.openAssetFileDescriptor(r6, r9)     // Catch:{ Exception -> 0x010c }
            com.google.android.play.core.splitcompat.zze r8 = r14.zzb     // Catch:{ Exception -> 0x010c }
            java.io.File r8 = r8.zze(r7)     // Catch:{ Exception -> 0x010c }
            boolean r9 = r8.exists()     // Catch:{ Exception -> 0x010c }
            if (r9 == 0) goto L_0x0068
            long r9 = r8.length()     // Catch:{ Exception -> 0x010c }
            long r11 = r6.getLength()     // Catch:{ Exception -> 0x010c }
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x0068
            goto L_0x006e
        L_0x0068:
            boolean r9 = r8.exists()     // Catch:{ Exception -> 0x010c }
            if (r9 != 0) goto L_0x002d
        L_0x006e:
            com.google.android.play.core.splitcompat.zze r9 = r14.zzb     // Catch:{ Exception -> 0x010c }
            java.io.File r7 = r9.zzg(r7)     // Catch:{ Exception -> 0x010c }
            boolean r7 = r7.exists()     // Catch:{ Exception -> 0x010c }
            if (r7 != 0) goto L_0x002d
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x010c }
            java.io.FileInputStream r6 = r6.createInputStream()     // Catch:{ Exception -> 0x010c }
            r7.<init>(r6)     // Catch:{ Exception -> 0x010c }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ all -> 0x00a2 }
            r6.<init>(r8)     // Catch:{ all -> 0x00a2 }
            r8 = 4096(0x1000, float:5.74E-42)
            byte[] r8 = new byte[r8]     // Catch:{ all -> 0x009d }
        L_0x008c:
            int r9 = r7.read(r8)     // Catch:{ all -> 0x009d }
            if (r9 <= 0) goto L_0x0096
            r6.write(r8, r3, r9)     // Catch:{ all -> 0x009d }
            goto L_0x008c
        L_0x0096:
            r6.close()     // Catch:{ all -> 0x00a2 }
            r7.close()     // Catch:{ Exception -> 0x010c }
            goto L_0x002d
        L_0x009d:
            r15 = move-exception
            r6.close()     // Catch:{ all -> 0x00a1 }
        L_0x00a1:
            throw r15     // Catch:{ all -> 0x00a2 }
        L_0x00a2:
            r15 = move-exception
            r7.close()     // Catch:{ all -> 0x00a6 }
        L_0x00a6:
            throw r15     // Catch:{ Exception -> 0x010c }
        L_0x00a7:
            java.lang.String r15 = "Splits copied."
            android.util.Log.i(r0, r15)     // Catch:{ Exception -> 0x010c }
            com.google.android.play.core.splitcompat.zze r15 = r14.zzb     // Catch:{ IOException -> 0x0105 }
            java.io.File r15 = r15.zzb()     // Catch:{ IOException -> 0x0105 }
            java.io.File[] r15 = r15.listFiles()     // Catch:{ IOException -> 0x0105 }
            com.google.android.play.core.internal.zzay r6 = r14.zzc     // Catch:{ Exception -> 0x00fc }
            boolean r6 = r6.zzc(r15)     // Catch:{ Exception -> 0x00fc }
            if (r6 == 0) goto L_0x00f6
            com.google.android.play.core.internal.zzay r6 = r14.zzc     // Catch:{ Exception -> 0x00fc }
            boolean r15 = r6.zza(r15)     // Catch:{ Exception -> 0x00fc }
            if (r15 == 0) goto L_0x00f6
            com.google.android.play.core.splitcompat.zze r15 = r14.zzb     // Catch:{ IOException -> 0x00ef }
            java.io.File r15 = r15.zzb()     // Catch:{ IOException -> 0x00ef }
            java.io.File[] r15 = r15.listFiles()     // Catch:{ IOException -> 0x00ef }
            java.util.Arrays.sort(r15)     // Catch:{ IOException -> 0x00ef }
            int r5 = r15.length     // Catch:{ IOException -> 0x00ef }
        L_0x00d4:
            int r5 = r5 + -1
            if (r5 < 0) goto L_0x00e9
            r6 = r15[r5]     // Catch:{ IOException -> 0x00ef }
            com.google.android.play.core.splitcompat.zze.zzm(r6)     // Catch:{ IOException -> 0x00ef }
            r6 = r15[r5]     // Catch:{ IOException -> 0x00ef }
            com.google.android.play.core.splitcompat.zze r7 = r14.zzb     // Catch:{ IOException -> 0x00ef }
            java.io.File r7 = r7.zzf(r6)     // Catch:{ IOException -> 0x00ef }
            r6.renameTo(r7)     // Catch:{ IOException -> 0x00ef }
            goto L_0x00d4
        L_0x00e9:
            java.lang.String r15 = "Splits verified."
            android.util.Log.i(r0, r15)     // Catch:{ all -> 0x001b }
            goto L_0x0114
        L_0x00ef:
            r15 = move-exception
            java.lang.String r3 = "Cannot write verified split."
            android.util.Log.e(r0, r3, r15)     // Catch:{ all -> 0x001b }
            goto L_0x0112
        L_0x00f6:
            java.lang.String r15 = "Split verification failed."
            android.util.Log.e(r0, r15)     // Catch:{ all -> 0x001b }
            goto L_0x0102
        L_0x00fc:
            r15 = move-exception
            java.lang.String r3 = "Error verifying splits."
            android.util.Log.e(r0, r3, r15)     // Catch:{ all -> 0x001b }
        L_0x0102:
            r3 = -11
            goto L_0x0114
        L_0x0105:
            r15 = move-exception
            java.lang.String r3 = "Cannot access directory for unverified splits."
            android.util.Log.e(r0, r3, r15)     // Catch:{ all -> 0x001b }
            goto L_0x0112
        L_0x010c:
            r15 = move-exception
            java.lang.String r3 = "Error copying splits."
            android.util.Log.e(r0, r3, r15)     // Catch:{ all -> 0x001b }
        L_0x0112:
            r3 = -13
        L_0x0114:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x001b }
            r4.release()     // Catch:{ all -> 0x001b }
            goto L_0x0122
        L_0x011c:
            if (r2 == 0) goto L_0x0121
            r2.close()     // Catch:{ all -> 0x0121 }
        L_0x0121:
            throw r15     // Catch:{ Exception -> 0x0128 }
        L_0x0122:
            if (r2 == 0) goto L_0x0127
            r2.close()     // Catch:{ Exception -> 0x0128 }
        L_0x0127:
            return r3
        L_0x0128:
            r15 = move-exception
            java.lang.String r2 = "Error locking files."
            android.util.Log.e(r0, r2, r15)
            java.lang.Integer r15 = java.lang.Integer.valueOf(r1)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.internal.zzaw.zze(java.util.List):java.lang.Integer");
    }

    public final void zzd(List list, zzf zzf) {
        if (SplitCompat.zze()) {
            this.zzd.execute(new zzav(this, list, zzf));
            return;
        }
        throw new IllegalStateException("Ingestion should only be called in SplitCompat mode.");
    }
}
