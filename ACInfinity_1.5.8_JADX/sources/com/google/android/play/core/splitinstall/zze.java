package com.google.android.play.core.splitinstall;

import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;
import com.google.android.play.core.splitinstall.testing.zzr;
import java.io.File;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zze implements zzp {
    private final zze zza = this;
    private final zzcs zzb;
    private final zzcs zzc;
    private final zzcs zzd;
    private final zzcs zze;
    private final zzcs zzf;
    private final zzcs zzg;
    private final zzcs zzh;
    private final zzcs zzi;
    private final zzcs zzj;
    private final zzcs zzk;
    private final zzcs zzl;

    /* synthetic */ zze(zzac zzac, zzd zzd2) {
        zzad zzad = new zzad(zzac);
        this.zzb = zzad;
        zzcs zzc2 = zzcq.zzc(new zzbd(zzad));
        this.zzc = zzc2;
        zzcs zzc3 = zzcq.zzc(new zzag(zzac));
        this.zzd = zzc3;
        zzcs zzc4 = zzcq.zzc(new zzt(zzad));
        this.zze = zzc4;
        zzcs zzc5 = zzcq.zzc(new zzbf(zzad));
        this.zzf = zzc5;
        zzcs zzc6 = zzcq.zzc(new zzab(zzc2, zzc3, zzc4, zzc5));
        this.zzg = zzc6;
        zzcs zzc7 = zzcq.zzc(new zzaf(zzad));
        this.zzh = zzc7;
        zzae zzae = new zzae(zzc7);
        this.zzi = zzae;
        zzcs zzc8 = zzcq.zzc(new zzr(zzad, zzc7, zzc4, zzae));
        this.zzj = zzc8;
        zzcs zzc9 = zzcq.zzc(new zzm(zzc6, zzc8, zzc7));
        this.zzk = zzc9;
        this.zzl = zzcq.zzc(new zzah(zzac, zzc9));
    }

    public final SplitInstallManager zza() {
        return (SplitInstallManager) this.zzl.zza();
    }

    public final File zzb() {
        return (File) this.zzh.zza();
    }
}
