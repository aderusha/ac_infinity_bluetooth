package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzp;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzm extends zzah {
    final /* synthetic */ zzi zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzq zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzm(zzq zzq, zzi zzi, zzi zzi2, String str) {
        super(zzi);
        this.zzc = zzq;
        this.zza = zzi2;
        this.zzb = str;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ((zzp) this.zzc.zza.zze()).zzc(this.zzc.zzd, zzq.zzi(), new zzo(this.zzc, this.zza));
        } catch (RemoteException e) {
            zzq.zzb.zzc(e, "completeUpdate(%s)", this.zzb);
            this.zza.zzd(new RuntimeException(e));
        }
    }
}
