package com.google.android.play.core.tasks;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zza implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzb zzb;

    zza(zzb zzb2, Task task) {
        this.zzb = zzb2;
        this.zza = task;
    }

    public final void run() {
        synchronized (this.zzb.zzb) {
            zzb zzb2 = this.zzb;
            if (zzb2.zzc != null) {
                zzb2.zzc.onComplete(this.zza);
            }
        }
    }
}
