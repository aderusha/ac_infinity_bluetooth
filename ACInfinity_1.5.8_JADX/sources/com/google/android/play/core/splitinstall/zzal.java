package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzca;
import com.google.android.play.core.tasks.zzi;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzal extends zzah {
    final /* synthetic */ Collection zza;
    final /* synthetic */ Collection zzb;
    final /* synthetic */ zzi zzc;
    final /* synthetic */ zzbc zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzal(zzbc zzbc, zzi zzi, Collection collection, Collection collection2, zzi zzi2) {
        super(zzi);
        this.zzd = zzbc;
        this.zza = collection;
        this.zzb = collection2;
        this.zzc = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        ArrayList zzm = zzbc.zzm(this.zza);
        zzm.addAll(zzbc.zzl(this.zzb));
        try {
            ((zzca) this.zzd.zza.zze()).zzj(this.zzd.zzd, zzm, zzbc.zza(), new zzba(this.zzd, this.zzc));
        } catch (RemoteException e) {
            zzbc.zzb.zzc(e, "startInstall(%s,%s)", this.zza, this.zzb);
            this.zzc.zzd(new RuntimeException(e));
        }
    }
}
