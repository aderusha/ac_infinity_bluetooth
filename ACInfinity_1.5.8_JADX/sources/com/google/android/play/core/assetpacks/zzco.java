package com.google.android.play.core.assetpacks;

import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzco {
    private final Map zza = new HashMap();

    zzco() {
    }

    /* access modifiers changed from: package-private */
    public final synchronized double zza(String str) {
        Double d = (Double) this.zza.get(str);
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    /* access modifiers changed from: package-private */
    public final synchronized double zzb(String str, zzdg zzdg) {
        double d;
        d = (((double) ((zzce) zzdg).zzf) + 1.0d) / ((double) ((zzce) zzdg).zzg);
        this.zza.put(str, Double.valueOf(d));
        return d;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzc(String str) {
        this.zza.put(str, Double.valueOf(0.0d));
    }
}
