package p014io.reactivex.observers;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.disposables.ListCompositeDisposable;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.util.EndConsumerHelper;

/* renamed from: io.reactivex.observers.ResourceMaybeObserver */
public abstract class ResourceMaybeObserver<T> implements MaybeObserver<T>, Disposable {
    private final ListCompositeDisposable resources = new ListCompositeDisposable();
    private final AtomicReference<Disposable> upstream = new AtomicReference<>();

    /* access modifiers changed from: protected */
    public void onStart() {
    }

    public final void add(Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "resource is null");
        this.resources.add(disposable);
    }

    public final void onSubscribe(Disposable disposable) {
        if (EndConsumerHelper.setOnce(this.upstream, disposable, getClass())) {
            onStart();
        }
    }

    public final void dispose() {
        if (DisposableHelper.dispose(this.upstream)) {
            this.resources.dispose();
        }
    }

    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.upstream.get());
    }
}
