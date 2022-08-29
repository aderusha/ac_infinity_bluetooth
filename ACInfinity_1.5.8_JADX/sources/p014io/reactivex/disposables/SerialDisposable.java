package p014io.reactivex.disposables;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.disposables.SerialDisposable */
public final class SerialDisposable implements Disposable {
    final AtomicReference<Disposable> resource;

    public SerialDisposable() {
        this.resource = new AtomicReference<>();
    }

    public SerialDisposable(Disposable disposable) {
        this.resource = new AtomicReference<>(disposable);
    }

    public boolean set(Disposable disposable) {
        return DisposableHelper.set(this.resource, disposable);
    }

    public boolean replace(Disposable disposable) {
        return DisposableHelper.replace(this.resource, disposable);
    }

    public Disposable get() {
        Disposable disposable = this.resource.get();
        return disposable == DisposableHelper.DISPOSED ? Disposables.disposed() : disposable;
    }

    public void dispose() {
        DisposableHelper.dispose(this.resource);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.resource.get());
    }
}
