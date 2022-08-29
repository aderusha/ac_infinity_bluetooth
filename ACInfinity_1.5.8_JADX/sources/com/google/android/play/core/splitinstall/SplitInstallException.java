package com.google.android.play.core.splitinstall;

import com.google.android.play.core.splitinstall.model.zza;
import com.google.android.play.core.tasks.zzj;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class SplitInstallException extends zzj {
    private final int zza;

    public SplitInstallException(int i) {
        super(String.format("Split Install Error(%d): %s", new Object[]{Integer.valueOf(i), zza.zzb(i)}));
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
