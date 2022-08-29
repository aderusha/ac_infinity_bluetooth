package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcr;
import com.google.android.play.core.internal.zzcs;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzt implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;
    private final zzcs zzc;

    public zzt(zzcs zzcs, zzcs zzcs2, zzcs zzcs3) {
        this.zza = zzcs;
        this.zzb = zzcs2;
        this.zzc = zzcs3;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        zzy zzy;
        Context zzb2 = ((zzu) this.zza).zzb();
        zzco zzb3 = zzcq.zzb(this.zzb);
        zzco zzb4 = zzcq.zzb(this.zzc);
        if (zzp.zzb(zzb2) == null) {
            zzy = (zzy) zzb3.zza();
        } else {
            zzy = (zzy) zzb4.zza();
        }
        zzcr.zza(zzy);
        return zzy;
    }
}
