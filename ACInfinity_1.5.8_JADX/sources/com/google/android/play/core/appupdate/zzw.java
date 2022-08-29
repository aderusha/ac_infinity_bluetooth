package com.google.android.play.core.appupdate;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzw extends AppUpdateOptions {
    private final int zza;
    private final boolean zzb;

    /* synthetic */ zzw(int i, boolean z, zzv zzv) {
        this.zza = i;
        this.zzb = z;
    }

    public final boolean allowAssetPackDeletion() {
        return this.zzb;
    }

    public final int appUpdateType() {
        return this.zza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AppUpdateOptions) {
            AppUpdateOptions appUpdateOptions = (AppUpdateOptions) obj;
            return this.zza == appUpdateOptions.appUpdateType() && this.zzb == appUpdateOptions.allowAssetPackDeletion();
        }
    }

    public final int hashCode() {
        return ((this.zza ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : 1231);
    }

    public final String toString() {
        int i = this.zza;
        boolean z = this.zzb;
        StringBuilder sb = new StringBuilder(73);
        sb.append("AppUpdateOptions{appUpdateType=");
        sb.append(i);
        sb.append(", allowAssetPackDeletion=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
