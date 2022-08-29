package com.google.android.play.core.tasks;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zze implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzf zzb;

    zze(zzf zzf, Task task) {
        this.zzb = zzf;
        this.zza = task;
    }

    public final void run() {
        synchronized (this.zzb.zzb) {
            zzf zzf = this.zzb;
            if (zzf.zzc != null) {
                zzf.zzc.onSuccess(this.zza.getResult());
            }
        }
    }
}
