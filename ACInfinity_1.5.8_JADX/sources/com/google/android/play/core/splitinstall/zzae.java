package com.google.android.play.core.splitinstall;

import com.google.android.play.core.internal.zzcs;
import com.google.android.play.core.splitinstall.testing.zzy;
import java.io.File;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzae implements zzcs {
    private final zzcs zza;

    public zzae(zzcs zzcs) {
        this.zza = zzcs;
    }

    public final /* bridge */ /* synthetic */ Object zza() {
        File file = (File) this.zza.zza();
        if (file == null) {
            return null;
        }
        return zzy.zza(file);
    }
}
