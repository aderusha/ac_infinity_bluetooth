package p014io.reactivex.internal.observers;

import java.util.concurrent.CountDownLatch;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.util.BlockingHelper;
import p014io.reactivex.internal.util.ExceptionHelper;

/* renamed from: io.reactivex.internal.observers.BlockingBaseObserver */
public abstract class BlockingBaseObserver<T> extends CountDownLatch implements Observer<T>, Disposable {
    volatile boolean cancelled;
    Throwable error;
    Disposable upstream;
    T value;

    public BlockingBaseObserver() {
        super(1);
    }

    public final void onSubscribe(Disposable disposable) {
        this.upstream = disposable;
        if (this.cancelled) {
            disposable.dispose();
        }
    }

    public final void onComplete() {
        countDown();
    }

    public final void dispose() {
        this.cancelled = true;
        Disposable disposable = this.upstream;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public final boolean isDisposed() {
        return this.cancelled;
    }

    public final T blockingGet() {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (InterruptedException e) {
                dispose();
                throw ExceptionHelper.wrapOrThrow(e);
            }
        }
        Throwable th = this.error;
        if (th == null) {
            return this.value;
        }
        throw ExceptionHelper.wrapOrThrow(th);
    }
}
