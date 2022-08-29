package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzay extends zzbb {
    zzay(zzbc zzbc, zzi zzi) {
        super(zzbc, zzi);
    }

    public final void zzg(int i, Bundle bundle) throws RemoteException {
        super.zzg(i, bundle);
        this.zza.zze(SplitInstallSessionState.zzd(bundle));
    }
}
