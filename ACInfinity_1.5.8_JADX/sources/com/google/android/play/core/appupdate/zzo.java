package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzo extends zzn {
    zzo(zzq zzq, zzi zzi) {
        super(zzq, new zzag("OnCompleteUpdateCallback"), zzi);
    }

    public final void zzb(Bundle bundle) throws RemoteException {
        super.zzb(bundle);
        if (bundle.getInt("error.code", -2) != 0) {
            this.zzb.zzd(new InstallException(bundle.getInt("error.code", -2)));
        } else {
            this.zzb.zze((Object) null);
        }
    }
}
