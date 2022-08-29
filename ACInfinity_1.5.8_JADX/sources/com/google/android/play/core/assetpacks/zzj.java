package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzj implements Runnable {
    public final /* synthetic */ zzl zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzi zzc;

    public /* synthetic */ zzj(zzl zzl, String str, zzi zzi) {
        this.zza = zzl;
        this.zzb = str;
        this.zzc = zzi;
    }

    public final void run() {
        this.zza.zzd(this.zzb, this.zzc);
    }
}
