package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(mo27511d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0011\u001a\u0004\u0018\u0001H\u0012\"\u0004\b\u0000\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0014H\b¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u001c\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\n\u0010\u0013\u001a\u00060\u001ej\u0002`\u001fH\u0016J\b\u0010 \u001a\u00020\u0006H\u0002J!\u0010!\u001a\u00020\u00102\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#2\u0006\u0010\u0005\u001a\u00020\u0019H\u0000¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\u0017H\u0000¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)H\u0000¢\u0006\u0002\b*J\b\u0010+\u001a\u00020\u0004H\u0016J\r\u0010\u000f\u001a\u00020\u0017H\u0000¢\u0006\u0002\b,R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, mo27512d2 = {"Lkotlinx/coroutines/CommonPool;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "()V", "DEFAULT_PARALLELISM_PROPERTY_NAME", "", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "parallelism", "", "getParallelism", "()I", "pool", "requestedParallelism", "usePrivatePool", "", "Try", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "close", "", "createPlainPool", "Ljava/util/concurrent/ExecutorService;", "createPool", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "getOrCreatePoolSync", "isGoodCommonPool", "fjpClass", "Ljava/lang/Class;", "isGoodCommonPool$kotlinx_coroutines_core", "restore", "restore$kotlinx_coroutines_core", "shutdown", "timeout", "", "shutdown$kotlinx_coroutines_core", "toString", "usePrivatePool$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: CommonPool.kt */
public final class CommonPool extends ExecutorCoroutineDispatcher {
    private static final String DEFAULT_PARALLELISM_PROPERTY_NAME = "kotlinx.coroutines.default.parallelism";
    public static final CommonPool INSTANCE = new CommonPool();
    private static volatile Executor pool;
    private static final int requestedParallelism;
    private static boolean usePrivatePool;

    /* access modifiers changed from: private */
    /* renamed from: isGoodCommonPool$lambda-9  reason: not valid java name */
    public static final void m2415isGoodCommonPool$lambda9() {
    }

    public String toString() {
        return "CommonPool";
    }

    private CommonPool() {
    }

    public Executor getExecutor() {
        Executor executor = pool;
        return executor == null ? getOrCreatePoolSync() : executor;
    }

    static {
        String str;
        int i;
        try {
            str = System.getProperty(DEFAULT_PARALLELISM_PROPERTY_NAME);
        } catch (Throwable unused) {
            str = null;
        }
        if (str == null) {
            i = -1;
        } else {
            Integer intOrNull = StringsKt.toIntOrNull(str);
            if (intOrNull == null || intOrNull.intValue() < 1) {
                throw new IllegalStateException(Intrinsics.stringPlus("Expected positive number in kotlinx.coroutines.default.parallelism, but has ", str).toString());
            }
            i = intOrNull.intValue();
        }
        requestedParallelism = i;
    }

    private final int getParallelism() {
        Integer valueOf = Integer.valueOf(requestedParallelism);
        if (!(valueOf.intValue() > 0)) {
            valueOf = null;
        }
        if (valueOf == null) {
            return RangesKt.coerceAtLeast(Runtime.getRuntime().availableProcessors() - 1, 1);
        }
        return valueOf.intValue();
    }

    private final <T> T Try(Function0<? extends T> function0) {
        try {
            return function0.invoke();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.concurrent.ExecutorService createPool() {
        /*
            r6 = this;
            java.lang.SecurityManager r0 = java.lang.System.getSecurityManager()
            if (r0 == 0) goto L_0x000b
            java.util.concurrent.ExecutorService r0 = r6.createPlainPool()
            return r0
        L_0x000b:
            r0 = 0
            java.lang.String r1 = "java.util.concurrent.ForkJoinPool"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ all -> 0x0013 }
            goto L_0x0014
        L_0x0013:
            r1 = r0
        L_0x0014:
            if (r1 != 0) goto L_0x001b
            java.util.concurrent.ExecutorService r0 = r6.createPlainPool()
            return r0
        L_0x001b:
            boolean r2 = usePrivatePool
            r3 = 0
            if (r2 != 0) goto L_0x004b
            int r2 = requestedParallelism
            if (r2 >= 0) goto L_0x004b
            java.lang.String r2 = "commonPool"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ all -> 0x0039 }
            java.lang.reflect.Method r2 = r1.getMethod(r2, r4)     // Catch:{ all -> 0x0039 }
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0039 }
            java.lang.Object r2 = r2.invoke(r0, r4)     // Catch:{ all -> 0x0039 }
            boolean r4 = r2 instanceof java.util.concurrent.ExecutorService     // Catch:{ all -> 0x0039 }
            if (r4 == 0) goto L_0x0039
            java.util.concurrent.ExecutorService r2 = (java.util.concurrent.ExecutorService) r2     // Catch:{ all -> 0x0039 }
            goto L_0x003a
        L_0x0039:
            r2 = r0
        L_0x003a:
            if (r2 != 0) goto L_0x003d
            goto L_0x004b
        L_0x003d:
            kotlinx.coroutines.CommonPool r4 = INSTANCE
            boolean r4 = r4.isGoodCommonPool$kotlinx_coroutines_core(r1, r2)
            if (r4 == 0) goto L_0x0046
            goto L_0x0047
        L_0x0046:
            r2 = r0
        L_0x0047:
            if (r2 != 0) goto L_0x004a
            goto L_0x004b
        L_0x004a:
            return r2
        L_0x004b:
            r2 = 1
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch:{ all -> 0x0070 }
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0070 }
            r4[r3] = r5     // Catch:{ all -> 0x0070 }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r4)     // Catch:{ all -> 0x0070 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0070 }
            kotlinx.coroutines.CommonPool r4 = INSTANCE     // Catch:{ all -> 0x0070 }
            int r4 = r4.getParallelism()     // Catch:{ all -> 0x0070 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0070 }
            r2[r3] = r4     // Catch:{ all -> 0x0070 }
            java.lang.Object r1 = r1.newInstance(r2)     // Catch:{ all -> 0x0070 }
            boolean r2 = r1 instanceof java.util.concurrent.ExecutorService     // Catch:{ all -> 0x0070 }
            if (r2 == 0) goto L_0x0071
            java.util.concurrent.ExecutorService r1 = (java.util.concurrent.ExecutorService) r1     // Catch:{ all -> 0x0070 }
            r0 = r1
            goto L_0x0071
        L_0x0070:
        L_0x0071:
            if (r0 != 0) goto L_0x0077
            java.util.concurrent.ExecutorService r0 = r6.createPlainPool()
        L_0x0077:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CommonPool.createPool():java.util.concurrent.ExecutorService");
    }

    public final boolean isGoodCommonPool$kotlinx_coroutines_core(Class<?> cls, ExecutorService executorService) {
        executorService.submit(CommonPool$$ExternalSyntheticLambda0.INSTANCE);
        Integer num = null;
        try {
            Object invoke = cls.getMethod("getPoolSize", new Class[0]).invoke(executorService, new Object[0]);
            if (invoke instanceof Integer) {
                num = (Integer) invoke;
            }
        } catch (Throwable unused) {
        }
        if (num != null && num.intValue() >= 1) {
            return true;
        }
        return false;
    }

    private final ExecutorService createPlainPool() {
        return Executors.newFixedThreadPool(getParallelism(), new CommonPool$$ExternalSyntheticLambda2(new AtomicInteger()));
    }

    /* access modifiers changed from: private */
    /* renamed from: createPlainPool$lambda-12  reason: not valid java name */
    public static final Thread m2414createPlainPool$lambda12(AtomicInteger atomicInteger, Runnable runnable) {
        Thread thread = new Thread(runnable, Intrinsics.stringPlus("CommonPool-worker-", Integer.valueOf(atomicInteger.incrementAndGet())));
        thread.setDaemon(true);
        return thread;
    }

    private final synchronized Executor getOrCreatePoolSync() {
        Executor executor;
        executor = pool;
        if (executor == null) {
            ExecutorService createPool = createPool();
            pool = createPool;
            executor = createPool;
        }
        return executor;
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        try {
            Executor executor = pool;
            if (executor == null) {
                executor = getOrCreatePoolSync();
            }
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            executor.execute(timeSource == null ? runnable : timeSource.wrapTask(runnable));
        } catch (RejectedExecutionException unused) {
            AbstractTimeSource timeSource2 = AbstractTimeSourceKt.getTimeSource();
            if (timeSource2 != null) {
                timeSource2.unTrackTask();
            }
            DefaultExecutor.INSTANCE.enqueue(runnable);
        }
    }

    public final synchronized void usePrivatePool$kotlinx_coroutines_core() {
        shutdown$kotlinx_coroutines_core(0);
        usePrivatePool = true;
        pool = null;
    }

    public final synchronized void shutdown$kotlinx_coroutines_core(long j) {
        Executor executor = pool;
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService != null) {
            executorService.shutdown();
            if (j > 0) {
                executorService.awaitTermination(j, TimeUnit.MILLISECONDS);
            }
            for (Runnable enqueue : executorService.shutdownNow()) {
                DefaultExecutor.INSTANCE.enqueue(enqueue);
            }
        }
        pool = CommonPool$$ExternalSyntheticLambda1.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* renamed from: shutdown$lambda-16  reason: not valid java name */
    public static final void m2416shutdown$lambda16(Runnable runnable) {
        throw new RejectedExecutionException("CommonPool was shutdown");
    }

    public final synchronized void restore$kotlinx_coroutines_core() {
        shutdown$kotlinx_coroutines_core(0);
        usePrivatePool = false;
        pool = null;
    }

    public void close() {
        throw new IllegalStateException("Close cannot be invoked on CommonPool".toString());
    }
}
