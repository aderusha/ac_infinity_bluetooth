package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzu;
import com.google.android.play.core.tasks.zzi;
import java.util.Map;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzae extends zzah {
    final /* synthetic */ Map zza;
    final /* synthetic */ zzi zzb;
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzae(zzaw zzaw, zzi zzi, Map map, zzi zzi2) {
        super(zzi);
        this.zzc = zzaw;
        this.zza = map;
        this.zzb = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzu) this.zzc.zzf.zze()).zze(this.zzc.zzc, zzaw.zzn(this.zza), new zzao(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzaw.zza.zzc(e, "syncPacks", new Object[0]);
            this.zzb.zzd(new RuntimeException(e));
        }
    }
}
