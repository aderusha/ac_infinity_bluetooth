package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.zzcs;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzbd implements zzcs {
    private final zzcs zza;

    public zzbd(zzcs zzcs) {
        this.zza = zzcs;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        Context zzb = ((zzad) this.zza).zzb();
        return new zzbc(zzb, zzb.getPackageName());
    }
}
