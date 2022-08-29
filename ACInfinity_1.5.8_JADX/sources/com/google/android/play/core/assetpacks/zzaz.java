package com.google.android.play.core.assetpacks;

import android.os.Bundle;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzaz implements Runnable {
    public final /* synthetic */ zzbb zza;
    public final /* synthetic */ Bundle zzb;
    public final /* synthetic */ AssetPackState zzc;

    public /* synthetic */ zzaz(zzbb zzbb, Bundle bundle, AssetPackState assetPackState) {
        this.zza = zzbb;
        this.zzb = bundle;
        this.zzc = assetPackState;
    }

    public final void run() {
        this.zza.zzc(this.zzb, this.zzc);
    }
}
