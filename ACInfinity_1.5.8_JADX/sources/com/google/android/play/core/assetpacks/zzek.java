package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.zzco;
import java.io.File;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzek {
    private final zzbh zza;
    private final zzco zzb;
    private final zzde zzc;
    private final zzco zzd;
    private final zzco zze;

    zzek(zzbh zzbh, zzco zzco, zzde zzde, zzco zzco2, zzco zzco3) {
        this.zza = zzbh;
        this.zzb = zzco;
        this.zzc = zzde;
        this.zzd = zzco2;
        this.zze = zzco3;
    }

    public final void zza(zzei zzei) {
        File zzh = this.zza.zzh(zzei.zzl, zzei.zza, zzei.zzc);
        if (zzh.exists()) {
            File zzh2 = this.zza.zzh(zzei.zzl, zzei.zzb, zzei.zzc);
            zzh2.mkdirs();
            if (zzh.renameTo(zzh2)) {
                ((Executor) this.zzd.zza()).execute(new zzej(this, zzei));
                this.zzc.zzk(zzei.zzl, zzei.zzb, zzei.zzc);
                this.zze.zzc(zzei.zzl);
                ((zzy) this.zzb.zza()).zzh(zzei.zzk, zzei.zzl);
                return;
            }
            throw new zzck(String.format("Cannot promote pack %s from %s to %s", new Object[]{zzei.zzl, zzh.getAbsolutePath(), zzh2.getAbsolutePath()}), zzei.zzk);
        }
        throw new zzck(String.format("Cannot find pack files to promote for pack %s at %s", new Object[]{zzei.zzl, zzh.getAbsolutePath()}), zzei.zzk);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzei zzei) {
        this.zza.zzB(zzei.zzl, zzei.zzb, zzei.zzc);
    }
}
