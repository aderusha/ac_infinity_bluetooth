package com.google.android.play.core.internal;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzal extends zzah {
    final /* synthetic */ zzas zza;

    zzal(zzas zzas) {
        this.zza = zzas;
    }

    public final void zza() {
        zzas zzas = this.zza;
        if (zzas.zzo != null) {
            zzas.zzc.zzd("Unbind from service.", new Object[0]);
            zzas zzas2 = this.zza;
            zzas2.zzb.unbindService(zzas2.zzn);
            this.zza.zzh = false;
            this.zza.zzo = null;
            this.zza.zzn = null;
        }
        this.zza.zzu();
    }
}
