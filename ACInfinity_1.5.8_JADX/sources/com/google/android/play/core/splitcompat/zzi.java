package com.google.android.play.core.splitcompat;

import java.util.Set;
import java.util.zip.ZipFile;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzi implements zzk {
    final /* synthetic */ Set zza;
    final /* synthetic */ zzs zzb;
    final /* synthetic */ ZipFile zzc;

    zzi(zzm zzm, Set set, zzs zzs, ZipFile zipFile) {
        this.zza = set;
        this.zzb = zzs;
        this.zzc = zipFile;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0074 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.play.core.splitcompat.zzl r4, java.io.File r5, boolean r6) throws java.io.IOException {
        /*
            r3 = this;
            java.util.Set r0 = r3.zza
            r0.add(r5)
            if (r6 != 0) goto L_0x007c
            r6 = 5
            java.lang.Object[] r6 = new java.lang.Object[r6]
            com.google.android.play.core.splitcompat.zzs r0 = r3.zzb
            java.lang.String r0 = r0.zzb()
            r1 = 0
            r6[r1] = r0
            r0 = 1
            java.lang.String r2 = r4.zza
            r6[r0] = r2
            r0 = 2
            com.google.android.play.core.splitcompat.zzs r2 = r3.zzb
            java.io.File r2 = r2.zza()
            java.lang.String r2 = r2.getAbsolutePath()
            r6[r0] = r2
            r0 = 3
            java.util.zip.ZipEntry r2 = r4.zzb
            java.lang.String r2 = r2.getName()
            r6[r0] = r2
            r0 = 4
            java.lang.String r2 = r5.getAbsolutePath()
            r6[r0] = r2
            java.lang.String r0 = "NativeLibraryExtractor: split '%s' has native library '%s' that does not exist; extracting from '%s!%s' to '%s'"
            java.lang.String r6 = java.lang.String.format(r0, r6)
            java.lang.String r0 = "SplitCompat"
            android.util.Log.i(r0, r6)
            java.util.zip.ZipFile r6 = r3.zzc
            java.util.zip.ZipEntry r4 = r4.zzb
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]
            boolean r2 = r5.exists()
            if (r2 == 0) goto L_0x0051
            r5.delete()
        L_0x0051:
            java.io.InputStream r4 = r6.getInputStream(r4)
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ all -> 0x0075 }
            r6.<init>(r5)     // Catch:{ all -> 0x0075 }
            com.google.android.play.core.splitcompat.zze.zzm(r5)     // Catch:{ all -> 0x0070 }
        L_0x005d:
            int r5 = r4.read(r0)     // Catch:{ all -> 0x0070 }
            if (r5 <= 0) goto L_0x0067
            r6.write(r0, r1, r5)     // Catch:{ all -> 0x0070 }
            goto L_0x005d
        L_0x0067:
            r6.close()     // Catch:{ all -> 0x0075 }
            if (r4 == 0) goto L_0x007c
            r4.close()
            return
        L_0x0070:
            r5 = move-exception
            r6.close()     // Catch:{ all -> 0x0074 }
        L_0x0074:
            throw r5     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r5 = move-exception
            if (r4 == 0) goto L_0x007b
            r4.close()     // Catch:{ all -> 0x007b }
        L_0x007b:
            throw r5
        L_0x007c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitcompat.zzi.zza(com.google.android.play.core.splitcompat.zzl, java.io.File, boolean):void");
    }
}
