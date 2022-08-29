package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class zzv extends zzl implements zzw {
    public zzv() {
        super("com.google.android.play.core.assetpacks.protocol.IAssetModuleServiceCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                zzn(parcel.readInt(), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 3:
                zzb(parcel.readInt(), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 4:
                zzf(parcel.readInt(), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 5:
                zzg(parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 6:
                zzi((Bundle) zzm.zza(parcel, Bundle.CREATOR), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 7:
                zzd((Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 8:
                zzj((Bundle) zzm.zza(parcel, Bundle.CREATOR), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 10:
                zzk((Bundle) zzm.zza(parcel, Bundle.CREATOR), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 11:
                zzh((Bundle) zzm.zza(parcel, Bundle.CREATOR), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 12:
                zze((Bundle) zzm.zza(parcel, Bundle.CREATOR), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 13:
                zzm((Bundle) zzm.zza(parcel, Bundle.CREATOR), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 14:
                zzl((Bundle) zzm.zza(parcel, Bundle.CREATOR), (Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            case 15:
                zzc((Bundle) zzm.zza(parcel, Bundle.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
