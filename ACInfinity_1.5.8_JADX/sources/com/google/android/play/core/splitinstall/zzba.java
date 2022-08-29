package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzba extends zzbb {
    zzba(zzbc zzbc, zzi zzi) {
        super(zzbc, zzi);
    }

    public final void zzi(int i, Bundle bundle) throws RemoteException {
        super.zzi(i, bundle);
        this.zza.zze(Integer.valueOf(i));
    }
}
