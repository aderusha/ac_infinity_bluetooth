package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import com.google.android.play.core.tasks.zzi;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzav extends zzal {
    private final List zzc;
    private final zzco zzd;
    private final zzeb zze;

    zzav(zzaw zzaw, zzi zzi, zzco zzco, zzeb zzeb, List list) {
        super(zzaw, zzi);
        this.zzd = zzco;
        this.zze = zzeb;
        this.zzc = list;
    }

    public final void zzn(int i, Bundle bundle) {
        super.zzn(i, bundle);
        this.zza.zze(AssetPackStates.zzc(bundle, this.zzd, this.zze, this.zzc));
    }
}
