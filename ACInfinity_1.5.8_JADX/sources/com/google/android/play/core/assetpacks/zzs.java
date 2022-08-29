package com.google.android.play.core.assetpacks;

import android.content.ComponentName;
import android.content.Context;
import com.google.android.play.core.internal.zzcd;
import com.google.android.play.core.internal.zzcr;
import com.google.android.play.core.internal.zzcs;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzs implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;

    public zzs(zzcs zzcs, zzcs zzcs2) {
        this.zza = zzcs;
        this.zzb = zzcs2;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        Object zza2 = this.zza.zza();
        Context zzb2 = ((zzu) this.zzb).zzb();
        zzl zzl = (zzl) zza2;
        zzcd.zza(zzb2.getPackageManager(), new ComponentName(zzb2.getPackageName(), "com.google.android.play.core.assetpacks.AssetPackExtractionService"), 4);
        zzcd.zza(zzb2.getPackageManager(), new ComponentName(zzb2.getPackageName(), "com.google.android.play.core.assetpacks.ExtractionForegroundService"), 4);
        zzcr.zza(zzl);
        return zzl;
    }
}
