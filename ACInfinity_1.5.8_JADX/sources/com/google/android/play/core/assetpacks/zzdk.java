package com.google.android.play.core.assetpacks;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzdk implements Runnable {
    public final /* synthetic */ zzdo zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ String zzc;

    public /* synthetic */ zzdk(zzdo zzdo, int i, String str) {
        this.zza = zzdo;
        this.zzb = i;
        this.zzc = str;
    }

    public final void run() {
        this.zza.zzn(this.zzb, this.zzc);
    }
}
