package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.zzi;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final /* synthetic */ class zzdn implements Runnable {
    public final /* synthetic */ zzdo zza;
    public final /* synthetic */ List zzb;
    public final /* synthetic */ zzi zzc;
    public final /* synthetic */ List zzd;

    public /* synthetic */ zzdn(zzdo zzdo, List list, zzi zzi, List list2) {
        this.zza = zzdo;
        this.zzb = list;
        this.zzc = zzi;
        this.zzd = list2;
    }

    public final void run() {
        this.zza.zzo(this.zzb, this.zzc, this.zzd);
    }
}
