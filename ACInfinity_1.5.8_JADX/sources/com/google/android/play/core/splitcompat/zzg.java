package com.google.android.play.core.splitcompat;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipFile;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzg implements zzj {
    final /* synthetic */ zzs zza;
    final /* synthetic */ Set zzb;
    final /* synthetic */ AtomicBoolean zzc;
    final /* synthetic */ zzm zzd;

    zzg(zzm zzm, zzs zzs, Set set, AtomicBoolean atomicBoolean) {
        this.zzd = zzm;
        this.zza = zzs;
        this.zzb = set;
        this.zzc = atomicBoolean;
    }

    public final void zza(ZipFile zipFile, Set set) throws IOException {
        this.zzd.zzf(this.zza, set, new zzf(this));
    }
}
