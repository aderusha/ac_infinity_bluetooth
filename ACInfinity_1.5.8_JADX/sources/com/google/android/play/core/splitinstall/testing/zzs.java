package com.google.android.play.core.splitinstall.testing;

import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.play:core@@1.10.3 */
abstract class zzs {
    zzs() {
    }

    /* access modifiers changed from: package-private */
    public abstract zzs zza(int i);

    /* access modifiers changed from: package-private */
    public abstract zzs zzb(Map map);

    /* access modifiers changed from: package-private */
    public abstract zzt zzc();

    /* access modifiers changed from: package-private */
    public abstract Map zzd();

    /* access modifiers changed from: package-private */
    public final zzt zze() {
        zzb(Collections.unmodifiableMap(zzd()));
        return zzc();
    }
}
