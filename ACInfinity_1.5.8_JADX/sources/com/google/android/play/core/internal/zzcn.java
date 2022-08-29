package com.google.android.play.core.internal;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzcn extends zzcm {
    private final zzcm zza;
    private final long zzb;
    private final long zzc;

    public zzcn(zzcm zzcm, long j, long j2) {
        this.zza = zzcm;
        long zzd = zzd(j);
        this.zzb = zzd;
        this.zzc = zzd(zzd + j2);
    }

    private final long zzd(long j) {
        if (j < 0) {
            return 0;
        }
        return j > this.zza.zza() ? this.zza.zza() : j;
    }

    public final void close() throws IOException {
    }

    public final long zza() {
        return this.zzc - this.zzb;
    }

    /* access modifiers changed from: protected */
    public final InputStream zzb(long j, long j2) throws IOException {
        long zzd = zzd(this.zzb);
        return this.zza.zzb(zzd, zzd(j2 + zzd) - zzd);
    }
}
