package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzu;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzak extends zzah {
    final /* synthetic */ zzi zza;
    final /* synthetic */ zzaw zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzak(zzaw zzaw, zzi zzi, zzi zzi2) {
        super(zzi);
        this.zzb = zzaw;
        this.zza = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzu) this.zzb.zzg.zze()).zzf(this.zzb.zzc, zzaw.zzA(), new zzap(this.zzb, this.zza));
        } catch (RemoteException e) {
            zzaw.zza.zzc(e, "keepAlive", new Object[0]);
        }
    }
}
