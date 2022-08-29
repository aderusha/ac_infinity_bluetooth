package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzn extends zzk implements zzp {
    zzn(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.appupdate.protocol.IAppUpdateService");
    }

    public final void zzc(String str, Bundle bundle, zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzr);
        zzb(3, zza);
    }

    public final void zzd(String str, Bundle bundle, zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzr);
        zzb(2, zza);
    }
}
