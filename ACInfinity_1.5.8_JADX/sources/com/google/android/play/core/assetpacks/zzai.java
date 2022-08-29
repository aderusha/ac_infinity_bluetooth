package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzu;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzai extends zzah {
    final /* synthetic */ int zza;
    final /* synthetic */ zzi zzb;
    final /* synthetic */ zzaw zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzai(zzaw zzaw, zzi zzi, int i, zzi zzi2) {
        super(zzi);
        this.zzc = zzaw;
        this.zza = i;
        this.zzb = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzu) this.zzc.zzf.zze()).zzi(this.zzc.zzc, zzaw.zzB(this.zza), zzaw.zzA(), new zzas(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzaw.zza.zzc(e, "notifySessionFailed", new Object[0]);
        }
    }
}
