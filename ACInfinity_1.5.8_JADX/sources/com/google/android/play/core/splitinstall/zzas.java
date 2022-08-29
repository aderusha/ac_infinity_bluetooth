package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzca;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzas extends zzah {
    final /* synthetic */ int zza;
    final /* synthetic */ zzi zzb;
    final /* synthetic */ zzbc zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzas(zzbc zzbc, zzi zzi, int i, zzi zzi2) {
        super(zzi);
        this.zzc = zzbc;
        this.zza = i;
        this.zzb = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzca) this.zzc.zza.zze()).zzc(this.zzc.zzd, this.zza, zzbc.zza(), new zzat(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzbc.zzb.zzc(e, "cancelInstall(%d)", Integer.valueOf(this.zza));
            this.zzb.zzd(new RuntimeException(e));
        }
    }
}
