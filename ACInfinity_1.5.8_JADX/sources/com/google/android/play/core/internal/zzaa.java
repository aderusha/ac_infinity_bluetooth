package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzaa extends zzk implements zzac {
    zzaa(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.inappreview.protocol.IInAppReviewService");
    }

    public final void zzc(String str, Bundle bundle, zzae zzae) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzae);
        zzb(2, zza);
    }
}
