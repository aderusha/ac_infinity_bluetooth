package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class zzcb extends zzl implements zzcc {
    public zzcb() {
        super("com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                zzi(parcel.readInt(), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 3:
                zzj(parcel.readInt(), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 4:
                zzb(parcel.readInt(), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 5:
                zzg(parcel.readInt(), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 6:
                zzl((Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 7:
                zzh(parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 8:
                zzf((Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 9:
                zzc((Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 10:
                zzm((Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 11:
                zzk((Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 12:
                zzd((Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 13:
                zze((Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
