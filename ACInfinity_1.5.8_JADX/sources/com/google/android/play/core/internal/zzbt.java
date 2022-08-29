package com.google.android.play.core.internal;

/* compiled from: com.google.android.play:core@@1.10.3 */
public final class zzbt extends RuntimeException {
    public zzbt(String str) {
        super(str);
    }

    public zzbt(String str, Throwable th) {
        super("Failed to initialize FileStorage", th);
    }
}
