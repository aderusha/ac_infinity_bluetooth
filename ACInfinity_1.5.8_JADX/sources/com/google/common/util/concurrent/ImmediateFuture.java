package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class ImmediateFuture<V> implements ListenableFuture<V> {
    private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

    public boolean cancel(boolean z) {
        return false;
    }

    public abstract V get() throws ExecutionException;

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    ImmediateFuture() {
    }

    public void addListener(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    public V get(long j, TimeUnit timeUnit) throws ExecutionException {
        Preconditions.checkNotNull(timeUnit);
        return get();
    }

    static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
        static final ImmediateSuccessfulFuture<Object> NULL = new ImmediateSuccessfulFuture<>((Object) null);
        @NullableDecl
        private final V value;

        ImmediateSuccessfulFuture(@NullableDecl V v) {
            this.value = v;
        }

        public V get() {
            return this.value;
        }

        public String toString() {
            return super.toString() + "[status=SUCCESS, result=[" + this.value + "]]";
        }
    }

    static final class ImmediateFailedFuture<V> extends AbstractFuture.TrustedFuture<V> {
        ImmediateFailedFuture(Throwable th) {
            setException(th);
        }
    }

    static final class ImmediateCancelledFuture<V> extends AbstractFuture.TrustedFuture<V> {
        ImmediateCancelledFuture() {
            cancel(false);
        }
    }
}
