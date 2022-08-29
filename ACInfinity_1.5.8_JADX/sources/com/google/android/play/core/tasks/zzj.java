package com.google.android.play.core.tasks;

/* compiled from: com.google.android.play:core@@1.10.3 */
public abstract class zzj extends RuntimeException {
    public zzj(String str) {
        super(str);
    }

    public abstract int getErrorCode();

    public zzj(Throwable th) {
        super(th);
    }
}
