package com.alibaba.android.arouter.thread;

import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultPoolExecutor extends ThreadPoolExecutor {
    private static final int CPU_COUNT;
    private static final int INIT_THREAD_COUNT;
    private static final int MAX_THREAD_COUNT;
    private static final long SURPLUS_THREAD_LIFE = 30;
    private static DefaultPoolExecutor instance;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = availableProcessors;
        int i = availableProcessors + 1;
        INIT_THREAD_COUNT = i;
        MAX_THREAD_COUNT = i;
    }

    public static DefaultPoolExecutor getInstance() {
        if (instance == null) {
            synchronized (DefaultPoolExecutor.class) {
                if (instance == null) {
                    instance = new DefaultPoolExecutor(INIT_THREAD_COUNT, MAX_THREAD_COUNT, SURPLUS_THREAD_LIFE, TimeUnit.SECONDS, new ArrayBlockingQueue(64), new DefaultThreadFactory());
                }
            }
        }
        return instance;
    }

    private DefaultPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                ARouter.logger.error("ARouter::", "Task rejected, too many task!");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th == null && (runnable instanceof Future)) {
            try {
                ((Future) runnable).get();
            } catch (CancellationException e) {
                th = e;
            } catch (ExecutionException e2) {
                th = e2.getCause();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        if (th != null) {
            ILogger iLogger = ARouter.logger;
            iLogger.warning("ARouter::", "Running task appeared exception! Thread [" + Thread.currentThread().getName() + "], because [" + th.getMessage() + "]\n" + TextUtils.formatStackTrace(th.getStackTrace()));
        }
    }
}
