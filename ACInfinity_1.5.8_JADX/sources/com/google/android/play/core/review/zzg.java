package com.google.android.play.core.review;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzad;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzas;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
class zzg extends zzad {
    final zzag zza;
    final zzi zzb;
    final /* synthetic */ zzi zzc;

    zzg(zzi zzi, zzag zzag, zzi zzi2) {
        this.zzc = zzi;
        this.zza = zzag;
        this.zzb = zzi2;
    }

    public void zzb(Bundle bundle) throws RemoteException {
        zzas zzas = this.zzc.zza;
        if (zzas != null) {
            zzas.zzs(this.zzb);
        }
        this.zza.zzd("onGetLaunchReviewFlowInfo", new Object[0]);
    }
}
