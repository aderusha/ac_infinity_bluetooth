package com.google.android.play.core.splitinstall;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzr {
    private static final AtomicReference zza = new AtomicReference((Object) null);

    static zzq zza() {
        return (zzq) zza.get();
    }

    public static void zzb(zzq zzq) {
        zza.compareAndSet((Object) null, zzq);
    }
}
