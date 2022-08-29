package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzb implements zzg {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Object zzb = new Object();
    /* access modifiers changed from: private */
    public final OnCompleteListener zzc;

    public zzb(Executor executor, OnCompleteListener onCompleteListener) {
        this.zza = executor;
        this.zzc = onCompleteListener;
    }

    public final void zzc(Task task) {
        synchronized (this.zzb) {
            if (this.zzc != null) {
                this.zza.execute(new zza(this, task));
            }
        }
    }
}
