package com.google.android.play.core.tasks;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzc implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzd zzb;

    zzc(zzd zzd, Task task) {
        this.zzb = zzd;
        this.zza = task;
    }

    public final void run() {
        synchronized (this.zzb.zzb) {
            zzd zzd = this.zzb;
            if (zzd.zzc != null) {
                zzd.zzc.onFailure(this.zza.getException());
            }
        }
    }
}
