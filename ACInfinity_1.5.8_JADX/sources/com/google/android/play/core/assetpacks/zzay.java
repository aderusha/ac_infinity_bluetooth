package com.google.android.play.core.assetpacks;

import android.os.Bundle;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzay implements Runnable {
    public final /* synthetic */ zzbb zza;
    public final /* synthetic */ Bundle zzb;

    public /* synthetic */ zzay(zzbb zzbb, Bundle bundle) {
        this.zza = zzbb;
        this.zzb = bundle;
    }

    public final void run() {
        this.zza.zzb(this.zzb);
    }
}
