package com.google.android.play.core.assetpacks;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzck extends RuntimeException {
    final int zza;

    zzck(String str) {
        super(str);
        this.zza = -1;
    }

    zzck(String str, int i) {
        super(str);
        this.zza = i;
    }

    zzck(String str, Exception exc) {
        super(str, exc);
        this.zza = -1;
    }

    zzck(String str, Exception exc, int i) {
        super(str, exc);
        this.zza = i;
    }
}
