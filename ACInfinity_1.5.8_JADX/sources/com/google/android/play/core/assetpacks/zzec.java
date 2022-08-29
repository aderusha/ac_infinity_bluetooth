package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.zza;
import com.google.android.play.core.internal.zzcs;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzec implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;
    private final zzcs zzc;

    public zzec(zzcs zzcs, zzcs zzcs2, zzcs zzcs3) {
        this.zza = zzcs;
        this.zzb = zzcs2;
        this.zzc = zzcs3;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        Object zza2 = this.zza.zza();
        return new zzeb((zzbh) zza2, (zzed) this.zzb.zza(), (zza) this.zzc.zza());
    }
}
