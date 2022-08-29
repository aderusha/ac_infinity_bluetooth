package p014io.reactivex.internal.observers;

/* renamed from: io.reactivex.internal.observers.BlockingLastObserver */
public final class BlockingLastObserver<T> extends BlockingBaseObserver<T> {
    public void onNext(T t) {
        this.value = t;
    }

    public void onError(Throwable th) {
        this.value = null;
        this.error = th;
        countDown();
    }
}
