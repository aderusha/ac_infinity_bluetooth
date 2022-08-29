package com.bumptech.glide.load.engine.executor;

import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class GlideExecutor implements ExecutorService {
    private static final String ANIMATION_EXECUTOR_NAME = "animation";
    private static final String DEFAULT_DISK_CACHE_EXECUTOR_NAME = "disk-cache";
    private static final int DEFAULT_DISK_CACHE_EXECUTOR_THREADS = 1;
    private static final String DEFAULT_SOURCE_EXECUTOR_NAME = "source";
    private static final long KEEP_ALIVE_TIME_MS = TimeUnit.SECONDS.toMillis(10);
    private static final int MAXIMUM_AUTOMATIC_THREAD_COUNT = 4;
    private static final String SOURCE_UNLIMITED_EXECUTOR_NAME = "source-unlimited";
    private static final String TAG = "GlideExecutor";
    private static volatile int bestThreadCount;
    private final ExecutorService delegate;

    public static GlideExecutor newDiskCacheExecutor() {
        return newDiskCacheExecutor(1, DEFAULT_DISK_CACHE_EXECUTOR_NAME, UncaughtThrowableStrategy.DEFAULT);
    }

    public static GlideExecutor newDiskCacheExecutor(UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return newDiskCacheExecutor(1, DEFAULT_DISK_CACHE_EXECUTOR_NAME, uncaughtThrowableStrategy);
    }

    public static GlideExecutor newDiskCacheExecutor(int i, String str, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return new GlideExecutor(new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new DefaultThreadFactory(str, uncaughtThrowableStrategy, true)));
    }

    public static GlideExecutor newSourceExecutor() {
        return newSourceExecutor(calculateBestThreadCount(), DEFAULT_SOURCE_EXECUTOR_NAME, UncaughtThrowableStrategy.DEFAULT);
    }

    public static GlideExecutor newSourceExecutor(UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return newSourceExecutor(calculateBestThreadCount(), DEFAULT_SOURCE_EXECUTOR_NAME, uncaughtThrowableStrategy);
    }

    public static GlideExecutor newSourceExecutor(int i, String str, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return new GlideExecutor(new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new DefaultThreadFactory(str, uncaughtThrowableStrategy, false)));
    }

    public static GlideExecutor newUnlimitedSourceExecutor() {
        return new GlideExecutor(new ThreadPoolExecutor(0, Integer.MAX_VALUE, KEEP_ALIVE_TIME_MS, TimeUnit.MILLISECONDS, new SynchronousQueue(), new DefaultThreadFactory(SOURCE_UNLIMITED_EXECUTOR_NAME, UncaughtThrowableStrategy.DEFAULT, false)));
    }

    public static GlideExecutor newAnimationExecutor() {
        return newAnimationExecutor(calculateBestThreadCount() >= 4 ? 2 : 1, UncaughtThrowableStrategy.DEFAULT);
    }

    public static GlideExecutor newAnimationExecutor(int i, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return new GlideExecutor(new ThreadPoolExecutor(0, i, KEEP_ALIVE_TIME_MS, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new DefaultThreadFactory(ANIMATION_EXECUTOR_NAME, uncaughtThrowableStrategy, true)));
    }

    GlideExecutor(ExecutorService executorService) {
        this.delegate = executorService;
    }

    public void execute(Runnable runnable) {
        this.delegate.execute(runnable);
    }

    public Future<?> submit(Runnable runnable) {
        return this.delegate.submit(runnable);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.delegate.invokeAll(collection);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.invokeAll(collection, j, timeUnit);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return this.delegate.invokeAny(collection);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.delegate.invokeAny(collection, j, timeUnit);
    }

    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.delegate.submit(runnable, t);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return this.delegate.submit(callable);
    }

    public void shutdown() {
        this.delegate.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.awaitTermination(j, timeUnit);
    }

    public String toString() {
        return this.delegate.toString();
    }

    public static int calculateBestThreadCount() {
        if (bestThreadCount == 0) {
            bestThreadCount = Math.min(4, RuntimeCompat.availableProcessors());
        }
        return bestThreadCount;
    }

    public interface UncaughtThrowableStrategy {
        public static final UncaughtThrowableStrategy DEFAULT;
        public static final UncaughtThrowableStrategy IGNORE = new UncaughtThrowableStrategy() {
            public void handle(Throwable th) {
            }
        };
        public static final UncaughtThrowableStrategy LOG;
        public static final UncaughtThrowableStrategy THROW = new UncaughtThrowableStrategy() {
            public void handle(Throwable th) {
                if (th != null) {
                    throw new RuntimeException("Request threw uncaught throwable", th);
                }
            }
        };

        void handle(Throwable th);

        static {
            C09082 r0 = new UncaughtThrowableStrategy() {
                public void handle(Throwable th) {
                    if (th != null && Log.isLoggable(GlideExecutor.TAG, 6)) {
                        Log.e(GlideExecutor.TAG, "Request threw uncaught throwable", th);
                    }
                }
            };
            LOG = r0;
            DEFAULT = r0;
        }
    }

    private static final class DefaultThreadFactory implements ThreadFactory {
        private static final int DEFAULT_PRIORITY = 9;
        private final String name;
        final boolean preventNetworkOperations;
        private int threadNum;
        final UncaughtThrowableStrategy uncaughtThrowableStrategy;

        DefaultThreadFactory(String str, UncaughtThrowableStrategy uncaughtThrowableStrategy2, boolean z) {
            this.name = str;
            this.uncaughtThrowableStrategy = uncaughtThrowableStrategy2;
            this.preventNetworkOperations = z;
        }

        public synchronized Thread newThread(Runnable runnable) {
            C09061 r0;
            r0 = new Thread(runnable, "glide-" + this.name + "-thread-" + this.threadNum) {
                public void run() {
                    Process.setThreadPriority(9);
                    if (DefaultThreadFactory.this.preventNetworkOperations) {
                        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                    }
                    try {
                        super.run();
                    } catch (Throwable th) {
                        DefaultThreadFactory.this.uncaughtThrowableStrategy.handle(th);
                    }
                }
            };
            this.threadNum = this.threadNum + 1;
            return r0;
        }
    }
}
