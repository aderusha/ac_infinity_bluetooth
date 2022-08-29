package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.internal.zzcq;
import com.google.android.play.core.internal.zzcs;
import com.google.android.play.core.splitinstall.zzad;
import com.google.android.play.core.splitinstall.zzs;
import java.io.File;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzr implements zzcs {
    private final zzcs zza;
    private final zzcs zzb;
    private final zzcs zzc;
    private final zzcs zzd;

    public zzr(zzcs zzcs, zzcs zzcs2, zzcs zzcs3, zzcs zzcs4) {
        this.zza = zzcs;
        this.zzb = zzcs2;
        this.zzc = zzcs3;
        this.zzd = zzcs4;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        return new FakeSplitInstallManager(((zzad) this.zza).zzb(), (File) this.zzb.zza(), (zzs) this.zzc.zza(), zzcq.zzb(this.zzd));
    }
}
