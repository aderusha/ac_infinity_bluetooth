package com.google.android.play.core.assetpacks;

import com.google.android.play.core.common.zza;
import com.google.android.play.core.internal.zzco;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzdz {
    private final zzbh zza;
    private final zzco zzb;
    private final zzde zzc;
    private final zzco zzd;
    private final zzco zze;
    private final zza zzf;
    private final zzeb zzg;

    zzdz(zzbh zzbh, zzco zzco, zzde zzde, zzco zzco2, zzco zzco3, zza zza2, zzeb zzeb) {
        this.zza = zzbh;
        this.zzb = zzco;
        this.zzc = zzde;
        this.zzd = zzco2;
        this.zze = zzco3;
        this.zzf = zza2;
        this.zzg = zzeb;
    }

    public final void zza(zzdw zzdw) {
        File zzj = this.zza.zzj(zzdw.zzl, zzdw.zza, zzdw.zzb);
        File zzl = this.zza.zzl(zzdw.zzl, zzdw.zza, zzdw.zzb);
        if (!zzj.exists() || !zzl.exists()) {
            throw new zzck(String.format("Cannot find pack files to move for pack %s.", new Object[]{zzdw.zzl}), zzdw.zzk);
        }
        File zzh = this.zza.zzh(zzdw.zzl, zzdw.zza, zzdw.zzb);
        zzh.mkdirs();
        if (zzj.renameTo(zzh)) {
            new File(this.zza.zzh(zzdw.zzl, zzdw.zza, zzdw.zzb), "merge.tmp").delete();
            File zzi = this.zza.zzi(zzdw.zzl, zzdw.zza, zzdw.zzb);
            zzi.mkdirs();
            if (zzl.renameTo(zzi)) {
                if (this.zzf.zza("assetOnlyUpdates")) {
                    try {
                        this.zzg.zzb(zzdw.zzl, zzdw.zza, zzdw.zzb, zzdw.zzc);
                        ((Executor) this.zzd.zza()).execute(new zzdy(this, zzdw));
                    } catch (IOException e) {
                        throw new zzck(String.format("Could not write asset pack version tag for pack %s: %s", new Object[]{zzdw.zzl, e.getMessage()}), zzdw.zzk);
                    }
                } else {
                    zzbh zzbh = this.zza;
                    zzbh.getClass();
                    ((Executor) this.zzd.zza()).execute(new zzdx(zzbh));
                }
                this.zzc.zzk(zzdw.zzl, zzdw.zza, zzdw.zzb);
                this.zze.zzc(zzdw.zzl);
                ((zzy) this.zzb.zza()).zzh(zzdw.zzk, zzdw.zzl);
                return;
            }
            throw new zzck("Cannot move metadata files to final location.", zzdw.zzk);
        }
        throw new zzck("Cannot move merged pack files to final location.", zzdw.zzk);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzdw zzdw) {
        this.zza.zzB(zzdw.zzl, zzdw.zza, zzdw.zzb);
    }
}
