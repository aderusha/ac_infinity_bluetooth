package com.google.android.play.core.internal;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzci {
    public static Object zza(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException((String) obj2);
    }

    public static void zzb(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }
}
