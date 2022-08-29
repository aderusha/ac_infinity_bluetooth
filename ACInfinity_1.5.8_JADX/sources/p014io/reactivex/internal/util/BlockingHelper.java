package p014io.reactivex.internal.util;

import java.util.concurrent.CountDownLatch;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.schedulers.NonBlockingThread;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.util.BlockingHelper */
public final class BlockingHelper {
    private BlockingHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static void awaitForComplete(CountDownLatch countDownLatch, Disposable disposable) {
        if (countDownLatch.getCount() != 0) {
            try {
                verifyNonBlocking();
                countDownLatch.await();
            } catch (InterruptedException e) {
                disposable.dispose();
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while waiting for subscription to complete.", e);
            }
        }
    }

    public static void verifyNonBlocking() {
        if (!RxJavaPlugins.isFailOnNonBlockingScheduler()) {
            return;
        }
        if ((Thread.currentThread() instanceof NonBlockingThread) || RxJavaPlugins.onBeforeBlocking()) {
            throw new IllegalStateException("Attempt to block on a Scheduler " + Thread.currentThread().getName() + " that doesn't support blocking operators as they may lead to deadlock");
        }
    }
}
