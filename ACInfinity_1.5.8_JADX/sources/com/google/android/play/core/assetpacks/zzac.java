package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzu;
import com.google.android.play.core.tasks.zzi;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzac extends zzah {
    final /* synthetic */ List zza;
    final /* synthetic */ Map zzb;
    final /* synthetic */ zzi zzc;
    final /* synthetic */ List zzd;
    final /* synthetic */ zzaw zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzac(zzaw zzaw, zzi zzi, List list, Map map, zzi zzi2, List list2) {
        super(zzi);
        this.zze = zzaw;
        this.zza = list;
        this.zzb = map;
        this.zzc = zzi2;
        this.zzd = list2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        ArrayList zzv = zzaw.zzv(this.zza);
        try {
            String zzu = this.zze.zzc;
            Bundle zzn = zzaw.zzn(this.zzb);
            zzaw zzaw = this.zze;
            ((zzu) this.zze.zzf.zze()).zzl(zzu, zzv, zzn, new zzav(zzaw, this.zzc, zzaw.zzd, zzaw.zze, this.zzd));
        } catch (RemoteException e) {
            zzaw.zza.zzc(e, "startDownload(%s)", this.zza);
            this.zzc.zzd(new RuntimeException(e));
        }
    }
}
