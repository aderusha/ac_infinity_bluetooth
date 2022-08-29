package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.zzi;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzdm implements Runnable {
    public final /* synthetic */ zzdo zza;
    public final /* synthetic */ List zzb;
    public final /* synthetic */ zzbe zzc;
    public final /* synthetic */ zzi zzd;

    public /* synthetic */ zzdm(zzdo zzdo, List list, zzbe zzbe, zzi zzi) {
        this.zza = zzdo;
        this.zzb = list;
        this.zzc = zzbe;
        this.zzd = zzi;
    }

    public final void run() {
        this.zza.zzm(this.zzb, this.zzc, this.zzd);
    }
}
