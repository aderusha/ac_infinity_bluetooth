package com.google.android.play.core.assetpacks;

import android.content.Intent;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzdl implements Runnable {
    public final /* synthetic */ zzdo zza;
    public final /* synthetic */ Intent zzb;

    public /* synthetic */ zzdl(zzdo zzdo, Intent intent) {
        this.zza = zzdo;
        this.zzb = intent;
    }

    public final void run() {
        this.zza.zzl(this.zzb);
    }
}
