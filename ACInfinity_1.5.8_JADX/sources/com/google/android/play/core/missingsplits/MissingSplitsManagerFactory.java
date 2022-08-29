package com.google.android.play.core.missingsplits;

import android.content.Context;
import java.util.concurrent.atomic.AtomicReference;

@Deprecated
/* compiled from: com.google.android.play:core@@1.10.3 */
public final class MissingSplitsManagerFactory {
    private static final AtomicReference zza = new AtomicReference((Object) null);

    private MissingSplitsManagerFactory() {
    }

    @Deprecated
    public static MissingSplitsManager create(Context context) {
        return new zzb(context, Runtime.getRuntime(), new zza(context, context.getPackageManager()), zza);
    }
}
