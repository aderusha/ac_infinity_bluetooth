package com.google.android.play.core.splitinstall;

import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzm implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;
    private final zzcs zzc;

    public zzm(zzcs zzcs, zzcs zzcs2, zzcs zzcs3) {
        this.zza = zzcs;
        this.zzb = zzcs2;
        this.zzc = zzcs3;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        return new zzl(zzcq.zzb(this.zza), zzcq.zzb(this.zzb), zzcq.zzb(this.zzc));
    }
}
