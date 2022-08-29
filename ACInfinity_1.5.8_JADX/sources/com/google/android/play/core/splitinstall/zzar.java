package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzca;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzar extends zzah {
    final /* synthetic */ zzi zza;
    final /* synthetic */ zzbc zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzar(zzbc zzbc, zzi zzi, zzi zzi2) {
        super(zzi);
        this.zzb = zzbc;
        this.zza = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            zzbc zzbc = this.zzb;
            ((zzca) this.zzb.zza.zze()).zzi(zzbc.zzd, new zzaz(zzbc, this.zza));
        } catch (RemoteException e) {
            zzbc.zzb.zzc(e, "getSessionStates", new Object[0]);
            this.zza.zzd(new RuntimeException(e));
        }
    }
}
