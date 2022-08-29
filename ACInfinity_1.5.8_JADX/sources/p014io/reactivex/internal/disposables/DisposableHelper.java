package p014io.reactivex.internal.disposables;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.ProtocolViolationException;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.disposables.DisposableHelper */
public enum DisposableHelper implements Disposable {
    DISPOSED;

    public void dispose() {
    }

    public boolean isDisposed() {
        return true;
    }

    public static boolean isDisposed(Disposable disposable) {
        return disposable == DISPOSED;
    }

    public static boolean set(AtomicReference<Disposable> atomicReference, Disposable disposable) {
        Disposable disposable2;
        do {
            disposable2 = atomicReference.get();
            if (disposable2 == DISPOSED) {
                if (disposable == null) {
                    return false;
                }
                disposable.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(disposable2, disposable));
        if (disposable2 == null) {
            return true;
        }
        disposable2.dispose();
        return true;
    }

    public static boolean setOnce(AtomicReference<Disposable> atomicReference, Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "d is null");
        if (atomicReference.compareAndSet((Object) null, disposable)) {
            return true;
        }
        disposable.dispose();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        reportDisposableSet();
        return false;
    }

    public static boolean replace(AtomicReference<Disposable> atomicReference, Disposable disposable) {
        Disposable disposable2;
        do {
            disposable2 = atomicReference.get();
            if (disposable2 == DISPOSED) {
                if (disposable == null) {
                    return false;
                }
                disposable.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(disposable2, disposable));
        return true;
    }

    public static boolean dispose(AtomicReference<Disposable> atomicReference) {
        Disposable andSet;
        Disposable disposable = atomicReference.get();
        DisposableHelper disposableHelper = DISPOSED;
        if (disposable == disposableHelper || (andSet = atomicReference.getAndSet(disposableHelper)) == disposableHelper) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.dispose();
        return true;
    }

    public static boolean validate(Disposable disposable, Disposable disposable2) {
        if (disposable2 == null) {
            RxJavaPlugins.onError(new NullPointerException("next is null"));
            return false;
        } else if (disposable == null) {
            return true;
        } else {
            disposable2.dispose();
            reportDisposableSet();
            return false;
        }
    }

    public static void reportDisposableSet() {
        RxJavaPlugins.onError(new ProtocolViolationException("Disposable already set!"));
    }

    public static boolean trySet(AtomicReference<Disposable> atomicReference, Disposable disposable) {
        if (atomicReference.compareAndSet((Object) null, disposable)) {
            return true;
        }
        if (atomicReference.get() != DISPOSED) {
            return false;
        }
        disposable.dispose();
        return false;
    }
}
