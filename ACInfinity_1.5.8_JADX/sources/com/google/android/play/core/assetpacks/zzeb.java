package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.zza;
import com.google.android.play.core.internal.zzag;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzeb {
    private static final zzag zza = new zzag("PackMetadataManager");
    private final zzbh zzb;
    private final zzed zzc;
    private final zza zzd;

    zzeb(zzbh zzbh, zzed zzed, zza zza2) {
        this.zzb = zzbh;
        this.zzc = zzed;
        this.zzd = zza2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0053 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(java.lang.String r6) {
        /*
            r5 = this;
            com.google.android.play.core.common.zza r0 = r5.zzd
            java.lang.String r1 = "assetOnlyUpdates"
            boolean r0 = r0.zza(r1)
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x0061
            com.google.android.play.core.assetpacks.zzbh r0 = r5.zzb
            boolean r0 = r0.zzG(r6)
            if (r0 != 0) goto L_0x0015
            goto L_0x0061
        L_0x0015:
            com.google.android.play.core.assetpacks.zzed r0 = r5.zzc
            int r0 = r0.zza()
            com.google.android.play.core.assetpacks.zzbh r2 = r5.zzb
            long r3 = r2.zzc(r6)
            java.io.File r2 = r2.zzk(r6, r0, r3)
            boolean r3 = r2.exists()     // Catch:{ IOException -> 0x0054 }
            if (r3 != 0) goto L_0x0030
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x0054 }
            goto L_0x004e
        L_0x0030:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0054 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0054 }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ all -> 0x004f }
            r2.<init>()     // Catch:{ all -> 0x004f }
            r2.load(r3)     // Catch:{ all -> 0x004f }
            r3.close()     // Catch:{ IOException -> 0x0054 }
            java.lang.String r3 = "moduleVersionTag"
            java.lang.String r2 = r2.getProperty(r3)     // Catch:{ IOException -> 0x0054 }
            if (r2 != 0) goto L_0x004d
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x0054 }
            goto L_0x004e
        L_0x004d:
            r6 = r2
        L_0x004e:
            return r6
        L_0x004f:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x0053 }
        L_0x0053:
            throw r0     // Catch:{ IOException -> 0x0054 }
        L_0x0054:
            com.google.android.play.core.internal.zzag r0 = zza
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r6
            java.lang.String r6 = "Failed to read pack version tag for pack %s"
            r0.zzb(r6, r2)
        L_0x0061:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.zzeb.zza(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public final void zzb(String str, int i, long j, String str2) throws IOException {
        if (str2 == null || str2.isEmpty()) {
            str2 = String.valueOf(i);
        }
        Properties properties = new Properties();
        properties.put("moduleVersionTag", str2);
        File zzk = this.zzb.zzk(str, i, j);
        zzk.getParentFile().mkdirs();
        zzk.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(zzk);
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }
}
