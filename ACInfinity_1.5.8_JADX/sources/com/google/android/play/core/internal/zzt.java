package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class zzt extends zzl implements zzu {
    public static zzu zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
        return queryLocalInterface instanceof zzu ? (zzu) queryLocalInterface : new zzs(iBinder);
    }
}
