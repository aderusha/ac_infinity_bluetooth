package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzca;
import com.google.android.play.core.tasks.zzi;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzan extends zzah {
    final /* synthetic */ List zza;
    final /* synthetic */ zzi zzb;
    final /* synthetic */ zzbc zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzan(zzbc zzbc, zzi zzi, List list, zzi zzi2) {
        super(zzi);
        this.zzc = zzbc;
        this.zza = list;
        this.zzb = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzca) this.zzc.zza.zze()).zzd(this.zzc.zzd, zzbc.zzm(this.zza), zzbc.zza(), new zzau(this.zzc, this.zzb));
        } catch (RemoteException e) {
            zzbc.zzb.zzc(e, "deferredInstall(%s)", this.zza);
            this.zzb.zzd(new RuntimeException(e));
        }
    }
}
