package com.google.android.play.core.splitinstall;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.play:core@@1.10.3 */
public enum zzo implements zzg {
    INSTANCE;
    
    private static final AtomicReference zzb = null;

    static {
        zzb = new AtomicReference((Object) null);
    }

    public final zzh zza() {
        return (zzh) zzb.get();
    }

    public final void zzb(zzh zzh) {
        zzb.set(zzh);
    }
}
