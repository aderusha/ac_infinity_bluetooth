package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzp extends zzn {
    final /* synthetic */ zzq zzd;
    private final String zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzp(zzq zzq, zzi zzi, String str) {
        super(zzq, new zzag("OnRequestInstallCallback"), zzi);
        this.zzd = zzq;
        this.zze = str;
    }

    public final void zzc(Bundle bundle) throws RemoteException {
        super.zzc(bundle);
        if (bundle.getInt("error.code", -2) != 0) {
            this.zzb.zzd(new InstallException(bundle.getInt("error.code", -2)));
        } else {
            this.zzb.zze(zzq.zzd(this.zzd, bundle, this.zze));
        }
    }
}
