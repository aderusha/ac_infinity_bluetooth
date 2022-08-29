package com.google.android.play.core.install;

import com.google.android.play.core.install.model.zza;
import com.google.android.play.core.tasks.zzj;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class InstallException extends zzj {
    private final int zza;

    public InstallException(int i) {
        super(String.format("Install Error(%d): %s", new Object[]{Integer.valueOf(i), zza.zza(i)}));
        if (i != 0) {
            this.zza = i;
            return;
        }
        throw new IllegalArgumentException("errorCode should not be 0.");
    }

    public int getErrorCode() {
        return this.zza;
    }
}
