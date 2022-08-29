package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.internal.zzce;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zza {
    private static zzaa zza;

    static synchronized zzaa zza(Context context) {
        zzaa zzaa;
        synchronized (zza.class) {
            if (zza == null) {
                zzy zzy = new zzy((zzx) null);
                zzy.zza(new zzh(zzce.zza(context)));
                zza = zzy.zzb();
            }
            zzaa = zza;
        }
        return zzaa;
    }
}
