package com.google.android.play.core.tasks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.play:core@@1.10.3 */
final class zzo implements zzp {
    private final CountDownLatch zza = new CountDownLatch(1);

    private zzo() {
    }

    public final void onFailure(Exception exc) {
        this.zza.countDown();
    }

    public final void onSuccess(Object obj) {
        this.zza.countDown();
    }

    public final void zza() throws InterruptedException {
        this.zza.await();
    }

    public final boolean zzb(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.zza.await(j, timeUnit);
    }

    /* synthetic */ zzo(zzn zzn) {
    }
}
