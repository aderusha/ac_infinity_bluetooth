package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzao extends zzah {
    final /* synthetic */ IBinder zza;
    final /* synthetic */ zzar zzb;

    zzao(zzar zzar, IBinder iBinder) {
        this.zzb = zzar;
        this.zza = iBinder;
    }

    public final void zza() {
        zzas zzas = this.zzb.zza;
        zzas.zzo = (IInterface) zzas.zzj.zza(this.zza);
        zzas.zzo(this.zzb.zza);
        this.zzb.zza.zzh = false;
        for (Runnable run : this.zzb.zza.zze) {
            run.run();
        }
        this.zzb.zza.zze.clear();
    }
}
