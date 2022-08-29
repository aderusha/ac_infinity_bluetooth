package com.google.android.play.core.assetpacks;

/* compiled from: com.google.android.play:core@@1.10.3 */
abstract class zzet {
    zzet() {
    }

    /* access modifiers changed from: package-private */
    public abstract int zza();

    /* access modifiers changed from: package-private */
    public abstract long zzb();

    /* access modifiers changed from: package-private */
    public abstract String zzc();

    /* access modifiers changed from: package-private */
    public abstract boolean zzd();

    /* access modifiers changed from: package-private */
    public abstract boolean zze();

    /* access modifiers changed from: package-private */
    public abstract byte[] zzf();

    /* access modifiers changed from: package-private */
    public final boolean zzg() {
        if (zzc() == null) {
            return false;
        }
        return zzc().endsWith("/");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzh() {
        return zza() == 0;
    }
}
