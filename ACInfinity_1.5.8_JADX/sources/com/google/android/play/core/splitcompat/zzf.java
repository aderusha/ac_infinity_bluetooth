package com.google.android.play.core.splitcompat;

import java.io.File;
import java.io.IOException;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzf implements zzk {
    final /* synthetic */ zzg zza;

    zzf(zzg zzg) {
        this.zza = zzg;
    }

    public final void zza(zzl zzl, File file, boolean z) throws IOException {
        this.zza.zzb.add(file);
        if (!z) {
            this.zza.zzc.set(false);
        }
    }
}
