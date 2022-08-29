package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzs extends zzk implements zzu {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
    }

    public final void zzc(String str, List list, Bundle bundle, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzw);
        zzb(14, zza);
    }

    public final void zzd(String str, Bundle bundle, Bundle bundle2, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzb(zza, bundle2);
        zzm.zzc(zza, zzw);
        zzb(11, zza);
    }

    public final void zze(String str, Bundle bundle, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzw);
        zzb(5, zza);
    }

    public final void zzf(String str, Bundle bundle, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzw);
        zzb(10, zza);
    }

    public final void zzg(String str, Bundle bundle, Bundle bundle2, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzb(zza, bundle2);
        zzm.zzc(zza, zzw);
        zzb(6, zza);
    }

    public final void zzh(String str, Bundle bundle, Bundle bundle2, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzb(zza, bundle2);
        zzm.zzc(zza, zzw);
        zzb(7, zza);
    }

    public final void zzi(String str, Bundle bundle, Bundle bundle2, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzb(zza, bundle2);
        zzm.zzc(zza, zzw);
        zzb(9, zza);
    }

    public final void zzj(String str, Bundle bundle, Bundle bundle2, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzm.zzb(zza, bundle);
        zzm.zzb(zza, bundle2);
        zzm.zzc(zza, zzw);
        zzb(13, zza);
    }

    public final void zzk(String str, List list, Bundle bundle, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzw);
        zzb(12, zza);
    }

    public final void zzl(String str, List list, Bundle bundle, zzw zzw) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeTypedList(list);
        zzm.zzb(zza, bundle);
        zzm.zzc(zza, zzw);
        zzb(2, zza);
    }
}
