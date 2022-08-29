package com.google.android.play.core.internal;

import java.util.Objects;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzcr {
    public static Object zza(Object obj) {
        Objects.requireNonNull(obj, "Cannot return null from a non-@Nullable @Provides method");
        return obj;
    }

    public static void zzb(Object obj, Class cls) {
        if (obj == null) {
            throw new IllegalStateException(String.valueOf(cls.getCanonicalName()).concat(" must be set"));
        }
    }
}
