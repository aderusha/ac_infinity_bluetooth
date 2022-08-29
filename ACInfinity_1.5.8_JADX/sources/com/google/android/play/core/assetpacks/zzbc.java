package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzbc implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;
    private final zzcs zzc;
    private final zzcs zzd;
    private final zzcs zze;
    private final zzcs zzf;
    private final zzcs zzg;
    private final zzcs zzh;
    private final zzcs zzi;

    public zzbc(zzcs zzcs, zzcs zzcs2, zzcs zzcs3, zzcs zzcs4, zzcs zzcs5, zzcs zzcs6, zzcs zzcs7, zzcs zzcs8, zzcs zzcs9) {
        this.zza = zzcs;
        this.zzb = zzcs2;
        this.zzc = zzcs3;
        this.zzd = zzcs4;
        this.zze = zzcs5;
        this.zzf = zzcs6;
        this.zzg = zzcs7;
        this.zzh = zzcs8;
        this.zzi = zzcs9;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        Context zzb2 = ((zzu) this.zza).zzb();
        Object zza2 = this.zzb.zza();
        Object zza3 = this.zzc.zza();
        zzco zzb3 = zzcq.zzb(this.zzd);
        Object zza4 = this.zze.zza();
        Object zza5 = this.zzf.zza();
        return new zzbb(zzb2, (zzde) zza2, (zzcl) zza3, zzb3, (zzco) zza4, (zzbx) zza5, zzcq.zzb(this.zzg), zzcq.zzb(this.zzh), (zzeb) this.zzi.zza());
    }
}
