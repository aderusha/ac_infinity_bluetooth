package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.play:core@@1.10.3 */
public interface zzca extends IInterface {
    void zzc(String str, int i, Bundle bundle, zzcc zzcc) throws RemoteException;

    void zzd(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException;

    void zze(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException;

    void zzf(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException;

    void zzg(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException;

    void zzh(String str, int i, zzcc zzcc) throws RemoteException;

    void zzi(String str, zzcc zzcc) throws RemoteException;

    void zzj(String str, List list, Bundle bundle, zzcc zzcc) throws RemoteException;
}
