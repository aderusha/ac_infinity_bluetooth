package com.google.android.play.core.review;

import android.app.PendingIntent;
import java.util.Objects;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zza extends ReviewInfo {
    private final PendingIntent zza;
    private final boolean zzb;

    zza(PendingIntent pendingIntent, boolean z) {
        Objects.requireNonNull(pendingIntent, "Null pendingIntent");
        this.zza = pendingIntent;
        this.zzb = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReviewInfo) {
            ReviewInfo reviewInfo = (ReviewInfo) obj;
            return this.zza.equals(reviewInfo.zza()) && this.zzb == reviewInfo.zzb();
        }
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : 1231);
    }

    public final String toString() {
        String obj = this.zza.toString();
        boolean z = this.zzb;
        StringBuilder sb = new StringBuilder(obj.length() + 40);
        sb.append("ReviewInfo{pendingIntent=");
        sb.append(obj);
        sb.append(", isNoOp=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final PendingIntent zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb() {
        return this.zzb;
    }
}
