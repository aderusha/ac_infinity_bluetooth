package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.play.core.tasks.zzi;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzan extends zzal {
    zzan(zzaw zzaw, zzi zzi) {
        super(zzaw, zzi);
    }

    public final void zze(Bundle bundle, Bundle bundle2) throws RemoteException {
        super.zze(bundle, bundle2);
        this.zza.zze((ParcelFileDescriptor) bundle.getParcelable("chunk_file_descriptor"));
    }
}
