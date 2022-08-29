package com.google.android.play.core.internal;

import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class zzah implements Runnable {
    private final zzi zza;

    zzah() {
        this.zza = null;
    }

    public zzah(zzi zzi) {
        this.zza = zzi;
    }

    public final void run() {
        try {
            zza();
        } catch (Exception e) {
            zzc(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza();

    /* access modifiers changed from: package-private */
    public final zzi zzb() {
        return this.zza;
    }

    public final void zzc(Exception exc) {
        zzi zzi = this.zza;
        if (zzi != null) {
            zzi.zzd(exc);
        }
    }
}
