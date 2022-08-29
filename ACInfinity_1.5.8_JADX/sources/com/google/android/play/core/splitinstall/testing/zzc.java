package com.google.android.play.core.splitinstall.testing;

import java.util.Map;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzc extends zzt {
    private final Integer zzb;
    private final Map zzc;

    /* synthetic */ zzc(Integer num, Map map, zzb zzb2) {
        this.zzb = num;
        this.zzc = map;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzt) {
            zzt zzt = (zzt) obj;
            Integer num = this.zzb;
            if (num != null ? num.equals(zzt.zza()) : zzt.zza() == null) {
                if (this.zzc.equals(zzt.zzb())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 81 + String.valueOf(valueOf2).length());
        sb.append("LocalTestingConfig{defaultSplitInstallErrorCode=");
        sb.append(valueOf);
        sb.append(", splitInstallErrorCodeByModule=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    public final Integer zza() {
        return this.zzb;
    }

    public final Map zzb() {
        return this.zzc;
    }

    public final int hashCode() {
        int i;
        Integer num = this.zzb;
        if (num == null) {
            i = 0;
        } else {
            i = num.hashCode();
        }
        return ((i ^ 1000003) * 1000003) ^ this.zzc.hashCode();
    }
}
