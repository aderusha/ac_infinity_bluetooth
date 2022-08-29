package com.google.android.play.core.splitcompat;

import java.io.IOException;
import java.util.Set;
import java.util.zip.ZipFile;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzh implements zzj {
    final /* synthetic */ Set zza;
    final /* synthetic */ zzs zzb;
    final /* synthetic */ zzm zzc;

    zzh(zzm zzm, Set set, zzs zzs) {
        this.zzc = zzm;
        this.zza = set;
        this.zzb = zzs;
    }

    public final void zza(ZipFile zipFile, Set set) throws IOException {
        this.zza.addAll(zzm.zza(this.zzc, set, this.zzb, zipFile));
    }
}
