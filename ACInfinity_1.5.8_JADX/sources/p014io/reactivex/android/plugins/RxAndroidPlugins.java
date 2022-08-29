package p014io.reactivex.android.plugins;

import java.util.Objects;
import java.util.concurrent.Callable;
import p014io.reactivex.Scheduler;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Function;

/* renamed from: io.reactivex.android.plugins.RxAndroidPlugins */
public final class RxAndroidPlugins {
    private static volatile Function<Callable<Scheduler>, Scheduler> onInitMainThreadHandler;
    private static volatile Function<Scheduler, Scheduler> onMainThreadHandler;

    public static void setInitMainThreadSchedulerHandler(Function<Callable<Scheduler>, Scheduler> function) {
        onInitMainThreadHandler = function;
    }

    public static Scheduler initMainThreadScheduler(Callable<Scheduler> callable) {
        Objects.requireNonNull(callable, "scheduler == null");
        Function<Callable<Scheduler>, Scheduler> function = onInitMainThreadHandler;
        if (function == null) {
            return callRequireNonNull(callable);
        }
        return applyRequireNonNull(function, callable);
    }

    public static void setMainThreadSchedulerHandler(Function<Scheduler, Scheduler> function) {
        onMainThreadHandler = function;
    }

    public static Scheduler onMainThreadScheduler(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler == null");
        Function function = onMainThreadHandler;
        if (function == null) {
            return scheduler;
        }
        return (Scheduler) apply(function, scheduler);
    }

    public static Function<Callable<Scheduler>, Scheduler> getInitMainThreadSchedulerHandler() {
        return onInitMainThreadHandler;
    }

    public static Function<Scheduler, Scheduler> getOnMainThreadSchedulerHandler() {
        return onMainThreadHandler;
    }

    public static void reset() {
        setInitMainThreadSchedulerHandler((Function<Callable<Scheduler>, Scheduler>) null);
        setMainThreadSchedulerHandler((Function<Scheduler, Scheduler>) null);
    }

    static Scheduler callRequireNonNull(Callable<Scheduler> callable) {
        try {
            Scheduler call = callable.call();
            if (call != null) {
                return call;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw Exceptions.propagate(th);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [io.reactivex.functions.Function, io.reactivex.functions.Function<java.util.concurrent.Callable<io.reactivex.Scheduler>, io.reactivex.Scheduler>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static p014io.reactivex.Scheduler applyRequireNonNull(p014io.reactivex.functions.Function<java.util.concurrent.Callable<p014io.reactivex.Scheduler>, p014io.reactivex.Scheduler> r0, java.util.concurrent.Callable<p014io.reactivex.Scheduler> r1) {
        /*
            java.lang.Object r0 = apply(r0, r1)
            io.reactivex.Scheduler r0 = (p014io.reactivex.Scheduler) r0
            java.lang.String r1 = "Scheduler Callable returned null"
            java.util.Objects.requireNonNull(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p014io.reactivex.android.plugins.RxAndroidPlugins.applyRequireNonNull(io.reactivex.functions.Function, java.util.concurrent.Callable):io.reactivex.Scheduler");
    }

    static <T, R> R apply(Function<T, R> function, T t) {
        try {
            return function.apply(t);
        } catch (Throwable th) {
            throw Exceptions.propagate(th);
        }
    }

    private RxAndroidPlugins() {
        throw new AssertionError("No instances.");
    }
}
