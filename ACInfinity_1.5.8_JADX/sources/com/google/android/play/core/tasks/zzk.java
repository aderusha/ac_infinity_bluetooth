package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzk implements Executor {
    zzk() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
