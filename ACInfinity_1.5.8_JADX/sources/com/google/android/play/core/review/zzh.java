package com.google.android.play.core.review;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzh extends zzg {
    final String zzd;

    zzh(zzi zzi, zzi zzi2, String str) {
        super(zzi, new zzag("OnRequestInstallCallback"), zzi2);
        this.zzd = str;
    }

    public final void zzb(Bundle bundle) throws RemoteException {
        super.zzb(bundle);
        this.zzb.zze(new zza((PendingIntent) bundle.get("confirmation_intent"), bundle.getBoolean("is_review_no_op")));
    }
}
