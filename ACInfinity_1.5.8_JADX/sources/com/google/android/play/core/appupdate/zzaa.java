package com.google.android.play.core.appupdate;

import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzaa {
    private final zzaa zza = this;
    private final zzcs zzb;
    private final zzcs zzc;
    private final zzcs zzd;
    private final zzcs zze;
    private final zzcs zzf;
    private final zzcs zzg;

    /* synthetic */ zzaa(zzh zzh, zzz zzz) {
        zzj zzj = new zzj(zzh);
        this.zzb = zzj;
        zzcs zzc2 = zzcq.zzc(new zzt(zzj));
        this.zzc = zzc2;
        zzcs zzc3 = zzcq.zzc(new zzr(zzj, zzc2));
        this.zzd = zzc3;
        zzcs zzc4 = zzcq.zzc(new zzc(zzj));
        this.zze = zzc4;
        zzcs zzc5 = zzcq.zzc(new zzg(zzc3, zzc4, zzj));
        this.zzf = zzc5;
        this.zzg = zzcq.zzc(new zzi(zzc5));
    }

    public final AppUpdateManager zza() {
        return (AppUpdateManager) this.zzg.zza();
    }
}
