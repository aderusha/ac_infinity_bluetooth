package com.google.android.play.core.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class zzcm implements Closeable {
    public abstract long zza();

    /* access modifiers changed from: protected */
    public abstract InputStream zzb(long j, long j2) throws IOException;

    public final synchronized InputStream zzc() throws IOException {
        return zzb(0, zza());
    }
}
