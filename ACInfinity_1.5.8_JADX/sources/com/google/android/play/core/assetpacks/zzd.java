package com.google.android.play.core.assetpacks;

import android.content.Context;
import com.google.android.play.core.internal.zzce;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzd {
    private static zza zza;

    static synchronized zza zza(Context context) {
        zza zza2;
        synchronized (zzd.class) {
            if (zza == null) {
                zzcb zzcb = new zzcb((zzca) null);
                zzcb.zzb(new zzp(zzce.zza(context)));
                zza = zzcb.zza();
            }
            zza2 = zza;
        }
        return zza2;
    }
}
