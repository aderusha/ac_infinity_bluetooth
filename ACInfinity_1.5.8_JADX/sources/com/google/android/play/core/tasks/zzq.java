package com.google.android.play.core.tasks;

import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzq implements zzp {
    private final Object zza = new Object();
    private final int zzb;
    private final zzm zzc;
    private int zzd;
    private int zze;
    private Exception zzf;

    public zzq(int i, zzm zzm) {
        this.zzb = i;
        this.zzc = zzm;
    }

    private final void zza() {
        if (this.zzd + this.zze != this.zzb) {
            return;
        }
        if (this.zzf != null) {
            zzm zzm = this.zzc;
            int i = this.zze;
            int i2 = this.zzb;
            StringBuilder sb = new StringBuilder(54);
            sb.append(i);
            sb.append(" out of ");
            sb.append(i2);
            sb.append(" underlying tasks failed");
            zzm.zza(new ExecutionException(sb.toString(), this.zzf));
            return;
        }
        this.zzc.zzb((Object) null);
    }

    public final void onFailure(Exception exc) {
        synchronized (this.zza) {
            this.zze++;
            this.zzf = exc;
            zza();
        }
    }

    public final void onSuccess(Object obj) {
        synchronized (this.zza) {
            this.zzd++;
            zza();
        }
    }
}
