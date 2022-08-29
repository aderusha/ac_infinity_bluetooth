package com.google.android.play.core.tasks;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzl implements Executor {
    private final Handler zza = new Handler(Looper.getMainLooper());

    public final void execute(Runnable runnable) {
        this.zza.post(runnable);
    }
}
