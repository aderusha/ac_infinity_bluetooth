package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzau extends zzal {
    private final zzco zzc;
    private final zzeb zzd;
    private final zzbe zze;

    zzau(zzaw zzaw, zzi zzi, zzco zzco, zzeb zzeb, zzbe zzbe) {
        super(zzaw, zzi);
        this.zzc = zzco;
        this.zzd = zzeb;
        this.zze = zzbe;
    }

    public final void zzm(Bundle bundle, Bundle bundle2) {
        super.zzm(bundle, bundle2);
        this.zza.zze(AssetPackStates.zzb(bundle, this.zzc, this.zzd, this.zze));
    }
}
