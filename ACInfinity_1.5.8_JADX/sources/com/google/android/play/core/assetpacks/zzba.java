package com.google.android.play.core.assetpacks;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzba implements Runnable {
    public final /* synthetic */ zzbb zza;
    public final /* synthetic */ AssetPackState zzb;

    public /* synthetic */ zzba(zzbb zzbb, AssetPackState assetPackState) {
        this.zza = zzbb;
        this.zzb = assetPackState;
    }

    public final void run() {
        this.zza.zzi(this.zzb);
    }
}
