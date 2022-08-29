package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzq;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
class zzn extends zzq {
    final zzag zza;
    final zzi zzb;
    final /* synthetic */ zzq zzc;

    zzn(zzq zzq, zzag zzag, zzi zzi) {
        this.zzc = zzq;
        this.zza = zzag;
        this.zzb = zzi;
    }

    public void zzb(Bundle bundle) throws RemoteException {
        this.zzc.zza.zzs(this.zzb);
        this.zza.zzd("onCompleteUpdate", new Object[0]);
    }

    public void zzc(Bundle bundle) throws RemoteException {
        this.zzc.zza.zzs(this.zzb);
        this.zza.zzd("onRequestInfo", new Object[0]);
    }
}
