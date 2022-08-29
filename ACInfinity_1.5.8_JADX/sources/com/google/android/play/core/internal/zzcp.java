package com.google.android.play.core.internal;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzcp implements zzcs {
    private zzcs zza;

    public static void zzb(zzcs zzcs, zzcs zzcs2) {
        zzcp zzcp = (zzcp) zzcs;
        if (zzcp.zza == null) {
            zzcp.zza = zzcs2;
            return;
        }
        throw new IllegalStateException();
    }

    public final Object zza() {
        zzcs zzcs = this.zza;
        if (zzcs != null) {
            return zzcs.zza();
        }
        throw new IllegalStateException();
    }
}
