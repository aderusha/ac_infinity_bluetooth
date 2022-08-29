package com.google.android.play.core.splitcompat;

import java.io.File;
import java.util.Objects;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzb extends zzs {
    private final File zza;
    private final String zzb;

    zzb(File file, String str) {
        Objects.requireNonNull(file, "Null splitFile");
        this.zza = file;
        Objects.requireNonNull(str, "Null splitId");
        this.zzb = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzs) {
            zzs zzs = (zzs) obj;
            return this.zza.equals(zzs.zza()) && this.zzb.equals(zzs.zzb());
        }
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        String obj = this.zza.toString();
        String str = this.zzb;
        StringBuilder sb = new StringBuilder(obj.length() + 35 + str.length());
        sb.append("SplitFileInfo{splitFile=");
        sb.append(obj);
        sb.append(", splitId=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final File zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        return this.zzb;
    }
}
