package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzdf implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;
    private final zzcs zzc;
    private final zzcs zzd;

    public zzdf(zzcs zzcs, zzcs zzcs2, zzcs zzcs3, zzcs zzcs4) {
        this.zza = zzcs;
        this.zzb = zzcs2;
        this.zzc = zzcs3;
        this.zzd = zzcs4;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        Object zza2 = this.zza.zza();
        return new zzde((zzbh) zza2, zzcq.zzb(this.zzb), (zzco) this.zzc.zza(), zzcq.zzb(this.zzd));
    }
}
