package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzf implements zzg {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Object zzb = new Object();
    /* access modifiers changed from: private */
    public final OnSuccessListener zzc;

    public zzf(Executor executor, OnSuccessListener onSuccessListener) {
        this.zza = executor;
        this.zzc = onSuccessListener;
    }

    public final void zzc(Task task) {
        if (task.isSuccessful()) {
            synchronized (this.zzb) {
                if (this.zzc != null) {
                    this.zza.execute(new zze(this, task));
                }
            }
        }
    }
}
