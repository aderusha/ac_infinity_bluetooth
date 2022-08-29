package com.google.android.play.core.appupdate;

import android.content.Context;
import java.io.File;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzs {
    private final Context zza;

    zzs(Context context) {
        this.zza = context;
    }

    private static long zzb(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        long j = 0;
        if (listFiles != null) {
            for (File zzb : listFiles) {
                j += zzb(zzb);
            }
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public final long zza() {
        return zzb(new File(this.zza.getFilesDir(), "assetpacks"));
    }
}
