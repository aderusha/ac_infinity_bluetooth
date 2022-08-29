package com.google.android.play.core.appupdate;

import com.google.android.play.core.appupdate.AppUpdateOptions;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzu extends AppUpdateOptions.Builder {
    private int zza;
    private boolean zzb;
    private byte zzc;

    zzu() {
    }

    public final AppUpdateOptions build() {
        if (this.zzc == 3) {
            return new zzw(this.zza, this.zzb, (zzv) null);
        }
        StringBuilder sb = new StringBuilder();
        if ((this.zzc & 1) == 0) {
            sb.append(" appUpdateType");
        }
        if ((this.zzc & 2) == 0) {
            sb.append(" allowAssetPackDeletion");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public final AppUpdateOptions.Builder setAllowAssetPackDeletion(boolean z) {
        this.zzb = z;
        this.zzc = (byte) (this.zzc | 2);
        return this;
    }

    public final AppUpdateOptions.Builder setAppUpdateType(int i) {
        this.zza = i;
        this.zzc = (byte) (this.zzc | 1);
        return this;
    }
}
