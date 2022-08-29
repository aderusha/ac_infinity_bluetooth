package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzu;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzah extends com.google.android.play.core.internal.zzah {
    final /* synthetic */ int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzi zzc;
    final /* synthetic */ int zzd;
    final /* synthetic */ zzaw zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzah(zzaw zzaw, zzi zzi, int i, String str, zzi zzi2, int i2) {
        super(zzi);
        this.zze = zzaw;
        this.zza = i;
        this.zzb = str;
        this.zzc = zzi2;
        this.zzd = i2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzu) this.zze.zzf.zze()).zzh(this.zze.zzc, zzaw.zzz(this.zza, this.zzb), zzaw.zzA(), new zzar(this.zze, this.zzc, this.zza, this.zzb, this.zzd));
        } catch (RemoteException e) {
            zzaw.zza.zzc(e, "notifyModuleCompleted", new Object[0]);
        }
    }
}
