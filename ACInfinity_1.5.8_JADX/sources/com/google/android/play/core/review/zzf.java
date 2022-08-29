package com.google.android.play.core.review;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.common.PlayCoreVersion;
import com.google.android.play.core.internal.zzac;
import com.google.android.play.core.internal.zzah;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzf extends zzah {
    final /* synthetic */ zzi zza;
    final /* synthetic */ zzi zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(zzi zzi, zzi zzi2, zzi zzi3) {
        super(zzi2);
        this.zzb = zzi;
        this.zza = zzi3;
    }

    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            String zzc = this.zzb.zzc;
            Bundle zza2 = PlayCoreVersion.zza("review");
            zzi zzi = this.zzb;
            ((zzac) this.zzb.zza.zze()).zzc(zzc, zza2, new zzh(zzi, this.zza, zzi.zzc));
        } catch (RemoteException e) {
            zzi.zzb.zzc(e, "error requesting in-app review for %s", this.zzb.zzc);
            this.zza.zzd(new RuntimeException(e));
        }
    }
}
