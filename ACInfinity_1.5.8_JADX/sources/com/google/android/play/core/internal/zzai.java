package com.google.android.play.core.internal;

import android.os.IBinder;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzai implements IBinder.DeathRecipient {
    public final /* synthetic */ zzas zza;

    public /* synthetic */ zzai(zzas zzas) {
        this.zza = zzas;
    }

    public final void binderDied() {
        zzas.zzi(this.zza);
    }
}
