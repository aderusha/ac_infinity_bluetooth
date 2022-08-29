package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzco;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzcl {
    private static final zzag zza = new zzag("ExtractorLooper");
    private final zzde zzb;
    private final zzcf zzc;
    private final zzer zzd;
    private final zzdu zze;
    private final zzdz zzf;
    private final zzeg zzg;
    private final zzek zzh;
    private final zzco zzi;
    private final zzdh zzj;
    private final AtomicBoolean zzk = new AtomicBoolean(false);

    zzcl(zzde zzde, zzco zzco, zzcf zzcf, zzer zzer, zzdu zzdu, zzdz zzdz, zzeg zzeg, zzek zzek, zzdh zzdh) {
        this.zzb = zzde;
        this.zzi = zzco;
        this.zzc = zzcf;
        this.zzd = zzer;
        this.zze = zzdu;
        this.zzf = zzdz;
        this.zzg = zzeg;
        this.zzh = zzek;
        this.zzj = zzdh;
    }

    private final void zzb(int i, Exception exc) {
        try {
            this.zzb.zzm(i, 5);
            this.zzb.zzn(i);
        } catch (zzck unused) {
            zza.zzb("Error during error handling: %s", exc.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        zzag zzag = zza;
        zzag.zza("Run extractor loop", new Object[0]);
        if (this.zzk.compareAndSet(false, true)) {
            while (true) {
                zzdg zzdg = null;
                try {
                    zzdg = this.zzj.zza();
                } catch (zzck e) {
                    zza.zzb("Error while getting next extraction task: %s", e.getMessage());
                    if (e.zza >= 0) {
                        ((zzy) this.zzi.zza()).zzi(e.zza);
                        zzb(e.zza, e);
                    }
                }
                if (zzdg != null) {
                    try {
                        if (zzdg instanceof zzce) {
                            this.zzc.zza((zzce) zzdg);
                        } else if (zzdg instanceof zzeq) {
                            this.zzd.zza((zzeq) zzdg);
                        } else if (zzdg instanceof zzdt) {
                            this.zze.zza((zzdt) zzdg);
                        } else if (zzdg instanceof zzdw) {
                            this.zzf.zza((zzdw) zzdg);
                        } else if (zzdg instanceof zzef) {
                            this.zzg.zza((zzef) zzdg);
                        } else if (zzdg instanceof zzei) {
                            this.zzh.zza((zzei) zzdg);
                        } else {
                            zza.zzb("Unknown task type: %s", zzdg.getClass().getName());
                        }
                    } catch (Exception e2) {
                        zza.zzb("Error during extraction task: %s", e2.getMessage());
                        ((zzy) this.zzi.zza()).zzi(zzdg.zzk);
                        zzb(zzdg.zzk, e2);
                    }
                } else {
                    this.zzk.set(false);
                    return;
                }
            }
        } else {
            zzag.zze("runLoop already looping; return", new Object[0]);
        }
    }
}
