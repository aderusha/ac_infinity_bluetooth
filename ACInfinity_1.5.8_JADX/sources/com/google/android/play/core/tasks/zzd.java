package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzd implements zzg {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Object zzb = new Object();
    /* access modifiers changed from: private */
    public final OnFailureListener zzc;

    public zzd(Executor executor, OnFailureListener onFailureListener) {
        this.zza = executor;
        this.zzc = onFailureListener;
    }

    public final void zzc(Task task) {
        if (!task.isSuccessful()) {
            synchronized (this.zzb) {
                if (this.zzc != null) {
                    this.zza.execute(new zzc(this, task));
                }
            }
        }
    }
}
