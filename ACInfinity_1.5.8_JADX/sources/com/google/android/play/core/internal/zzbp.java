package com.google.android.play.core.internal;

import java.io.File;
import java.util.Set;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbp implements zzaz {
    zzbp() {
    }

    static void zzc(ClassLoader classLoader, Set set) {
        zzbk.zzc(classLoader, set, new zzbn());
    }

    static boolean zzd(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzbf.zze(classLoader, file, file2, z, new zzbh(), "path", new zzbo());
    }

    public final void zza(ClassLoader classLoader, Set set) {
        zzc(classLoader, set);
    }

    public final boolean zzb(ClassLoader classLoader, File file, File file2, boolean z) {
        return zzd(classLoader, file, file2, z);
    }
}
