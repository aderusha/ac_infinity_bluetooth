package com.google.android.play.core.internal;

import java.io.File;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbc implements zzbd {
    zzbc() {
    }

    public final boolean zza(Object obj, File file, File file2) {
        return new File((String) zzbw.zzg(obj.getClass(), "optimizedPathFor", String.class, File.class, file, File.class, file2)).exists();
    }
}
