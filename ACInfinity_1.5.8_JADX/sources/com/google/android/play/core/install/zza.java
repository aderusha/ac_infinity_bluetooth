package com.google.android.play.core.install;

import java.util.Objects;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zza extends InstallState {
    private final int zza;
    private final long zzb;
    private final long zzc;
    private final int zzd;
    private final String zze;

    zza(int i, long j, long j2, int i2, String str) {
        this.zza = i;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = i2;
        Objects.requireNonNull(str, "Null packageName");
        this.zze = str;
    }

    public final long bytesDownloaded() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InstallState) {
            InstallState installState = (InstallState) obj;
            return this.zza == installState.installStatus() && this.zzb == installState.bytesDownloaded() && this.zzc == installState.totalBytesToDownload() && this.zzd == installState.installErrorCode() && this.zze.equals(installState.packageName());
        }
    }

    public final int hashCode() {
        int i = this.zza;
        long j = this.zzb;
        long j2 = this.zzc;
        return ((((((((i ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003) ^ this.zzd) * 1000003) ^ this.zze.hashCode();
    }

    public final int installErrorCode() {
        return this.zzd;
    }

    public final int installStatus() {
        return this.zza;
    }

    public final String packageName() {
        return this.zze;
    }

    public final String toString() {
        int i = this.zza;
        long j = this.zzb;
        long j2 = this.zzc;
        int i2 = this.zzd;
        String str = this.zze;
        StringBuilder sb = new StringBuilder(str.length() + 164);
        sb.append("InstallState{installStatus=");
        sb.append(i);
        sb.append(", bytesDownloaded=");
        sb.append(j);
        sb.append(", totalBytesToDownload=");
        sb.append(j2);
        sb.append(", installErrorCode=");
        sb.append(i2);
        sb.append(", packageName=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    public final long totalBytesToDownload() {
        return this.zzc;
    }
}
