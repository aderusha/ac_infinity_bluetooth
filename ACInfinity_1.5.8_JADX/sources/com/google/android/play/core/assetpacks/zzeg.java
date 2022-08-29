package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzco;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzeg {
    private static final zzag zza = new zzag("PatchSliceTaskHandler");
    private final zzbh zzb;
    private final zzco zzc;

    zzeg(zzbh zzbh, zzco zzco) {
        this.zzb = zzbh;
        this.zzc = zzco;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00ab */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.play.core.assetpacks.zzef r15) {
        /*
            r14 = this;
            com.google.android.play.core.assetpacks.zzbh r0 = r14.zzb
            java.lang.String r1 = r15.zzl
            int r2 = r15.zza
            long r3 = r15.zzb
            java.io.File r0 = r0.zzh(r1, r2, r3)
            com.google.android.play.core.assetpacks.zzbh r1 = r14.zzb
            java.lang.String r2 = r15.zzl
            int r3 = r15.zza
            long r4 = r15.zzb
            java.lang.String r6 = r15.zzf
            java.io.File r7 = new java.io.File
            java.io.File r1 = r1.zzi(r2, r3, r4)
            r7.<init>(r1, r6)
            r1 = 2
            r2 = 1
            r3 = 0
            java.io.InputStream r4 = r15.zzh     // Catch:{ IOException -> 0x00ac }
            int r5 = r15.zze     // Catch:{ IOException -> 0x00ac }
            if (r5 == r1) goto L_0x0029
            goto L_0x0031
        L_0x0029:
            java.util.zip.GZIPInputStream r5 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x00ac }
            r6 = 8192(0x2000, float:1.14794E-41)
            r5.<init>(r4, r6)     // Catch:{ IOException -> 0x00ac }
            r4 = r5
        L_0x0031:
            com.google.android.play.core.assetpacks.zzbk r5 = new com.google.android.play.core.assetpacks.zzbk     // Catch:{ all -> 0x00a7 }
            r5.<init>(r0, r7)     // Catch:{ all -> 0x00a7 }
            com.google.android.play.core.assetpacks.zzbh r8 = r14.zzb     // Catch:{ all -> 0x00a7 }
            java.lang.String r9 = r15.zzl     // Catch:{ all -> 0x00a7 }
            int r10 = r15.zzc     // Catch:{ all -> 0x00a7 }
            long r11 = r15.zzd     // Catch:{ all -> 0x00a7 }
            java.lang.String r13 = r15.zzf     // Catch:{ all -> 0x00a7 }
            java.io.File r0 = r8.zzp(r9, r10, r11, r13)     // Catch:{ all -> 0x00a7 }
            boolean r6 = r0.exists()     // Catch:{ all -> 0x00a7 }
            if (r6 != 0) goto L_0x004d
            r0.mkdirs()     // Catch:{ all -> 0x00a7 }
        L_0x004d:
            com.google.android.play.core.assetpacks.zzen r13 = new com.google.android.play.core.assetpacks.zzen     // Catch:{ all -> 0x00a7 }
            com.google.android.play.core.assetpacks.zzbh r7 = r14.zzb     // Catch:{ all -> 0x00a7 }
            java.lang.String r8 = r15.zzl     // Catch:{ all -> 0x00a7 }
            int r9 = r15.zzc     // Catch:{ all -> 0x00a7 }
            long r10 = r15.zzd     // Catch:{ all -> 0x00a7 }
            java.lang.String r12 = r15.zzf     // Catch:{ all -> 0x00a7 }
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r12)     // Catch:{ all -> 0x00a7 }
            com.google.android.play.core.assetpacks.zzcn r6 = new com.google.android.play.core.assetpacks.zzcn     // Catch:{ all -> 0x00a7 }
            r6.<init>(r0, r13)     // Catch:{ all -> 0x00a7 }
            long r7 = r15.zzg     // Catch:{ all -> 0x00a7 }
            com.google.android.play.core.internal.zzcl.zza(r5, r4, r6, r7)     // Catch:{ all -> 0x00a7 }
            r13.zzi(r3)     // Catch:{ all -> 0x00a7 }
            r4.close()     // Catch:{ IOException -> 0x00ac }
            com.google.android.play.core.internal.zzag r0 = zza
            java.lang.Object[] r4 = new java.lang.Object[r1]
            java.lang.String r5 = r15.zzf
            r4[r3] = r5
            java.lang.String r5 = r15.zzl
            r4[r2] = r5
            java.lang.String r5 = "Patching and extraction finished for slice %s of pack %s."
            r0.zzd(r5, r4)
            com.google.android.play.core.internal.zzco r0 = r14.zzc
            java.lang.Object r0 = r0.zza()
            com.google.android.play.core.assetpacks.zzy r0 = (com.google.android.play.core.assetpacks.zzy) r0
            int r4 = r15.zzk
            java.lang.String r5 = r15.zzl
            java.lang.String r6 = r15.zzf
            r0.zzg(r4, r5, r6, r3)
            java.io.InputStream r0 = r15.zzh     // Catch:{ IOException -> 0x0095 }
            r0.close()     // Catch:{ IOException -> 0x0095 }
            return
        L_0x0095:
            com.google.android.play.core.internal.zzag r0 = zza
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r4 = r15.zzf
            r1[r3] = r4
            java.lang.String r15 = r15.zzl
            r1[r2] = r15
            java.lang.String r15 = "Could not close file for slice %s of pack %s."
            r0.zze(r15, r1)
            return
        L_0x00a7:
            r0 = move-exception
            r4.close()     // Catch:{ all -> 0x00ab }
        L_0x00ab:
            throw r0     // Catch:{ IOException -> 0x00ac }
        L_0x00ac:
            r0 = move-exception
            com.google.android.play.core.internal.zzag r4 = zza
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = r0.getMessage()
            r5[r3] = r6
            java.lang.String r6 = "IOException during patching %s."
            r4.zzb(r6, r5)
            com.google.android.play.core.assetpacks.zzck r4 = new com.google.android.play.core.assetpacks.zzck
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r5 = r15.zzf
            r1[r3] = r5
            java.lang.String r3 = r15.zzl
            r1[r2] = r3
            java.lang.String r2 = "Error patching slice %s of pack %s."
            java.lang.String r1 = java.lang.String.format(r2, r1)
            int r15 = r15.zzk
            r4.<init>(r1, r0, r15)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.zzeg.zza(com.google.android.play.core.assetpacks.zzef):void");
    }
}
