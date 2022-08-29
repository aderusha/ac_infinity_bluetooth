package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.zza;
import com.google.android.play.core.tasks.zzj;

/* compiled from: com.google.android.play:core@@1.10.3 */
public class AssetPackException extends zzj {
    private final int zza;

    AssetPackException(int i) {
        super(String.format("Asset Pack Download Error(%d): %s", new Object[]{Integer.valueOf(i), zza.zza(i)}));
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
