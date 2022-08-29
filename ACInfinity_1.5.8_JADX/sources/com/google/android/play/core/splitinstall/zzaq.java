package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzca;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzaq extends zzah {
    final /* synthetic */ int zza;
    final /* synthetic */ zzi zzb;
    final /* synthetic */ zzbc zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaq(zzbc zzbc, zzi zzi, int i, zzi zzi2) {
        super(zzi);
        this.zzc = zzbc;
        this.zza = i;
        this.zzb = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            zzbc zzbc = this.zzc;
            ((zzca) this.zzc.zza.zze()).zzh(zzbc.zzd, this.zza, new zzay(zzbc, this.zzb));
        } catch (RemoteException e) {
            zzbc.zzb.zzc(e, "getSessionState(%d)", Integer.valueOf(this.zza));
            this.zzb.zzd(new RuntimeException(e));
        }
    }
}
