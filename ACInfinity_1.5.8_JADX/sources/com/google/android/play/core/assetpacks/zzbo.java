package com.google.android.play.core.assetpacks;

import java.util.Map;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzbo extends AssetPackStates {
    private final long zza;
    private final Map zzb;

    zzbo(long j, Map map) {
        this.zza = j;
        this.zzb = map;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackStates) {
            AssetPackStates assetPackStates = (AssetPackStates) obj;
            return this.zza == assetPackStates.totalBytes() && this.zzb.equals(assetPackStates.packStates());
        }
    }

    public final int hashCode() {
        long j = this.zza;
        return this.zzb.hashCode() ^ ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003);
    }

    public final Map<String, AssetPackState> packStates() {
        return this.zzb;
    }

    public final String toString() {
        long j = this.zza;
        String obj = this.zzb.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 61);
        sb.append("AssetPackStates{totalBytes=");
        sb.append(j);
        sb.append(", packStates=");
        sb.append(obj);
        sb.append("}");
        return sb.toString();
    }

    public final long totalBytes() {
        return this.zza;
    }
}
