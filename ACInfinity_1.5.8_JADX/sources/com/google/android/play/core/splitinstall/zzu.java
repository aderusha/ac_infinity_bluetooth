package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.zzce;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzu {
    private static zzp zza;

    public static synchronized zzp zza(Context context) {
        zzp zzp;
        synchronized (zzu.class) {
            if (zza == null) {
                zzc zzc = new zzc((zzb) null);
                zzc.zza(new zzac(zzce.zza(context)));
                zza = zzc.zzb();
            }
            zzp = zza;
        }
        return zzp;
    }
}
