package p014io.reactivex.internal.observers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.util.NotificationLite;

/* renamed from: io.reactivex.internal.observers.BlockingObserver */
public final class BlockingObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    public static final Object TERMINATED = new Object();
    private static final long serialVersionUID = -4875965440900746268L;
    final Queue<Object> queue;

    public BlockingObserver(Queue<Object> queue2) {
        this.queue = queue2;
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    public void onNext(T t) {
        this.queue.offer(NotificationLite.next(t));
    }

    public void onError(Throwable th) {
        this.queue.offer(NotificationLite.error(th));
    }

    public void onComplete() {
        this.queue.offer(NotificationLite.complete());
    }

    public void dispose() {
        if (DisposableHelper.dispose(this)) {
            this.queue.offer(TERMINATED);
        }
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }
}
