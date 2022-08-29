package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzby extends zzk implements zzca {
    zzby(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.splitinstall.protocol.ISplitInstallService");
    }

    public final void zzc(String str, int i, Bundle bundle, zzcc zzcc) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeInt(i);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzcc);
        zzb(4, zza);
    }

    public final void zzd(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzcc);
        zzb(8, zza);
    }

    public final void zze(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzcc);
        zzb(13, zza);
    }

    public final void zzf(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzcc);
        zzb(14, zza);
    }

    public final void zzg(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzcc);
        zzb(7, zza);
    }

    public final void zzh(String str, int i, zzcc zzcc) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeInt(i);
        zzm.zzc(zza, zzcc);
        zzb(5, zza);
    }

    public final void zzi(String str, zzcc zzcc) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzc(zza, zzcc);
        zzb(6, zza);
    }

    public final void zzj(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzcc);
        zzb(2, zza);
    }
}
