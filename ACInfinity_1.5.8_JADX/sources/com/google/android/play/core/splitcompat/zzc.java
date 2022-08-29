package com.google.android.play.core.splitcompat;

import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzc implements ThreadFactory {
    zzc() {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "SplitCompatBackgroundThread");
    }
}
