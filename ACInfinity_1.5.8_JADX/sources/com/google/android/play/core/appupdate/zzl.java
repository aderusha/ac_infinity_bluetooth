package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.internal.zzp;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzl extends zzah {
    final /* synthetic */ String zza;
    final /* synthetic */ zzi zzb;
    final /* synthetic */ zzq zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzl(zzq zzq, zzi zzi, String str, zzi zzi2) {
        super(zzi);
        this.zzc = zzq;
        this.zza = str;
        this.zzb = zzi2;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            zzq zzq = this.zzc;
            ((zzp) this.zzc.zza.zze()).zzd(zzq.zzd, zzq.zzb(zzq, this.zza), new zzp(this.zzc, this.zzb, this.zza));
        } catch (RemoteException e) {
            zzq.zzb.zzc(e, "requestUpdateInfo(%s)", this.zza);
            this.zzb.zzd(new RuntimeException(e));
        }
    }
}
