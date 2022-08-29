package com.google.android.play.core.assetpacks;

import java.util.Objects;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbl extends AssetLocation {
    private final String zza;
    private final long zzb;
    private final long zzc;

    zzbl(String str, long j, long j2) {
        Objects.requireNonNull(str, "Null path");
        this.zza = str;
        this.zzb = j;
        this.zzc = j2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetLocation) {
            AssetLocation assetLocation = (AssetLocation) obj;
            return this.zza.equals(assetLocation.path()) && this.zzb == assetLocation.offset() && this.zzc == assetLocation.size();
        }
    }

    public final int hashCode() {
        int hashCode = this.zza.hashCode();
        long j = this.zzb;
        long j2 = this.zzc;
        return ((((hashCode ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2));
    }

    public final long offset() {
        return this.zzb;
    }

    public final String path() {
        return this.zza;
    }

    public final long size() {
        return this.zzc;
    }

    public final String toString() {
        String str = this.zza;
        long j = this.zzb;
        long j2 = this.zzc;
        StringBuilder sb = new StringBuilder(str.length() + 76);
        sb.append("AssetLocation{path=");
        sb.append(str);
        sb.append(", offset=");
        sb.append(j);
        sb.append(", size=");
        sb.append(j2);
        sb.append("}");
        return sb.toString();
    }
}
