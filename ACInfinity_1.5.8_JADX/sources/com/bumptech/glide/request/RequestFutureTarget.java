package com.bumptech.glide.request;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFutureTarget<R> implements FutureTarget<R>, RequestListener<R>, Runnable {
    private static final Waiter DEFAULT_WAITER = new Waiter();
    private final boolean assertBackgroundThread;
    private GlideException exception;
    private final int height;
    private boolean isCancelled;
    private boolean loadFailed;
    private final Handler mainHandler;
    private Request request;
    private R resource;
    private boolean resultReceived;
    private final Waiter waiter;
    private final int width;

    public void onDestroy() {
    }

    public void onLoadCleared(Drawable drawable) {
    }

    public void onLoadStarted(Drawable drawable) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void removeCallback(SizeReadyCallback sizeReadyCallback) {
    }

    public RequestFutureTarget(Handler handler, int i, int i2) {
        this(handler, i, i2, true, DEFAULT_WAITER);
    }

    RequestFutureTarget(Handler handler, int i, int i2, boolean z, Waiter waiter2) {
        this.mainHandler = handler;
        this.width = i;
        this.height = i2;
        this.assertBackgroundThread = z;
        this.waiter = waiter2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean cancel(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isDone()     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x000a
            r3 = 0
            monitor-exit(r2)
            return r3
        L_0x000a:
            r0 = 1
            r2.isCancelled = r0     // Catch:{ all -> 0x0019 }
            com.bumptech.glide.request.RequestFutureTarget$Waiter r1 = r2.waiter     // Catch:{ all -> 0x0019 }
            r1.notifyAll(r2)     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0017
            r2.clearOnMainThread()     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r2)
            return r0
        L_0x0019:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.RequestFutureTarget.cancel(boolean):boolean");
    }

    public synchronized boolean isCancelled() {
        return this.isCancelled;
    }

    public synchronized boolean isDone() {
        return this.isCancelled || this.resultReceived || this.loadFailed;
    }

    public R get() throws InterruptedException, ExecutionException {
        try {
            return doGet((Long) null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public R get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return doGet(Long.valueOf(timeUnit.toMillis(j)));
    }

    public void getSize(SizeReadyCallback sizeReadyCallback) {
        sizeReadyCallback.onSizeReady(this.width, this.height);
    }

    public void setRequest(Request request2) {
        this.request = request2;
    }

    public Request getRequest() {
        return this.request;
    }

    public synchronized void onLoadFailed(Drawable drawable) {
    }

    public synchronized void onResourceReady(R r, Transition<? super R> transition) {
    }

    private synchronized R doGet(Long l) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.assertBackgroundThread && !isDone()) {
            Util.assertBackgroundThread();
        }
        if (this.isCancelled) {
            throw new CancellationException();
        } else if (this.loadFailed) {
            throw new ExecutionException(this.exception);
        } else if (this.resultReceived) {
            return this.resource;
        } else {
            if (l == null) {
                this.waiter.waitForTimeout(this, 0);
            } else if (l.longValue() > 0) {
                long currentTimeMillis = System.currentTimeMillis();
                long longValue = l.longValue() + currentTimeMillis;
                while (!isDone() && currentTimeMillis < longValue) {
                    this.waiter.waitForTimeout(this, longValue - currentTimeMillis);
                    currentTimeMillis = System.currentTimeMillis();
                }
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else if (this.loadFailed) {
                throw new ExecutionException(this.exception);
            } else if (this.isCancelled) {
                throw new CancellationException();
            } else if (this.resultReceived) {
                return this.resource;
            } else {
                throw new TimeoutException();
            }
        }
    }

    public void run() {
        Request request2 = this.request;
        if (request2 != null) {
            request2.clear();
            this.request = null;
        }
    }

    private void clearOnMainThread() {
        this.mainHandler.post(this);
    }

    public synchronized boolean onLoadFailed(GlideException glideException, Object obj, Target<R> target, boolean z) {
        this.loadFailed = true;
        this.exception = glideException;
        this.waiter.notifyAll(this);
        return false;
    }

    public synchronized boolean onResourceReady(R r, Object obj, Target<R> target, DataSource dataSource, boolean z) {
        this.resultReceived = true;
        this.resource = r;
        this.waiter.notifyAll(this);
        return false;
    }

    static class Waiter {
        Waiter() {
        }

        /* access modifiers changed from: package-private */
        public void waitForTimeout(Object obj, long j) throws InterruptedException {
            obj.wait(j);
        }

        /* access modifiers changed from: package-private */
        public void notifyAll(Object obj) {
            obj.notifyAll();
        }
    }
}
